package code.sample.inputanalysis.impl;

import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeMap;

import code.sample.inputanalysis.InputAnalyzerResult;

public class InputAnalyzerResultImpl implements InputAnalyzerResult {
	
	private List<String> stringValues = null;
	private List<Double> numericValues = null;
	private List<String> sortedStringValues = null;
	private List<Double> sortedNumericValues = null;
	private int countOfNumbers = 0;
	private int countOfStrings = 0;
	private double total = 0.0;
	private double avg = 0.0;
	private double median = 0.0;
	private double percentageOfNumbers = 0.0;
	private String stringInput = "";
	
	
	public List<String> getStringValues() {
		return stringValues;
	}
	
	public void setStringValues(List<String> stringValues) {
		this.stringValues = stringValues;
		countOfStrings = this.stringValues.size();
		StringBuilder sb = new StringBuilder();
		for(String s: this.stringValues) {
			sb.append(s).append(" ");
		}
		stringInput = sb.toString();
	}
	
	public List<String> getSortedStringValues() {
		return sortedStringValues;
	}
	
	protected void setSortedStringValues(List<String> sortedStringValues) {
		this.sortedStringValues = sortedStringValues;
	}
	
	public List<Double> getNumericValues() {
		return numericValues;
	}
	
	protected void setNumericValues(List<Double> numericValues) {
		this.numericValues = numericValues;
		countOfNumbers = this.numericValues.size();
	}
	
	public List<Double> getSortedNumericValues() {
		return sortedNumericValues;
	}
	
	protected void setSortedNumericValues(List<Double> sortedNumericValues) {
		this.sortedNumericValues = sortedNumericValues;
	}
	
	public int getCountOfNumbers() {
		return countOfNumbers;
	}
	
	public int getCountOfStrings() {
		return countOfStrings;
	}

	public double getTotal() {
		return total;
	}
	
    protected void setTotal(double total) {
		this.total = total;
	}
	
	public double getAvg() {
		return avg;
	}
	
	protected void setAvg(double avg) {
		this.avg = avg;
	}
	
	public double getPercentageOfNumbers() {
		return percentageOfNumbers;
	}

	protected void setPercentageOfNumbers(double percentageOfNumbers) {
		this.percentageOfNumbers = percentageOfNumbers;
	}

	public double getMedian() {
		return median;
	}
	
	protected void setMedian(double median) {
		this.median = median;
	}
	
	public boolean contains(String src ) 
	{
		return contains(src, true);
	}
	
	public boolean contains(String src, boolean caseSensitive ) 
	{
		if (src.isEmpty()) {
			return false;
		}
		
		if (!caseSensitive) {
			return stringInput.toLowerCase().contains(src.toLowerCase());
		}
		else {
			return stringInput.contains(src);
		}
	}
	
	public TreeMap<String, Integer> createStringCountDetails() 
	{
		//make sure sorted List has been set
		if( this.sortedStringValues == null){
			return null;
		}
		
		TreeMap<String, Integer> map = new TreeMap<>();
		for(String s : this.sortedStringValues)
		{
			//is the string a map key already
			if (map.containsKey(s))
			{
				//if yes, then increment the count
				int currentVal = map.get(s);
				map.replace(s, currentVal, currentVal + 1);
			} 
			else {
				//string not in map yet so put it there
				map.put(s, 1);
			}
		}
		return map;
	}
	
	// Display logic is available so it's more easily available.
	public void displayInputAnalyzerStringDetails(TreeMap<String, Integer> map) 
	{
		System.out.println( "\n --- STRING COUNT DETAILS --- ");
		
		if(map == null) {
			System.out.println( "*** No details to print *** ");
		}
		//print count results for keys in descending order
		NavigableSet<String> navSet = map.descendingKeySet();
		Iterator<String> i = navSet.iterator();
		
		
		while (i.hasNext()) {
			String str = i.next();
			System.out.println(String.format("%s, count= %d", str, map.get(str)));
		}
		System.out.println( "\n");
	}

}
