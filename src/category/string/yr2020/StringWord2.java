package category.string.yr2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2021 카카오 채용 연계형 인턴쉽
 * 2차 풀이 - 문자열 유형
 */
public class StringWord2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringWord2 sw2 = new StringWord2();
        System.out.println(sw2.solution(s));
    }
    public int solution(String s){
        String[] str = {"zero", "one", "two", "three", "four","five", "six", "seven", "eight", "nine"};
        for(int i =0; i< str.length; i++){
            s = s.replace(str[i],Integer.toString(i));
            /*replaceAll 에선 정규식을 target 의 인자로 받을 수 있다. */
        }
        return Integer.parseInt(s);
    }
}
