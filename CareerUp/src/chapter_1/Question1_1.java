package chapter_1;
//Implement an algorithm to determine if a string has all unique characters  What if you 
//can not use additional data structures

//Author: Yin Kam Hung
public class Question1_1 {

	
	public static boolean ifDuplicated (String str){
		int i,j;
		i=0;
		int len=str.length();
		while (i<len) {
			for(j=i+1;j<len;j++){
				if(str.charAt(i)==str.charAt(j))
					return false;
			}
			i++;
		}		
		return true;

	}
	
	public static boolean isUnique_ASCII(String str) {
		boolean[] ascii_set = new boolean[256];
		for (int i = 0; i < str.length(); i++) {
			int index = str.charAt(i);
			if (ascii_set[index] == false) ascii_set[index] = true;
			else if (ascii_set[index] == true) return false;
		}
		return true;
	}
	
	
	
	public static void main(String[] args){
		
		String s1 = "ABC";
		System.out.println(ifDuplicated(s1));
		System.out.println(isUnique_ASCII(s1));
		
		
		
	}	
}
