package cn.mkepu.xbzs.document;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;

import cn.mkepu.xbzs.document.XContent;

public class XDOCDocument extends XDocument {
	private HWPFDocument document = null;
	private WordExtractor word_extractor = null;

	public XDOCDocument(String filename) {
		try {
			File file = new File(filename);
			FileInputStream ins = new FileInputStream(file.getAbsolutePath());
			document = new HWPFDocument(ins);
			word_extractor = new WordExtractor(document);
			this.parseContents();
			//word_extractor.close();
			ins.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void parseContents() throws IOException {
		this.contents.clear();
		this.tables.clear();
		Range range = document.getRange();

		for (int i = 0; i < range.numParagraphs(); i++) {
			Paragraph p = range.getParagraph(i);
			if (p.isInTable())
				continue;
			String data = p.text();
			if (data.length() > 0) {
				XContent content = new XContent(data, i+1);
				this.contents.put( i+1, content);
				this.bodykeys.add( i+1);
			}
		}

		int index = 1;
		TableIterator it = new TableIterator(range);
		while (it.hasNext()) {
			Table table=(Table)it.next();
			XTable xtable = new XTable(index, table.text());
			for (int row = 0; row < table.numRows(); row++) {
				TableRow tr = table.getRow(row);
				for (int col = 0; col < tr.numCells(); col++) {
					TableCell td = tr.getCell(col);
					StringBuilder temp=new StringBuilder();
					for (int k = 0; k < td.numParagraphs(); k++) {
						temp.append( td.getParagraph(k).text());
					}
					xtable.setCellContent(row+1, col+1,temp.toString().trim());
				}
			}
			this.tables.put(xtable.getTableIndex(), xtable);
			this.tablekeys.add(index);
			index++;
		}
	}
}
