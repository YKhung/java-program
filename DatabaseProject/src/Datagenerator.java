import java.io.*;


public class Datagenerator {
	public static void main(String arg[]) throws IOException{
		FileWriter fw = new FileWriter("C:\\Users\\xianqin\\Desktop\\test.txt");   
		String str1 = insertNode("Vertex",3,4,5,6,7,8,"1","2")+System.getProperty("line.separator");
		String str2 = insertNode("Vertex",3,4,5,6,7,8,"1","2")+System.getProperty("line.separator");
		String str3 = insertNode("Vertex",3,4,5,6,7,8,"1","2")+System.getProperty("line.separator");
		fw.write(str1);
		fw.write(str2);
		fw.write(str3);
		fw.close();
		System.out.println(insertNode("Vertex",3,4,5,6,7,8,"1","2"));
	}
	public static String insertNode(String name, int a1,int a2,int a3, int a4, int a5, int a6, String str1, String str2){
		String results;
		
		results ="insert "+ name+" " +"values("+Integer.toString(a1)+","+Integer.toString(a2)+","+Integer.toString(a3)+","+Integer.toString(a4)+","+Integer.toString(a5)+","+Integer.toString(a6)+","+str1+","+str2+");" ;
		return results;
	}
}
