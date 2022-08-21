package dataStructures.hashtable.closedAddressing;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyHashTableTest {
    MyHashTable hashTable;

    @Before
    public void init(){
        hashTable = new MyHashTable(20);
    }


    @Test
    public void testHashCollision(){
        hashTable.saveData("J", "Junho Choi");
        hashTable.saveData("J", "James Choi");
        hashTable.saveData("J", "Jenny Choi");
        hashTable.saveData("H", "Henry Choi");
        hashTable.saveData("D", "Diana Choi");
        System.out.println(hashTable.getData("J"));
        assertEquals("Jenny Choi James Choi Jenny Choi ", hashTable.getData("J"));
    }

}
