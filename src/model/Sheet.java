package model;

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

	public Object getParameterKey(String o) {
		// TODO Auto-generated method stubfini
		return null;
	}

	public Object getParameterValue(Object o) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
