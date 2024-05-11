package guiTableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import manage.ManageAll;
import manage.MenadzeriManager;
import users.Menadzer;


public class MenadzeriCRUDTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = -5519372712630599241L;
	
	private String[] columnNames = {"ID", "Ime", "Prezime", "Pol", "Telefon", "Adresa", "K. Ime", "Lozinka", "Obrisan"};
	private MenadzeriManager menadzeriMng;
	private ArrayList<Menadzer> data = new ArrayList<Menadzer>();
	
	
	public MenadzeriCRUDTableModel(ManageAll manageAll) {
		this.menadzeriMng = manageAll.getMenadzeriMng();
		podesiData();
	}
		
	
	private void podesiData() {
		for (Menadzer m1 : menadzeriMng.getMenadzeri()) {
			data.add(m1);
		}
	}
	
	public void add(Menadzer m) {
		menadzeriMng.add(m);
		menadzeriMng.saveData();
		data.clear();
		podesiData();
    }
	
	public void edit(Menadzer m, String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka, Boolean obrisan) {
		m.setIme(ime);
		m.setPrezime(prezime);
		m.setPol(pol);
		m.setTelefon(telefon);
		m.setAdresa(adresa);
		m.setKorisnickoIme(korisnickoIme);
		m.setLozinka(lozinka);
		m.setObrisan(obrisan);
		menadzeriMng.saveData();
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
		return menadzeriMng.getMenadzeri().size();
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		Menadzer m2 = menadzeriMng.getMenadzeri().get(row);
		switch(col) {
			case 0: return String.valueOf(m2.getId());
			case 1: return m2.getIme();
			case 2: return m2.getPrezime();
			case 3: return m2.getPol();
			case 4: return m2.getTelefon();
			case 5: return m2.getAdresa();
			case 6: return m2.getKorisnickoIme();
			case 7: return m2.getLozinka();
			case 8: return String.valueOf(m2.getObrisan());
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
