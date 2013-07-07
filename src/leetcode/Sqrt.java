package leetcode;


/**
 * Use binary search to calculate the sqrt of the integer.
 * In order to handle overflow case, we need to use long data type instead of int
 */
public class Sqrt {

    public int sqrt(int x) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (x < 0) return -1;

        return (int)sqrt(0, x, x);

    }

    private long sqrt(long start, long end, long target) {
        if (end == start) {
            if (end * end == target || (end * end < target && (end + 1) * (end + 1) > target))
                return end;
            else
                return -1; // should never get here
        }

        long mid = start + (end - start) / 2;

        if (mid * mid == target || (mid * mid < target && (mid + 1) * (mid + 1) > target)) {
            return mid;
        } else if (mid * mid > target) {
            return sqrt(start, mid, target);
        } else
            return sqrt(mid + 1, end, target);
    }
}
