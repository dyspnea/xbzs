package cn.mkepu.xbzs.segmenter;

import java.io.IOException;
import java.util.ArrayList;

//用于分词

public abstract class XSegmenter {
	protected String name="XXXSEG";
	
	//获取分词结果
	public abstract ArrayList<String> getWords(String text) throws IOException;
	
	//分词器的标示
	public String getSegmenterName(){
		return this.getClass().getSimpleName();
	}
	
}
