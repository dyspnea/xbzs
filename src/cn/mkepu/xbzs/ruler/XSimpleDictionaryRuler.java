package cn.mkepu.xbzs.ruler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class XSimpleDictionaryRuler extends XDictionaryRuler{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4275245947439133665L;
	
	
	ArrayList<String> items=new ArrayList<String>();

	public XSimpleDictionaryRuler(){
		
	}
	
	public XSimpleDictionaryRuler(String rulename) throws IOException, ClassNotFoundException{
		String filename=this.rule_path+rulename+".bin";
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
		XSimpleDictionaryRuler a=(XSimpleDictionaryRuler)ois.readObject();
		ois.close();
	}
	
	public boolean isInTheRule(String word) {
		return false;
	}
	
}
