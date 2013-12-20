package cn.mkepu.xbzs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;

public class XBZS {
	/**
	 * 
	 */
	public static ArrayList<String> document_types=new ArrayList<String>();
	static{
		document_types.add("PDF");
		document_types.add("DOCX");
		document_types.add("DOC");
		document_types.add("INX");
		document_types.add("TXT");
	}
	
	/**
	 * 内容类型
	 */
	public final static int BODY=0;
	public final static int TABLE=1;
	
	/**
	 * 各种分词器
	 */
	public static final int XIKSEG = 0;
	public static final int XMMSEG = 1;
	public static final int XANSEG = 2;
	public static final int XJCSEG = 3;
	public static final int XFDSEG = 4;
	
	/**
	 * 编码探测器
	 */
	public static CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
	static {
		detector.add(JChardetFacade.getInstance());
	}
	
	public static String getFileEncode(String filename) throws IOException{
		String result=null;
		File file=new File(filename);
		result=XBZS.detector.detectCodepage(file.toURI().toURL()).name();
		return result;
	}

}
