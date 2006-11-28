package controller;

import model.WorkBook;
import java.io.*;

public class WorkbookSerializer {
	
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
