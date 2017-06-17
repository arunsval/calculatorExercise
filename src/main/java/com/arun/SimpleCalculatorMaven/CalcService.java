package com.arun.SimpleCalculatorMaven;

import com.arun.SimpleCalcException.CalcServiceException;

public interface CalcService {
	public String validateInput(String s) throws CalcServiceException ;
	public int doArithMaticOperation();
	public void intializeStructures();
	public Integer splitUsingRegEx(String s);
}
