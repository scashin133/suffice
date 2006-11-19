package controller;

public static class SufficeController {
	
	public SufficeController () {
	}
	
	public static Workbook newWorkbook() {
		return WorkbookDirector.construct();
	}
	
	public static void save(Workbook w, OutputStream o) {
	}
	
	public static void load(InputStream input) {
	}
}
