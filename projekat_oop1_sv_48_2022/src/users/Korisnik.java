package users;


public abstract class Korisnik {
	
	protected int id;
	protected String ime;
	protected String prezime;
	protected String pol;
	protected String telefon;
	protected String adresa;
	protected String korisnickoIme;
	protected String lozinka;
	protected Boolean obrisan;
	
	public Korisnik() {}
	public Korisnik(int id, String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka, Boolean obrisan) {
		super();
		this.setId(id);
		this.setIme(ime);
		this.setPrezime(prezime);
		this.setPol(pol);
		this.setTelefon(telefon);
		this.setAdresa(adresa);
		this.setKorisnickoIme(korisnickoIme);
		this.setLozinka(lozinka);
		this.setObrisan(obrisan);
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	public String getPol() {
		return pol;
	}
	public void setPol(String pol) {
		this.pol = pol;
	}
	
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	
	public Boolean getObrisan() {
		return obrisan;
	}
	public void setObrisan(Boolean obrisan) {
		this.obrisan = obrisan;
	}
}
