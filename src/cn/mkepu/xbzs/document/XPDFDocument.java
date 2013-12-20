package cn.mkepu.xbzs.document;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.PDFTextStripper;

import cn.mkepu.xbzs.XBZS;

/**
 * 
 * 
 * @author kofxx
 *
 */
public class XPDFDocument extends XDocument {

	PDDocument document = null;
	PDFTextStripper stripper = null;
	int PAGES_NO = 0;
	
	public XPDFDocument(String filename) {
		try {
			document = PDDocument.load(filename);
			stripper = new PDFTextStripper();
			this.PAGES_NO = document.getNumberOfPages();
			this.DOCUMENT_ENCODE=XBZS.getFileEncode(filename);
			this.parseContents();
			document.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void parseContents() throws IOException {
		this.contents.clear();
		//List allPages=document.getDocumentCatalog().getAllPages();
		//PDPage page=(PDPage)allPages.get(2);
		//System.out.println(page.getContents().getInputStreamAsString());
		
		for (int i=1;i<=this.PAGES_NO;i++){
			stripper.setStartPage(i);
			stripper.setEndPage(i);
			
			String data = stripper.getText(document);
			XContent content=new XContent(data,i);
			this.contents.put(i,content);
			this.bodykeys.add(i);
		}
	}
}