package guiTableModels;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import manage.KozmeticariManager;
import manage.KozmetickiTretmaniManager;
import manage.ManageAll;
import manage.ZakazaniTretmaniManager;
import treatments.ZakazaniTretman;
import users.Klijent;


public class ZakazaniTretmaniKlijentTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = -5519372712630599241L;
	
	private String[] columnNames = {"Redni broj", "Tip", "Tretman", "Termin", "Trajanje", "Cena", "Stanje", "Kozmetičar"};
	private ManageAll manageAll;
	private KozmeticariManager kozmeticariMng;
	private KozmetickiTretmaniManager kozmetickiTretmaniMng;
	private ZakazaniTretmaniManager zakazaniTretmaniMng;
	private ArrayList<ZakazaniTretman> data = new ArrayList<ZakazaniTretman>();
	
	
	public ZakazaniTretmaniKlijentTableModel(ManageAll manageAll, Klijent k) {
		this.manageAll = manageAll;
		this.kozmeticariMng = manageAll.getKozmeticariMng();
		this.kozmetickiTretmaniMng = manageAll.getKozmetickiTretmaniMng();
		this.zakazaniTretmaniMng =  manageAll.getZakazaniTretmaniMng();
		manageAll.getKozmetickeUslugeMng();
		for (ZakazaniTretman zt : zakazaniTretmaniMng.getZakazaniTretmani()) {
			if (zt.getIdKlijenta() == k.getId()) {
				data.add(zt);
			}
		}
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
		ZakazaniTretman zt = data.get(row);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy.  HH:mm");
		String formattedDate = format.format(zt.getDatumVreme());
		int idTret = zt.getZakazanaKozmetickaUsluga().getIdTretmana();
		switch(col) {
			case 0: return row + 1;
			case 1: return zt.getZakazanaKozmetickaUsluga().getNaziv();
			case 2: return kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(idTret).getNaziv();
			case 3: return formattedDate + "h";
			case 4: return String.valueOf(zt.getTrajanje()) + "min";
			case 5: return zt.getCena();
			case 6: return String.valueOf(zt.getStanje());
			case 7: return String.valueOf(kozmeticariMng.pronadjiKozmeticaraPoId(zt.getIdKozmeticara()).getIme() + " " + kozmeticariMng.pronadjiKozmeticaraPoId(zt.getIdKozmeticara()).getPrezime());
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
	

	public void otkaziZakazaniTretman(int row) {
		ZakazaniTretman zt = data.get(row);
		if (!zt.getStanje().equals("ZAKAZAN")) {
			JOptionPane.showMessageDialog(null, "Tretman je već otkazan!", null, JOptionPane.WARNING_MESSAGE);
			return;
		}
		int rb = row + 1;
		int choice = JOptionPane.showConfirmDialog(null, "Potvrdite otkazivanje tretmana sa rednim brojem " + rb +  ".");
		if (choice == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "Tretman uspešno otkazan!");
			manageAll.otkaziTretman("klijent", zt);
			fireTableDataChanged();	
		} 
	}
}