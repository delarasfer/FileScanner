package com.hicx.interview1;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class FileScanner 
{

	public static void main( String[] args )
    {
    
    	if(args.length == 0)
    	{
    		System.out.println("need to add a directory to look for files");

    		System.exit(0);
    	}
		try
		{
			String[] checkProcessed = {args[0], "processed"};
			File theDir = new File(String.join("/", checkProcessed));
			if (!theDir.exists())
			    theDir.mkdirs();
			String[] checkError = {args[0], "error"};
			theDir = new File(String.join("/", checkError));
			if (!theDir.exists())
			    theDir.mkdirs();
			

	    	FileProcessor fp = new FileProcessor();
			do
			{
	    	
				Set<String> setFiles = listFilesUsingFilesList(args[0]);
				for(String fileToProcess : setFiles)
				{
					String[] foundFileParts = {args[0], fileToProcess};
					String foundFile = String.join("/", foundFileParts);
					String[] processedFile = {args[0], "processed", fileToProcess};
					Path pthFoundFile = Paths.get(foundFile);
					try
					{
						fp.GetInfo(pthFoundFile);
					    Files.move(pthFoundFile, Paths.get(String.join("/", processedFile)), StandardCopyOption.REPLACE_EXISTING);
					}
					catch(Exception e)
					{
						System.out.println(e);
						String[] errorFile = {args[0], "error", fileToProcess};
					    Files.move(pthFoundFile, Paths.get(String.join("/", errorFile)), StandardCopyOption.REPLACE_EXISTING);
					}

				}
   	    		Thread.sleep(5000L);
			} while(true);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
    }
    public static Set<String> listFilesUsingFilesList(String dir) throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get(dir))) {
            return stream
                .filter(file -> !Files.isDirectory(file))
                .map(Path::getFileName)
                .map(Path::toString)
                .collect(Collectors.toSet());
        }
    }
}
