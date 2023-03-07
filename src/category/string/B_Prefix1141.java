package category.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * --------------------------------------------------------------<br/>
 * <b> 백준 1141 접두사가 아닌 원소들의 최대 부분 집합 크기 구하기</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * <br/> 길이로 오름차순 정렬하여 단어의 길이가 작은 문자열부터 접두사 처리를 진행
 * <br/> prefix 문자열로써 순회하되 자신보다 길이가 긴 타겟 문자열을 대상으로 String.startsWith() 메소드 이용하기
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 1. 처음엔 모든 부분 접두사까지 Set으로 만들었는데, 불필요한 공간 낭비였음
 * 2. 부분 집합 용어 때문에 문제를 잘못 이해해서 어떻게 최대 부분 집합의 크기를 구하는 지 제대로 이해하지 못 했었음
 *
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  / 256MB , 실행시간  / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class B_Prefix1141 {
    static ArrayList<String> inputs;

    static void processInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        inputs = new ArrayList<>(N);
        for(int i = 0; i < N; i++) {
            inputs.add(br.readLine());
        }
    }

    static int findSubset() {
        // 입력으로 받은 문자들의 리스트 길이순으로 정렬
        inputs.sort(Comparator.comparingInt(String::length));
        int result = 0;

        // O(50*50*L)
        int length = inputs.size();
        for(int i = 0; i < length; i++) {
            boolean isFound = false;
            String prefix = inputs.get(i);
            for(int j = i+1; j < length; j++) {
                String target = inputs.get(j);
                if(target.startsWith(prefix)) { // target.matches(prefix+"\\w*"); -> 212ms
                    isFound = true;
                    break;
                }
            }

            if(!isFound) {
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        processInput();
        System.out.println(findSubset());
    }
}
