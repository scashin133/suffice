package view;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import model.Sheet;

public class WorkbookEditor extends JFrame {
	
	private JMenuBar menubar;
	private JMenu menu;
	private JMenuItem openfile;
	private JMenuItem saveas;
	private JMenuItem save;
	private JMenuItem exit;
	private JMenuItem newsheet;
	private JMenuItem removesheet;
	private JMenuItem renamesheet;
	private JTabbedPane tabpane;
		
	public WorkbookEditor() {
		
		menubar = new JMenuBar();
		formMenu(); 
		this.setJMenuBar(menubar);
		
		tabpane = new JTabbedPane();
		
	
		tabpane.addTab("Sheet 1", makeSheetPanel("1"));
		
		this.add(tabpane);
				
		this.setVisible(true);
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.pack();
		this.setTitle("Suffice");
		this.setResizable(true);
	}
	
	private void formMenu() {
		menu = new JMenu("File");
		menu.getAccessibleContext().setAccessibleDescription("Perform file operations");
		menubar.add(menu);
		
		openfile = new JMenuItem("Open File...");
		saveas = new JMenuItem("Save As...");
		save = new JMenuItem("Save");
		exit = new JMenuItem("Exit");
		menu.add(openfile);
		menu.addSeparator();
		menu.add(save);
		menu.add(saveas);
		menu.addSeparator();
		menu.add(exit);
		
		menu = new JMenu("Workbook");
		menu.getAccessibleContext().setAccessibleDescription("Perform actions on this workbook");
		menubar.add(menu);
		
		newsheet = new JMenuItem("Add new spreadsheet");
		newsheet.addActionListener(new AddNewSheetListener());
		removesheet = new JMenuItem("Remove current spreadsheet");
		removesheet.addActionListener(new RemoveSheetListener());
		renamesheet = new JMenuItem("Rename sheet");
		renamesheet.addActionListener(new RenameSheetListener());
		menu.add(newsheet);
		menu.add(removesheet);
		menu.add(renamesheet);
	}
	
	private JPanel makeSheetPanel(String title) {
		Sheet testSheet = new Sheet("Sheet1");
		SufficeTable testTable = new SufficeTable(testSheet);
		TableColumnModel testColumnModel = testTable.getColumnModel();
		Enumeration<TableColumn> columns = testColumnModel.getColumns();
		while (columns.hasMoreElements()) {
			TableColumn column = columns.nextElement();

			column.setHeaderValue(testSheet.getColumnName(column.getModelIndex()));
		}

		JPanel tablePanel = new JPanel();
		tablePanel.add(new JScrollPane(testTable));
		return tablePanel;
	}
	
	private void addNewSheet() {
		tabpane.addTab("Sheet " + (tabpane.getTabCount() + 1), makeSheetPanel("Test"));
	}
	
	private void removeSheet() {
		tabpane.remove(tabpane.getSelectedIndex());
		
		String sheetTitleRegExp = "Sheet [0-9]*";
		for(int x = 0; x < tabpane.getTabCount(); x++) {
			String tabTitle = tabpane.getTitleAt(x);
			if(tabTitle.matches(sheetTitleRegExp)) {
				tabpane.setTitleAt(x, "Sheet " + (x+1));
			}
		}
	}
	
	private void renameSheet() {
		int index = tabpane.getSelectedIndex();
		String newName = JOptionPane.showInputDialog("Rename " + tabpane.getTitleAt(index) + " to:");
		if(!(newName == null)) {
			tabpane.setTitleAt(index, newName); 
		}
	}
	
	private class AddNewSheetListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			addNewSheet();
		}
	}
	
	private class RemoveSheetListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			removeSheet();
		}
	}
	
	private class RenameSheetListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			renameSheet();
		}
	}

}
