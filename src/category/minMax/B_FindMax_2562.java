package category.minMax;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class B_FindMax_2562 {
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        list = new ArrayList<>();

        for (int T = 0; T < 9; T++) {
            list.add(Integer.parseInt(br.readLine()));
        }

        int max_idx = 0;
        int idx = 0;
        for (Integer n : list) {
            if (list.get(max_idx) < n) {
                max_idx = idx;
            }
            idx++;
        }

        System.out.println(list.get(max_idx));
        System.out.println(max_idx + 1); // 1부터 시작하게 출력
    }
}
