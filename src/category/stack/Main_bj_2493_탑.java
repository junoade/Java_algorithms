package category.stack;

import java.io.*;
import java.util.*;

public class Main_bj_2493_탑 {
    static class Pos {
        int idx;
        int value;

        public Pos(int idx, int value) {
            super();
            this.idx = idx;
            this.value = value;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Pos [idx=").append(idx).append(", value=").append(value).append("]");
            return builder.toString();
        }
    }

    static void solution(Pos[] a) {

        final int L = a.length;
        int[] answer = new int[L + 1];

        ArrayDeque<Pos> stack = new ArrayDeque<>();
        // stack.push(a[0]);

        for(int i = 0; i < L; i++) {
            Pos cursor = a[i];

            // 높이 맞춰주기
            while(!stack.isEmpty() && stack.peek().value < cursor.value) {
                stack.pop();
            }

            if(stack.isEmpty()) {
                answer[cursor.idx] = 0;
            } else {
                answer[cursor.idx] = stack.peek().idx;
            }
            stack.push(cursor);

        }

        // 이제 출력
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < answer.length; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int L = Integer.parseInt(br.readLine());

        Pos[] inputs = new Pos[L];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < L; i++) {
            inputs[i] = new Pos(i + 1, Integer.parseInt(st.nextToken()));
        }

        solution(inputs);
    }

}
