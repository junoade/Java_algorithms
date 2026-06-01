package daily.y2026;

import java.util.*;

public class Leet_ExclusiveTimeOfFunctions {
    final String START = "start";
    final String END = "end";

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] answer = new int[n];

        Deque<int[]> stack = new ArrayDeque<>();

        for (String log : logs) {
            String[] parsedLog = parse(log);

            int funcId = Integer.parseInt(parsedLog[0]);
            String cmd = parsedLog[1];
            int timestamp = Integer.parseInt(parsedLog[2]);

            if (cmd.equals(START)) {
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

    private String[] parse(String log) {
        return log.split(":");
    }

    public static void main(String[] args) {
        Leet_ExclusiveTimeOfFunctions obj = new Leet_ExclusiveTimeOfFunctions();
        var answer = obj.exclusiveTime(2, List.of(new String[]{"0:start:0","1:start:2","1:end:5","0:end:6"}));
        System.out.println(Arrays.toString(answer));
    }
}
