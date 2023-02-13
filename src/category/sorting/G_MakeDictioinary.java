package category.sorting;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

/**
 * --------------------------------------------------------------<br/>
 * <b> 구름 먼데이 알고리즘 챌린지 - 0주차 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * - BufferedReader 객체 호출 시점 조심
 * -
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * <br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  / 256MB , 실행시간  / 1000ms<br/>
 * --------------------------------------------------------------
 */
class G_MakeDictionary {
    static String[] dict;
    static int N, K;

    public static void main(String[] args) throws IOException {
        processInput();
        sortByRules(dict);
        System.out.println(getKthValue(K-1));
    }

    static void processInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        K = Integer.parseInt(temp[1]);

        makeDictionary(br);
    }

    static void makeDictionary(BufferedReader br) throws IOException {
        dict = new String[N];
        for(int i = 0; i < N; i++){
            dict[i] = br.readLine();
        }
    }

    static void sortByRules(String[] arr){
        Arrays.sort(arr, (s1, s2) -> {
            int length1 = s1.length(), length2 = s2.length();
            if (length1 == length2) {
                return s1.compareTo(s2);
            } else {
                return length1 > length2 ? 1 : -1;
            }
        });
    }

    static String getKthValue(int k) {
        if(dict.length != 0){
            return dict[k];
        } else {
            return "";
        }
    }
}

