package daily.a1227;

import java.util.*;

public class P_DoAssignment {
    static class Work {
        String name;
        int hour;
        int minute;
        final int TIME;
        int leftTime;

        Work(String name, String start, String playtime) {
            this.name = name;
            String[] temp = start.split(":");
            this.hour = Integer.parseInt(temp[0]);
            this.minute = Integer.parseInt(temp[1]);
            TIME = Integer.parseInt(playtime);
            leftTime = TIME;
        }

        @Override
        public String toString() {
            return "Work{"+ name + " " + hour+ ":"+ minute + ", " + leftTime + "/" +TIME +"}";
        }
    }

    // 다음 작업이 시작되기 전까지 기존 작업의 작업 시간을 갱신시켜줌
    static void updateWorkTime(Work cur, Work next) {
        int curMin = cur.hour * 60 + cur.minute;
        int nextMin = next.hour * 60 + next.minute;

        int gap = Math.abs(nextMin - curMin);
        if(gap >= cur.TIME) { // 충분히 앞에 과제를 수행할 수 있는 경우
            cur.leftTime = 0;
        } else { // 앞에 과제를 중단하고 새로운 과제를 시작해야하는 경우
            cur.leftTime -= gap;
        }
    }

    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        int ansIdx = 0, next = 0;

        // 1. start 시간 순으로 정렬
        Arrays.sort(plans, (p1, p2) -> {
            // p[1] : start(HH:MM)
            return p1[1].compareTo(p2[1]);
        });

        // 2. 시작 시간이 빠른 순서대로 규칙대로 작업 수행
        ArrayDeque<Work> stack = new ArrayDeque<>();
        String[] temp = plans[next++];
        Work firstWork = new Work(temp[0], temp[1], temp[2]);
        stack.push(firstWork);

        //현재 시간 기록(분 단위)
        int curTime = firstWork.hour * 60 + firstWork.minute;

        // System.out.println(">> while starts");
        while(!stack.isEmpty()) {
            Work curWork = stack.peek();
            Work nextWork = null;

            if(next < plans.length) {
                String[] nextTemp = plans[next];
                nextWork = new Work(nextTemp[0], nextTemp[1], nextTemp[2]);
            }

            // System.out.println(">>> curTime : " + curTime / 60 + ":" + curTime %60);
            // System.out.println(">>> curWork : " + curWork);
            // System.out.println(">>> nextWork : " + nextWork);

            if(nextWork == null) { // 더 이상 인터셉트할 다음 과제가 없음
                // System.out.println(">>> 더 이상 인터셉트 X");
                answer[ansIdx++] = curWork.name;
                curTime += curWork.leftTime;
                stack.pop();
                continue;
            }

            // 현재 시간 vs 다음 과제 시작 시간 비교 후 현재 중단 작업부터 처리할 건지
            // 그렇다면 현재 시간 남은 과제 시간 더해서 update
            int nextStartTime = nextWork.hour * 60 + nextWork.minute;
            int gap = nextStartTime - curTime;
            // System.out.println(">>> 다음 과제 시작 시간 : "
            //                    + nextStartTime / 60 + ":" + nextStartTime % 60);
            if(gap <= 0) {
                // 바로 다음 작업 시작해야함
                // System.out.println("다음 작업 수행");
                stack.push(nextWork);
                curTime = nextStartTime;
                next++;
                continue;
            }

            // System.out.println(">>>> 현재 작업 먼저 수행");
            // 바로 시작 안해도 되니 현재 stack top에 있는 작업을 수행
            if(curWork.leftTime > gap) {
                curWork.leftTime -= gap;
                curTime += gap;
            } else {
                answer[ansIdx++] = curWork.name;
                curTime += curWork.leftTime;
                stack.pop();

                if(stack.isEmpty()) {
                    stack.push(nextWork);
                    next++;
                    curTime = nextStartTime;
                }
            }

            // System.out.println(">>> updated curTime : " + curTime / 60 + ":" + curTime % 60);
        }

        while(next < plans.length) {
            answer[ansIdx++] = plans[next][0];
            next++;
        }

        return answer;
    }
}
