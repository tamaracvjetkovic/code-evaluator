
package utils;

import java.util.ArrayList;

import treatments.ZakazaniTretman;


public class Pair {

	private int[] brIzvrsenihTretmanaPoKozmeticaru;
	private double[] ukupnaZaradaPoKozmeticaru;  // ZA IZVESTAJ 1
	
	private int brojIzvrsenihUsluga;
	private int[] brIzvrsenihUslugaPoStanju;
	private double prihodiOdUsluge; // ZA IZVESTAJ 3
	
	private ZakazaniTretman zt;
	private double potrosioZaZT; // DA SE NAPRAVI PAR ZA F1
	private ArrayList<Pair> ztSaTroskom;
	private double ukupanTrosak; // ZA F1
	
	 
	public Pair() {}
	public Pair(int[] brIzvrsenihTretmanaPoKozmeticaru, double[] ukupnaZaradaPoKozmeticaru) {
		this.brIzvrsenihTretmanaPoKozmeticaru = brIzvrsenihTretmanaPoKozmeticaru;
		this.ukupnaZaradaPoKozmeticaru = ukupnaZaradaPoKozmeticaru;
	}
	public Pair(int brojIzvrsenihUsluga, int[] brIzvrsenihUslugaPoStanju, double prihodiOdUsluge) {
		this.brojIzvrsenihUsluga = brojIzvrsenihUsluga;
		this.setBrIzvrsenihUslugaPoStanju(brIzvrsenihUslugaPoStanju);
		this.prihodiOdUsluge = prihodiOdUsluge;
	}
    public Pair(ZakazaniTretman zt, double potrosioZaZT) {
		this.setZt(zt);
		this.setPotrosioZaZT(potrosioZaZT);
	}
    public Pair(ArrayList<Pair> ztSaTroskom, double ukupanTrosak) { 
    	this.setZtSaTroskom(ztSaTroskom);
    	this.setUkupanTrosak(ukupanTrosak);
    }
    
    
	public int[] getBrIzvrsenihTretmanaPoKozmeticaru() {
		return brIzvrsenihTretmanaPoKozmeticaru;
	}
	public void setBrIzvrsenihTretmanaPoKozmeticarurr1(int[] brIzvrsenihTretmanaPoKozmeticaru) {
		this.brIzvrsenihTretmanaPoKozmeticaru = brIzvrsenihTretmanaPoKozmeticaru;
	}
	public double[] getUkupnaZaradaPoKozmeticaru() {
		return ukupnaZaradaPoKozmeticaru;
	}
	public void setUkupnaZaradaPoKozmeticaru(double[] ukupnaZaradaPoKozmeticaru) {
		this.ukupnaZaradaPoKozmeticaru = ukupnaZaradaPoKozmeticaru;
	}
	
	public int getBrojIzvrsenihUsluga() {
		return brojIzvrsenihUsluga;
	}
	public void setBrojIzvrsenihUsluga(int brojIzvrsenihUsluga) {
		this.brojIzvrsenihUsluga = brojIzvrsenihUsluga;
	}
	public int[] getBrIzvrsenihUslugaPoStanju() {
		return brIzvrsenihUslugaPoStanju;
	}
	public void setBrIzvrsenihUslugaPoStanju(int[] brIzvrsenihUslugaPoStanju) {
		this.brIzvrsenihUslugaPoStanju = brIzvrsenihUslugaPoStanju;
	}
	public double getPrihodiOdUsluge() {
		return prihodiOdUsluge;
	}
	public void setPrihodiOdUsluge(double prihodiOdUsluge) {
		this.prihodiOdUsluge = prihodiOdUsluge;
	}
	
	public ZakazaniTretman getZt() {
		return zt;
	}
	public void setZt(ZakazaniTretman zt) {
		this.zt = zt;
	}
	public double getPotrosioZaZT() {
		return potrosioZaZT;
	}
	public void setPotrosioZaZT(double potrosioZaZT) {
		this.potrosioZaZT = potrosioZaZT;
	}
	public ArrayList<Pair> getZtSaTroskom() {
		return ztSaTroskom;
	}
	public void setZtSaTroskom(ArrayList<Pair> ztSaTroskom) {
		this.ztSaTroskom = ztSaTroskom;
	}
	public double getUkupanTrosak() {
		return ukupanTrosak;
	}
	public void setUkupanTrosak(double ukupanTrosak) {
		this.ukupanTrosak = ukupanTrosak;
	}
	
}
