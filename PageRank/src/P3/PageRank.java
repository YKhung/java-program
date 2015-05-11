package P3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collections;
import java.util.Iterator;

public class PageRank {
	private String fileName = null;
	private double approximation = 0;
	private double beta = 0.85;
	private HashMap<String, HashSet<String>> graphAdjacentList = null;
	private int numNodes = 0;

	private HashMap<String, Double> pageRankVector = null;
	private ArrayList<pageVector> sortedPageVector = null;

	public class pageVector {
		String Vertex;
		double rank;

		public pageVector(String v, double r) {
			this.Vertex = v;
			this.rank = r;
		}
	}
	
	/**
	 * easy for me to compare the rank among pages
	 *
	 */
	public class pageComp implements Comparator<pageVector> {

		public int compare(pageVector p1, pageVector p2) {
			double ret = p1.rank - p2.rank;
			if (ret >  0)
				return -1;
			else if (ret < 0)
				return 1;
			else
				return 0;
		}

	}

	public PageRank(String fileName, double approximation) throws IOException {
		this.fileName = fileName;
		this.approximation = approximation;
		this.graphAdjacentList = new HashMap<String, HashSet<String>>();
		this.pageRankVector = new HashMap<String, Double>();
		this.sortedPageVector = new ArrayList<pageVector>();

		getEdgeFromFile();
		createPageRankVector();
		sortPagebyRank();
	}

	public void getEdgeFromFile() throws IOException {
		FileReader inputFile = new FileReader(this.fileName);
		BufferedReader bufferReader = new BufferedReader(inputFile);
		String line = null;

		// read number of edges from file
		if ((line = bufferReader.readLine()) != null) {
			this.numNodes = Integer.valueOf(line);
		}

		// read edges from file
		HashSet<String> set0 = null;
		HashSet<String> set1 = null;
		while ((line = bufferReader.readLine()) != null) {
			String[] part = line.split("\\s+");
			if (!this.graphAdjacentList.containsKey(part[0])) {
				set0 = new HashSet<String>();
			} else {
				set0 = this.graphAdjacentList.get(part[0]);
			}
			set0.add(part[1]);
			this.graphAdjacentList.put(part[0], set0);
			
			//for part[1], there may exist a situation that it has no out going links
			//so it can not appear in part[0], but we still need to add part[1] also into graph
			if (!this.graphAdjacentList.containsKey(part[1])) {
				set1 = new HashSet<String>();
				this.graphAdjacentList.put(part[1], set1);
			}

		}
		bufferReader.close();
	}

	public void createPageRankVector() {
		double num = (double) this.numNodes;
		boolean converged = false;
		// initialize the vector
		for (String name : this.graphAdjacentList.keySet()) {
			double value = 1.0 / num;
			this.pageRankVector.put(name, value);
		}
		int step = 0;
		HashMap<String, Double> p1 = null;
		while (!converged) {
			p1 = randomWalk(this.pageRankVector);
			if (Norm(this.pageRankVector, p1))
				converged = true;
			this.pageRankVector = p1;
			step++;
		}
		System.out.println("step: " + step);
		
	}

	/**
	 * simulate random walk from vector p0
	 * @param p0
	 * @return
	 */
	public HashMap<String, Double> randomWalk(HashMap<String, Double> p0) {
		HashMap<String, Double> p1 = new HashMap<String, Double>();
		int num = this.numNodes;
		//initialize the P(n+1)
		for (String name : p0.keySet()) {
			double value = (1.0 - this.beta) / num;
			p1.put(name, value);
		}
		
		for (String name : p0.keySet()) {
			// get links from page name
			HashSet<String> set = this.graphAdjacentList.get(name);
			
			//get size of set 
			int size = set.size();
			//size equal to 0, find all vertex in graph, else find links from name 
			if (size == 0) {
				for (String vertex : this.graphAdjacentList.keySet()) {
					double v = p1.get(vertex) + this.beta * (p0.get(name))
							/ num;
					p1.put(vertex, v);
				}
			} else {
				Iterator<String> itr = set.iterator();
				while (itr.hasNext()) {
					String vertex = itr.next();
					double v = p1.get(vertex) + this.beta * (p0.get(name))
							/ size;
					p1.put(vertex, v);
				}

			}

		}
		return p1;
	}

	/**
	 * sort the page rank vector by JAVA built-in algorithm
	 */
	public void sortPagebyRank() {
		for (String name : this.pageRankVector.keySet()) {
			pageVector e = new pageVector(name, this.pageRankVector.get(name));
			this.sortedPageVector.add(e);
		}

		Collections.sort(this.sortedPageVector, new pageComp());
	}

	/**
	 * @param p0
	 * @param p1
	 * @return Norm(M) as sum absolute values of all entries of M
	 */
	public boolean Norm(HashMap<String, Double> p0, HashMap<String, Double> p1) {
		double norm = 0;
		for (String name : p0.keySet()) {
			double n0 = p0.get(name);
			double n1 = p1.get(name);
			norm += Math.abs(n0 - n1);
		}

		if (norm <= this.approximation)
			return true;
		return false;
	}

	/**
	 * get number of edges in graph
	 * 
	 * @return 0 if no edges
	 */
	public int numNodes() {
		return numNodes;
	}

	/**
	 * get out degree of one vertex
	 * 
	 * @param vertexName
	 * @return 0 if no such vertex exist
	 */
	public int outDegreeOf(String vertexName) {
		if (!this.graphAdjacentList.containsKey(vertexName))
			return -1;
		return this.graphAdjacentList.get(vertexName).size();
	}

	/**
	 * get in degree of one vertex
	 * 
	 * @param vertexName
	 * @return 0 if no such vertex exist
	 */
	public int inDegreeOf(String vertexName) {
		int num = 0;
		for (String name : this.graphAdjacentList.keySet()) {
			HashSet<String> set = this.graphAdjacentList.get(name);
			if (set.contains(vertexName))
				num++;
		}
		return num;
	}
	
	/**
	 * gets an integer k as parameter and returns an array (of strings) of pages with top k out degree.
	 * @param k
	 * @return list
	 */
	public ArrayList<String> topKOutDegree(int k) {
		ArrayList<String> res = new ArrayList<String>();
		
		ArrayList<pageVector> temp = new ArrayList<pageVector>();
		// find the number of out-going links from adjacent list
		for (String vertex : this.graphAdjacentList.keySet()) {
			pageVector e = new pageVector(vertex, (double)outDegreeOf(vertex));
			temp.add(e);
		}
		//sort them in temp
		Collections.sort(temp, new pageComp());
		for(int i=0; i<k; i++) {
			res.add(temp.get(i).Vertex);
		}
		return res;
	}
	
	/**
	 * gets an integer k as parameter and returns an array (of strings) of pages with top k in degree.
	 * @param k
	 * @return list
	 */
	public ArrayList<String> topKInDegree(int k) {
		ArrayList<String> res = new ArrayList<String>();
		
		ArrayList<pageVector> temp = new ArrayList<pageVector>();
		// find the number of in-going links from adjacent list
		for (String vertex : this.graphAdjacentList.keySet()) {
			pageVector e = new pageVector(vertex, (double)inDegreeOf(vertex));
			temp.add(e);
		}
		//sort them in temp
		Collections.sort(temp, new pageComp());
		for(int i=0; i<k; i++) {
			res.add(temp.get(i).Vertex);
		}
		return res;
	}

	
	/**
	 * gets an integer k as parameter and returns an array (of strings) of pages with top k page ranks.
	 * @param k
	 * @return list
	 */
	public ArrayList<String> topKPageRank(int k) {
		ArrayList<String> res = new ArrayList<String>();
		for (int i = 0; i < k; i++) {
			String vertex = this.sortedPageVector.get(i).Vertex;
			res.add(vertex);
		}
		return res;
	}

}
