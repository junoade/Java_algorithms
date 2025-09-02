package daily.y2025.q3;

import java.util.*;

public class Solution_P_유연근무제 {
    final int MAX_DAYS = 7;

    // 5 -> 6 -> 7 ->...
    //[ [710, 2359, 1050, 700, 650, 631, 659],
    //  [800, 801, 805, 800, 759, 810, 809],
    //  [1105, 1001, 1002, 600, 1059, 1001, 1100] ]

    public int solution(int[] schedules, int[][] timelogs, int startday) {
        Set<Integer> fails = new HashSet<>();
        final int N = schedules.length;

        for(int i = 0; i < MAX_DAYS; i++) {
            // int curDay = (startday + i)  % (MAX_DAYS + 1); // if(curday == 0) curday = 1;
            int curDay = (startday + i - 1)  % MAX_DAYS + 1;package daily.y2025.q3;

import java.util.*;

            public class Solution_P_유연근무제 {
                final int MAX_DAYS = 7;

                // 5 -> 6 -> 7 ->...
                //[ [710, 2359, 1050, 700, 650, 631, 659],
                //  [800, 801, 805, 800, 759, 810, 809],
                //  [1105, 1001, 1002, 600, 1059, 1001, 1100] ]

                public int solution(int[] schedules, int[][] timelogs, int startday) {
                    Set<Integer> fails = new HashSet<>();
                    final int N = schedules.length;

                    for(int i = 0; i < MAX_DAYS; i++) {
                        // int curDay = (startday + i)  % (MAX_DAYS + 1);
                        int curDay = (startday + i - 1)  % MAX_DAYS + 1;

                        if(isWeekend(curDay)) {
                            continue;
                        }

                        // 직원별로 이벤트 탈락 확인
                        for(int j = 0; j < N; j++) {

                            // 이미 이벤트 탈락한 직원
                            if(fails.contains(j)) {
                                continue;
                            }

                            int expectedTime = schedules[j];
                            int acutalTime = timelogs[j][i];
                            if(!validWorkTime(expectedTime, acutalTime)) {
                                fails.add(j);
                            }
                        }

                    }

                    return N - fails.size();
                }

                boolean validWorkTime(int expectedTime, int actualTime) {
                    int minute = (expectedTime + 10) % 100;
                    int hour = expectedTime / 100;

                    if(minute >= 60) {
                        minute -= 60;
                        hour += 1;
                    }

                    expectedTime = hour * 100 + minute;
                    return actualTime <= expectedTime;
                }

                boolean isWeekend(int day) {
                    // System.out.println(day);
                    return day == 6 || day == 7;
                }
            }


            if(isWeekend(curDay)) {
                continue;
            }

            // 직원별로 이벤트 탈락 확인
            for(int j = 0; j < N; j++) {

                // 이미 이벤트 탈락한 직원
                if(fails.contains(j)) {
                    continue;
                }

                int expectedTime = schedules[j];
                int acutalTime = timelogs[j][i];
                if(!validWorkTime(expectedTime, acutalTime)) {
                    fails.add(j);
                }
            }

        }

        return N - fails.size();
    }

    boolean validWorkTime(int expectedTime, int actualTime) {
        int minute = (expectedTime + 10) % 100;
        int hour = expectedTime / 100;

        if(minute >= 60) {
            minute -= 60;
            hour += 1;
        }

        expectedTime = hour * 100 + minute;
        return actualTime <= expectedTime;
    }

    boolean isWeekend(int day) {
        // System.out.println(day);
        return day == 6 || day == 7;
    }
}
