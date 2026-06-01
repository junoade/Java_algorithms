package daily.y2026;

import java.util.*;

public class Leet_ExclusiveTimeOfFunctions {

    // [0, n-1] 범위의 n개의 함수가 콜스택에 저장
    // 콜 끝나면 popped off
    // top에 있는 건 현재 실행중인 함수
    // 함수의 시작과 끝에 로깅함 {function_id}:{"start" | "end"}:{timestamp}
    // i-th 번쨰 함수에 대한 exclusive time 반환하기
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] answer = new int[n];

        Deque<int[]> stack = new ArrayDeque<>();
        for (String log : logs) {
            int[] parsedLog = parsedFast(log);

            int funcId = parsedLog[0];
            int cmd = parsedLog[1];
            int timestamp = parsedLog[2];

            if (cmd == 1) { // "start"
                if (!stack.isEmpty()) {
                    int[] prev = stack.peek();
                    int prevFuncId = prev[0];
                    int prevTimestamp = prev[1];
                    answer[prevFuncId] += timestamp - prevTimestamp; // 멈추기 전까지 시간을 누적
                }
                stack.push(new int[]{funcId, timestamp});
            } else {
                int[] prev = stack.pop();
                int prevFuncId = prev[0];
                int prevTimestamp = prev[1];
                answer[funcId] += timestamp - prevTimestamp + 1; // +1 inclusive

                if (!stack.isEmpty()) {
                    stack.peek()[1] = timestamp + 1; // 이전 prevFuncId에 대한 타임스탬프 갱신
                }
            }

        }

        return answer;
    }

    // indexOf 를 활용하여 파싱
    private int[] parsedFast(String log) {
        int first = log.indexOf(':');
        int second = log.indexOf(':', first + 1);

        int funcId = Integer.parseInt(log.substring(0, first));
        // cmd는 s/e 첫 글자만 보면 됨
        int isStart = log.charAt(first + 1) == 's' ? 1 : 0;
        int timestamp = Integer.parseInt(log.substring(second + 1));

        return new int[]{funcId, isStart, timestamp};
    }

    public static void main(String[] args) {
        Leet_ExclusiveTimeOfFunctions obj = new Leet_ExclusiveTimeOfFunctions();
        var answer = obj.exclusiveTime(2, List.of(new String[]{"0:start:0","1:start:2","1:end:5","0:end:6"}));
        System.out.println(Arrays.toString(answer));
    }
}
