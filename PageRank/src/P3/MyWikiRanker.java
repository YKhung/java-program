package P3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MyWikiRanker {
	private String filename;
	private double approx;
	private MinHash minhash;
	public MyWikiRanker(String f, double a, int k) throws IOException {
		this.filename = f;
		this.approx = a;
		PageRank pr = new PageRank(this.filename, this.approx);
		
		FileWriter fw = new FileWriter(new File("./space/PageRank.txt"));
		FileWriter fw1 = new FileWriter(new File("./space/OutDegree.txt"));
		FileWriter fw2 = new FileWriter(new File("./space/InDegree.txt"));
		//top page rank
		ArrayList<String> list = pr.topKPageRank(k);
		System.out.println("top " + k + " page rank created in file PageRank.txt under space folder");
		for(int i=0; i<list.size(); i++) {
			fw.write(list.get(i) + "\r\n");
			//System.out.println(list.get(i));
		}
		
		
		//top out degree
		ArrayList<String> list1 = pr.topKOutDegree(k);
		System.out.println("\ntop " + k + " out degree created in file outDegree.txt under space folder");
		for(int i=0; i<list1.size(); i++) {
			fw1.write(list1.get(i) + "\r\n");
			//System.out.println(list1.get(i));
		}
		
		//top in degree
		ArrayList<String> list2 = pr.topKInDegree(k);
		System.out.println("\ntop " + k + " in degree created in file inDegree.txt under space folder");
		for(int i=0; i<list2.size(); i++) {
			fw2.write(list2.get(i) + "\r\n");
			//System.out.println(list2.get(i));
		}
		
		fw.close();
		fw1.close();
		fw2.close();
		
		this.minhash = new MinHash("./space", 600);
		System.out.println();
		System.out.println();
		System.out.println("PageRank VS OutDegree :" +  minhash.exactJaccard("PageRank.txt", "OutDegree.txt"));
		System.out.println("PageRank VS InDegree :"  + minhash.exactJaccard("PageRank.txt", "InDegree.txt"));
		System.out.println("OutDegree VS InDegree :"  + minhash.exactJaccard("OutDegree.txt", "InDegree.txt"));
	}
}
