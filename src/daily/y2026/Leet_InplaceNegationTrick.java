package daily.y2026;

import java.util.ArrayList;
import java.util.List;

/**
 * Find All Numbers Disappeared in an Array
 * <a href="https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/description/?envType=problem-list-v2&envId=dsa-linear-shoal-array-ii"/>
 */
public class Leet_InplaceNegationTrick {
    public List<Integer> findDisappearNumbers(int[] nums) {
        final int L = nums.length;

        for (int i = 0; i < L; i++) {
            int idx = Math.abs(nums[i]) - 1;
            if (nums[idx] > 0) {
                nums[idx] = -nums[idx];
            }
        }

        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < L; i++) {
            if(nums[i] > 0) {
                answer.add(i + 1);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Leet_InplaceNegationTrick leet = new Leet_InplaceNegationTrick();
        System.out.println(leet.findDisappearNumbers(new int[]{4,3,2,7,8,2,3,1}));
    }

}
