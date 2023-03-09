package category.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_CircularQueue1021 {

    static int[] init_idx;
    static final int startIdx = 1;
    static int N;
    static int M;
    static int cursorIdx = 0;

    static void processInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        init_idx = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            init_idx[i] = Integer.parseInt(st.nextToken());
        }
    }

    static int findMinCost() {
        int leftResult = 0, rightResult = 0;

        for (int idx : init_idx) {
            int leftDistance = Math.abs(idx - startIdx);
            int rightDistance = Math.abs(idx - N) + 1;
            int compResult = Integer.compare(leftDistance, rightDistance);

            if (compResult < 0) {
                goLeft(leftDistance);
                leftResult += leftDistance;
            } else if (compResult > 0) {
                goRight(rightDistance);
                rightResult += rightDistance;
            } else {
                if (leftResult > rightResult) {
                    goRight(rightDistance);
                    rightResult += rightDistance;
                } else {
                    goLeft(leftDistance);
                    leftResult += leftDistance;
                }
            }

            cursorIdx++;
            /* pop operation */
            N--;
            relocateIdx();
        }

        return leftResult + rightResult;
    }

    static void goLeft(int distance) {
        for (int idx = cursorIdx; idx < M; idx++) {
            init_idx[idx] = update(true, init_idx[idx], distance);
        }
    }

    static void goRight(int distance) {
        for (int idx = cursorIdx; idx < M; idx++) {
            init_idx[idx] = update(false, init_idx[idx], distance);
        }
    }

    static int update(boolean isLeftFlag, int value, int distance) {
        int temp = 0;
        if (isLeftFlag) {
            temp = value - distance;
            if (temp < startIdx) {
                temp = N + temp; // 이 때 temp는 음수
            }
        } else {
            temp = value + distance;
            if (temp > N) {
                temp = temp - N;
            }
        }
        return temp;
    }

    static void relocateIdx() {
        for (int i = 0; i < M; i++) {
            if (init_idx[i] > 1)
                init_idx[i]--;
        }
    }

    public static void main(String[] args) throws IOException {
        processInput();
        System.out.println(findMinCost());
    }
}
