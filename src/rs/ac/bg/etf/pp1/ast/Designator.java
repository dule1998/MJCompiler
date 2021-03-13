// generated with ast extension for cup
// version 0.8
// 9/1/2021 16:10:37


package rs.ac.bg.etf.pp1.ast;

public class Designator implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private DesName DesName;
    private ExprDes ExprDes;

    public Designator (DesName DesName, ExprDes ExprDes) {
        this.DesName=DesName;
        if(DesName!=null) DesName.setParent(this);
        this.ExprDes=ExprDes;
        if(ExprDes!=null) ExprDes.setParent(this);
    }

    public DesName getDesName() {
        return DesName;
    }

    public void setDesName(DesName DesName) {
        this.DesName=DesName;
    }

    public ExprDes getExprDes() {
        return ExprDes;
    }

    public void setExprDes(ExprDes ExprDes) {
        this.ExprDes=ExprDes;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesName!=null) DesName.accept(visitor);
        if(ExprDes!=null) ExprDes.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesName!=null) DesName.traverseTopDown(visitor);
        if(ExprDes!=null) ExprDes.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesName!=null) DesName.traverseBottomUp(visitor);
        if(ExprDes!=null) ExprDes.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Designator(\n");

        if(DesName!=null)
            buffer.append(DesName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprDes!=null)
            buffer.append(ExprDes.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Designator]");
        return buffer.toString();
    }
}
