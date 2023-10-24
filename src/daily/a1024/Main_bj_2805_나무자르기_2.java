package daily.a1024;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_bj_2805_나무자르기_2 {

    static int N, M;

    static int[] arr; // 나무 높이 저장

    // H의 최소 범위, 최대 범위로 조정해간다
    static final int START = 1;
    static final int END = 1_000_000_000;

    // 유효한 나무들 일단 정렬
    // 임의의 H를 이분 탐색 기법으로 설정
    // H가 자를 수 있는 나무가 있는지 검사
    // H 조정 반복
    // 주어진 문제; 적어도 M미터의 나무를 가져가는 최대 H를 반환하기
    static void solution() {
        Arrays.sort(arr);
        System.out.println(iter_search(START, END));
    }

    static int iter_search(int from, int to) {
        while (from <= to) {
            int h = (from + to) / 2;
            int idx = tree_binarySearch(h);
            long count = getCount(h, idx);
            // 조건(M)보다 적은 나무 길이를 얻는 경우
            // 조건 불만족
            // h를 줄이도록 작은 범위에 대해 탐색한다
            if (count < M) {
                to = h - 1;
            }
            // M보다 크거나 같은 나무 길이를 얻는 경우
            // h를 최대한 높이도록 더 큰 범위에 대해 탐색한다.
            else {
                from = h + 1;
            }
        }

        return to;
    }

    static int tree_binarySearch(int h) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (h == arr[mid]) {
                return mid;
            } else if (h < arr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        // not found; 유효한 나무가 없는 H인 경우
        return -(start + 1);
    }

    // arr[idx] >= h; 인 경우가 전달됨
    static long getCount(int h, int idx) {
        final int L = arr.length;
        int i = 0;
        if (idx < 0) {
            i = Math.abs(idx) - 1;
        } else {
            i = idx;
        }

        long sum = 0;
        for (; i < L; i++) {
            // arr[i] > h일 때만 수행
            if(arr[i] > h) {
                sum += arr[i] - h;
            }
        }

        return sum;
    }

    public static void main(String[] args) throws Exception {
        // int T = 4;
        // System.setIn(new FileInputStream("src/daily/a1024/input_2805_ex0" + tc + ".txt"));
        // System.setIn(new FileInputStream("src/daily/a1024/input_2805_ex04.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solution();
        br.close();
    }

}