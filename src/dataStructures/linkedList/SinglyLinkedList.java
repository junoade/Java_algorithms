package dataStructures.linkedList;

/**
 * <b>0812 - 오랜만에 직접 링크드 리스트 구현 복습</b>
 *
 * @param <T>
 */
class Node<T> {
    T data;
    Node<T> next;

    Node(T data) {
        this.data = data;
    }
}

public class SinglyLinkedList<T> {
    Node<T> head;

    /* 삽입 */
    public void add(T data) {
        if (isEmpty()) {
            head = new Node<>(data);
        } else {
            Node<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node<>(data); // 오랜만에 해봐서 순간 놓침;
        }
    }

    public void add(int idx, T data) {
        // int count = 0;
        Node<T> newNode = new Node<>(data);

        if (!isEmpty()) {
            Node<T> temp = head;
            if (idx == 0) {
                newNode.next = temp;
                head = newNode;
            } else {
                for(int count = 1; count < idx -1; count++){
                    temp = temp.next;
                }
                newNode.next = temp.next;
                temp.next = newNode;
            }
        }
    }

    public void addAfter(T target, T data) {
        /* Target를 키값으로 갖는 노드가 존재하는가 ? */
        Node<T> searched = searchKey(target);
        if (searched != null) {
            Node<T> newNode = new Node<>(data);
            newNode.next = searched.next;
            searched.next = newNode;
        } else {
            add(data);
        }
    }

    /**
     * O(N)
     *
     * @param data
     * @return
     */
    public Node<T> searchKey(T data) {
        if (!isEmpty()) {
            Node<T> temp = head;
            while (temp != null) {
                if (temp.data == data)
                    return temp;
                temp = temp.next;
            }
        }
        return null;
    }

    /* 삭제 */
    public boolean delete(T target) {
        if (!isEmpty()) {
            if (head.data == target) {
                head = head.next;
            } else {
                Node<T> temp = head;
                while (temp.next != null) {
                    if (temp.next.data == target) {
                        temp.next = temp.next.next;
                        return true;
                    }
                    temp = temp.next;
                }
                return false;
            }
        }
        return false;
    }
    /* 갱신 */

    /* empty 여부 */
    public boolean isEmpty() {
        return head == null;
    }

    /* 전체 출력 */
    public void printList() {
        if (!isEmpty()) {
            Node<T> temp = head;
            System.out.print(temp.data + " ");
            while (temp.next != null) {
                temp = temp.next;
                System.out.print(temp.data + " ");
            }
            System.out.println();
        } else {
            System.out.println("A Singly Linked List is Empty");
        }
    }
}
