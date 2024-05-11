package guiTableModels;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import manage.KozmeticariManager;
import manage.ManageAll;
import manage.ZakazaniTretmaniManager;
import treatments.ZakazaniTretman;
import users.Kozmeticar;


public class Izvestaji1TableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private String[] columnNames = {"Kozmetičar", "Broj tretmana", "Prihod"};
	private KozmeticariManager kozmeticariMng;
	private ZakazaniTretmaniManager zakazaniTretmaniMng;
	private ArrayList<Podatak> data = new ArrayList<Podatak>();
	
	
	public Izvestaji1TableModel(ManageAll manageAll, LocalDateTime d1, LocalDateTime d2) {
		this.kozmeticariMng = manageAll.getKozmeticariMng();
		this.zakazaniTretmaniMng = manageAll.getZakazaniTretmaniMng();
		manageAll.getKozmetickiTretmaniMng();
		for (Kozmeticar k : kozmeticariMng.getKozmeticari()) {
			double prihodovao = 0;
			int brTretmana = 0;
			for (ZakazaniTretman zt : zakazaniTretmaniMng.getZakazaniTretmani()) {
				if (zt.getDatumVreme().isAfter(d1) && zt.getDatumVreme().isBefore(d2)) {
					if (!zt.getStanje().equals("IZVRŠEN")) {
						continue;
					}
					if (zt.getIdKozmeticara() == k.getId()) {
						brTretmana += 1;
						prihodovao += zt.getCena();
					}
				}
			}
			data.add(new Podatak(k, brTretmana, prihodovao));
		}
		
	}
	
	
	private class Podatak {
		
		private Kozmeticar k;
		private int brTretmana;
		private Double prihodovao;
		
		private Podatak(Kozmeticar k, int brTretmana, Double prihodovao) {
            this.k = k;
            this.brTretmana = brTretmana;
            this.prihodovao = prihodovao;
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
		Podatak p = data.get(row);
		switch(col) {
			case 0: return p.k.getIme() + " " + p.k.getPrezime();
			case 1: return String.valueOf(p.brTretmana);
			case 2: return String.valueOf(p.prihodovao) + " RSD";
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
