package category.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * 앞서 풀이는 시간이 간당했음.
 * 어떤 곳에서 오버헤드가 난 걸까
 * -> System.out.println() 의 반복 조심!
 */
public class PocketmonMaster2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int T = Integer.parseInt(temp[0]);
        int N = Integer.parseInt(temp[1]);

        HashMap<String, Integer> map = new HashMap<>();
        String[] pocketMons = new String[T + 1]; // 1 부터 시작 하기 때문

        for (int i = 0; i < T; i++) {
            String name = br.readLine();
            map.put(name, i + 1);
            pocketMons[i + 1] = name;
        }
        StringBuilder sb = new StringBuilder(); // StringBuilder, 멀티 쓰레드 안정성 X. 그러나 싱글 쓰레드에서는 조금 더 좋은 효과 기대 가능

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            char digitChecker = s.charAt(0);
            // if (isIntNumber(s)) {
            if(digitChecker >= '0' && digitChecker <= '9'){ // 문자열이 숫자형 문자열이라면, 앞 글자를 char로 변환하고 숫자의 범위에 있는지 확인한다.
                // System.out.println(pocketMons[Integer.parseInt(s)]);
                sb.append(pocketMons[Integer.parseInt(s)]).append("\n");

            } else {
                // System.out.println(map.get(s));
                sb.append(map.get(s)).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    /* 해당 부분 메모리 크게, 속도 크게 차지 */
    /*public static boolean isIntNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }*/
}
