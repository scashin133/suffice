package controller;

//This class is for initialization of the application.
//It creates a new workbook (using the builder), and starts everything up.

public static class SufficeBootstrapper {

	public static void main(String[] args) {
		WorkbookEditor we = new WorkbookEditor ();
		
		SufficeController.newWorkbook();
		
		// pass a Workbook to the WorkbookEditor somehow
	}

}
