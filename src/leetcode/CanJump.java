package leetcode;

/**
 * Created with IntelliJ IDEA.
 * User: zhuang
 * Date: 5/3/13
 * Time: 3:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class CanJump {

    /**
     *
     Given an array of non-negative integers, you are initially positioned at the first index of the array.

     Each element in the array represents your maximum jump length at that position.

     Determine if you are able to reach the last index.

     For example:
     A = [2,3,1,1,4], return true.

     A = [3,2,1,0,4], return false.
     *
     *
     *
     * Summary: At first, I was thinking to use DFS which is not efficient.
     *          The time complexity for DFS is V + E, which in our case, we have many edges to explore.
     *          Turns out DFS in this case is not an efficient algorithm
     *
     *
     *          Then, I design a greedy algorithm, figure out the greedy choice lies in the fact that each time, we
     *          should pick the farthest one where all the available route that can reach the farthest.
     */
    public boolean canJump(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
//        if (A == null) return false;
//        if (dfs(A, 0)) return true;
//        else
//            return false;
        if (A == null) return false;

        if (A.length < 2) return true;

        int lastIndex = A.length - 1;
        int currentIndex = 0;

        while (A[currentIndex] > 0) {
            int bestIndex = currentIndex + 1;
            int farthestIndex = bestIndex + A[bestIndex];
            int next, maxForNext;

            /* Making a greedy choice here, we need to find the farthest index we could go and make the decision */
            for (int j = 1; j <= A[currentIndex]; j++) {
                next = currentIndex + j;
                maxForNext = next + A[next];
                if (maxForNext >= lastIndex) return true;
                else {
                    if (maxForNext > farthestIndex) {
                        bestIndex = next;
                        farthestIndex = maxForNext;
                    }
                }

            }
            currentIndex = bestIndex;
        }
        return false;

    }

//    /**
//     * This version is a compact DFS version
//     * @param A
//     * @return
//     */
//    public boolean canJump(int[] A) {
//        boolean[] reachable = new boolean[A.length];
//
//        reachable[0] = true;
//        for (int i = 0; i < A.length; i++) {
//
//            if (reachable[i]) {
//                for (int j = 0; j <= A[i]; j++) {
//                    reachable[i + j] = true;
//                    if (i + j == A.length - 1) return true;
//                }
//            }
//        }
//        return false;
//    }

    private boolean dfs(int[] A, int start) {
        if (A[start] + start < A.length - 1) {

            for (int i = A[start]; i >= 1; i--) {  // From large to small
                int newStart = start + i;
                if (dfs(A, newStart))
                    return true;
            }
            return false;
        } else
            return true;
    }

    public static void main(String[] args) {
        int[] cj = {3,2,1,0,4};

        int[] failTest = {2,0,6,9,8,4,5,0,8,9,1,2,9,6,8,8,0,6,3,1,2,2,1,2,6,5,3,1,2,2,6,4,2,4,3,0,0,0,3,8,2,4,0,1,2,0,1,4,6,5,8,0,7,9,3,4,6,6,5,8,9,3,4,3,7,0,4,9,0,9,8,4,3,0,7,7,1,9,1,9,4,9,0,1,9,5,7,7,1,5,8,2,8,2,6,8,2,2,7,5,1,7,9,6};
        CanJump canJump = new CanJump();
        System.out.println(canJump.canJump(cj));
        System.out.println(canJump.canJump(failTest));
    }

}
