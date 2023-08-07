package category.queue;

import java.io.*;
import java.util.*;

public class SM_d3_1225_암호생성기 {

    static final int L = 8;
    static final int CYCLE = 6;

    static String solution(Queue<Integer> queue) {
        int INC = 1;
        while(true) {
            int next = queue.poll() - INC;

            if(next <= 0) {
                next = 0;
                queue.offer(next);
                break;
            }

            queue.offer(next);
            INC++;
            if(INC % CYCLE == 0) {
                INC = 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()) {
            sb.append(queue.poll()).append(" ");
        }

        return sb.toString();
    }


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/category/queue/input/input-d3-1225.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        int T = 10;
        while(T > 0) {
            int tc = Integer.parseInt(br.readLine());

            Queue<Integer> queue = new ArrayDeque<>(L);
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < L; i++) {
                queue.add(Integer.parseInt(st.nextToken()));
            }

            sb.append("#").append(tc).append(" ").append(solution(queue)).append("\n");
            T--;
        }

        System.out.print(sb);
        br.close();
    }
}
