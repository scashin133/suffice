package model;

import com.eteks.parser.*;

public class Sheet
implements ExpressionParameter{

	private String name;
	private ExpressionParser parser;
	
	Sheet(String name){
		this.name = name;
	}
	
	public void rename(String name){
		this.name = name;
		
	}

	public Object getParameterKey(String o) {
		// TODO Auto-generated method stubfini
		return null;
	}

	public Object getParameterValue(Object o) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
