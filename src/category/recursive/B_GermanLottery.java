package category.recursive;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class B_GermanLottery {
    static ArrayList<String> dict;
    static final int initR = 6;
    static void combination(int[] arr, boolean[] visited, int depth, int n, int r) {
        // base case #1: 더 이상 뽑을 수 없을 때
        if(r == 0) {
            appendToDict(arr, visited, n);
            return;
        }

        // base case #2: 배열의 최대 크기까지 탐색을 마쳤을 때
        if(depth == n) {
            return;
        }

        // general case: depth 방문 후, 다음 조합 재귀 호출 하고 돌아 와서
        visited[depth] = true;
        combination(arr, visited, depth+1, n, r-1);
        // 백트랙킹 할 수 있도록 false로 바꿔줌
        visited[depth] = false;
        combination(arr, visited, depth+1, n, r);
    }

    static void appendToDict(int[] arr, boolean[] visited, int n) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            if(visited[i]) {
                sb.append(arr[i]).append(" ");
            }
        }
        sb.deleteCharAt(sb.length() - 1); // 마지막 공백 제거
        dict.add(sb.toString());
    }

    static void printDict() {
        // dict.sort(Comparator.naturalOrder()); // 주의 1 2 13 3 처럼 두자리 숫자
        for(String s : dict) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            String[] inputs = br.readLine().split(" ");
            int n = Integer.parseInt(inputs[0]);
            if(n == 0) {
                break;
            }

            dict = new ArrayList<>();
            // List<Integer> list = Arrays.stream(input.substring(2).split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
            // list.sort(Comparator.naturalOrder());
            int[] arr = Arrays.stream(inputs)
                    .skip(1)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            boolean[] visited = new boolean[n];
            combination(arr, visited, 0, n, initR);
            printDict();
            System.out.println();
        } while(true);
    }
}
