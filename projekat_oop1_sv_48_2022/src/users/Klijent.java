package users;

import java.util.ArrayList;

import treatments.ZakazaniTretman;


public class Klijent extends Korisnik {

	private Boolean karticaLojalnosti;
	private ArrayList<ZakazaniTretman> realizovaniTretmani;
	private ArrayList<ZakazaniTretman> nerealizovaniTretmani;
	
	
	public Klijent() {}
	public Klijent(int id, String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka, Boolean karticaLojalnosti, ArrayList<ZakazaniTretman> realizovaniTretmani, ArrayList<ZakazaniTretman> nerealizovaniTretmani, Boolean obrisan) {
		super(id, ime, prezime, pol, telefon, adresa, korisnickoIme, lozinka, obrisan);
		this.karticaLojalnosti = karticaLojalnosti;
		this.realizovaniTretmani = realizovaniTretmani;
		this.nerealizovaniTretmani = nerealizovaniTretmani;
	} // -> za citanje iz fajla
	public Klijent(int id, String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka, Boolean obrisan) {
		super(id, ime, prezime, pol, telefon, adresa, korisnickoIme, lozinka, obrisan);
		this.karticaLojalnosti = false;
		this.realizovaniTretmani = new ArrayList<ZakazaniTretman>();
		this.nerealizovaniTretmani = new ArrayList<ZakazaniTretman>();
	}
	
	
	public Boolean getKarticaLojalnosti() {
		return karticaLojalnosti;
	}
	public void setKarticaLojalnosti(Boolean karticaLojalnosti) {
		this.karticaLojalnosti = karticaLojalnosti;
	}
	
	
	public void addRealizovaniTretman(ZakazaniTretman zt) {
		this.realizovaniTretmani.add(zt);
	}
	public ArrayList<ZakazaniTretman> getRealizovaniTretmani() {
		return realizovaniTretmani;
	}
	public void setRealizovaniTretmani(ArrayList<ZakazaniTretman> realizovaniTretmani) {
		this.realizovaniTretmani = realizovaniTretmani;
	}
	
	public void addNerealizovaniTretman(ZakazaniTretman zt) {
		this.nerealizovaniTretmani.add(zt);
	}
	public ArrayList<ZakazaniTretman> getNerealizovaniTretmani() {
		return nerealizovaniTretmani;
	}
	public void setNerealizovaniTretmani(ArrayList<ZakazaniTretman> nerealizovaniTretmani) {
		this.nerealizovaniTretmani = nerealizovaniTretmani;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Klijent other = (Klijent) obj;
		//if (id != other.id)
		if (getId() != other.getId())
			return false;
		return true;
	}
	
	@Override 
	public String toString() {
		return "Klijent [id = " + this.id + ", ime = " + this.ime + ", prezime = " + this.prezime +
			   ", pol = " + this.pol + ", telefon = " + this.telefon + ", adresa = " + this.adresa + 
			   ", korisnickoIme = " + this.korisnickoIme + ", lozinka = " + this.lozinka + 
			   ", karticaLojalnosti = " + this.karticaLojalnosti + 
			   ", obrisan = " + this.getObrisan() + "]\nRealizovaniTretmani = " + this.realizovaniTretmani + 
			   "\nNerealizovaniTretmani = " + this.nerealizovaniTretmani + "\n";                                                                                                              		                                  
	}
	
	public String toStringOnlyBasicInfo() {
		return "Klijent [id = " + this.id + ", ime = " + this.ime + ", prezime = " + this.prezime +
			   ", pol = " + this.pol + ", telefon = " + this.telefon + ", adresa = " + this.adresa + 
			   ", korisnickoIme = " + this.korisnickoIme + ", lozinka = " + this.lozinka + 
			   ", karticaLojalnosti = " + this.karticaLojalnosti + 
			   ", obrisan = " + this.getObrisan() + "]\n";
	}
	
	public String toFileString() {
		String nerealizovaniID = "";
		for (ZakazaniTretman zt : this.nerealizovaniTretmani) {
			int id = zt.getId();
			nerealizovaniID += (Integer.toString(id) + ";");
		}
		String realizovaniID = "";
		for (ZakazaniTretman zt : this.realizovaniTretmani) {
			int id = zt.getId();
			realizovaniID += (Integer.toString(id) + ";");
		}
		return this.id + "," + this.ime + "," + this.prezime + "," + this.pol + "," +
			     this.telefon + "," + this.adresa + "," + this.korisnickoIme + "," +
			     this.lozinka + "," + karticaLojalnosti + "," + realizovaniID + "," +
			     nerealizovaniID + "," + this.getObrisan() + ",";
	}
}
