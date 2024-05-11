package guiTableModels;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import manage.KozmetickeUslugeManager;
import manage.KozmetickiTretmaniManager;
import manage.ManageAll;
import manage.ZakazaniTretmaniManager;
import treatments.KozmetickaUsluga;
import treatments.ZakazaniTretman;


public class Izvestaji3TableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private String[] columnNames = {"Naziv", "Cena", "Trajanje", "Tretman", "Broj zakazanih", "Prihod"};
	private KozmetickeUslugeManager kozmetickeUslugeMng;
	private KozmetickiTretmaniManager kozmetickiTretmaniMng;
	private ZakazaniTretmaniManager zakazaniTretmaniMng;
	private ArrayList<Podatak> data = new ArrayList<Podatak>();
	
	
	public Izvestaji3TableModel(ManageAll manageAll, LocalDateTime d1, LocalDateTime d2) {
		this.kozmetickeUslugeMng = manageAll.getKozmetickeUslugeMng();
		this.kozmetickiTretmaniMng = manageAll.getKozmetickiTretmaniMng();
		this.zakazaniTretmaniMng = manageAll.getZakazaniTretmaniMng();
		for (KozmetickaUsluga ku2 : kozmetickeUslugeMng.getKozmetickeUsluge()) {
			if (ku2.getObrisan() == true) {
				continue;
			}
			double prihodi = 0;
			int brZakazanih = 0;
			for (ZakazaniTretman zt : zakazaniTretmaniMng.getZakazaniTretmani()) {
				if (zt.getDatumVreme().isAfter(d1) && zt.getDatumVreme().isBefore(d2)) {
					if (zt.getZakazanaKozmetickaUsluga().equals(ku2)) {
						if (!zt.getStanje().equals("ZAKAZAN")) {
							continue;
						}
						brZakazanih += 1;
						prihodi += zt.getCena();			
					}
					
				}
			}
			data.add(new Podatak(ku2, brZakazanih, prihodi));
		}
		
	}
	
	
	private class Podatak {
		
		private KozmetickaUsluga ku;
		private int brZakazanih;
		private Double prihodi;
		
		private Podatak(KozmetickaUsluga ku, int brZakazanih, Double prihodi) {
            this.ku = ku;
            this.brZakazanih = brZakazanih;
            this.prihodi = prihodi;
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
			case 0: return p.ku.getNaziv();
			case 1: return String.valueOf(p.ku.getCena()) + " RSD";
			case 2: return String.valueOf(p.ku.getTrajanje()) + "min";
			case 3: return String.valueOf(kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(p.ku.getIdTretmana()).getNaziv());
			case 4: return String.valueOf(p.brZakazanih);
			case 5: return String.valueOf(p.prihodi);
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
