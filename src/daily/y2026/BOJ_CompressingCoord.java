package daily.y2026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_CompressingCoord {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 1. 중복 제거 후 정렬
        int[] sorted = Arrays.stream(arr).distinct().sorted().toArray();

        // 2. key-value 자료구조에 좌표압축 값을 저장
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < sorted.length; i++) {
            map.put(sorted[i], i);
        }

        // 3. 출력
        StringBuilder sb = new StringBuilder();
        for (int v : arr) {
            sb.append(map.get(v));
        }
        System.out.println(sb);
    }
}
