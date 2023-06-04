package category.linkedList;

import java.io.*;
import java.util.*;

/**
 * --------------------------------------------------------------<br/>
 * <b>백준 카드2 2164 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 링크드리스트의 삽입 연산의 시간복잡도를 알고 있는가?<br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 기본적인 알고리즘 로직 짜는 것 OK<br/>
 * ArrayList 또는 배열 vs 링크드 리스트 차이점 확인<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리 45MB / 128MB  , 실행시간 228ms / 2000ms<br/>
 * --------------------------------------------------------------
 */
public class B_Card2 {

    static void solution(int N) {
        List<Integer> list = makeList(N);

        while(list.size() != 1) {
            int topIdx = list.size() - 1;
            list.remove(topIdx);

            if(list.size() == 1) {
                break;
            }

            topIdx = list.size() - 1;
            Integer temp = list.get(topIdx);
            list.remove(topIdx);
            list.add(0, temp);
        }

        System.out.println(list.get(0));
    }

    static List<Integer> makeList(int N) {
        List<Integer> list = new LinkedList<>();
        for(int i = N; i >= 1; i--) {
            list.add(i);
        }
        return list;
    }


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        solution(N);
    }
}
