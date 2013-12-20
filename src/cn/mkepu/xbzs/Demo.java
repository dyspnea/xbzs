package cn.mkepu.xbzs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import cn.mkepu.xbzs.document.XDocument;
import cn.mkepu.xbzs.document.XDocumentFactory;
import cn.mkepu.xbzs.ruler.XRulerGenerator;
import cn.mkepu.xbzs.ruler.XRulerFactory;
import cn.mkepu.xbzs.ruler.XSimpleDictionaryRuler;
import cn.mkepu.xbzs.segmenter.XSegmenter;
import cn.mkepu.xbzs.segmenter.XSegmenterFactory;

public class Demo {
	HashMap<Integer,XSegmenter> segmenters=new HashMap<Integer,XSegmenter>();
	XDocument document=null;
	XRulerGenerator rule=null;
	public Demo(String filename) throws IOException{
		document=XDocumentFactory.getInstance(filename);
	}
	
	public boolean addSegmenter(int type){
		boolean result=true;
		if (segmenters.containsKey(type)) result= false;
		segmenters.put(type,XSegmenterFactory.getInstance(type));
		return result;
	}
	
	public ArrayList<String> getWords(String text) throws IOException{
		ArrayList<String> result = new ArrayList<String>();
		String temp=null;
		for (XSegmenter segmenter:segmenters.values()){
			temp=segmenter.getSegmenterName()+":"+segmenter.getWords(text);
			result.add(temp);
		}
		return result;
	}
	
	public static void main(String[] args) throws Exception{
		//XRulerFactory.getInstance("gfz");
		//new XSimpleDictionaryRuler("规范字");
		//XRulerFactory.initFactory();
		Demo d=new Demo("九下.txt");
		//Demo d=new Demo("test.docx");
		//Demo d=new Demo("九上.pdf");
		//System.out.println(d.document.getContentsNum());
		//System.out.println(d.document.getRawContent(1));
		//System.out.println(d.document.getEncode());
		//System.out.println(d.document.getContent(2));
		//System.out.println(d.document.getOrderedBodyKeys().size());
		//System.out.println(d.document.getOrderedTableKeys().size());
		//System.out.println(d.document.getTableRawContent(1));
		//System.out.println(d.document.getTableCellContent(1,2,1));
		for (String line:d.document.getLines()){
			System.out.println(line);
		}
	}

}
