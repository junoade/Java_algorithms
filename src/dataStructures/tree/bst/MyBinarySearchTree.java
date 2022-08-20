package dataStructures.tree.bst;

public class MyBinarySearchTree implements BinarySearchTreeAPI {
    Node root = null;
    StringBuilder sb;

    /**
     * @param value
     * @return
     */
    @Override
    public void insertNode(int value) {
        /* Case 1) Node 가 하나도 없다면? */
        if (isEmpty()) {
            this.root = new Node(value);
        } else { /* Case 2) Node가 하나 이상 들어가 있을 때, left subtree, right subtree 조사 */
            iterativeSearch(value);
        }
    }

    /**
     * 특정 key값을 갖는 Node를 찾아, 반환한다
     *
     * @param value
     * @return
     */
    @Override
    public Node searchNode(int value) {
        if (isEmpty()) {
            return null;
        } else {
            return iterativeSearch(value);
        }
    }

    /**
     * root 참조변수가 null이면 true 반환
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * 반복을 통해 left subtree, right subtree를 탐색하며 새로운 노드가 들어갈 자리를 찾음
     */
    @Override
    public Node iterativeSearch(int value) {
        Node cursor = this.root;
        while (true) {
            // 현재 Node의 왼쪽에 Node가 들어가야 할 때
            if (value < cursor.value) {
                if (cursor.left != null) {
                    cursor = cursor.left;
                } else {
                    cursor.left = new Node(value);
                    break;
                }
            } else if (value > cursor.value) { // 오른쪽 Node에 들어가야 할 때
                if (cursor.right != null) {
                    cursor = cursor.right;
                } else {
                    cursor.right = new Node(value);
                    break;
                }
            } else {
                return cursor;
            }
        }
        return null;
    }

    /**
     * 재귀적인 방법으로 찾음
     */
    @Override
    public Node recursiveSearch(int value) {
        return null;
    }

    /**
     * 재귀함수를 호출하는 부분
     */
    @Override
    public void printTreeByInorder() {
        sb = new StringBuilder();
        printTreeByInorder(root);
    }

    /**
     * recursive한 방법으로 출력
     */
    @Override
    public void printTreeByInorder(Node node) {
        if (node == null)
            return;

        printTreeByInorder(node.left);
        sb.append((char)node.value).append(" ");
        printTreeByInorder(node.right);
    }
}
