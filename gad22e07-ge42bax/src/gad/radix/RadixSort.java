package gad.radix;

import java.util.ArrayList;
import java.util.List;

public final class RadixSort {
    private static final int DIGITS = 10;

    private RadixSort() {

    }

    public static int key(int element, int decPlace) {
        int i=0;
        int n=1;
        int number =element;
        while(i<decPlace){
            n*=10;
            i++;
        }
        number=number/n;

        number = number % 10;


        return number;
    }

    public static int getMaxDecimalPlaces(int[] elements) {
       int temp;

       if(elements.length==0){
           return 0;
       }
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] < 0){
                elements[i] = -elements[i];
            }
        }
        for (int i = 0; i <elements.length ; i++) {
            for (int j = i+1; j <elements.length ; j++) {
                if(elements[j]>elements[i]){
                    temp=elements[i];
                    elements[i]=elements[j];
                    elements[j]=temp;
                }

            }

        }
        if(elements[0]==0){
            return 0;
        }
        String num=String.valueOf(elements[0]);

        return Integer.parseInt(String.valueOf(num.length()));
    }

    public static void concatenate(List<Integer>[] buckets, int[] elements) {
        int k = 0;
        for (int i = 0; i < buckets.length; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                elements[k++] = buckets[i].get(j);
            }
        }
    }

    public static void kSort(int[] elements, int decPlace) {
        @SuppressWarnings("unchecked")
        List<Integer>[] buckets = new List[DIGITS];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>(elements.length / DIGITS);
        }
        for (int i = 0; i < elements.length; i++) {
            buckets[key(elements[i], decPlace)].add(elements[i]);
        }
        concatenate(buckets, elements);
    }

    public static void sort(int[] elements, Result result) {
        int decPlaces = getMaxDecimalPlaces(elements);
        for (int decPlace = 0; decPlace < decPlaces; decPlace++) {
            kSort(elements, decPlace);
            result.logArray(elements);
        }
    }

    public static void main(String[] args) {

         int[] a =new  int[]{90,11,22};
       // System.out.println(RadixSort.kSort(a,2));
        //System.out.println(RadixSort.kSort(a,));
    }

}

