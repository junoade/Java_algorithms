package category.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class B_Director_2 {
    static int N;
    static List<Long> evils;
    static int[] records;

    static void solution() {
        evils = new ArrayList<>(N);
        evils.add(666L);

        int digit = 4;

        records = new int[7];
        while(digit < 7) {
            // 0~9 호출하도록
            rec_comb(digit, 0, "");
            digit++;
        }

        Collections.sort(evils);
        System.out.println(evils);
        if(evils.size() > N) {
            System.out.println(evils.get(N - 1));
        } else {
            long lastValue = evils.get(evils.size() - 1);
            N -= evils.size();
            int cnt = 0;
            while(cnt != N) {
                lastValue++;
                if (String.valueOf(lastValue).contains("666")) {
                    cnt++;
                }
            }
            System.out.println(lastValue);
        }

    }

    static void rec_comb(int digit, int r, String status) {

        if(digit - 3 == r) {
            // 여기서 status 위치에 666를 넣어주면 되겠네
            // 먼저 뒤에 넣는 코드
            StringBuilder sb = new StringBuilder(status);
            sb.append("666");
            long temp = Long.parseLong(sb.toString());
            if (!evils.contains(temp)) {
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
            rec_comb(digit, r + 1, status + i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        solution();
    }
}
