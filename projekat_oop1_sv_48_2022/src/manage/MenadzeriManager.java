package manage;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import users.Menadzer;


public class MenadzeriManager {
	
	private String menadzeriFile;
	private ArrayList<Menadzer> menadzeri;
	
	
	public MenadzeriManager() {}
	public MenadzeriManager(String menadzeriFilename) {
		this.menadzeriFile = menadzeriFilename;
		this.menadzeri = new ArrayList<Menadzer>();
	}
	
	
	public ArrayList<Menadzer> getMenadzeri() {
		return menadzeri;
	}
	public void setMenadzeri(ArrayList<Menadzer> menadzeri) {
		this.menadzeri = menadzeri;
	}
	
	
	public int getMenadzeriLastID() {
		return (menadzeri.size() == 0 ? 0 : menadzeri.get(menadzeri.size() - 1).getId());
	}
	
	public Menadzer pronadjiMenadzeraPoId(int id) {
		Menadzer retVal = null;
        for (int i = 0; i < menadzeri.size(); i++) {
        	Menadzer m = menadzeri.get(i);
            if (m.getId() == id) {
                retVal = m;
                break;
            }
        }
        return retVal;
    }
	
	public void add(String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka, Boolean obrisan) {
		int id = getMenadzeriLastID() + 1;
		Menadzer m = new Menadzer(id, ime, prezime, pol, telefon, adresa, korisnickoIme, lozinka, obrisan);
    	menadzeri.add(m);
    	this.saveData();
    }
	public void add(Menadzer m) {
		menadzeri.add(m);
		this.saveData();
    }
	
	public void edit(int id, String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka, Boolean obrisan) {
		Menadzer m = pronadjiMenadzeraPoId(id);
		m.setIme(ime);
		m.setPrezime(prezime);
		m.setPol(pol);
		m.setTelefon(telefon);
		m.setAdresa(adresa);
		m.setKorisnickoIme(korisnickoIme);
		m.setLozinka(lozinka);
		m.setObrisan(obrisan);
		this.saveData();
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
		this.saveData();
	}
	
	public void remove(int id) {
		Menadzer m = pronadjiMenadzeraPoId(id);
		m.setObrisan(true);
		//menadzeri.remove(m);
		this.saveData();
    }
	public void remove(Menadzer m) {
		m.setObrisan(true);
		//menadzeri.remove(m);
    	this.saveData();
    }
	
	public ArrayList<Menadzer> prikaziMenadzere() {
		ArrayList<Menadzer> sviMenadzeri = new ArrayList<Menadzer>();
		ArrayList<Menadzer> sviObrisaniMenadzeri = new ArrayList<Menadzer>();
		for (Menadzer m : menadzeri) {
			if (!m.getObrisan()) {
				sviMenadzeri.add(m);
			} else {
				sviObrisaniMenadzeri.add(m);
			}
		}
		return sviMenadzeri;
	}
	public ArrayList<Menadzer> prikaziObrisaneMenadzere() {
		ArrayList<Menadzer> sviObrisaniMenadzeri = new ArrayList<Menadzer>();
		for (Menadzer m : menadzeri) {
			if (!m.getObrisan()) {
				sviObrisaniMenadzeri.add(m);
			}
		}
		return sviObrisaniMenadzeri;
	}
	

	public boolean loadData() {
		this.menadzeri = new ArrayList<Menadzer>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.menadzeriFile));
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
				Boolean obrisan = Boolean.parseBoolean(tokeni[8]);
				
				Menadzer s = new Menadzer(id, ime, prezime, pol, telefon, adresa, korisnickoIme, lozinka, obrisan);
				this.menadzeri.add(s);
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
			pw = new PrintWriter(new FileWriter(this.menadzeriFile, false));
			for (Menadzer s : menadzeri) {
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
	        pw = new PrintWriter(new FileWriter(this.menadzeriFile, false));
	        pw.write("");
	        pw.close();
	        return true;
	    } catch (IOException e) {
	        return false;
	    }
	}
	
}
