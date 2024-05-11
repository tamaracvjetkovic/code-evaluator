package utils;

public class AppSettings {
	
	private String menadzeriFilename;
	private String kozmeticariFilename;
	private String recepcioneriFilename;
	private String klijentiFilename;
	private String kozmetickeUslugeFilename;
	private String kozmetickiTretmaniFilename;
	private String zakazaniTretmaniFilename; 
	
	public AppSettings() {}
	public AppSettings(String menadzeriFilename, String kozmeticariFilename, String recepcioneriFilename, String klijentiFilename, String kozmetickeUslugeFilename, String kozmetickiTretmaniFilename, String zakazaniTretmaniFilename) {
		super();
		this.menadzeriFilename = menadzeriFilename;
		this.kozmeticariFilename = kozmeticariFilename;
		this.recepcioneriFilename = recepcioneriFilename;
		this.klijentiFilename = klijentiFilename;
		this.setKozmetickeUslugeFilename(kozmetickeUslugeFilename);
		this.setKozmetickiTretmaniFilename(kozmetickiTretmaniFilename);
		this.setZakazaniTretmaniFilename(zakazaniTretmaniFilename);
	}
	
	
	public String getMenadzeriFilename() {
		return menadzeriFilename;
	}
	public String getKozmeticariFilename() {
		return kozmeticariFilename;
	}	
	public String getRecepcioneriFilename() {
		return recepcioneriFilename;
	}
	public String getKlijentiFilename() {
		return klijentiFilename;
	}
	public String getKozmetickeUslugeFilename() {
		return kozmetickeUslugeFilename;
	}
	public void setKozmetickeUslugeFilename(String kozmetickeUslugeFilename) {
		this.kozmetickeUslugeFilename = kozmetickeUslugeFilename;
	}
	public String getKozmetickiTretmaniFilename() {
		return kozmetickiTretmaniFilename;
	}
	public void setKozmetickiTretmaniFilename(String kozmetickiTretmaniFilename) {
		this.kozmetickiTretmaniFilename = kozmetickiTretmaniFilename;
	}
	public String getZakazaniTretmaniFilename() {
		return zakazaniTretmaniFilename;
	}
	public void setZakazaniTretmaniFilename(String zakazaniTretmaniFilename) {
		this.zakazaniTretmaniFilename = zakazaniTretmaniFilename;
	}	
	
}
