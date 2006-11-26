package controller;

import model.WorkBook;

/* I'm not sure if this is how they envisioned this
 * builder/director pattern working. Overall, it seems
 * the builder pattern isn't designed to be applied
 * like this, based on all the documentation I've
 * read on builder patterns. I could see it better
 * applied if there were different parsers to choose
 * from, premade workbook preferences, etc. Maybe
 * it'll come into focus once I start working on
 * loading/saving.
 */

public class WorkbookDirector {
	
	//public WorkbookDirector ()
	//{
	//}
	
	public static WorkBook construct ()
	{
		WorkbookBuilder wbb = new WorkbookBuilder ();
		SheetBuilder sb = new SheetBuilder ();
		//ExpressionParserBuilder epb = new ExpressionParserBuilder ();
		WorkBook wb;
		
		wbb.buildPart();
		wb = wbb.getResult();
		
		sb.buildPart();
		wb.addSheet (sb.getResult());
		
		//epb.buildPart();
		//wb.setParser (epb.getResult());
		
		return wb;
	}
}
