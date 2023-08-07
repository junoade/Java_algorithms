package category.array;

import java.io.*;
import java.util.*;

public class Main_boj_1158_요세푸스문제 {

    static void solution(int N, int K) {
        List<Integer> list = new ArrayList<>(N);
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            list.add(i);
        }

        sb.append("<");

        int idx = -1;
        int next = 0;
        while(!list.isEmpty()) {
            next = (idx + K) % list.size(); // 0'based라
            int removedValue = list.remove(next);

            sb.append(removedValue);
            if(list.size() > 0) {
                sb.append(", ");
            }

            idx = next - 1;
        }

        sb.append(">");

        System.out.println(sb);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        solution(N, K);
        br.close();
    }
}
