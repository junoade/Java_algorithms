package category.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * SW 역량 테스트 준비, 알고리즘 온라인 스터디
 * D1~D2 문풀 기초다지기 Part1
 * 연월일 확인
 * 정답률 69%
 */

public class DateConvertor_samsung {

    /* 데이터를 어떻게 넣을 것 인가? */
    public static final String[] final_date = {"31", "28", "30"};
    public static Map<String, String> calendar = new HashMap<String, String>(){
        {
            put("01", final_date[0]);
            put("02", final_date[1]);
            put("03", final_date[0]);
            put("04", final_date[2]);
            put("05", final_date[0]);
            put("06", final_date[2]);
            put("07", final_date[0]);
            put("08", final_date[0]);
            put("09", final_date[2]);
            put("10", final_date[0]);
            put("11", final_date[2]);
            put("12", final_date[0]);
        }
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            solution(i, temp);
        }
    }

    public static void solution(int T, String s) {
        /*
        int year = Integer.parseInt(s.substring(0, 4));
        int month = Integer.parseInt(s.substring(4, 6));
        int date = Integer.parseInt(s.substring(6, 8));
        */
        String year =s.substring(0, 4);
        String month = s.substring(4, 6);
        String date = s.substring(6, 8);

        /* 간단한 알고리즘 */
        if (calendar.containsKey(month) && Integer.parseInt(calendar.get(month)) >= Integer.parseInt(date)) {
            System.out.println("#" + T + " " +convert(year, month, date) );
        }else {
            System.out.println("#" + T + " " +-1);
        }
    }

    /* year, month, date*/
    public static String convert(String... s) {
        return s[0] + "/" + s[1] + "/" + s[2];
    }
}
