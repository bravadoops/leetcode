// 1.prefix sum array
// 1.1 infinite size array(not recommend)
// 1.2 loop array: use mod() (This solution)
// 1.3 LinkedList 
class MovingAverage {
    private int[] pre;
    private int id;
    private int s;

    public MovingAverage(int size) {
        pre = new int[size + 1];
        id = 0;
        s = size;
    }
    private int mod(int n) {
        // size of pre is size + 1
        return n % (s + 1);
    }
    public double next(int val) {
        id++;
        pre[mod(id)] = pre[mod(id - 1)] + val;
        if (id >= s) {
            return (pre[mod(id)] - pre[mod(id - s)]) * 1.0 / s;
        } else 
            return pre[mod(id)] * 1.0 / id;
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */