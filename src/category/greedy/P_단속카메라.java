package category.greedy;

import java.util.*;

public class P_단속카메라 {
    public int solution(int[][] routes) {
        // routes의 끝 지점을 바탕으로 오름차순 정렬한다.
        Arrays.sort(routes, (r1, r2) -> Integer.compare(r1[1], r2[1]));

        // 마지막 cctv의 위치를
        // 각 route의 끝 지점에 맞춰서 설치해본다.
        // cctv 범위내라면 skip
        // 아니라면 cctv 추가
        int count = 0;
        int last = -30_001;
        for(int[] r : routes) {
            boolean isCatchable = r[0] <= last && last <= r[1];
            if(isCatchable) {
                continue;
            }

            last = r[1];
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        P_단속카메라 test = new P_단속카메라();
        int[][] routes = {{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}};
        System.out.println(test.solution(routes));
    }
}
