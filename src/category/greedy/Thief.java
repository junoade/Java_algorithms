package category.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

/**
 * <title>체육복 도둑</title>
 * <a>https://programmers.co.kr/learn/courses/30/lessons/42862</a>
 *
 * @problem 히든 테스트케이스 주요 문제점
 * 1. lost 배열, reserve 배열을 정렬할 필요가 있다.
 * - 4, [2, 4], [3, 5] 이렇게 입력되었다 가정하면, lost 2 는 rsv 3을 갖고, lost 4는 rsv 5를 가져서 max는 4여야 한다.
 * - 반면에, 4, [4, 2], [3, 5] 이렇게 입력된다면?
 * -- lost 4는 자신의 왼쪽부터 가져가, reserve 3을 가져가면, lost 2는 가져갈 것이 없다.
 *
 * 2. 내 여분이 도난되었다면, lost, reserve 리스트에서 제거해줄 필요가 있다.
 * - 예를 들어 [1, 2. 4] [2,3,4] 일 때, 2번 4번 옷이 도난되었는데, 1번에게 2번 옷을 빌려주면 2번은 자기 옷이 없음.
 * - 이런 경우 이 학생은 다른 학생에게 옷을 빌려줄 수 없다고 문제에서 명시되어 있기 때문이다.
 *
 * @idea
 * 1. lost배열, reserve배열을 정렬할 필요가 있다.
 * 2. 내 여분이 도난되었다면 적절하게 처리해줄 필요가 있다.
 * 3. 그외 도난받은 학생들에게 자신의 키값의 -1~+1 에 적절한 여분을 -1 부터 배정한다. (이를 위해 정렬 필요)
 *
 */
public class Thief {

    public static int solution(int N, int[] lost, int[] reserve) {
        int answer = N - lost.length;

        ArrayList<Integer> lst = new ArrayList<>();
        for (int num : lost)
            lst.add(num);

        ArrayList<Integer> rsv = new ArrayList<>();
        for (int num : reserve)
            rsv.add(num);

        /* step1) 이 로직대로 하려면 정렬 필요 */
        Collections.sort(lst);
        Collections.sort(rsv);

        /* step2) lost 배열, reserve 배열 중 여분을 가진 자신이 도난당한 경우 처리 */
        int i = 0;
        while(i<lst.size()){
            Integer key = lst.get(i);
            if(rsv.contains(key)) {
                answer++;
                rsv.remove(rsv.indexOf(key));
                lst.remove(i);
                i = 0;
                continue; // 일반적인 for문의 경우 i가 증가되어버려 5, [2,3,5], [2,3,5]와 같은 경우 제대로 처리 X
                // 향상된 for문의 경우 반복하면서, 순회하는 List를 제거해서 에러가 발생가능
            }
            i++;
        }

        /* step3) lost 학생들을 순회하며, 자신의 번호의 앞뒤 번호가 reserve 배열에 있는지 확인*/
        for (Integer key : lst) {
            /* 여벌 체육복이 있는 학생이 도난된 경우? *//*
            if (rsv.contains(key)) {
                answer++;
                rsv.remove(rsv.indexOf(key));
            }*/

            /* 그렇지 않아서, 다른 학생들에게 빌려주는 경우 */
            if (rsv.contains(key - 1)) {
                answer++;
                rsv.remove(rsv.indexOf(key - 1)); // 빌려주고 나서는, 리스트에서 제거
            } else if (rsv.contains(key + 1)) {
                answer++;
                rsv.remove(rsv.indexOf(key + 1));
            }

        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");
        String[] temp2 = br.readLine().split(" ");

        int[] lost = new int[temp.length];
        int[] reserve = new int[temp2.length];

        for (int i = 0; i < lost.length; i++) {
            lost[i] = Integer.parseInt(temp[i]);
        }
        for (int i = 0; i < reserve.length; i++)
            reserve[i] = Integer.parseInt(temp2[i]);

        System.out.println(solution(N, lost, reserve));

    }
}
