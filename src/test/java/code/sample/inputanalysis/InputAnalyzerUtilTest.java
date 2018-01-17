package code.sample.inputanalysis;

import java.util.List;
import java.util.ArrayList;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import code.sample.inputanalysis.InputAnalyzerUtil;


/* 
 * Tests to validate input analysis logic
 */
public class InputAnalyzerUtilTest 
{
	
	public InputAnalyzerUtilTest() {}

	
    @Test
    public void shouldConfirmSortedNumbers()
    {
    	List<Double> toSort = new ArrayList<>();
    	toSort.add(52.36);
    	toSort.add(1.0);
    	toSort.add(42.42);
    	
    	List<Double> sorted = new ArrayList<>();
    	sorted.add(1.0);
    	sorted.add(42.42);
    	sorted.add(52.36);
    	
    	try 
    	{
    		assertEquals( 
    				InputAnalyzerUtil.getSortedNumbers( toSort ),
            		sorted, 
            		"Sorted order does not match" );
	    }
	    catch( Exception e ) 
    	{ 
	    	assertNull( e );     
	    }
    }
    
    @Test
    public void shouldConfirmReverseSortedNumbers()
    {
    	
    	List<Double> toSortReverse = new ArrayList<>();
    	toSortReverse.add(52.36);
    	toSortReverse.add(1.0);
    	toSortReverse.add(42.42);
    	
    	List<Double> reverseSort = new ArrayList<>();
    	reverseSort.add(52.36);
    	reverseSort.add(42.42);
    	reverseSort.add(1.0);
    	
      	try 
    	{
    		assertEquals( 
    				InputAnalyzerUtil.getSortedNumbers( toSortReverse, true ),
    				reverseSort, 
            		"Reverse sorted order does not match" );
	    }
	    catch( Exception e ) 
    	{ 
	    	assertNull( e );     
	    }
    }
    
    @Test
    public void shouldConfirmTotalSum()
    {
    	
    	List<Double> numbers = new ArrayList<>();
    	numbers.add(10.36);
    	numbers.add(3.0);
    	numbers.add(5.64);
    	
    	double sumVal = 19.00;
      	try 
    	{
    		assertEquals( 
    				InputAnalyzerUtil.findSum(numbers),
    				sumVal, 
            		"Sum of numbers in collection does not match." );
	    }
	    catch( Exception e ) 
    	{ 
	    	assertNull( e );     
	    }
    }
    
    @Test
    public void shouldConfirmAverage()
    {
    	
    	List<Double> numbers = new ArrayList<>();
    	numbers.add(10.36);
    	numbers.add(3.0);
    	numbers.add(5.64);
    	
    	double sumVal = 19.00;
    	double avgVal = sumVal/3.0;
      	try 
    	{
    		assertEquals( 
    				InputAnalyzerUtil.findAverage(numbers),
    				avgVal, 
            		"Avg of numbers in collection does not match." );
	    }
	    catch( Exception e ) 
    	{ 
	    	assertNull( e );     
	    }
    }
    
    
    @Test
    public void shouldConfirmMedian()
    {
    	//when collection is odd count
    	List<Double> oddTest = new ArrayList<>();
    	oddTest.add(1.0);
    	oddTest.add(42.42);
    	oddTest.add(52.36);
    	double oddVal = 42.42;
    	
    	//when collection is even count
    	List<Double> evenTest = new ArrayList<>();
    	evenTest.add(2.0);
    	evenTest.add(25.00);
    	evenTest.add(75.00);
    	evenTest.add(52.36);
    	double evenVal = 50.00;
    	
      	try 
    	{
      		List<Double> oddSorted = InputAnalyzerUtil.getSortedNumbers(oddTest);
    		assertEquals( 
    				InputAnalyzerUtil.findMedian(oddSorted),
    				oddVal, 
            		"Odd median value does not match" );
	    }
	    catch( Exception e ) 
    	{ 
	    	assertNull( e );     
	    }
      	
     	try 
    	{
    		assertEquals( 
    				InputAnalyzerUtil.findMedian(evenTest),
    				evenVal, 
            		"Even median value does not match" );
	    }
	    catch( Exception e ) 
    	{ 
	    	assertNull( e );     
	    }
    }
}
