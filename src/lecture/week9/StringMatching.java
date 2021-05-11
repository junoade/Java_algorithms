package lecture.week9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StringMatching {

    public static void main(String[] args) throws IOException {
        /* 같은 패키지 내 RandomGenerator 인스턴스 생성을 통하여 파일을 만듬 */
        /*String filepath = ""; // 다른 환경에서 실행시 파일 경로 설정 필요*/
        String filepath = "C:\\Users\\ChoiJunho\\Desktop\\Sample"; // 다른 환경에서 실행시 파일 경로 설정 필요

        final int weighted = 10000;
        final int ptrn_size = 15;
        final int TestCase = 4;

        /*for(int i=1; i<5; i++){ // 0~9 까지의 숫자들을 랜덤으로, 10,000 100,000 1,000,000 10,000,000 개 생성
            RandomGenerator mkrand = new RandomGenerator(filepath+"\\"+i+".txt");
            RandomGenerator.mkFile(mkrand.getFilepath(), weighted*(int) Math.pow(10,i-1));
        }*/

        System.out.println("2017112095 컴퓨터공학과 최준호");
        /*String pattern = RandomGenerator.mkPattern(15); // 15자리 패턴 생성
        System.out.println(pattern);*/
        String pattern = "ACCACAAGGCTCGAG";

        /* 방법 1) 길이가 15인 숫자 패턴 랜덤으로 생성 */

        /* bruteforce algorithm 호출 */
        for (int i = 1; i <= TestCase; i++) {
            //* 방법2) 첫번째 파일을 읽고, 0 부터 파일의 끝 15숫자 앞까지 랜덤한 시작 번호를 기준으로 패턴 생성 *//*
            //*ptrn = selectPattern(filepath + "/"+i+".txt", ptrn_size);*//*
            FindBrutePattern(filepath + "/" + i + ".txt", pattern);
        }

    }

    /**
     * 라빈 카프 알고리즘 구현
     */
    static void FindRabinKarp(String filePath, int[] pattern) throws IOException {

    }

    /**
     * KMP 알고리즘 구현
     */
    static void FindKMP(String filePath, int[] pattern) throws IOException {

    }


    /**
     * BrueteForce
     * 직선적 알고리즘
     */
    static void FindBrutePattern(String filePath, String pattern) throws IOException {
        /* 주어진 경로의 파일을 열고, 숫자 하나씩 읽어 드림*/
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        ArrayList<Integer> findIdxList = new ArrayList<>();

        /* 해당 숫자 패턴 안내 */
        System.out.println(pattern);

        String readLine = "";
        int readNum = 0, idx = 0, matchCnt = 0;
        String temp = "";
        /* BufferedReader함수 readLine() 을 통해 open 한 파일의 String을 읽어올수있음. 힙메모리/2 또는 INT_MAX 사이즈 만큼*/

        if ((readLine = br.readLine()) != null) {
            while (true) {

                /* buffer[15] 에 읽은 (idx에서부터 idx+14)까지 자른 값을 int형으로 변환하여 저장*/
                try {

                    temp = readLine.substring(idx, idx + pattern.length()); // 끝에 도달했을 때 더이상 버퍼의 크기만큼 자를수 없을 것

                } catch (StringIndexOutOfBoundsException e) {
                    break;
                }

                /* buffer의 15개의 숫자와 15개의 패턴을 비교 */
                for (int i = 0; i < pattern.length(); i++) {
                    if (pattern.equals(temp)) {
                        findIdxList.add(idx); // 그리고, idx=idx+pattern.length 해줌;
                        idx += pattern.length();
                    } else {
                        idx++;
                        break;
                        // 그자리에서 멈추고, 다음 while문 흐름으로 진행해 다음 15개 읽어 버퍼에 저장하도록 함.
                    }
                }
            }
        }
        /* 결과 출력 */

        System.out.println("패턴과 일치하는 숫자의 시작 위치는? ");
        System.out.println(findIdxList);

        /*return !findIdxList.isEmpty(); // Empty가 아니면 true 리턴*/
    }

    static void printArr(int[] arr) {
        System.out.print("선택된 패턴 : ");
        for (int i : arr)
            System.out.print(i + " ");
        System.out.println();
    }

    static int[] selectPattern(String filePath, int p_size) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        int[] retArr = new int[p_size];

        /* 읽어드린 파일의 길이 확인하기 */
        String temp = br.readLine();
        int length = temp.length();

        /* 범위 내에서 랜덤 인덱스 고르기 0~ 최대길이 - pattern_size */
        int start = (int) (Math.random() * (length - p_size));
        try {
            temp = temp.substring(start, start + p_size);
            for (int i = 0; i < p_size; i++) {
                retArr[i] = temp.charAt(i) - 48;
            }
        } catch (StringIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return retArr;
    }
}