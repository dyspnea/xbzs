package cn.mkepu.xbzs.ruler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;
import org.ini4j.Wini;

public class XRulerGenerator {
	protected String name = null;
	protected String detail = null;
	protected String entry_seperater = null;
	protected String item_seperater = null;
	protected String word_seperater = null;
	protected String type=null;
	protected String entry_type=null;
	protected String[] items;
	protected Wini ini = null;
	protected HashMap<String,ArrayList<XRuleEntry>> rules=new HashMap<String,ArrayList<XRuleEntry>>(); 
	
	protected void initBaseInfo(){
		this.name = ini.get("BASE_INFO", "NAME");
		this.detail = ini.get("BASE_INFO", "DETAIL");
		this.entry_seperater = ini.get("BASE_INFO", "ENTRY_SEPERATOR").trim();
		this.item_seperater = ini.get("BASE_INFO", "ITEM_SEPERATOR").trim();
		this.word_seperater = ini.get("BASE_INFO", "WORD_SEPERATOR").trim();
		this.items = ini.get("BASE_INFO", "ITMES").split(",");
		this.type = ini.get("BASE_INFO", "TYPE");
		this.entry_type=ini.get("BASE_INFO", "ENTRY_TYPE");
	}
	
	public void generateRuleFile(String userfile,String section) throws IOException{
		ini = new Wini(new File("./userrules/dictionary/" + userfile + ".ini"));
		this.initBaseInfo();
		String keywords[] = null;
		for (int i=0;i<items.length;i++){
			ArrayList<XRuleEntry> section_rule=new ArrayList<XRuleEntry>();
			for(String keyword:ini.get(items[i]).keySet()){
				String propose_word=null;
				String detail=null;
				keywords=keyword.split(this.entry_seperater);
				if (this.word_seperater.length()>0){					
					String data=ini.get(items[i],keyword);
					if (data!=null){
						String[] entryitems=data.split(this.item_seperater);
						if (entryitems.length>0) propose_word=entryitems[0];
						if (entryitems.length>1) detail=entryitems[1];
					}
				}
				
				for (int j=0;j<keywords.length;j++){
					if (keywords[j].trim().length()==0) continue;
					section_rule.add(new XRuleEntry(keywords[j],propose_word,detail));
				}
			}
			if (section_rule.size()>0) this.rules.put(items[i],section_rule);
		}
	}
	
	public void generateSimpleDicRule(String rulename) throws IOException{
		XSimpleDictionaryRuler xsdr=new XSimpleDictionaryRuler();
		ini = new Wini(new File("./userrules/dictionary/" + rulename + ".ini"));
		this.initBaseInfo();
		for (Entry<String,Section> item:ini.entrySet()){
			if (item.getKey().equals("BASE_INFO")) continue;
			String data=item.getValue().keySet().iterator().next();
			for (String word:data.split(this.entry_seperater)){
				xsdr.items.add(word);
			}
		}
		xsdr.name=this.name;
		xsdr.detail=this.detail;
		xsdr.type=this.type;
		String filename="./rules/dictionary/"+this.name+".bin";
		File file = new File(filename);
        ObjectOutputStream oos =  new ObjectOutputStream( new FileOutputStream(file));
        oos.writeObject(xsdr);
        oos.close();
	}
	
	public void generateSimpleDicRule2(String rulename) throws IOException{
		ArrayList<String> words=new ArrayList<String>();
		//XSimpleDictionaryRuler xsdr=new XSimpleDictionaryRuler();
		ini = new Wini(new File("./userrules/dictionary/" + rulename + ".ini"));
		this.initBaseInfo();
		for (Entry<String,Section> item:ini.entrySet()){
			if (item.getKey().equals("BASE_INFO")) continue;
			String data=item.getValue().keySet().iterator().next();
			for (String word:data.split(this.entry_seperater)){
				words.add(word);
			}
		}
		HashMap<String,Object> hm=new HashMap<String,Object>();
		hm.put("NAME",this.name);
		hm.put("DETAIL",this.detail);
		hm.put("TYPE",this.type);
		hm.put("ITMES",words);
		String filename="./rules/dictionary/"+this.name+".rule";
		
	}
	
	public  static void main(String[] args){
		XRulerGenerator xrg=new XRulerGenerator();
		try {
			xrg.generateSimpleDicRule("gfz");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
