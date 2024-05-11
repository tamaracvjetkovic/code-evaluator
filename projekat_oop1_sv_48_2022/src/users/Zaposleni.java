package users;

public abstract class Zaposleni extends Korisnik {
	
	protected int nivoStrucneSpreme;
	protected int staz;
	protected double bonus;
	protected double plata;
	
	
	public Zaposleni() {}
	public Zaposleni(int id, String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka, int nivoStrucneSpreme, int staz, double bonus, double plata, Boolean obrisan) {
		super(id, ime, prezime, pol, telefon, adresa, korisnickoIme, lozinka, obrisan);
		this.nivoStrucneSpreme = nivoStrucneSpreme;
		this.staz = staz;
		this.bonus = bonus;
		this.plata = plata;
	}
	
	public int getNivoStrucneSpreme() {
		return nivoStrucneSpreme;
	}
	public void setNivoStrucneSpreme(int nivoStrucneSpreme) {
		this.nivoStrucneSpreme = nivoStrucneSpreme;
	}
	
	public int getStaz() {
		return staz;
	}
	public void setStaz(int staz) {
		this.staz = staz;
	}
	
	public double getBonus() {
		return bonus;
	}
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	
	public double getPlata() {
		return plata;
	}
	public void setPlata(double plata) {
		this.plata = plata;
	}
}
