package category.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * --------------------------------------------------------------<br/>
 * <b> 터렛 백준 1002</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * <br/> 두 원이 만나는지 안만나는지, 만난다면 외접으로? 내접으로? 무수히 많은 접점이 있는지?
 * <br/> if-else if 구문 순서 잘 따져야 함
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * <br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  14300KB / 256MB , 실행시간  136ms / 2000ms<br/>
 * --------------------------------------------------------------
 */
public class B_Turret {
    static class Point {
        int x;
        int y;
        int r;

        Point(int[] inputs) {
            if (inputs.length == 3) {
                x = inputs[0];
                y = inputs[1];
                r = inputs[2];
            }
        }
    }

    static int T;
    static Point pointA;
    static Point pointB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        initTestcase(br);
        for (int i = 0; i < T; i++) {
            initPoint(br);
            System.out.println(calculate());
        }
    }

    static void initTestcase(BufferedReader br) throws IOException {
        T = Integer.parseInt(br.readLine());
    }

    static void initPoint(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int SIZE = 3;
        int[] inputs = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }
        pointA = new Point(inputs);

        for (int i = 0; i < SIZE; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }
        pointB = new Point(inputs);
    }

    static int calculate() {
        double d = Math.sqrt(Math.pow(pointA.x - pointB.x, 2) + Math.pow(pointA.y - pointB.y, 2));
        int result = -1;

        boolean meetTwoPoints = d < pointA.r + pointB.r;
        boolean meetOnePointInside = d == Math.abs(pointA.r - pointB.r);
        boolean meetOnePointOutside = d == pointA.r + pointB.r;

        boolean meetZeroInside = d < Math.abs(pointA.r - pointB.r);
        boolean meetZeroOutside = d > pointA.r + pointB.r;
        boolean infMeets = d == 0 && pointA.r == pointB.r;

        // 무수히 많은 경우부터
        if (infMeets) {
            result = -1;
        } // 두 원이 만나지 않는 경우부터 반례) (1,1,1), (1,1,5) 때문에
        else if (meetZeroInside || meetZeroOutside) {
            result = 0;
        } else if (meetOnePointInside || meetOnePointOutside) {
            result = 1;
        } else if (meetTwoPoints) {
            result = 2;
        }
        return result;
    }
}
