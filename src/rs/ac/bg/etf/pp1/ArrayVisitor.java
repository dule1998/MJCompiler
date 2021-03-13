package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;

public class ArrayVisitor extends VisitorAdaptor {

	boolean arr = false;
	
	public void visit(ExprDesignator exprDesignator){
		arr = true;
	}
}
