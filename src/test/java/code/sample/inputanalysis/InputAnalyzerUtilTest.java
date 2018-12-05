package code.sample.inputanalysis;

import org.junit.Test;

import code.sample.inputanalysis.InputAnalyzerUtil;

import java.util.List;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/* 
 * Tests to validate input analysis logic
 */
public class InputAnalyzerUtilTest 
{
	public InputAnalyzerUtilTest() {}
	
    @Test
    public void sortNumbers_listOfUnorderedNum_returnsOrderedList() {
    	List<Double> numbersToSort = new ArrayList<>();
    	numbersToSort.add(52.36);
    	numbersToSort.add(1.0);
    	numbersToSort.add(42.42);
    	
    	List<Double> expectedSortedList = new ArrayList<>();
    	expectedSortedList.add(1.0);
    	expectedSortedList.add(42.42);
    	expectedSortedList.add(52.36);
     
    	assertThat(InputAnalyzerUtil.sortNumbers(numbersToSort)).containsExactlyElementsOf(expectedSortedList);
    }
    
    @Test
    public void sortNumbers_listOfUnorderedNum_returnsReversedOrderedList() {
    	List<Double> numbersToSort = new ArrayList<>();
    	numbersToSort.add(52.36);
    	numbersToSort.add(1.0);
    	numbersToSort.add(42.42);
    	
    	List<Double> expectedSortedList = new ArrayList<>();
    	expectedSortedList.add(52.36);
    	expectedSortedList.add(42.42);
    	expectedSortedList.add(1.0);
     
    	assertThat(InputAnalyzerUtil.sortNumbers(numbersToSort, true)).containsExactlyElementsOf(expectedSortedList);
    }
    
    @Test
    public void sortNumbers_emptyList_returnsNull() {
    	assertThat(InputAnalyzerUtil.sortNumbers(new ArrayList<>())).isEmpty();
    }
    
    @Test
    public void sortNumbers_nullList_returnsNull() {
    	assertThat(InputAnalyzerUtil.sortNumbers(null, true)).isNull();
    }
    
    @Test
    public void sum_listOfNumbers_returnsCorrectSum() {
    	List<Double> numbers = new ArrayList<>();
    	numbers.add(10.36);
    	numbers.add(3.0);
    	numbers.add(5.64);
    	
    	double expectedSum = 19.00;
    	assertThat(InputAnalyzerUtil.sum(numbers)).isEqualTo(expectedSum);
    }
    
    @Test
    public void sum_emptyList_returnsZero() {
    	assertThat(InputAnalyzerUtil.sum(new ArrayList<>())).isEqualTo(0.0);
    }
    
    @Test
    public void sum_nullList_returnsZero() {
    	assertThat(InputAnalyzerUtil.sum(null)).isEqualTo(0.0);
    }
    
    @Test
    public void avg_listOfNumbers_returnCorrectAvg() {
    	
    	List<Double> numbers = new ArrayList<>();
    	numbers.add(10.36);
    	numbers.add(3.0);
    	numbers.add(5.64);
    	
    	double sumVal = 19.00;
    	double expectedAvg = sumVal/3.0;
       	assertThat(InputAnalyzerUtil.avg(numbers)).isEqualTo(expectedAvg);
    }
    
    @Test
    public void median_listWithEvenElements_returnsCorrectVal() {
      	List<Double> evenCount = new ArrayList<>();
      	evenCount.add(2.0);
      	evenCount.add(25.00);
      	evenCount.add(75.00);
      	evenCount.add(100.36);
    	
    	List<Double> evenSorted = InputAnalyzerUtil.sortNumbers(evenCount);
    	double expectedEvenVal = 50.00;
    	
    	assertThat(InputAnalyzerUtil.median(evenSorted)).isEqualTo(expectedEvenVal);
    }
    
    @Test
    public void median_listWithOddElements_returnsCorrectVal() {
    	List<Double> oddCount = new ArrayList<>();
    	oddCount.add(1.0);
    	oddCount.add(42.42);
    	oddCount.add(52.36);
    	
    	List<Double> oddSorted = InputAnalyzerUtil.sortNumbers(oddCount);
    	double expectedOddVal = 42.42;
    	
    	assertThat(InputAnalyzerUtil.median(oddSorted)).isEqualTo(expectedOddVal);
    }
    
    @Test
    public void median_nullList_throwsException() {
    	assertThatThrownBy(() -> {InputAnalyzerUtil.median(null);}).hasMessage("sortedNumbers Input cannot be null");
    }
 
}
