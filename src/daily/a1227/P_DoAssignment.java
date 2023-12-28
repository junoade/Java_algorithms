package daily.a1227;

import java.util.*;

public class P_DoAssignment {
    static class Work {
        String name;
        int hour;
        int minute;
        final int TIME;
        int leftTime;

        Work(String[] plan) {
            name = plan[0];
            String start = plan[1], playtime = plan[2];
            String[] temp = start.split(":");
            this.hour = Integer.parseInt(temp[0]);
            this.minute = Integer.parseInt(temp[1]);
            TIME = Integer.parseInt(playtime);
            leftTime = TIME;
        }

        @Override
        public String toString() {
            return "Work{" + name + " " + hour + ":" + minute + ", " + leftTime + "/" + TIME + "}";
        }
    }

    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];

        // 1. start 시간 순으로 정렬
        Arrays.sort(plans, Comparator.comparing(p -> p[1])); // keyExtractor!

        // 2. 시작 시간이 빠른 순서대로 규칙대로 작업 수행
        int ansIdx = 0, next = 0;
        ArrayDeque<Work> stack = new ArrayDeque<>();
        Work firstWork = new Work(plans[next++]);
        stack.push(firstWork);

        //현재 시간 기록(분 단위)
        int curTime = firstWork.hour * 60 + firstWork.minute;

        // System.out.println(">> while starts");
        while (!stack.isEmpty()) {
            Work curWork = stack.peek();
            Work nextWork;

            // 다음 새로운 과제가 존재하는 경우
            if (next < plans.length) {
                nextWork = new Work(plans[next]);
            } else {
                answer[ansIdx++] = curWork.name;
                curTime += curWork.leftTime;
                stack.pop();
                continue;
            }

            // 현재 시간 vs 다음 과제 시작 시간 비교 후 현재 중단 작업부터 처리할 건지
            // 그렇다면 현재 시간 남은 과제 시간 더해서 update
            int nextStartTime = nextWork.hour * 60 + nextWork.minute;
            int gap = nextStartTime - curTime;
            if (gap <= 0) {
                // 바로 다음 작업 시작해야함
                stack.push(nextWork);
                curTime = nextStartTime;
                next++;
                continue;
            }

            // 바로 시작 안해도 되니 현재 stack top에 있는 작업을 수행
            if (curWork.leftTime > gap) {
                curWork.leftTime -= gap;
                curTime += gap;
            } else {
                curTime += curWork.leftTime;
                answer[ansIdx++] = curWork.name;
                stack.pop();

                // [주의] 현재 작업 스택에서 과제를 수행하고 스택이 비어서 while문이 아예 끝날 수 있음
                // 그래서 만약 마지막 진행 과제였다면 새로운 다음 과제가 존재할 수 있으므로 nextWork를 push한다.
                if (stack.isEmpty()) {
                    stack.push(nextWork);
                    next++;
                    curTime = nextStartTime;
                }
            }

        }

        return answer;
    }
}
