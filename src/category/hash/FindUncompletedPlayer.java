package category.hash;

/**
 *
 */
import java.io.InputStreamReader;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;

public class FindUncompletedPlayer {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] part = br.readLine().split(",");
        String[] complete = br.readLine().split(",");
        System.out.println(solution(part, complete));


    }
    public static String solution(String[] participant, String[] completion){

        String answer = "";
        /* first try. HashSet을 사용했으나, 동명이인에 대한 문제로 인하여 숨겨진 테스트케이스에서 실패함 */

        /*HashSet<String> hashSet = new HashSet<String>(Arrays.asList(completion));
        for(String member : participant){
            if(hashSet.contains(member)){
                hashSet.remove(member);
            }else{ // !hashSet.contains(member)
                answer = member;
            }
        }*/

        /* second try */
        HashMap<String, Integer> map = new HashMap<>();
        int idx = 0;
        for(String complete : completion){ // 자료 넣기
            map.put(complete, idx);
            idx++;
        }

        for(String member : participant){
            if(map.containsKey(member)){
                // map.remove(member); // 단순히 remove하면 다 삭제 됨

            }else{
                answer = member;
            }
        }

        return answer;
    }
}
