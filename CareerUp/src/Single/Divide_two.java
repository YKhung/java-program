package Single;

import java.util.List;

public class Divide_two {
	public static void main (String[] args) throws java.lang.Exception
	{
		
		System.out.print(divide(84,16));
		// your code goes here
	}
	public static int divide(int dividend, int divisor) {
        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);
        
        long ret = 0;
        
        while (a >= b) {
            for (long tmp = b, cnt = 1; a >= tmp; tmp <<= 1, cnt <<= 1) {
                ret += cnt;
                a -= tmp;
            }
        }
        
        ret = (dividend > 0) ^ (divisor > 0) ? -ret: ret;;
        if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        
        return (int)ret;
    }
}
