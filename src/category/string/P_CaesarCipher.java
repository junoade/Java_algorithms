package category.string;

public class P_CaesarCipher {
    static final char EMPTY_SPACE = ' ';

    public String solution(String s, int n) {
        StringBuilder answer = new StringBuilder();
        for(char c : s.toCharArray()) {
            if(c == EMPTY_SPACE) {
                answer.append(EMPTY_SPACE);
                continue;
            }

            answer.append(moveAlphabet(c, n));
        }
        return answer.toString();
    }

    char moveAlphabet(char c, int n) {
        final char UPPERCASE_LAST_CHAR = 'Z';
        final char LOWERCASE_LAST_CHAR = 'z';

        int idx = 0;
        char newChar = (char)(c + n);
        if(Character.isUpperCase(c) && newChar > UPPERCASE_LAST_CHAR) {
            idx = newChar - UPPERCASE_LAST_CHAR - 1;
            newChar = (char)('A' + idx);
        }

        if(Character.isLowerCase(c) && newChar > LOWERCASE_LAST_CHAR) {
            idx = newChar - LOWERCASE_LAST_CHAR - 1;
            newChar = (char)('a' + idx);
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
