package guiTableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import manage.ManageAll;
import manage.RecepcioneriManager;
import users.Recepcioner;


public class RecepcioneriCRUDTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;

	private String[] columnNames = {"ID", "Ime", "Prezime", "Pol", "Telefon", "Adresa", "K. Ime", "Lozinka", "S. sprema", "Staž (god.)", "Bonus (RDS)", "Plata (RDS)", "Obrisan"};
	private RecepcioneriManager recepcioneriMng;
	private ArrayList<Recepcioner> data = new ArrayList<Recepcioner>();
	
	
	public RecepcioneriCRUDTableModel(ManageAll manageAll) {
		this.recepcioneriMng = manageAll.getRecepcionariMng();
		podesiData();
	}
	
	private void podesiData() {
		for (Recepcioner r1 : recepcioneriMng.getRecepcioneri()) {
			data.add(r1);
		}
	}
	
	public void add(Recepcioner r) {
		recepcioneriMng.add(r);
		recepcioneriMng.saveData();
		data.clear();
		podesiData();
    }
	
	public void edit(int id, String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka, int nivoStrucneSpreme, int staz, double bonus, double plata) {
		Recepcioner r = recepcioneriMng.pronadjiRecepcioneraPoId(id);
		r.setIme(ime);
		r.setPrezime(prezime);
		r.setPol(pol);
		r.setTelefon(telefon);
		r.setAdresa(adresa);
		r.setKorisnickoIme(korisnickoIme);
		r.setLozinka(lozinka);
		r.setNivoStrucneSpreme(nivoStrucneSpreme);
		r.setStaz(staz);
		r.setBonus(bonus);
		r.setPlata(plata);
		recepcioneriMng.saveData();
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
		Recepcioner r2 = data.get(row);
		switch(col) {
			case 0: return String.valueOf(r2.getId());
			case 1: return r2.getIme();
			case 2: return r2.getPrezime();
			case 3: return r2.getPol();
			case 4: return r2.getTelefon();
			case 5: return r2.getAdresa();
			case 6: return r2.getKorisnickoIme();
			case 7: return r2.getLozinka();
			case 8: return String.valueOf(r2.getNivoStrucneSpreme());
			case 9: return String.valueOf(r2.getStaz());	
			case 10: return String.valueOf(r2.getBonus());
			case 11: return String.valueOf(r2.getPlata());
			case 12: return String.valueOf(r2.getObrisan());
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
