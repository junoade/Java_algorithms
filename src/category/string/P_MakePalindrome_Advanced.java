package category.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * --------------------------------------------------------------<br/>
 * <b> 백준 1254 응용 - 팰린도럼 문자를 만들기 위해 swap하고 아니면 append하는 예제 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * <br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  / 256MB , 실행시간  / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class P_MakePalindrome_Advanced {

    static class InfoRecord {
        int count;
        ArrayList<Integer> idxList;

        InfoRecord() {
            count = 0;
            idxList = new ArrayList<>();
        }
    }

    static HashMap<String, InfoRecord> candidate;
    static String input;
    static int output;

    static void processInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
    }

    // isPalindrome
    // appendIfNot
    static void getPalindrome(String s) {
        makeCandidateMap(s);
        s = swapIfNotPalindrome(s);

        if (isPalindrome(s)) {
            output = s.length();
        } else { // 최적화 고민은 일단 나중에
            getPalindrome(s + getCandidateSymbol());
        }
    }

    // O(50)
    static void makeCandidateMap(String str) {
        candidate = new HashMap<>();
        int idx = 0;
        for (String s : str.split("")) {
            InfoRecord temp;
            if (candidate.containsKey(s)) {
                temp = candidate.get(s);
            } else {
                temp = new InfoRecord();
            }
            temp.count++;
            temp.idxList.add(idx);
            idx++;
            candidate.put(s, temp);
        }
    }

    static String getCandidateSymbol() {
        ArrayList<String> keyList = new ArrayList<>(candidate.keySet());
        keyList.sort(String::compareTo); // natural order sorting

        for (String key : keyList) {
            boolean goNextCond = candidate.get(key).idxList.isEmpty();
            if (goNextCond) {
                continue;
            }
            return key;
        }
        return keyList.get(0); // 제일 작은 문자 추가하도록
    }

    static String swapIfNotPalindrome(String s) {
        char[] arr = s.toCharArray();
        int L = arr.length;

        for (int i = 0; i < L / 2; i++) {
            String leftVal = String.valueOf(arr[i]);
            String rightVal = String.valueOf(arr[L - i - 1]);

            if (leftVal.compareTo(rightVal) != 0) {
                String swapValue = min(leftVal, rightVal);
                ArrayList<Integer> tempIdxList = candidate.get(swapValue).idxList;
                boolean validCond = tempIdxList.size() > 1;
                if(validCond) {
                    int nextSymIdx = tempIdxList.get(1); // just like queue but needs next Symbol idx
                    int maxIdx = leftVal.compareTo(rightVal) > 0 ? i : L - i - 1;

                    char temp = arr[nextSymIdx];
                    arr[nextSymIdx] = arr[maxIdx];
                    arr[maxIdx] = temp;

                    tempIdxList.remove(0);
                    tempIdxList.remove(0);
                } else { // stop
                    break;
                }
            }
        }
        return String.valueOf(arr);
    }


    // O(50);
    static boolean isPalindrome(String s) {
        int L = s.length();
        for (int i = 0; i < L / 2; i++) {
            char leftVal = s.charAt(i);
            char rightVal = s.charAt(L - i - 1);
            if (leftVal != rightVal) return false;
        }
        return true;
    }

    static String min(String s1, String s2) {
        return s1.compareTo(s2) > 0 ? s2 : s1;
    }

    // processInput
    // getPalindrome
    public static void main(String[] args) throws IOException {
        processInput();
        getPalindrome(input);
        System.out.println(output);
    }
}
