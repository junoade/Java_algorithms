package category.string;

public class P_CaesarCipher {
    static final char EMPTY_SPACE = ' ';
    static final int ALPHABET_LENGTH = 26;

    public String solution(String s, int n) {
        StringBuilder answer = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == EMPTY_SPACE) {
                answer.append(EMPTY_SPACE);
                continue;
            }

            answer.append(moveAlphabet(c, n));
        }
        return answer.toString();
    }

    char moveAlphabet(char c, int n) {
        final char UPPERCASE_FIRST_CHAR = 'A';
        final char LOWERCASE_FIRST_CHAR = 'a';

        int idx = 0;
        char newChar;

        if (Character.isUpperCase(c)) {
            idx = (c - UPPERCASE_FIRST_CHAR + n) % ALPHABET_LENGTH;
            newChar = (char) (UPPERCASE_FIRST_CHAR + idx);
        } else {
            idx = (c - LOWERCASE_FIRST_CHAR + n) % ALPHABET_LENGTH; // c-'a'의 결과는 int형
            newChar = (char) (LOWERCASE_FIRST_CHAR + idx);
        }
        return newChar;
    }

    public static void main(String[] args) {
        P_CaesarCipher p_caesarCipher = new P_CaesarCipher();
        String test01 = "AB";
        String test02 = "z";
        String test03 = " ";
        String test04 = "a B z";
        System.out.println(p_caesarCipher.solution(test01, 1));
        System.out.println(p_caesarCipher.solution(test02, 1));
        System.out.println(p_caesarCipher.solution(test03, 1));
        System.out.println(p_caesarCipher.solution(test04, 4));
    }
}
