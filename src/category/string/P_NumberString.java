package category.string;

public class P_NumberString {
    static final String[] rules = {"zero", "one", "two", "three", "four", "five",
            "six", "seven", "eight", "nine"};

    public int solution(String s) {
        for(int i = 0; i < rules.length; i++) {
            s = s.replace(rules[i], Integer.toString(i));
        }
        return Integer.parseInt(s);
    }

    public static void main(String[] args) {
        P_NumberString p = new P_NumberString();
        String test01 = "one4seveneight";
        System.out.println(p.solution(test01));
    }
}
