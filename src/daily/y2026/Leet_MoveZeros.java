package daily.y2026;

public class Leet_MoveZeros {
    /**
     * <a href="https://leetcode.com/problems/move-zeroes/description/?envType=study-plan-v2&envId=leetcode-75">
     *     리트코드 283. Moves Zeros</a>
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        final int N = nums.length;
        // write 포인터, read 포인터 를 둠.
        // read 포인터 는 O(N) 탐색하면서 write 포인터 위치에 저장
        int w = 0;
        for(int r = 0; r < N; r++) {
            if(nums[r] != 0) {
                nums[w] = nums[r];
                w++;
            }
        }

        for(int i = w; i < N; i++) {
            nums[i] = 0;
        }
    }
}
