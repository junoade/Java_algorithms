package category.hash;

import java.io.*;
import java.util.*;

public class Find_Union {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = inputs[0], M = inputs[1];

        Set<String> unseens = new HashSet<>();
        List<String> results = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            unseens.add(br.readLine());
        }

        for(int i = 0; i < M; i++) {
            String key = br.readLine();
            if (unseens.contains(key)) {
                results.add(key);
            }
        }

        System.out.println(results.size());
        results.sort(Comparator.naturalOrder());
        for (String result : results) {
            System.out.println(result);
        }
    }
}
