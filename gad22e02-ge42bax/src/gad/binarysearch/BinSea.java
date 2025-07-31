package gad.binarysearch;


public final class BinSea {

    private BinSea() {
    }

    public static int search(int[] sortedData, int value, Result result){

        int a=0;
        int b=sortedData.length-1;
        int average=(sortedData.length-1)/2;
       // boolean inside=false;
        while(true) {
            if (a == b ) {
                average=a;
                //average = a;
                result.addStep(average);
                return average;
            }
            if (sortedData[average] == value) {
                result.addStep(average);

                return average;
            }

            else if (sortedData[average] > value) {
                b = average-1;
                if(b<a){
                    b=b+1;
                }


              // a=average+1;
                result.addStep(average);
                average = (a + b) / 2;
            } else if (sortedData[average] < value) {
                a = average+1;
               // b=b-1;
                result.addStep(average);
                average = (a + b) / 2;
            }

//            for (int i = 0; i <= sortedData.length-1 ; i++) {
//                if(sortedData[i]==value){
//                    inside= true;
//                break;}




//            }else if (a + 1 == b && sortedData[average] < value ) {
//
//               // average = b;
//               // result.addStep(average);
//                return average;
//            }
//            else if (a + 1 == b && sortedData[average] < value ) {
//                average = b;
//                result.addStep(average);
//                return average;
//            }
        }

    }

    public static int search(int[] sortedData, int value, boolean lowerBound, Result result) {
        // int s=search(sortedData,value,result);
       // lowerBound=false;

        int number;
        int sameNumber=0;


      int average=search(sortedData,value,result);

      if(lowerBound){
           if(sortedData[average]==value) {

               for (int i = average; i >=0 ; i--) {
                   if (sortedData[i] < value) {
                       return i+1;
                       //result.addStep(i);
                   }
               }      return 0;
           }

           else if(sortedData[sortedData.length-1]<value){
               return -1;
           }else if(sortedData[0]>value){
               return 0;
           }
           else if(sortedData[average]<value){
               for (int i = average;i< sortedData.length;i++){
                   if (sortedData[i]>=value){
                       return i;
                   }
               }
               return sortedData.length;

           }

           else{
             //  result.addStep(average+1);
              return  average;
        }}

        else {
            if(sortedData[average]==value) {

                for (int j = average; j < sortedData.length; j++) {
                    if (sortedData[j] > value) {
                        return j-1;
                    }//result.addStep(j);
                }
                return sortedData.length-1;

            }
            else if(sortedData[0]>value){
               // result.addStep(-1);
                return -1;
            }else if(sortedData[average]>value){
                for (int j = average; j >=0; j--) {
                    if (sortedData[j] <= value) {
                        return j;
                    }//result.addStep(j);
                }
                return 0;
            }
            else{
                return average;
            }
        }



    }

    public static Interval search(int[] sortedData, Interval valueRange, Result resultLower, Result resultHigher) {
        int lowerInterval=valueRange.getFrom();
        int upperInterval=valueRange.getTo();
       int lower=search(sortedData,lowerInterval,true,resultLower);
        int upper=search(sortedData,upperInterval,false,resultHigher);


        // Interval search=search(sortedData,valueRange,resultLower,resultHigher);

   return Interval.fromArrayIndices(lower,upper);
    }

    public static void main(String[] args) {
         int[] array = new int[] { 2, 7, 7, 42, 69, 1337, 2000, 9001 };
//        int[] arraym = new int[] {1,1,1,2,2,2,5,6,7,9,9,9,9,9};
//        System.out.println(search(arraym,9,false,new StudentResult()));

//
//
  //   System.out.println(search(array, 7, new StudentResult()));
//        System.out.println(search(array, 100, new StudentResult()));
//       int[] array1 =new int[]{1, 1, 1, 1, 1, 1, 1, 4};
//        System.out.println(search(array1,1,new StudentResult()));
//

//        System.out.println(search(array, 7, false, new StudentResult()));
//        System.out.println(search(array, 100, true, new StudentResult()));

//        System.out.println(search(array, new NonEmptyInterval(7, 1500), new StudentResult(), new StudentResult()));
//       System.out.println(search(array, new NonEmptyInterval(9002, 10000), new StudentResult(), new StudentResult()));
    }
}
