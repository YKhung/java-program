package Single;

/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Test
{
	public static void main (String[] args) throws java.lang.Exception
	{
		List<Integer> ret = grayCode(2);
		System.out.print(ret);
		// your code goes here
	}
	public static List<Integer> grayCode(int n) {
        List<Integer> ret = new ArrayList<Integer>();
        if (n <=0 ) {
            return ret;
        }
        ArrayList<Integer> a = new ArrayList<Integer>();
        count (n, ret,a);
        return ret;
    }
    public static void count(int n, List<Integer> ret, ArrayList<Integer> a){
    	
        if (n == a.size()) {
            ret.add(TwoTen(a));
            System.out.println(a);
            return;
        }
        for (int i =0; i<2; i ++) {
            a.add(i);
            
            count(n, ret,a);
            a.remove(a.size()-1);
        }
        
    }
    public static int TwoTen (ArrayList<Integer> a) {
        int sum =0;
        int i = 0;
        int j = a.size()-1;
        for (; i < a.size(); i ++,j--) {
            sum+=(a.get(j)*Math.pow(2, i));
        }
        return sum;
    }
}