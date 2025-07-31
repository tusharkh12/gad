package gad.doublehashing;

public class DoubleHashString implements DoubleHashable<String> {
	private int primeSize;
	private int p=31;

	public DoubleHashString(int primeSize) {
		this.primeSize=primeSize;

	}

	@Override
	public int hash(String key) {
		int c ;
		c= (key.charAt(0)*p);
		for (int i = 1; i < key.length(); i++) {
			c =  ((key.charAt(i))+c);
			c=  (p*c);

		}
		//c=key.charAt(key.length()-1)+c;
		if(c<-1){
			c=-c;
		}


		return c%primeSize;
	}

	@Override
	public int hashTick(String key) {
		int c ;
		c= (key.charAt(0)*p);
		for (int i = 1; i < key.length(); i++) {
			c =  ((key.charAt(i))+c);
			c=  (p*c);

		}
		//c=key.charAt(key.length()-1)+c;

		if(c<-1){
			c=  -c;
		}


		return 1 + (c % (primeSize-2));
	}
}