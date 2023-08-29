package com.testng;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Rerun implements IRetryAnalyzer {
	
		
	int a=1,b=2;
	public boolean retry(ITestResult result) {
		if(a<b) {
			a++;
			System.out.println("Failed Test Case"+ " " +result.getName());
			return true;
		}
		
		return false;
	}

}
