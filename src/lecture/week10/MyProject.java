package lecture.week10;

import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 컴퓨터알고리즘과실습_03, 주종화 교수님
 * 실습10 문제3
 * 2017112095 컴퓨터공학과 최준호
 */
public class MyProject {
    public static void main(String[] args) throws IOException {

        String filepath1 = "C:\\Users\\ajcho\\Desktop\\myfile\\Test2\\refGenome.txt"; //랜덤함수로 임의로 만든 reference genome
        String filepath2 = "C:\\Users\\ajcho\\Desktop\\myfile\\Test2\\shortreadT1"; //shortread저장
        String filepath3 = "C:\\Users\\ajcho\\Desktop\\myfile\\Test2\\myGenome.txt"; //복구한 게놈 시퀀스
        final int RDNA_length = 1000000;
        /* #1. reference genome sequence 생성 */
        SequenceGenerator.mkFile(filepath1,RDNA_length);

        /* #2. k와 n 동적 할당 입력 */
        /*Scanner sc = new Scanner(System.in);
        System.out.println("2017112095 컴퓨터공학과 최준호");
        System.out.println("k와 n입력");
        int k = sc.nextInt();
        int n = sc.nextInt();
        System.out.println(k+" "+n);*/

        /* #3. shortread 파일 생성 */

        //SequenceGenerator.mkShortRead(filepath1,filepath2,k,n);

        /* #4. reconstruct sequencing */
        /*System.out.println("미스 매치 허용개수 입력");
        int mismatch = sc.nextInt();
        mkRecoveredSequence(filepath1, filepath2, filepath3,k,n, mismatch);*/


        /* Test 1 k=30, n=40,000 */
        //SequenceGenerator.mkShortRead(filepath1,filepath2,30,40000);
        /* k=30, n=40,000 일 때 */
        //mkRecoveredSequence(filepath1, filepath2, filepath3,30,40000, 3);

        /* Test 2 k=60, n=30,000 */
        SequenceGenerator.mkShortRead(filepath1,filepath2,60,30000);
        mkRecoveredSequence(filepath1, filepath2, filepath3,60,30000, 3);

        getDifference(filepath1, filepath3);

    }
    static void getDifference(String filepath1, String filepath2) throws IOException {
        BufferedReader br1 = new BufferedReader(new FileReader(filepath1));
        BufferedReader br2 = new BufferedReader(new FileReader(filepath2));
        String ref = br1.readLine();
        String my = br2.readLine();
        ArrayList<Integer> findDiffIdx = new ArrayList<>();

        int ref_length = ref.length();
        int my_length = my.length();

        long start = System.nanoTime(); // 총 시간을 구하기 위한 변수
        if(ref_length == my_length){
            for(int i=0; i<ref_length; i++){
                if(ref.charAt(i) != my.charAt(i)){
                    findDiffIdx.add(i);
                }
            }
        }
        long end = System.nanoTime();
        double result = ((ref.length() - findDiffIdx.size())/(double)ref.length()) *100.0;
        System.out.println("2017112095 컴퓨터공학과 최준호");
        System.out.println("SNP 비교 시간 : " +(end - start) / 1000000000.0 + "sec" );
        System.out.println("> 정확도 : " + result);
        System.out.println("ref 길이"+ref.length());
        System.out.println("mySequence 길이"+my_length);
        System.out.println("차이나는 부분" +findDiffIdx.size());
    }

    static void mkRecoveredSequence(String filepath1, String filepath2, String filepath3,int k, int N, int mismatch) throws IOException {
        BufferedReader br1 = new BufferedReader(new FileReader(filepath1));
        BufferedReader br2 = new BufferedReader(new FileReader(filepath2));
        ArrayList<Integer> findIdxList = new ArrayList<>();
        ArrayList<String> findShortRead = new ArrayList<>();
        ArrayList<Integer> findLineNo = new ArrayList<>();

        String reconstruct = br1.readLine();
        int length = reconstruct.length();
        int cnt_mismatch = 0;

        long group_start = System.nanoTime(); // 총 시간을 구하기 위한 변수

        if (reconstruct != null) {
            String shortRead = br2.readLine(); // 먼저 뽑는다
            int lno=1;
            if (shortRead != null) {
                while (lno<=N) {
                    long start = System.nanoTime();
                    for (int i = 0; i < length - k; i++) {
                        /*trivial method, =brute force 이용*/
                        for (int j = 0; j < k; j++) {
                            if (shortRead.charAt(j) != reconstruct.charAt(i + j)) {
                                /* mismatch 증가시킴*/
                                cnt_mismatch++;
                                if (cnt_mismatch > mismatch) // 흠.. {reconstruct = reconstruct.replace(reconstruct.charAt(i+j), ' '); }
                                    break;
                            }
                        }
                        if (cnt_mismatch < mismatch) {
                            reconstruct = reconstruct.replace(reconstruct.substring(i, i + k), shortRead);
                            findIdxList.add(i);
                            findShortRead.add(shortRead); // 해당 shortRead를 따로 저장함
                            findLineNo.add(lno);
                        }
                        cnt_mismatch = 0;
                    }
                    if(lno<N){
                        shortRead = br2.readLine();
                    }
                    lno++;
                    long end = System.nanoTime();
                    System.out.println(lno + "행 도달 , 시간 " + (end - start) / 1000000000.0 + "sec");

                }
            }
        }
        long group_end = System.nanoTime();
        System.out.println("변화 내역");
        System.out.println(findIdxList);
        System.out.println("short read 행 넘버");
        System.out.println(findIdxList);
        System.out.println("short read 내역");
        System.out.println(findIdxList);
        System.out.println("Total 시간 : " +(group_end - group_start) / 1000000000.0 + "sec" );
        /* 출력 */
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath3));
        PrintWriter pw = new PrintWriter(writer);
        pw.write(reconstruct);
        pw.flush();
        pw.close();
    }

}

/* 임의의 Reference DNA sequence 를 만들어주고, k길이의 n개의 shortRead를 만들어주는 클래스*/
class SequenceGenerator {
    private String filepath;
    private int SIZE;
    private static char[] AGTC = {'A', 'G', 'T', 'C'};

    SequenceGenerator(String filepath) {
        this.filepath = filepath;
    }

    static void mkFile(String filepath, int size) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        PrintWriter pw = new PrintWriter(writer);
        SecureRandom srand = new SecureRandom();
        int random = 0;
        char temp;
        for (int i = 0; i < size; i++) {
            /*random = (int) (Math.random() * 4); // 0,1,2,3 중 랜덤하게 하나를 고름*/
            random=srand.nextInt(4);
            temp = AGTC[random];

            /*pw.printf("%d ", random);*/
            pw.print(temp);
        }
        System.out.println("파일 출력 완료");
        pw.flush();
        pw.close();
    }

    /*  sort Read 파일 만들기 구현 */
    static void mkShortRead(String filepath1, String filepath2, int k, int n) throws IOException { // k: short-read의 길이, n : sort-read의 개수
        /* 주어진 경로의 파일을 열고, 생성한 문자열을 읽음*/
        BufferedReader br = new BufferedReader(new FileReader(filepath1));

        /* short read를 저장하기로 함*/
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath2));
        PrintWriter pw = new PrintWriter(writer);

        SecureRandom srand = new SecureRandom(); // 향상된 random 함수

        String readLine = "";
        if ((readLine = br.readLine()) != null) {
            String temp = "";
            int maxLength = readLine.length();
            int numRandShortRead =0;
            int idx_rand=0;
            char randChar='\u0000';
            /* k길이의 n개의 short read만들고 파일로 저장하기 */
            for (int i = 0; i < n; i++) { //i는 카운터
                /*int startIdx = (int) (Math.random() * maxLength); //시작 위치 랜덤하게*/
                int startIdx = srand.nextInt(maxLength);
                System.out.println(startIdx);
                System.out.println("cnt" + i);
                if (startIdx + k > maxLength) {// k길이의 short read를 뽑으려는데 max Length를 넘어가는 경우,
                    startIdx = maxLength - k;// 해당 차이만큼 앞으로 startIdx를 옮겨서 short read를 뽑음

                }

                temp = readLine.substring(startIdx, startIdx + k);

                /* 각 short Read 에서 랜덤하게 0~2개 까지의 살짝 다른 A,G,T,C 가 들어가는데 그 문자도 랜덤, 위치로 랜덤 */
                numRandShortRead = srand.nextInt(3);
                for(int j=0; j<numRandShortRead; j++){
                    idx_rand = srand.nextInt(k);
                    randChar = AGTC[srand.nextInt(3)];
                    temp=temp.replace(temp.charAt(idx_rand),randChar);
                }
                pw.print(temp+"\n");

            }
        }
        pw.flush();
        pw.close();
    }


    String getFilepath() {
        return filepath;
    }

    int getSIZE() {
        return SIZE;
    }

    void setSIZE(int SIZE) {
        this.SIZE = SIZE;
    }

}
