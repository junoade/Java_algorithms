package programmers.kakao.internship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * --------------------------------------------------------------
 * 프로그래머스_Level1_신고 결과 받기
 * 유형 - 구현, E-R 다이어그램 개념과 ORM 연관성
 * 출처, 2022 KAKAO BLIND RECRUITMENT
 * --------------------------------------------------------------
 * 주요 키포인트
 * -> HashMap 이용, 신고자 - 피신고자 관계 맵핑 고려하기
 * -> Map과 List 활용하기
 * -> 객체지향스럽게 바꿀 수 있다.
 *
 * 한번 다시 풀어보기
 *
 * 채점 : 25/25
 */
public class GetReport_2022_Kakao {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] id_list = br.readLine().split(",");
        String[] report = br.readLine().split(",");
        int k = Integer.parseInt(br.readLine());
        System.out.println(Arrays.toString(solution(id_list, report, k)));

    }
    public static int[] solution(String[] id_list, String[] report, int k){
        int[] answer = new int[id_list.length];

        /* 한 피신고자와 여러 신고자와의 관계 */
        Map<String, ArrayList<String>> user = new HashMap<>();
        for(String info:report){
            String[] temp = info.split(" ");
            String reporter = temp[0], reported = temp[1];

            /* 신고된 적이 없는 피신고자일 경우 */
            if(user.get(reported) == null){
                user.put(reported, new ArrayList<>(Arrays.asList(reporter)));
            }
            /* 신고된 적이 있는 피신고자일 경우 */
            else{
                // 이 때 신고자의 중복을 피할 필요가 있음
                 if(!user.get(reported).contains(reporter)){
                     user.get(reported).add(reporter);
                 }
            }
        }
        /* 아이디가 신고 받은 횟수 */
        ArrayList<String> id = new ArrayList<>(Arrays.asList(id_list));
        user.forEach((key, valueList)->{
            int report_cnt = valueList.size(); // 피신고자를 신고한 사람들의 수를 센다. 즉 신고된 횟수
            for(String reporter : valueList){ // 피신고자를 신고한 사람들의 List를 순회
                int idx = id.indexOf(reporter); // 각 신고자들의 idx를 얻고,
                if(idx != -1 && k <= report_cnt){ // 찾았고, k 이상이라면,
                    answer[idx]++; // 증가 시켜준다.
                }
            }
        });

        return answer;
    }
}
