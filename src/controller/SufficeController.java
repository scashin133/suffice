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
        

        	ObjectOutputStream oos = null;
			try {
				oos = new ObjectOutputStream (o);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		try {
				oos.writeObject(w);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		try {
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		try {
				o.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       

		// assuming the view will be responsible for selecting
		// file (probably through JFileChooser, creating
		// the OutputStream, and closing it after saving
	}
	
	
	//Retrieving the saved info, then casting it as a workbook. 
	public static WorkBook load(InputStream input) {
		WorkBook w = null;
		
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream (input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				w = (WorkBook) ois.readObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			// will readObject() create a new JTable, cause Suffice
			// to start up as normal, etc?
		
		
		
		return w;
	}
}
