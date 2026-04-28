package daily.y2026;

public class LeetGCDofStrings_1071 {
    public String solution(String str1, String str2) {
        int N = Math.min(str1.length(), str2.length());

        String answer = "";
        for(int i = 1; i <= N; i++) {
            String t = str1.substring(0, i);

            // 부분 문자열로 나눌 수 있는 지 확인
            if(!dividable(str1, t) || !dividable(str2, t)) {
                continue;
            }

            // str1, str2 각각 prefix의 합으로만 이루어 져있는지 확인
            // 그러면 임시로 저장.
            if(hasAllCommon(str1, t) && hasAllCommon(str2, t)) {
                answer = t;
            }
        }
        return answer;
    }

    private boolean dividable(String s, String t) {
        return s.length() % t.length() == 0;
    }

    private boolean hasAllCommon(String s, String t) {
        int startIdx = 0;
        int next = t.length();

        boolean result = true;
        while(true) {
            if(startIdx + next > s.length()) {
                break;
            }

            String temp = s.substring(startIdx, startIdx + next);
            if(!temp.equals(t)) {
                result = false;
                break;
            }
            startIdx += next;

        }
        return result;
    }

    public static void main(String[] args) {
        LeetGCDofStrings_1071 solution = new LeetGCDofStrings_1071();
        String anwer = solution.solution("ABCABC", "ABC");
        System.out.println(anwer);
    }
}
