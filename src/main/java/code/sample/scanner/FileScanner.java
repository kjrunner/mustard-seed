package code.sample.scanner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.DirectoryStream;


/**
 * Scan all files and directories below a specified path on a local filesystem.
 */
public class FileScanner 
{
	
	/** 
	 * Traverse all directories below specified dir
	 */
	private static void traverseEntries( 
			Path dir, FileScanner.ScanResult scanResults) throws IOException, SecurityException
	{
		List<Path> subDirs = new ArrayList<>();
		
		//get dir stream and iterate over entries
		DirectoryStream<Path> dirStream = Files.newDirectoryStream( dir );
	    Iterator<Path> i = dirStream.iterator();
		while(i.hasNext())
		{
			Path p = i.next();
			if ( Files.isDirectory(p))
			{
				System.out.println("Directory name: " + p.getFileName().toString());
				scanResults.incrementNumDirectories();
				subDirs.add(p);
			} 
			else 
			{
				System.out.println("File name: " + p.getFileName().toString());
				scanResults.incrementNumFiles();
				long fSize = getFileByteSize( p );
				scanResults.incrementTotalBytes(fSize);
			}
		}
		
		// recursive call for all sub-directories
		for(Path d: subDirs) {
			FileScanner.traverseEntries(d, scanResults);
		};
		
	} //traverseEntries
	
	private static long getFileByteSize(Path fPath) throws IOException
	{
		long fSize = (Long)Files.getAttribute(fPath, "size");
		System.out.println( "File name: " + 
				fPath.getFileName().toString() + " size:" + fSize);
		return fSize;
	}

	/** 
	 * Scan all entries below given path
	 */
	public static ScanResult scan(String path) 
	{
		final FileScanner.ScanResult scanResults = new FileScanner.ScanResult();
		try 
	    { 
			 Path startPath = Paths.get(path);
			 if(Files.isDirectory( startPath))
			 {
				 //increment count and then traverse
				 scanResults.incrementNumDirectories();
				 System.out.println("Start path: " + startPath + " is a dir");
				 FileScanner.traverseEntries( startPath, scanResults);
			 } 
			 else if (Files.isRegularFile(startPath))
			 {
				 //initial path submitted is a file
				 System.out.println( "Start path: " + startPath + " is a file");
				 long fSize = getFileByteSize( startPath );
				 scanResults.incrementTotalBytes( fSize );
				 scanResults.incrementNumFiles();
			 }
			 else {
				 throw new Exception("Unable to scan path: " + path );
			 }
			 
			 //get avg size
			 scanResults.setAvgBytes(scanResults.getTotalBytes()/scanResults.getNumFiles());
			 
		} // keep ex separate rather than one catch, in practice may want to handle ex differently
		catch(InvalidPathException badPath) 
		{
			System.out.println(badPath.getMessage());
		}
		catch(SecurityException secEx) 
		{
			System.out.println(secEx.getMessage());
		}
		catch(IOException ioEx) 
		{
			System.out.println(ioEx.getMessage());
		}
		catch(Exception e)
	    {
			System.out.println(e.getMessage() );
		}
		
		return scanResults;

	 };
	 
	/** 
	 * nested class to managing scan results
	 */
	 public static class ScanResult 
	 {
		 private int numFiles = 0;
		 private int numDirectories = 0;
		 private long totalBytes = 0;
		 private long avgBytes = 0;

        /** 
    	 * return the number of files scanned
    	 */
         public int getNumFiles()
         {
        	 return numFiles;
        	 
         };  

         private void incrementNumFiles(){
        	 numFiles = numFiles + 1;
         }

         public int getNumDirectories()
         {
        	 return numDirectories;
        	 
         }; 
         
         private void incrementNumDirectories(){
        	 numDirectories = numDirectories + 1;
         }
         
         public long getTotalBytes()
         {
        	return totalBytes; 
         }; 
         
         private void incrementTotalBytes( long bytes )
         {
        	 totalBytes = totalBytes + bytes;
         }
         
         public long getAvgBytes()
         {
        	 return avgBytes;
         }; // return the average size of the scanned _files_
         

         private void setAvgBytes( long avgBytes)
         {
        	 this.avgBytes = avgBytes;
         }; 
	 }
}
