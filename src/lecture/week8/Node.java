package lecture.week8;

/**
 * 컴퓨터알고리즘과실습3 주종화 교수님
 * 2017112095 컴퓨터공학과 최준호
 * 8주차 Red Black Tree Insertion 구현을 위한 Node 클래스
 */

public class Node {
    private int key; //key value of a node in a Binary Tree.
    private char color; // character type to denote a color of each node.

    private Node parent; // address of a parent node
    private Node left; // a pointer of  left child

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

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
    public void setColor(char color){
        this.color = color;
    }
    public char getColor(){
        return this.color;
    }
}
