package baekjoon.problems.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 백준 실버5 요세푸스 문제
 * 1차 풀이 2021-09-21
 * 핵심 N명이 둥글게 있다고 할때 K번째 사람을 제거하고 이를 반복함
 * 제거된 사람들의 순열을 요세푸스 순열이라고 부른다.
 */
public class Josephus_1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        solution(N, K);

    }

    public static void solution(int N, int K) {
        ArrayList<Integer> arr = new ArrayList<>(N);
        ArrayList<Integer> result = new ArrayList<>(N);

        /*리스트 생성 */
        for (int i = 1; i <= N; i++) {
            arr.add(i);
        }
        int i = -1;
        int idx = 0;
        int temp = 0;
        while (arr.size() != 0) {
            idx = (i + K) % arr.size();
            temp = arr.get(idx);
            arr.remove(idx);
            result.add(temp);
            i = idx - 1;
        }
        System.out.print("<");
        for (int num : result) {
            if (num == result.get(N - 1)) {
                System.out.print(num);
            } else {
                System.out.print(num + ", ");
            }
        }
        System.out.println(">");
    }
}
