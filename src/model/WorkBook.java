package model;

import java.io.Serializable;
import java.util.ArrayList;

public class WorkBook implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6179891068265228196L;

	private ArrayList<Sheet> sheets;

	public WorkBook() {
		sheets = new ArrayList<Sheet>();
	}

	public void addSheet(Sheet s) {
		sheets.add(s);
	}

	public boolean removeSheet(String name) {

		for (Sheet s : sheets) {

			// this is the sheet we want to remove,
			// so do it and return true
			if (s.getName() == name) {
				sheets.remove(s);
				return true;
			}

		}

		// sheet was never found, nothing removed
		return false;

	}

	public ArrayList<Sheet> getSheets() {
		return sheets;
	}

	public boolean setSheetOrder(String name, int zOrder) {

		for (int i = 0; i < sheets.size(); i++) {

			Sheet s = sheets.get(i);

			// this is the sheet who's zOrder we wish to change
			if (s.getName() == name) {

				if (!(i == zOrder)) {
					// remove this sheet and insert it at the desired zOrder
					sheets.remove(s);
					sheets.add(zOrder, s);
				} else {
					// target sheet is already at the desired
					// zOrder, do nothing
				}

				return true;

			}

		}

		// never found the target sheet
		return false;

	}

	public String toString() {
		String string = "";
		for (Sheet s : sheets) {
			string += s.getName();
		}
		return string;

	}

}
