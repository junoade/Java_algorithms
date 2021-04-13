package lecture.week5;

/**
 * 컴퓨터알고리즘과실습 _ 주종화 교수님
 * 2017112095 컴퓨터공학과 최준호
 * 과제5 문제1_2
 */
public class DPManhattan {
    public static void main(String[] args) {
        int[][] sum = new int[5][5]; // 동적 계획법에서, bottom up method 사용할때, sub problems의 정답을 담아 놓는 배열
        SouthEdge sth = new SouthEdge();
        EastEdge eth = new EastEdge();

        /* 1. s(1,0),s(2,0),s(3,0),s(4,0) 값 담기 */
        for (int row = 1; row < 5; row++)
            sum[row][0] = sum[row - 1][0] + sth.weight[row-1][0];   //남쪽 방향의 weighted 그래프는 4x5 2차원 배열이라 row-1

        /* 2. s(0,1),s(0,2), s(0,3),s(0,4) 값 담기 */
        for (int col = 1; col < 5; col++)
            sum[0][col] = sum[0][col - 1] + eth.weight[0][col-1]; //동쪽 방향의 weighted 그래프는 5x4 2차원 배열 col-1

        /* 3. 본격적으로 DP을 이용해 sub problems을 풀어가기.*/
        for(int i=1; i<5; i++){
            for(int j=1; j<5; j++){
                /* ex) sum[1][1] 엔 어떤 값이 담기는가? s[1][0]값과 east 간선 가중치값 더한값 또는 s[0][1]값과 south간선 가중치값 더한값 중 큰값! */
                sum[i][j]=Integer.max((sum[i-1][j]+sth.weight[i-1][j]),(sum[i][j-1]+eth.weight[i][j-1])); //일반화된 점화식
            }
        }
        printArr(sum);

    }

    private static void printArr(int[][] sum) {
        System.out.println("2017112095 컴퓨터공학과 최준호");
        for (int i = 0; i < sum.length; i++) {
            for (int j = 0; j < sum[i].length; j++)
                System.out.print(sum[i][j] + " ");
            System.out.println();
        }
    }
}
/* weighted graph을 객체 지향적으로 풀기 위해, 구현한 두 클래스*/
class SouthEdge {
    int[][] weight;

    SouthEdge() {
        this.weight = new int[][]{{1, 0, 2, 4, 3}, {4, 6, 5, 2, 1}, {4, 4, 5, 2, 1}, {5, 6, 8, 5, 3}};
    }

}

class EastEdge {
    int[][] weight;

    EastEdge() {
        this.weight = new int[][]{{3, 2, 4, 0}, {3, 2, 4, 1}, {0, 7, 3, 4}, {3, 3, 0, 2}, {1, 3, 2, 2}};
    }
}
/*class Point{
    int down;
    int right;
    int sum;

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    Point(int right, int down){
        this.right=right;
        this.down=down;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public int getDown() {
        return down;
    }

    public int getRight() {
        return right;
    }
    public void setRD(int down, int right){
        this.down=down;
        this.right=right;
    }
}*/
