package code.sample.inputanalysis;

import java.util.Collections;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/** 
 * Helper class to abstract file analysis logic
 */
public abstract class InputAnalyzerUtil 
{

	public static double findSum(List<Double> numbers)
	{
		return numbers.stream().mapToDouble(n -> n.doubleValue() ).sum();
	}

	public static double findAverage(List<Double> numbers)
	{
		return numbers.stream().mapToDouble(n -> n.doubleValue() ).average().getAsDouble();
	}

	public static double findMedian(List<Double> sortedNumbers)
	{
		double median = 0;
		int count = sortedNumbers.size();
		int half = count/2;
	
		if(count%2 == 1) {
			median = sortedNumbers.get(half);
		} 
		else {
			double val1 = sortedNumbers.get(half - 1);
			double val2 = sortedNumbers.get(half) ;
		    median = (val1 + val2)/2.0;
		}
		
		return median;
	}
	
	public static List<Double> getSortedNumbers(List<Double> numbers) 
	{
		return getSortedNumbers(numbers, false);
	}
	
	public static List<Double> getSortedNumbers(List<Double> numbers, boolean reverse) 
	{
		List<Double> sortedNumbers = new ArrayList<>( numbers );
		if(reverse) {
			Collections.sort(sortedNumbers, Collections.reverseOrder());
		}
		else {
			Collections.sort(sortedNumbers);
		}
		
		return sortedNumbers;
	}
	
	public static List<String> getSortedStrings(List<String> stringValues) 
	{
		return getSortedStrings(stringValues, false);
	}

	public static List<String> getSortedStrings(List<String> stringValues, boolean reverse) 
	{
		List<String> sortedStrings = new ArrayList<>(stringValues);
		if (reverse) {
			Collections.sort(sortedStrings, Collections.reverseOrder());
		}
		else {
			Collections.sort(sortedStrings);
		}
		return sortedStrings;
	}
	
	public static double getPercentageOfNumbers(int numCount, int allCount) 
	{
		return (numCount * 100.0)/allCount;
	}

	public static double getRoundedValue(double d, int scale, RoundingMode mode) 
	{
		BigDecimal bd = new BigDecimal(d);
		return bd.setScale(scale, mode).doubleValue();
	}
	
	public static double defaultGetRoundedValue(double d)
	{
		return getRoundedValue(d, 2, RoundingMode.HALF_UP );
	}
	
	public static boolean contains(String src, String searchStr, boolean caseSensitive)
	{
		if (!caseSensitive) {
			return src.toLowerCase().contains(searchStr.toLowerCase());
		}
		else {
			return src.contains(searchStr);
		}
	}

}
