package daily.y2026;

/**
 * https://leetcode.com/problems/merge-strings-alternately/?envType=study-plan-v2&envId=leetcode-75
 */
public class Leet_MergeStrings_1768 {

    public String mergeAlternately(String word1, String word2) {

        StringBuilder sb = new StringBuilder();
        char[] wordArr1 = word1.toCharArray();
        char[] wordArr2 = word2.toCharArray();
        int N = wordArr1.length, M = wordArr2.length;

        for (int i = 0; i < N; i++) {
            sb.append(wordArr1[i]);
            if (i < M) {
                sb.append(wordArr2[i]);
            }
        }

        String answer = sb.toString();
        if (N < M) {
            answer += word2.substring(N);
        }

        return answer;
    }
}
