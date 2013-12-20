package cn.mkepu.xbzs.document;

public class XContent {
	private String content=null;
	private int no=-1;

	public XContent(String data,int no) {
		this.content=data;
		this.no=no;
	}
	
	protected String getRawContent(){
		return this.content;
	}
	
	protected int getNo(){
		return this.no;
	}
	
	public String getContent(){
		StringBuilder result = new StringBuilder();		
		for (String line :this.content.split("\r\n")){
			result.append(line.trim());
		}		
		return result.toString();
	}
}
