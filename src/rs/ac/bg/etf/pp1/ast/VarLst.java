// generated with ast extension for cup
// version 0.8
// 9/1/2021 16:10:37


package rs.ac.bg.etf.pp1.ast;

public class VarLst extends VarList {

    private VarList VarList;
    private VarWithSquBrack VarWithSquBrack;

    public VarLst (VarList VarList, VarWithSquBrack VarWithSquBrack) {
        this.VarList=VarList;
        if(VarList!=null) VarList.setParent(this);
        this.VarWithSquBrack=VarWithSquBrack;
        if(VarWithSquBrack!=null) VarWithSquBrack.setParent(this);
    }

    public VarList getVarList() {
        return VarList;
    }

    public void setVarList(VarList VarList) {
        this.VarList=VarList;
    }

    public VarWithSquBrack getVarWithSquBrack() {
        return VarWithSquBrack;
    }

    public void setVarWithSquBrack(VarWithSquBrack VarWithSquBrack) {
        this.VarWithSquBrack=VarWithSquBrack;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarList!=null) VarList.accept(visitor);
        if(VarWithSquBrack!=null) VarWithSquBrack.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarList!=null) VarList.traverseTopDown(visitor);
        if(VarWithSquBrack!=null) VarWithSquBrack.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarList!=null) VarList.traverseBottomUp(visitor);
        if(VarWithSquBrack!=null) VarWithSquBrack.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarLst(\n");

        if(VarList!=null)
            buffer.append(VarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarWithSquBrack!=null)
            buffer.append(VarWithSquBrack.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarLst]");
        return buffer.toString();
    }
}
