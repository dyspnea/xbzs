package cn.mkepu.xbzs.document;

import java.io.IOException;

public class XDocumentFactory {

	public static XDocument getInstance(String filename) throws IOException {
		XDocument document = null;
		int dot = filename.lastIndexOf(".");
		String extname = filename.substring(dot + 1).toUpperCase();
		switch (extname){
		case "PDF":
			document = new XPDFDocument(filename);
			break;
		case "DOCX":
			document = new XDOCXDocument(filename);
			break;
		case "DOC":
			document = new XDOCDocument(filename);
			break;
		case "TXT":
			document = new XTXTDocument(filename);
			break;
		case "INX":
			document = new XINXDocument(filename);
			break;
		}
		return document;
	}
}
