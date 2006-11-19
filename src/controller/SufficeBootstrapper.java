package controller;

public static class SufficeBootstrapper {

	public static void main(String[] args) {
		WorkbookEditor we = new WorkbookEditor ();
		
		SufficeController.newWorkbook();
		
		// pass a Workbook to the WorkbookEditor somehow
	}

}
