package category.string;

/**
 * --------------------------------------------------------------<br/>
 * <b> 숫자 짝꿍 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * <br/> 시간 복잡도 O(N^2)가 아닌 O(k*N)으로 풀기
 * - 그렇게 하기 위해, 문자열이 0~9로 이뤄진 문자들로 이뤄진 다는 점에서 숫자 기록표를 만들고
 * - 예를 들어 숫자 9에 대해 xRecord[9] 와 yRecord[9]에 기록된
 * - 숫자 9의 등장 갯수 중 min값으로 결과 문자열을 구성하는 방식
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * <br/> 처음엔 O(N^2)으로 풀어서 시간 초과가 떴음
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 19/19 <br/>
 * --------------------------------------------------------------
 */
public class P_MakeTwins {
    public String solution(String X, String Y) {
        int[] xRecord = new int[10];
        int[] yRecord = new int[10];

        // 각 자릿수의 갯수를 카운트 및 기록
        countAndRecord(X, xRecord);
        countAndRecord(Y, yRecord);

        // 9부터 0까지 순회하며
        // 몇개나 일치하는지 에 따라 문자열 반환
        // O(k*N); 2700,0000
        StringBuilder sb = new StringBuilder();
        for(int i = 9; i >= 0; i--) {
            int matchValue = Math.min(xRecord[i], yRecord[i]);
            if(matchValue != 0) {
                for(int t = 0; t < matchValue; t++) {
                    sb.append(i);
                }
            }
        }

        // check no twins;
        String result = sb.toString();
        if(result.equals("")) {
            return "-1";
        }

        // check starts with 0;
        if(result.charAt(0) == '0') {
            return "0";
        }

        return result;
    }

    // 0~9까지의 숫자로 이루어진 문자열에서 0~9의 개수를 세서 기록
    static void countAndRecord(String str, int[] arr) {
        for(String s : str.split("")) {
            int digit = s.charAt(0) - '0';
            arr[digit]++;
        }
    }
}
