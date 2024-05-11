package manage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import treatments.KozmetickaUsluga;
import treatments.ZakazaniTretman;


public class ZakazaniTretmaniManager {
	
	private String zakazaniTretmaniFile;
	private ArrayList<ZakazaniTretman> zakazaniTretmani;
	private KozmetickeUslugeManager kozmetickeUslugeMng;
	
	
	public ZakazaniTretmaniManager() {}
	public ZakazaniTretmaniManager(String zakazaniTretmaniFilename, KozmetickeUslugeManager kozmetickeUslugeMng) {
		this.zakazaniTretmaniFile = zakazaniTretmaniFilename;
		this.zakazaniTretmani = new ArrayList<ZakazaniTretman>();
		this.kozmetickeUslugeMng = kozmetickeUslugeMng;
	}
	
	
	public ArrayList<ZakazaniTretman> getZakazaniTretmani() {
		return zakazaniTretmani;
	}
	public void setZakazaniTretmani(ArrayList<ZakazaniTretman> zakazaniTretmani) {
		this.zakazaniTretmani = zakazaniTretmani;
	}
	
	
	public int getZakazaniTretmaniLastID() {
		return (zakazaniTretmani.size() == 0 ? 0 : zakazaniTretmani.get(zakazaniTretmani.size() - 1).getId());
	}
	
	public ZakazaniTretman pronadjiZakazaniTretmanPoId(int id) {
		ZakazaniTretman retVal = null;
        for (int i = 0; i < zakazaniTretmani.size(); i++) {
        	ZakazaniTretman kt = zakazaniTretmani.get(i);
            if (kt.getId() == id) {
                retVal = kt;
                break;
            }
        }
        return retVal;
    }
	
	
	public void add(KozmetickaUsluga zakazanaKozmetickaUsluga, LocalDateTime datumVreme, int trajanje, double cena, String stanje, int idKozmeticara, int idKlijenta) {
		int id = getZakazaniTretmaniLastID() + 1;
		ZakazaniTretman zt = new ZakazaniTretman(id, zakazanaKozmetickaUsluga, datumVreme, trajanje, cena, stanje, idKozmeticara, idKlijenta);
		zakazaniTretmani.add(zt);
    	this.saveData();
    }
	public void add(ZakazaniTretman zt) {
		zakazaniTretmani.add(zt);
    	this.saveData();
    }
	
	public void edit(int id, KozmetickaUsluga zakazanaKozmetickaUsluga, LocalDateTime datumVreme, int trajanje, double cena, String stanje, int idKozmeticara, int idKlijenta) {
		ZakazaniTretman zt = this.pronadjiZakazaniTretmanPoId(id);
		zt.setId(id);
		zt.setZakazanaKozmetickaUsluga(zakazanaKozmetickaUsluga);
		zt.setDatumVreme(datumVreme);
		zt.setTrajanje(trajanje);
		zt.setCena(cena);
		zt.setStanje(stanje);
		zt.setIdKozmeticara(idKozmeticara);
		zt.setIdKlijenta(idKlijenta);
		this.saveData();
	}
	public void edit(ZakazaniTretman zt, KozmetickaUsluga zakazanaKozmetickaUsluga, LocalDateTime datumVreme, int trajanje, double cena, String stanje, int idKozmeticara, int idKlijenta) {
		zt.setZakazanaKozmetickaUsluga(zakazanaKozmetickaUsluga);
		zt.setDatumVreme(datumVreme);
		zt.setTrajanje(trajanje);
		zt.setCena(cena);
		zt.setStanje(stanje);
		zt.setIdKozmeticara(idKozmeticara);
		zt.setIdKlijenta(idKlijenta);
		this.saveData();
	}
	
	public void remove(int id) {
		ZakazaniTretman zt = this.pronadjiZakazaniTretmanPoId(id);
		zakazaniTretmani.remove(zt);
    	this.saveData();
    }
	public void remove(ZakazaniTretman zt) {
		zakazaniTretmani.remove(zt);
    	this.saveData();
    }
	
	
	public boolean loadData() {
		this.zakazaniTretmani = new ArrayList<ZakazaniTretman>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.zakazaniTretmaniFile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				String[] tokeni = linija.split(",");
				
				int id = Integer.parseInt(tokeni[0]);
				int idUsluge = Integer.parseInt(tokeni[1]);
				KozmetickaUsluga ku = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(idUsluge);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime datumVreme = LocalDateTime.parse(tokeni[2], formatter);
				int trajanje = Integer.parseInt(tokeni[3]);
				double cena = Double.parseDouble(tokeni[4]);
				String stanje = tokeni[5];
				int idKozmeticara = Integer.parseInt(tokeni[6]);
				int idKlijenta = Integer.parseInt(tokeni[7]);
	            
				ZakazaniTretman zt = new ZakazaniTretman(id, ku, datumVreme, trajanje, cena, stanje, idKozmeticara, idKlijenta);
				this.zakazaniTretmani.add(zt);
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
			pw = new PrintWriter(new FileWriter(this.zakazaniTretmaniFile, false));
			for (ZakazaniTretman zt : zakazaniTretmani) {
				pw.println(zt.toFileString());
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
	        pw = new PrintWriter(new FileWriter(this.zakazaniTretmaniFile, false));
	        pw.write("");
	        pw.close();
	        return true;
	    } catch (IOException e) {
	        return false;
	    }
	}
	
}
