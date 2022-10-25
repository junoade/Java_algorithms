package category.string;

/**
 * 프로그래머스
 * https://school.programmers.co.kr/learn/courses/30/lessons/60057
 */
public class StringCompression {

    public void appendLastStr(StringBuffer sb, int count, String beforeStr) {
        if (count > 1)
            sb.append(count);
        sb.append(beforeStr);
    }

    public int solution(String s) {
        int min_length = 1000;
        for (int unit = 1; unit <= s.length(); unit++) {
            StringBuffer sb = new StringBuffer();
            String beforeStr = s.substring(0, unit);
            int count = 1;

            /* unit으로 짜르고 남은 길이에 대해서도 추가 해줘야 함 */
            for (int i = unit; i < s.length(); i += unit) {
                int rangeInside = i + unit <= s.length() ? i + unit : s.length();
                String currentStr = s.substring(i, rangeInside);

                if (beforeStr.equals(currentStr)) {
                    count++;
                } else {
                    appendLastStr(sb, count, beforeStr);
                    count = 1;
                }
                beforeStr = currentStr;
            }
            appendLastStr(sb, count, beforeStr);

            if (sb.length() < min_length)
                min_length = sb.length();
        }

        return min_length;
    }

    public static void main(String[] args) {
        StringCompression strComp = new StringCompression();
        /*String[] arr = {"aabbaccc", "ababcdcdababcdcd", "abcabcdede", "abcabcabcabcdededededede"};
        for (String str : arr) {
            strComp.solution(str);
        }*/
        strComp.solution("abcabcdede");
    }
}
