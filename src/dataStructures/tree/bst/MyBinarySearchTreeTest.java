package dataStructures.tree.bst;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyBinarySearchTreeTest {

    MyBinarySearchTree bst;

    @Before
    public void initBST() {
        bst = new MyBinarySearchTree();
    }

    public void insertTestSample(){
        bst.insertNode('A');
        bst.insertNode('B');
        bst.insertNode('D');
        bst.insertNode('C');
        bst.insertNode('E');
        bst.insertNode('F');
        bst.insertNode('G');
        bst.insertNode('I');
        bst.insertNode('H');
    }

    public void insertTestSample2(){
        bst.insertNode(10);
        bst.insertNode(7);
        bst.insertNode(6);
        bst.insertNode(8);
        bst.insertNode(15);
        bst.insertNode(18);
        bst.insertNode(13);
        bst.insertNode(11);
        bst.insertNode(14);
        bst.insertNode(16);
        bst.insertNode(19);
        bst.insertNode(17);
    }


    @Test
    public void testInsertNode() {
        bst.insertNode(10);
        assertEquals(10, bst.root.value);
    }

    @Test
    public void testSearchNode() {
        bst.insertNode(5);
        bst.insertNode(1);
        bst.insertNode(10);
        bst.insertNode(77);
        bst.insertNode(8);

        assertEquals(10, bst.searchNode(10).value);
    }

    @Test
    public void testFindMinValue(){
        insertTestSample();
        assertEquals("A", Character.toString((char)bst.min()));
    }

    @Test
    public void testFindMaxValue(){
        insertTestSample();
        assertEquals("I", Character.toString((char)bst.max()));
    }

    @Test
    public void testPrintTreeByInorder() {
        insertTestSample();
        bst.printTreeByInorder();
        String result = bst.sb.toString();
        assertEquals("A B C D E F G H I ", result);
    }

    @Test
    public void testDeleteNodeWithNoChild(){
        insertTestSample2();
        bst.printTreeByInorder();
        bst.iterativeDeleteNode(6);
        bst.printTreeByInorder();
        String result = bst.sb.toString();
        assertEquals("7 8 10 11 13 14 15 16 17 18 19 ", result);
    }

    @Test
    public void testDeleteNodeWithOneChild(){
        insertTestSample2();
        bst.printTreeByInorder();
        bst.iterativeDeleteNode(16);
        bst.printTreeByInorder();
        String result = bst.sb.toString();
        assertEquals("6 7 8 10 11 13 14 15 17 18 19 ", result);
    }

    @Test
    public void testDeleteNodeWithTwoChild(){
        insertTestSample2();
        bst.printTreeByInorder();
        bst.iterativeDeleteNode(18);
        bst.printTreeByInorder();
        String result = bst.sb.toString();
        assertEquals("6 7 8 10 11 13 14 15 16 17 19 ", result);
    }
}
