package category.array;

/**
 * --------------------------------------------------------------<br/>
 * <b> 프로그래머스 행렬의 곱셈 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * <br/> 행렬의 곱셈 개념을 코드로 구현할 수 있는가?
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * <br/> arr2의 열을 기준으로 arr1의 성분을 순회하면서 arr2의 행 인덱스를 적절히 늘려주는 식으로 행렬의 곱셈 구현
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 정확성 100점 16/16 <br/>
 * --------------------------------------------------------------
 */
public class P_MatrixMultiply {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int m1 = arr1.length;
        int n1 = arr1[0].length; // 열의 길이가 일정하다고 가정

        int m2 = arr2.length;
        int n2 = arr2[0].length; // 열의 길이가 일정하다고 가정

        if(n1 != m2) {
            return null;
        }

        int[][] answer = new int[m1][n2];

        // arr2의 열을 기준으로
        for(int c2 = 0; c2 < n2; c2++) {
            for(int r1 = 0; r1 < m1; r1++) {
                int sum = 0;
                int r2 = 0;
                for(int c1 = 0; c1 < n1; c1++) {
                    int value1 = arr1[r1][c1];
                    if(r2 < m2) {
                        int value2 = arr2[r2++][c2];
                        sum += value1 * value2;
                    }
                }
                answer[r1][c2] = sum;
            }
        }

        return answer;
    }
}
