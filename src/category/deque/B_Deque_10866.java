package category.deque;

import java.io.*;
import java.util.*;

public class B_Deque_10866 {

    enum Op {
        push_front,
        push_back,
        pop_front,
        pop_back,
        size,
        empty,
        front,
        back
    }

    static Deque<Integer> deque;

    static void updateDeque(String op, int key) {

        if (key == -1) {
            if (op.equals(Op.front.toString())) {
                if (deque.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(deque.getFirst());
                }
            } else if (op.equals(Op.back.toString())) {
                if (deque.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(deque.getLast());
                }
            } else if (op.equals(Op.empty.toString())) {
                if (deque.isEmpty()) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            } else if (op.equals(Op.size.toString())) {
                System.out.println(deque.size());
            } else if (op.equals(Op.pop_front.toString())) {
                if (deque.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(deque.removeFirst());
                }
            } else if (op.equals(Op.pop_back.toString())) {
                if (deque.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(deque.removeLast());
                }
            }
            return;
        }

        if (op.equals(Op.push_front.toString())) {
            deque.addFirst(key);
        } else if (op.equals(Op.push_back.toString())) {
            deque.add(key);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        deque = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            int key = -1;
            if (st.hasMoreTokens()) {
                key = Integer.parseInt(st.nextToken());
            }
            updateDeque(op, key);
        }

    }
}
