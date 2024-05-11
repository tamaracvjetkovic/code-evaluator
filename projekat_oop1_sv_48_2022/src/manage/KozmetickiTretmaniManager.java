package manage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import treatments.KozmetickaUsluga;
import treatments.KozmetickiTretman;


public class KozmetickiTretmaniManager {
	
	private String kozmetickiTretmaniFile;
	private ArrayList<KozmetickiTretman> kozmetickiTretmani;
	private KozmetickeUslugeManager kozmetickeUslugeMng;
	
	
	public KozmetickiTretmaniManager() {} 
	public KozmetickiTretmaniManager(String kozmetickiTretmaniFile, KozmetickeUslugeManager kozmetickeUslugeMng) {
		this.kozmetickiTretmaniFile = kozmetickiTretmaniFile;
		this.kozmetickiTretmani = new ArrayList<KozmetickiTretman>();
		this.kozmetickeUslugeMng = kozmetickeUslugeMng;
	} 
	   
	
	public ArrayList<KozmetickiTretman> getKozmetickiTretmani() {
		return kozmetickiTretmani;
	}
	public void setKozmetickiTretmani(ArrayList<KozmetickiTretman> kozmetickiTretmani) {
		this.kozmetickiTretmani = kozmetickiTretmani;
	}
	

	public int getKozmetickiTretmaniLastID() {
		return (kozmetickiTretmani.size() == 0 ? 0 : kozmetickiTretmani.get(kozmetickiTretmani.size() - 1).getId());
	}
	
	
	public KozmetickiTretman pronadjiKozmetickiTretmanPoId(int id) {
		KozmetickiTretman retVal = null;
        for (int i = 0; i < kozmetickiTretmani.size(); i++) {
        	KozmetickiTretman kt = kozmetickiTretmani.get(i);
            if (kt.getId() == id) {
                retVal = kt;
                break;
            }
        }
        return retVal;
    }
			
	public void add(String naziv, Boolean obrisan) {
		int id = getKozmetickiTretmaniLastID() + 1;
		KozmetickiTretman kt = new KozmetickiTretman(id, naziv, obrisan);
		kozmetickiTretmani.add(kt);
    	this.saveData();
    }
	public void add(KozmetickiTretman kt) {
		kozmetickiTretmani.add(kt);
    	this.saveData();
    }
	
	public void edit(int id, String naziv) {
		KozmetickiTretman kt = this.pronadjiKozmetickiTretmanPoId(id);
		kt.setNaziv(naziv);
		this.saveData();
	}
	public void edit(KozmetickiTretman kt, String naziv) {
		kt.setNaziv(naziv);
		this.saveData();
	}
	
	public void remove(int id) {
		KozmetickiTretman kt = this.pronadjiKozmetickiTretmanPoId(id);
		kt.setObrisan(true);
		for (KozmetickaUsluga ku : kozmetickeUslugeMng.getKozmetickeUsluge()) {
			if (ku.getIdTretmana() == id) {
				ku.setObrisan(true);
			}
		}
		this.saveData();
		kozmetickeUslugeMng.saveData();
	}
	public void remove(KozmetickiTretman kt) {
		kt.setObrisan(true);
		for (KozmetickaUsluga ku : kozmetickeUslugeMng.getKozmetickeUsluge()) {
			if (ku.getIdTretmana() == kt.getId()) {
				ku.setObrisan(true);
			}
		}
    	this.saveData();
    	kozmetickeUslugeMng.saveData();
    } 
	
	public void vratiObrisanTretman(int idTretmana) {
		KozmetickiTretman kt = this.pronadjiKozmetickiTretmanPoId(idTretmana);
		kt.setObrisan(false);
		for (KozmetickaUsluga ku : kozmetickeUslugeMng.getKozmetickeUsluge()) {
			if (ku.getIdTretmana() == idTretmana) {
				ku.setObrisan(false);
			}
		}
		this.saveData();
		kozmetickeUslugeMng.saveData();
	}
	
	
	public boolean loadData() {
		this.kozmetickiTretmani = new ArrayList<KozmetickiTretman>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.kozmetickiTretmaniFile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				String[] tokeni = linija.split(",");
				int id = Integer.parseInt(tokeni[0]);
				String naziv = tokeni[1];
				Boolean obrisan = Boolean.parseBoolean(tokeni[2]);	
				
				KozmetickiTretman kt = new KozmetickiTretman(id, naziv, obrisan);
				this.kozmetickiTretmani.add(kt);
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
			pw = new PrintWriter(new FileWriter(this.kozmetickiTretmaniFile, false));
			for (KozmetickiTretman kt : kozmetickiTretmani) {
				pw.println(kt.toFileString());
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
	        pw = new PrintWriter(new FileWriter(this.kozmetickiTretmaniFile, false));
	        pw.write("");
	        pw.close();
	        return true;
	    } catch (IOException e) {
	        return false;
	    }
	}
	
}
