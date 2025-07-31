package gad.doublehashing;

public class DoubleHashInt implements DoubleHashable<Integer> {
	private int primeSize;

	public DoubleHashInt(int primeSize) {
		this.primeSize=primeSize;
	}

	@Override
	public int hash(Integer key) {
		int hashValue= (key % primeSize);
		if(key<0){
			int newKey;
			newKey=key*-1;
			hashValue=newKey % primeSize;
		}

		return hashValue;
	}

	@Override
	public int hashTick(Integer key) {
		int hashValue=1 + (key % (primeSize-1));
		if(key<0){
			int newKey;
			newKey=key*-1;
			hashValue=1 + (newKey % (primeSize-1));
		}
		return hashValue;
	}


}