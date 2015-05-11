package BloomFilter;

import java.util.UUID;

public class FalsePositives {
	
		   private int numberOfAddedElements;
		   private int bitPerElement;
		   public FalsePositives (int numberOfAddedElements,int bitPerElement) {
			   this.numberOfAddedElements = numberOfAddedElements;
			   this.bitPerElement = bitPerElement;
		   }
 
		  

		    public double getFalsePositiveProbability() {  
		        // (1 - e^(-k * n / m)) ^ k  
		        return Math.pow(0.618,bitPerElement);  
		  
		    }  
		  

		    public String getFNVFalsePositiveProbability(int TestnumberOfElements) {  
		    	int size = 0;
		    	BloomFilterDet a = new BloomFilterDet(numberOfAddedElements,bitPerElement);
		    	       for(int i=0;i<numberOfAddedElements+TestnumberOfElements;i++){
		    	    	   String uu = UUID.randomUUID().toString();  
		    	    	   if(i<numberOfAddedElements){
		                   a.add(uu);
		    	    	   } else {
		    	    		if(a.appears(uu)){
		    	    			size++;
		    	    		}
		    	    	   }
		               }
		    	      double rate = (double) size/TestnumberOfElements;
		    	 return "ExpectedFalse is:"+getFalsePositiveProbability()+"   Curr FNVBF is :"+rate;
		    }
		    public String getRANFalsePositiveProbability(int TestnumberOfElements) {  
		    	int size = 0;
		    	BloomFilterRan a = new BloomFilterRan(numberOfAddedElements,bitPerElement);
		    	       for(int i=0;i<(numberOfAddedElements+TestnumberOfElements);i++){
		    	    	   if(i<numberOfAddedElements){
		                   a.add(UUID.randomUUID().toString());
		    	    	   } else {
		    	    		if(a.appears(UUID.randomUUID().toString())){
		    	    			size++;
		    	    		}
		    	    	   }
		               }
		    	       double rate = (double) size/TestnumberOfElements;
		    	       
		    	 return "ExpectedFalse is:"+getFalsePositiveProbability()+"   Curr RANBF is :"+rate;
		    }
}
