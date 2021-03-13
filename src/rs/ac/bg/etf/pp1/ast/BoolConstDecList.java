// generated with ast extension for cup
// version 0.8
// 9/1/2021 16:10:37


package rs.ac.bg.etf.pp1.ast;

public class BoolConstDecList extends ConstList {

    private BoolConstList BoolConstList;

    public BoolConstDecList (BoolConstList BoolConstList) {
        this.BoolConstList=BoolConstList;
        if(BoolConstList!=null) BoolConstList.setParent(this);
    }

    public BoolConstList getBoolConstList() {
        return BoolConstList;
    }

    public void setBoolConstList(BoolConstList BoolConstList) {
        this.BoolConstList=BoolConstList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(BoolConstList!=null) BoolConstList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(BoolConstList!=null) BoolConstList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(BoolConstList!=null) BoolConstList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("BoolConstDecList(\n");

        if(BoolConstList!=null)
            buffer.append(BoolConstList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BoolConstDecList]");
        return buffer.toString();
    }
}
