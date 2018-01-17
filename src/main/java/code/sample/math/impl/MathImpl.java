package code.sample.math.impl;

import code.sample.math.Math;



public class MathImpl implements Math {
	
	/**
	 * Implementation free of multiplication or division operators
	 */
	public long multiply(int a, int b) {
		int aIsNegative = 0;
		int bIsNegative = 0;
		long result = 0L;
		
		if ((a == 0)||(b == 0)) {
			return result;
		}
		
		//keep track of sign for each
		if (a < 0) {
			aIsNegative = 1;
		}
		if (b < 0) {
			bIsNegative = 1;
		}
		
		result = getAbsoluteProduct(a, b);
	
		if ((aIsNegative + bIsNegative) == 1) {
			return (result - result) - result;
		} else {
			return result;
		}
	}
	
	private long getAbsoluteProduct(int a, int b) {
		long result = 0L;
		
		a = this.absolute(a);
		b = this.absolute(b);
		
		// minimize iterations
		int count;
		int operand;
		if (a < b) {
			count = a;
			operand = b;
		} else {
			count = b;
			operand = a;
		}
		
		int iterations = 0;
		for (int i=0; i < count; i++) {
			result = result + operand;
			iterations += 1;
		}
		
		System.out.println("GetAbsoluteProduct iteration count: " + iterations); 
		return result;
	}
	
	/**
	 * Implementation free of multiplication or division operators
	 */
	public int absolute(int value) {
		if (value < 0) {
			return (value - value) - value;
		} 
		return value;
	}

}
