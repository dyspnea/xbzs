package cn.mkepu.xbzs.ruler;

public class XRuleEntry {
	protected String DETECTED=null;
	protected String PROPOSE=null;
	protected String DETAIL=null;
	public XRuleEntry(String dword,String pword,String detail){
		this.DETECTED=dword;
		this.PROPOSE=pword;
		this.DETAIL=detail;
	}
	
	public String toString(){
		return this.DETECTED+":"+this.PROPOSE+":"+this.DETAIL;
	}
}
