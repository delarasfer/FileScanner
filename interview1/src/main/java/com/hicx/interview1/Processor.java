package com.hicx.interview1;

import java.nio.file.Path;
import java.util.Map;

public interface Processor {
	Map<String, Long> getWords(Path file) throws Exception;
	Long getOccurenceOfChar(Path file, char charToFind) throws Exception;
}
