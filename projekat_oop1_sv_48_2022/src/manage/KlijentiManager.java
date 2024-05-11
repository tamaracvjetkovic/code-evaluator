package manage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;*/
import java.util.ArrayList;

import treatments.ZakazaniTretman;
import users.Klijent;


public class KlijentiManager {
	
	private String klijentiFile;
	private ArrayList<Klijent> klijenti;
	private ZakazaniTretmaniManager zakazaniTretmaniMng;
	
	
	public KlijentiManager() {}
	public KlijentiManager(String klijentiFilename, ZakazaniTretmaniManager zakazaniTretmaniMng) {
		this.klijentiFile = klijentiFilename;
		this.klijenti = new ArrayList<Klijent>();
		this.zakazaniTretmaniMng = zakazaniTretmaniMng;
	}
	
	
	public ArrayList<Klijent> getKlijenti() {
		return klijenti;
	}
	public void setKlijenti(ArrayList<Klijent> klijenti) {
		this.klijenti = klijenti;
	}
	
	
	public int getKlijentiLastID() {
		return (klijenti.size() == 0 ? 0 : klijenti.get(klijenti.size() - 1).getId());
	}
	
	public Klijent pronadjiKlijentaPoId(int id) {
		Klijent retVal = null;
        for (int i = 0; i < klijenti.size(); i++) {
        	Klijent k = klijenti.get(i);
            if (k.getId() == id) {
                retVal = k;
                break;
            }
        }
        return retVal;
    }
	
	
	public ArrayList<ZakazaniTretman> pregledajRealizovaneTretmane(int idKlijenta) {
		Klijent k = pronadjiKlijentaPoId(idKlijenta);
		return (k.getRealizovaniTretmani());
	}
	public ArrayList<ZakazaniTretman> pregledajRealizovaneTretmane(Klijent k) {
		return (k.getRealizovaniTretmani());
	}
	
	
	public ArrayList<ZakazaniTretman> pregledajNerealizovaneTretmane(int idKlijenta) {
		Klijent k = pronadjiKlijentaPoId(idKlijenta);
		return (k.getNerealizovaniTretmani());
	}
	public ArrayList<ZakazaniTretman> pregledajNerealizovaneTretmane(Klijent k) {
		return (k.getNerealizovaniTretmani());
	}
	
	
	public void add(String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka, Boolean obrisan) {
		int id = getKlijentiLastID() + 1;
		Klijent k = new Klijent(id, ime, prezime, pol, telefon, adresa, korisnickoIme, lozinka, obrisan);
		klijenti.add(k);
		this.saveData();
	}
	public void add(Klijent k) {
		klijenti.add(k);
		this.saveData();
	}
	
	public void edit(int id, String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka, Boolean obrisan) {
		Klijent k = pronadjiKlijentaPoId(id);
		k.setIme(ime);
		k.setPrezime(prezime);
		k.setPol(pol);
		k.setTelefon(telefon);
		k.setAdresa(adresa);
		k.setKorisnickoIme(korisnickoIme);
		k.setLozinka(lozinka);
		k.setObrisan(obrisan);
		this.saveData();
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
	  this.saveData();
	}
	
	public void remove(int id) {
		Klijent k = pronadjiKlijentaPoId(id);
		k.setObrisan(true);
		//klijenti.remove(k);
		this.saveData();
  }
	public void remove(Klijent k) {
		k.setObrisan(true);
		//klijenti.remove(k);
		this.saveData();
	}
	 
	public ArrayList<Klijent> prikaziKlijente() {
		ArrayList<Klijent> sviKlijenti = new ArrayList<Klijent>();
		for (Klijent k : klijenti) {
			if (!k.getObrisan()) {
				sviKlijenti.add(k);
			} 
		}
		return sviKlijenti;
	}
	public ArrayList<Klijent> prikaziObrisaneKlijente() {
		ArrayList<Klijent> sviObrisaniKlijenti = new ArrayList<Klijent>();
		for (Klijent k : klijenti) {
			if (k.getObrisan()) {
				sviObrisaniKlijenti.add(k);
			}
		}
		return sviObrisaniKlijenti;
	}
	
	
	public boolean loadData() {
		this.klijenti = new ArrayList<Klijent>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.klijentiFile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				String[] tokeni = linija.split(",");
				int id = Integer.parseInt(tokeni[0]);
				String ime = tokeni[1];
				String prezime = tokeni[2];
				String pol = tokeni[3];
				String telefon2 = tokeni[4];
				String telefon = telefon2;
				if (telefon2.charAt(0) != '0') {
					telefon = "0" + telefon2;
				}
				String adresa = tokeni[5];
				String korisnickoIme = tokeni[6];
				String lozinka = tokeni[7];
				Boolean karticaLojalnosti = Boolean.parseBoolean(tokeni[8]);
				int n;
				String[] realizovaniID = tokeni[9].split(";");
				ArrayList<ZakazaniTretman> realizovani = new ArrayList<ZakazaniTretman>();
				n = 0;
				if (realizovaniID[0].equals("")) { 
					n = 0;
				} else {
					n = realizovaniID.length;
				}
				for (int i = 0; i < n; i++) {
					int idRealizovanog = Integer.parseInt(realizovaniID[i]);
					ZakazaniTretman zt = zakazaniTretmaniMng.pronadjiZakazaniTretmanPoId(idRealizovanog);
					realizovani.add(zt);
				}
				String[] nerealizovaniID = tokeni[10].split(";");
				ArrayList<ZakazaniTretman> nerealizovani = new ArrayList<ZakazaniTretman>();
				n = 0;
				if (nerealizovaniID[0].equals("")) { 
					n = 0;
				} else {
					n = nerealizovaniID.length;
				}
				for (int i = 0; i < n; i++) {
					int idNerealizovanog = Integer.parseInt(nerealizovaniID[i]);
					ZakazaniTretman zt = zakazaniTretmaniMng.pronadjiZakazaniTretmanPoId(idNerealizovanog);
					nerealizovani.add(zt);
				}
				Boolean obrisan = Boolean.parseBoolean(tokeni[11]);
				
				Klijent k = new Klijent(id, ime, prezime, pol, telefon, adresa, korisnickoIme, lozinka, karticaLojalnosti, realizovani, nerealizovani, obrisan);
				this.klijenti.add(k);
				
			}
			br.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public boolean saveData() {
		this.deleteData();
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter(this.klijentiFile, false));
			for (Klijent k : klijenti) {
				pw.println(k.toFileString());
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
	        pw = new PrintWriter(new FileWriter(this.klijentiFile, false));
	        pw.write("");
	        pw.close();
	        return true;
	    } catch (IOException e) {
	        return false;
	    }
	}
}
