package com.hicx.interview1;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class FileProcessor {
	private Map<String, Processor> map = new HashMap<>();
	private long numberOfWords = 0;
	private String mostUsedWord = "";
	

	public FileProcessor()
	{
		map.put("txt", new TxtProcessor());
	}
	public void GetInfo(Path source) throws Exception
	{
		Optional<String>ext = getExtensionByStringHandling(source.getFileName().toString());
		if(ext.isPresent())
		{
			final Processor processor = map.get(ext.get());
			if(processor == null)
				throw new Exception(String.format("No available processor for %s file type", ext.get()));
			Map<String, Long> words = processor.getWords(source);
			System.out.println(String.format("Number of words: %d", getNumberOfWords(words)));
			System.out.println("Most used word:");
			getMostUsedWord(words);
			System.out.println(String.format("Number of dots: %d",processor.getOccurenceOfChar(source, '.')));
		}
	}
	public Optional<String> getExtensionByStringHandling(String filename) {
	    return Optional.ofNullable(filename)
	      .filter(f -> f.contains("."))
	      .map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}
	public long getNumberOfWords(Map<String, Long> words)
	{
		numberOfWords = 0;
		words.entrySet()
         .stream()
         .forEach(e -> numberOfWords += (long)e.getValue());
		
		return numberOfWords;
		
	}
	public void getMostUsedWord(Map<String, Long> words)
	{
		Long maxRep = (long) -1;
		List sorted = words.entrySet()
	    .stream()
	    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
	    .collect(Collectors.toList());
		
		for(int i=0;i< sorted.size();i++)
		{
			Map.Entry<String, Long> word = (Entry<String, Long>) sorted.get(i);
			if(maxRep == -1)
			{
				System.out.println(word.getKey());
				maxRep = word.getValue();
			}
			else if(maxRep == word.getValue())
				System.out.println(word.getKey());
			else
				break;
		}
		
		
	}
}
