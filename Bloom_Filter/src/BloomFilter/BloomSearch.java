package BloomFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BloomSearch {
	private ArrayList<DocumentFilterDet> Det = new ArrayList<DocumentFilterDet>() ;
	private ArrayList<DocumentFilterRan> Ran = new ArrayList<DocumentFilterRan>() ;
	private ArrayList<ArrayList<String>> simply = new ArrayList<ArrayList<String>>() ;
	private String folderName;
	public BloomSearch(String folderName) throws IOException {
		this.folderName = folderName;
	}
	public void addAllDocumentDet() throws IOException {
		Runtime runtime = Runtime.getRuntime();
		runtime.gc();
		long memory = runtime.totalMemory() - runtime.freeMemory();
		
		File f=null;
		f=new File(folderName);                 
		File[] list=f.listFiles();        
		for(int i=0;i<list.length;i++){
			DocumentFilterDet a = new DocumentFilterDet(8,list[i].getName(),folderName);
			//System.out.println(a.getDocument());
			Det.add(a);
		}
        runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory1 = runtime.totalMemory() - runtime.freeMemory() - memory;
        System.out.println("Used memory for DocumentFilterDet is (bytes): " + memory1);
	}
	public void addAllDocumentRan() throws IOException {
		Runtime runtime = Runtime.getRuntime();
		runtime.gc();
		long memory = runtime.totalMemory() - runtime.freeMemory();
		
		File f=null;
		f=new File(folderName);                 
		File[] list=f.listFiles();        
		for(int i=0;i<list.length;i++){
			DocumentFilterRan a = new DocumentFilterRan(8,list[i].getName(),folderName);
			//System.out.println(a.getDocument());
			Ran.add(a);
		}
        runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory1 = runtime.totalMemory() - runtime.freeMemory() - memory;
        System.out.println("Used memory for DocumentFilterRan is (bytes): " + memory1);
	}
	public void Simplyway() throws IOException {
		Runtime runtime = Runtime.getRuntime();
		runtime.gc();
		long memory = runtime.totalMemory() - runtime.freeMemory();
		
		File f=null;
		f=new File(folderName);                 
		File[] list=f.listFiles();        
		for(int i=0;i<list.length;i++){
		       BufferedReader buffer=new BufferedReader(new FileReader(folderName+"/"+list[i].getName()));
		        StringBuffer sb=new StringBuffer();
		        
		        String str=null;
		        while((str=buffer.readLine())!=null){
		            sb.append(str);
		        }
		         
		       
		        buffer.close();
		        ArrayList<String> file = new ArrayList<String>();

		        Pattern pattern=Pattern.compile("[a-zA-Z]{3,}+");
		        Matcher matcher=pattern.matcher(sb.toString());
		        String word="";
		        while(matcher.find()){
		            word=matcher.group();
		            if(!word.equals("the")|| !word.equals("The")) {
			            file.add(word);
		            }


		        }
			simply.add(file);
		}
        runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory1 = runtime.totalMemory() - runtime.freeMemory() - memory;
        System.out.println("Used memory for Simply is (bytes): " + memory1);
	}
	public String searchbyDet (String s) {

		ArrayList<DocumentFilterDet> temp = new ArrayList<DocumentFilterDet>(Det) ;
		ArrayList<DocumentFilterDet> temp2 = new ArrayList<DocumentFilterDet>() ;
        Pattern pattern=Pattern.compile("[a-zA-Z]{3,}+");
        Matcher matcher=pattern.matcher(s);
        String word="";
        while(matcher.find()){

            word=matcher.group();
            
    		for(DocumentFilterDet a : temp) {
    			//System.out.println(a.getDocument());
    			if(a.appears(word)){
    				
    				temp2.add(a);
    			}
    		}
			temp.clear();
			temp = new ArrayList<DocumentFilterDet>(temp2);
			temp2.clear();

        }
        StringBuilder sb = new StringBuilder();
		for (DocumentFilterDet a : temp) {
			//System.out.println(a.getDocument());
			sb.append(a.getDocument()+"+");
		}
		return sb.toString();
		
	}
	public String searchbyRan (String s) {
		ArrayList<DocumentFilterRan> temp = new ArrayList<DocumentFilterRan>(Ran) ;
		ArrayList<DocumentFilterRan> temp2 = new ArrayList<DocumentFilterRan>() ;
        Pattern pattern=Pattern.compile("[a-zA-Z]{3,}+");
        Matcher matcher=pattern.matcher(s);
        String word="";

        while(matcher.find()){
        	
            word=matcher.group();
    		for(DocumentFilterRan a : temp) {
    			
    			if(a.appears(word)){
    				//System.out.println(a.getDocument());
    				temp2.add(a);
    			}
    		}
			temp.clear();
			temp = new ArrayList<DocumentFilterRan>(temp2);
			temp2.clear();

        }
        StringBuilder sb = new StringBuilder();
		for (DocumentFilterRan a : temp) {
			//System.out.println(a.getDocument());
			sb.append(a.getDocument()+"+");
		}
		return sb.toString();
		
	}
}
