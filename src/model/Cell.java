package model;

import java.util.regex.Pattern;

import com.eteks.parser.CompilationException;
import com.eteks.parser.CompiledExpression;

public class Cell {

	private int row;

	private int column;

	private String rawExpression;

	private Object calculatedValue;

	private Sheet sheet;

	public Cell(Sheet sheet, int row, int col, Object value) {
		this.sheet = sheet;
		this.row = row;
		this.column = col;
		this.calculatedValue = value;
		setRawExpression(String.valueOf(value));
	}

	/**
	 * Raw meaning before being processed by our parser.
	 * 
	 * @return The expression that is being held by this cell. The expression
	 *         raw expression is not only the expression that our parser deals
	 *         with. It could also be any value that it is given.
	 */
	public String getRawExpression() {
		return rawExpression;
	}

	/**
	 * Checks if the rawExpression starts with an '='. If it does then it does
	 * nothing (right now). If it doesn't start with that then it sets our
	 * calculatedValue to the rawExpression (since no calculations are needed)
	 * and then sets new rawExpression.
	 * 
	 * @param rawExpression
	 */
	public void setRawExpression(String rawExpression) {
		if (rawExpression.length() == 0) {
			calculatedValue = rawExpression;
		} else if (rawExpression.charAt(0) == '=') {

		} else {
			calculatedValue = rawExpression;
		}
		this.rawExpression = rawExpression;
	}

	/**
	 * Value calculated when we set the raw expression. No additional
	 * calculation or work is necessary here because will already be calculated.
	 * 
	 * @return The object that we ended up with at the end of the
	 *         setRawExpression method. Could be String, Int, Double, etc.
	 */
	public Object getCalculatedValue() {
		// TODO Auto-generated method stub
		return calculatedValue;
	}

}
