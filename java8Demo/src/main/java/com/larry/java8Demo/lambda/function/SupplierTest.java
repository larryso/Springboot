package com.larry.java8Demo.lambda.function;

import java.util.function.Supplier;

/**
 * 供给型接口
 * @author larryso
 *
 */
public class SupplierTest {
	public static void getMaxInt(Supplier<Integer> sup) {
		System.out.println("MAX Integer:"+sup.get());
	}
	public static void main(String[] args) {
		Integer[] i = {1,2,3,45,6,7,100};
		getMaxInt(()->{
			int large = 0;
			for(int ii = 0;ii<i.length;ii++) {
				if(large<i[ii]) {
					large=i[ii];
				}
			}
			return large;
			
		});
	}
}
