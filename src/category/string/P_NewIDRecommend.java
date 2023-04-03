package category.string;

public class P_NewIDRecommend {
    private static final char EMPTY_STRING_APPEND_SYMBOL = 'a';
    private static final int MAX_LENGTH = 16;
    private static final int MIN_LENGTH = 3;
    public String solution(String new_id) {
        // step1
        new_id = new_id.toLowerCase();
        // step2
        new_id = new_id.replaceAll("[^a-z0-9-_.]", ""); // 굳이 |를 redundant 하게 안써도 됨

        // step3
        new_id = new_id.replaceAll("\\.{2,}", "."); // 주의 .이 그대로 쓰이면 개행 문자를 제외한 아무 문자를 의미하는 정규표현식임

        // step4
        new_id = new_id.replaceAll("^\\.|\\.$", ""); // Optional[] 이 아닌 그냥 ^이 쓰이면 접두사를 가르키는 정규표현식

        // step5
        if(new_id.length() == 0) {
            new_id += EMPTY_STRING_APPEND_SYMBOL;
        }

        // step6
        if(new_id.length() >= MAX_LENGTH) {
            new_id = new_id.substring(0, MAX_LENGTH - 1).replaceAll("\\.$", "");
        }

        // step7
        StringBuilder sb = new StringBuilder(new_id);
        while(sb.length() < MIN_LENGTH) {
            char lastChar = new_id.charAt(new_id.length() - 1);
            sb.append(lastChar);
        }
        new_id = sb.toString();

        return new_id;
    }

    public static void main(String[] args) {
        P_NewIDRecommend p = new P_NewIDRecommend();
        String test01 = "..aZXsasd...asd.";
        System.out.println(p.solution(test01));
    }
}
