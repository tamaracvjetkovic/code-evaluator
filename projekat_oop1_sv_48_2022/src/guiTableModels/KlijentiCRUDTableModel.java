package guiTableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import manage.KlijentiManager;
import manage.ManageAll;
import users.Klijent;


public class KlijentiCRUDTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private String[] columnNames = {"ID", "Ime", "Prezime", "Pol", "Telefon", "Adresa", "K. Ime", "Lozinka", "Kartica", "Obrisan"};
	private KlijentiManager klijentiMng;
	private ArrayList<Klijent> data = new ArrayList<Klijent>();
	private int br1;
	private int br2;
	private int ok;
	
	public KlijentiCRUDTableModel(ManageAll manageAll, int ok) {
		this.klijentiMng = manageAll.getKlijentiMng();
		this.ok = ok;
		this.br1 = 0;
		this.br2 = 0;
		podesiData();
	}
	
	private void podesiData() {
		if (ok == 0) {
			for (Klijent k1 : klijentiMng.getKlijenti()) {
				data.add(k1);
			}
		} else if (ok == 2) {
			for (Klijent k1 : klijentiMng.getKlijenti()) {
				if (k1.getObrisan() == true) {
					continue;
				}
				br2++;
				data.add(k1);
			}
		} else {
			for (Klijent k1 : klijentiMng.getKlijenti()) {
				if (k1.getKarticaLojalnosti() == true) {
					br1++;
					data.add(k1);
				}
			}
		}
	}
	
	public void add(Klijent k) {
		klijentiMng.add(k);
		klijentiMng.saveData();
		data.clear();
		podesiData();
	}
	
	public void edit(Klijent k, String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka, Boolean obrisan) {
		k.setIme(ime);
		k.setPrezime(prezime);
		k.setPol(pol);
		k.setTelefon(telefon);
		k.setAdresa(adresa);
		k.setKorisnickoIme(korisnickoIme);
		k.setLozinka(lozinka);
		k.setObrisan(obrisan);
		klijentiMng.saveData();
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
		if (this.ok == 1) {
			return br1;
		} 
		if (this.ok == 2) {
			return br2;
		}
		return data.size();
	}
	@Override
	public Object getValueAt(int row, int col) {
		Klijent k2 = data.get(row);
		switch(col) {
			case 0: return String.valueOf(k2.getId());
			case 1: return k2.getIme();
			case 2: return k2.getPrezime();
			case 3: return k2.getPol();
			case 4: return k2.getTelefon();
			case 5: return k2.getAdresa();
			case 6: return k2.getKorisnickoIme();
			case 7: return k2.getLozinka();
			case 8: return String.valueOf(k2.getKarticaLojalnosti());
			case 9: return String.valueOf(k2.getObrisan());
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
