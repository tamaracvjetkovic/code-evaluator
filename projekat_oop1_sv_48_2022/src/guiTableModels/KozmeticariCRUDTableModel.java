package guiTableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import manage.KozmeticariManager;
import manage.ManageAll;
import treatments.KozmetickiTretman;
import users.Kozmeticar;

public class KozmeticariCRUDTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;

	private String[] columnNames = {"ID", "Ime", "Prezime", "Pol", "Telefon", "Adresa", "K. Ime", "Lozinka", "S. sprema", "Staž (god.)", "Bonus (RSD)", "Plata (RSD)", "Kozmetički tretmani", "Obrisan"};
	private KozmeticariManager kozmeticariMng;
	private ArrayList<Kozmeticar> data = new ArrayList<Kozmeticar>();
	
	
	public KozmeticariCRUDTableModel(ManageAll manageAll) {
		this.kozmeticariMng = manageAll.getKozmeticariMng();
		podesiData();
	}
	
	
	private void podesiData() {
		for (Kozmeticar k1 : kozmeticariMng.getKozmeticari()) {
			data.add(k1);
		}
	}
	
	public void add(Kozmeticar k) {
		kozmeticariMng.add(k);
		kozmeticariMng.saveData();
		data.clear();
		podesiData();
    }
	
	public void edit(Kozmeticar k, String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka, int nivoStrucneSpreme, int staz, double bonus, double plata) {
		k.setIme(ime);
		k.setPrezime(prezime);
		k.setPol(pol);
		k.setTelefon(telefon);
		k.setAdresa(adresa);
		k.setKorisnickoIme(korisnickoIme);
		k.setLozinka(lozinka);
		k.setNivoStrucneSpreme(nivoStrucneSpreme);
		k.setStaz(staz);
		k.setBonus(bonus);
		k.setPlata(plata);
		kozmeticariMng.saveData();
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
		Kozmeticar k2 = data.get(row);
		String tretmani = "";
		for (KozmetickiTretman kt : k2.getKozmetickiTretmani()) {
			tretmani += String.valueOf(kt.getNaziv());
			tretmani += ",";
		}
		switch(col) {
			case 0: return String.valueOf(k2.getId());
			case 1: return k2.getIme();
			case 2: return k2.getPrezime();
			case 3: return k2.getPol();
			case 4: return k2.getTelefon();
			case 5: return k2.getAdresa();
			case 6: return k2.getKorisnickoIme();
			case 7: return k2.getLozinka();
			case 8: return String.valueOf(k2.getNivoStrucneSpreme());
			case 9: return String.valueOf(k2.getStaz());	
			case 10: return String.valueOf(k2.getBonus());
			case 11: return String.valueOf(k2.getPlata());
			case 12: return tretmani;
			case 13: return String.valueOf(k2.getObrisan());
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
