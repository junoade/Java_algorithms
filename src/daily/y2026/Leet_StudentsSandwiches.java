package daily.y2026;

import java.util.*;

public class Leet_StudentsSandwiches {

    /**
     * <a href="https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/description/?envType=problem-list-v2&envId=dsa-sequence-valley-queue">
     *     Number of Students Unable to Eat Lunch
     * </a>
     * @param students
     * @param sandwiches
     * @return
     */
    public int countStudents(int[] students, int[] sandwiches) {
        Queue<Integer> queue = new ArrayDeque<>();

        // init, 학생이 누구인진 중요하지 않고 위치만 중요함.
        for(int s : students) {
            queue.offer(s);
        }

        for(int i = 0; i < sandwiches.length; i++) {
            int currentType = sandwiches[i];
            int passCount = 0;

            while(!queue.isEmpty()) {
                if(passCount >= queue.size()) {
                    // 여기서 바깥 for 문도 break 해줘야함. 안돌아야함. (돌면 queue에 있는 학생들이 패스한 샌드위치는 버린다는 규칙이 됨.)
                    return queue.size();
                }
                int preferredType = queue.poll();
                if(currentType == preferredType) {
                    break;
                }
                queue.offer(preferredType);
                passCount++;
            }
        }

        return queue.size();
    }

    public static void main(String[] args) {
        Leet_StudentsSandwiches obj = new Leet_StudentsSandwiches();
        int answer = obj.countStudents(
                new int[]{1,1,1,0,0,1},
                new int[]{1,0,0,0,1,1}
        );

        System.out.println(answer);
    }
}
