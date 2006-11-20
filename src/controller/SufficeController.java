package controller;

public static class SufficeController {
	
	public SufficeController () {
	}
	
	public static Workbook newWorkbook() {
		return WorkbookDirector.construct();
	}
	
	//Storing it as a workbook. Object OutputStream?
	public static void save(Workbook w, OutputStream o) {
	}
	
	
	//Retrieving the saved info, then casting it as a workbook. 
	//Object InputStream?
	public static void load(InputStream input) {
	}
}
