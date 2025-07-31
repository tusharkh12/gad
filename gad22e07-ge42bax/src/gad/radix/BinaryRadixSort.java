package gad.radix;

import java.util.Arrays;
import java.util.Random;

public final class BinaryRadixSort {


    private BinaryRadixSort() {

    }

    public static int key(int element, int binPlace) {
        int a = element >> binPlace;
        return (a & 1);
    }

    public static void kSort(BinaryBucket from, BinaryBucket to, int binPlace) {
        int a;
        if (binPlace == 0) {
            a = from.getMid();
        } else {
            a = to.getMid();
        }


        for (int i = 0; i <= a - 1; i++) {
            if (key(from.getBucket()[i], binPlace) == 0) {
                to.insertLeft(from.getBucket()[i]);
            } else {
                to.insertRight(from.getBucket()[i]);
            }

        }
        for (int i = from.getBucket().length - 1; i >= a; i--) {
            if (key(from.getBucket()[i], binPlace) == 0) {
                to.insertLeft(from.getBucket()[i]);
            } else {
                to.insertRight(from.getBucket()[i]);
            }


        }
        for (int i = 0; i < to.getBucket().length; i++) {
            from.getBucket()[i] = to.getBucket()[i];
        }

        //from.setBucket(to.getBucket());
        //from.setCount(0);

    }


    public static void lastSort(BinaryBucket from, int[] to) {
        int[] newArray = Arrays.copyOf(from.getBucket(), from.getBucket().length);
        to = new int[from.getBucket().length];
        int count = 0;//negative index count

        //z.b Array:- {1,2,3,-1,-2,-3}
        //negative array {-3,-2,-1} kopiert
        for (int i = newArray.length - 1; i > 0; i--) {
            if (newArray[i] < 0) {
                //  array1 = Arrays.copyOf(array1, array1.length + 1);
                to[count] = newArray[i];
                count++;
            }

        }
        //positive array {-3,-2,-1,1,2,3} kopiert
        for (int i = 0; i < newArray.length; i++) {
            if (newArray[i] > 0) {
                //array2 = Arrays.copyOf(array2, array2.length + 1);
                to[count] = newArray[i];
                count++;
            }

        }


    }

    public static void sort(int[] elements, Result result) {

        BinaryBucket from = new BinaryBucket(elements.length);
        from.setBucket(elements);
        BinaryBucket to = new BinaryBucket(elements.length);
        for (int i = 0; i < 32; i++) {
            kSort(from, to, i);
            to.logArray(result);
        }
    }

    public static void main(String[] args) {
//        int[] test = new int[10_000_000];
//        Random random = new Random();
//        for (int i = 0; i < test.length; i++) {
//            test[i] = random.nextInt(Integer.MAX_VALUE);
//        }
//        int[] testTwo = Arrays.copyOf(test, test.length);
//
//        long start = System.nanoTime();
//        sort(test, ignored -> {
//        });
//        long binaryTime = System.nanoTime() - start;
//
//        start = System.nanoTime();
//        RadixSort.sort(testTwo, ignored -> {
//        });
//        long decimalTime = System.nanoTime() - start;
//
//        System.out.println("Korrekt sortiert:" + Arrays.equals(test, testTwo));
//        System.out.println("Binary: " + binaryTime / 1_000_000);
//        System.out.println("Decimal: " + decimalTime / 1_000_000);


        int[] newArray = {1, 2, 3, -1, -2, -3};
        int[] to = new int[newArray.length];
//     int[] array1 =new int[0] ;//negative element
//     int[] array2 =new int[0];//positive element
        int count = 0;//negative index count

        //z.b Array:- {1,2,3,-1,-2,-3}
        //negative array {-3,-2,-1} kopiert
        for (int i = newArray.length - 1; i > 0; i--) {
            if (newArray[i] < 0) {
                //  array1 = Arrays.copyOf(array1, array1.length + 1);
                to[count] = newArray[i];
                count++;
            }

        }
        //positive array {1,2,3} kopiert
        for (int i = 0; i < newArray.length; i++) {
            if (newArray[i] > 0) {
                //array2 = Arrays.copyOf(array2, array2.length + 1);
                to[count] = newArray[i];
                count++;
            }

        }
        for (int i = 0; i < to.length; i++) {
            System.out.println(i + " " + to[i]);
        }

    }
}

