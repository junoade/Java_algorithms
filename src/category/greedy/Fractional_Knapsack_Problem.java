package category.greedy;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * --------------------------------------------------------------<br/>
 * <b> 배낭 문제 - 부분 배낭 문제 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 배낭 문제는 (무게, 가치)를 갖는 물건들을 가방의 최대 무게에 가치가 최대로 되게 넣는 문제이다. <br/>
 * 1. 부분 배낭 문제(Fractional Knapsack) : 한 물건을 쪼개도 그 가치가 유지된다. 그리디 알고리즘 적용
 * 2. 0/1 Knapsack 문제 : 물건을 쪼갤 수 없어, DP, 백트래킹으로 풀이한다.
 * <p>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * <br/> 일단 부분 배낭 문제를 풀어본다.
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  / 256MB , 실행시간  / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class Fractional_Knapsack_Problem {

    // HashMap<Integer, Integer> map;
    // 사실 static class 로 만드는 것도... compareTo override

    public static double solution(Integer[][] items, int MAX_WEIGHT) {
        double result = 0.0;
        double availWeight = MAX_WEIGHT;

        /* step1) 단위 가치를 기준으로 내림차순 정렬 O(N log N)*/
        // sortByValue(items);
        sortByPartialValue(items);


        /* item[0] : weight, item[1] : value */
        for (Integer[] item : items) {
            if (availWeight >= item[0]) {
                result += item[1]; // 가치를 그냥 추가
                availWeight -= item[0];
            } else { // 남은 무게 공간 분수로 물건을 쪼갤 수 있는가?
                double partialValue = item[1] / (double) item[0];
                final int unit = 1;
                while(availWeight >= unit){
                    result += partialValue;
                    availWeight -= unit;
                }
            }
        }

        return result;
    }

    static void sortByValue(Integer[][] items){
        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < items[0].length - 1; j++) {
                if (items[j][1] <= items[j + 1][1]) {
                    // swap
                    int tempW = items[j][0];
                    int tempV = items[j][1];
                    items[j][0] = items[j + 1][0];
                    items[j][1] = items[j + 1][1];
                    items[j + 1][0] = tempW;
                    items[j + 1][1] = tempV;
                }
            }
        }
    }

    static void sortByPartialValue(Integer[][] items){
        Arrays.sort(items, new Comparator<Integer[]>(){
            @Override
            public int compare(Integer[] item1, Integer[] item2){
                // v / w
                double rate1 = item1[1] / (double) item1[0];
                double rate2 = item2[1] / (double) item1[0];
                return (int) (rate2 - rate1); // 내림차순 정렬
            }
        });
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int ITEM_COUNT = Integer.parseInt(st.nextToken());
        final int MAX_WEIGHT = Integer.parseInt(st.nextToken());
        Integer[][] items = new Integer[2][ITEM_COUNT];

        for (int i = 0; i < ITEM_COUNT; i++) {
            st = new StringTokenizer(br.readLine());
            items[i][0] = Integer.parseInt(st.nextToken());
            items[i][1] = Integer.parseInt(st.nextToken());
        }

        solution(items, MAX_WEIGHT);
    }
}
