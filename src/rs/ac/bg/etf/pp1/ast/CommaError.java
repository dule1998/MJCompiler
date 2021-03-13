// generated with ast extension for cup
// version 0.8
// 9/1/2021 16:10:37


package rs.ac.bg.etf.pp1.ast;

public class CommaError extends GlobVarList {

    private GlobVarList GlobVarList;
    private ErrorComma ErrorComma;

    public CommaError (GlobVarList GlobVarList, ErrorComma ErrorComma) {
        this.GlobVarList=GlobVarList;
        if(GlobVarList!=null) GlobVarList.setParent(this);
        this.ErrorComma=ErrorComma;
        if(ErrorComma!=null) ErrorComma.setParent(this);
    }

    public GlobVarList getGlobVarList() {
        return GlobVarList;
    }

    public void setGlobVarList(GlobVarList GlobVarList) {
        this.GlobVarList=GlobVarList;
    }

    public ErrorComma getErrorComma() {
        return ErrorComma;
    }

    public void setErrorComma(ErrorComma ErrorComma) {
        this.ErrorComma=ErrorComma;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(GlobVarList!=null) GlobVarList.accept(visitor);
        if(ErrorComma!=null) ErrorComma.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(GlobVarList!=null) GlobVarList.traverseTopDown(visitor);
        if(ErrorComma!=null) ErrorComma.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(GlobVarList!=null) GlobVarList.traverseBottomUp(visitor);
        if(ErrorComma!=null) ErrorComma.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CommaError(\n");

        if(GlobVarList!=null)
            buffer.append(GlobVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ErrorComma!=null)
            buffer.append(ErrorComma.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CommaError]");
        return buffer.toString();
    }
}
