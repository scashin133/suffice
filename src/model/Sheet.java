package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.table.DefaultTableModel;

import com.eteks.parser.*;

public class Sheet
extends DefaultTableModel
implements ExpressionParameter
{

	public String name;
	public ExpressionParser parser;
	
	Sheet(String name){
		this.name = name;
		parser = new ExpressionParser(this);
	}
	
	public void rename(String name){
		this.name = name;
		
	}

	public Object getParameterKey(String parameter) {
		
		// return parameter if it's a legal cell name
		
		// regular expression to match
		// 1 or more capital letters followed by
		// 1 or more numbers
		String regexCellName = "\\b[A-Z]+\\d+\\b";
		
		Pattern cellNamePattern = Pattern.compile(regexCellName);
		
		// check to see if the parameter is a legal cell name,
		// and if so return it
		if(cellNamePattern.matches(regexCellName, parameter)){

			return parameter;
			
		}
		else{
			
			return null;
			
		}
		
		
	}

	public Object getParameterValue(Object o) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
