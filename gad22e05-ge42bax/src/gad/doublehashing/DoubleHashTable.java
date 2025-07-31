package gad.doublehashing;


import java.util.Optional;

public class DoubleHashTable<K, V> {
	private int primeSize;
	private HashableFactory<K> hashableFactory;
	private Pair<K, V>[] array;
	private DoubleHashable<K> doubleHashable;
	//private int [] array =new Array;

	@SuppressWarnings("unchecked")
	public DoubleHashTable(int primeSize, HashableFactory<K> hashableFactory) {
		this.primeSize = primeSize;
		this.hashableFactory = hashableFactory;
		this.doubleHashable = hashableFactory.create(primeSize);
		this.array = new Pair[primeSize];
		// Erstellt ein Array von Pairs: (Pair<K, V>[]) new Pair[primeSize];
	}

	public int hash(K key, int i) {
		int hashValue=(doubleHashable.hash(key) + i * doubleHashable.hashTick(key)) % primeSize;

		return hashValue ;
	}

	public boolean insert(K k, V v) {
		boolean value = false;

		if (array[hash(k, 0)] != null) {
			//Overwriting
			if (array[doubleHashable.hash(k)].one().equals(k)) {
				array[hash(k, 0)] = new Pair<>(k, v);
				return true;
				//Collision
			} else if (!array[doubleHashable.hash(k)].one().equals(k)) {
				int i = 1;
				while (true) {
					//New value
					if (array[hash(k, i)] == null) {
						array[hash(k, i)] = new Pair<>(k, v);
						return  true;
						//break;
						//Overwriting
					} else if (array[hash(k,i)].one().equals(k)) {
						array[hash(k, i)] = new Pair<>(k, v);
						return  true;
						//break;
						//To ovoid infinite loop
					} else if (i ==array.length) {
						return  false;
						//break;
					}
					i++;
				}
			}
			return true;

		} else if (array[hash(k, 0)] == null) {
			array[hash(k, 0)] = new Pair<>(k, v);
			return true;
		} else
			return false;
			//return  false;

	}




	public Optional<V> find(K k) {
		for (int i = 0; i <array.length; i++) {
			if (array[i] != null) {
				if (array[i].one().equals(k)) {

					return Optional.of(array[i].two());

				}
			}
		}

		return Optional.empty();

	}


	public int collisions() {
		int count=0;
		for (int i = 0; i <array.length ; i++) {

			if( array[i]!=null) {
				if (doubleHashable.hash(array[i].one()) != i) {
					count++;
				}
			}
		}
		return count;
	}

	public int maxRehashes() {
		return 0;
	}

	public static void main(String[] args) {
		DoubleHashTable<Integer, Integer> d=new DoubleHashTable<>(11,new IntHashableFactory());
		//System.out.println(d.insert(1,2));
		System.out.println(d.insert(20,3));

		System.out.println(d.insert(34,1));
		System.out.println(d.insert(45,4));
		System.out.println(d.insert(70,42));

		System.out.println(d.insert(56,1));

		//System.out.println(d.find(3));
		System.out.println(d.collisions());
	}
}
