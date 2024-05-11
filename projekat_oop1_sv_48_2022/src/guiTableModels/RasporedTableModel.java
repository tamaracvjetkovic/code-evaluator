package guiTableModels;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import manage.KlijentiManager;
import manage.ManageAll;
import treatments.ZakazaniTretman;
import users.Kozmeticar;


public class RasporedTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;

	private String[] columnNames = {"Redni broj", "Zakazana usluga", "Termin", "Trajanje", "Cena", "Stanje", "Klijent"};
	private KlijentiManager klijentiMng;
	private ArrayList<ZakazaniTretman> data = new ArrayList<ZakazaniTretman>();
	
	
	public RasporedTableModel(ManageAll manageAll, Kozmeticar k) {
		this.klijentiMng = manageAll.getKlijentiMng();
		for (ZakazaniTretman zt : k.getRaspored()) {
			data.add(zt);
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
		switch(col) {
			case 0: return String.valueOf(row + 1);
			case 1: return zt.getZakazanaKozmetickaUsluga().getNaziv();
			case 2: return formattedDate + "h";
			case 3: return String.valueOf(zt.getTrajanje()) + "min";
			case 4: return String.valueOf(zt.getCena()) + " RSD";
			case 5: return String.valueOf(zt.getStanje());
			case 6: return String.valueOf(klijentiMng.pronadjiKlijentaPoId(zt.getIdKlijenta()).getIme() + " " + klijentiMng.pronadjiKlijentaPoId(zt.getIdKlijenta()).getPrezime());
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
