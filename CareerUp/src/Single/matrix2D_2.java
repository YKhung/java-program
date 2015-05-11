package Single;

import java.util.ArrayList;

public class matrix2D_2 {
		public static void main(String arg[]){
			String a="this is blue";
			System.out.println(reverse(a,0,11));
			
		}
	    public static String reverse(String s, int start, int end) {
	        for (int i = start, j = end; i < j; i++, j--) {
	            char temp = s.charAt(i);
	            s.replace(s.charAt(i), s.charAt(j));
	            s.replace(s.charAt(j), temp);

	        }
	        return s;
	    }
	    public static int searchMatrix(ArrayList<ArrayList<Integer>> matrix, int target) {
	            if(matrix == null || matrix.size()==0 || matrix.get(0).size()==0) { 
	            return 0;
	            }
	            int row = matrix.size() -1;
	            int column = 0;
	            int count = 0;

	            
	            while(row != -1 && column != matrix.get(0).size()) {
	                if(matrix.get(row).get(column) < target) {
	                    column = column +1;
	                }
	                if(matrix.get(row).get(column) > target) {
	                    row = row -1;
	                }
	                if(matrix.get(row).get(column) == target) {
	    	            System.out.println(row);
	    	            System.out.println(column);
	                    count = count +1;
	                    row = row -1;
	                    column = column +1;
	    	            System.out.println(row);
	    	            System.out.println(column);
	                }
	            }
	            return count;
	    }
}
