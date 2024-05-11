package users;

import java.util.ArrayList;

import treatments.KozmetickiTretman;
import treatments.ZakazaniTretman;


public class Kozmeticar extends Zaposleni {
	
	private ArrayList<KozmetickiTretman> kozmetickiTretmani; // koje zna
	private ArrayList<ZakazaniTretman> raspored;
	 
	public Kozmeticar() {}
	// ZA CITANJE IZ FAJLA
	public Kozmeticar(int id, String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka, int nivoStrucneSpreme, int staz, double bonus, double plata, ArrayList<KozmetickiTretman> kozmetickiTretmani, ArrayList<ZakazaniTretman> raspored, Boolean obrisan) {
		super(id, ime, prezime, pol, telefon, adresa, korisnickoIme, lozinka, nivoStrucneSpreme, staz, bonus, plata, obrisan);
		this.kozmetickiTretmani = kozmetickiTretmani;
		this.raspored = raspored;
	} 
	public Kozmeticar(int id, String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka, int nivoStrucneSpreme, int staz, double bonus, double plata, ArrayList<KozmetickiTretman> kozmetickiTretmani, Boolean obrisan) {
		super(id, ime, prezime, pol, telefon, adresa, korisnickoIme, lozinka, nivoStrucneSpreme, staz, bonus, plata, obrisan);
		this.kozmetickiTretmani = kozmetickiTretmani;
		this.raspored = new ArrayList<ZakazaniTretman>();
	} 
	// ZA DODAVANJE NOVOG KOZMETICARA
	public Kozmeticar(int id, String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka, int nivoStrucneSpreme, int staz, double bonus, double plata, Boolean obrisan) {
		super(id, ime, prezime, pol, telefon, adresa, korisnickoIme, lozinka, nivoStrucneSpreme, staz, bonus, plata, obrisan);
		this.kozmetickiTretmani = new ArrayList<KozmetickiTretman>();
		this.raspored = new ArrayList<ZakazaniTretman>();
	}
	
	
	public ArrayList<KozmetickiTretman> getKozmetickiTretmani() {
		return kozmetickiTretmani;
	}
	public void setKozmetickiTretmani(ArrayList<KozmetickiTretman> kozmetickiTretmani) {
		this.kozmetickiTretmani = kozmetickiTretmani;
	}
	
	public ArrayList<ZakazaniTretman> getRaspored() {
		return raspored;
	}
	public void setRaspored(ArrayList<ZakazaniTretman> raspored) {
		this.raspored = raspored;
	}
	public void addToRaspored(ZakazaniTretman zt) {
		this.raspored.add(zt);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kozmeticar other = (Kozmeticar) obj;
		if (getId() != other.getId())
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Kozmeticar [id = " + this.id + ", ime = " + this.ime + ", prezime = " + this.prezime +
			   ", pol = " + this.pol + ", telefon = " + this.telefon + ", adresa = " + this.adresa + 
			   ", korisnickoIme = " + this.korisnickoIme + ", lozinka = " + this.lozinka +
			   ", nivoStrucneSpreme = " + this.nivoStrucneSpreme + ", staz = " + this.staz + 
			   ", bonus = " + this.bonus + ", plata = " + this.plata +
			   " , obrisan = " + this.obrisan + "]\nKozmetickiTretmani: " + this.kozmetickiTretmani +
			   "\nRaspored: " + this.raspored + "\n";                                                                                                              		                                  
	}  
	
	public String toFileString() {
		String kozmetickiTretmaniID = "";
		for (KozmetickiTretman kt : this.kozmetickiTretmani) {
			int id = kt.getId();
			kozmetickiTretmaniID += (Integer.toString(id) + ";");
		}
		String zakazaniTretmaniID = "";
		for (ZakazaniTretman zt : this.raspored) {
			int id = zt.getId();
			zakazaniTretmaniID += (Integer.toString(id) + ";");
		}
		//System.out.println("RAS: " + rasporedFajl);
		return this.id + "," + this.ime + "," + this.prezime + "," + this.pol + "," +
			   this.telefon + "," + this.adresa + "," + this.korisnickoIme + "," +
		       this.lozinka + "," + this.nivoStrucneSpreme + "," + this.staz + "," + 
			   this.bonus + "," +  this.plata + "," + kozmetickiTretmaniID + "," + 
		       zakazaniTretmaniID + "," + this.obrisan + ",";
	}
}
