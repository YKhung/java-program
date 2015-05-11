package P3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Iterator;

public class MinHash {

	private int numPermutations;
	private String folder;
	private int numOfterm;
	private int p;
	private int a[];
	private int b[];
	public String[] docNames;
	private Map<String, int[]> minhash = new HashMap<String, int[]>();
	private HashMap<String, boolean[]> termsMatix = new HashMap<String, boolean[]>();

	/**
	 * @throws IOException
	 * 
	 */
	public MinHash(String folder, int numPermutations) throws IOException {
		this.numPermutations = numPermutations;
		this.folder = folder;

		AddDocument();

		Random ran = new Random();

		this.p = prime(numOfterm);
		a = new int[numPermutations];
		b = new int[numPermutations];
		for (int i = 0; i < numPermutations; i++) {
			a[i] = (int) ran.nextInt(numOfterm);
			b[i] = (int) ran.nextInt(numOfterm);
		}
		/*modify here*/
		int count=0;
		docNames = new String[termsMatix.size()];
		for (String filename : termsMatix.keySet()) {
			docNames[count++] = filename;
			int[] minHash = new int[numPermutations];
			boolean[] array = termsMatix.get(filename);
			for (int i = 0; i < numPermutations; i++) {
				int min = Integer.MAX_VALUE;
				for (int j = 0; j < array.length; j++) {
					if (array[j]) {
						int val = (a[i] * j + b[i]) % p;
						min = Math.min(val, min);
					}
				}
				minHash[i] = min;
			}
			minhash.put(filename, minHash);
		}

	}

	public int[] minHashSig(String fileName) throws IOException {

		return minhash.get(fileName);

	}

	private int prime(int origin) {
		int value = origin;
		while (true) {
			if (isprime(value)) {
				return value;
			}
			value++;
		}
	}

	private boolean isprime(int n) {
		if (n < 2) {
			return false;
		}
		int sqrt = (int) Math.sqrt(n);
		for (int i = 2; i <= sqrt; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public void AddDocument() throws IOException {
		HashMap<String, HashSet<String>> allStringsPerFile = new HashMap<String, HashSet<String>>();
		ArrayList<String> terms = new ArrayList<String>();
		File file = new File(folder);
		File[] files = file.listFiles();
		for (File f : files) {		
			
			HashSet<String> hashSet = new HashSet<String>();
			BufferedReader buffer = new BufferedReader(new FileReader(f));
			StringBuffer sb = new StringBuffer();
			String str = null;
			while ((str = buffer.readLine()) != null) {
				sb.append(str);
			}
			buffer.close();

			Pattern pattern = Pattern.compile("[a-zA-Z]{3,}+");
			Matcher matcher = pattern.matcher(sb.toString());
			String word = "";
			while (matcher.find()) {
				word = matcher.group().toLowerCase();
				if (!word.equals("the") || !word.equals("The")) {
					hashSet.add(word);
					if (!terms.contains(word)) {
						terms.add(word);
					}
				}
			}
			allStringsPerFile.put(f.getName(), hashSet);
		}


		for (String filename : allStringsPerFile.keySet()) {
			HashSet<String> hashSet = allStringsPerFile.get(filename);
			boolean[] ary = new boolean[terms.size()];
			for (int i = 0; i < terms.size(); i++) {
				String term = terms.get(i);
				ary[i] = hashSet.contains(term) ? true : false;
			}
			termsMatix.put(filename, ary);
		}
		numOfterm = terms.size();

	}

	public int[][] minHashMatrix() {
		int[][] matrix = new int[numPermutations][minhash.keySet().size()];
		Iterator<String> iter = minhash.keySet().iterator();
		for (int i = 0; i < minhash.keySet().size(); i++) {
			int[] hashs = minhash.get(iter.next());
			for (int j = 0; j < numPermutations; j++) {
				matrix[j][i] = hashs[j];
			}
		}
		return matrix;

	}

	public int numTerms() {
		return numOfterm;

	}

	public int numPermutations() {
		return numPermutations;
	}

	public double exactJaccard(String file1, String file2) {

		boolean[] document1 = termsMatix.get(file1);
		boolean[] document2 = termsMatix.get(file2);
		int intersection = 0;
		int all = 0;
		for (int i = 0; i < document1.length; i++) {
			if (document1[i] || document2[i]) {
				if (document1[i] && document2[i])
					intersection++;
				all++;
			}
		}
		return (double) intersection / all;

	}

	public double approximateJaccard(String file1, String file2)
			throws IOException {
		int[] document1 = minhash.get(file1);
		int[] document2 = minhash.get(file2);
		int intersection = 0;
		for (int i = 0; i < document1.length; i++) {
			if (document1[i] == document2[i])
				intersection++;
		}
		return (double) intersection / document1.length;

	}

}