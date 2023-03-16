package category.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class P_MakeStar {
    ArrayList<Long> pointX;
    ArrayList<Long> pointY;

    public String[] solution(int[][] line) {
        pointX = new ArrayList<>();
        pointY = new ArrayList<>();

        /* step1) 정수인 교점의 집합 구하기 O(N^2) */
        for (int i = 0; i < line.length; i++) {
            for (int j = i; j < line.length; j++) {
                double[] point = getPoint(line[i], line[j]);

                /* step2) 정수인 교점만 저장하도록 */
                if (isInteger(point)) {
                    long[] temp = Arrays.stream(point).mapToLong(e -> (long) e).toArray();
                    pointX.add(temp[0]);
                    pointY.add(temp[1]);
                }
            }
        }

        /* step3) 정수인 좌표들의 최대 최소 위치 확인하여 최소 사각형 구하기 */
        long x_min = Collections.min(pointX);
        long x_max = Collections.max(pointX);
        long y_min = Collections.min(pointY);
        long y_max = Collections.max(pointY);

        /* step4) 결과 출력 */
        final int X = (int) Math.abs(x_max - x_min) + 1;
        final int Y = (int) Math.abs(y_max - y_min) + 1;
        String[] answer = new String[Y];

        for (int i = 0; i < Y; i++) {
            answer[i] = ".".repeat(X);
        }

        for (int i = 0; i < pointX.size(); i++) {
            int newX = (int) Math.abs(pointX.get(i) + x_min * (-1));
            int newY = (int) Math.abs(pointY.get(i) + y_max * (-1));
            String tempRow = answer[newY];
            answer[newY] = replace(tempRow, newX, '*');
        }

        return answer;
    }


    /* 두 직선의 방정식의 교점의 좌표를 구해 반환*/
    double[] getPoint(int[] l1, int[] l2) {
        double[] result = new double[2];
        long a = l1[0], b = l1[1], e = l1[2];
        long c = l2[0], d = l2[1], f = l2[2];
        result[0] = (double) (b * f - e * d) / (a * d - b * c);
        result[1] = (double) (e * c - a * f) / (a * d - b * c);
        return result;
    }

    boolean isInteger(double[] point) {
        for (double p : point) {
            if ((p % 1) != 0.0) { // 정수형인가? 아닌가?
                return false;
            }
        }
        return true;
    }

    String replace(String src, int idx, char newChar) {
        char[] arr = src.toCharArray();
        arr[idx] = newChar;
        return new String(arr);
    }

    /* main 메소드 테스트 */
    public static void main(String[] args) {
        P_MakeStar p_makeStar = new P_MakeStar();
        int[][] line = {{2, -1, 4}, {-2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}};
        int[][] boundTest = {{100000, 100000, 100000}, {999999, 999999, 999999}};

        p_makeStar.solution(boundTest);
    }
}
