package category.simulation.programmers.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewID_Recommend {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(solution(br.readLine()));
    }

    public static String solution(String new_id){
        new_id = new_id.toLowerCase();

        /* 2단계 */
        String regx_2 = "[^a-z0-9-_.]";
        Pattern ptrn = Pattern.compile(regx_2);
        Matcher m = ptrn.matcher(new_id);
        new_id = m.replaceAll("");

        /* 3단계 */
        String regx_3 = "[.]{2,}"; // (.){2} 로 하니까 ............m 이런식으로 되네.
        ptrn = Pattern.compile(regx_3);
        m = ptrn.matcher(new_id);
        new_id = m.replaceAll(".");

        /* 4단계 */
        String regx_4 = "^(.)|(.)$";
        ptrn = Pattern.compile(regx_4);
        m = ptrn.matcher(new_id);
        new_id = m.replaceAll("");

        /* 5단계 */
        if(new_id.length() == 0)
            new_id += 'a';

        if(new_id.length() >= 16) {
            new_id = new_id.substring(0, 15);
            if (new_id.charAt(14) == '.') {
                new_id = new_id.substring(14);
            }
        }

        /* 7단계 */
        StringBuilder sb = new StringBuilder(new_id);
        while(sb.length() <= 2){
            sb.append(sb.charAt(sb.length() - 1));
        }
        new_id = sb.toString();

        return new_id;
    }
}
