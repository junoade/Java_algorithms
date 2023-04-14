package category.bruteforce;

import java.util.*;

public class P_ReportBadUser {
    String[] user_id;
    String[] banned_id;
    boolean[] visited; // user_id의 방문 여부 기록

    Set<Set<String>> banSet;

    public int solution(String[] user_id, String[] banned_id) {
        this.user_id = user_id;
        this.banned_id = getRegex(banned_id);
        banSet = new HashSet<>();
        Set<String> matchedSet = new HashSet<>();
        visited = new boolean[user_id.length];

        recursive(banned_id.length,0, matchedSet);
        return banSet.size();
    }

    void recursive(int m, int bIdx, Set<String> matchedSet) {
        // base case : 주어진 깊이에 대해 탐색 완료한 경우
        if(bIdx == m) {
            banSet.add(new HashSet<>(matchedSet)); // 주의! 그냥 전달하면 테케 3번에서 걸림
            return;
        }

        // 현재 개인정보처리된 banned_id 정보
        String searchRegex = banned_id[bIdx];
        final int n = user_id.length;

        // 다음 탐색 공간 선정 과정
        for(int j = 0; j < n; j++) {
            if (!visited[j] && user_id[j].matches(searchRegex)) {
                visited[j] = true;
                matchedSet.add(user_id[j]);

                recursive(m, bIdx + 1, matchedSet);

                // 다음 banned_id에서 해당 user_id[j]를 조회 수 있도록
                visited[j] = false;
                matchedSet.remove(user_id[j]);
            }
        }
    }

    // 이 경우에도 시간이 좀 오래 걸렸다.
    private String[] getRegex(String[] arr) {
        String[] result = new String[arr.length];
        int i = 0;
        for(String s : arr) {
            result[i] = s.replaceAll("\\*", "[a-z0-9]");
            // result[i] = s.replaceAll("\\*", ".");
            // result[i] = s.replace('*', '.');
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        P_ReportBadUser test = new P_ReportBadUser();
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        // String[] banned_id = {"fr*d*", "abc1**"};
        String[] banned_id = {"fr*d*", "*rodo", "******", "******"};
        System.out.println(test.solution(user_id, banned_id));
    }
}
