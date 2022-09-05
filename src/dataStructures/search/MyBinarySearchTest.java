package dataStructures.search;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyBinarySearchTest {
    MyBinarySearch<Integer> bs;
    Integer[] testData = {2, 3, 8, 12, 20};

    @Before
    public void init(){
        bs = new MyBinarySearch<>();
    }

    @Test
    public void testBinarySearch(){
        init();
        int score = 0;
        for(Integer data : testData){
            if(bs.binarySearch(testData, 0, testData.length, data)){
                score++;
            }
        }
        assertEquals(testData.length, score);
    }

    @Test
    public void testIterBinarySearch(){
        init();
        int score = 0;
        for(Integer data : testData){
            if(bs.binarySearch(testData, data)){
                score++;
            }
        }
        assertEquals(testData.length, score);
    }

}
