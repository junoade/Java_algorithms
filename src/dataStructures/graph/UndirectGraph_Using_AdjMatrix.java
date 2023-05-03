package dataStructures.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class UndirectGraph_Using_AdjMatrix {
    final int MAX = 8;
    int[][] graph;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        graph = new int[MAX][MAX];

        for(int i = 0; i < T; i++) {
            int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int startIdx = inputs[0];
            int endIdx = inputs[1];

            // 무방향 그래프 이므로
            graph[startIdx][endIdx] = 1;
            graph[endIdx][startIdx] = 1;
        }

        print();
    }

    private void print() {
        for(int[] row : graph) {
            for(int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }
}
