package test;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import manage.KozmeticariManager;
import manage.KozmetickeUslugeManager;
import manage.KozmetickiTretmaniManager;
import manage.ZakazaniTretmaniManager;
import users.Kozmeticar;


public class KozmeticariManagerTest {
	public static String pathKozmeticari = "./data/kozmeticari_dummy.csv";
	public static String pathTretmani = "./data/kozmetickiTretmani_dummy.csv";
	public static String pathZakazani = "./data/zakazaniTretmani_dummy.csv";
	public static String pathUsluge = "./data/kozmetickeUsluge_dummy.csv";
	public static KozmetickiTretmaniManager kozmetickiTretmaniMng;
	public static ZakazaniTretmaniManager zakazaniTretmaniMng;
	public static KozmetickeUslugeManager kozmetickeUslugeMng;
	public static KozmeticariManager kozmeticariMng;
	public static KozmeticariManager kozmeticariMng2;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File file1 = new File(pathKozmeticari); file1.toString();
		kozmetickeUslugeMng = new KozmetickeUslugeManager(pathUsluge);
		zakazaniTretmaniMng = new ZakazaniTretmaniManager(pathZakazani, kozmetickeUslugeMng);
		kozmetickiTretmaniMng = new KozmetickiTretmaniManager(pathTretmani, kozmetickeUslugeMng);
		kozmeticariMng = new KozmeticariManager(pathKozmeticari, kozmetickiTretmaniMng, zakazaniTretmaniMng);
		kozmeticariMng2 = new KozmeticariManager(pathKozmeticari, kozmetickiTretmaniMng, zakazaniTretmaniMng);
		kozmeticariMng.add("Milica", "Milić", "ž", "0657777123", "Cara Lazara 68", "milica_milic", "milica123", 2, 20, 0, 90000, false);
		kozmeticariMng.add("Živko", "Živić", "m", "0657777124", "Cara Lazara 68", "zivko123", "zivko123", 1, 15, 0, 90000, false);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		File file1 = new File(pathKozmeticari);
		file1.delete();
	}

	
	@Test
	public void testAdd() {
		int id = kozmeticariMng.getKozmeticariLastID() + 1;
		assertTrue(id == 3);
		assertTrue(kozmeticariMng.getKozmeticari().size() == 2);
		
		kozmeticariMng.add("Proba", "Proba", "m", "0651234125", "Cara Lazara 68", "proba", "Proba123", 4, 25, 5000.5343, 120000, false);
		Kozmeticar k3 = kozmeticariMng.pronadjiKozmeticaraPoId(3);
		Kozmeticar k4 = kozmeticariMng.pronadjiKozmeticaraPoId(4);
		assertTrue((k4 == null) == true);
		assertTrue(k3.getIme().equals("Proba") == true);
		assertTrue(k3.getIme().equals("Živko") == false);
		
		kozmeticariMng.getKozmeticari().remove(k3);
	}
	
	@Test
	public void testPronadji() {
		Kozmeticar k1 = kozmeticariMng.pronadjiKozmeticaraPoId(1);
		Kozmeticar k3 = kozmeticariMng.pronadjiKozmeticaraPoId(3);
		assertTrue((k3 == null) == true);
		assertTrue(k1.getIme().equals("Milica") == true);
		assertTrue(k1.getIme().equals("Živko") == false);
	}
	
	@Test
	public void testEdit() {	
		kozmeticariMng.add("Proba", "Proba", "m", "0651234125", "Cara Lazara 68", "proba", "Proba123", 4, 25, 5000.5343, 120000, false);
		Kozmeticar k3 = kozmeticariMng.pronadjiKozmeticaraPoId(3);

		kozmeticariMng.edit(k3.getId(), "Mirko", k3.getPrezime(), k3.getPol(), k3.getTelefon(), k3.getAdresa(), k3.getKorisnickoIme(), k3.getLozinka(), 2, 20, 2000.54, 85000);
		assertTrue(k3.getIme().equals("Proba") == false);
		assertTrue(k3.getIme().equals("Mirko") == true);
		
		kozmeticariMng.getKozmeticari().remove(k3);
	}	
	
	@Test
	public void testRemove() {
		Kozmeticar k2 = kozmeticariMng.pronadjiKozmeticaraPoId(2);
		kozmeticariMng.remove(k2);
		assertTrue((k2.getObrisan() == true) == true);
	}
	
	@Test
	public void testFiles() {
		kozmeticariMng.add("Veselin", "Roganović", "m", "0651234123", "Cara Lazara 68", "vesa_vesa", "vesa123", 4, 25, 5000.5343, 120000, false);
		kozmeticariMng.add("Tamara", "Cvjetković", "ž", "0651231234", "Cara Lazara 68", "taca_taca", "taca123", 2, 20, 2000.54, 110000, false);
		Kozmeticar k1 = kozmeticariMng.pronadjiKozmeticaraPoId(1);
		kozmeticariMng.remove(k1);
		kozmeticariMng.saveData();
		kozmeticariMng2.loadData();
		assertTrue(kozmeticariMng2.getKozmeticari().size() == 4);
		assertTrue(kozmeticariMng2.pronadjiKozmeticaraPoId(1).getObrisan() == true);
		assertTrue(kozmeticariMng2.pronadjiKozmeticaraPoId(2).getObrisan() == false);
		assertTrue(kozmeticariMng2.pronadjiKozmeticaraPoId(1).getIme().equals("Milica") == true);
		assertTrue(kozmeticariMng2.pronadjiKozmeticaraPoId(2).getIme().equals("Živko") == true);
		assertTrue(kozmeticariMng2.pronadjiKozmeticaraPoId(3).getIme().equals("Veselin") == true);
		assertTrue(kozmeticariMng2.pronadjiKozmeticaraPoId(4).getIme().equals("Tamara") == true);
		
		Kozmeticar k3 = kozmeticariMng.pronadjiKozmeticaraPoId(3);
		Kozmeticar k4 = kozmeticariMng.pronadjiKozmeticaraPoId(4);
		kozmeticariMng.getKozmeticari().remove(k3);
		kozmeticariMng.getKozmeticari().remove(k4);
	}	
	
	
}
