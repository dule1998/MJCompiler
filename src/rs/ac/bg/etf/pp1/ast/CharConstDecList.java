// generated with ast extension for cup
// version 0.8
// 9/1/2021 16:10:37


package rs.ac.bg.etf.pp1.ast;

public class CharConstDecList extends ConstList {

    private CharConstList CharConstList;

    public CharConstDecList (CharConstList CharConstList) {
        this.CharConstList=CharConstList;
        if(CharConstList!=null) CharConstList.setParent(this);
    }

    public CharConstList getCharConstList() {
        return CharConstList;
    }

    public void setCharConstList(CharConstList CharConstList) {
        this.CharConstList=CharConstList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CharConstList!=null) CharConstList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CharConstList!=null) CharConstList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CharConstList!=null) CharConstList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CharConstDecList(\n");

        if(CharConstList!=null)
            buffer.append(CharConstList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CharConstDecList]");
        return buffer.toString();
    }
}
