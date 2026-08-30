package daily.y2026.study;

import java.io.*;

public class Study_ColorPaper {

    // 백준 2563 색종이
    // https://jungol.co.kr/contest/4303/problem/7?cursor=ImNfNDMwMyIsMCw2

    // 예외케이스 : 현재는 2개 색종이에 대해 포함배제 원리를 적용했는데 3개 4개... 등 교집합이 생길 수 있음.


//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        final int N = Integer.parseInt(br.readLine());
//        final int MAX_L = 100;
//        boolean[][] v = new boolean[MAX_L + 1][MAX_L + 1];
//
//
//        for(int i = 0; i < N; i++) {
//            String[] temp = br.readLine().split(" ");
//            arr[i][0] = Integer.parseInt(temp[0]);
//            arr[i][1] = Integer.parseInt(temp[1]);
//        }
//
//        int areaSum = N * 100;
//        for(int i = 0; i < N; i++) {
//            for(int j = i + 1; j < N; j++) {
//                int[] aPos = arr[i], bPos = arr[j];
//                int elapsedArea = getElaspedArea(aPos[0], aPos[1], bPos[0], bPos[1]);
//                areaSum -= elapsedArea;
//            }
//        }
//
//        System.out.println(areaSum);
//    }
//
//
//    static int getElaspedArea(int x1, int y1, int x2, int y2) {
//        // x2 <= x1 일때 x2+10 - x1 >= 0?
//        // x2 > x1 일때 x1+10 - x2 >= 0?
//        int nx = 0, ny = 0, result = 0;
//        if(x2 <= x1 && (x2+10 - x1) >= 0 ) {
//            nx = x2 + 10 - x1;
//        } else if(x2 > x1 && (x1 + 10 - x2) >= 0) {
//            nx = x1 + 10 - x2;
//        }
//
//        // y도 마찬가지
//        if(y1 >= y2 && (y2 + 10 - y1) >=0 ) {
//            ny = y2 + 10 - y1;
//        } else if(y1 < y2 && (y1 + 10 - y2) >= 0) {
//            ny = y1 + 10 - y2;
//        }
//
//        if(nx > 0 && ny > 0 ) {
//            result = nx * ny;
//        }
//
//        return result;
//    }

    static final int MAX_L = 100;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(br.readLine());
        v = new boolean[MAX_L][MAX_L];

        for(int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            int x = Integer.parseInt(temp[0]);
            int y = Integer.parseInt(temp[1]);
            mark(x, y);
        }

        int answer = calculate();
        System.out.println(answer);
    }

    static void mark(int x, int y) {
        if(isOutbound(x, y)) {
            return;
        }

        for(int nx = x; nx < x + 10; nx++) {
            for(int ny = y; ny < y + 10; ny++) {
                if(isOutbound(nx, ny) || v[nx][ny] == true) {
                    continue;
                }
                v[nx][ny] = true;
            }
        }
    }

    static int calculate() {
        int sum = 0;
        for(int i = 0; i < v.length; i++) {
            for(int j = 0; j < v[i].length; j++) {
                if(v[i][j]) {
                    sum++;
                }
            }
        }
        return sum;
    }

    static boolean isOutbound(int x, int y) {
        return (x < 0 || x >= MAX_L) || (y < 0 || y >= MAX_L);
    }
}
