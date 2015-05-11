package chapter_1;
//Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column is set to 0.
public class Question1_7 {
	public static void main(String arg[]){
		
		
        int[][] array = new int[][]{{1,0,3,4,5}, {5,6,7,8,9}, {9,0,1,2,5}, {3,4,5,6,7}};   
          
        printArray(array, 4, 5);  
        System.out.println("After setting:");  
        SetZero(array, 4, 5);  
        printArray(array, 4, 5);
		
	}
    public static void SetZero(int[][] array, int m, int n)  
    {  
        boolean[] row = new boolean[m];  
        boolean[] col = new boolean[n];  
          
        // store row and column of 0 separately  
        for (int i = 0; i < m; i++)  
            for (int j = 0; j < n; j++)  
                if (array[i][j] == 0)  
                {  
                    row[i] = true;  
                    col[j] = true;   
                }  
          
        for (int i = 0; i < m; i++)  
            for (int j = 0; j < n; j++)  
                if (row[i] == true || col[j] == true)  
                    array[i][j]= 0;   
    }  
    public static void printArray(int[][] array, int m, int n)  
    {  
        for (int i = 0; i < m; i++)  
        {  
            for (int j = 0; j < n; j++)  
                System.out.print(array[i][j] + " ");  
            System.out.println();  
        }  
    }  
}
