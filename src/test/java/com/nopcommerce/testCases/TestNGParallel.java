package com.nopcommerce.testCases;

import org.testng.annotations.Test;

public class TestNGParallel {

	@Test(threadPoolSize = 10, invocationCount = 2, timeOut = 5000)
	public void testMethod() {
		System.out.println("The thread id  is : " + Thread.currentThread().getId());
	}

}
