package category.string;

public class P_StringCompression {
    public int solution(String s) {
        final int L = s.length();
        int minLength = s.length();

        // 문자 압축의 길이를 1부터 L-1 까지 반복 수행하며 최소 길이를 찾는다 O(N*N)
        for(int unit = 1; unit < L; unit++) {
            int count = 1;
            StringBuilder sb = new StringBuilder();
            String priorStr = s.substring(0, unit);

            for(int i = unit; i < L; i += unit) {
                int to = Math.min(i + unit, L);
                String nextStr = s.substring(i, to);

                if(priorStr.equals(nextStr)) {
                    count++;
                } else {
                    appendLastStr(sb, count, priorStr);
                    count = 1;
                }

                priorStr = nextStr;
            }
            appendLastStr(sb, count, priorStr); // 제일 마지막 문자 집합을 추가해줌(앞 문자열들만 추가하고 있었으므로
            minLength = Math.min(sb.length(), minLength);
        }

        return minLength;
    }

    void appendLastStr(StringBuilder sb, int count, String s) {
        if(count > 1) {
            sb.append(count);
        }
        sb.append(s);
    }

    public static void main(String[] args) {
        P_StringCompression p = new P_StringCompression();
        String test01 = "abcabcabcabcdededededede";
        System.out.println(p.solution(test01));
    }
}
