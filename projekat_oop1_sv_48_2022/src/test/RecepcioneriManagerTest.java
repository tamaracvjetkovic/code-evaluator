package test;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import manage.RecepcioneriManager;
import users.Recepcioner;


public class RecepcioneriManagerTest {
	public static String pathRecepcioneri = "./data/recepcioneri_dummy.csv";
	public static RecepcioneriManager recepcioneriMng;
	public static RecepcioneriManager recepcioneriMng2;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File file1 = new File(pathRecepcioneri); file1.toString();
		recepcioneriMng = new RecepcioneriManager(pathRecepcioneri);
		recepcioneriMng2 = new RecepcioneriManager(pathRecepcioneri);
		recepcioneriMng.add("Milica", "Milić", "ž", "0657777123", "Cara Lazara 68", "milica_milic", "milica123", 2, 20, 0, 90000, false);
		recepcioneriMng.add("Živko", "Živić", "m", "0657777124", "Cara Lazara 68", "zivko123", "zivko123", 3, 10, 200.5, 70000, false);

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		File file1 = new File(pathRecepcioneri);
		file1.delete();
	}

	
	@Test
	public void testAdd() {
		int id = recepcioneriMng.getRecepcioneriLastID() + 1;
		assertTrue(id == 3);
		assertTrue(recepcioneriMng.getRecepcioneri().size() == 2);
		
		recepcioneriMng.add("Proba", "Proba", "m", "0657777125", "Cara Lazara 68", "proba", "proba123", 2, 20, 2000.54, 110000, false);
		Recepcioner r3 = recepcioneriMng.pronadjiRecepcioneraPoId(3);
		Recepcioner r4 = recepcioneriMng.pronadjiRecepcioneraPoId(4);
		assertTrue((r4 == null) == true);
		assertTrue(r3.getIme().equals("Milica") == false);
		assertTrue(r3.getIme().equals("Proba") == true);
		assertTrue((r3.getBonus() == 2000.54) == true);
		assertTrue((r3.getPlata() == 90000) == false);
		
		recepcioneriMng.getRecepcioneri().remove(r3);
	}
	
	@Test
	public void testPronadji() {
		Recepcioner r1 = recepcioneriMng.pronadjiRecepcioneraPoId(1);
		Recepcioner r3 = recepcioneriMng.pronadjiRecepcioneraPoId(3);
		assertTrue((r3 == null) == true);
		assertTrue(r1.getIme().equals("Milica") == true);
		assertTrue(r1.getIme().equals("Živko") == false);
	}
	
	@Test
	public void testEdit() {	
		recepcioneriMng.add("Proba", "Proba", "m", "0657777125", "Cara Lazara 68", "proba", "proba123", 2, 20, 2000.54, 110000, false);
		Recepcioner r3 = recepcioneriMng.pronadjiRecepcioneraPoId(3);

		recepcioneriMng.edit(r3, "Mirko", r3.getPrezime(), r3.getPol(), r3.getTelefon(), r3.getAdresa(), r3.getKorisnickoIme(), r3.getLozinka(), r3.getNivoStrucneSpreme(), r3.getStaz(), 3000, 90000, false);
		assertTrue(r3.getIme().equals("Proba") == false);
		assertTrue(r3.getIme().equals("Mirko") == true);
		assertTrue((r3.getBonus() == 2000.54) == false);
		assertTrue((r3.getBonus() == 3000) == true);
		assertTrue((r3.getPlata() == 90000) == true);
		
		recepcioneriMng.getRecepcioneri().remove(r3);
	}	
	
	@Test
	public void testRemove() {
		Recepcioner r2 = recepcioneriMng.pronadjiRecepcioneraPoId(2);
		recepcioneriMng.remove(r2);
		assertTrue((r2.getObrisan() == true) == true);
	}
	
	@Test
	public void testFiles() {
		recepcioneriMng.add("Veselin", "Roganović", "m", "0651234123", "Cara Lazara 68", "vesa_vesa", "vesa123", 4, 25, 5000.5343, 120000, false);
		recepcioneriMng.add("Tamara", "Cvjetković", "ž", "0651231234", "Cara Lazara 68", "taca_taca", "taca123", 2, 20, 2000.54, 110000, false);
		Recepcioner r1 = recepcioneriMng.pronadjiRecepcioneraPoId(1);
		recepcioneriMng.remove(r1);
		recepcioneriMng.saveData();
		recepcioneriMng2.loadData();
		assertTrue(recepcioneriMng2.getRecepcioneri().size() == 4);
		assertTrue(recepcioneriMng2.pronadjiRecepcioneraPoId(1).getObrisan() == true);
		assertTrue(recepcioneriMng2.pronadjiRecepcioneraPoId(2).getObrisan() == false);
		assertTrue(recepcioneriMng2.pronadjiRecepcioneraPoId(1).getIme().equals("Milica") == true);
		assertTrue(recepcioneriMng2.pronadjiRecepcioneraPoId(2).getIme().equals("Živko") == true);
		assertTrue(recepcioneriMng2.pronadjiRecepcioneraPoId(3).getIme().equals("Veselin") == true);
		assertTrue(recepcioneriMng2.pronadjiRecepcioneraPoId(4).getIme().equals("Tamara") == true);
		
		Recepcioner r3 = recepcioneriMng.pronadjiRecepcioneraPoId(3);
		Recepcioner r4 = recepcioneriMng.pronadjiRecepcioneraPoId(4);
		recepcioneriMng.getRecepcioneri().remove(r3);
		recepcioneriMng.getRecepcioneri().remove(r4);
	}		
}
