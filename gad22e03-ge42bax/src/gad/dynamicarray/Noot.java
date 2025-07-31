package gad.dynamicarray;

public class Noot implements Queue, Stack {
    private DynamicStack first;
    private DynamicStack second;
    private DynamicStack third;
    int count;

    public Noot(int growthFactor, int maxOverhead) {
        Result ignoringResult = (ignored) -> {
        };
        first = new DynamicStack(growthFactor, maxOverhead, ignoringResult);
        second = new DynamicStack(growthFactor, maxOverhead, ignoringResult);
        third = new DynamicStack(growthFactor, maxOverhead, ignoringResult);
        count=0;

    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void pushBack(int value) {
        count++;
        first.pushBack(value);
    }

    @Override
    public int popFront() {
        count--;
        if (second.size() == 0) {
            while (!first.isEmpty()) {
                second.pushBack(first.popBack());
            }
        }
        return second.popBack();

    }

    @Override
    public int popBack() {
        count--;
        return third.popBack();

    }

    @Override
    public String toString() {
        return first + ", " + second + ", " + third;
    }
}