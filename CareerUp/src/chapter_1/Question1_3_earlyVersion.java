package chapter_1;
// Design an algorithm and write code to remove the duplicate characters in a string without using any additional buffer. 
//NOTE: One or two additional variables are fine. An extra copy of the array is not. FOLLOW UP: Write the test cases for this method.
//1. String does not contain any duplicates, e.g.: abcd; 2. String contains all duplicates, e.g.: aaaa; 3. Null string; 4. Empty string; 5. String with all continuous duplicates, e.g.: aaabbb; 
//6. String with non-contiguous duplicates, e.g.: abababa
public class Question1_3_earlyVersion {
    public static void main(String[] args)  
    {  
 
        StringBuffer[] strings =   
            {  
                new StringBuffer("abcdefg"),  
                new StringBuffer("aabbccddeeffgghhiijjkkllmmnn"),  
                new StringBuffer("abcdefgabcdefg"),  
                new StringBuffer("aaaaaaa"),  
                new StringBuffer("t"),  
                new StringBuffer("ababababababa"),  
                new StringBuffer("12 32 9 ' e.g e' f"),  
                new StringBuffer(),  
            };  
          
        for (StringBuffer str : strings)  
        {  
            DeleteDuplicate2(str);  
            System.out.println(str);  
        }  
    } 
    public static void DeleteDuplicate1(StringBuffer str)  
    {  
        if (str.length() == 0 || str.length() == 1)  
            return;  
          
        boolean[] chars = new boolean[256];  
        int i, tail = 0;  
          
        for (i = 0; i < str.length(); i++)  
        {  
            // there is no duplicate at i, so add str[i] to tail.(copy and increment)  
            if (chars[str.charAt(i)] == false)  
            {  
                chars[str.charAt(i)] = true;  
                str.setCharAt(tail, str.charAt(i));  
                tail++;  
            }  
        }  
          
        str.delete(tail, str.length());  
    }
    public static void DeleteDuplicate2(StringBuffer str)  
    {  
        if (str.length() == 0 || str.length() == 1)  
            return;  
          
        int i, j, tail = 1;  
        for (i = 1; i < str.length(); i++)  
        {  
            // check if there is duplicate  
            for (j = 0; j < tail; j++)  
                if (str.charAt(i) == str.charAt(j))  
                    break;  
              
            // there is no duplicate  
            if (j == tail)  
            {  
                str.setCharAt(tail, str.charAt(i));  
                tail++;  
            }  
        }  
          
        str.delete(tail, str.length());  
    }
}
