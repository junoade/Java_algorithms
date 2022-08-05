package category.hash;

import java.util.*;
import java.io.*;

/**
 * --------------------------------------------------------------<br/>
 * <b>백준 친구 네트워크</b><br/>
 * <a href="https://www.acmicpc.net/problem/4195">문제 바로가기</a>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * <br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * - 최대 10만의 입력이 주어질 수 있음을 고려하여,<br/>
 * - 각 HashSet에 대해 O(1)로 이미 존재하는 유저인지 판별<br/>
 * - 그러면 해당 유저의 친구 목록에 넣어주고 숫자 반환<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  / 256MB , 실행시간  시간초과/ 3000ms<br/>
 * -> 아 T가 커지면 시간이 O(T*100,000)겠구나...
 * --------------------------------------------------------------
 */
public class FriendNetwork_4195 {

    static ArrayList<HashSet<String>> users;

    public static void solution(String s1, String s2) {
        int result = 0;
        int idxS1 = -1;
        int idxS2 = -1;

        for (int i = 0; i < users.size(); i++) {
            HashSet<String> temp = users.get(i);
            if (temp.contains(s1)) {
                idxS1 = i;
            }
            if (temp.contains(s2)) {
                idxS2 = i;
            }
        }

        /* 두 셋을 합침*/
        if (idxS1 != -1 && idxS2 != -1) {
            HashSet<String> temp = users.get(idxS2);
            users.get(idxS1).addAll(temp);
            users.remove(idxS2);
            result = users.get(idxS1).size();
        } else if (idxS1 != -1 && idxS2 == -1) {
            users.get(idxS1).add(s2);
            result = users.get(idxS1).size();
        } else if (idxS1 == -1 && idxS2 != -1) {
            users.get(idxS2).add(s1);
            result = users.get(idxS2).size();
        } else {
            users.add(new HashSet<>(List.of(s1, s2)));
            result = 2;
        }

        System.out.println(result);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int F = Integer.parseInt(br.readLine());
            users = new ArrayList<>();
            for (int j = 0; j < F; j++) {
                String[] inputs = br.readLine().split(" ");
                String s1 = inputs[0];
                String s2 = inputs[1];
                solution(s1, s2);
            }
        }
    }
}
