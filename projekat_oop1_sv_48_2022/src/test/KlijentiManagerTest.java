package test;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import manage.KlijentiManager;
import manage.KozmetickeUslugeManager;
import manage.ZakazaniTretmaniManager;
import users.Klijent;


public class KlijentiManagerTest {
	public static String pathKlijenti = "./data/klijenti_dummy.csv";
	public static String pathUsluge = "./data/kozmetickeUsluge_dummy.csv";
	public static String pathZakazani = "./data/zakazaniTretmani_dummy.csv";
	public static KozmetickeUslugeManager kozmetickeUslugeMng;
	public static ZakazaniTretmaniManager zakazaniTretmaniMng;
	public static KlijentiManager klijentiMng;
	public static KlijentiManager klijentiMng2;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File file1 = new File(pathKlijenti); file1.toString();	 
		kozmetickeUslugeMng = new KozmetickeUslugeManager(pathUsluge);
		zakazaniTretmaniMng = new ZakazaniTretmaniManager(pathZakazani, kozmetickeUslugeMng);
		klijentiMng = new KlijentiManager(pathKlijenti, zakazaniTretmaniMng);
		klijentiMng2 = new KlijentiManager(pathKlijenti, zakazaniTretmaniMng);
		klijentiMng.add("Milica", "Milić", "ž", "0657777123", "Cara Lazara 68", "milica_milic", "milica123", false);
		klijentiMng.add("Živko", "Živić", "m", "0657777124", "Cara Lazara 68", "zivko123", "zivko123", false);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		File file1 = new File(pathKlijenti);
		file1.delete();
	}

	@Test
	public void testAdd() {
		int id = klijentiMng.getKlijentiLastID() + 1;
		assertTrue(id == 3);
		assertTrue(klijentiMng.getKlijenti().size() == 2);
		
		klijentiMng.add("Proba", "Proba", "m", "0657777125", "Cara Lazara 68", "proba", "proba123", false);
		Klijent k3 = klijentiMng.pronadjiKlijentaPoId(3);
		Klijent k4 = klijentiMng.pronadjiKlijentaPoId(4);
		assertTrue((k4 == null) == true);
		assertTrue(k3.getIme().equals("Proba") == true);
		assertTrue(k3.getIme().equals("Živko") == false);
		
		klijentiMng.getKlijenti().remove(k3);
	}
	
	@Test
	public void testPronadji() {
		Klijent k1 = klijentiMng.pronadjiKlijentaPoId(1);
		Klijent k3 = klijentiMng.pronadjiKlijentaPoId(3);
		assertTrue((k3 == null) == true);
		assertTrue(k1.getIme().equals("Milica") == true);
		assertTrue(k1.getIme().equals("Živko") == false);
	}
	
	@Test
	public void testEdit() {	
		klijentiMng.add("Proba", "Proba", "m", "0657777125", "Cara Lazara 68", "proba", "proba123", false);
		Klijent k3 = klijentiMng.pronadjiKlijentaPoId(3);

		klijentiMng.edit(k3, "Mirko", k3.getPrezime(), k3.getPol(), k3.getTelefon(), k3.getAdresa(), k3.getKorisnickoIme(), k3.getLozinka(), false);
		assertTrue(k3.getIme().equals("Proba") == false);
		assertTrue(k3.getIme().equals("Mirko") == true);
		
		klijentiMng.getKlijenti().remove(k3);
	}	
	
	@Test
	public void testRemove() {
		Klijent k2 = klijentiMng.pronadjiKlijentaPoId(2);
		klijentiMng.remove(k2);
		assertTrue((k2.getObrisan() == true) == true);
	}
	
	@Test
	public void testFiles() {
		klijentiMng.add("Veselin", "Roganović", "m", "0651234123", "Cara Lazara 68", "vesa_vesa", "vesa123", false);
		klijentiMng.add("Tamara", "Cvjetković", "ž", "0651231234", "Cara Lazara 68", "taca_taca", "taca123", false);
		Klijent k1 = klijentiMng.pronadjiKlijentaPoId(1);
		klijentiMng.remove(k1);
		klijentiMng.saveData();
		klijentiMng2.loadData();
		assertTrue(klijentiMng2.getKlijenti().size() == 4);
		assertTrue(klijentiMng2.pronadjiKlijentaPoId(1).getObrisan() == true);
		assertTrue(klijentiMng2.pronadjiKlijentaPoId(2).getObrisan() == false);
		assertTrue(klijentiMng2.pronadjiKlijentaPoId(1).getIme().equals("Milica") == true);
		assertTrue(klijentiMng2.pronadjiKlijentaPoId(2).getIme().equals("Živko") == true);
		assertTrue(klijentiMng2.pronadjiKlijentaPoId(3).getIme().equals("Veselin") == true);
		assertTrue(klijentiMng2.pronadjiKlijentaPoId(4).getIme().equals("Tamara") == true);
		
		Klijent k3 = klijentiMng.pronadjiKlijentaPoId(3);
		Klijent k4 = klijentiMng.pronadjiKlijentaPoId(4);
		klijentiMng.getKlijenti().remove(k3);
		klijentiMng.getKlijenti().remove(k4);
	}	
}
