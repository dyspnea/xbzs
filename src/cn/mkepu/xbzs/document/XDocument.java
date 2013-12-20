package cn.mkepu.xbzs.document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 用于处理文档的虚类，现在主要用于处理文本。 文档的文本主要分为正文文本和表格文本两大类。
 * DOCX文档才能把两者区分开，其他类型文档均按照正文文本进行处理。 正文文本的组织方式分为两种，PDF按页组织，其他按照段落进行组织。
 * 
 * @author kofxx
 * 
 */

public abstract class XDocument {

	protected String DOCUMENT_ENCODE = "UTF-8";

	public int DOCUMENT_TYPE;

	/**
	 * 用于保存DOC、DOCX格式文件的正文,PDF、TXT等格式文件的所有文本。
	 */
	protected HashMap<Integer, XContent> contents = new HashMap<Integer, XContent>();

	/**
	 * 用于保存DOC、DOCX格式文件中的表格，其他格式文档暂不使用。
	 */
	protected HashMap<Integer, XTable> tables = new HashMap<Integer, XTable>();

	ArrayList<Integer> bodykeys = new ArrayList<Integer>();
	ArrayList<Integer> tablekeys = new ArrayList<Integer>();

	/**
	 * 解析内容，除PDF格式文档按页解析外，其他按段落解析
	 * 
	 * @throws IOException
	 */
	protected abstract void parseContents() throws IOException;

	/**
	 * 获取原始的指定编号的内容
	 * 
	 * @param no
	 * @throws IOException
	 */
	public String getRawContent(int no) {
		String result = null;
		if (this.contents.containsKey(no)) {
			result = this.contents.get(no).getRawContent();
		}
		return result;
	}

	/**
	 * 获取处理过的指定编号的内容
	 * 
	 */
	public String getContent(int no) {
		if (!this.contents.containsKey(no))
			return null;
		return this.contents.get(no).getContent();
	}

	/**
	 * 获取指定编号的表格中的内容
	 * 
	 * @param no
	 * @return
	 */
	public String getTableRawContent(int no) {
		if (!this.tables.containsKey(no))
			return null;
		return this.tables.get(no).getRawContent();

	}

	/**
	 * 获得某个表格的某个单元格中的内容
	 */
	public String getTableCellContent(int no, int row, int col) {
		if (!this.tables.containsKey(no))
			return null;
		// System.out.println(this.tables.get(no).getRawContent());
		return this.tables.get(no).getCellContent(row, col);
	}

	/**
	 * 内容片断的数量
	 * 
	 */
	public int getContentsNum() {
		return this.contents.size();
	}

	/**
	 * 正文型内容片断的编号，这些编号从小到大排列
	 */
	public ArrayList<Integer> getOrderedBodyKeys() {
		return this.bodykeys;
	}

	/**
	 * 表格型内容片断的编号，编号从小到大排列，用于DOC和DOCX类型文档
	 */
	public ArrayList<Integer> getOrderedTableKeys() {
		return this.tablekeys;
	}

	/**
	 * 指定文档编码
	 * 
	 * @param encode
	 */
	public String getEncode() {
		return this.DOCUMENT_ENCODE;
	}

	/**
	 * 得到非空的内容片断
	 */
	public ArrayList<String> getLines() {
		ArrayList<String> lines = new ArrayList<String>();
		for (int key : this.bodykeys) {
			String data = this.contents.get(key).getContent();
			if (!isAEmptyLine(data))
				lines.add(data);
		}
		return lines;
	}
	
	public boolean isAEmptyLine(String line){
		boolean result=false;
		String data=line.replace("　", "").replace("\r", "").replace("\n", "").replace("\t", "");
		result=data.length()<=0;
		return result;
	}
}
