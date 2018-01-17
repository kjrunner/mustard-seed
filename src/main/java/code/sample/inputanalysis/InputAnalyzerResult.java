package code.sample.inputanalysis;

import java.util.List;
import java.util.TreeMap;

public interface InputAnalyzerResult {
	
	public List<String> getStringValues();

	public List<String> getSortedStringValues();
	
	public List<Double> getNumericValues();
	
	public List<Double> getSortedNumericValues();
	
	public int getCountOfNumbers();
	
	public int getCountOfStrings();

	public double getTotal();
	
	public double getAvg();
	
	public double getPercentageOfNumbers();

	public double getMedian();
	
	public TreeMap<String, Integer> createStringCountDetails();
	
	public void displayInputAnalyzerStringDetails(TreeMap<String, Integer> map);
	
	public boolean contains(String src) ;
	
	public boolean contains(String src, boolean caseSensitive) ;
}
