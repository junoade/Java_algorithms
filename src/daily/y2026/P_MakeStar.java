package daily.y2026;

import java.util.*;

public class P_MakeStar {

    List<Long> posX;
    List<Long> posY;

    public String[] solution(int[][] lines) {
        posX = new ArrayList<>();
        posY = new ArrayList<>();

        // 1) 교점 구하기
        for (int i = 0; i < lines.length - 1; i++) {
            for (int j = i + 1; j < lines.length; j++) {
                int[] a = lines[i];
                int[] b = lines[j];

                if(!hasCommon(a, b))
                    continue;

                double[] matches = getCommon(a, b);
                if(isInteger(matches)) {
                    posX.add((long) matches[0]);
                    posY.add((long) matches[1]);
                }
            }
        }

        // 2. 교점이 그려질 배열 크기 구하기
        long x_min = Collections.min(posX);
        long x_max = Collections.max(posX);
        long y_min = Collections.min(posY);
        long y_max = Collections.max(posY);

        int X_LENGTH = (int) (Math.abs(x_max - x_min) + 1);
        int Y_LENGTH = (int) (Math.abs(y_max - y_min) + 1);


        // 3. 교점 그리기
        char[][] grid = new char[Y_LENGTH][X_LENGTH];
        for(char[] row : grid) Arrays.fill(row, '.');

        for(int i = 0; i < posX.size(); i++) {
            int nx = (int) Math.abs(posX.get(i) + x_min * (-1)); // x=0으로 이동하도록
            int ny = (int) Math.abs(posY.get(i) + y_max * (-1)); // y=0으로 이동하도록 가장 높은 점이 0행이 되도록
            grid[ny][nx] = '*';
        }

        String[] answer = new String[Y_LENGTH];
        for(int i = 0; i < Y_LENGTH; i++) {
            answer[i] = new String(grid[i]);
        }

        return answer;
    }

    boolean hasCommon(int[] x, int[] y) {
        long A = x[0], B = x[1];
        long C = y[0], D = y[1];
        return (A * D - B * C) != 0;
    }

    double[] getCommon(int[] x, int[] y) {
        double[] answer = new double[2];
        long a1 = x[0], b1 = x[1], c1 = x[2];
        long a2 = y[0], b2 = y[1], c2 = y[2];

        answer[0] = (double) (b1 * c2 - b2 * c1) / (a1 * b2 - a2 * b1);
        answer[1] = (double) (a1 * c2 - a2 * c1) / (a2 * b1 - a1 * b2);

        return answer;
    }

    boolean isInteger(double[] pos) {
        for (double d : pos) {
            if (d % 1 != 0.0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        P_MakeStar p = new P_MakeStar();

        int[][] testInput = {
                {2, -1, 4}, {-2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}
        };
        System.out.println(Arrays.toString(p.solution(testInput)));
    }
}
