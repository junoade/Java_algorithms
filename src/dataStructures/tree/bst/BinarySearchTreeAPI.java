package dataStructures.tree.bst;

public interface BinarySearchTreeAPI {
    void insertNode(int value);
    Node searchNode(int value);
    boolean isEmpty();
    Node iterativeSearch(int value);
    Node recursiveSearch(int value);
}
