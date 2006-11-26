package controller;

import java.io.*;
import model.WorkBook;

public class SufficeController {
	
	// private WorkbookSerializer ws;
	//
	// Due to the simplicity of serialization,
	// using a separate class to help save/load
	// files seems unnecessary. However, it
	// would separate concerns and be easier
	// to expand or transfer.
	//
	// I'll leave it out for now, just to
	// keep things localized for development,
	// but should probably implement 
	// WorkbookSerializer eventually.
	
	public static WorkBook newWorkbook() {
		return WorkbookDirector.construct();
	}
	
	//Storing it as a workbook.
	public static void save(WorkBook w, OutputStream o) {
        try {

        	ObjectOutputStream oos = new ObjectOutputStream (o);
    		oos.writeObject(w);
        }
        catch (Exception e) {}

		// assuming the view will be responsible for selecting
		// file (probably through JFileChooser, creating
		// the OutputStream, and closing it after saving
	}
	
	
	//Retrieving the saved info, then casting it as a workbook. 
	public static void load(InputStream input) {
		try {
			ObjectInputStream ois = new ObjectInputStream (input);
			WorkBook w = (WorkBook) ois.readObject();
			// will readObject() create a new JTable, cause Suffice
			// to start up as normal, etc?
		}
		catch (Exception e) {}
	}
}
