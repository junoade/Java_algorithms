package lecture.week1.GreedyAndBrute00;

import java.util.Scanner;
/*
* 컴퓨터알고리즘과실습 _ 주종화교수님
* 문제2-4 BruteForceChange 프로그래밍언어로 구현
* 2017112095 컴퓨터공학과 최준호
* */

public class Assignment2_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Assignment2_4 assign = new Assignment2_4();
        int M;
        String input;
        long beforeTime =0, afterTime=0; // 시간 측정용

        System.out.println("2017112095 컴퓨터공학과 최준호 문제2-4");
        System.out.println("M과 c를 입력해주세요. 입력 예 M (c0,c1,c2,...,cd-1) ");

        /* M과 c=(c0,c1,c2,c3,...cd) 입력받음 */
        M = sc.nextInt();
        sc.nextLine();  // 한번 버퍼를 비워줌

        input = sc.nextLine();

        beforeTime = System.currentTimeMillis(); // 시각 측정 시작
        /* HelpGreedy의 구현된 유저의 입력 문자열에서 괄호를 제거해주는 static 메소드 호출 */
        input = HelpGreedy.trimInput(input);

        /* 로직 처리 알고리즘 호출 및 결과 보여주기*/
        /*System.out.println("BruteForceChange " + assign.solution(M, input));*/
        System.out.println("BruteForceChange");
        HelpGreedy.printArray(assign.solution(M,input)); //최적의 k의 배열을 출력
        System.out.println(HelpGreedy.sumOfArray(assign.solution(M,input))); // 그때 k의 요소들의 합을 출력

        afterTime = System.currentTimeMillis();
        System.out.println("소요 시간 : " + (afterTime-beforeTime)/1000.0+"ms");
    }

    public int[] solution(int M, String input) {
        int smallestNumberOfCoins = Integer.MAX_VALUE;  // int형의 최대값을 넣어줌
        int valueOfCoins = 0, numberOfCoins = 0;

        /* 동전 화폐단위 문자열 배열을 int형 배열로 변환해줌 */
        int[] c = HelpGreedy.convertIntArr(input.split(","));
        int d = c.length;
        int[] k = new int[d];
        int[] best_k = new int[d];
        /* k[i]의 값으로 M/c[i]의 값으로 초기화 */
        for (int i = 0; i < d; i++)
            k[i] = M / c[i]; // greedy 알고리즘의 결과, k=(1,0,1,0,1,0) 이므로 합은 3임.

        /* Pseudo code 의 for each 부 구현 */
        for (int i = 0; i <= k[0]; i++) { // k[0] 의 갯수만큼 반복할것
            for (int j = 0; j <= k[1]; j++) { // k[1] 의 갯수만큼 반복할것
                for (int l = 0; l <= k[2]; l++) {
                    for (int m = 0; m <= k[3]; m++) {
                        for (int n = 0; n <= k[4]; n++) {
                            for (int o = 0; o <= k[5]; o++) { //k[5] 의 갯수만큼 반복할것
                                valueOfCoins = c[0] * i + c[1] * j + c[2] * l + c[3] * m + c[4] * n + c[5] * o;
                                if (valueOfCoins == M) {
                                    numberOfCoins = i + j + l + m + n + o; // 동전의 갯수
                                    /*새로운 알고리즘으로 구한 동전의 갯수가 그리디 알고리즘으로 구한 동전의 개수의 합보다 작다면,*/
                                    if (numberOfCoins < smallestNumberOfCoins) {
                                        smallestNumberOfCoins = numberOfCoins; // 브루트 포스로 구한 최선의 동전의 값을 재할당
                                        /* 해당의 경우에, 최선의 k배열에 ( i,j,l,m,n,o ) 값들을 넣어줌 */
                                        best_k[0] = i;
                                        best_k[1] = j;
                                        best_k[2] = l;
                                        best_k[3] = m;
                                        best_k[4] = n;
                                        best_k[5] = o;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        /*HelpGreedy.printArray(best_k);*/
        /*return HelpGreedy.sumOfArray(best_k);*/
        return best_k;
        /*for(int i=0; i<d; i++){ // for each ( k1,k2,...,kd )
            for(int j=0; j<d; j++){
                k[i]=M/Integer.parseInt(c[j]);
                M=M-Integer.parseInt(c[j])*k[j];
            }
        }*/
    }
}
