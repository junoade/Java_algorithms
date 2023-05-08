package category.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * --------------------------------------------------------------<br/>
 * <b>프로그래머스 - 길 찾기 게임</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 2차원 좌표 값 입력 순서에 따라 키 값 부여<br/>
 * y 값으로 먼저 내림차순 정렬, x값에 따라서 오름차순 정렬<br/>
 * 트리 구성 - 포인터를 이용한 리스트 구성 방식<br/>
 * 재귀를 이용한 전위, 중위 순행 알고리즘 구현<br/>
 * Integer 타입의 리스트를 int형 배열로 변환 후 반환<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리 103MB, 실행시간 약 27ms<br/>
 * --------------------------------------------------------------
 */
public class P_FindRoads {
    static class Node {
        int key;
        int x;
        int y;

        // Node parent;
        Node left;
        Node right;

        Node(int key, int x, int y) {
            this.key = key;
            this.x = x;
            this.y = y;
            // parent = null;
            left = null;
            right = null;
        }

        public String toString() {
            return String.format("%d(%d, %d)", key, x, y);
        }
    }

    Node root;
    Node[] waitNodes;

    public int[][] solution(int[][] nodeinfo) {
        // 좌표값 정렬 후 노드 생성
        waitNodes = initNodes(nodeinfo);

        // [일단 없이] 트리 생성
        initBinaryTree(waitNodes);

        // 트리 순회 결과 기록
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        getPreorder(root, list1);
        getInorder(root, list2);

        return new int[][]{convert(list1), convert(list2)};
    }

    int[] convert(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    // root - leftSubtree - rightSubtree
    void getPreorder(Node target, List<Integer> visited) {
        // base case: leaf node일 때
        if (target == null) {
            return;
        }
        // root
        visited.add(target.key);
        // left
        getPreorder(target.left, visited);
        // right
        getPreorder(target.right, visited);
    }

    void getInorder(Node target, List<Integer> visited) {
        // base case: leaf node일 때
        if (target == null) {
            return;
        }
        // left
        getInorder(target.left, visited);
        // right
        getInorder(target.right, visited);
        // root
        visited.add(target.key);
    }


    // node info를 key 값, 좌표값을 갖는 Node 배열로 정렬 후 전달
    Node[] initNodes(int[][] arr) {
        Node[] result = new Node[arr.length];

        int idx = 0;
        for (int[] e : arr) {
            result[idx] = new Node(idx + 1, e[0], e[1]);
            idx++;
        }

        // System.out.println(Arrays.toString(result));
        Arrays.sort(result, (r1, r2) -> {
            int x1 = r1.x, y1 = r1.y;
            int x2 = r2.x, y2 = r2.y;
            // 정렬 기준1) y값 기준으로 내림차순 정렬
            if (y1 != y2) {
                return Integer.compare(y2, y1);
            }
            // 정렬 기준2) x값 기준으로 오름차순 정렬
            return Integer.compare(x1, x2); //
        });

        // System.out.println(Arrays.toString(result));
        return result;
    }

    void initBinaryTree(Node[] arr) {
        root = arr[0];
        for (int i = 1; i < arr.length; i++) {
            insert(root, arr[i]);
        }
    }

    void insert(Node p, Node t) {
        // 오른쪽
        if (p.x < t.x) {
            // base case: 오른쪽 자식 노드에 삽입
            if (p.right == null) {
                p.right = t;
                return;
            }
            insert(p.right, t);
        } else {
            // base case: 왼쪽 자식 노드에 삽입
            if (p.left == null) {
                p.left = t;
                return;
            }
            insert(p.left, t);
        }
    }
}
