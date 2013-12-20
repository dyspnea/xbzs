package cn.mkepu.xbzs.document;

import java.io.File;
import java.io.IOException;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlObject;

import cn.mkepu.xbzs.XBZS;

public class XDOCXDocument extends XDocument {

	private XWPFDocument document = null;
	private XWPFWordExtractor docx = null;

	public XDOCXDocument(String filename){
		File file = new File(filename);

		try {
			document = new XWPFDocument(POIXMLDocument.openPackage(file
					.getAbsolutePath()));
			//docx = new XWPFWordExtractor(document);
			this.parseContents();
			//docx.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void parseContents() {
		this.contents.clear();
		this.tables.clear();
		
		int index = 1;

		for (XWPFParagraph data : document.getParagraphs()) {
			System.out.println(data.getRuns().size());
			for (XmlObject embeddedParagraph : data.getCTP().selectPath("declare namespace w='http://schemas.openxmlformats.org/wordprocessingml/2006/main' declare namespace wps='http://schemas.microsoft.com/office/word/2010/wordprocessingShape' .//*/wps:txbx/w:txbxContent/w:p")) {
		           System.out.println(embeddedParagraph.toString());
		       }
			XContent content = new XContent(data.getText(), index);
			this.contents.put(index, content);
			this.bodykeys.add(index);
			index++;
		}
		
		index = 1;
		for (int i=0;i<50;i++){
			try {
				//System.out.println(document.getDocument().xmlText());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (XWPFTable table : document.getTables()) {
			XTable xtable = new XTable(index, table.getText());
			int xrow = 1;
			for (XWPFTableRow row : table.getRows()) {
				int xcol = 1;
				for (XWPFTableCell cell : row.getTableCells()) {
					xtable.setCellContent(xrow, xcol,cell.getText());
					xcol++;
				}
				xrow++;
			}
			this.tables.put(xtable.getTableIndex(), xtable);
			this.tablekeys.add(index);
			index++;
		}
	}
}
