package com.hicx.interview1;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrueAlso()
    {
    	HashMap<String, Long> words = new HashMap<String, Long>();
    	words.put("dos", (long) 5);
    	words.put("cinco", (long) 3);
    	words.put("seis", (long) 1);
    	words.put("Fernando", (long) 2);
    	
    	FileProcessor fp = new FileProcessor();
        assertTrue( fp.getNumberOfWords(words) == 11 );
    }

}
