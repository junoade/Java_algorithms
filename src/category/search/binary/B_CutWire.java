package category.search.binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * --------------------------------------------------------------<br/>
 * <b>백준 - 랜선 자르기 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 1 ~ 2^32 - 1 범위의 새로운 랜선 길이(d)로 기존 랜선 K개를 분리할 때 (소수점 버림)<br/>
 * 그 갯수가 N개 이상인 조건 중 가장 최대 d를 구하라 <br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 범위가 일단 크고 결정 조건을 만족하는 요소 중 가장 큰 위치에 있는 경우를 반환하도록 요구<br/>
 * [1, 2^32-1]에서 d를 가정하고<br/>
 * 그 d가 결정 조건을 만족하는 지 판별 후 만족하면 d를 포함해서 높여주고<br/>
 * 만족하지 않으면 d를 포함해서 내려줌<br/>
 * -> 결정 조건을 만족하는 경우가 연속적이지 않을 수 있음<br/>
 * -> 어떤 경우엔 위로 아래로 이동해야할 경우가 있을 수 있어서 d를 포함<br/>
 *
 * **결정 조건** : 기대하는 랜선 길이(d)로 K개의 기존 랜선들을 자르고 그 갯수가 N보다 크거나 같은지 비교<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리 18020KB / 128MB , 실행시간  196ms / 2000ms<br/>
 * --------------------------------------------------------------
 */
public class B_CutWire {
    // K : 초기 랜선의 개수 in [1, 10,000], N : 새로운 랜선의 개수 in [1, 1,000,000]
    static void solution(int[] wires, int K, int N){
        long start = 1;
        long end = Integer.MAX_VALUE + 1L; // exclusive

        while(end - start > 1) {
            long d = (start + end) / 2;

            // 결정 조건
            // 결정 조건이 연속한다는 보장은 없다
            boolean cond = isPossible(wires, d, N);

            if(cond) {
                // 결정 조건을 만족하는 최대 d를 찾기 위해
                // 더 큰 d를 가정하고 검사하도록
                start = d;
            } else {
                // 더 작은 d의 값을 가정하고 검사하도록
                end = d;
            }
        }

        // 해당 범위에 d는 반드시 존재한다 // [F, F]는 없다
        System.out.println(start);
    }

    // 기대하는 랜선의 길이(expected)로
    // 주어진 랜선들을 자르고 그 개수를 셀 때
    // N보다 크거나 같은지 판별
    static boolean isPossible(int[] wires, long expected, int N) {
        int count = 0;
        for(int wire : wires) {
            count += wire / expected;
        }

        return count >= N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int K = inputs[0];
        int N = inputs[1];

        int[] wires = new int[K];
        for(int i = 0; i < K; i++) {
            wires[i] = Integer.parseInt(br.readLine());
        }

        solution(wires, K, N);
    }
}
