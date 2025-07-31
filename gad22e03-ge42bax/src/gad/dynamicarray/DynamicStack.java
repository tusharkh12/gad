package gad.dynamicarray;

public class DynamicStack implements Stack {
    private DynamicArray array;
    private Result result;
    int top;
    int number;
//    private int growthFactor;
//    private int maxOverhead;


    public DynamicStack(int growthFactor, int maxOverhead, Result result) {
       array=new DynamicArray(growthFactor,maxOverhead);
       this.result=result;
       this.top=-1;
       this.number=0;


    }

    @Override
    public int size() {
        //int numberOfElements=0;
        return top+1;
    }

    @Override
    public void pushBack(int value) {
        number++;
        if (top == -1) {

            array.reportUsage(Interval.EmptyInterval.getEmptyInterval(), number);
            top++;
            array.set(top, value);
            // array.reportUsage(new Interval.NonEmptyInterval(0,top),size());
            array.reportArray(result);
        } else {


            array.reportUsage(new Interval.NonEmptyInterval(0, top), number);
            top++;
            //array.reportArray(result);
            array.set(top, value);
           // array.reportUsage(new Interval.NonEmptyInterval(0, top), size());
            array.reportArray(result);
        }
    }
    @Override
    public int popBack() {

        int get;
        if(top!=-1){
            get= array.get(top);
            --top;
            --number;
            if(top==-1) {
                array.reportUsage(Interval.EmptyInterval.getEmptyInterval(), 0);
                array.reportArray(result);
                return get;
            }
            array.reportUsage(new Interval.NonEmptyInterval(0,top),number) ;
            array.reportArray(result);
            return get ;
        }else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return array + ", length: " + size();
    }

//    public static void main(String[] args) {
//        DynamicStack d=new DynamicStack(3, 4, new Result() {
//            @Override
//            public void logArray(int[] array) {
//
//            }
//        });
//       d.pushBack(1);
//
//    }
}
