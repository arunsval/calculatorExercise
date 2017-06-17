package com.arun.SimpleCalculatorMaven;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.arun.SimpleCalcException.CalcServiceException;


public class ArithmeticTest extends TestCase {
	static CalcService calcService = new CalcServiceImpl();
	final static Logger logger = Logger.getLogger(ArithmeticTest.class);

	@Test(expected=CalcServiceException.class)
	public void testInvalidInput(){
		try {
			calcService.validateInput("%%##");
		} catch (CalcServiceException e) {
			logger.error("CalcServiceException trigger");
		}
		
	}
	
	@Test
	public void testAddition(){
		Assert.assertEquals(new Double(8), new Double(calcService.splitUsingRegEx("add(add(add(1,3),1),add(1,2))")));
		
	}
	@Test
	public void testSubtract(){
		Assert.assertEquals(new Double(1), new Double(calcService.splitUsingRegEx("sub(3,2)")));
		
	}
	@Test
	public void testDiv(){
		Assert.assertEquals(new Double(5), new Double(calcService.splitUsingRegEx("div(10,2)")));
		
	}
	@Test(expected=ArithmeticException.class)
	public void testDivByZero(){
		try{
			calcService.splitUsingRegEx("div(3,0)");} catch(Exception e){
			System.out.println("Div by zero error");
		}
		}
	
	@Test
	public void testMult(){
		Assert.assertEquals(new Double(6), new Double(calcService.splitUsingRegEx("mult(3,2)")));
	}
	
	@Test
	public void testAssign(){
		System.out.println("let");
		//Assert.assertEquals(new Double(10), new Double(Calculator.splitUsingRegEx("let(a,5,add(a,a))")));
		Assert.assertEquals(new Double(55), new Double(calcService.splitUsingRegEx("let(a,5,let(b,mult(a,10),add(b,a)))")));
	}
}
