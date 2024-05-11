package treatments;


public class KozmetickiTretman {
	
	private int id;
	private String naziv;
	private Boolean obrisan;
	
	 
	public KozmetickiTretman(int id, String naziv, Boolean obrisan) {
		this.setId(id);
		this.setNaziv(naziv);
		this.obrisan = obrisan; 
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
		KozmetickiTretman other = (KozmetickiTretman) obj;
		if (getId() != other.getId())
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "KozmetickiTretman [id = " + this.id + ", naziv = " + this.naziv + 
			   ", obrisan = " + this.obrisan + "]";                                                                                                              		                                  
	}
	 
	public String toFileString() {
		return this.id + "," + this.naziv + "," + this.obrisan + ",";
	}
	
}
