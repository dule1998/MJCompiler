package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor {

	Obj currentMethod = null;
	Struct currentType = null;
	List<Struct> designatorType = new ArrayList<Struct>();
	boolean errorDetected = false;
	boolean arrDetect = false;
	int nVars;

	Logger log = Logger.getLogger(getClass());

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}

	public void visit(VarWith varWith) {
		report_info("Deklarisana promenljiva " + varWith.getVarName().getVarName(), varWith);
		Obj varNode = Tab.insert(Obj.Var, varWith.getVarName().getVarName(), new Struct(Struct.Array, currentType));
	}

	public void visit(VarWithout varWithout) {
		report_info("Deklarisana promenljiva " + varWithout.getVarName().getVarName(), varWithout);
		Obj varNode = Tab.insert(Obj.Var, varWithout.getVarName().getVarName(), currentType);
	}

	public void visit(GlobVarWith globVarWith) {
		report_info("Deklarisana promenljiva " + globVarWith.getVarName().getVarName(), globVarWith);
		Obj varNode = Tab.insert(Obj.Var, globVarWith.getVarName().getVarName(), new Struct(Struct.Array, currentType));
	}

	public void visit(GlobVarWithout globVarWithout) {
		report_info("Deklarisana promenljiva " + globVarWithout.getVarName().getVarName(), globVarWithout);
		Obj varNode = Tab.insert(Obj.Var, globVarWithout.getVarName().getVarName(), currentType);
	}
	
	public void visit(GlobalFirst globalFirst) {
		report_info("Deklarisana promenljiva " + globalFirst.getVarName(), globalFirst);
		Obj varNode;
		if (arrDetect) {
			varNode = Tab.insert(Obj.Var, globalFirst.getVarName(), new Struct(Struct.Array, currentType));
		} else {
			varNode = Tab.insert(Obj.Var, globalFirst.getVarName(), currentType);
		}
	}
	
	public void visit(With with) {
		arrDetect = true;
	}
	
	public void visit(Without without) {
		arrDetect = false;
	}

	public void visit(NumConst numConst) {
		report_info("Definisana simbolcika konstanta " + numConst.getConstName(), numConst);
		numConst.struct = Tab.intType;
		Obj constNum = Tab.insert(Obj.Con, numConst.getConstName(), numConst.struct);
		constNum.setAdr(numConst.getNConst());
	}

	public void visit(NumConstDeclarations numConstDeclarations) {
		report_info("Definisana simbolcika konstanta " + numConstDeclarations.getConstName(), numConstDeclarations);
		numConstDeclarations.struct = numConstDeclarations.getNumConstList().struct;
		Obj constNum = Tab.insert(Obj.Con, numConstDeclarations.getConstName(), numConstDeclarations.struct);
		constNum.setAdr(numConstDeclarations.getN3());
	}

	public void visit(NumConstDecList numConstDecList) {
		numConstDecList.struct = numConstDecList.getNumConstList().struct;
	}

	public void visit(CharConst charConst) {
		report_info("Definisana simbolcika konstanta " + charConst.getConstName(), charConst);
		charConst.struct = Tab.charType;
		Obj constChar = Tab.insert(Obj.Con, charConst.getConstName(), charConst.struct);
		constChar.setAdr(charConst.getCConst().charAt(1));
	}

	public void visit(CharConstDeclarations charConstDeclarations) {
		report_info("Definisana simbolcika konstanta " + charConstDeclarations.getConstName(), charConstDeclarations);
		charConstDeclarations.struct = charConstDeclarations.getCharConstList().struct;
		Obj constChar = Tab.insert(Obj.Con, charConstDeclarations.getConstName(), charConstDeclarations.struct);
		constChar.setAdr(charConstDeclarations.getC3().charAt(1));
	}

	public void visit(CharConstDecList charConstDecList) {
		charConstDecList.struct = charConstDecList.getCharConstList().struct;
	}

	public void visit(BoolConst boolConst) {
		report_info("Definisana simbolcika konstanta " + boolConst.getConstName(), boolConst);
		boolConst.struct = Tabela.boolType;
		Obj constBool = Tab.insert(Obj.Con, boolConst.getConstName(), boolConst.struct);
		constBool.setAdr(boolConst.getBConst().equals("true") ? 1 : 0);
	}

	public void visit(BoolConstDeclarations boolConstDeclarations) {
		report_info("Definisana simbolcika konstanta " + boolConstDeclarations.getConstName(), boolConstDeclarations);
		boolConstDeclarations.struct = boolConstDeclarations.getBoolConstList().struct;
		Obj constBool = Tab.insert(Obj.Con, boolConstDeclarations.getConstName(), boolConstDeclarations.struct);
		constBool.setAdr(boolConstDeclarations.getB3().equals("true") ? 1 : 0);
	}

	public void visit(BoolConstDecList boolConstDecList) {
		boolConstDecList.struct = boolConstDecList.getBoolConstList().struct;
	}

	public void visit(ConstDecl constDecl) {
		if (!(constDecl.getConstList().struct.equals(constDecl.getType().struct))) {
			report_error("Greska: Pogresan tip", constDecl);
		}
	}

	public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getPrName(), Tab.noType);
		Tab.openScope();
	}

	public void visit(Program program) {
		nVars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}

	public void visit(Type type) {
		Obj typeNode = Tab.find(type.getTypeName());
		if (typeNode == Tab.noObj) {
			report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola! ", null);
			type.struct = Tab.noType;
			currentType = type.struct;
		} else {
			if (Obj.Type == typeNode.getKind()) {
				type.struct = typeNode.getType();
				currentType = type.struct;
			} else {
				report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
				type.struct = Tab.noType;
				currentType = type.struct;
			}
		}
	}

	public void visit(MethodName methodName) {
		currentMethod = Tab.insert(Obj.Meth, methodName.getMethName(), Tab.noType);
		if (!(methodName.getMethName().equals("main"))) {
			report_error(
					"Greska: Pogresan naziv glavne funkcije, nazvana je " + methodName.getMethName() + " umesto main",
					methodName);
		}
		methodName.obj = currentMethod;
		Tab.openScope();
		report_info("Obradjuje se funkcija " + methodName.getMethName(), methodName);
	}

	public void visit(MethodDecl methodDecl) {
		Tab.chainLocalSymbols(methodDecl.getMethodName().obj);
		Tab.closeScope();

		currentMethod = null;
	}

	public void visit(Designator designator) {
		Obj obj = Tab.find(designator.getDesName().getName());
		if (obj == Tab.noObj) {
			report_error("Greska na liniji " + designator.getLine() + " : ime " + designator.getDesName().getName()
					+ " nije deklarisano! ", null);
		} else if (designator.getExprDes().struct == Tab.intType) {
			if (obj.getType().getKind() != Struct.Array) {
				report_error("Greska na liniji " + designator.getLine() + " : " + designator.getDesName().getName()
						+ " nije niz! ", null);
			} else {
				designatorType.add(obj.getType().getElemType());
			}
		}
		if (obj != Tab.noObj) {
			report_info("Obradjuje se cvor " + "["
					+ ((obj.getKind() == 0) ? "Con "
							: ((obj.getKind() == 1) ? "Var " : ((obj.getKind() == 2) ? "Var " : "Type ")))
					+ designator.getDesName().getName() + ": "
					+ ((obj.getType().getKind() == Struct.Array)
							? ("Array of " + ((obj.getType().getElemType() == Tab.intType) ? "int,"
									: ((obj.getType().getElemType() == Tab.charType) ? "char," : "bool,")))
							: ((obj.getType() == Tab.intType) ? "int,"
									: ((obj.getType() == Tab.charType) ? "char," : "bool,")))
					+ " " + obj.getAdr() + ", " + obj.getLevel() + "]" + " na liniji " + designator.getLine(), null);
		}
		designator.obj = obj;
	}

	public void visit(ExprDesignator exprDesignator) {
		Struct t = exprDesignator.getExpr().struct;
		if (t == Tab.intType) {
			exprDesignator.struct = t;
		} else {
			report_error("Greska na liniji " + exprDesignator.getLine() + " : indeks niza mora biti celobrojnog tipa.",
					null);
			exprDesignator.struct = t;
		}
	}

	public void visit(MulTerm mulTerm) {
		Struct tf = mulTerm.getFactor().struct;
		Struct t = mulTerm.getTerm().struct;
		if (tf.equals(t) && tf == Tab.intType) {
			mulTerm.struct = tf;
		} else {
			report_error("Greska na liniji " + mulTerm.getLine() + " : nekompatibilni tipovi u izrazu za mnozenje.",
					null);
			mulTerm.struct = Tab.noType;
		}
	}

	public void visit(TermFactor termFactor) {
		termFactor.struct = termFactor.getFactor().struct;
	}

	public void visit(NumConstFactor numConstFactor) {
		numConstFactor.struct = Tab.intType;
	}

	public void visit(CharConstFactor charConstFactor) {
		charConstFactor.struct = Tab.charType;
	}

	public void visit(BoolConstFactor boolConstFactor) {
		boolConstFactor.struct = Tabela.boolType;
	}

	public void visit(NewType newType) {
		if (newType.getExpr().struct != Tab.intType) {
			report_error("Greska na liniji " + newType.getLine() + " : broj elemenata niza mora biti celobrojnog tipa.",
					null);
		}
		newType.struct = new Struct(Struct.Array, newType.getType().struct);
	}

	public void visit(MinTerm minTerm) {
		minTerm.struct = minTerm.getTerm().struct;
	}

	public void visit(NoMinusTerm noMinusTerm) {
		noMinusTerm.struct = noMinusTerm.getTerm().struct;
	}

	public void visit(TermExpr termExpr) {
		termExpr.struct = termExpr.getMinusTerm().struct;
	}

	public void visit(AddExpr addExpr) {
		Struct te = addExpr.getExpr1().struct;
		Struct t = addExpr.getTerm().struct;
		if (te.equals(t) && te == Tab.intType) {
			addExpr.struct = te;
		} else {
			report_error("Greska na liniji " + addExpr.getLine() + " : nekompatibilni tipovi u izrazu za sabiranje.",
					null);
			addExpr.struct = Tab.noType;
		}
	}

	public void visit(NoCondition noCondition) {
		noCondition.struct = noCondition.getExpr1().struct;
	}

	public void visit(Cond cond) {
		cond.struct = cond.getExpr1().struct;
	}

	public void visit(FirstExpr firstExpr) {
		firstExpr.struct = firstExpr.getExpr1().struct;
	}

	public void visit(SecondExpr secondExpr) {
		secondExpr.struct = secondExpr.getExpr1().struct;
	}

	public void visit(Condition condition) {
		Struct tb = condition.getCond().struct;
		Struct te = condition.getFirstExpr().struct;
		Struct t = condition.getSecondExpr().struct;
		if (te.equals(t)) {
			condition.struct = te;
			if (tb != Tabela.boolType) {
				report_error("Greska na liniji " + condition.getLine()
						+ " : u ternarnom operatoru uslov mora biti logickog tipa.", null);
			}
		} else {
			report_error("Greska na liniji " + condition.getLine() + " : nekompatibilni tipovi u ternarnom operatoru.",
					null);
			condition.struct = Tab.noType;
		}
	}

	public void visit(ExprFact exprFact) {
		exprFact.struct = exprFact.getExpr().struct;
	}

	/*public void visit(DesExpr desExpr) {
		desExpr.struct = desExpr.getExpr().struct;
	}*/

	public void visit(DesInc desInc) {
		Struct t = desInc.getDesignator().obj.getType();
		if (t != Tab.intType) {
			report_error("Greska na liniji " + desInc.getLine() + " : promenljiva "
					+ desInc.getDesignator().getDesName().getName() + " mora biti celobrojnog tipa.", null);
		} else if (t.getKind() == Struct.Array) {
			if (designatorType.size() > 0) {
				designatorType.remove(0);
			} else {
				report_error("Greska na liniji " + desInc.getLine() + " : promenljiva "
						+ desInc.getDesignator().getDesName().getName() + " mora biti celobrojnog tipa.", null);
			}
		} else if (desInc.getDesignator().obj.getKind() == Obj.Con) {
			report_error("Greska na liniji " + desInc.getLine() + " : konstanta "
					+ desInc.getDesignator().getDesName().getName() + " se ne moze inkrementirati.", null);
		}
	}

	public void visit(DesDec desDec) {
		Struct t = desDec.getDesignator().obj.getType();
		if (t != Tab.intType) {
			report_error("Greska na liniji " + desDec.getLine() + " : promenljiva "
					+ desDec.getDesignator().getDesName().getName() + " mora biti celobrojnog tipa.", null);
		} else if (t.getKind() == Struct.Array) {
			if (designatorType.size() > 0) {
				designatorType.remove(0);
			} else {
				report_error("Greska na liniji " + desDec.getLine() + " : promenljiva "
						+ desDec.getDesignator().getDesName().getName() + " mora biti celobrojnog tipa.", null);
			}
		} else if (desDec.getDesignator().obj.getKind() == Obj.Con) {
			report_error("Greska na liniji " + desDec.getLine() + " : konstanta "
					+ desDec.getDesignator().getDesName().getName() + " se ne moze dekrementirati.", null);
		}
	}

	public void visit(DesignFactor designFactor) {
		if (designFactor.getDesignator().obj.getType().getKind() != Struct.Array) {
			designFactor.struct = designFactor.getDesignator().obj.getType();
		} else if (designatorType.size() > 0) {
			if (designatorType.size() > 1) {
				designFactor.struct = designatorType.remove(1);
			} else {
				designFactor.struct = designatorType.remove(0);
			}
		} else {
			designFactor.struct = designFactor.getDesignator().obj.getType();
		}
	}

	public void visit(DesEqu desEqu) {
		Struct d;
		if (designatorType.size() > 0) {
			d = designatorType.remove(0);
		} else {
			d = desEqu.getDesignator().obj.getType();
		}
		if (!desEqu.getExpr().struct.assignableTo(d)) {
			report_error("Greska na liniji " + desEqu.getLine() + " : " + "nekompatibilni tipovi u dodeli vrednosti! ",
					null);
		}
	}

	public void visit(PrintStatement printStatement) {
		Struct p = printStatement.getExpr().struct;
		if (p != Tab.intType && p != Tab.charType && p != Tabela.boolType) {
			report_error("Greska na liniji " + printStatement.getLine() + " : "
					+ "parametar funkcije print mora biti osnovnog tipa! ", null);
		}
	}

	public void visit(ReadStatement readStatement) {
		Struct p = readStatement.getDesignator().obj.getType();
		if (p.getKind() == Struct.Array) {
			if (designatorType.size() > 0) {
				designatorType.remove(0);
			} else {
				report_error(
						"Greska na liniji " + readStatement.getLine() + " : promenljiva "
								+ readStatement.getDesignator().getDesName().getName() + " ne sme biti nizovnog tipa.",
						null);
			}
		} else if (readStatement.getDesignator().obj.getKind() == Obj.Con) {
			report_error("Greska na liniji " + readStatement.getLine() + " : konstanta "
					+ readStatement.getDesignator().getDesName().getName() + " ne sme biti parametar funkcije read.",
					null);
		}
	}

	public boolean passed() {
		return !errorDetected;
	}

}
