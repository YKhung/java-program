package chapter_1;

//Assume you have a method isSubstring which checks if one word is a substring of another. Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using only one call to isSubstring 
//(i.e., ¡°waterbottle¡± is a rotation of ¡°erbottlewat¡±)
public class Question1_8 {
    public static void main(String[] args)  
    {   
          
        System.out.println(isRotation("waterbottle", "terbottlewa"));  
    }  
      
    public static boolean isRotation(String string1, String string2)  
    {  
        // test if the two strings have different length or have length 0  
        if (string1.length() != string2.length() || string1.length() == 0)  
            return false;  
          
        return isSubstring(string1 + string1, string2);  
    }  
	public static boolean isSubstring(String string1, String string2)  
    {  
        for (int i = 0; i <= string1.length(); i++)  
            for (int j = i; j <= string1.length(); j++)  
                if (string1.substring(i, j).equals(string2))  
                    return true;  
          
        return false;  
    } 

}
