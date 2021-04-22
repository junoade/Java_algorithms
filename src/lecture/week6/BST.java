package lecture.week6;

/**
 * 컴퓨터알고리즘과실습 _ 주종화 교수님
 * 2017112095 컴퓨터공학과 최준호
 * 과제6_Binary Search Tree 클래스 작성
 */
public class BST {
    Node root;

    public Node getRoot() {
        return root;
    }

    public BST(int newKey) {
        root = new Node(newKey, null, null);
    }

    public BST() {
        root = null;
    }

    /* insert */
    public void insert(int key) {

        Node newNode = new Node(key, null, null);
        if (root == null) { // Empty라면
            root = newNode;
        } else {
            Node findNode = root; //root부터 탐색할 노드,
            Node parent; //그러한 findNode의 부모
            while (true) {
                parent = findNode;

                if (key < parent.getKey()) { // x<y 일 때
                    findNode = parent.getLeft();// 왼쪽으로 이동
                    if (findNode == null) {
                        //newNode와 이어주기.
                        parent.setLeft(newNode);
                        return;
                    }
                } else {
                    findNode = parent.getRight();
                    if (findNode == null) {
                        //newNode와 이어주기.
                        parent.setRight(newNode);
                        return;
                    }
                }
            }
        }

    }

    /* getChilds */
    public Node getNode(int searchKey) {
        return getNodeSub(root, searchKey);
    }

    public Node getNodeSub(Node p, int searchKey) {
        if (p != null) {
            if (searchKey > p.getKey()) { // x.compareTo(y) =1 이면 x>y
                /*오른쪽 트리 탐색*/
                return getNodeSub(p.getRight(), searchKey);
            } else if (searchKey < p.getKey()) { //탐색키가 현재 노드의 키보다 작은 경우
                /*왼쪽 트리 탐색 */
                return getNodeSub(p.getLeft(), searchKey);
            } else {
                //base case) searchKey가 있다
                return p;
            }
        } else {
            return null;
        }

    }

    /* 인자로 받은 키값이 현재 BST에 존재하는지 boolean 값으로 리턴 */
    public boolean findNode(int searchKey) {
        return findNodeSub(root, searchKey);
    }

    public boolean findNodeSub(Node p, int searchKey) {
        //base case1) searchKey 발견, 또는 트리 Empty()
        boolean result = false;
        if (p == null) {
            return false;
        } else {
            if (searchKey > p.getKey()) { // x.compareTo(y) =1 이면 x>y
                /*오른쪽 트리 탐색*/
                p = p.getRight();
                return findNodeSub(p, searchKey);   //return 안하면.. 자바는 c++과 다르게..다시 돌아가나봐..
            } else if (searchKey < p.getKey()) { //탐색키가 현재 노드의 키보다 작은 경우
                /*왼쪽 트리 탐색 */
                p = p.getLeft();
                return findNodeSub(p, searchKey);
            } else {
                //base case) searchKey가 있다
                p.setKey(searchKey);
                return true;
            }

        }

    }

    /* delete Node*/
    public void delete(int key) {
        root = deleteSub(root, key);
    }

    public Node deleteSub(Node p, int key) {
        /*base case) 트리가 비어있거나 못 찾는다면,*/
        if (p == null) {
            System.out.println("트리가 비었거나, 해당 키값을 갖는 노드가 없습니다.");
            return p;
        }
        /* 지금 찾으러 갑니다, 키가 노드의 키값보다 크면,*/
        if (key > p.getKey()) {
            p.setRight(deleteSub(p.getRight(), key));
        } else if (key < p.getKey()) {
            p.setLeft(deleteSub(p.getLeft(), key));
        } else {

            // case 자식 1개만 있거나 없는 경우
            if (p.getLeft() == null)
                return p.getRight();
            else if (p.getRight() == null)
                return p.getLeft();

            // case 3 자식 2개 있는 경우, 오른쪽 서브트리의 가장 작은 키값을 갖는 노드랑 이어줌
            p.setKey(findMostLeft(p.getRight()));

            // Delete 
            p.setRight(deleteSub(p.getRight(), p.getKey()));
        }
        return p;
    }
    /* deleteSub에서 case3 를 위해 가장 작은 키값을 갖는 찾는 메소드*/
    public int findMostLeft(Node p) {
        int min = p.getKey();
        while (p.getLeft() != null) {
            min = p.getLeft().getKey();
            p = p.getLeft();
        }
        return min;
    }

    /*show BST strucutre*/
    public void show() {
        if (root == null)
            System.out.println("Empty Tree");
        else {
            System.out.println();
            showSub(root, 1);
            System.out.println();
        }
    }

    public void showSub(Node p, int level) {
        int j; //loop counter
        if (p != null) {
            /* Output right subtree*/
            showSub(p.getRight(), level + 1);

            for (j = 0; j < level; j++)
                System.out.print("\t"); // tab
            System.out.print(" " + p.getKey());

            /* < means a branch between left child and right child */
            if ((p.getLeft() != null) && p.getRight() != null)
                System.out.print("<");
                /* / and \\ means edges toward a node located in right or left */
            else if (p.getRight() != null)
                System.out.print("/");
            else if (p.getLeft() != null)
                System.out.print("\\");
            System.out.println();

            /* Output left subtree */
            showSub(p.getLeft(), level + 1);
        }
    }


}
