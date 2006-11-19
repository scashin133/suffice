package controller;

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

public static class WorkbookDirector {
	
	//public WorkbookDirector ()
	//{
	//}
	
	public Workbook construct ()
	{
		WorkbookBuilder wbb = new WorkbookBuilder ();
		SheetBuilder sb = new SheetBuilder ();
		ExpressionParserBuilder epb = new ExpressionParserBuilder ();
		
		wb = wbb.construct();
		wb.addSheet (sb.construct());
		wb.setParser (epb.construct());
		
		return wb;
	}
}
