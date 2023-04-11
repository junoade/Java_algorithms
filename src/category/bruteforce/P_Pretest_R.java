package category.bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class P_Pretest_R {

    final int[] A_GUESS = {1, 2, 3, 4, 5};
    final int[] B_GUESS = {2, 1, 2, 3, 2, 4, 2, 5};
    final int[] C_GUESS = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

    public int[] solution(int[] answers) {
        int aScore = 0, bScore = 0, cScore = 0;
        for(int i = 0; i < answers.length; i++) {
            int answer = answers[i];
            if(A_GUESS[i % A_GUESS.length] == answer) {
                aScore++;
            }

            if(B_GUESS[i % B_GUESS.length] == answer) {
                bScore++;
            }

            if(C_GUESS[i % C_GUESS.length] == answer) {
                cScore++;
            }
        }

        int[] answer = new int[]{aScore, bScore, cScore};
        return getMaxArrByIntStream(answer);
    }

    private int[] getMaxArr(int[] answer) {
        // ArrayList<Integer> maxValue = (ArrayList)Arrays.stream(answer).boxed().collect(Collectors.toList());
        int maxValue = Math.max(answer[0], Math.max(answer[1], answer[2]));
        List<Integer> maxIdxList = new ArrayList<>();

        for(int i = 0; i < answer.length; i++) {
            if(answer[i] >= maxValue) {
                maxValue = answer[i];
                maxIdxList.add(i+1); // 1번 부터 시작해서
            }
        }
        Collections.sort(maxIdxList);
        return maxIdxList.stream().mapToInt(Integer::intValue).toArray();
    }

    private int[] getMaxArrByIntStream(int[] answer) {
        final int maxValue = Math.max(answer[0], Math.max(answer[1], answer[2]));
        return IntStream.range(0, answer.length)
                .filter(i -> answer[i] == maxValue)
                .map(i -> i + 1)
                .toArray();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] answer = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        P_Pretest_R p = new P_Pretest_R();
        System.out.println(Arrays.toString(p.solution(answer)));

    }
}
