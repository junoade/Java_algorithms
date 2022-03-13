package category.minMax;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LottoRank_Prgrms_Level1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] a = br.readLine().split(" ");
        String[] b = br.readLine().split(" ");
        int[] lottos = new int[a.length];
        int[] win_nums = new int[b.length];

        for(int i = 0; i<lottos.length; i++){
            lottos[i] = Integer.parseInt(a[i]);
            win_nums[i] = Integer.parseInt(b[i]);
        }
        int[] answer = solution(lottos, win_nums);
        System.out.println(answer[0]+" "+answer[1]);
    }
    public static int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = {};

        /* step1) wildCard 개수 세기 */
        int wildCard = 0;
        for (int lotto : lottos) {
            if (lotto == 0) {
                wildCard++;
            }
        }

        /* step2) 비교하기 */
        // Map 이나 Set 의 구현체를 활용하여 성능을 높일 수 있다.
        // Arrays 의 정렬, binarySearch 메소드를 활용할 수도 있다.
        int correct = 0;
        for (int lotto : lottos) {
            for (int win_num : win_nums) {
                if (lotto == win_num)
                    correct++;
            }
        }

        /* step3) 결과 */
        int max = correct + wildCard;
        int min = correct;
        answer = new int[]{checkRank(max), checkRank(min)};
        // answer = new int[]{Math.min(7-max, 6), Math.max(7-min, 6)};
        return answer;
    }

    public static int checkRank(int n){
        int result = 0;
        switch(n){
            case 6:
                result = 1;
                break;
            case 5:
                result = 2;
                break;
            case 4:
                result = 3;
                break;
            case 3:
                result = 4;
                break;
            case 2:
                result = 5;
                break;
            default:
                result = 6;
                break;
        }
        return result;
    }
}
