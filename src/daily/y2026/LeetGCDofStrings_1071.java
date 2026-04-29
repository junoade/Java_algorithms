package daily.y2026;

public class LeetGCDofStrings_1071 {
    public String solution(String str1, String str2) {
        String x = str1 + str2;
        String y = str2 + str1;

        String answer = "";
        if(!x.equals(y)) {
            return answer;
        }

        int gcd = gcd(str1.length(), str2.length());
        answer = str1.substring(0, gcd);

        return answer;
    }

    private int gcd(int a, int b) {
        // a % b == 0 이 true일때 b가 최대공약수
        // a % b = r 이면, gcd(b, r) 는 gcd(a, b)의 최대공약수와 같다. (유클리드 호제법)
        return b == 0 ? a : gcd(b, a % b);
    }


    public static void main(String[] args) {
        LeetGCDofStrings_1071 solution = new LeetGCDofStrings_1071();
        String anwer = solution.solution("ABCABC", "ABC");
        System.out.println(anwer);
    }
}
