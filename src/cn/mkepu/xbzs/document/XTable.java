package cn.mkepu.xbzs.document;

import java.util.HashMap;

class XTableCellKey{
	private int row=-1;
	private int col=-1;
	
	public XTableCellKey(int row,int col){
		this.row=row;
		this.col=col;
	}
	
	protected int getRowNum(){
		return this.row;
	}
	
	protected int getColNum(){
		return this.col;
	}
}

class XTableCell{
	private XTableCellKey cellkey=null;
	private String key=null;
	private int row=-1;
	private int col=-1;
	private String content=null;
	
	public XTableCell(int row,int col,String content){
		this.key=this.generateCellKey(row, col);
		this.content=content;
	}
	
	protected String getCellKey(){
		return this.key;
	}
	
	protected int getCellRow(){
		return this.row;
	}
	
	protected int getCellCol(){
		return this.col;
	}
	
	protected String getContent(){
		return this.content;
	}
	
	protected static String generateCellKey(int row,int col){
		return row+":"+col;
	}
}

public class XTable {
	private int table_index=-1;
	private String rawContent=null;
	
	private HashMap<String,XTableCell> contents=new HashMap<String,XTableCell>();
	
	public XTable(int index,String tablecontent){
		this.table_index=index;
		this.rawContent=tablecontent;
	}
	
	public void setCellContent(int row,int col,String content){
		XTableCell cell= new XTableCell(row,col,content);
		this.contents.put(cell.getCellKey(),cell);
	}
	
	public String getCellContent(int row,int col){
		String result=null;
		String cellkey=XTableCell.generateCellKey(row, col);
		if (this.contents.containsKey(cellkey)){
			result=this.contents.get(cellkey).getContent();
		}
		return result;
	}
	
	public int getTableIndex(){
		return this.table_index;
	}
	
	public String getRawContent(){
		return this.rawContent;
	}
	
}
