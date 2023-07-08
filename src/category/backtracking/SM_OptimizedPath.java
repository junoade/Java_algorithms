package category.backtracking;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SM_OptimizedPath {
    static class Node {
        int x;
        int y;
        boolean visited;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.visited = false;
        }
    }

    static int min;
    static int N;
    // static int rec_cnt;

    // 2개씩 추출함
    static int solution(int n, int[] inputs) {
        List<Node> list = init(inputs);
        Node start = list.get(0);
        Node end = list.get(1);
        list.remove(0); //
        list.remove(0);

        min = Integer.MAX_VALUE;
        N = n;

        rec(start, end, list, 0, 0);
        // System.out.println(rec_cnt);
        return min;
    }

    // 백트랙킹 구현
    static void rec(Node start, Node end, List<Node> list, int total, int depth) {
        // rec_cnt++;
        // base case : 끝에 도달
        // 현재까지 사용한 비용을 반환
        if (depth == N) {
            min = Math.min(min, total + calculate(start, end));
            // System.out.println(min);
            return;
        }

        // general case
        for (Node other : list) {
            /*if (start.x == other.x && start.y == other.y) { continue; }*/

            if(other.visited) {
                continue;
            }

            int distance = calculate(start, other);
            other.visited = true;
            rec(other, end, list, total + distance, depth + 1);
            other.visited = false;
        }
    }

    static int calculate(Node start, Node end) {
        return Math.abs(start.x - end.x) + Math.abs(start.y - end.y);
    }

    static List<Node> init(int[] inputs) {
        List<Node> result = new ArrayList<>();
        for (int i = 0; i < inputs.length; i += 2) {
            result.add(new Node(inputs[i], inputs[i + 1]));
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.printf("#%d %d\n", t + 1, solution(n, inputs));
        }
    }
}
