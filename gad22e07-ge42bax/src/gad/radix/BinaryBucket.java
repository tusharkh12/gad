package gad.radix;

public class BinaryBucket {

	private int[] bucket;
	private  int size;
	private int count;

	public BinaryBucket(int size) {
		this.size=size;
		bucket =new int[size];
		count =0;

	}

	public int[] getBucket() {
		return bucket;
	}

	public void setBucket(int[] bucket) {
		this.bucket = bucket;
	}

	public void insertLeft(int number) {
		for (int i = 0; i <size ; i++) {
			if(bucket[i]==0){
				bucket[i]=number;
				count++;
				break;
			}
		}

	}

	public void insertRight(int number) {
		for (int i = size-1; i >=0 ; i--) {
			if(bucket[i]==0){
				bucket[i]=number;
				break;
			}

		}

	}

	public int getMid() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

//	public int getCount() {
//		return count;
//	}

	public void logArray(Result result) {
		result.logArray(bucket);
	}

	public static void main(String[] args) {
		BinaryBucket b=new BinaryBucket(4);
		//b.insertLeft(1);
		b.insertRight(2);
		b.insertRight(3);
		b.insertRight(4);
		b.insertRight(2);
		for (int i = 0; i < b.size ; i++) {

			System.out.println(b.bucket[i]);
		}
		//System.out.println(b.bucket[2]);
	}
}
