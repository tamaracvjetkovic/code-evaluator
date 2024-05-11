package guiTableModels;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import manage.KlijentiManager;
import manage.KozmeticariManager;
import manage.KozmetickiTretmaniManager;
import manage.ManageAll;
import manage.ZakazaniTretmaniManager;
import treatments.ZakazaniTretman;


public class ZakazaniTretmaniCRUDTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private String[] columnNames = {"ID", "Tip", "Tretman", "Termin", "Trajanje", "Cena", "Stanje", "Kozmeticar", "Klijent"};
	private KozmeticariManager kozmeticariMng;
	private ZakazaniTretmaniManager zakazaniTretmaniMng;
	private KozmetickiTretmaniManager kozmetickiTretmaniMng;
	private KlijentiManager klijentiMng;
	private ArrayList<ZakazaniTretman> data = new ArrayList<ZakazaniTretman>();
	
	
	public ZakazaniTretmaniCRUDTableModel(ManageAll manageAll, int opcija) {
		this.kozmeticariMng = manageAll.getKozmeticariMng();
		this.zakazaniTretmaniMng = manageAll.getZakazaniTretmaniMng();
		this.kozmetickiTretmaniMng = manageAll.getKozmetickiTretmaniMng();
		this.klijentiMng = manageAll.getKlijentiMng();
		for (ZakazaniTretman zt : zakazaniTretmaniMng.getZakazaniTretmani()) {
			if (opcija != 1) {
				data.add(zt);
			} else {
				if (!zt.getStanje().equals("ZAKAZAN")) {
					continue;
				} else {
					data.add(zt);
				}
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
			case 0: return String.valueOf(zt.getId());
			case 1: return zt.getZakazanaKozmetickaUsluga().getNaziv();
			case 2: return kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(idTret).getNaziv();
			case 3: return formattedDate + "h";
			case 4: return String.valueOf(zt.getTrajanje()) + "min";
			case 5: return zt.getCena();
			case 6: return String.valueOf(zt.getStanje());
			case 7: return String.valueOf(kozmeticariMng.pronadjiKozmeticaraPoId(zt.getIdKozmeticara()).getIme() + " " + kozmeticariMng.pronadjiKozmeticaraPoId(zt.getIdKozmeticara()).getPrezime());
			case 8: return String.valueOf(klijentiMng.pronadjiKlijentaPoId(zt.getIdKlijenta()).getIme() + " " + klijentiMng.pronadjiKlijentaPoId(zt.getIdKlijenta()).getPrezime());
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
