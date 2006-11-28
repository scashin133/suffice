package view;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import model.Sheet;

class RowHeaderListModel extends AbstractListModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7675217368873032737L;
	ArrayList<Integer> headers;
	
	public RowHeaderListModel(Sheet s) {
		headers = new ArrayList<Integer>();
		for(int x = 0; x < s.getRowCount(); x++) {
			headers.add(x + 1);
		}
	}
	public int getSize() {
		return headers.size();
	}

	public Object getElementAt(int arg0) {
		return headers.get(arg0);
	}
	
}