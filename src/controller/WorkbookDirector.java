package controller;

public class WorkbookDirector {
	WorkbookBuilder workbookBuilder;
	SheetBuilder sheetBuilder;
	ExpressionParserBuilder expressionParserBuilder;
	
	public WorkbookDirector ()
	{
		workbookBuilder = new WorkbookBuilder()
		sheetBuilder = new SheetBuilder()
		expressionParserBuilder = new ExpressionParserBuilder()
	}
	
	public void construct ()
	{
		workbookBuilder.buildPart();
		sheetBuilder.buildPart();
		expressionParserBuilder.buildPart();
	}
}
