package category.recursive;

import java.io.*;
import java.util.Arrays;

public class B_CutPapers {

    static int[][] maps;

    static int whitePaper; // 0으로 묶인 갯수
    static int bluePaper; // 1로 묶인 갯수

    static void solution() {
        rec(0, 0, maps.length);
    }

    // 시작위치부터 주어진 크기만큼 탐색 수행
    // x, y 초기 시작 위치
    static void rec(int x, int y, int L) {

        // base case : L=1 인 경우
        if(L == 1) {
            mark(maps[x][y]);
            return;
        }


        // base case : 모두 동일한 경우
        boolean notSame = false;
        int firstValue = maps[x][y];
        for(int i = x; i < x + L; i++) {
            if(notSame) {
                break;
            }
            for(int j = y; j < y + L; j++) {
                if(firstValue != maps[i][j]) {
                    notSame = true;
                    break;
                }
            }
        }
        // 모두 다르면?
        if(notSame) {
            // 1사분면 탐색
            int newSize = L / 2;
            rec(x, y, newSize);
            // 2사분면 탐색
            rec(x, y + newSize, newSize);
            // 3사분면 탐색
            rec(x + newSize, y, newSize);
            // 4사분면 탐색
            rec(x + newSize, y + newSize, newSize);
        }
        // 모두 같으면?
        else {
            mark(firstValue);
            return;
        }
    }

    static void mark(int value) {
        if(value == 1) {
            bluePaper++;
        } else {
            whitePaper++;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        maps = new int[N][N];
        for(int i = 0; i < N; i++) {
            maps[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        whitePaper = 0;
        bluePaper = 0;

        solution();
        System.out.println(whitePaper);
        System.out.println(bluePaper);
    }
}
