// generated with ast extension for cup
// version 0.8
// 9/1/2021 16:10:37


package rs.ac.bg.etf.pp1.ast;

public class BoolConstDeclarations extends BoolConstList {

    private BoolConstList BoolConstList;
    private String constName;
    private Assignop Assignop;
    private String B3;

    public BoolConstDeclarations (BoolConstList BoolConstList, String constName, Assignop Assignop, String B3) {
        this.BoolConstList=BoolConstList;
        if(BoolConstList!=null) BoolConstList.setParent(this);
        this.constName=constName;
        this.Assignop=Assignop;
        if(Assignop!=null) Assignop.setParent(this);
        this.B3=B3;
    }

    public BoolConstList getBoolConstList() {
        return BoolConstList;
    }

    public void setBoolConstList(BoolConstList BoolConstList) {
        this.BoolConstList=BoolConstList;
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public Assignop getAssignop() {
        return Assignop;
    }

    public void setAssignop(Assignop Assignop) {
        this.Assignop=Assignop;
    }

    public String getB3() {
        return B3;
    }

    public void setB3(String B3) {
        this.B3=B3;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(BoolConstList!=null) BoolConstList.accept(visitor);
        if(Assignop!=null) Assignop.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(BoolConstList!=null) BoolConstList.traverseTopDown(visitor);
        if(Assignop!=null) Assignop.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(BoolConstList!=null) BoolConstList.traverseBottomUp(visitor);
        if(Assignop!=null) Assignop.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("BoolConstDeclarations(\n");

        if(BoolConstList!=null)
            buffer.append(BoolConstList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        if(Assignop!=null)
            buffer.append(Assignop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+B3);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BoolConstDeclarations]");
        return buffer.toString();
    }
}
