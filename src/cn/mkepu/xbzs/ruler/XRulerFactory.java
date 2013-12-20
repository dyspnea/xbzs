package cn.mkepu.xbzs.ruler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

import org.ini4j.Wini;

public class XRulerFactory {
	
	private static HashMap <String,String[]> rules=new HashMap <String,String[]>();
	
	public static void initFactory() throws IOException{
		Wini ini = new Wini(new File("./rules/rules.ini"));
		for (String section:ini.keySet()){
			String[] values=new String[3];
			values[0]=ini.get(section,"TYPE");
			values[2]=ini.get(section,"DETAIL");
			values[1]=ini.get(section,"RULE_FILE");
			rules.put(section,values);
		}
		System.out.println(rules);
	}
	
	public static XRuler getInstance(String rulename) throws IOException, ClassNotFoundException {
		XRuler ruler=null;
		if (rules.size()<1) XRulerFactory.initFactory();
		rulename=rulename.toUpperCase();
		if (XRulerFactory.rules.containsKey(rulename)){
			String[] values=rules.get(rulename);
			String ruletype=values[0];
			switch (ruletype){
			case "SIMPLE_DICTIONARY":
				String filename="./rules/dictionary/"+values[1]+".bin";
				ruler=XRulerFactory.loadRule(filename);
				break;
			}
		}
		System.out.println(ruler);
		return ruler;
	}
	
	public static XRuler loadRule(String filename) throws IOException, ClassNotFoundException{
		System.out.println(filename);
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
		XRuler rule=(XRuler)ois.readObject();
		ois.close();
		return rule;
	}
}
