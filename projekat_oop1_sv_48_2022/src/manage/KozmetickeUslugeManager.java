package manage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import treatments.KozmetickaUsluga;


public class KozmetickeUslugeManager {
	
	private String kozmetickeUslugeFile;
	private ArrayList<KozmetickaUsluga> kozmetickeUsluge;

	
	public KozmetickeUslugeManager() {}
	public KozmetickeUslugeManager(String kozmetickeUslugeFilename) {
		this.kozmetickeUslugeFile = kozmetickeUslugeFilename;
		this.kozmetickeUsluge = new ArrayList<KozmetickaUsluga>();
	} 
	
	 
	public ArrayList<KozmetickaUsluga> getKozmetickeUsluge() {
		return kozmetickeUsluge;
	}
	public void setKozmetickeUsluge(ArrayList<KozmetickaUsluga> kozmetickeUsluge) {
		this.kozmetickeUsluge = kozmetickeUsluge;
	}
	 
	
	public int getKozmetickeUslugeLastID() {
		return (kozmetickeUsluge.size() == 0 ? 0 : kozmetickeUsluge.get(kozmetickeUsluge.size() - 1).getId());
	}
	
	public KozmetickaUsluga pronadjiKozmetickuUsluguPoId(int id) {
		KozmetickaUsluga retVal = null;
        for (int i = 0; i < kozmetickeUsluge.size(); i++) {
        	KozmetickaUsluga ku = kozmetickeUsluge.get(i);
            if (ku.getId() == id) {
                retVal = ku;
                break;
            }
        }
        return retVal;
    }
	
	public ArrayList<KozmetickaUsluga> prikaziKozmetickeUsluge() {
		ArrayList<KozmetickaUsluga> sveKozmetickeUsluge = new ArrayList<KozmetickaUsluga>();
		for (KozmetickaUsluga ku : kozmetickeUsluge) {
			if (!ku.getObrisan()) {
				sveKozmetickeUsluge.add(ku); 
			}
		}
		return sveKozmetickeUsluge;
	}
	public ArrayList<KozmetickaUsluga> prikaziObrisaneKozmetickeUsluge() {
		ArrayList<KozmetickaUsluga> sveObrisaneKozmetickeUsluge = new ArrayList<KozmetickaUsluga>();
		for (KozmetickaUsluga ku : kozmetickeUsluge) {
			if (ku.getObrisan()) {
				sveObrisaneKozmetickeUsluge.add(ku);
			}
		}
		return sveObrisaneKozmetickeUsluge;
	}
	
	
	
	
	public void add(String naziv, double cena, int trajanje, int idTretmana) {
		int id = getKozmetickeUslugeLastID() + 1;
		KozmetickaUsluga ku = new KozmetickaUsluga(id, naziv, cena, trajanje, idTretmana, false);
		kozmetickeUsluge.add(ku);
    	this.saveData();
    }
	public void add(KozmetickaUsluga ku) {
		kozmetickeUsluge.add(ku);
    	this.saveData();
    }
	
	public void edit(int id, String naziv, double cena, int trajanje, int idTretmana, Boolean obrisan) {
		KozmetickaUsluga ku = this.pronadjiKozmetickuUsluguPoId(id);
		ku.setId(id);
		ku.setNaziv(naziv);
		ku.setCena(cena);
		ku.setTrajanje(trajanje);
		ku.setIdTretmana(idTretmana);
		ku.setObrisan(obrisan);
		this.saveData();
	}
	public void edit(KozmetickaUsluga ku, String naziv, double cena, int trajanje, int idTretmana, Boolean obrisan) {
		ku.setNaziv(naziv);
		ku.setCena(cena);
		ku.setTrajanje(trajanje);
		ku.setIdTretmana(idTretmana);
		ku.setObrisan(obrisan);
		this.saveData();
	}
	
	public void remove(int id) {
		KozmetickaUsluga ku = this.pronadjiKozmetickuUsluguPoId(id);
		ku.setObrisan(true);
    	this.saveData();
    }
	public void remove(KozmetickaUsluga ku) {
		ku.setObrisan(true);
    	this.saveData();
    }
	

	public boolean loadData() {
		this.kozmetickeUsluge = new ArrayList<KozmetickaUsluga>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.kozmetickeUslugeFile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				String[] tokeni = linija.split(",");
				int id = Integer.parseInt(tokeni[0]);
				String naziv = tokeni[1];
				double cena = Double.parseDouble(tokeni[2]);
				int trajanje = Integer.parseInt(tokeni[3]);
				int idTretmana = Integer.parseInt(tokeni[4]);
				Boolean obrisan = Boolean.parseBoolean(tokeni[5]);
				
				KozmetickaUsluga ku = new KozmetickaUsluga(id, naziv, cena, trajanje, idTretmana, obrisan);
				this.kozmetickeUsluge.add(ku);
			}
			br.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	

	public boolean saveData() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter(this.kozmetickeUslugeFile, false));
			for (KozmetickaUsluga ku : kozmetickeUsluge) {
				pw.println(ku.toFileString());
			}
			pw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public boolean deleteData() {
		PrintWriter pw = null;
	    try {
	        pw = new PrintWriter(new FileWriter(this.kozmetickeUslugeFile, false));
	        pw.write("");
	        pw.close();
	        return true;
	    } catch (IOException e) {
	        return false;
	    }
	}
	
}
