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

	public static double sum(List<Double> numbers) {
		if (numbers == null) {
			return 0.0;
		}
		return numbers.stream().mapToDouble(Double::doubleValue).sum();
	}

	public static double avg(List<Double> numbers) {
		if (numbers == null) {
			return 0.0;
		}
		return numbers.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
	}

	public static double median(List<Double> sortedNumbers) throws IllegalArgumentException {
		if (sortedNumbers == null) {
			throw new IllegalArgumentException("sortedNumbers Input cannot be null");
		}
		
		double median = 0;
		int count = sortedNumbers.size();
		int half = count/2;
	
		if (count%2 == 1) {
			median = sortedNumbers.get(half);
		} else {
			double val1 = sortedNumbers.get(half - 1);
			double val2 = sortedNumbers.get(half) ;
		    median = (val1 + val2)/2.0;
		}
		return median;
	}
	
	public static List<Double> sortNumbers(List<Double> numbers) {
		return sortNumbers(numbers, false);
	}
	
	public static List<Double> sortNumbers(List<Double> numbers, boolean reverse) {
		if (numbers == null) {
			return null;
		}
		List<Double> sortedNumbers = new ArrayList<>( numbers );
		
		if (reverse) {
			Collections.sort(sortedNumbers, Collections.reverseOrder());
		} else {
			Collections.sort(sortedNumbers);
		}
		return sortedNumbers;
	}
	
	public static List<String> sortStrings(List<String> stringValues) {
		return sortStrings(stringValues, false);
	}

	public static List<String> sortStrings(List<String> stringValues, boolean reverse) {
		if (stringValues == null) {
			return null;
		}
		
		List<String> sortedStrings = new ArrayList<>(stringValues);
		
		if (reverse) {
			Collections.sort(sortedStrings, Collections.reverseOrder());
		} else {
			Collections.sort(sortedStrings);
		}
		return sortedStrings;
	}
	
	public static double calculatePercentageOfNumbers(int numCount, int allCount)  {
		return (numCount * 100.0)/allCount;
	}

	public static double roundValue(double d, int scale, RoundingMode mode) {
		BigDecimal bd = new BigDecimal(d);
		return bd.setScale(scale, mode).doubleValue();
	}
	
	public static double roundValue(double d) {
		return roundValue(d, 2, RoundingMode.HALF_UP );
	}
	
	public static boolean contains(String src, String searchStr, boolean caseSensitive) {
		if (src == null || src.isEmpty()) {
			return false;
		}
		if (caseSensitive) {
			return src.contains(searchStr);
		} else {
			return src.toLowerCase().contains(searchStr.toLowerCase());
		}
	}

}
