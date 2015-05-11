package chapter_1;
//Given two strings, write a method to decide if one is a permutation of the other.
import java.util.Arrays;
public class Question1_3 {
	public static void main(String[] args)  
    {  
        String string1 = new String("abcaeef");  
        String string2 = new String("cbaafee");   
          
        System.out.println(IsAnagram2(string1, string2));  
    }  
  
    public static boolean IsAnagram1(String string1, String string2)  
    {  
        if (string1.length() != string2.length())  
            return false;  
          
        return sortString(string1).equals(sortString(string2));  
    }  
    private static String sortString(String string)  
    {  
        char[] ch = string.toCharArray();  
        Arrays.sort(ch);  
        return new String(ch);  
    }  
      
    public static boolean IsAnagram2(String string1, String string2)  
    {  
        if (string1.length() != string2.length())  
            return false;  
          
        char[] chars = new char[256];  
        for (int i = 0; i < string1.length(); i++)  
            chars[string1.charAt(i)]++;  
        for (int i = 0; i < string2.length(); i++)  
            chars[string2.charAt(i)]--;  
        for (int i = 0; i < 256; i++)  
            if (chars[i] != 0)  
                return false;  
        return true;  
    }  
}
