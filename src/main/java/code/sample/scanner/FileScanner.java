package code.sample.scanner;

import java.util.Iterator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.DirectoryStream;


/**
 * Scan all directories and files below a specified path
 */
public class FileScanner {

	
	private void traversePath(Path path, FileScanner.ScanResult scanResults) throws IOException {
		String msg = "Path [%s] is a %s";
		
		if (Files.isDirectory(path)) {
			System.out.println(String.format(msg, path, "dir"));
			DirectoryStream<Path> dirStream = Files.newDirectoryStream( path );
			Iterator<Path> i = dirStream.iterator();
			while(i.hasNext()) {
				Path p = i.next();
				if (Files.isDirectory(p)) {
					scanResults.incrementDirectoryCount();
				}
				this.traversePath(p,scanResults);
			}
		} else {
			System.out.println(String.format(msg, path, "file"));
			scanResults.incremenFileCount();
			long fSize = (Long)Files.getAttribute(path, "size");
			System.out.println( String.format("File %s size is %d bytes", path.getFileName().toString(), fSize));
			scanResults.incrementTotalBytes(fSize);
		}
	}
	
	public ScanResult scan(String path) throws IOException {
		FileScanner.ScanResult scanResults = new FileScanner.ScanResult();
		this.traversePath(Paths.get(path), scanResults);
		return scanResults;
	}


	/** 
	 * manage scan results
	 */
	 public static class ScanResult {
		 private int fileCount = 0;
		 private int directoryCount = 0;
		 private long totalBytes = 0;

         public int getFileCount() {
        	 return this.fileCount;
         }

         private void incremenFileCount() {
        	 this.fileCount = this.fileCount + 1;
         }

         public int getDirectoryCount() {
        	 return this.directoryCount;
         }
         
         private void incrementDirectoryCount() {
        	 this.directoryCount = directoryCount + 1;
         }
         
         public long getTotalBytes() {
        	return totalBytes; 
         }
         
         private void incrementTotalBytes(long bytes) {
        	 this.totalBytes = totalBytes + bytes;
         }
         
         public long getAvgBytes() {
        	 return this.totalBytes/this.fileCount;
         }
	 }
}
