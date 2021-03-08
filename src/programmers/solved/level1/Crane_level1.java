package programmers.solved.level1;

import java.util.ArrayList;

public class Crane_level1 {
	public int solution(int[][] board, int[] moves) {
		int answer = 0;
		/**/
		int N = board.length;
		int MOV = moves.length;
		int cursor = -1;
		/* moves결과로 인형담을 어레이리스트 구성 */
		ArrayList<Integer> arrList = new ArrayList<>();

		/* 크레인이 움직임 */
		for (int i = 0; i < MOV; i++) {
			int j = moves[i];
			for (int k = 0; k < N; k++) {
				/* 크레인이 내려오는데 인형이 있을 때 */
				if (board[k][j - 1] != 0) {
					/* 해당 인형 스택에 push */
					int putInt = board[k][j - 1];
					arrList.add(putInt);
					/* cursor++; */
					cursor=arrList.size()-1;
					/*추가하면서, 아랫 커서넘버와 현재 커서 비교*/
					if (arrList.size()>2 && (arrList.get(cursor) == arrList.get(cursor - 1)) ) {
						/* 같다면, */
						arrList.remove(cursor);
						arrList.remove(cursor - 1);
						cursor = arrList.size()-1;
						/*
						 * if(cursor <0 ) cursor =0;
						 */
						answer+=2;
					}else if(arrList.size()==2 && (arrList.get(0)==arrList.get(1))){
	                    arrList.remove(1);
	                    arrList.remove(0);
	                    answer+=2;
	                } else {
						/**/
					}
					board[k][j-1]=0;
					break;
				} else {
					// 진행
				}
			}
		}
		/* 크레인이 내려옴 */

		return answer;
	}
	public static void main(String[] args) {
		Crane_level1 cr=new Crane_level1();
		int board[][] = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
		int moves[]= {1,5,3,5,1,2,1,4};	
		
		int result = cr.solution(board, moves);
		System.out.println(result);
	}
}
