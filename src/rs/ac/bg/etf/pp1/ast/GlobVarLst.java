// generated with ast extension for cup
// version 0.8
// 9/1/2021 16:10:37


package rs.ac.bg.etf.pp1.ast;

public class GlobVarLst extends GlobVarList {

    private GlobVarList GlobVarList;
    private GlobVarWithSquBr GlobVarWithSquBr;

    public GlobVarLst (GlobVarList GlobVarList, GlobVarWithSquBr GlobVarWithSquBr) {
        this.GlobVarList=GlobVarList;
        if(GlobVarList!=null) GlobVarList.setParent(this);
        this.GlobVarWithSquBr=GlobVarWithSquBr;
        if(GlobVarWithSquBr!=null) GlobVarWithSquBr.setParent(this);
    }

    public GlobVarList getGlobVarList() {
        return GlobVarList;
    }

    public void setGlobVarList(GlobVarList GlobVarList) {
        this.GlobVarList=GlobVarList;
    }

    public GlobVarWithSquBr getGlobVarWithSquBr() {
        return GlobVarWithSquBr;
    }

    public void setGlobVarWithSquBr(GlobVarWithSquBr GlobVarWithSquBr) {
        this.GlobVarWithSquBr=GlobVarWithSquBr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(GlobVarList!=null) GlobVarList.accept(visitor);
        if(GlobVarWithSquBr!=null) GlobVarWithSquBr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(GlobVarList!=null) GlobVarList.traverseTopDown(visitor);
        if(GlobVarWithSquBr!=null) GlobVarWithSquBr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(GlobVarList!=null) GlobVarList.traverseBottomUp(visitor);
        if(GlobVarWithSquBr!=null) GlobVarWithSquBr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobVarLst(\n");

        if(GlobVarList!=null)
            buffer.append(GlobVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(GlobVarWithSquBr!=null)
            buffer.append(GlobVarWithSquBr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobVarLst]");
        return buffer.toString();
    }
}
