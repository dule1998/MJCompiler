package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPc;

	List<Boolean> des = new ArrayList<Boolean>();
	List<Boolean> addOp = new ArrayList<Boolean>();
	List<Integer> mulOp = new ArrayList<Integer>();
	List<Integer> pcStk = new ArrayList<Integer>();
	List<Integer> pcStk1 = new ArrayList<Integer>();
	boolean arr = false;

	public int getMainPc() {
		return mainPc;
	}

	public void visit(PrintStatement printStatement) {
		if (printStatement.getExpr().struct == Tab.intType) {
			Code.loadConst(4);
			Code.put(Code.print);
		} else {
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
	}

	public void visit(ReadStatement readStatement) {
		if (readStatement.getDesignator().obj.getType() != Tab.charType) {
			Code.put(Code.read);
		} else {
			Code.put(Code.bread);
		}
		if (des.remove(des.size() - 1) && readStatement.getDesignator().obj.getType().getKind() == Struct.Array) {
			if (readStatement.getDesignator().obj.getType().getElemType().getKind() == Struct.Char) {
				Code.put(Code.bastore);
			} else {
				Code.put(Code.astore);
			}
		} else {
			Code.store(readStatement.getDesignator().obj);
		}
	}

	public void visit(NumConstFactor numConstFactor) {
		Obj con = Tab.insert(Obj.Con, "$", numConstFactor.struct);
		con.setLevel(0);
		con.setAdr(numConstFactor.getN1());

		Code.load(con);
	}

	public void visit(CharConstFactor charConstFactor) {
		Obj con = Tab.insert(Obj.Con, "$", charConstFactor.struct);
		con.setLevel(0);
		con.setAdr(charConstFactor.getC1().charAt(1));

		Code.load(con);
	}

	public void visit(BoolConstFactor boolConstFactor) {
		Obj con = Tab.insert(Obj.Con, "$", boolConstFactor.struct);
		con.setLevel(0);
		con.setAdr(boolConstFactor.getB1().equals("true") ? 1 : 0);

		Code.load(con);
	}

	public void visit(MethodName methodName) {

		if ("main".equals(methodName.getMethName())) {
			mainPc = Code.pc;
		}
		methodName.obj.setAdr(Code.pc);
		SyntaxNode methodNode = methodName.getParent();

		CounterVisitor varCnt = new CounterVisitor();
		methodNode.traverseTopDown(varCnt);

		Code.put(Code.enter);
		Code.put(0);
		Code.put(varCnt.getCount());

	}

	public void visit(MethodDecl methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	public void visit(DesEqu desEqu) {
		if (des.remove(des.size() - 1) && desEqu.getDesignator().obj.getType().getKind() == Struct.Array) {
			if (desEqu.getDesignator().obj.getType().getElemType().getKind() == Struct.Char) {
				Code.put(Code.bastore);
			} else {
				Code.put(Code.astore);
			}
		} else {
			Code.store(desEqu.getDesignator().obj);
		}
	}

	public void visit(Designator designator) {
		SyntaxNode parent = designator.getParent();

		if (DesignFactor.class == parent.getClass() || DesInc.class == parent.getClass()
				|| DesDec.class == parent.getClass()) {
			if (des.remove(des.size() - 1) && designator.obj.getType().getKind() == Struct.Array) {
				if (designator.obj.getType().getElemType().getKind() == Struct.Char) {
					Code.put(Code.baload);
				} else {
					Code.put(Code.aload);
				}
			}
		}
	}

	public void visit(NewType newType) {
		Code.put(Code.newarray);
		if (newType.struct != Tab.charType) {
			Code.put(1);
		} else {
			Code.put(0);
		}
	}

	public void visit(ExprDesignator exprDesignator) {
		des.add(true);
	}

	public void visit(NoSqu NoSqu) {
		des.add(false);
	}

	public void visit(DesName desName) {
		SyntaxNode parent = desName.getParent();
		SyntaxNode grandParent = parent.getParent();
		ArrayVisitor arrFound = new ArrayVisitor();
		parent.traverseTopDown(arrFound);
		if (DesignFactor.class == grandParent.getClass()
				|| (DesEqu.class == grandParent.getClass()
						&& ((Designator) parent).obj.getType().getKind() == Struct.Array && arrFound.arr)
				|| DesInc.class == grandParent.getClass() || DesDec.class == grandParent.getClass()
				|| (ReadStatement.class == grandParent.getClass()
						&& ((Designator) parent).obj.getType().getKind() == Struct.Array && arrFound.arr)) {
			Code.load(((Designator) parent).obj);
		}
	}

	public void visit(Plus plus) {
		addOp.add(true);
	}

	public void visit(Minus minus) {
		addOp.add(false);
	}

	public void visit(AddExpr addExpr) {
		if (addOp.remove(addOp.size() - 1)) {
			Code.put(Code.add);
		} else {
			Code.put(Code.sub);
		}
	}

	public void visit(Mul mul) {
		mulOp.add(0);
	}

	public void visit(Div div) {
		mulOp.add(1);
	}

	public void visit(Mod mod) {
		mulOp.add(2);
	}

	public void visit(MulTerm mulTerm) {
		int mulop = mulOp.remove(mulOp.size() - 1);
		if (mulop == 0) {
			Code.put(Code.mul);
		} else if (mulop == 1) {
			Code.put(Code.div);
		} else {
			Code.put(Code.rem);
		}
	}

	public void visit(DesInc desInc) {
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(desInc.getDesignator().obj);
	}

	public void visit(DesDec desDec) {
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(desDec.getDesignator().obj);
	}

	public void visit(MinTerm minTerm) {
		Code.put(Code.neg);
	}

	public void visit(Cond cond) {
		Code.loadConst(0);
		pcStk.add(Code.pc);
		Code.put(Code.jcc);
		Code.put2(0);
	}

	public void visit(FirstExpr firstExpr) {
		pcStk1.add(Code.pc);
		Code.put(Code.jmp);
		Code.put2(0);
		int pcPos = pcStk.remove(pcStk.size() - 1);
		Code.put2(pcPos + 1, Code.pc - pcPos);
	}

	public void visit(SecondExpr secondExpr) {
		int pcPos = pcStk1.remove(pcStk1.size() - 1);
		Code.put2(pcPos + 1, Code.pc - pcPos);
	}

}
