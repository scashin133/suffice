package model;

import com.eteks.parser.CompilationException;
import com.eteks.parser.CompiledExpression;

public class Cell {
	
	private int row;
	private int column;
	private String rawExpression;
	private Sheet sheet;
	private CompiledExpression compiledExpression;
	
	public Cell(Sheet sheet, int row, int col){
		this.sheet = sheet;
		this.row = row;
		this.column = col;
	}
	
	public boolean recompile(){
		
		try {
			this.compiledExpression = this.sheet.parser.compileExpression(rawExpression);
		} catch (CompilationException e) {
			return false;
		}
		
		return true;
	}

	public String getRawExpression() {
		return this.rawExpression;
	}

	public void setRawExpression(String rawExpression) {
		this.rawExpression = rawExpression;
	}
	
	
	
}
