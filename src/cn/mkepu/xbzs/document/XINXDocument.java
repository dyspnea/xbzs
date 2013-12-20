package cn.mkepu.xbzs.document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class XINXDocument extends XDocument {

	String re_str = "(?<=<pcnt>c_)[^<].+?[^>](?=</pcnt>)";
	Pattern pattern = Pattern.compile(re_str);

	public XINXDocument(String filename) throws IOException {
		File file = new File(filename);
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		String data = sb.toString();
		Matcher matcher = pattern.matcher(data);
		ArrayList<String> content = new ArrayList<String>();
		while (matcher.find()) {
			content.add(matcher.group());
		}
		
		int index=0;
		for (String line2 : content) {
			index=index+1;
			System.out.println(line2);
			if (index>100) break;
		}
	}

	@Override
	protected void parseContents() throws IOException {
		// TODO Auto-generated method stub
		
	}

}
