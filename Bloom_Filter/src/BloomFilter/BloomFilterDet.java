package BloomFilter;
import java.math.BigInteger;
import java.util.BitSet;
public class BloomFilterDet {
    protected  BitSet bits;
    protected int setSize;
    protected int bitsPerElement;
    protected int size;
    protected int k;
	protected String fileName;
	protected String pathName;
	BloomFilterDet(int setSize, int bitsPerElement) {
		this.setSize = setSize;
		this.bitsPerElement = bitsPerElement;
		this.bits =new BitSet(setSize * bitsPerElement);
		this.k = (int) (bitsPerElement * Math.log(2.0));
	}
	BloomFilterDet(int bitsPerWord, String fileName, String pathName) {
		this.setSize = 0;
		this.bitsPerElement = bitsPerWord;
		this.fileName =fileName;
		this.pathName = pathName;
		this.k = (int) (bitsPerElement * Math.log(2.0));
	}
	
	public void add(String s) {
		int [] hashCodes = null;
		if(s == null) {
			return;
		}
		if(s != null) {
			hashCodes = new FNVHash().getintHashCodes(s.toLowerCase(), k);
			//System.out.println();
		if(!appears(s)) {
		for(int i =0; i < k; i ++) {
			//System.out.println(hashCodes[i]);
			
			bits.set(hashCodes[i],true);
			
		}
		size++;
		}	
		}
	}
	public boolean appears(String s){
		if(s == null) return false;  
        boolean ret = true;  
        int [] hashCodes = new FNVHash().getintHashCodes(s.toLowerCase(), k);
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
	class FNVHash {
		   
	    private  final BigInteger INIT64  = new BigInteger("cbf29ce484222325", 16);
	    private  final BigInteger PRIME64 = new BigInteger("100000001b3",      16);
	    private  final BigInteger MOD64   = new BigInteger("2").pow(64);
	    public int[] getintHashCodes(String object, int k) {

	        
	    	int[] hashCodes = new int[k];

	    	

	            for (int i = 0; i < k; i++) {
	            	BigInteger newHash = hash(object, i);
	            	int a = newHash.intValue()%(setSize * bitsPerElement);
	                hashCodes[i] = a < 0 ? a*-1:a;
	                
	            }

	            return hashCodes;
	        
	    }
	    public BigInteger hash(String value,int seed) {
	    	BigInteger result = INIT64;   
	    		byte[] b = value.getBytes();
	    		for(byte c : b) {
	    			result = result.xor(BigInteger.valueOf((int) c>>seed & 0xff));
	    			result = result.multiply(PRIME64).mod(MOD64);
	    		}
	        return  result;  
	    }
	}
}
