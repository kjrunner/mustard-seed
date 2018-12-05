package code.sample;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Properties;

import code.sample.inputanalysis.impl.FileInputAnaylzerImpl;
import code.sample.math.Math;
import code.sample.math.impl.MathImpl;
import code.sample.scanner.FileScanner;

import code.sample.inputanalysis.InputAnalyzer;
import code.sample.inputanalysis.InputAnalyzerResult;


/** 
 * Class with Main for command line execution
 */
public class QuickRun 
{
	private Properties props = new Properties();	

    public Properties getProperties() { 
    	return this.props;
    }

    public void loadProps( String propFileName ) throws FileNotFoundException, IOException {
    	try ( FileInputStream in = new FileInputStream( propFileName )) {
	        props.load( in );
    	}
    }

    public void runFileScanner(String scanFile) throws IOException {
    	FileScanner scanner = new FileScanner();
    	FileScanner.ScanResult scanResults = scanner.scan(scanFile);
    	System.out.println(String.format("Dir Count: %d", scanResults.getDirectoryCount()));
    	System.out.println(String.format("File Count: %d", scanResults.getFileCount()));
    	System.out.println(String.format("Total File Bytes: %d", scanResults.getTotalBytes()));
    	System.out.println(String.format("Averave of File Bytes: %d", scanResults.getAvgBytes()));
    } 
    
    public void runInputAnalyzer(String inputFilePath, String containsSrc) {	
    	try {
	    	InputAnalyzer inputAnalyzer = new FileInputAnaylzerImpl(inputFilePath);
	    	InputAnalyzerResult result = inputAnalyzer.getInputAnalyzerResult();
	    	
	    	result.displayInputAnalyzerStringDetails(result.createStringCountDetails());
	    	
	    	System.out.println(String.format("Total Sum: %f", result.getTotal()));
			System.out.println(String.format("Average: %f", result.getAvg()));
			System.out.println(String.format("Percentage of numbers: %f", result.getPercentageOfNumbers()));
			System.out.println(String.format("Median: %f", result.getMedian()));
			System.out.println(String.format("Numeric count: %d", result.getCountOfNumbers()));
			System.out.println(String.format(
					"Does input contain search string %s?: %s", 
					containsSrc, 
					result.contains(containsSrc)));
    	} catch( IOException ioE ) {
            System.out.println("Error during QuickRun for InputAnaylzer: " + ioE.getMessage());
        }  
    } 

    public static void main(String[] args)  
    { 
    	String inPath = "";
    	String containsSrc = "";
    	String defaultContainsSrc = "foo";
    	QuickRun quickRun = new QuickRun();
        try
        {  
            System.out.println("\n --  Begin QuickRun -------");
          
            quickRun.loadProps( "src/main/resources/sample.properties" );
          	Properties props = quickRun.getProperties(); 
          	
          	// check for command line args
        	if ( args != null && args.length >= 1)
        	{
        		int sampleType = Integer.parseInt( args[0]);
        		
        		// if input analyzer
        		if (sampleType == 1) 
        		{
        			System.out.println( "Execute QuickRun for Input Analyzer");
        			if ( args.length == 3 )
        			{
                		inPath = args[1];
                		String s = args[2];
                		if(s.trim().isEmpty())
                		{
                			//empty value - use default
                			containsSrc = defaultContainsSrc;
                		} else {
                			containsSrc = s;
                		}
        			}
        	       	else //use properties file
        	       	{ 
                		inPath = props.getProperty("analyzer.file.path");
                		containsSrc = props.getProperty("analyzer.contains.string", defaultContainsSrc);
        	       	}
        			
        			if ((inPath==null) || (inPath.isEmpty())){
            			System.out.println("Error: File path property cannot be empty.");
            			return;
            		}
        			
        			System.out.println("File Analyzer Path set to: " + inPath);
        			quickRun.runInputAnalyzer(inPath, containsSrc);
        		}
        		else if (sampleType == 2) 
        		{
        			System.out.println("Execute QuickRun for FileScanner");
        		
         			if (args.length == 2) {
                		inPath = args[1];
        			}
        	       	else {
                		inPath = props.getProperty("scanner.file.path");
                	}
         			
         			if((inPath == null) || inPath.isEmpty()){
         				System.out.println("Error: Path property cannot be empty");
         				return;
         			}
         			
         			System.out.println("File Scanner Path set to: " + inPath);
         			quickRun.runFileScanner(inPath);
        		}
        		else if (sampleType == 3) 
        		{
        			System.out.println("Execute QuickRun for Math Multiplier");
        			
        			if (args.length == 3) 
        			{
        				int a = 0;
            			int b = 0;
            			
        				try {
        					a = Integer.valueOf(args[1]);
                    		b = Integer.valueOf(args[2]);

                    		Math multi = new MathImpl();
                			System.out.println("Result:  " + multi.multiply(a, b));
                    	} 
        				catch (NumberFormatException nfe) {
                    		System.out.println("Error: Numeric value cannot be created " + nfe.getMessage());
                    	}
                		
        			} else {
        				System.out.println("Error: Invalid number of command line arguments for multiplier");
        			}
        		}
        		else {
        			System.out.println("Command line arg is invalid. Sample code indicator value must be 1, 2 or 3");
        		}
        	}
        	else {
        		System.out.println("Command line arg must indicate type");
        	}
        } 
        catch (Throwable thr)  {
        	System.out.println(thr);
            System.out.println("Error during processsing: " + thr.getMessage());
        }  
    } //main
 
}
