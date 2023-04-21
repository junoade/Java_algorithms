package category.sorting;

import java.util.Arrays;

/**
 * --------------------------------------------------------------<br/>
 * <b>P_가장 큰 수 구하기</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 정렬 기준 잘 생각해서 구현하기<br/>
 * "000...00" -> "0"으로 바꾸기<br/>
 * N = 100,000 이므로 O(N log N) 풀이 고안하기<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리 116MB, 최대 실행시간 255ms <br/>
 * --------------------------------------------------------------
 */
public class P_BiggestNumber {
    public String solution(int[] numbers) {
        // Integer[] numArr = Arrays.stream(numbers).boxed().toArray(Integer[]::new);
        String[] arr = Arrays.stream(numbers).mapToObj(String::valueOf).toArray(String[]::new);

        // [s1, s2] 가 있을 때 내림차순으로 정렬할 건데,
        // 만약 이미 s1>s2보다 라서 s1+s2가 큰 경우라면
        // temp2.compareTo(temp1);은 -1를 반환하게 되고 정렬되지 않음
        Arrays.sort(arr, (s1, s2) -> {
            String temp1 = s1 + s2;
            String temp2 = s2 + s1;
            return temp2.compareTo(temp1); //
        });
        //System.out.println(Arrays.toString(arr));
        return convertToStr(arr).replaceAll("^0+", "0"); // "0000...00" -> "0"으로
    }

    private String convertToStr(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for(String s : arr) {
            sb.append(s);
        }
        return sb.toString();
    }
}
