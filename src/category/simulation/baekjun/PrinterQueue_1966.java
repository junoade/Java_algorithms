package category.simulation.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * --------------------------------------------------------------
 * <title> 백준 1966, 프린터 큐</title>
 * --------------------------------------------------------------
 * <b> 주요 키포인트 </b>
 * - 큐의 개념
 * - 주어진대로 구현할 수 있는가?
 * --------------------------------------------------------------
 * <b> 나의 풀이 </b>
 * - static 클래스를 만들고, 이를 통해 주어진 방식으로 정렬 후에도
 * - 중복되는 우선순위 구분할 수 있도록 함
 * --------------------------------------------------------------
 *
 * <b> 채점 </b>
 * <p> 메모리 15296KB/ 128MB , 시간 204ms / 2000ms
 * --------------------------------------------------------------
 */
public class PrinterQueue_1966 {
    static class Element {
        int id;
        int priority;

        Element(int id, int priority) {
            this.id = id;
            this.priority = priority;
        }
    }

    public static void solution(int[] priority, int N, int M) {
        int count = 0;
        ArrayList<Element> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            list.add(new Element(i, priority[i]));
        }

        while (!list.isEmpty()) {
            sort(list);
            count++;
            if(list.get(0).id != M){
                list.remove(0);
            }else{
                break;
            }
        }
        System.out.println(count);
    }

    public static int max(ArrayList<Element> list) {
        int max = 0;
        for (Element e : list) {
            if (e.priority >= max) {
                max = e.priority;
            }
        }
        return max;
    }

    public static void sort(ArrayList<Element> list){
        for (int i = 0; i < list.size(); i++) {
            Element head = list.get(0);
            /* 우선순위가 낮은 경우 뒤로 보내준다. */
            if (head.priority < max(list)) { // O(N)
                list.add(head);
                list.remove(0);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken()); // 테스트케이스의 수

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[] priority = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            solution(priority, N, M);
        }

    }
}
