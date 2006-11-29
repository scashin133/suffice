package controller;

import model.WorkBook;

public class ConcreteWorkbookFactory implements IWorkbookFactory{

	public WorkBook createWorkbook() {
		return new WorkBook();
	}
}
