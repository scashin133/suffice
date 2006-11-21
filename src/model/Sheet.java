package model;

import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.eteks.parser.*;

public class Sheet extends AbstractTableModel {

	/**
	 * We can change how these are intialized. Probably should be a value that
	 * is passed to the table model when constructed.
	 */
	private final static int ROW_COUNT = 20;

	private final static int COLUMN_COUNT = 20;

	public String name;

	/**
	 * Where all of our cells are stored. The String is the cell location in
	 * terms of spreadsheet location. For example: row 0 and column 0 == A1 row
	 * 20 and column 5 == F21
	 */
	private TreeMap<String, Cell> cells;

	/**
	 * Initializes the name of the Sheet and its underlying structure.
	 * 
	 * @param name -
	 *            The name of the Sheet that is being created.
	 */
	public Sheet(String name) {
		this.name = name;
		cells = new TreeMap<String, Cell>();

	}

	/**
	 * Changes the Sheets name.
	 * @param name - Name to change the sheet to.
	 */
	public void rename(String name) {
		this.name = name;

	}

	/*
	 * public Object getParameterKey(String parameter) { // return parameter if
	 * it's a legal cell name // regular expression to match // 1 or more
	 * capital letters followed by // 1 or more numbers String regexCellName =
	 * "\\b[A-Z]+\\d+\\b";
	 * 
	 * Pattern cellNamePattern = Pattern.compile(regexCellName); // check to see
	 * if the parameter is a legal cell name, // and if so return it if
	 * (cellNamePattern.matches(regexCellName, parameter)) {
	 * 
	 * return parameter; } else {
	 * 
	 * return null; } }
	 * 
	 * public Object getParameterValue(Object o) { // TODO Auto-generated method
	 * stub return null; }
	 */

	/**
	 * Overrides the AbstractTableModel.getColumnCount so that we can tell
	 * JTable how many columns we want to have in our table model.
	 */
	public int getColumnCount() {

		return COLUMN_COUNT;
	}

	/**
	 * Overrides the AbstractTableModel.getRowCount() so that we can tell the
	 * JTable how many rows we have in our table model.
	 */
	public int getRowCount() {

		return ROW_COUNT;
	}

	/**
	 * Returns the final calculated value of this cell. If it does not find a
	 * cell at this location (has never been accessed before) then it creates a
	 * new cell with an empty string as its calculated value.
	 * 
	 * Used by JTable to initially populate the JTable.
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {

		String cellLocation = getColumnName(columnIndex) + rowIndex;
		Cell tempCell = cells.get(cellLocation);
		if (tempCell == null) {
			cells.put(cellLocation, new Cell(this, rowIndex, columnIndex, ""));
			tempCell = cells.get(cellLocation);
			return tempCell.getCalculatedValue();
		} else {
			return cells.get(cellLocation).getCalculatedValue();
		}
	}

	/**
	 * Because JTable uses getValueAt to populate the table, we could not have
	 * getValueAt give back the cell. Instead we decided to create this function
	 * that will return the cell at the specified location.
	 * 
	 * @param rowIndex
	 * @param columnIndex
	 * @return
	 */
	public Cell getCellAt(int rowIndex, int columnIndex) {
		String cellLocation = getColumnName(columnIndex) + rowIndex;

		Cell tempCell = cells.get(cellLocation);
		System.out.println(tempCell);
		if (tempCell == null) {
			cells.put(cellLocation, new Cell(this, rowIndex, columnIndex, ""));
			return cells.get(cellLocation);
		} else {
			return tempCell;
		}
	}

	/**
	 * Sets the value at the location given to the one listed. Right now we are
	 * only using this for JTable's sake. Will need to explore further if this
	 * could be used elsewhere.
	 */
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		String cellLocation = getColumnName(columnIndex) + rowIndex;
		Cell temp = cells.get(cellLocation);
		if (temp == null) {
			cells.put(cellLocation, new Cell(this, rowIndex, columnIndex,
					aValue));
		} else {

		}
	}

	/**
	 * Our cells are always editable so this always returns true.
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;

	}

}
