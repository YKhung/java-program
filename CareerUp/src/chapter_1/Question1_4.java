package chapter_1;
//Write a method to replace all spaces in a string with ¡®%20¡¯
public class Question1_4 {
    public static void main(String[] args)  
    {  
   
          
        System.out.println(ReplaceSpace(new String("abcdef").toCharArray()));  
        System.out.println(ReplaceSpace(new String("abc d e f").toCharArray()));  
        System.out.println(ReplaceSpace(new String(" ").toCharArray()));  
        System.out.println(ReplaceSpace(new String("  ").toCharArray()));  
    }  
  
    public static char[] ReplaceSpace(char[] str)  
    {  
        // considering the worst case, where all the elements are space, we must have an array whose  
        // size is three times the input array size. If memory size is a main consideration, we can  
        // first compute the  number of spaces.   
        char[] strClone = new char[str.length * 3 + 1];  
          
        int i, j;  
        for (i = 0, j = 0; i < str.length; i++)  
        {  
            if (str[i] != ' ')  
                strClone[j++] = str[i];  
            else  
            {  
                strClone[j++] = '%';  
                strClone[j++] = '2';  
                strClone[j++] = '0';  
            }  
        }  
        // j is at the end of strClone  
        strClone[j] = '\0';  
          
        return strClone;  
    } 
}
