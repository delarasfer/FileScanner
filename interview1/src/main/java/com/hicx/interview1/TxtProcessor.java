package com.hicx.interview1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TxtProcessor implements Processor {

	@Override
	public Map<String, Long> getWords(Path file) throws Exception {
		Map<String, Long> wordsCount = Files.lines(file)
	    .flatMap(line -> Arrays.stream(line.trim().split(" ")))
	    .map(word -> word.replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim())
	    .filter(word -> !word.isEmpty())
	    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		return wordsCount;
	}

	@Override
	public Long getOccurenceOfChar(Path file, char charToFind) throws Exception {
		Long ret = (long) 0;
		Scanner textInp = new Scanner(file); // Or new style
		   while (textInp.hasNextLine()) {
		       String line = textInp.nextLine();
		       ret += line.chars().filter(ch -> ch == charToFind).count();

		   }
		return ret;
	}

}
