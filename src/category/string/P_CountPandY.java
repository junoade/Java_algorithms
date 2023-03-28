package category.string;

/**
 * --------------------------------------------------------------<br/>
 * <b>프로그래머스 P와 Y의 개수 세기 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * <br/> p와 P 그리고 y와 Y에 대해선 대소문자 구별 X
 * <br/> replace() 메소드 또는 replaceAll() 메소드를 이용해 그 전의 길이와 이후의 길이를 뺴서 해당 문자의 갯수를 손쉽게 구할 수 있음
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 30/30 <br/>
 * --------------------------------------------------------------
 */
public class P_CountPandY {
    boolean solution(String s) {
        // p의 개수 확인
        int L = s.length();
        s = s.replace("p", "");
        s = s.replace("P", "");
        int p_count = L - s.length();


        // y의 개수 확인
        L = s.length();
        s = s.replace("y", "");
        s = s.replace("Y", "");
        int y_count = L - s.length();

        return p_count == y_count;
    }
}
