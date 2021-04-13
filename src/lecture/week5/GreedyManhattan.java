package lecture.week5;

/**
 * 컴퓨터알고리즘과실습 _ 주종화 교수님
 * 2017112095 컴퓨터공학과 최준호
 * 과제5 문제1_3
 */
public class GreedyManhattan {
    public static void main(String[] args) {
        int[][] sum = new int[5][5]; // 탐욕알고리즘 방식으로 2차원 그래프(i,j)의 vertex에 담아놓을 값에 대한 배열
        SouthEdge sth = new SouthEdge();
        EastEdge eth = new EastEdge();

        int i=0; //south 관련 성분
        int j=0; //east 관련 성분
        while(true){
            if(i<4 && j<4){
                if (sth.weight[i][j] < eth.weight[i][j]) { //east 방향으로 이동 sum[i][j+1]에 저장
                    sum[i][j + 1] = sum[i][j] + eth.weight[i][j];
                    j++; //east방향 이동

                } else { //south 가 더 크면. south 방향으로 이동 sum[i+1][j]에 저장
                    sum[i + 1][j] = sum[i][j] + sth.weight[i][j];
                    i++;// east방향 이동
                }
            }
            if(j==4&&i!=4){ //east 끝이고, south 끝 아닐 때
                sum[i+1][j]=sum[i][j]+sth.weight[i][j]; //south방향으로 이동하라.
                i++;
            }
            if(i==4&&j!=4){ //south 끝이면, east 끝 아닐 때
                sum[i][j+1]=sum[i][j]+eth.weight[i][j];
                j++;
            }
            
            if(i==4 && j==4){   //sum(4,4)에 도달시 종료
                break;
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
    /**/
}
/**
 * DPManhattan 클래스에서 구현된
 * class SouthEdge{}
 * class EastEdge{}
 * 을 그대로 사용함.
 */
