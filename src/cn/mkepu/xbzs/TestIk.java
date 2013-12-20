package cn.mkepu.xbzs;

import java.io.IOException;
import java.io.StringReader;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class TestIk {
	public static void main(String[] args){
		String text="小明天天上学";
		IKSegmenter ikSegmenter = new IKSegmenter(new StringReader(text),true);
		Lexeme lexeme;
		try {
			while ((lexeme = ikSegmenter.next()) != null) {
				//System.out.println(lexeme.getLexemeText());
				System.out.println(lexeme);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
