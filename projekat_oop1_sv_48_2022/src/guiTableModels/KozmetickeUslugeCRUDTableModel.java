package guiTableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import manage.KozmetickeUslugeManager;
import manage.KozmetickiTretmaniManager;
import manage.ManageAll;
import treatments.KozmetickaUsluga;


public class KozmetickeUslugeCRUDTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private String[] columnNames = {"ID", "Naziv", "Cena", "Trajanje", "Tretman", "Obrisan"};
	private KozmetickeUslugeManager kozmetickeUslugeMng;
	private KozmetickiTretmaniManager kozmetickiTretmaniMng;
	private ArrayList<KozmetickaUsluga> data = new ArrayList<KozmetickaUsluga>();
	
	
	public KozmetickeUslugeCRUDTableModel(ManageAll manageAll) {
		this.kozmetickeUslugeMng = manageAll.getKozmetickeUslugeMng();
		this.kozmetickiTretmaniMng = manageAll.getKozmetickiTretmaniMng();
		podesiData();
	}
	
	
	private void podesiData() {
		for (KozmetickaUsluga ku : kozmetickeUslugeMng.getKozmetickeUsluge()) {
			data.add(ku);
		}
	}
	
	
	public void add(KozmetickaUsluga ku) {
		kozmetickeUslugeMng.add(ku);
		kozmetickeUslugeMng.saveData();
		data.clear();
		podesiData();
    }
	
	public void edit(int id, String naziv, double cena, int trajanje, int idTretmana, Boolean obrisan) {
		KozmetickaUsluga ku = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(id);
		ku.setId(id);
		ku.setNaziv(naziv);
		ku.setCena(cena);
		ku.setTrajanje(trajanje);
		ku.setIdTretmana(idTretmana);
		ku.setObrisan(obrisan);
		kozmetickeUslugeMng.saveData();
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
		return data.size();
	}
	@Override
	public Object getValueAt(int row, int col) {
		KozmetickaUsluga ku = data.get(row);
		switch(col) {
			case 0: return String.valueOf(ku.getId());
			case 1: return ku.getNaziv();
			case 2: return String.valueOf(ku.getCena()) + " RSD";
			case 3: return String.valueOf(ku.getTrajanje()) + "min";
			case 4: return String.valueOf(kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(ku.getIdTretmana()).getNaziv());
			case 5: return String.valueOf(ku.getObrisan());
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
