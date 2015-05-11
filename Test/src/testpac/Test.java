package testpac;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Test {
	 public static void main(String[] args){
		 ArrayList<ArrayList<Integer>> s = createBtreeByLevel(4);
		 
		 }
	 public static ArrayList<ArrayList<Integer>> createBtreeByLevel(int level){
		 ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		 ArrayList<Integer> first = new ArrayList<Integer>();
		 int sum =getRandomNum(5);
		 first.add(sum);
		 result.add(first);
		 
		 for(int i = 1; i <level; i ++){
			 ArrayList<Integer> temp = result.get(i-1); 
			 ArrayList<Integer> list = new ArrayList<Integer>();
				 for(int j : temp){
					 
					 for(int k = 0; k < j; k++){
						 int a = getRandomNum(5);
						 sum = sum+a;
						 list.add(a);
					 }
					 
				 }	
				 result.add(new ArrayList<Integer>(list));
			 
		 }
		 System.out.print(sum);

		return result;
		 
	 }
	 public static int getRandomNum(int Max) {
	        int max=Max;
	        int min=3;
	        Random random = new Random();

	        int s = random.nextInt(max)%(max-min+1) + min;
			return s;
	 }
	
	

}
