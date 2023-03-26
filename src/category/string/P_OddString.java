package category.string;

public class P_OddString {

    public String solutionWithLinear(String s) {
        char[] arr = s.toCharArray();
        StringBuilder sb = new StringBuilder();

        int idx = 0;
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (c == ' ') {
                idx = 0;
                sb.append(' ');
                continue;
            }

            if (idx % 2 == 0) {
                c = Character.toUpperCase(c);
            } else {
                c = Character.toLowerCase(c);
            }
            sb.append(c);
            idx++;
        }
        return sb.toString();

    }

    public String solution(String s) {
        String[] arr = s.split(" ", -1); // 공백문자가 하나 이상이라 주의
        StringBuilder answer = new StringBuilder();

        for (String str : arr) {
            char[] charArr = str.toCharArray();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < charArr.length; i++) {
                char newChar;
                if (i % 2 == 0) {
                    newChar = Character.toUpperCase(charArr[i]);
                } else {
                    newChar = Character.toLowerCase(charArr[i]);
                }
                sb.append(newChar);
            }
            answer.append(sb).append(" ");
        }
        answer.deleteCharAt(answer.length() - 1);
        return answer.toString();
    }

    public static void main(String[] args) {
        P_OddString p_oddString = new P_OddString();

        String[] tests = {"TrIe TrIe  TrIe "};
        for (String test : tests) {
            System.out.println(p_oddString.solution(test));
        }
    }
}
