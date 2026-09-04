package daily.y2026.study;

import java.io.*;

public class Study_RoomsAssigned {

    /**
     * <a href="https://jungol.co.kr/contest/4303/problem/8">문제링크</a>
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] temp = br.readLine().split(" ");

        int[] rooms = new int[3];

        for(int i = 0; i < rooms.length; i++) {
            rooms[i] = Integer.parseInt(temp[i]);
        }

        final int N = Integer.parseInt(temp[rooms.length]);
        Boolean[] memo = new Boolean[N + 1]; // null은 아직 계산을 위해 탐색 안한 경우.

        if(recursive(rooms, memo, N)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }


    }

    static boolean recursive(int[] rooms, Boolean[] memo, int left) {
        // base case
        if(left == 0) {
            return true;
        }
        if (left < 0) {
            return false;
        }
        if(memo[left] != null) return memo[left];

        for(int i = 0; i < rooms.length; i++) {

            if(recursive(rooms, memo, left - rooms[i])) {
                return memo[left] = true;
            }
        }

        return memo[left] = false;
    }
}
