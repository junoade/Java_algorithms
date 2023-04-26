package category.queue;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * --------------------------------------------------------------<br/>
 * <b>프로그래머스 기능개발 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 진척도 배열의 인덱스 순서는 곧 기능의 순서<br/>
 * 그러나 각 기능의 진척도는 따로 계산이 되나,<br/>
 * 배포는 앞선 기능이 완료되었을 때 가능<br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 현재 진척도, 개발 속도, 지난 날짜를 바탕으로 진척도가 100인지 검사<br/>
 * 그렇다면 배포된 기능 count를 증가시켜주고, 다음 기능으로 이동할 수 있도록 포인터용 인덱스를 증가시켜줌<br/>
 * 만약 아니라면
 * a. 지금까지 배포된 기능의 count를 answer 리스트에 삽입하고 초기화 해줌
 * b. 지금까지의 진척도에 대해서 완료되는데 소요되는 시간을 누적 갱신해줌
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  85MB, 실행시간  3.14ms <br/>
 * --------------------------------------------------------------
 */
public class P_FunctionDev {
    final int MAX = 100;

    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<>();
        int head = 0;
        int L = progresses.length;
        int passedDays = 0;
        int count = 0;

        while (head < L) {
            // 첫 작업 진척도, 지난 기간, 작업 속도를 바탕으로 현재 누적된 작업 진척도를 구함
            int value = progresses[head] + passedDays * speeds[head];

            // 그 값이 MAX 이상이라면
            if (value >= MAX) {
                count++;
                head++;
            } else { // 그 값이 MAX보다 낮으면 새로운 배포 단위를 생성
                if (count != 0) {
                    answer.add(count);
                    count = 0;
                }
                // 새로운 배포 단위의 기능이 필요로 하는 추가 날짜 계산
                passedDays += getPassedDays(value, speeds[head]);
            }
        }

        // 마지막 배포 단위는 위의 루프에서 처리되지 않으므로, 마지막 count 추가
        answer.add(count);

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private int getPassedDays(int progress, int speed) {
        if (progress >= MAX) {
            return 0;
        }

        if ((MAX - progress) % speed > 0) {
            return (MAX - progress) / speed + 1;
        } else {
            return (MAX - progress) / speed;
        }
    }

    public static void main(String[] args) {
        P_FunctionDev test = new P_FunctionDev();
        int[] progresses = {93, 30, 55};
        int[] p2 = {95, 90, 99, 99, 80, 99};

        int[] speeds = {1, 30, 5};
        int[] s2 = {1, 1, 1, 1, 1, 1};
        System.out.println(Arrays.toString(test.solution(p2, s2)));
    }
}
