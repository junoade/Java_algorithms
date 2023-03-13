package category.greedy;

import java.util.ArrayList;
import java.util.Arrays;

public class P_FindHotelRoom_F {
    static final int CLEANING_MINUTE = 10;

    class Schedule {
        String startTime;
        String endTime;

        Schedule(String startTime, String endTime) {
            this.startTime = startTime;
            this.endTime = updateEndTime(endTime);
        }
    }

    class Room {
        ArrayList<Schedule> schedules;

        Room(Schedule s) {
            schedules = new ArrayList<>();
            schedules.add(s);
        }

        void addSchedule(Schedule s) {
            schedules.add(s);
        }

        /* O(N) */
        boolean isAvailable(Schedule newSchedule) {
            for (Schedule s : schedules) {
                if (!isAvail(s, newSchedule)) {
                    return false;
                }
            }
            return true;
        }
    }

    static String updateEndTime(String endTime) {
        int hour = Integer.parseInt(endTime.substring(0, 2));
        int minute = Integer.parseInt(endTime.substring(3)) + CLEANING_MINUTE;
        if (minute >= 60) {
            hour++;
            minute -= 60;
        }

        String strHour = hour >= 10 ? String.valueOf(hour) : "0" + hour;
        String strMin = minute >= 10 ? String.valueOf(minute) : "0" + minute;
        return String.format("%s:%s", strHour, strMin); // 여기서 주의; 17:0 이런식으로 반환되어서 문제 발생 가능성
    }

    static boolean isAvail(Schedule oldData, Schedule newData) {
        // boolean startTimeAvail = newData.startTime.compareTo(oldData.startTime) < 0;
        // boolean endTimeAvail = newData.endTime.compareTo(oldData.endTime) < 0;
        // return startTimeAvail && endTimeAvail;
        return newData.startTime.compareTo(oldData.endTime) >= 0;
    }

    public int solution(String[][] book_time) {
        ArrayList<Room> rooms = new ArrayList<>();
        sortByTerms(book_time);

        for (String[] row : book_time) {
            Schedule newSchedule = new Schedule(row[0], row[1]);
            boolean needMoreRoom = true;
            for (Room room : rooms) {
                // 기존 스케쥴 리스트를 순회하며 해당 방이 이용가능한지 탐색 후 새로 방 추가가 필요한지 확인
                if (room.isAvailable(newSchedule)) {
                    room.addSchedule(newSchedule); // ok
                    needMoreRoom = false;
                    break;
                }
            }

            if (needMoreRoom) {
                rooms.add(new Room(newSchedule));
            }
        }
        return rooms.size();
    }

    void sortByTerms(String[][] book_time) {
        Arrays.sort(book_time, (b1, b2) -> {
            int result = b1[0].compareTo(b2[0]);
            if (result == 0) {
                String t1_end = updateEndTime(b1[1]);
                String t2_end = updateEndTime(b2[1]);
                return t1_end.compareTo(t2_end);
            } else {
                return result;
            }
        });
    }

    public static void main(String[] args) {
        P_FindHotelRoom_F hotel = new P_FindHotelRoom_F();
        // String[][] book_time = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};
        String[][] bound_test = {{"00:00", "03:55"},{"00:00", "02:55"}, {"09:55", "10:55"}, {"11:05", "16:50"}, {"23:00", "23:50"}};
        String[][] test2 = {{"23:12", "23:30"}, {"01:23", "01:54"}, {"02:04", "03:00"}};

        System.out.println(hotel.solution(test2));
    }
}
