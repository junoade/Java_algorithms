package category.search;

import java.io.*;
import java.util.*;

/**
 * --------------------------------------------------------------<br/>
 * <b> 코드트리 - 포탑 부수기</b><br/>
 * <a href="https://www.codetree.ai/training-field/frequent-problems/destroy-the-turret/description?page=3&pageSize=20&username=ajchoi0928">https://www.codetree.ai/training-field/frequent-problems/destroy-the-turret/description?page=3&pageSize=20&username=ajchoi0928</a>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 시뮬레이션 로직 구현 <br/>
 * BFS 탐색 <br/>
 * 최단 경로 역순 탐색 <br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 주요 static 변수들,
 * Turret객체와 기록용 테이블 정적 변수들,
 * 시뮬레이션 로직과 관련된 메소드들,
 * - K 시간이 지나면서 turn이 증가되도록(기존에는 이전 recentK 배열값에 +1 만 해줘서 틀렸음
 * - 문제 조건 놓치지 말자 {최단 거리가 같다면 어떻게 한다했지??}
 * BFD 탐색과 최단 경로 역순 탐색 알고리즘
 * --------------------------------------------------------------<br/>
 * <b> 채점 성공</b><br/>
 * <p> 메모리 24MB / 80 MB , 실행시간  294ms / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class CT_TurretWar {
    static class Turret {
        // power, K, r, c
        int power;
        int recentTime;
        int r;
        int c;
        boolean isAffected;

        Turret(int power, int recentTime, int r, int c) { //
            this.power = power;
            this.recentTime = recentTime;
            this.r = r;
            this.c = c;
            isAffected = false;
        }

        // 관련 필요 메소드 선언
        void setAffectionOnTrue() {
            isAffected = true;
        }

        void setAffectionOnFalse() {
            isAffected = false;
        }

        void increase(int P) {
            if(power > 0) {
                power += P;
                arr[r][c] = power;
            }
        }

        void damaged(int P) {
            power -= P;
            if(power < 0) {
                power = 0;
            }
            arr[r][c] = power;
            setAffectionOnTrue();
        }

        void awakenTurret(int P) {
            increase(P);
            recentK[r][c] = turn;
            recentTime = turn; // turn 만큼 증가
            setAffectionOnTrue();
        }

        @Override
        public String toString() {
            return String.format("{power:%d, recentTime:%d, pos:(%d, %d), isAffected : %d}", power, recentTime, r, c, (isAffected ? 1 : 0));
        }

    }

    // 오름차순 정렬
    static class SortTurret implements Comparator<Turret>{
        @Override
        public int compare(Turret o1, Turret o2) {
            if(o1.power != o2.power) return Integer.compare(o1.power, o2.power); // 이미 오름차순이라면 -1이 반환되겠지
            if(o1.recentTime != o2.recentTime) return Integer.compare(o2.recentTime, o1.recentTime); // recentTime에 대해선 내림차순 // 값이 제일 큰게 앞에 오도록
            if(o1.r + o1.c != o2.r + o2.c) return Integer.compare(o2.r + o2.c, o1.r + o1.c); // 좌표의 합에 대해선 내림차순 // 합이 제일 큰게 앞에 오도록
            return o2.c - o1.c;
        }
    }

    // 현재 터렛들의 power를 기록
    static int[][] arr;
    static int[][] recentK; // turn으로 기록하기!!
    static int turn;

    // bfs를 이용한 최단거리 구하기와 관련
    static boolean[][] visited;
    static int[][] backX;
    static int[][] backY;

    // 살아있는 터렛{power, K, r, c, 영향여부}들을 기록
    static List<Turret> maps;

    static Turret attacker;
    static Turret target;

    static int N, M, K;
    //주의!! r-d-l-u 순
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};
    //mid-r-d-l-u - nw - ne - ws - se 순
    static final int[] dx2 = {0, 0, 1, 0, -1, -1, -1, 1, 1};
    static final int[] dy2 = {0, 1, 0, -1, 0, 1, -1, -1,1};

    static void solution() {
        while(K > 0) {
            //현재 시점에 살아있는 터렛 기록
            initMaps();
            if(maps.size() < 2) {
                break;
            }

            // 각성/공격자 선정
            maps.sort(new SortTurret());
            attacker = getAttacker();
            awake();

            // 공격 대상자 선정
            target = getTarget();

            // 공격 진행
            attack();

            // 정비/비축
            elseTurretSavePower();

            // 다음 턴
            K--;
        }

    }

    static void initMaps() {
        maps = new ArrayList<>();
        visited = new boolean[N][M];
        //dist = new int[N][M];
        backX = new int[N][M];
        backY = new int[N][M];
        turn++; // K가 줄어들면 turn은 늘어남, 각성 기록과 관련

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(arr[i][j] > 0) {
                    maps.add(new Turret(arr[i][j], recentK[i][j], i, j));
                }
                // 벽이면 visited를 해야할까?
            }
        }
    }

    static Turret getAttacker() {
        if(maps.size() > 0) {
            return maps.get(0);
        }
        return null;
    }

    static Turret getTarget() {
        if(maps.size() > 0) {
            return maps.get(maps.size() - 1);
        }
        return null;
    }

    static void awake() {
        if(attacker == null) {
            System.out.println("Suppose Not");
            return ; }
        final int P = N + M;
        attacker.awakenTurret(P);
    }

    static void attack() {
        // 유효성 체크
        if(attacker == null || target == null) { return; }

        // 레이저 공격 가능여부 확인(최단 거리가 있는가?)
        if(findShortestPath()) {
            raserAttack();
        } else {
            bombAttack();
        }

    }


    // 여기가 문제
    static boolean findShortestPath() {
        // 좌표 관련
        int ax = attacker.r, ay = attacker.c;
        int tx = target.r, ty = target.c;

        // BFS 관련
        // 시작 노드 추가
        Queue<Turret> q = new LinkedList<>();
        // List<Turret> shortestPath = new ArrayList<>();

        visited[ax][ay] = true;
        q.add(attacker);
        // boolean result = false;

        while(!q.isEmpty()) {
            // 현재 가장 처음 들어온 노드에 대해
            int x = q.peek().r;
            int y = q.peek().c;
            // 큐에선 제거하고 최단 경로 조사 리스트엔 넣어줌
            q.poll();

            // 종료 조건
            if(x == tx && y == ty) {
                return true;
                // return shortestPath;
            }

            // 다음 상태 추가
            // 우 -> 하 -> 좌 -> 상 순서로 방문
            for(int dir = 0; dir < dx.length; dir++) {
                int nx = (x + dx[dir] + N) % N; // 좌표 조정도 고려
                int ny = (y + dy[dir] + M) % M;

                if(visited[nx][ny]) {
                    continue;
                }

                if(arr[nx][ny] == 0) {
                    continue;
                }

                visited[nx][ny] = true;
                // 이전 최단 경로 좌표 기록
                backX[nx][ny] = x;
                backY[nx][ny] = y;
                q.add(new Turret(arr[nx][ny], recentK[nx][ny], nx, ny));

            }
        }

        return false;
    }

    static void raserAttack() {
        final int P = attacker.power;
        int ax = attacker.r, ay = attacker.c;
        int tx = target.r, ty = target.c;
        target.damaged(P); //

        // 출발 노드, 도착 노드 제거
        // shortestPath.remove(0);
        // shortestPath.remove(shortestPath.size() - 1);

        // for(Turret n : shortestPath) {
        //	n.damaged(P/2);
        // }

        int curX = backX[tx][ty];
        int curY = backY[tx][ty];

        while(!(curX == ax && curY == ay)) {
            Turret temp = getTurret(curX, curY);
            if(temp != null) {
                temp.damaged(P/2);
            }

            int nextX = backX[curX][curY];
            int nextY = backY[curX][curY];
            curX = nextX;
            curY = nextY;
        }
    }

    // 타겟을 중앙으로 3x3 범위에 대해 공격
    static void bombAttack() {
        final int P = attacker.power;
        int ax = attacker.r, ay = attacker.c;
        int tx = target.r, ty = target.c;

        for(int dir = 0; dir < dx2.length; dir++) {
            int nx = (tx + dx2[dir] + N) % N; // 좌표 조정
            int ny = (ty + dy2[dir] + M) % M;

            // 자기 자신이면
            if(nx == ax && ny == ay) {
                continue;
            }

            // 타겟일 때
            if(nx == tx && ny == ty) {
                target.damaged(P); // 객체 뿐만 아니라 기록용 2차원 배열까지 갱신
            } else {
                Turret temp = getTurret(nx, ny);
                if(temp != null) {
                    temp.damaged(P/2); // 객체 뿐만 아니라 기록용 2차원 배열까지 갱신
                }
            }
        }
    }


    static void elseTurretSavePower() {
        for(Turret t : maps) {
            if(!t.isAffected) {
                t.increase(1);
                // t.setAffectionOnFalse(); // 어짜피 초기화 되어서 상관 X // 그리고 여기 오려면 이미 false임
            }
        }
    }

    private static Turret getTurret(int x, int y) {
        for(Turret t: maps) {
            if(t.r == x && t.c == y) {
                return t;
            }
        }
        return null;
    }

    /* 입력 관련 */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // int T = Integer.parseInt(br.readLine());

        // for(int i = 1; i <= T; i++) {
        int[] infos = getArray(br);
        N = infos[0]; M = infos[1]; K = infos[2];
        init2DArray(br);
        solution();

        System.out.println(getMax());
        // }
    }

    private static int[] getArray(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    }

    private static void init2DArray(BufferedReader br) throws IOException {
        arr = new int[N][M];
        recentK = new int[N][M];
        turn = 0;
        for(int i = 0; i < N; i++) {
            arr[i] = getArray(br);
        }
    }

    private static int getMax() {
        int result = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                result = Math.max(result, arr[i][j]);
            }
        }
        return result;
    }
}
