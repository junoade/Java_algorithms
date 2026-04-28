package daily.y2026;

/**
 * https://leetcode.com/problems/merge-strings-alternately/?envType=study-plan-v2&envId=leetcode-75
 */
public class LeetMergeStrings_1768 {

    public String mergeAlternately(String word1, String word2) {

        StringBuilder sb = new StringBuilder();
        char[] wordArr1 = word1.toCharArray();
        char[] wordArr2 = word2.toCharArray();
        int N = wordArr1.length, M = wordArr2.length;

        int minLen = Math.min(N, M);
        for (int i = 0; i < minLen; i++) {
            sb.append(wordArr1[i]).append(wordArr2[i]);
        }

        String answer = sb.toString();
        if(N > M) answer += word1.substring(M);
        else if(N < M) answer += word2.substring(N);

        return answer;
    }
}
