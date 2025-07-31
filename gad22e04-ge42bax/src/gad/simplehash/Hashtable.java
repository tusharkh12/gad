package gad.simplehash;

import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Stream;

public class Hashtable<K, V> {
	private List<Pair<K, V>>[] table;
	private int[] key;
	@SuppressWarnings("unchecked")
	public Hashtable(int minSize, int[] a) {
		table = (List<Pair<K, V>>[]) new List[getNextPowerOfTwo(minSize)];
		this.key=a;
		for (int i = 0; i < table.length ; i++) {
			table[i]= new ArrayList<>();

		}
		// TODO: Change and complete
		//table = (List<Pair<K, V>>[]) new List[42];
	}

	public List<Pair<K, V>>[] getTable() {
		return table;
	}

	public static int getNextPowerOfTwo(int i) {

			int n=2;
			int potenz=2;
			if(1>=i){
				return 1;
			}
			for (int j = 0; j <=i ; j++) {
				if(potenz>=i){
					break;
				}
				potenz=potenz*n;

				//n=n+2;
			}
		return potenz;

	}

	public static int fastModulo(int i, int divisor) {
		if(divisor==0 || i<=0){
			return 0 ;
		}
		int n= i-divisor*(i/divisor);
		return n;
	}

	private byte[] bytes(K k) {
		return k.toString().getBytes(Charset.forName("UTF-8"));
	}

	public int h(K k, ModuloHelper mH) {
		int sum=0;
		int count=0;
		byte[] x = bytes(k);
		for (int i = 0; i <x.length ; i++) {
			sum=sum+ x[i]*key[count];
			count++;
			if(count==key.length){
				count=0;
			}
		}

		return 	mH.doTheMagic(sum, table.length);
	}

	public void insert(K k, V v, ModuloHelper mH) {
		//h(k,mH);
		table[h(k,mH)].add(new Pair<>(k,v));

	}

	public boolean remove(K k, ModuloHelper mH) {
		//int b=h;
		List<Pair<K, V>> list=table[h(k,mH)];
		List<Pair<K,V>> values=new ArrayList<>();
		boolean status = false;
		for (int i = 0; i <list.size(); i++) {
			if (list.get(i).one().equals(k) ){
				values.add(list.get(i));
				status=true;
			}
		}
		for (Pair<K,V> e: values) {
			list.remove(e);

		}


		return status;
	}

	public Optional<V> find(K k, ModuloHelper mH) {
		int b=h(k,mH);
		for (int i = 0; i <table[b].size(); i++) {
			if (table[h(k, mH)].get(i).one().equals(k)) {

				return Optional.of(table[h(k, mH)].get(i).two());
				//return  );

			}
		}

		return Optional.empty();
	}

	public List<V> findAll(K k, ModuloHelper mH) {
		int b = h(k, mH);
		List<V> list = new ArrayList<>();
		for (int i = 0; i < table[b].size(); i++) {
			if (table[h(k, mH)].get(i).one().equals(k)) {
				list.add(table[b].get(i).two());

				//return Optional.of(table[h(k, mH)].get(i).two());
				//return  );
				}

		}
		return list;
	}


	public Stream<Pair<K, V>> stream() {
		return Stream.of(table).filter(ps -> ps != null).flatMap(List::stream);
	}

	public Stream<K> keys() {
		return stream().map(Pair::one).distinct();
	}

	public Stream<V> values() {
		return stream().map(Pair::two);
	}

//	public static void main(String[] args) {
//		System.out.println(getNextPowerOfTwo(17));
//	}
}
