// generated with ast extension for cup
// version 0.8
// 9/1/2021 16:10:37


package rs.ac.bg.etf.pp1.ast;

public class Condition extends Expr {

    private Cond Cond;
    private FirstExpr FirstExpr;
    private SecondExpr SecondExpr;

    public Condition (Cond Cond, FirstExpr FirstExpr, SecondExpr SecondExpr) {
        this.Cond=Cond;
        if(Cond!=null) Cond.setParent(this);
        this.FirstExpr=FirstExpr;
        if(FirstExpr!=null) FirstExpr.setParent(this);
        this.SecondExpr=SecondExpr;
        if(SecondExpr!=null) SecondExpr.setParent(this);
    }

    public Cond getCond() {
        return Cond;
    }

    public void setCond(Cond Cond) {
        this.Cond=Cond;
    }

    public FirstExpr getFirstExpr() {
        return FirstExpr;
    }

    public void setFirstExpr(FirstExpr FirstExpr) {
        this.FirstExpr=FirstExpr;
    }

    public SecondExpr getSecondExpr() {
        return SecondExpr;
    }

    public void setSecondExpr(SecondExpr SecondExpr) {
        this.SecondExpr=SecondExpr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Cond!=null) Cond.accept(visitor);
        if(FirstExpr!=null) FirstExpr.accept(visitor);
        if(SecondExpr!=null) SecondExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Cond!=null) Cond.traverseTopDown(visitor);
        if(FirstExpr!=null) FirstExpr.traverseTopDown(visitor);
        if(SecondExpr!=null) SecondExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Cond!=null) Cond.traverseBottomUp(visitor);
        if(FirstExpr!=null) FirstExpr.traverseBottomUp(visitor);
        if(SecondExpr!=null) SecondExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Condition(\n");

        if(Cond!=null)
            buffer.append(Cond.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FirstExpr!=null)
            buffer.append(FirstExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SecondExpr!=null)
            buffer.append(SecondExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Condition]");
        return buffer.toString();
    }
}
