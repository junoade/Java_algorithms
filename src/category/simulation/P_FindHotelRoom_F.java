package category.simulation;

import java.util.ArrayList;
import java.util.Arrays;

public class P_FindHotelRoom_F {
    class Schedule {
        String startTime;
        String endTime;
        final int CLEANING_MINUTE = 10;

        Schedule(String startTime, String endTime) {
            this.startTime = startTime;
            this.endTime = updateEndTime(endTime);
        }

        String updateEndTime(String endTime) {
            int hour = Integer.parseInt(endTime.substring(0, 2));
            int minute = Integer.parseInt(endTime.substring(3)) + CLEANING_MINUTE;
            if (minute >= 60) {
                hour++;
                minute -= 60;
            }
            String strMin = minute > 10 ? String.valueOf(minute) : "0" + minute;
            return String.format("%s:%s", hour, strMin); // 여기서 주의; 17:0 이런식으로 반환되어서 문제 발생 가능성
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

    boolean isAvail(Schedule oldData, Schedule newData) {
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
            Room roomPtr;
            if (rooms.isEmpty()) {
                roomPtr = new Room(newSchedule);
                rooms.add(roomPtr);
            } else {
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
        }
        return rooms.size();
    }

    // 내림차순 정렬
    void sortByTerms(String[][] book_time) {
        Arrays.sort(book_time, (b1, b2) -> {
            int t1_start = Integer.parseInt(b1[0].substring(0, 2) + b1[0].substring(3));
            int t1_end = Integer.parseInt(b1[1].substring(0, 2) + b1[1].substring(3));
            int t2_start = Integer.parseInt(b2[0].substring(0, 2) + b2[0].substring(3));
            int t2_end = Integer.parseInt(b2[1].substring(0, 2) + b2[1].substring(3));
            return Integer.compare(Math.abs(t1_end - t1_start), Math.abs(t2_end - t2_start));
        });
    }

    public static void main(String[] args) {
        P_FindHotelRoom_F hotel = new P_FindHotelRoom_F();
        // String[][] book_time = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};
        String[][] bound_test = {{"15:00", "16:50"}, {"23:00", "23:50"}};

        System.out.println(hotel.solution(bound_test));
    }
}
