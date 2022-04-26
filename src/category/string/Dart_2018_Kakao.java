package category.string;

/**
 * Programmers_ 2018 Kakao Blind
 * 다트
 * 유형 : 문자열
 *
 */
import java.io.*;

public class Dart_2018_Kakao {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        System.out.println(solution(s));
    }

    /* 정규표현식으로, 점수부, 옵션부 나눠서 주어진 규칙에 맞게 계산하자. */
    public static int solution(String s){
        s = s.replace(" ", "");
        String[] scores = s.split("\\D");
        String[] options = s.split("\\d|\\*");

        int result = 0;
        for(int i = 0; i<scores.length; i++){
            int temp = Integer.parseInt(scores[i]);
            int before = 0;
            if(i>=2)
                before = Integer.parseInt(scores[i-1]);

            switch(options[i]){
                case "S":
                    result += Math.pow(temp, 1);
                    break;
                case "D":
                    result += Math.pow(temp, 2);
                    break;
                case "T":
                    result += Math.pow(temp, 3);
                    break;
                case "*":
                    result += before + temp * 2;
                    break;
                case "-":
                    result += temp * (-1);
                    break;
            }
        }
        return result;
    }

}
