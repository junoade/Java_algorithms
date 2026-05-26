package daily.y2026;

public class Leet_MoveZeros {
    /**
     * <a href="https://leetcode.com/problems/move-zeroes/description/?envType=study-plan-v2&envId=leetcode-75">
     *     리트코드 283. Moves Zeros</a>
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        final int N = nums.length;

        for(int i = 0; i < N; i++) {

            if(nums[i] != 0 ) {
                continue;
            }

            int j = i + 1;
            while(j < N && nums[j] == 0) j++;
            if(j == N) return;


            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
