package dataStructures.tree.bst;

public interface BinarySearchTreeAPI {
    boolean isEmpty();
    boolean isTerminal(Node node);
    Node searchNode(int value);
    Node iterativeSearch(int value);
    Node recursiveSearch(int value);
    Node getRightSubtreeMin(Node node);
    int min();
    int max();
    int getDegree(Node node);
    void insertNode(int value);
    void iterativeDeleteNode(int value);
    void recursiveDeleteNode(int value);
    void printTreeByInorder();
    void printTreeByInorder(Node node);
}
