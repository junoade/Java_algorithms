package category.string;

public class P_FlipBase3 {
    static final int BASE = 3;

    public int solution(int n) {
        String s = getReversedBase3Str(n);
        // s = reverse(s);
        // return convertToDecimal(s);
        return Integer.parseInt(s, 3); // radix(base) = 3으로 줘서 3진수 -> 10진수를 구할 수 있다.
    }

    String getReversedBase3Str(int n) {
        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            int mod = n % BASE;
            n /= BASE;
            sb.append(mod);
        }
        // n / 3 > 0 하면 아래의 코드가 필요함
        // sb.append(n);
        return sb.toString();
    }

    String reverse(String s) {
        char[] original = s.toCharArray();
        int L = original.length;
        char[] target = new char[L];
        for(int i = 0; i < L; i++) {
            target[i] = original[L - i - 1];
        }
        return new String(target);
    }

    int convertToDecimal(String s) {
        int result = 0;
        int powIdx = 0;
        for(int i = s.length() - 1; i >= 0; i--) {
            int num = Character.getNumericValue(s.charAt(i));
            result += Math.pow(3, powIdx++) * num;
        }
        return result;
    }

    public static void main(String[] args) {
        P_FlipBase3 p = new P_FlipBase3();
        System.out.println(p.solution(45));
    }
}
