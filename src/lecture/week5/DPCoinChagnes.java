package lecture.week5;
/**
 * 컴퓨터알고리즘과실습 _ 주종화 교수님
 * 2017112095 컴퓨터공학과 최준호
 * 과제5 문제2
 */
public class DPCoinChagnes {
    public static void main(String[] args) {
        int[] c = {1,4,7};
        solution(11,c);
    }
    static void solution(int M, int[] c){
        int d=c.length;
        int[] smallest = new int[M+1];
        /* DP의 Bottom up 방식, 작은 sub problem 부터 풀어가는 방식 */
        System.out.println("2017112095 컴퓨터공학과 최준호");
        for(int m=1; m<=M; m++){ //m=1일 때 부터 M일 때까지
            smallest[m]=Integer.MAX_VALUE; // int형의 최댓값을 임시로 담아놓음
            for(int i=0; i<d; i++){
                if(m>=c[i]){ // 현재 금액 m이 화폐 단위c[i]보다 더 큰 경우
                    if(smallest[m-c[i]]+1<smallest[m]){
                        smallest[m]=smallest[m-c[i]]+1; //m-c[i] 값이 변하면서 가장 작은 경우 값이 저장됨.
                    }
                }
            }
            System.out.println("M="+m+"일 때,"+smallest[m]);
        }
    }
}
