package lecture.week8;
/**
 * 컴퓨터알고리즘과실습3 주종화 교수님
 * 2017112095 컴퓨터공학과 최준호
 * 8주차 Red Black Tree Insertion 구현을 위한 RBTree 클래스
 */

public class RBTree {
    Node root;
    Node nil = null;

    public Node getRoot() {
        return root;
    }

    public RBTree(int newKey) {
        root = new Node(newKey, null, null);
    }

    public RBTree() {
        root = null;
    }

    /* insert 하고나서 Red Black 규칙 만족하게 만들기 */
    public void insert(int key) {

        Node newNode = new Node(key, null, null);
        Node y = nil;
        Node x = root;
        while (x != nil) {
            y = x;
            if (newNode.getKey() < x.getKey())
                x = x.getLeft();
            else
                x = x.getRight();
        }
        newNode.setParent(y);
        if (y == nil) {// y는 newNode의 부모가 됨
            root = newNode;
            root.setColor('B');
        } else if (newNode.getKey() < y.getKey())
            y.setLeft(newNode);
        else
            y.setRight(newNode);
        /* 새로 추가된 노드와 nil 과 연결해줌 */
        newNode.setLeft(nil);
        newNode.setRight(nil);
        newNode.setColor('R');

        /*newNode에 대해서 RB-Insert FIXUP 해주기*/
        insertFixup(newNode);

    }

    /*newNode에 대해서 RB-Insert FIXUP*/
    public void insertFixup(Node z) {
        /*parent, uncle, grandparent 가 있는가? */

        Node uncle = new Node();
        Node grand = new Node();
        if (z != root) {
            while (z.getParent() != null && z.getParent().getColor() == 'R' ) { // 새로 insert한 컬러는 R라 이러면 RBTree 규칙 x . 고쳐줘야함
                grand = z.getParent().getParent();
                if (z.getParent() == grand.getLeft()) {//GrandParent의 왼쪽 자식이 z의 부모노드라면,
                    uncle = grand.getRight(); // Uncle 노드는 GrandParent노드의 오른쪽
                    if(uncle == null){
                        uncle = new Node();
                        uncle.setColor('B'); // uncle이 없을 땐, B으로 고려함 (nil)
                    }
                    if (uncle.getColor() == 'R') { // Uncle노드의 color 가 Red라면, Color Flip
                        z.getParent().setColor('B');
                        uncle.setColor('B');
                        grand.setColor('R');
                        z = grand;
                    } else if (z == z.getParent().getRight()) { // blck 일때
                        z = z.getParent();
                        leftRotate(z);
                    }
                    if(z.getParent()!=null && z.getParent().getParent()!=null){
                    z.getParent().setColor('B');
                    z.getParent().getParent().setColor('R');
                    rightRotate(grand);
                    }

                } else { // right 와 left 를 바꾼경우 z.getParent() == grand.getRight()
                    uncle = grand.getLeft(); // uncle은 grandparent의 왼쪽
                    if(uncle == null){
                        uncle = new Node();
                        uncle.setColor('B'); // uncle이 없을 땐, B으로 고려함 (nil)
                    }
                    if (uncle.getColor() == 'R') {// Color Flip
                        z.getParent().setColor('B');
                        uncle.setColor('B');
                        grand.setColor('R');
                        z = grand;
                    } else if (z == z.getParent().getRight()) { // blck 일때
                        z = z.getParent();
                        leftRotate(z);
                    }
                    if(z.getParent()!=null && z.getParent().getParent()!=null){
                        z.getParent().setColor('B');
                        z.getParent().getParent().setColor('R');
                        rightRotate(grand);
                    }
                }
            }
            root.setColor('B');
        } else
            root.setColor('B');

    }

    public boolean hasGrand(Node p) {
        return p.getParent().getParent() != null;
    }

    /* 오른쪽에서 RB 규칙 안맞아 left rotate 하는 경우 */
    public void leftRotate(Node x) {
        Node y = x.getRight(); // y 설정
        x.setRight(y.getLeft()); // y의 왼쪽 서브 트리를 x의 오른쪽 서브 트리로 옮김
        if (y.getLeft() != nil)
            y.getLeft().setParent(x);
        y.setParent(x.getParent()); // x의 부모를 y로 연결
        if (x.getParent() == nil)
            root = y;
        else if (x == x.getParent().getLeft())
            x.getParent().setLeft(y);
        else
            x.getParent().setRight(y);

        y.setLeft(x); // x를 다시 y의 왼쪽에 둠
        x.setParent(y);
    }

    /* 왼쪽에서 RB 규칙 안맞아 right rotate 하는 경우 */
    public void rightRotate(Node x) {
        Node y = x.getLeft(); // y 설정
        x.setLeft(y.getRight()); // y의 오른쪽 서브 트리를 x의 왼쪽 서브 트리로 옮김
        if (y.getRight() != nil)
            y.getRight().setParent(x);
        y.setParent(x.getParent()); // x의 부모를 y로 연결
        if (x.getParent() == nil)
            root = y;
        else if (x == x.getParent().getLeft())
            x.getParent().setLeft(y);
        else
            x.getParent().setRight(y);

        y.setRight(x); // x를 다시 y의 오른쪽에 둠
        x.setParent(y);
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

    /* 인자로 받은 키값을 갖는 노드를 리턴 */
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
            System.out.print(" " + p.getKey() + "," + p.getColor());

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
