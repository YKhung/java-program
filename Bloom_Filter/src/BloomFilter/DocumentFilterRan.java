package BloomFilter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DocumentFilterRan extends BloomFilterRan {

	DocumentFilterRan(int bitsPerElement, String fileName, String pathName) throws IOException {
		super(bitsPerElement, fileName, pathName);
		addDocument();
	}

	

	public void addDocument() throws IOException{
	       BufferedReader buffer=new BufferedReader(new FileReader(pathName+"/"+fileName));
	        StringBuffer sb=new StringBuffer();
	        
	        String str=null;
	        while((str=buffer.readLine())!=null){
	            sb.append(str);
	        }
	         
	        buffer.close();
	        List<String> file = new ArrayList<String>();

	        Pattern pattern=Pattern.compile("[a-zA-Z]{3,}+");
	        Matcher matcher=pattern.matcher(sb.toString());
	        String word="";
	        while(matcher.find()){
	            word=matcher.group();
	            file.add(word);

	        }
	        setSize = file.size();
	        //System.out.println(getDocument()+"+"+file.size());
			this.bits =new BitSet(file.size() * bitsPerElement);
			for(String c : file) {
	            if(!c.equals("the")|| !c.equals("The")){
	            	add(word);
	            }
			}
	        

	}


	public String getDocument() {
		return fileName;
		
	}

	public int dataSize(){
		return setSize;
	}


}
