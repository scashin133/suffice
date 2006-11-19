package controller;

public class WorkbookBuilder extends Builder {
	private Workbook wb;

	public void buildPart()
	{
		wb = new Workbook (); // tentative constructor
	}
	public Workbook getResult()
	{
		return wb;
	}
}
