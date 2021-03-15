package lecture.week1.GreedyAndBrute00;

/*
 * 컴퓨터알고리즘과실습 _ 주종화교수님
 * 문제2-5 GreedyBetterChange와 BruteForceChange의 결과를 비교!
 * 2017112095 컴퓨터공학과 최준호
 * */

public class Assignment2_5 {
    public static void main(String[] args) {
        Assignment2_2 greedy = new Assignment2_2();
        Assignment2_4 brute = new Assignment2_4();

        String c = "55,33,15,10,7,1"; //주어진 c의 요소들
        int total = 0;  //
        long beforeTime =0, afterTime=0;
        beforeTime = System.currentTimeMillis();

        System.out.println("2017112095 컴퓨터공학과 최준호");
        System.out.println("M=1 부터 100 까지 두 결과를 비교하고, 값이 서로 다른 경우를 출력");

        for (int M = 1; M <= 100; M++) {
            if (HelpGreedy.sumOfArray(greedy.solution(M, c)) != HelpGreedy.sumOfArray(brute.solution(M, c))) {
                System.out.print(M + " ");
                total++;
                if (total % 10 == 0) //M 의 출력을 한줄에 10개 씩 하기 위해
                    System.out.println();
            }

        }
        System.out.println();

        afterTime = System.currentTimeMillis();
        System.out.println("소요 시간 : " + (afterTime-beforeTime)/1000.0+"ms");

        /*System.out.println();*/
    }
}
