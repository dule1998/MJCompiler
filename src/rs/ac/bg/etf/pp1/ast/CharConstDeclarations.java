// generated with ast extension for cup
// version 0.8
// 9/1/2021 16:10:37


package rs.ac.bg.etf.pp1.ast;

public class CharConstDeclarations extends CharConstList {

    private CharConstList CharConstList;
    private String constName;
    private Assignop Assignop;
    private String C3;

    public CharConstDeclarations (CharConstList CharConstList, String constName, Assignop Assignop, String C3) {
        this.CharConstList=CharConstList;
        if(CharConstList!=null) CharConstList.setParent(this);
        this.constName=constName;
        this.Assignop=Assignop;
        if(Assignop!=null) Assignop.setParent(this);
        this.C3=C3;
    }

    public CharConstList getCharConstList() {
        return CharConstList;
    }

    public void setCharConstList(CharConstList CharConstList) {
        this.CharConstList=CharConstList;
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

    public String getC3() {
        return C3;
    }

    public void setC3(String C3) {
        this.C3=C3;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CharConstList!=null) CharConstList.accept(visitor);
        if(Assignop!=null) Assignop.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CharConstList!=null) CharConstList.traverseTopDown(visitor);
        if(Assignop!=null) Assignop.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CharConstList!=null) CharConstList.traverseBottomUp(visitor);
        if(Assignop!=null) Assignop.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CharConstDeclarations(\n");

        if(CharConstList!=null)
            buffer.append(CharConstList.toString("  "+tab));
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

        buffer.append(" "+tab+C3);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CharConstDeclarations]");
        return buffer.toString();
    }
}
