package com.hicx.interview1;

import static org.junit.Assert.*;

import java.nio.file.Path;

import org.junit.Rule;
import org.junit.jupiter.api.Test;


public class ProcessorTest
{
    @Rule
    public ResourceFile res = new ResourceFile("test1.txt");

    @Test
    public void test() throws Exception
    {
        assertTrue(res.getContent().length() > 0);
        assertTrue(res.getFile().exists());
    	FileProcessor fp = new FileProcessor();
		fp.GetInfo(Path.of(res.getFile().toURI()));

    }
}