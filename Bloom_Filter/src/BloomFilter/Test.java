package BloomFilter;

import java.io.IOException;


public class Test {
    public static void main(String[] args) throws IOException {  




    	BloomSearch bb = new BloomSearch("C:/Users/xianqin/Desktop/sci.space");
    	//bb.addAllDocumentRan();
    	//System.out.println(bb.searchbyRan("sci astro"));
    	bb.addAllDocumentDet();
    	System.out.println(bb.searchbyDet("sci astro"));
    	//bb.Simplyway();
    	

//	        FalsePositives f = new FalsePositives(10000,8);
//	        System.out.println("Current bit is 8   " +f.getRANFalsePositiveProbability(1000000));
//	        System.out.println("Current bit is 8   " +f.getFNVFalsePositiveProbability(1000000));
//	        FalsePositives f1 = new FalsePositives(10000,16);
//	        System.out.println("Current bit is 16   " +f1.getRANFalsePositiveProbability(1000000));
//	        System.out.println("Current bit is 16   " +f1.getFNVFalsePositiveProbability(1000000));



    } 

}
