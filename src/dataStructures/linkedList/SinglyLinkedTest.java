package dataStructures.linkedList;

public class SinglyLinkedTest {

    static SinglyLinkedList<Integer> list;

    public static void testNodeAssigning(){
        Node<Integer> head = new Node<>(2);
        head.next = new Node<>(3);
        System.out.println(head.next.data);
    }

    public static void testAdd(){
        list = new SinglyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(99);
        testPrint();
    }

    public static void testAddByIdx(){
        list = new SinglyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(5);
        list.add(0, 99);
        list.add(2, 54);
        list.add(5, 110);
        testPrint();
    }

    public static void testAddAfter(){
        list = new SinglyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(5);
        list.addAfter(1, 99);
        testPrint();
    }

    public static void testPrint(){
        list.printList();
    }

    public static void main(String[] args){
        testNodeAssigning();
        testAdd();
        testAddByIdx();
        testAddAfter();
    }

}
