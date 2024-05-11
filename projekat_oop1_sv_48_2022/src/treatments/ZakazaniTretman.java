package treatments;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ZakazaniTretman {
	
	private int id;
	private KozmetickaUsluga zakazanaKozmetickaUsluga;
	private LocalDateTime datumVreme;
	private int trajanje;
	private double cena;
	private String stanje;
	private int idKozmeticara;
	private int idKlijenta;
	 
	
	public ZakazaniTretman() {} 
	public ZakazaniTretman(int id, KozmetickaUsluga zakazanaKozmetickaUsluga, LocalDateTime datumVreme, int trajanje, double cena, String stanje, int idKozmeticara, int idKlijenta) {
		this.setId(id);
		this.setZakazanaKozmetickaUsluga(zakazanaKozmetickaUsluga);
		this.setDatumVreme(datumVreme);
		this.setTrajanje(trajanje);
		this.setCena(cena);
		this.setStanje(stanje);
		this.setIdKozmeticara(idKozmeticara);
		this.setIdKlijenta(idKlijenta);
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public KozmetickaUsluga getZakazanaKozmetickaUsluga() {
		return zakazanaKozmetickaUsluga;
	}
	public void setZakazanaKozmetickaUsluga(KozmetickaUsluga zakazanaKozmetickaUsluga) {
		this.zakazanaKozmetickaUsluga = zakazanaKozmetickaUsluga;
	}
	
	public LocalDateTime getDatumVreme() {
		return datumVreme;
	}
	public void setDatumVreme(LocalDateTime datumVreme) {
		this.datumVreme = datumVreme;
	}
	
	public int getTrajanje() {
		return trajanje;
	}
	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}
	
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	
	public String getStanje() {
		return stanje;
	}
	public void setStanje(String stanje) {
		this.stanje = stanje;
	}
	
	public int getIdKozmeticara() {
		return idKozmeticara;
	}
	public void setIdKozmeticara(int idKozmeticara) {
		this.idKozmeticara = idKozmeticara;
	}
	
	public int getIdKlijenta() {
		return idKlijenta;
	}
	public void setIdKlijenta(int idKlijenta) {
		this.idKlijenta = idKlijenta;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ZakazaniTretman other = (ZakazaniTretman) obj;
		if (getId() != other.getId())
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDatumVreme = this.datumVreme.format(f);
		return "ZakazaniTretman [id = " + this.id + ", datumVreme = " + formattedDatumVreme +  
			   ", trajanje = " + this.trajanje + ", cena = " + this.cena + 
			   ", stanje = " + this.stanje + ", idKozmeticara = " + this.idKozmeticara + 
			   ", idKlijenta = " + this.idKlijenta + 
			   "], Odabrana usluga = " + this.zakazanaKozmetickaUsluga;                                                                                                              		                                  
	}
	
	 
	public String toFileString() {
		int idKU = this.zakazanaKozmetickaUsluga.getId();
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDatumVreme = this.datumVreme.format(f);
		return this.id + "," + idKU + "," + formattedDatumVreme + "," + this.trajanje + "," + 
			   this.cena + "," + this.stanje + "," + this.idKozmeticara + "," + 
		       this.idKlijenta + ",";
	}

}
