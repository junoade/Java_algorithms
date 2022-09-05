package category.simulation.programmers.level1;

import org.junit.Test;

import static org.junit.Assert.*;

public class MBTI_Check_Test {

    @Test
    public void testcase01(){
        String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
        int[] choices = {5, 3, 2, 7, 5};
        assertEquals("TCMA", MBTI_Check.solution(survey, choices));
    }
}
