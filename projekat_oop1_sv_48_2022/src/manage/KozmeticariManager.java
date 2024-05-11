package manage;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import treatments.KozmetickiTretman;
import treatments.ZakazaniTretman;
import users.Kozmeticar;


public class KozmeticariManager {
	private String kozmeticariFile;
	private ArrayList<Kozmeticar> kozmeticari;
	private ArrayList<KozmetickiTretman> kozmetickiTretmani;
	private KozmetickiTretmaniManager kozmetickiTretmaniMng;
	private ZakazaniTretmaniManager zakazaniTretmaniMng;
	
	public KozmeticariManager() {} 
	public KozmeticariManager(String kozmeticariFilename, KozmetickiTretmaniManager kozmetickiTretmaniMng, ZakazaniTretmaniManager zakazaniTretmaniMng) {
		this.kozmeticariFile = kozmeticariFilename;
		this.kozmeticari = new ArrayList<Kozmeticar>();
		this.kozmetickiTretmani = new ArrayList<KozmetickiTretman>();
		this.kozmetickiTretmaniMng = kozmetickiTretmaniMng;
		this.zakazaniTretmaniMng = zakazaniTretmaniMng;
	} 
	
	
	public ArrayList<Kozmeticar> getKozmeticari() {
		return kozmeticari;
	} 
	public void setKozmeticari(ArrayList<Kozmeticar> kozmeticari) {
		this.kozmeticari = kozmeticari;
	}
	
	public ArrayList<KozmetickiTretman> getKozmetickiTretmani() {
		return kozmetickiTretmani;
	}
	public void setKozmetickiTretmani(ArrayList<KozmetickiTretman> kozmetickiTretmani) {
		this.kozmetickiTretmani = kozmetickiTretmani;
	}
	
	
	public int getKozmeticariLastID() {
		return (kozmeticari.size() == 0 ? 0 : kozmeticari.get(kozmeticari.size() - 1).getId());
	}
	
	public Kozmeticar pronadjiKozmeticaraPoId(int id) {
		Kozmeticar retVal = null;
        for (int i = 0; i < kozmeticari.size(); i++) {
        	Kozmeticar k = kozmeticari.get(i);
            if (k.getId() == id) {
                retVal = k;
                break;
            }
        }
        return retVal;
    }
	
	public void dodajTretmanKozmeticaru(int idKozmeticara, KozmetickiTretman kt) {
		Kozmeticar k = pronadjiKozmeticaraPoId(idKozmeticara);
		k.getKozmetickiTretmani().add(kt);
		this.saveData();
	}
	
	public void add(String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka, int nivoStrucneSpreme, int staz, double bonus, double plata, Boolean obrisan) {
		int id = getKozmeticariLastID() + 1;
		Kozmeticar k = new Kozmeticar(id, ime, prezime, pol, telefon, adresa, korisnickoIme, lozinka, nivoStrucneSpreme, staz, bonus, plata, obrisan);
		kozmeticari.add(k);
    	this.saveData();
    }
	public void add(String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka, int nivoStrucneSpreme, int staz, double bonus, double plata, ArrayList<KozmetickiTretman> kozmetickiTretmani, ArrayList<ZakazaniTretman> zakazaniTretmani, Boolean obrisan) {
		int id = getKozmeticariLastID() + 1;
		Kozmeticar k = new Kozmeticar(id, ime, prezime, pol, telefon, adresa, korisnickoIme, lozinka, nivoStrucneSpreme, staz, bonus, plata, kozmetickiTretmani, zakazaniTretmani, obrisan);
		kozmeticari.add(k);
    	this.saveData();
    }
	public void add(Kozmeticar k) {
		kozmeticari.add(k);
    	this.saveData();
    }
	
	public void edit(int id, String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka, Boolean obrisan, int nivoStrucneSpreme, int staz, double bonus, double plata, ArrayList<KozmetickiTretman> tretmani, ArrayList<ZakazaniTretman> raspored) {
		Kozmeticar k = pronadjiKozmeticaraPoId(id);
		k.setIme(ime);
		k.setPrezime(prezime);
		k.setPol(pol);
		k.setTelefon(telefon);
		k.setAdresa(adresa);
		k.setKorisnickoIme(korisnickoIme);
		k.setLozinka(lozinka);
		k.setObrisan(obrisan);
		k.setNivoStrucneSpreme(nivoStrucneSpreme);
		k.setStaz(staz);
		k.setBonus(bonus);
		k.setPlata(plata);
		k.setKozmetickiTretmani(tretmani);
		k.setRaspored(raspored);
		this.saveData();
	}
	public void edit(int id, String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka, int nivoStrucneSpreme, int staz, double bonus, double plata, ArrayList<KozmetickiTretman> tretmani) {
		Kozmeticar k = pronadjiKozmeticaraPoId(id);
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
		k.setKozmetickiTretmani(tretmani);;
		this.saveData();
	}
	public void edit(int id, String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka, int nivoStrucneSpreme, int staz, double bonus, double plata) {
		Kozmeticar k = pronadjiKozmeticaraPoId(id);
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
		this.saveData();
	}
	
	public void remove(int id) {
		Kozmeticar k = pronadjiKozmeticaraPoId(id);
		k.setObrisan(true);
		//kozmeticari.remove(k);
    	this.saveData();
    }
	public void remove(Kozmeticar k) {
		k.setObrisan(true);
		//kozmeticari.remove(k);
    	this.saveData();
    }
	
	public ArrayList<Kozmeticar> prikaziKozmeticare() {
		ArrayList<Kozmeticar> sviKozmeticari = new ArrayList<Kozmeticar>();
		for (Kozmeticar k : kozmeticari) {
			if (!k.getObrisan()) {
				sviKozmeticari.add(k);
			} 
		}
		return sviKozmeticari;
	}
	public ArrayList<Kozmeticar> prikaziObrisaneKozmeticare() {
		ArrayList<Kozmeticar> sviObrisaniKozmeticari = new ArrayList<Kozmeticar>();
		for (Kozmeticar k : kozmeticari) {
			if (k.getObrisan()) {
				sviObrisaniKozmeticari.add(k);
			} 
		}
		return sviObrisaniKozmeticari;
	}

	
	public boolean loadData() {
		this.kozmeticari = new ArrayList<Kozmeticar>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.kozmeticariFile));
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
				int nivoStrucneSpreme = Integer.parseInt(tokeni[8]);
				int staz = Integer.parseInt(tokeni[9]);
				double bonus = Double.parseDouble(tokeni[10]);
				double plata = Double.parseDouble(tokeni[11]);
				int n;
				String[] kozmetickiTretmaniID = tokeni[12].split(";");
				ArrayList<KozmetickiTretman> kozmetickiTretmani = new ArrayList<KozmetickiTretman>();
				n = 0;
				if (kozmetickiTretmaniID[0].equals("")) { 
					n = 0;
				} else {
					n = kozmetickiTretmaniID.length;
				}
				for (int i = 0; i < n; i++) {
					int idKozmetickogTretmana = Integer.parseInt(kozmetickiTretmaniID[i]);
					KozmetickiTretman kt = kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(idKozmetickogTretmana);
					kozmetickiTretmani.add(kt);
				}
				String[] zakazaniTretmaniID = tokeni[13].split(";");
				ArrayList<ZakazaniTretman> zakazaniTretmani = new ArrayList<ZakazaniTretman>();
				n = 0;
				if (zakazaniTretmaniID[0].equals("")) { 
					n = 0;
				} else {
					n = zakazaniTretmaniID.length;
				}
				for (int i = 0; i < n; i++) {
					int idZakazanogTretmana = Integer.parseInt(zakazaniTretmaniID[i]);
					ZakazaniTretman zt = zakazaniTretmaniMng.pronadjiZakazaniTretmanPoId(idZakazanogTretmana);
					zakazaniTretmani.add(zt);
				}
				Boolean obrisan = Boolean.parseBoolean(tokeni[14]);
				
				Kozmeticar k = new Kozmeticar(id, ime, prezime, pol, telefon, adresa, korisnickoIme, lozinka, nivoStrucneSpreme, staz, bonus, plata, kozmetickiTretmani, zakazaniTretmani, obrisan);
	            this.kozmeticari.add(k);
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
			pw = new PrintWriter(new FileWriter(this.kozmeticariFile, false));
			for (Kozmeticar s : kozmeticari) {
				pw.println(s.toFileString());
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
	        pw = new PrintWriter(new FileWriter(this.kozmeticariFile, false));
	        pw.write("");
	        pw.close();
	        return true;
	    } catch (IOException e) {
	        return false;
	    }
	}
}
