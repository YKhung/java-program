package BloomFilter;
import java.util.BitSet;
import java.util.Random;

	class BloomFilterRan {
	public  BitSet bits;
	public int setSize;
	public int bitsPerElement;
	public int size;
	public int k;
	public int[] hashA;
	public int[] hashB;
	protected String fileName;
	protected String pathName;
	BloomFilterRan(int setSize, int bitsPerElement) {
		this.setSize = setSize;
		this.bitsPerElement = bitsPerElement;
		this.bits =new BitSet(setSize * bitsPerElement);
		this.k = (int) (bitsPerElement * Math.log(2.0));
		this.hashA = new int[k];
		this.hashB = new int[k];
		setHash();
	}
	BloomFilterRan(int bitsPerElement,String fileName, String pathName) {
		this.setSize = 0;
		this.fileName =fileName;
		this.pathName = pathName;
		this.bitsPerElement = bitsPerElement;
		this.k = (int) (bitsPerElement * Math.log(2.0));
		this.hashA = new int[k];
		this.hashB = new int[k];
		
	}
	protected void setHash() {

		Random ran = new Random();
		for (int i =0; i < k; i ++) {

			hashA[i] = ran.nextInt(prime());
			hashB[i] = ran.nextInt(prime());
		}
		
	}
	private int prime() {
		Random ran = new Random();
		while(true){
		int value = setSize+ran.nextInt(setSize);
			if(isprime(value)) {
				return value;
			}
		}
	}
	private boolean isprime(int n){
		if (n < 2) {
			return false;
		}
		int sqrt = (int)  Math.sqrt(n) ;
		for (int i = 2;  i <= sqrt;  i++)  {
			if (n % i == 0)  return false;
		}
		return true;
	}
	public void add(String s) {
		int [] hashCodes = null;
		if(s != null) {
			hashCodes = new RANHash().getintHashCodes(s.toLowerCase(), k,hashA,hashB);
		}
		for(int i =0; i < k; i ++) {
			bits.set(hashCodes[i],true);
		}
		size++;
	}
	public boolean appears(String s){
		if(s == null) return false;  
        boolean ret = true;  
        int [] hashCodes = new RANHash().getintHashCodes(s.toLowerCase(), k,hashA,hashB);
        for(int f : hashCodes){ 
            ret = ret && bits.get(f);
            if(ret == false) {
            	return false;
            }
        }
        return ret;
	}
	public int filterSize(){
		return setSize * bitsPerElement;
	}
	public int dataSize(){
		return size;
	}
	public int numHashes(){
		return k;
	}
	class RANHash {
	    public int[] getintHashCodes(String object, int k,int[] hashA,int[] hashB) {

	        
	    	int[] hashCodes = new int[k];


	            for (int i = 0; i < k; i++) {
	            	int newHash = hash(object, hashA[i],hashB[i]);
	                hashCodes[i] = newHash;
	            }

	            return hashCodes;
	        
	    }
	    public int hash(String value,int A,int B) {  
	    	int result = 0;  
	    		result = value.hashCode() < 0 ? ~(value.hashCode())+1 : value.hashCode();
	            result = (A*result+ B)  % (setSize * bitsPerElement) ;  
	   
	            return   result < 0 ? ~result+1 : result;  
	    }
	}
}
