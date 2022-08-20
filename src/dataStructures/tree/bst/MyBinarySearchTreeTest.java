package dataStructures.tree.bst;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyBinarySearchTreeTest {

    MyBinarySearchTree bst;

    @Before
    public void initBST(){
        bst = new MyBinarySearchTree();
    }


    @Test
    public void testInsertNode(){
        bst.insertNode(10);
        assertEquals(10, bst.root.value);
    }

    @Test
    public void testSearchNode(){
        int key = 10;
        bst.insertNode(5);
        bst.insertNode(1);
        bst.insertNode(10);
        bst.insertNode(77);
        bst.insertNode(8);

        assertEquals(10, bst.searchNode(10).value);
    }
}
