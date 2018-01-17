package code.sample.inputanalysis;

import java.io.IOException;

public interface InputAnalyzer {
	
	//Gets results after processing text file
	public InputAnalyzerResult getInputAnalyzerResult() throws IOException ;
}
