package com.arun.SimpleCalculatorMaven;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.arun.SimpleCalcException.CalcServiceException;

public class CalculatorMain {
	final static Logger logger = Logger.getLogger(CalculatorMain.class);
	public static void main(String[] args) {
		logger.info("Enter expression:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String input = br.readLine();
			CalcService cl = new CalcServiceImpl(); 
			String message = cl.validateInput(input);
			logger.info(message);
		
		} catch (IOException e) {
			logger.error("IOException occurred");
		} catch (CalcServiceException e) {
			logger.error("CalcServiceException occurred");
		}
	}
}
