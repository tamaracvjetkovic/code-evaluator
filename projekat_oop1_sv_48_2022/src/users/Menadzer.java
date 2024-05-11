package users;


public class Menadzer extends Korisnik {
	
	
	public Menadzer() {}
	public Menadzer(int id, String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka, Boolean obrisan) {
		super(id, ime, prezime, pol, telefon, adresa, korisnickoIme, lozinka, obrisan);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menadzer other = (Menadzer) obj;
		//if (id != other.id)
		if (getId() != other.getId())
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Menadzer [id = " + this.id + ", ime = " + this.ime + ", prezime = " + this.prezime +
			   ", pol = " + this.pol + ", telefon = " + this.telefon + ", adresa = " + this.adresa + 
			   ", korisnickoIme = " + this.korisnickoIme + ", lozinka = " + this.lozinka + 
			   ", obrisan = " + this.obrisan + "]";                                                                                                              		                                  
	}
	
	
	public String toFileString() {
		return this.id + "," + this.ime + "," + this.prezime + "," + this.pol + "," +
			   this.telefon + "," + this.adresa + "," + this.korisnickoIme + "," +
			   this.lozinka + "," + this.obrisan + ",";
	}
}
