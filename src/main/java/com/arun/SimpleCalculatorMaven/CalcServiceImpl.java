package com.arun.SimpleCalculatorMaven;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.arun.SimpleCalcException.CalcServiceException;

public class CalcServiceImpl implements CalcService {
	final static Logger logger = Logger.getLogger(CalcServiceImpl.class);
	static String op = "";
	static int l,r= 0;
	static Deque dq1 = new LinkedList();
	static Deque dq2 = new LinkedList();
	static Deque dq3 =  new LinkedList();
	static final List<String> opList = new java.util.ArrayList<String>();
	
	static final String numRegEx = "\\d+";
	static final String alphaRegEx = "[a-zA-Z]+";
	static final String allowedSymbols = "^[a-zA-Z,()]*$";
	static HashMap<String,Integer> assignMap = new HashMap<String,Integer>();;
	
	CalcServiceImpl(){
		opList.add("add");
		opList.add("sub");
		opList.add("mult");
		opList.add("div");
		opList.add("let");
	}
	
	@Override
	public int doArithMaticOperation() {
		java.util.List<Integer> numList = new java.util.ArrayList<Integer>();
		int exprResult = 0;
		String operation = ""; 
		int result = 0;	
		Integer no = 0;
		try{
		while(true){
			if(dq2.isEmpty()){
					break;
				}
			String ch = String.valueOf(dq2.removeLast());

    		if(",".equals(ch)){
    		 operation = (String)dq1.removeLast();

    		}else if(String.valueOf(ch).matches(numRegEx)){
    			no = Integer.parseInt(ch);
    				numList.add(no);
    				continue;
    			} else if(String.valueOf(ch).matches(alphaRegEx)){
    				no = (Integer)assignMap.get(ch);

    				numList.add(no);
    				continue;
    			}
    		
    		else if("(".equals(ch)){
    		
    				if("add".equals(operation)){
        				result = numList.get(0) + numList.get(1);
        			} else if("sub".equals(operation)){
        				result = numList.get(1) - numList.get(0);
        			} else if("div".equals(operation)){
        				result = numList.get(1) / numList.get(0);
        			} else if("mult".equals(operation)){
        				result = numList.get(0) * numList.get(1);
        			}
        			numList.clear();
    		
    				dq2.addLast(result);
    				break;
    		}
    		
    	}
		exprResult = (int) dq2.getLast();
		logger.info("Result="+exprResult);
		}
		catch(ArithmeticException ae){
			throw ae;
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		return exprResult;
	}

	@Override
	public void intializeStructures() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer splitUsingRegEx(String s) {
		//dq1 = new LinkedList();
		//dq2 = new LinkedList();
		//dq3 = new LinkedList();
		String varName = "";
		int resultValue = 0;
		StringTokenizer tokens = new StringTokenizer(s, ",()", true);
		
		try{
			while(tokens.hasMoreTokens()){
            String tkn = tokens.nextToken();
            if(opList.contains(tkn)){
            	dq1.addLast(tkn);
            } else{
            	if("(".equals(tkn)||
            			",".equals(tkn)||
            			tkn.matches(numRegEx) || tkn.matches(alphaRegEx)){
            		
            		if("let".equals(dq1.getLast())){
            			if(tkn.matches(alphaRegEx)){
            				varName = tkn;
                			assignMap.put(tkn, null);
                			dq2.addLast(tkn);
                		} else if(tkn.matches(numRegEx)){
                			assignMap.put(varName,Integer.parseInt(tkn));
                		}
            			continue;
            		}
            		else{
            			dq2.addLast(tkn);
            		}
            		
            	} 
            	else if(")".equals(tkn)){
            		if(!dq1.isEmpty()){
            				resultValue = doArithMaticOperation();
        					if(!dq1.isEmpty()){
        						if("let".equals((String)dq1.getLast())){
            						dq2.removeLast();
            						
                					dq1.removeLast();
                					assignMap.put((String)dq2.removeLast(),resultValue);
                				} 
        					}
        				}

            	}
            	
      
            }
            
        }
  
		} catch(ArithmeticException ae){
			throw ae;
		}
		
		catch(Exception e){
			logger.fatal("Invalid operation");
		}
		
		return resultValue;
	}

	@Override
	public String validateInput(String input) throws CalcServiceException {
		// TODO Auto-generated method stub
		if(!input.matches(allowedSymbols)){
			CalcServiceException ex = new CalcServiceException();
			ex.setErrorMessage("invalid symbols present in your input please coorect them");
			throw ex;
		}
		else{
			return "input accepted";
		}
		
	}
	
}
