package lecture.week7;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;

/**
 * 컴퓨터 알고리즘과 실습3, 주종화 교수님
 * 실습7 스트링 매칭
 * 2017112095 컴퓨터공학과 최준호
 */

public class FindPattern {

    public static void main(String[] args) throws IOException {
        /* 같은 패키지 내 RandomGenerator 인스턴스 생성을 통하여 파일을 만듬 */
        String filepath = "C:\\Users\\ajcho\\Desktop\\Studying Programing\\IntelliJ\\Java_algorithms\\src\\lecture\\week7";
        final int weighted = 10000;
        final int ptrn_size = 15;
        final int TestCase = 4;

        for(int i=1; i<5; i++){ // 0~9 까지의 숫자들을 랜덤으로, 10,000 100,000 1,000,000 10,000,000 개 생성
            RandomGenerator mkrand = new RandomGenerator(filepath+"/"+i+".txt");
            RandomGenerator.mkFile(mkrand.getFilepath(), weighted*(int) Math.pow(10,i-1));
        }

        /* 길이가 15인 숫자 패턴 최초 생성 */
        int ptrn[] = new int[ptrn_size];
        for (int i = 0; i < ptrn.length; i++)
            ptrn[i] = (int) (Math.random() * 10);

        /* 해당 숫자 패턴 안내 */
        /*printArr(ptrn);*/

        /* bruteforce algorithm 호출 */

        for (int i = 1; i <= TestCase; i++) {
            BrutePattern(filepath + "/" + i + ".txt", new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5});
        }
        //new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5} 디버깅용 테스트 pattern
    }

    static void BrutePattern(String filePath, int[] pattern) throws IOException {
        /* 주어진 경로의 파일을 열고, 숫자 하나씩 읽어 드림*/
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        ArrayList<Integer> findIdxList = new ArrayList<>();

        /* 해당 숫자 패턴 안내 */
        printArr(pattern);

        String readLine = "";
        int readNum = 0, idx = 0, matchCnt = 0;
        int[] buffer = new int[pattern.length]; // 파일을 읽어드리고 저장하는 버퍼 역할
        String temp = "";
        /* BufferedReader함수 readLine() 을 통해 open 한 파일의 String을 읽어올수있음. 힙메모리/2 또는 INT_MAX 사이즈 만큼*/

        if ((readLine = br.readLine()) != null) {
            while (true) {

                /* buffer[15] 에 읽은 (idx에서부터 idx+14)까지 자른 값을 int형으로 변환하여 저장*/
                try {
                    temp = readLine.substring(idx, idx + buffer.length); // 끝에 도달했을 때 더이상 버퍼의 크기만큼 자를수 없을 것
                    for (int i = 0; i < pattern.length; i++) {
                        buffer[i] = temp.charAt(i) - 48;
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    break;
                }

                /* buffer의 15개의 숫자와 15개의 패턴을 비교 */
                for (int i = 0; i < pattern.length; i++) {
                    if (pattern[i] == buffer[i]) {
                        matchCnt++;
                    } else {
                        idx++;
                        break;
                        // 그자리에서 멈추고, 다음 while문 흐름으로 진행해 다음 15개 읽어 버퍼에 저장하도록 함.
                    }
                }

                if (matchCnt == pattern.length) {
                    //이때 idx는 buffer[0] 의 시작 위치 인덱스를 list에 넣도록 설계함.
                    findIdxList.add(idx); // 그리고, idx=idx+pattern.length 해줌;
                    idx += pattern.length;
                }
                matchCnt = 0;   //얼마나 일치했는지를 초기화 해주고 스트링 자르러가야함
                /* readNum++;*/
            }
        }
        /* 결과 출력 */
        System.out.println("패턴과 일치하는 숫자의 시작 위치는? ");
        System.out.println(findIdxList);

    }

    static void printArr(int[] arr) {
        for (int i : arr)
            System.out.print(i + " ");
        System.out.println();
    }

}
