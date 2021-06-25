package lecture.week10;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 컴퓨터알고리즘과실습_03, 주종화 교수님
 * 10주차 실습 Burrows Wheeler Transform
 * 2017112095 컴퓨터공학과 최준호
 */
public class BWT {

    public static void main(String[] args) {
        System.out.println("2017112095 컴퓨터 공학과 최준호");
        System.out.print("문자열 T를 입력하세요 : ");
        Scanner sc = new Scanner(System.in);
        String T = sc.nextLine();
        String bwt = getBWT(T+"$");
        System.out.println("BWT(T) : "+ bwt);
        String origin = getOrigin(bwt);
        System.out.println("원래 문자열 : " + origin);

        /*System.out.println(enCodeStr);
        System.out.println("The encoded string is: "+enCodeStr.split(":")[0]);
        System.out.println("The decoded string is: "+deCode(enCodeStr.split(":")[0],enCodeStr.split(":")[1]));*/
    }

    //getBWT : T+$ 하고 fill the rest한 다음, 정렬하여 bwt(T)리턴
    public static String getBWT(String T){
        int length = T.length();
        String strs[] = new String[length]; // $를 붙인 all suffix를 만들기 위해,

        /* fill the rest 부분 */
        for(int i =0; i<strs.length; i++){
            strs[i]=T.substring(i)+T.substring(0,i); // i가 증가함에 따라 $사인 뒤로 문자를 보내게 됨.
        }
        /*System.out.println("fill the rest 부분 ");
        for(String s: strs ){
            System.out.println(s);
        }*/

        /* 사전순으로 해당 문자열들을 정렬 하는 부분*/
        /*System.out.println("정렬 부분");*/
        /*ascSort(strs); //개선 가능성*/
        /*RecursiveQuickSort(strs,0,strs.length-1);*/
        Arrays.sort(strs);
        /*for(String s: strs ){
            System.out.println(s);
        }*/

        /* bwt 리턴하기 */
        StringBuffer bwtS = new StringBuffer();
        for(String s : strs){
            bwtS.append(s.substring(length-1, length)); // 마지막 문자만 bwtS에 추가해줌
        }
        return bwtS.toString();

    }

    //getOrigin: bwt(T)값을 이용하여 원래의 문자열로 복원하는 과정
    public static String getOrigin(String bwt){
        Integer[] indices = new Integer[bwt.length()];
        /* 초기화 부분*/
        for (int i = 0; i < indices.length; ++i) {
            indices[i] = i;
        }
        java.util.Arrays.sort(indices, new BWTComparator(bwt));

        int startIdx = 0;
        for (; bwt.charAt(startIdx) != '$'; startIdx++); // $사인을 만나기 전까지 index를 증가시켜나감

        StringBuffer origin = new StringBuffer();

        for (int i = 0; i < indices.length - 1; ++i) {
            startIdx = indices[startIdx];
            char c = bwt.charAt(startIdx);
            origin.append(c);
        }
        return origin.toString();
    }

    public static void ascSort(String[] str){ // 차후 개선 가능성
        for(int i = 0; i < str.length; i++){
            for(int j = i + 1; j < str.length; j++){
                if(str[i].compareTo(str[j]) > 0){
                    /* swap */
                    String temp = str[i];
                    str[i] = str[j];
                    str[j] = temp;
                }
            }
        }
       /* return str;*/
    }
    /* 인덱스를 갖는 문자열들에 대해 정렬하기 위해, */
    private static final class BWTComparator implements java.util.Comparator<Integer> {
        private final String string;

        BWTComparator(String string) {
            this.string = string;
        }

        @Override
        public int compare(Integer i, Integer j) {
            return string.charAt(i) - string.charAt(j);
        }

        public boolean equals(Integer i, Integer j) {
            return string.charAt(i) == string.charAt(j);
        }
    }
    /* 핵심 : Pivot을 잡고 왼쪽은 Pivot 보다 작은 값들, 오른쪽은 Pivot 보다 큰 값들에 대해 재귀적으로 풀이*//*
    public static void RecursiveQuickSort(String[] str, int from, int to) {

        if (from >= to) // index named from 가 to 보다 큰 경우 이미 정렬 완료.
            return;

        int pivot = partition(str, from, to);

        *//* recursive 하게,*//*
        //pivot의 좌측
        RecursiveQuickSort(str, from, pivot-1);
        RecursiveQuickSort(str, pivot+1, to);

    }

    public static int partition(String[] str, int from, int to) {

        String pivot = str[to]; //배열의 끝 부분의 키값을 pivot으로 잡는다.
        int counter = from; // swap이 일어난 자리를 저장하는 counter

        *//* partition 부분 *//*
        for (int i = from; i < to; i++) {
            *//*if (str[i] > pivot) {
                swap(str, i, counter);
                counter++;
            }*//*
            if(str[i].compareTo(pivot) > 0){
                *//* swap *//*
                String temp = str[i];
                str[i] = pivot;
                pivot = temp;
            }

        }
        *//* partition 이후 *//*
        // 끝 부분과 counter가 가르키는 키값을 swap
        String temp = str[to];
        str[to] = str[counter];
        str[counter] = temp;

        return counter;
    }*/


}
