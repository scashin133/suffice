package controller;

import model.WorkBook;

public class WorkbookBuilder extends Builder {
	private WorkBook wb;

	public void buildPart()
	{
		wb = new WorkBook (); // tentative constructor
	}
	public WorkBook getResult()
	{
		return wb;
	}
}
