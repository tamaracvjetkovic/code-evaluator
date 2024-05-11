package manage;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import users.Recepcioner;


public class RecepcioneriManager {
	
	private String recepcioneriFile;
	private ArrayList<Recepcioner> recepcioneri;

	
	public RecepcioneriManager() {}
	public RecepcioneriManager(String recepcioneriFilename) {
		this.recepcioneriFile = recepcioneriFilename;
		this.recepcioneri = new ArrayList<Recepcioner>();
	}
	
	
	public ArrayList<Recepcioner> getRecepcioneri() {
		return recepcioneri;
	}
	public void setRecepcioneri(ArrayList<Recepcioner> recepcionari) {
		this.recepcioneri = recepcionari;
	}
	
	
	public int getRecepcioneriLastID() {
		return (recepcioneri.size() == 0 ? 0 : recepcioneri.get(recepcioneri.size() - 1).getId());
	}
	
	public Recepcioner pronadjiRecepcioneraPoId(int id) {
		Recepcioner retVal = null;
        for (int i = 0; i < recepcioneri.size(); i++) {
        	Recepcioner r = recepcioneri.get(i);
            if (r.getId() == id) {
                retVal = r;
                break;
            }
        }
        return retVal;
    }
	
		
	public void add(String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka, int nivoStrucneSpreme, int staz, double bonus, double plata, Boolean obrisan) {
		int id = getRecepcioneriLastID() + 1;
		Recepcioner r = new Recepcioner(id, ime, prezime, pol, telefon, adresa, korisnickoIme, lozinka, nivoStrucneSpreme, staz, bonus, plata, obrisan);
		recepcioneri.add(r);
		this.saveData();
    }
	public void add(Recepcioner r) {
		recepcioneri.add(r);
		this.saveData();
    }
	
	public void edit(Recepcioner r, String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka, int nivoStrucneSpreme, int staz, double bonus, double plata, Boolean obrisan) {
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
		r.setObrisan(obrisan);
		this.saveData();
	}
	public void edit(int id, String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka, int nivoStrucneSpreme, int staz, double bonus, double plata) {
		Recepcioner r = pronadjiRecepcioneraPoId(id);
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
		this.saveData();
	}
	
	
	public void remove(int idRecepcionera) {
		Recepcioner r = pronadjiRecepcioneraPoId(idRecepcionera);
		r.setObrisan(true);
		//recepcioneri.remove(r);
		this.saveData();
    }
	public void remove(Recepcioner r) {
		r.setObrisan(true);
		//recepcioneri.remove(r);
		this.saveData();
    }
	
	public ArrayList<Recepcioner> prikaziRecepcionere() {
		ArrayList<Recepcioner> sviRecepcioneri = new ArrayList<Recepcioner>();
		ArrayList<Recepcioner> sviObrisaniRecepcioneri = new ArrayList<Recepcioner>();
		for (Recepcioner r : recepcioneri) {
			if (!r.getObrisan()) {
				sviRecepcioneri.add(r);
			} else {
				sviObrisaniRecepcioneri.add(r);
			}
		}
		return sviRecepcioneri;
	}
	public ArrayList<Recepcioner> prikaziObrisaneRecepcionere() {
		ArrayList<Recepcioner> sviObrisaniRecepcioneri = new ArrayList<Recepcioner>();
		for (Recepcioner r : recepcioneri) {
			if (r.getObrisan()) {
				sviObrisaniRecepcioneri.add(r);
			} 
		}
		return sviObrisaniRecepcioneri;
	}
	
	
	public boolean loadData() {
		this.recepcioneri = new ArrayList<Recepcioner>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.recepcioneriFile));
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
				Boolean obrisan = Boolean.parseBoolean(tokeni[12]);
		 		
				Recepcioner r = new Recepcioner(id, ime, prezime, pol, telefon, adresa, korisnickoIme, lozinka, nivoStrucneSpreme, staz, bonus, plata, obrisan);
				this.recepcioneri.add(r);
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
			pw = new PrintWriter(new FileWriter(this.recepcioneriFile, false));
			for (Recepcioner r : recepcioneri) {
				pw.println(r.toFileString());
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
	        pw = new PrintWriter(new FileWriter(this.recepcioneriFile, false));
	        pw.write("");
	        pw.close();
	        return true;
	    } catch (IOException e) {
	        return false;
	    }
	}
}
