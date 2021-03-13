// generated with ast extension for cup
// version 0.8
// 9/1/2021 16:10:37


package rs.ac.bg.etf.pp1.ast;

public class TermExpr extends Expr1 {

    private MinusTerm MinusTerm;

    public TermExpr (MinusTerm MinusTerm) {
        this.MinusTerm=MinusTerm;
        if(MinusTerm!=null) MinusTerm.setParent(this);
    }

    public MinusTerm getMinusTerm() {
        return MinusTerm;
    }

    public void setMinusTerm(MinusTerm MinusTerm) {
        this.MinusTerm=MinusTerm;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MinusTerm!=null) MinusTerm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MinusTerm!=null) MinusTerm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MinusTerm!=null) MinusTerm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TermExpr(\n");

        if(MinusTerm!=null)
            buffer.append(MinusTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TermExpr]");
        return buffer.toString();
    }
}
