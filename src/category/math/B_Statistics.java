package category.math;

import java.io.*;
import java.util.*;

/**
 * --------------------------------------------------------------<br/>
 * <b>백준 2108 - 통계학 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 주어진 문제 조건대로 구현<br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 1. 산술 평균 구하기 : 형식 지정자 이용해서 반올림 구현한다음 int로 형변환<br/>
 * - 바로 형변환 하면 그냥 버림이라 안될 듯
 * 2. 중앙값 출력 : deepCopy 한 다음에 정렬 후 mid 값 반환; 홀수라 크게 신경 쓸 건 없었다.
 * 3. 최빈값 출력 : deepCopy 한 다음 2가지 기준으로 정렬;
 * 4. 범위 계산 : deepCopy 한 다음 정렬 후 최대, 최소 차이 반환;
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  / 256MB , 실행시간  / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class B_Statistics {

    static void solution(int[] inputs) {
        // 산술 평균 값 계산 후 출력
        calculateAvg(inputs);

        // 중앙값 출력
        calculateMid(inputs);

        // 최빈값 출력 - 최빈값 중 두 번째로 작은 값
        calculateMost(inputs);

        // 범위 계산 - abs(최대값 - 최소값)
        calculateRange(inputs);
    }

    static void calculateAvg(int[] a) {
        int sum = 0;
        for (int num : a) {
            sum += num;
        }
        String answer = String.format("%.0f", (double) sum / a.length);
        System.out.println(Integer.parseInt(answer));
    }

    static void calculateMid(int[] a) {
        int[] copy = Arrays.copyOf(a, a.length);
        Arrays.sort(copy);
        int mid = (copy.length - 1) / 2;
        int answer = copy[mid];
        System.out.println(answer);
    }

    static void calculateMost(int[] a) {
        final int L = a.length;
        // 배열의 요소는 해당 숫자가 불린 갯수
        Map<Integer, Integer> freqs = new HashMap<>();
        for(int num : a) {
            if(freqs.containsKey(num)) {
                freqs.put(num, freqs.get(num) + 1);
            } else {
                freqs.put(num, 1);
            }
        }

        // values 기준으로 정렬
        Integer[] keys = freqs.keySet().toArray(Integer[]::new);

        // 최빈수에 대해선 내림차순 정렬
        // 키에 대해서는 오름차순 정렬
        Arrays.sort(keys, (k1, k2) -> {
            if (!Objects.equals(freqs.get(k2), freqs.get(k1))) {
                return Integer.compare(freqs.get(k2), freqs.get(k1));
            }
            return Integer.compare(k1, k2); // 키에 대해서는 오름차순 정렬
        });

        int answer = 0;

        if(keys.length > 1) {
            if (!Objects.equals(freqs.get(keys[0]), freqs.get(keys[1]))) {
                answer = keys[0];
            } else {
                // 두번째 값을 갖는 키를 찾음
                for(int i = 0; i < keys.length - 1; i++) {
                    if(!Objects.equals(keys[i], keys[i + 1])) {
                        answer = keys[i + 1];
                        break;
                    }
                }
            }

        } else {
            answer = keys[0];
        }

        System.out.println(answer);
    }

    static void calculateRange(int[] a) {
        int[] copy = Arrays.copyOf(a, a.length);
        Arrays.sort(copy);
        int min = copy[0];
        int max = copy[copy.length - 1];
        System.out.println(Math.abs(max - min));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] inputs = new int[N];

        for (int i = 0; i < N; i++) {
            inputs[i] = Integer.parseInt(br.readLine());
        }

        solution(inputs);
    }
}
