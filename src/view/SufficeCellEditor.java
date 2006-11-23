/*
 * @(#)JeksCellEditor.java   08/23/99
 *
 * Copyright (c) 1998-2001 Emmanuel PUYBARET / eTeks <info@eteks.com>. All Rights Reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * Visit eTeks web site for up-to-date versions of this file and other
 * Java tools and tutorials : http://www.eteks.com/
 */
package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.DefaultCellEditor;

import model.Cell;
import model.Sheet;

import com.eteks.parser.CompilationException;

/**
 * Allows for a cell to show its raw expression when it is being edited and its
 * computed expression when it is not.
 * 
 * @author Sean
 * 
 */
public class SufficeCellEditor extends DefaultCellEditor {

	private JTable table;

	private Object cellValue;

	/**
	 * Creates our cell editor.
	 * @param paramTextField
	 */
	public SufficeCellEditor(JTextField paramTextField) {
		super(paramTextField); // No default super constructor

		final JTextField textField = paramTextField;
		editorComponent = textField;
		// Reassign an other delegate that checks stopCellEditing ()
		// before firing stop event
		delegate = new EditorDelegate() {
			public void setValue(Object value) {
				textField.setText((value != null) ? value.toString() : "");
			}

			public Object getCellEditorValue() {
				return textField.getText();
			}

			public void actionPerformed(ActionEvent event) {
				if (SufficeCellEditor.this.stopCellEditing())
					super.actionPerformed(event);
			}
		};
		textField.addActionListener(delegate);

	}

	/**
	 * Computes the value entered into the cell and then sets that as the 
	 * raw expression for this cell.  Then changes the displayed value to
	 * the calculated value.
	 */
	public boolean stopCellEditing() {

		String valueEnteredIntoCell = (String) super.getCellEditorValue();
		Sheet sheet = (Sheet) table.getModel();
		Cell tempCell = null;
		
		if (table.getEditingRow() != -1 && table.getEditingColumn() != -1) {
			tempCell = sheet.getCellAt(table.getEditingRow(), table
					.getEditingColumn());
		}

		if (tempCell != null) {
			tempCell.setRawExpression(valueEnteredIntoCell);

			cellValue = tempCell.getCalculatedValue();
		}
		if (cellValue == null) {
			return false;
		} else {
			return super.stopCellEditing();
		}

	}

	/**
	 * The value of the cell while it is being edited.
	 */
	public Object getCellEditorValue() {

		return cellValue;
	}

	/**
	 * Sets the edit value of the cell to whatever this cells raw expression is.
	 */
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {

		Sheet sheet = (Sheet) table.getModel();
		Cell tempCell = sheet.getCellAt(row, column);

		value = tempCell.getRawExpression();

		cellValue = value;

		this.table = table;
		return super.getTableCellEditorComponent(table, value, isSelected, row,
				column);
	}

}
