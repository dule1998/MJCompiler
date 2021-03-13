// generated with ast extension for cup
// version 0.8
// 9/1/2021 16:10:37


package rs.ac.bg.etf.pp1.ast;

public class DesErr extends DesignatorStatement {

    private Designator Designator;
    private ErHndl ErHndl;

    public DesErr (Designator Designator, ErHndl ErHndl) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.ErHndl=ErHndl;
        if(ErHndl!=null) ErHndl.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public ErHndl getErHndl() {
        return ErHndl;
    }

    public void setErHndl(ErHndl ErHndl) {
        this.ErHndl=ErHndl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(ErHndl!=null) ErHndl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(ErHndl!=null) ErHndl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(ErHndl!=null) ErHndl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesErr(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ErHndl!=null)
            buffer.append(ErHndl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesErr]");
        return buffer.toString();
    }
}
