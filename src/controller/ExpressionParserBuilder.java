package controller;

import com.eteks.parser.ExpressionParser;
import com.eteks.jeks.JeksParameter;
import com.eteks.parser.DoubleInterpreter;
import javax.swing.table.TableModel;

public class ExpressionParserBuilder extends Builder {
	private ExpressionParser ep;
	
	public ExpressionParser ()
	{
	}

	public void buildPart()
	{
		JeksExpressionSyntax s = new JeksExpressionSyntax (); // using default locale
		
		Interpreter i = new Interpreter (new DoubleInterpreter()); // I'm pretty sure we'll be using 
																   // DoubleInterpreter, as opposed to
																   // MathMLInterpreter
		
		// arbitrary numbers for table dimensions
		int numOfRows = 100;
		int numOfColumns = 20;
		DefaultTableModel dtm = DefaultTableModel(numOfRows, numOfColumns);
		
		JeksParameter jp = new JeksParameter (s, i, dtm);
		ep = new ExpressionParser (jp);
	}
	public ExpressionParser getResult()
	{
		return ep;
	}
}
