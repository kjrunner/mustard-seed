package code.sample.inputanalysis.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import code.sample.inputanalysis.InputAnalyzer;
import code.sample.inputanalysis.InputAnalyzerResult;
import code.sample.inputanalysis.InputAnalyzerUtil;


/** 
 * Class to manage file analysis process
 */
public class FileInputAnaylzerImpl implements InputAnalyzer 
{
	private String filePath;;
  
	public FileInputAnaylzerImpl(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public InputAnalyzerResult getInputAnalyzerResult() throws IOException
	{
		List<String> inputData = readFile( );
		return getResult( inputData );
	} 

	private List<String> readFile() throws IOException
    {
    	String sCurrentLine = "";
    
    	List<String> inputData = new ArrayList<String>();
    	
    	//try-with-resource will handle close
    	try ( BufferedReader br = new BufferedReader(new FileReader( filePath )))
    	{
    		while (( sCurrentLine= br.readLine()) != null) 
    		{
    			if(!sCurrentLine.trim().isEmpty()) {
                    inputData.add( sCurrentLine.trim());
				}
    		}
    	}
    	return inputData;
    } 

	private InputAnalyzerResult getResult(List<String> inputData)
	{
		InputAnalyzerResultImpl result = new InputAnalyzerResultImpl();
		List<Double> numericInput = new ArrayList<>();
		List<String> stringInput = new ArrayList<>();
		
		//determine data type and add to appropriate collection
		for( String element: inputData )
		{
			try {
				double dElement = Double.parseDouble(element);
				numericInput.add( InputAnalyzerUtil.defaultGetRoundedValue(dElement));
			}
			catch( NumberFormatException numEx){
				//cannot be converted
				stringInput.add(element);
			}
		}
		
		result.setStringValues(stringInput);
		result.setNumericValues(numericInput);
		
		result.setTotal(
				InputAnalyzerUtil.findSum(result.getNumericValues()));
		
		result.setAvg( InputAnalyzerUtil.defaultGetRoundedValue( 
				InputAnalyzerUtil.findAverage(result.getNumericValues())));
		
		result.setSortedNumericValues( 
				InputAnalyzerUtil.getSortedNumbers(result.getNumericValues()));
		
		result.setSortedStringValues(
				InputAnalyzerUtil.getSortedStrings(result.getStringValues()));
		
		result.setMedian( 
				InputAnalyzerUtil.findMedian(result.getSortedNumericValues()));
		
		result.setPercentageOfNumbers(
				InputAnalyzerUtil.defaultGetRoundedValue( 
						InputAnalyzerUtil.getPercentageOfNumbers( 
								result.getCountOfNumbers(), 
								(result.getCountOfNumbers() + result.getCountOfStrings()))));
		return result;
	}

}
