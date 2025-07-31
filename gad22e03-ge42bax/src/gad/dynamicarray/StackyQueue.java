package gad.dynamicarray;

public class StackyQueue implements Queue {
    private final DynamicStack first;
    private final DynamicStack second;

    public StackyQueue(int growthFactor, int maxOverhead) {
        Result ignoringResult = (ignored) -> {
        };
        first = new DynamicStack(growthFactor, maxOverhead, ignoringResult);
        second = new DynamicStack(growthFactor, maxOverhead, ignoringResult);
    }

    @Override
    public int size() {
        return first.size() + second.size();
    }

    @Override
    public void pushBack(int value) {
        first.pushBack(value);
    }

    @Override
    public int popFront() {
        if (second.size() == 0) {
            while (!first.isEmpty()) {
                second.pushBack(first.popBack());
            }
        }
        return second.popBack();
    }

    @Override
    public String toString() {
        return first + ", " + second;
    }
}