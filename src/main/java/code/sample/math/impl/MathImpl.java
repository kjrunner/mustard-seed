package code.sample.math.impl;

import code.sample.math.Math;

/**
 * Implementations free of multiplication or division operators
 */
public class MathImpl implements Math {

	public long multiply(int a, int b) {
		int aIsNegative = 0;
		int bIsNegative = 0;
		long abResult = 0L;
		
		if ((a == 0)||(b == 0)) {
			return abResult;
		}
		
		//keep track of sign for each
		if (a < 0) {
			aIsNegative = 1;
		}
		if (b < 0) {
			bIsNegative = 1;
		}
		
		abResult = getAbsoluteProduct(a, b);
	
		if ((aIsNegative + bIsNegative) == 1) {
			return 0 - abResult;
		} else {
			return abResult;
		}
	}
	
	public int absolute(int value) {
		if (value < 0) {
			return (value - value) - value;
		} 
		return value;
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
		
		for (int i=0; i < count; i++) {
			result += operand;
		}
		
		return result;
	}
}
