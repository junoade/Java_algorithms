package baekjoon.problems.bronze.class1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StudyingVoca1157 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().toUpperCase();
        int[] ch_cnt = new int[26]; // 알파벳 갯수 (A-Z)

        int max=0, idx=0;
        char st='?';
        for(int i=0; i<input.length(); i++){
            idx = input.charAt(i)-65;
            ch_cnt[idx]++;
            if(max<ch_cnt[idx]){
                max = ch_cnt[idx];
                st=input.charAt(i);
            }else if(max==ch_cnt[idx])
                st='?';
        }
        System.out.println(st);

    }
}
