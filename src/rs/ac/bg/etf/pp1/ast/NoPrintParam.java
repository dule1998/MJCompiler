// generated with ast extension for cup
// version 0.8
// 9/1/2021 16:10:37


package rs.ac.bg.etf.pp1.ast;

public class NoPrintParam extends PrintList {

    public NoPrintParam () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NoPrintParam(\n");

        buffer.append(tab);
        buffer.append(") [NoPrintParam]");
        return buffer.toString();
    }
}
