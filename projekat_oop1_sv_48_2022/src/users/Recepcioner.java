package users;


public class Recepcioner extends Zaposleni {
		
	
	public Recepcioner() {}
	public Recepcioner(int id, String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka, int nivoStrucneSpreme, int staz, double bonus, double plata, Boolean obrisan) {
		super(id, ime, prezime, pol, telefon, adresa, korisnickoIme, lozinka, nivoStrucneSpreme, staz, bonus, plata, obrisan);
		
	} // -> za citanje iz fajla
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recepcioner other = (Recepcioner) obj;
		//if (id != other.id)
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
			   " , obrisan = " + this.obrisan + "]\n";                                                                                                           		                                  
	}
	
	
	public String toFileString() {
		return this.id + "," + this.ime + "," + this.prezime + "," + this.pol + "," +
			   this.telefon + "," + this.adresa + "," + this.korisnickoIme + "," +
			   this.lozinka + "," + this.nivoStrucneSpreme + "," + this.staz + "," + 
			   this.bonus + "," +  this.plata + "," + this.obrisan + ",";
	}
}
