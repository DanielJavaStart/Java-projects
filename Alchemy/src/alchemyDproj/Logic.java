package alchemyDproj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Logic {
	
		static List<Element>AllElements = new ArrayList<Element>();	
		static List<Element>OpenedElements = new ArrayList<Element>();	
		static File emix = new File("ElementsMix.dproj");
		static File eopen = new File("OpenedElements.dproj");
		static File elog = new File("log.dproj");
		
		public static void startInitialize() {
		
		if(!elog.exists())
			try {
				elog.createNewFile();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		//getting all elements from pictures
		File f = new File("pict");
		String[]arr = f.list();
		for(String s:arr) {
		AllElements.add(new Element(s.substring(0, s.lastIndexOf(".")),"pict/"+s));
		}
		
		//getting opened elements from file
		
		try(FileReader fr = new FileReader(eopen);){
			int n=0;
			String name="";
			while((n=fr.read())!=-1) {
				if((char)n!='\n')name+=(char)n;
				if((char)n=='\n') {
					OpenedElements.add(new Element(name,"pict/"+name+".png"));
					name="";
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
// output opened elements
	public static void outlineCorrect(int i) {
		if(OpenedElements.size()>4) {
			if(i==1) {
				if(GraphicPanel.pos>0)GraphicPanel.pos--;
			}
			if(i==-1) {
				if(GraphicPanel.pos<OpenedElements.size()-1)GraphicPanel.pos++;
			}
		}
	}
	//garbage for elements
	public synchronized static void garbageCheck() {
	 Iterator<Element>iter = GraphicPanel.ElementsInUse.iterator();
	 while(iter.hasNext()) {
		 Element e = iter.next();
		 if(e.x>0 && e.x<75 && e.y>600 && e.y<720) {
			 GraphicPanel.ElementsInUse.remove(e);
		 }
	 }
	}
	
	// functions to mix elements
	public static boolean mixStepOne(Element e, Element e1) {
		
		try(FileReader fr = new FileReader(emix);){
		//getting row from file
		String row="";
		int n=0;
		while((n=fr.read())!=-1) {
			
			if((char)n!='\n') {
				row+=(char)n;
			}
			if((char)n=='\n') {
				if(mixStepTwo(e,e1,row)) {
					
				return true;
				}else {
				row="";
				n=0;
				}
			}
		}
		
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	public static boolean mixStepTwo(Element e,Element e1,String row) {
		Element toCreate = null;
		String firstE = "",secondE="",result="";
		int i=0;
		while(row.charAt(i)!=' ' && i<row.length()) {//getting words from row into three vars
			firstE+=row.charAt(i);
			i++;
		}
		i++;
		
		while(row.charAt(i)!=' ' && i<row.length()) {
			secondE+=row.charAt(i);
			i++;
		}
		i++;
	
		while(i<row.length()-1) {
			result+=row.charAt(i);
			i++;
		}
		
		
		if(e.getName().contentEquals(firstE) && e1.getName().contentEquals(secondE) || e.getName().contentEquals(secondE) && e1.getName().contentEquals(firstE)) {
			
			try {
				FileWriter fw = new FileWriter(elog,true);
				fw.write(firstE+"+"+secondE+"="+result+'\n');
				fw.close();
			}catch(IOException ex) {
				ex.printStackTrace();
			}
			
			Iterator<Element>iter = AllElements.iterator();
			Element e2 = null;
			while(iter.hasNext()) {
				e2 = iter.next();
				if(e2.getName().equals(result)) {
					toCreate = new Element(e2.getName(),e2.getImageName());// do not copy elements , make new!!!!
					break;
				}
			}
			if(e2!=null && !AlreadyHaveElement(toCreate)) {
				GraphicPanel.currMessage=toCreate.getName()+" = new element!";
				OpenedElements.add(toCreate);
				try {
					FileWriter fw = new FileWriter(eopen,true);
					fw.write(toCreate.getName()+'\n');
					fw.close();
				}catch(IOException exx) {
					exx.printStackTrace();
				}
			}
			
			toCreate.x = e.x;
			toCreate.y = e.y;
			GraphicPanel.ElementsInUse.add(toCreate);
			GraphicPanel.ElementsInUse.remove(e);
			GraphicPanel.ElementsInUse.remove(e1);
			return true;
		}
		return false;
	}
	static boolean AlreadyHaveElement(Element e) {
		Iterator<Element>iter = OpenedElements.iterator();
		while(iter.hasNext()) {
			Element e1 = iter.next();
			if(e1.getName().equals(e.getName())) {
				return true;
			}
		}
		return false;
	}
}
