package daily.y2026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class BOJ_Prefix_1141 {
    static ArrayList<String> inputs;

    static void processInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        inputs = new ArrayList<>(N);
        for(int i = 0; i < N; i++) {
            inputs.add(br.readLine());
        }
    }

    static int findSubset() {
        // 입력으로 받은 문자들의 리스트 길이순으로 정렬
        inputs.sort(Comparator.comparingInt(String::length));
        int result = 0;

        // O(50*50*L)
        int length = inputs.size();
        for(int i = 0; i < length; i++) {
            boolean isFound = false;
            String prefix = inputs.get(i);
            for(int j = i+1; j < length; j++) {
                String target = inputs.get(j);
                if(target.startsWith(prefix)) { // target.matches(prefix+"\\w*"); -> 212ms
                    isFound = true;
                    break;
                }
            }

            if(!isFound) {
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        processInput();
        System.out.println(findSubset());
    }
}
