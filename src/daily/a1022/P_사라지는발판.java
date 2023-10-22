package daily.a1022;

public class P_사라지는발판 {
    int N, M; // in [1, 5]

    //상-하-좌-우
    final int[] dx = {-1, 1, 0, 0}; // r
    final int[] dy = {0, 0, -1, 1}; // c
    final int MIN = 0, MAX = Integer.MAX_VALUE / 2;

    static class Record {
        boolean canWin;
        // [헷갈렸던 것]
        // 현재 기록한 moveCnt에 대해 a_move, b_move 다 따로 기록해야할까?
        // 어떤 p가 승리 가능하면 최소 턴을 고를 것, p가 패배할 것이라면 최대 턴을 고를 것
        // 고로 한 moveCnt에 해당 선택들이 다 담겨져 있을 것
        int moveCnt;

        Record(boolean canWin, int moveCnt) {
            this.canWin = canWin;
            this.moveCnt = moveCnt;
        }

        @Override
        public String toString() {
            return String.format("Record{canWin: %s, moveCnt:%d}", canWin, moveCnt);
        }
    }


    public int solution(int[][] board, int[] aloc, int[] bloc) {
        // [규칙] A가 먼저 플레이
        N = board.length;
        M = board[0].length;
        Record answer = rec(board, aloc[0], aloc[1], bloc[0], bloc[1]);
        return answer.moveCnt;
    }

    // 현재 플레이어의 좌표{ax, ay}와 다음 플레이어의 좌표{bx, by}가 주어졌을 때
    // 게임이 다음에 종료되는 깊이까지 간 다음
    // 그때부터 현재 플레이어가 이기는 지, 지는 지에 따라
    // 최적의 결과를 리턴해서 최종 반환
    // f(b, ax, ay, bx, by) : Record;
    Record rec(int[][] board, int ax, int ay, int bx, int by) {

        // base case #1 : 현재 상태에서 다음 상태로 전이할 수 없다.
        if(isTerm(board, ax, ay)) {
            //현재 플레이어는 승리를 결정할 수(이길 수) 없다. 움직일 수 없다.
            return new Record(false, 0);
        }
        // base case #2 : 현재 플레이어와 다음 플레이어의 위치가 동일한 곳이다.
        // -> 현재 플레이어가 이동하고나면 다음 플레이어는 진다.
        // -> base case #1이 앞서 수행될 것; base case 실행 순서가 중요한 경우
        if(ax == bx && ay == by) {
            return new Record(true, 1);
        }

        // local 결과 값 기록
        boolean canWin = false;
        int minMove = MAX, maxMove = MIN;

        for(int d = 0; d < dx.length; d++) {
            int nx = ax + dx[d];
            int ny = ay + dy[d];

            if(isOutbound(nx, ny) || board[nx][ny] == 0) {
                continue;
            }

            // 다음 상태로 전이(이동) 가능한 경우
            // 다음 탐색 깊이로 가기 전, 발판을 0으로 만들고
            // 갔다와서 다른 탐색에 영향 주지 않도록 1로 만듬
            board[ax][ay] = 0;
            Record result = rec(board, bx, by, nx, ny); // 상대방이 움직이고 난 후의 결과

            board[ax][ay] = 1;

            // 로컬 최대, 최소 갱신
            int nextMove = result.moveCnt + 1;
            if(!result.canWin) {
                // 다음 플레이어가 수행한 결과
                // 현재 플레이어가 이길 수 있을 때;
                // 최적화된 선택 : 최소 이동 횟수 선택
                canWin = true;
                minMove = Math.min(minMove, nextMove);
            } else {
                // 다음 플레이어가 수행한 결과
                // 현재 플레이어는 이길 수 없을 때
                // 최적화된 선택 : 최대 이동 횟수 선택
                maxMove = Math.max(maxMove, nextMove);
            }
        }
        return new Record(canWin, canWin ? minMove : maxMove);
    }

    boolean isTerm(int[][] board, int x, int y) {
        for(int d = 0; d < dx.length; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            // [my-style] : 하나라도 이동할 수 있는 경우라면
            if(!isOutbound(nx, ny) && board[nx][ny] == 1) {
                return false;
            }
        }
        return true;
    }


    boolean isOutbound(int x, int y) {
        return (x < 0 || x >= N) || (y < 0 || y >= M);
    }
}
