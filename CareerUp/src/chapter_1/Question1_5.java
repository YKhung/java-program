package chapter_1;
//Implement a method to perform basic string compression using the counts of repeated characters.
public class Question1_5 {
   public static void main(String arg[]){
	   String str="aabcccccdaaa";
	   System.out.println(Compression(str));  
   }
   public static String Compression(String str){
	   int size=CoutCompression(str);
	   if(size>=str.length()){
		   return str;
		   
	   }
	   char[] array=new char[size];
	   int index=0;
	   char last=str.charAt(0);
	   int count=1;
	   for(int i=1;i<str.length();i++){
		   if(str.charAt(i)==last){
			   count++;
		   }
		   else{
			   index=setchar(array,last,index,count);
			   last=str.charAt(i);
			   count=1;
			   
		   }
		   
	   }
	   index=setchar(array,last,index,count);
	return String.valueOf(array);
	   
   }
   public static int setchar(char[]array,char c,int index,int count){
	   
	   array[index]=c;
	   index++;
	   char[] cnt=String.valueOf(count).toCharArray();
	   for(char x:cnt){
		   array[index]=x;
		   index++;
	   }
	   
	return index;
	   
	   
   }
   public static int CoutCompression(String str){
	   if(str==null||str.isEmpty()) return 0;
	   char last=str.charAt(0);
	   int size=0;
	   int count=1;
	   for(int i=1;i<str.length();i++){
		   if(str.charAt(i)==last){
			   count++;
		   }
		   else{
			   last=str.charAt(i);
			   size=1+String.valueOf(count).length()+size;
			   count=1;
		   }
	   }
	   size+=1+String.valueOf(count).length();
	   return size;
   }
}
