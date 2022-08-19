package dataStructures.hashtable.simple;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleHashTableTest {

    MySimpleHashTest hashTable;

    @Before
    public void initTest() {
        hashTable = new MySimpleHashTest(20);
    }

    @Test
    public void testRetrieve() {
        hashTable.saveData("J", "Junho Choi");
        hashTable.saveData("H", "Hunho Choi");

        assertEquals("Hunho Choi", hashTable.getData("H"));
    }

    @Test
    public void testHashCollision(){
        hashTable.saveData("J", "Junho Choi");
        hashTable.saveData("J", "James Choi");
        hashTable.saveData("J", "Jenny Choi");

        assertEquals("Jenny Choi", hashTable.getData("J"));
    }
}
