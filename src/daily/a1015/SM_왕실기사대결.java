package daily.a1015;

import java.io.*;
import java.util.*;

public class SM_왕실기사대결 {
    static class Knight {
        int key;
        int r;
        int c;
        int h;
        int w;
        int k;
        final int K;

        public Knight(int key, int r, int c, int h, int w, int k, int k2) {
            super();
            this.key = key;
            this.r = r;
            this.c = c;
            this.h = h;
            this.w = w;
            this.k = k;
            K = k2;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Knight [key=").append(key).append(", r=").append(r).append(", c=").append(c).append(", h=")
                    .append(h).append(", w=").append(w).append(", k=").append(k).append(", K=").append(K).append("]");
            return builder.toString();
        }

    }

    static int L, N, Q;

    static int[][] maps;
    static int[][] knight_maps;

    static final int EMPTY = 0;
    static final int MINE = 1;
    static final int WALL = 2;

    // 상, 우, 하, 좌
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static boolean hasStucked;

    static int solution(Map<Integer, Knight> knights, int[][] cmds) {
        for(int[] cmd : cmds) {
            int key = cmd[0], dir = cmd[1];

            if(!knights.containsKey(key)) {
                continue;
            }

            hasStucked = false;

            Queue<Knight> affected = new ArrayDeque<>();
            move(knights, affected, key, dir);

            if(hasStucked) {
                continue;
            }

            while(!affected.isEmpty()) {
                Knight cur = affected.poll();

                // 좌상단 좌표 수정
                cur.r += dx[dir];
                cur.c += dy[dir];

                // [규칙]
                if(key == cur.key) {
                    continue;
                }

                // 함정 수 계산 후
                countMines(cur);
                // 그만큼 데미지 계산
                // 내구도 0 이하 되면 아웃
                if(cur.k <= 0) {
                    knights.remove(cur.key);
                }
            }


            // 맵 갱신 필요
            updateKnightMaps(knights);
        }

        return getResult(knights);
    }

    /**
     * 재귀 호출을 통해 해당 기사가 해당 방향으로 다른 기사들을 밀친다.
     * @param knights
     * @param q
     * @param key
     */
    static void move(Map<Integer, Knight> knights, Queue<Knight> q, int key, int dir) {

        // 만약 knights가 없는데 밀려할 때
        if(!knights.containsKey(key)) return;

        Knight knight = knights.get(key);

        // visit 체크
        if(q.contains(knight)) {
            return;
        }
        q.offer(knight);

        Set<Integer> adj = new HashSet<>();
        // 상 하 일 때
        if(dir == 0 || dir == 2) {
            moveUpOrDown(adj, knight, dir);
        }

        // 우 좌 일 때
        else if(dir == 1 || dir == 3) {
            moveRightOrLeft(adj, knight, dir);

        }

        Iterator<Integer> iter = adj.iterator();
        while(iter.hasNext()) {
            int nextKey = iter.next();
            move(knights, q, nextKey, dir);
        }
    }


    private static void countMines(Knight knight) {
        final int x = knight.r, y = knight.c;

        int count = 0;
        for(int i = x; i < x + knight.h; i++) {
            for(int j = y; j < y + knight.w; j++) {
                if(maps[i][j] == MINE) {
                    count++;
                }
            }
        }

        knight.k -= count;
    }

    private static void updateKnightMaps(Map<Integer, Knight> knights) {
        initKnightMaps();

        for(Map.Entry<Integer, Knight> entry : knights.entrySet()) {
            Knight knight = entry.getValue();
            markKnight(knight);
        }
    }

    private static void initKnightMaps() {
        for(int i = 1; i <= L; i++) {
            for(int j = 1; j <= L; j++) {
                knight_maps[i][j] = 0;
            }
        }
    }

    private static int getResult(Map<Integer, Knight> knights) {
        int result = 0;

        for(Map.Entry<Integer, Knight> entry : knights.entrySet()) {
            Knight knight = entry.getValue();
            result += knight.K - knight.k; // k로 잘 뺐겠지???
        }

        return result;
    }

    private static void moveUpOrDown(Set<Integer> adj, Knight knight, int dir) {
        int x = knight.r, y = knight.c;
        // 하 방향일 땐 knight의 height를 고려
        if(dir == 2) {
            x += knight.h - 1; // 시험볼때 -1 이거 했나???
        }

        x += dx[dir];
        y += dy[dir];

        for(int col = y; col < y + knight.w; col++) {
            if(isOutbound(x, col)) {
                // 밀 수 없는 상태
                hasStucked = true;
                return;
            }

            if(maps[x][col] == WALL) {
                // 밀 수 없는 상태
                hasStucked = true;
                return;
            }

            adj.add(knight_maps[x][col]);

        }

    }

    private static void moveRightOrLeft(Set<Integer> adj, Knight knight, int dir) {
        int x = knight.r, y = knight.c;
        // 우 방향일 땐 knight의 width를 고려
        if(dir == 1) {
            y += knight.w - 1; // 시험볼때 -1 이거 했나???
        }

        x += dx[dir];
        y += dy[dir];

        for(int row = x; row < x + knight.h; row++) {
            if(isOutbound(row, y)) {
                // 밀 수 없는 상태
                hasStucked = true;
                return;
            }

            if(maps[row][y] == WALL) {
                // 밀 수 없는 상태
                hasStucked = true;
                return;
            }

            adj.add(knight_maps[row][y]);
        }
    }

    private static boolean isOutbound(int x, int y) {
        return (x <= 0 || x > L) || (y <= 0 || y > L); // 1's based indexing 고려
    }


    public static void main(String[] args) throws Exception {

        // System.setIn(new FileInputStream("src\\a1015\\self\\input_05.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        maps = new int[L + 1][L + 1]; // 1's based indexing 방법 고려
        knight_maps = new int[L + 1][L + 1];

        for(int i = 1; i <= L; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= L; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Map<Integer, Knight> knights = new HashMap<>();
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            Knight temp = new Knight(i, r, c, h, w, k, k);
            knights.put(i, temp);

            // 여기서 markKnight() 호출
            markKnight(temp);
        }


        int[][] cmds = new int[Q][2];
        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            cmds[i] = new int[] {key, dir};
        }


        System.out.println(solution(knights, cmds));
    }

    /**
     * 기사 번호를 기사맵에 기록
     * @param knight
     */
    private static void markKnight(Knight knight) {
        final int x = knight.r, y = knight.c;

        for(int i = x; i < x + knight.h; i++) {
            for(int j = y; j < y + knight.w; j++) {
                knight_maps[i][j] = knight.key;
            }
        }
    }
}
