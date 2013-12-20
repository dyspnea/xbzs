package cn.mkepu.xbzs.ruler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class XRuler implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1798710081407797655L;
	protected String family=null;
	protected String name = null;
	protected String detail = null;
	protected String type=null;
	protected ArrayList<XRuleEntry> items=new ArrayList<XRuleEntry>(); 
	
	protected String rulepath="./rules";
	
	public abstract boolean isInTheRule(String word) ;
	
	
	public void loadObjFromJoson(String filename)throws IOException{
	}
}
