package cn.mkepu.xbzs.document;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.mkepu.xbzs.XBZS;

public class XTXTDocument extends XDocument {

	File document = null;
	
	public XTXTDocument(String filename){
		try {
			document = new File(filename);
			this.DOCUMENT_ENCODE=XBZS.getFileEncode(filename);
			this.parseContents();
		} catch (IOException e) {
			e.printStackTrace();
		}   
		
	}

	public void parseContents() throws IOException {
		this.contents.clear();
		this.tables.clear();
		this.bodykeys.clear();
		this.tablekeys.clear();
		
		FileInputStream fis = new FileInputStream(document.getAbsolutePath());
		InputStreamReader isr = new InputStreamReader(fis, this.DOCUMENT_ENCODE);
		BufferedReader br = new BufferedReader(isr);

		String line = null;
		int index = 1;
		while ((line = br.readLine()) != null) {
			String data = line.trim();
			if (data.length() == 0)
				continue;
			XContent content = new XContent(data, index);
			this.contents.put(index, content);
			this.bodykeys.add(index);
			index++;
		}

		br.close();
		isr.close();
		fis.close();
	}
}
