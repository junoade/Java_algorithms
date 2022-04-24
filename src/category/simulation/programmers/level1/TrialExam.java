package category.simulation.programmers.level1;

/**
 * 프로그래머스, 모의고사
 * 완전 탐색
 * https://programmers.co.kr/learn/courses/30/lessons/42840
 * <p>
 * 핵심 : 주어진 조건에 따라 자료를 저장하고, max 찾아서 반환하기
 *
 * @date 2022-04-24
 */

import java.io.*;
import java.util.*;

public class TrialExam {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(", ");
        int[] answers = new int[inputs.length];
        for (int i = 0; i < answers.length; i++) {
            answers[i] = Integer.parseInt(inputs[i]);
        }


    }

    public static int[] solution(int[] answers) {
        int L = answers.length;
        int[] ptrn_A = {1, 2, 3, 4, 5};
        int[] ptrn_B = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] ptrn_C = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        /*최초 구현시, A, B, C에 대한 배열을 다 만들고 할당해줬는데, O(N) 순회 하면서, answer와 비교하고 각각의 scores를 저장하면 더 간결하다.*/
        int[] scores = {0,0,0};
        for (int i = 0; i < L; i++) {
            int A = ptrn_A[i % ptrn_A.length];
            int B = ptrn_B[i % ptrn_B.length];
            int C = ptrn_C[i % ptrn_C.length];

            /* answer와 각각 확인 */
            if(answers[i] == A)
                scores[0]++;
            if(answers[i] == B)
                scores[1]++;
            if(answers[i] == C)
                scores[2]++;
        }

        /* MAX 찾고, 반환 준비 */
        int max = Math.max(scores[0], Math.max(scores[1], scores[2]));
        ArrayList<Integer> result = new ArrayList<>();
        if(max == scores[0])
            result.add(1);
        if(max == scores[1])
            result.add(2);
        if(max == scores[2])
            result.add(3);

        /* java 8 - stream, 기존의 Integer 형 element를 int 형으로 mapping하고, int[]로 반환 */
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
