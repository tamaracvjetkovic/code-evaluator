package treatments;

//import java.util.ArrayList;

//import users.Kozmeticar;

public class KozmetickaUsluga {
	
	private int id;
	private String naziv; //usluga
	private double cena; 
	private int trajanje; // minute
	private int idTretmana;
	private Boolean obrisan;
	
	 
	public KozmetickaUsluga() {}
	public KozmetickaUsluga(int id, String naziv, double cena, int trajanje, int idTretmana, Boolean obrisan) {
		this.setId(id); 
		this.setNaziv(naziv);
		this.setCena(cena);
		this.setTrajanje(trajanje);
		this.setIdTretmana(idTretmana);
		this.setObrisan(obrisan);
	}
 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	
	
	public int getTrajanje() {
		return trajanje;
	}
	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

	public int getIdTretmana() {
		return idTretmana;
	}
	public void setIdTretmana(int idTretmana) {
		this.idTretmana = idTretmana;
	}

	public Boolean getObrisan() {
		return obrisan;
	}
	public void setObrisan(Boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KozmetickaUsluga other = (KozmetickaUsluga) obj;
		if (getId() != other.getId())
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "KozmetickaUsluga [id = " + this.id + ", naziv = " + this.naziv +
			   ", cena = " + this.cena + ", trajanje = " + this.trajanje + 
			   ", idTretmana = " + this.idTretmana + ", obrisan = " + this.obrisan + "]";                                                                                                              		                                  
	}
	
	
	public String toFileString() {
		return this.id + "," + this.naziv + "," + this.cena + "," + this.trajanje + "," + 
			   this.idTretmana + "," + this.obrisan + ",";
	}
	
}
