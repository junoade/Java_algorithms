package lecture.week6;
/**
 * 컴퓨터알고리즘과실습 _ 주종화 교수님
 * 2017112095 컴퓨터공학과 최준호
 * 과제6_더블 링크드 리스트의 노드 객체를 위한 Node 클래스 작성
 */
public class Node {
    private int key; //key value of a node in a Binary Tree.

    private Node left; // a pointer of  left child
    private Node right; // a pointer of right child

    public Node(int newItem, Node prev, Node next){
        key=newItem;
        this.left=prev;
        this.right=next;
    }
    public Node(){
        key=0;
        left=null;
        right=null;
    }
    //getter And Setter
    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
