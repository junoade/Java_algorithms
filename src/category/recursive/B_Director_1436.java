package category.recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class B_Director_1436 {

    static int N;
    static List<Long> evils;

    static void solution() {
        evils = new ArrayList<>(N);
        evils.add(666L);

        int digit = 4;
        while(evils.size() <= N) {
            // 0~9 호출하도록
            rec_comb(digit, 0,  new StringBuilder());
            digit++;
        }

        Collections.sort(evils);
        System.out.println(evils);
        System.out.println(evils.get(N - 1));

    }

    static void rec_comb(int digit, int r, StringBuilder status) {

        if(digit - 3 == r) {
            // 여기서 status 위치에 666를 넣어주면 되겠네
            // 먼저 뒤에 넣는 코드
            StringBuilder sb = new StringBuilder(status);
            sb.append("666");
            long temp = Long.parseLong(sb.toString());
            if (!evils.contains(temp) && evils.size() < N) {
                evils.add(Long.parseLong(sb.toString()));
            }

            // 틈새에 넣는 코드
            for (int i = r-1; i >= 0; i--) {
                sb = new StringBuilder(status);
                sb.insert(i, "666");
                temp = Long.parseLong(sb.toString());
                if (!evils.contains(temp)) {
                    evils.add(Long.parseLong(sb.toString()));
                }
            }

            return;
        }

        // 0~9 추가
        for (int i = 0; i <= 9; i++) {
            rec_comb(digit, r + 1, new StringBuilder(status).append(i));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        solution();

    }
}
