package cn.mkepu.xbzs.segmenter;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

//基于IKAnalyzer2012_u6的分词器

public class XIKSegmenter extends XSegmenter{
	IKSegmenter ikSegmenter=null;
	
	public ArrayList<String> getWords(String text) throws IOException {
		ArrayList<String> result=new ArrayList<String>();
		ikSegmenter = new IKSegmenter(new StringReader(text),true);
		Lexeme word;
		while ((word = ikSegmenter.next()) != null) {
			result.add(word.getLexemeText());
		}
		return result;
	}
	
}
