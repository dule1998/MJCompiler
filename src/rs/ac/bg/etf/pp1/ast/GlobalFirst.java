// generated with ast extension for cup
// version 0.8
// 9/1/2021 16:10:37


package rs.ac.bg.etf.pp1.ast;

public class GlobalFirst extends GlbDeclFirst {

    private String varName;
    private SquareBrack SquareBrack;

    public GlobalFirst (String varName, SquareBrack SquareBrack) {
        this.varName=varName;
        this.SquareBrack=SquareBrack;
        if(SquareBrack!=null) SquareBrack.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public SquareBrack getSquareBrack() {
        return SquareBrack;
    }

    public void setSquareBrack(SquareBrack SquareBrack) {
        this.SquareBrack=SquareBrack;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SquareBrack!=null) SquareBrack.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SquareBrack!=null) SquareBrack.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SquareBrack!=null) SquareBrack.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalFirst(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(SquareBrack!=null)
            buffer.append(SquareBrack.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalFirst]");
        return buffer.toString();
    }
}
