package category.hash;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * --------------------------------------------------------------
 * <title> 수 찾기, 백준 1920</title>
 * 유형 - 이진 탐색을 이용한 풀이, 해시셋을 이용한 풀이
 * 복습 - Yes
 * --------------------------------------------------------------
 * <b> 주요 키포인트 </b>
 * - 해시셋을 이용하여 해시값을 바탕으로 이미 해시셋에 포함된 수인지 판단, O(1)
 * --------------------------------------------------------------
 * <b> 나의 풀이 </b>
 * --------------------------------------------------------------
 * <b> 채점 </b>
 * 메모리 50928KB/128MB, 시간 632ms/1000ms
 * - 다른 사람 풀이 188ms, 240ms, 308ms(직접 버퍼를 활용해서 입력하는 경우 구현)
 * --------------------------------------------------------------
 */
public class Find_Number_1920 {
    static void solution(Integer[] arr1, Integer[] arr2){
        // Arrays.stream(arr1).boxed().toArray(Integer[]::new);
        HashSet<Integer> set = new HashSet<Integer>(Arrays.asList(arr1));
        StringBuilder sb = new StringBuilder();

        for (Integer integer : arr2) {
            if (set.contains(integer))
                sb.append("1\n");
            else
                sb.append("0\n");
        }
        System.out.print(sb);

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Integer[] arr1 = new Integer[N];
        int i = 0;
        for(String s : br.readLine().split(" ")){
            arr1[i] = Integer.parseInt(s);
            i++;
        }
        int M = Integer.parseInt(br.readLine());
        Integer[] arr2 = new Integer[M];
        i = 0;
        for(String s : br.readLine().split(" ")){
            arr2[i] = Integer.parseInt(s);
            i++;
        }
        solution(arr1, arr2);
    }
}
