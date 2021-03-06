// generated with ast extension for cup
// version 0.8
// 9/1/2021 16:10:37


package rs.ac.bg.etf.pp1.ast;

public class NumConstDecList extends ConstList {

    private NumConstList NumConstList;

    public NumConstDecList (NumConstList NumConstList) {
        this.NumConstList=NumConstList;
        if(NumConstList!=null) NumConstList.setParent(this);
    }

    public NumConstList getNumConstList() {
        return NumConstList;
    }

    public void setNumConstList(NumConstList NumConstList) {
        this.NumConstList=NumConstList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(NumConstList!=null) NumConstList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(NumConstList!=null) NumConstList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(NumConstList!=null) NumConstList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NumConstDecList(\n");

        if(NumConstList!=null)
            buffer.append(NumConstList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NumConstDecList]");
        return buffer.toString();
    }
}
