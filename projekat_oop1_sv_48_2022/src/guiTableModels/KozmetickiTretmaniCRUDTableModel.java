package guiTableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import manage.KozmetickiTretmaniManager;
import manage.ManageAll;
import treatments.KozmetickiTretman;


public class KozmetickiTretmaniCRUDTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private String[] columnNames = {"ID", "Naziv", "Obrisan"};
	private KozmetickiTretmaniManager kozmetickiTretmaniMng;
	private ArrayList<KozmetickiTretman> data = new ArrayList<KozmetickiTretman>();
	
	
	public KozmetickiTretmaniCRUDTableModel(ManageAll manageAll) {
		this.kozmetickiTretmaniMng = manageAll.getKozmetickiTretmaniMng();
		podesiData();
	}
	
	
	private void podesiData() {
		for (KozmetickiTretman kt : kozmetickiTretmaniMng.getKozmetickiTretmani()) {
			data.add(kt);
		}
	}
	
	public void add(KozmetickiTretman kt) {
		kozmetickiTretmaniMng.add(kt);
    	kozmetickiTretmaniMng.saveData();
    	data.clear();
		podesiData();
    }
	
	public void edit(int id, String naziv) {
		KozmetickiTretman kt = kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(id);
		kt.setNaziv(naziv);
		kozmetickiTretmaniMng.saveData();
		data.clear();
		podesiData();
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	@Override
	public int getRowCount() {
		return kozmetickiTretmaniMng.getKozmetickiTretmani().size();
	}
	@Override
	public Object getValueAt(int row, int col) {
		KozmetickiTretman kt = kozmetickiTretmaniMng.getKozmetickiTretmani().get(row);
		switch(col) {
			case 0: return String.valueOf(kt.getId());
			case 1: return kt.getNaziv();
			case 2: return String.valueOf(kt.getObrisan());
			default: return null;
		}
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		return true;
	}
	
	@Override
	public void setValueAt(Object value, int row, int col) {}
	
	@Override
    public Class<?> getColumnClass(int col) {
        return String.class;
    }

}
