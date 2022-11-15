package category.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class GNS_D3 {
    final static HashMap<String, Integer> DIGIT_MAP = new HashMap<>();

    static {
        DIGIT_MAP.put("ZRO", 0);
        DIGIT_MAP.put("ONE", 1);
        DIGIT_MAP.put("TWO", 2);
        DIGIT_MAP.put("THR", 3);
        DIGIT_MAP.put("FOR", 4);
        DIGIT_MAP.put("FIV", 5);
        DIGIT_MAP.put("SIX", 6);
        DIGIT_MAP.put("SVN", 7);
        DIGIT_MAP.put("EGT", 8);
        DIGIT_MAP.put("NIN", 9);
    }

    static class Element {
        String digit;
        int decimalValue;

        Element(String digit) {
            this.digit = digit;
            decimalValue = DIGIT_MAP.get(digit);
        }

        public String toString() {
            return digit;
        }
    }

    static ArrayList<Element> initArray(StringTokenizer st) throws IOException {
        ArrayList<Element> array = new ArrayList<>();
        while (st.hasMoreTokens()) {
            array.add(new Element(st.nextToken()));
        }
        return array;
    }

    static void printArray(ArrayList<Element> array, int test_case) {
        String temp = array.toString().replace(",", "").replace("[", "").replace("]", "");
        System.out.printf("#%d\n", test_case);
        System.out.println(temp);
    }

    public static void main(String args[]) throws Exception {
        //System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

        for (int test_case = 1; test_case <= T; test_case++) {
            /////////////////////////////////////////////////////////////////////////////////////////////
            // 입력 길이 확보
            int L = Integer.parseInt(br.readLine().split(" ")[1]);
            ArrayList<Element> array = initArray(new StringTokenizer(br.readLine()));
            array.sort(Comparator.comparingInt(o -> o.decimalValue));
            printArray(array, test_case);
            /////////////////////////////////////////////////////////////////////////////////////////////
        }
    }
}
