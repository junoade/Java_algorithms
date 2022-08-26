package dataStructures.tree.bst;

import java.util.NoSuchElementException;

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
     * Iterative하게 구현
     *
     * @param value
     */
    @Override
    public void iterativeDeleteNode(int value) {
        // 일단 부모 - 자식 의 두 포인터가 필요하다
        Node parent = this.root;
        Node cursor = this.root;
        boolean isFound = false;
        if (isEmpty())
            return;

        while (cursor != null) {
            if (value < cursor.value) {
                if (cursor.left != null) {
                    parent = cursor;
                    cursor = cursor.left;
                }
            } else if (value > cursor.value) {
                if (cursor.right != null) {
                    parent = cursor;
                    cursor = cursor.right;
                }
            } else {
                isFound = true;
                break;
            }
        }

        if (!isFound) {
            throw new NoSuchElementException();
        }

        // 현재 시점에서, cursor의 부모는 parent 노드 포인터
        int degree = getDegree(cursor);
        if (degree == 0) { // terminal node이면 그냥 null
            if (value < parent.value) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        /* Case 2 : 삭제할 Node가 하나의 Child 노드만을 갖는 경우 */
        else if (degree == 1) {
            if (value < parent.value) { // Case 2-1 : parent 노드의 left node에 있는 경우
                if (cursor.left != null) {
                    parent.left = cursor.left;
                } else {
                    parent.left = cursor.right;
                }
            } else { // Case 2-2 : parent 노드의 right node에 있는 경우
                if (cursor.left != null) {
                    parent.right = cursor.left;
                } else {
                    parent.right = cursor.right;
                }
            }
        }
        /* Case 3 : 삭제할 Node가 두 개의 Child 노드만을 갖을 때 */
        else if (degree == 2) {
            Node subParent = cursor.right;
            Node subLeast = cursor.right;
            Node subTerminal = null;
            while (subLeast.left != null) {
                subParent = subLeast;
                subLeast = subLeast.left;
            }

            if (subLeast.right != null) { // 오른쪽 Subtree의 least 키 값을 갖는 노드의 오른쪽 자식이 있다?
                subTerminal = subLeast.right;
            }

            /* 1차 swap */
            subLeast.left = cursor.left;
            parent.right = subLeast;

            /* 2차 swap */
            if (subTerminal != null) {
                subLeast.right = cursor.right;
                subParent.left = subTerminal;
            }
        }
    }

    @Override
    public void recursiveDeleteNode(int value) {

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
     * node가 단일 노드인지 반환
     *
     * @param node
     * @return boolean
     */
    @Override
    public boolean isTerminal(Node node) {
        return node.left == null && node.right == null;
    }

    @Override
    public int getDegree(Node node) {
        int result = 0;
        if (node.left != null)
            result++;
        if (node.right != null)
            result++;
        return result;
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

    @Override
    public Node getRightSubtreeMin(Node node) {
        Node cursor = node;
        while (cursor.left != null) {
            cursor = cursor.left;
        }
        return cursor;
    }

    /**
     * 이진 탐색 트리의 최소값을 반환한다.
     *
     * @return int min
     */
    @Override
    public int min() {
        Node cursor = this.root;
        while (cursor.left != null) {
            cursor = cursor.left;
        }
        return cursor.value;
    }

    /**
     * 이진 탐색 트리의 최대값을 반환한다.
     *
     * @return
     */
    @Override
    public int max() {
        Node cursor = this.root;
        while (cursor.right != null) {
            cursor = cursor.right;
        }
        return cursor.value;
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
        if (isNumeric((char) node.value))
            sb.append((char) node.value).append(" ");
        else
            sb.append(node.value).append(" ");
        printTreeByInorder(node.right);
    }

    boolean isNumeric(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

}
