package lecture.week9;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * 컴퓨터알고리즘과실습_03, 주종화 교수님
 * 실습9 스트링 매칭
 * 2017112095 컴퓨터공학과 최준호
 */
public class StringMatching {

    public static void main(String[] args) throws IOException {
        /* 같은 패키지 내 RandomGenerator 인스턴스 생성을 통하여 파일을 만듬 */
        /*String filepath = ""; // 다른 환경에서 실행시 파일 경로 설정 필요*/
        String filepath="";
        try {
            filepath = "C:\\Sample"; // 다른 환경에서 실행시 파일 경로 설정 필요
        }catch(Exception e){
            System.out.println("파일 경로 설정을 확인해주세요. 현재 경로 : "+filepath);
        }
        final int weighted = 10000;
        final int ptrn_size = 15;
        final int TestCase = 4;

        /*for(int i=1; i<5; i++) { // 0~9 까지의 숫자들을 랜덤으로, 10,000 100,000 1,000,000 10,000,000 개 생성
            RandomGenerator mkrand = new RandomGenerator(filepath + "\\" + i + ".txt");
            RandomGenerator.mkFile(mkrand.getFilepath(), weighted * (int) Math.pow(10, i - 1));
        }*/

        System.out.println("2017112095 컴퓨터공학과 최준호");
        /* 패턴 길이 15) 길이가 15인 숫자 패턴 랜덤으로 생성 */
         /*String pattern = RandomGenerator.mkPattern(15); // 15자리 패턴 생성
        System.out.println(pattern);*/
        String pattern15 = "TCAACAGCATATAAT";
        /*String pattern15 = "bababca";*/
        /* 패턴 길이 30) 길이가 30인 숫자 패턴 랜덤으로 생성 */
         /*String pattern = RandomGenerator.mkPattern(30); // 30자리 패턴 생성
        System.out.println(pattern);*/
        String pattern30 = "TCAACAGCATATAATAAGCAGAGTTTGCTC";
        /* 패턴 길이 45) 길이가 45인 숫자 패턴 랜덤으로 생성 */
         /*String pattern = RandomGenerator.mkPattern(45); // 45자리 패턴 생성
        System.out.println(pattern);*/
        String pattern45 = "TCAACAGCATATAATAAGCAGAGTTTGCTCGGAATCAGGGTGGTC";

        String patternX = "ACACABACACABACACAC";
        printArr(ComputeSP(patternX));


        /* bruteforce algorithm 호출 */
        for (int i = 1; i <= TestCase; i++) {
            // 방법2) 첫번째 파일을 읽고, 0 부터 파일의 끝 15숫자 앞까지 랜덤한 시작 번호를 기준으로 패턴 생성
            // ptrn = selectPattern(filepath + "/"+i+".txt", ptrn_size);*/
            System.out.println("-- BRUTEFORCE 주어진 시퀀스 길이 " + weighted * (int) Math.pow(10, i - 1) + " --");
            FindBrutePattern(filepath + "/" + i + ".txt", pattern15);
            FindBrutePattern(filepath + "/" + i + ".txt", pattern30);
            FindBrutePattern(filepath + "/" + i + ".txt", pattern45);
        }

        /* Rabin-Karp algorithm 호출 */
        for (int i = 1; i <= TestCase; i++) {
            // 방법2) 첫번째 파일을 읽고, 0 부터 파일의 끝 15숫자 앞까지 랜덤한 시작 번호를 기준으로 패턴 생성
            // ptrn = selectPattern(filepath + "/"+i+".txt", ptrn_size);*/
            System.out.println("-- RABIN-KARP 주어진 시퀀스 길이 " + weighted * (int) Math.pow(10, i - 1) + " --");
            FindRabinKarp(filepath + "/" + i + ".txt", pattern15, 101);
            FindRabinKarp(filepath + "/" + i + ".txt", pattern30, 101);
            FindRabinKarp(filepath + "/" + i + ".txt", pattern45, 101);
        }

        /* KMP algorithm 호출 */
        for (int i = 1; i <= TestCase; i++) {
            // 방법2) 첫번째 파일을 읽고, 0 부터 파일의 끝 15숫자 앞까지 랜덤한 시작 번호를 기준으로 패턴 생성
            // ptrn = selectPattern(filepath + "/"+i+".txt", ptrn_size);*/
            System.out.println("-- KMP 주어진 시퀀스 길이 " + weighted * (int) Math.pow(10, i - 1) + " --");
            FindKMP(filepath + "/" + i + ".txt", pattern15);
            FindKMP(filepath + "/" + i + ".txt", pattern30);
            FindKMP(filepath + "/" + i + ".txt", pattern45);
        }

    }

    /**
     * 라빈 카프 알고리즘 구현
     */
    static void FindRabinKarp(String filePath, String pattern, int q) throws IOException { // q 는 소수, 해시 함수에 의해 결정

        long start=0; // 시간 측정을 위한 변수

        /* 주어진 경로의 파일을 열고, 숫자 하나씩 읽어 드림*/
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        ArrayList<Integer> findIdxList = new ArrayList<>();

        /* 해당 숫자 패턴 안내 */
        System.out.println("패턴 길이 " + pattern.length() + " RabinKarp 알고리즘 수행 시작");

        String readLine = "";

        /* BufferedReader함수 readLine() 을 통해 open 한 파일의 String을 읽어올수있음. 힙메모리/2 또는 INT_MAX 사이즈 만큼*/
        if ((readLine = br.readLine()) != null) {
            int n = readLine.length(); // 전체 시퀀스의 길이
            int m = pattern.length(); // 패턴의 길이
            int D = 1; // pow (d, m-1)를 위해
            int d = 4; // A G T C 알파벳 개수 4개

            int p = 0;  // pattern 의 해시값
            int t = 0;  // 시퀀스의 해시값
            int j = 0;

            /* 시간 측정 시작 */
            start = System.nanoTime();

            //D 구하기
            /*D = (int) (Math.pow(d, m - 1) % q);*/
            for (int i = 0; i < m - 1; i++)
                D = (D * d) % q;

            /*  호너 방법을 통한 패턴 길이 만큼 패턴과 서브 시퀀스의 해시값 계산 */
            for (int i = 0; i <= m - 1; i++) {
                p = (d * p + pattern.charAt(i)) % q;
                t = (d * t + readLine.charAt(i)) % q;
            }

            // 슬라이드하여 패턴의 해시값과 시퀀스의 해시값 비교
            for (int i = 0; i <= n - m; i++) {

                // 해시값이 같더라도 해시 충돌로 인한 해시값의 결과가 같을 수도 있음
                if (p == t) {
                    /* 해시 충돌 검사를 위해 하나씩 알파벳 비교 */
                    for (j = 0; j < m; j++) {
                        if (readLine.charAt(i + j) != pattern.charAt(j))
                            break;
                    }
                    // 진짜 서브 시퀀스와 패턴이 같은 것이 라면,
                    if (j == m)
                        findIdxList.add(i);
                }

                // 점화식을 통한 다음 해시값 계산하기
                if (i < n - m) {
                    t = (d * (t - readLine.charAt(i) * D) + readLine.charAt(i + m)) % q;

                    // 시퀀스를 읽다가 음수가 나올 수도 있으므로,
                    if (t < 0)
                        t = (t + q);
                }
            }

        }
        /* 시간 측정 종료 */
        long end = System.nanoTime();

        /* 결과 출력 */
        System.out.println("패턴과 일치하는 서브 시퀀스의 시작 위치는? ");
        System.out.println(findIdxList);
        System.out.println("측정 시간 : " + (end - start) / 1000000000.0 + "sec");
    }

    /**
     * KMP 알고리즘 구현
     */
    static void FindKMP(String filePath, String pattern) throws IOException {

        long start=0; // 시간 측정을 위한 변수

        /* 주어진 경로의 파일을 열고, 숫자 하나씩 읽어 드림*/
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        ArrayList<Integer> findIdxList = new ArrayList<>();

        /* 해당 숫자 패턴 안내 */
        System.out.println("패턴 길이 " + pattern.length() + " KMP 알고리즘 수행 시작");

        String readLine = "";
        String temp = "";

        /* BufferedReader함수 readLine() 을 통해 open 한 파일의 String을 읽어올수있음. 힙메모리/2 또는 INT_MAX 사이즈 만큼*/
        if ((readLine = br.readLine()) != null) {

            /* 시간 측정 시작 */
            start = System.nanoTime();
            /* Pattern의 Suffix Prefix 테이블 계산 과정 */
            int[] sp = ComputeSP(pattern);

            int n = readLine.length(); // 전체 시퀀스의 길이
            int m = pattern.length(); // 패턴의 길이
            int j = 0;
            for (int i = 0; i < n; i++) {
                while (j > 0 && pattern.charAt(j) != readLine.charAt(i)) // 0 부터 시작해서, j
                    j = sp[j - 1];  // 0 부터 시작하기 때문에 j-1;
                if (pattern.charAt(j) == readLine.charAt(i)) {
                    if (j == m - 1) {
                        findIdxList.add(i - m + 1); // i 에서 패턴길이 m을 뺴고 숫자 구할 때의 +1 하여 시작위치를 찾음
                        j = sp[j];
                    }else
                        j++;
                }
            }//for end
        }

        /* 시간 측정 종료 */
        long end = System.nanoTime();

        /* 결과 출력 */
        System.out.println("패턴과 일치하는 서브 시퀀스의 시작 위치는? ");
        System.out.println(findIdxList);
        System.out.println("측정 시간 : " + (end - start) / 1000000000.0 + "sec");

    }

    static int[] ComputeSP(String pattern) {
        /* Pattern 의 Suffix Prefix 테이블을 만듬 */
        /* Pattern 의 Suffix Prefix 테이블을 만듬 */
        int m = pattern.length();
        int[] sp = new int[m];
        int k = 0;
        sp[0] = 0;
        for (int j = 1; j <= m - 1; j++) {
            while (k > 0 && pattern.charAt(k) != pattern.charAt(j)) // k=0부터 시작해서,
                k = sp[k - 1];  // k=0 부터 시작해서
            if (pattern.charAt(k) == pattern.charAt(j))
                sp[j] = ++k;

        }
        return sp;
    }

    /**
     * BrueteForce
     * 직선적 알고리즘
     */
    static void FindBrutePattern(String filePath, String pattern) throws IOException {

        long start=0; // 시간 측정을 위한 변수

        /* 주어진 경로의 파일을 열고, 숫자 하나씩 읽어 드림*/
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        ArrayList<Integer> findIdxList = new ArrayList<>();

        /* 해당 숫자 패턴 안내 */
        System.out.println("패턴 길이 " + pattern.length() + " 직선적 알고리즘 수행 시작");

        String readLine = "";
        int readNum = 0;
        String temp = "";

        /* BufferedReader함수 readLine() 을 통해 open 한 파일의 String을 읽어올수있음. 힙메모리/2 또는 INT_MAX 사이즈 만큼*/
        if ((readLine = br.readLine()) != null) {
            int n = readLine.length();
            int m = pattern.length();
            int j = 0;

            /* 시간 측정 시작 */
            start = System.nanoTime();
            for (int i = 0; i <= n - m; i++) {
                for (j = 0; j < m; j++) {
                    if (pattern.charAt(j) != readLine.charAt(i + j)) { // i는 주어진 패턴의 시작위치, j는 그 오프셋
                        break;
                    }
                }
                if (j == m) {
                    findIdxList.add(i);
                }
            }
        }

        /* 시간 측정 종료 */
        long end = System.nanoTime();

        /* 결과 출력 */
        System.out.println("패턴과 일치하는 서브 시퀀스의 시작 위치는? ");
        System.out.println(findIdxList);
        System.out.println("측정 시간 : " + (end - start) / 1000000000.0 + "sec");
        /*return !findIdxList.isEmpty(); // Empty가 아니면 true 리턴*/
    }

    static void printArr(int[] arr) {
        System.out.print("선택된 패턴 : ");
        for (int i : arr)
            System.out.print(i + " ");
        System.out.println();
    }
}