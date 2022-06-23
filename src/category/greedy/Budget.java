package category.greedy;

/**
 * <title>예산</title>
 * <a>https://programmers.co.kr/learn/courses/30/lessons/12982</a>
 * @idea
 * - 핵심은 최대한 많은 부서에게 예산을 배정하는것, 그리디 알고리즘
 * 1. 오름차순 정렬 (중요)
 * 2. budget - d[i] < 0 조건에서만 예산 배정하기
 * 3. 배정 받은 부서의 개수를 counting 하다가 max로 swap 해주기
 */
public class Budget {
    public static int solution(int[] d, int budget) {
        int answer = 0;
        // O(N^2) 버블 소트, 오름차순 정렬
        for(int i = 0; i < d.length-1; i++){
            for(int j = i+1; j<d.length; j++){
                if(d[i] > d[j]){
                    int temp = d[i];
                    d[i] = d[j];
                    d[j] = temp;
                }
            }
        }

        // Greedy 알고리즘
        for(int i = 0; i<d.length; i++){
            int cnt = 0;
            for(int j = i; j<d.length; j++){
                if(budget - d[j] >= 0){
                    budget -= d[j];
                    cnt++;
                }else
                    continue;
                if(answer < cnt)
                    answer = cnt;
            }
        }
        return answer;
    }
}
