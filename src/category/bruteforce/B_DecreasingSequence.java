package category.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class B_DecreasingSequence {
    static final int N = 1_000_000;

    //
    static int solution(int idx) {
        List<Integer> list = makeList();

        if (idx >= list.size() || idx <= 0) {
            return -1;
        }

        return list.get(idx - 1);
    }

    static List<Integer> makeList() {
        List<Integer> list = new ArrayList<>();

        for (int n = 0; n <= N; n++) {
            if (isValid(n)) {
                list.add(n);
            }
        }
        return list;
    }

    static boolean isValid(int n) {
        char[] arr = String.valueOf(n).toCharArray();
        if (arr.length == 1) {
            return true;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            int prev = arr[i] - '0', next = arr[i + 1] - '0';
            // 주어진 조건과 다를 때
            if (prev <= next) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int idx = Integer.parseInt(br.readLine());
        System.out.println(solution(idx));
    }
}
