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
	public InputAnalyzerResult getInputAnalyzerResult() throws IOException, IllegalArgumentException {
		List<String> inputData = readFile( );
		return getResult( inputData );
	} 

	private List<String> readFile() throws IOException {
    	String currentLine = "";
    
    	List<String> inputData = new ArrayList<String>();

    	try ( BufferedReader br = new BufferedReader(new FileReader( filePath ))) {
    		while (( currentLine= br.readLine()) != null) {
    			if (!currentLine.trim().isEmpty()) {
                    inputData.add( currentLine.trim());
				}
    		}
    	}
    	return inputData;
    } 

	private InputAnalyzerResult getResult(List<String> inputData) throws IllegalArgumentException {
		InputAnalyzerResultImpl result = new InputAnalyzerResultImpl();
		List<Double> numericInput = new ArrayList<>();
		List<String> stringInput = new ArrayList<>();
		
		//determine data type and add to appropriate collection (refactor so not using catch)
		for( String element: inputData ) {
			try {
				double dElement = Double.parseDouble(element);
				numericInput.add( InputAnalyzerUtil.roundValue(dElement));
			} catch( NumberFormatException numEx) {
				//cannot be converted
				stringInput.add(element);
			}
		}
		
		result.setStringValues(stringInput);
		result.setNumericValues(numericInput);
		result.setTotal(InputAnalyzerUtil.sum(result.getNumericValues()));	
		result.setAvg( InputAnalyzerUtil.roundValue( 
				InputAnalyzerUtil.avg(result.getNumericValues())));	
		result.setSortedNumericValues(InputAnalyzerUtil.sortNumbers(result.getNumericValues()));	
		result.setSortedStringValues(InputAnalyzerUtil.sortStrings(result.getStringValues()));	
		result.setMedian( InputAnalyzerUtil.median(result.getSortedNumericValues()));	
		result.setPercentageOfNumbers(InputAnalyzerUtil.roundValue( 
						InputAnalyzerUtil.calculatePercentageOfNumbers( 
								result.getCountOfNumbers(), (result.getCountOfNumbers() + result.getCountOfStrings()))));
		return result;
	}

}
