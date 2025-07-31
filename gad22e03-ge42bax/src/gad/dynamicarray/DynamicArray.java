package gad.dynamicarray;

import java.util.Arrays;

public class DynamicArray {
    private int[] elements;
    private final int growthFactor;
    private final int maxOverhead;

    public DynamicArray(int growthFactor, int maxOverhead) {
        this.growthFactor = growthFactor;
        this.maxOverhead = maxOverhead;
        this.elements = new int[0];
        if(growthFactor<1||maxOverhead<2||growthFactor>=maxOverhead){
            throw new IllegalArgumentException();
        }
    }

    public int getLength() {
        return elements.length;
    }

    public Interval reportUsage(Interval usage, int minSize) {
        int[] newArray = new int[0];
          if (minSize == 0) {

            ////Null Array
            elements = new int[0];
            return Interval.EmptyInterval.getEmptyInterval();
        }
        if (usage.isEmpty()) {
            if (minSize > elements.length) {
                newArray = new int[growthFactor * (minSize)];

                for (int i = 0; i < elements.length; i++) {
                    newArray[i] = elements[i];
                }
                elements = newArray;
                return Interval.EmptyInterval.getEmptyInterval();

            } else if (elements.length > ((maxOverhead) * minSize)) {


                ////Small Array
                newArray = new int[growthFactor * (minSize)];

//                for (int i = 0; i < elements.length; i++) {
//                    newArray[i] = elements[i];
//                }
                elements = newArray;
                return Interval.EmptyInterval.getEmptyInterval();


            }
//            else if (minSize == 0) {
//
//                ////Null Array
//                elements = new int[0];
//                return Interval.EmptyInterval.getEmptyInterval();
//            }
            else {
                /////No change
                return usage;
            }
        }
        int leftElement = usage.getFrom();
        int rightElement = usage.getTo();

        int[] intervalArray = new int[0];
        int[] rightArray = new int[0];
        int[] leftArray = new int[0];
        int a = 0;
        int b = 0;
        int c = 0;

///1st Condition
        if (rightElement >= leftElement && !usage.isEmpty()) {
            if (minSize > (elements.length)) {
                /////Big
                newArray = new int[growthFactor * (minSize)];
                //elements=new int[growthFactor * (minSize)];
                intervalArray = new int[rightElement - leftElement + 1];
                for (int i = leftElement; i <= rightElement; i++) {

                    intervalArray[a] = elements[i];
                    a++;
                }
                //i <= (rightElement - leftElement)
                for (int i = 0; i < intervalArray.length; i++) {
                    newArray[i] = intervalArray[i];
                }
                elements = newArray;

                //(0,intervalArray.length-1)
                return new Interval.NonEmptyInterval(0, rightElement - leftElement);
            }

//            else if (minSize == 0) {
//                ////Null Array
//                elements = new int[0];
//                return Interval.EmptyInterval.getEmptyInterval();
//            }

               else if (elements.length > ((maxOverhead) * minSize)) {


                    ////Small Array
                    newArray = new int[growthFactor * (minSize)];
                    intervalArray = new int[rightElement - leftElement + 1];
                    for (int i = leftElement; i <= rightElement; i++) {
                        intervalArray[a] = elements[i];
                        a++;
                    }
                    for (int i = 0; i <= (rightElement - leftElement); i++) {
                        newArray[i] = intervalArray[i];
                    }
                    elements = newArray;
                    return new Interval.NonEmptyInterval(0, rightElement - leftElement);

                }
//            else if (minSize == 0) {
//
//                ////Null Array
//                elements = new int[0];
//                return Interval.EmptyInterval.getEmptyInterval();
//            }
            else {

                    /////No change

                    return usage;
                }




// 2nd Condition
        }


        else if (leftElement > rightElement && !usage.isEmpty()) {


            if (minSize > (elements.length)) {

///A
                /////Big
                newArray = new int[growthFactor * (minSize)];
                intervalArray = new int[(elements.length - leftElement) + rightElement + 1];
                leftArray = new int[elements.length - leftElement];
                rightArray = new int[rightElement + 1];
                //left array
                for (int i = leftElement; i < elements.length; i++) {
                    leftArray[b] = elements[i];
                    b++;

                }

                //right array
                for (int i = 0; i <= rightElement; i++) {
                    rightArray[c] = elements[i];
                    c++;
                }
                // left interval array
                for (int i = 0; i < leftArray.length; i++) {
                    intervalArray[i] = leftArray[i];
                }

                // left+right= FINAL interval array
                //a = leftArray.length;
                for (int i = leftArray.length; i < intervalArray.length; i++) {

                    intervalArray[i] = rightArray[a];
                    a++;

                }
                //copying in new array (done)

                for (int i = 0; i < intervalArray.length; i++) {
                    newArray[i] = intervalArray[i];
                }
                elements = newArray;

                return new Interval.NonEmptyInterval(0, intervalArray.length - 1);


            } else if (elements.length > ((maxOverhead) * minSize)) {


                // Small Array
                newArray = new int[growthFactor * (minSize)];
                intervalArray = new int[(elements.length - leftElement) + rightElement + 1];
                leftArray = new int[elements.length - leftElement];
                rightArray = new int[rightElement + 1];
                //left array
                for (int i = leftElement; i < elements.length; i++) {
                    leftArray[b] = elements[i];
                    b++;

                }

                //right array
                for (int i = 0; i <= rightElement; i++) {
                    rightArray[c] = elements[i];
                    c++;
                }
                // left interval array
                for (int i = 0; i < leftArray.length; i++) {
                    intervalArray[i] = leftArray[i];
                }

                // left+right= FINAL interval array
                a = leftArray.length;
                for (int i = 0; i < rightArray.length; i++) {

                    intervalArray[a] = rightArray[i];
                    a++;

                }
                //copying in new array (done)

                for (int i = 0; i < intervalArray.length; i++) {
                    newArray[i] = intervalArray[i];
                }
                elements = newArray;

                return new Interval.NonEmptyInterval(0, intervalArray.length - 1);


            }
//            else if (minSize == 0) {
//
//                ////Null Array
//                elements = new int[0];
//                return Interval.EmptyInterval.getEmptyInterval();
//            }

            else {

                /////No change

                return usage;
            }
        }

       ///3rd Condition

        return Interval.EmptyInterval.getEmptyInterval();



    }


    public int get(int index) {

        return elements[index];
    }
    public int getMaxOverhead(){
        return this.maxOverhead;
    }

    public void set(int index, int value) {
        elements[index]=value;
    }

    public void reportArray(Result result) {
        result.logArray(elements);
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

    public static void main(String[] args) {
        DynamicArray array = new DynamicArray(3, 4);
      System.out.println(array.reportUsage(Interval.EmptyInterval.getEmptyInterval(), 1));
//        System.out.println(array);
        array.set(2, 1);
        array.set(0,3);
        System.out.println(array);
        System.out.println(array.reportUsage(new Interval.NonEmptyInterval(1,2), 4));
        System.out.println(array);

        System.out.println((array.reportUsage(new Interval.NonEmptyInterval(1, 1), 1)));
        System.out.println(array);
        System.out.println(array.reportUsage(new Interval.NonEmptyInterval(2,0),4));
        System.out.println(array);


    }
}