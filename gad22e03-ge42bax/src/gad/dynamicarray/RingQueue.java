package gad.dynamicarray;

public class RingQueue implements Queue {
    private DynamicArray array;
    private Result result;
    int tail;
    int head;
    int number;
    int pop;


    public RingQueue(int growthFactor, int maxOverhead, Result result) {
        array=new DynamicArray(growthFactor,maxOverhead);
        this.result=result;
        tail=-1;
        head=0;
        number=0;
        pop=-1;


    }

    @Override
    public int size() {
        return number;
    }

//    @Override
//    public void pushBack(int value) {
//        number++;
//        if (tail == -1 && head == 0) {
//            array.reportUsage(Interval.EmptyInterval.getEmptyInterval(), number);
//            tail++;
//            head++;
//            array.set(0, value);
//            array.reportArray(result);
//        } else {
//
//            if ((head > 0 && tail < array.getLength()) ||
//                    (head == 0 && tail == 0)) {
//                array.reportUsage(new Interval.NonEmptyInterval(0, tail), number);
//                tail++;
//                head++;
//                array.set(tail, value);
//                array.reportArray(result);
//
//            } else if (head == 0 && tail > 0) {
//
//                array.reportUsage(new Interval.NonEmptyInterval(headNum+1, 0), number);
//                array.set(0, value);
//                array.reportArray(result);
//                head++;
//                tail++;
//            } else if (number == array.getLength()) {
//                array.reportUsage(new Interval.NonEmptyInterval(headNum+1, 0), number);
//                tail++;
//                array.set(tail, value);
//                array.reportArray(result);
//                head++;
//
//
//
//            }
//        }
//        headNum=0;
//        tailNum=tail;
//    }

    @Override
    public void pushBack(int value) {
        number++;
        if (tail == -1 && head == 0) {
            array.reportUsage(Interval.EmptyInterval.getEmptyInterval(), number);
            tail++;
            array.set(0, value);
            array.reportArray(result);
        }

            else if (number > array.getLength() && head!=0)  {
                array.reportUsage(new Interval.NonEmptyInterval(head, head-1), number);
                tail = tail + head;
                array.set(tail, value);
                array.reportArray(result);
                head = 0;
                pop = -1;

            } else {
                if(tail+2== array.getLength() && pop!=-1){
                    array.reportUsage(new Interval.NonEmptyInterval(head-pop-1, tail), number);
                    tail++;
                    array.set(tail, value);
                    array.reportArray(result);

                    //number--;
                }
              else if (pop==-1 ||
                tail+1<array.getLength()) {
                    array.reportUsage(new Interval.NonEmptyInterval(head, tail), number);
                    tail++;
                    array.set(tail, value);
                    array.reportArray(result);
                    //head--;
                    //pop = 0;
                } else {
                    array.reportUsage(new Interval.NonEmptyInterval(head,tail),number);
                    array.set(pop,value);
                    array.reportArray(result);
                    //head--;
                    //pop--;

                    }

                }


            }




    @Override

    public int popFront() {
        int get;


        if(head>tail){
            head=tail-pop;
            get=array.get(head);
            if(tail<0) {
                array.reportUsage(Interval.EmptyInterval.getEmptyInterval(), 0);
                array.reportArray(result);
                return get;
            }
            head=0;

            head++;
            array.reportUsage(new Interval.NonEmptyInterval(0,tail),number) ;
            array.reportArray(result);

            pop++;
            return get ;
        }
        number--;
        //int get;

        get= array.get(head);

        if((array.getLength()>(array.getMaxOverhead()*number))  && tail!=-1){

          if(tail<0) {
              array.reportUsage(Interval.EmptyInterval.getEmptyInterval(), 0);
              array.reportArray(result);
              return get;
          }
          head++;
          array.reportUsage(new Interval.NonEmptyInterval(head,tail),number);
          array.reportArray(result);
          tail=tail-head;
          head=0;
          pop=-1;

          return get;

      }
       else if((array.getLength()<=(array.getMaxOverhead()*number))  && tail!=-1){


           if(tail<0) {
                array.reportUsage(Interval.EmptyInterval.getEmptyInterval(), 0);
                array.reportArray(result);
                return get;
            }
            head++;
            array.reportUsage(new Interval.NonEmptyInterval(head,tail),number) ;
            array.reportArray(result);

            pop++;
            return get ;
        }else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return array + ", size: " + size();
    }
}