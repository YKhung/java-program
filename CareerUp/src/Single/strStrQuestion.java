package Single;

public class strStrQuestion {
	public static void main(String arg[]){
		String s="a";
		String t="a";
		System.out.println(strStr(s,t));
		
	}
    public static int strStr(String source, String target) {
        //write your code here
        if(source == null || target == null){
            return -1;
        }

        int Slen=source.length();
        int Tlen=target.length();
        if(Slen<Tlen){
            return -1;
            
        }
        int num = 0;
        for(int i = 0;i<=Slen-Tlen;i++){
        	num=0;
            for(int j = 0;j<Tlen;j++){
                if(source.charAt(i+j)==target.charAt(j)){
                    num++;
                }
            }
            
            if(num==Tlen){
                return i;
            }
        }
        return -1;
    }
}
