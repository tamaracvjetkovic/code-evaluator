package test;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import manage.MenadzeriManager;
import users.Menadzer;


public class MenadzeriManagerTest {
	public static String pathMenadzeri = "./data/menadzeri_dummy.csv";
	public static MenadzeriManager menadzeriMng;
	public static MenadzeriManager menadzeriMng2;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File file1 = new File(pathMenadzeri); file1.toString();
		menadzeriMng = new MenadzeriManager(pathMenadzeri);
		menadzeriMng2 = new MenadzeriManager(pathMenadzeri);
		menadzeriMng.add("Milica", "Milić", "ž", "0657777123", "Cara Lazara 68", "milica_milic", "milica123", false);
		menadzeriMng.add("Živko", "Živić", "m", "0657777124", "Cara Lazara 68", "zivko123", "zivko123", false);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		File file1 = new File(pathMenadzeri);
		file1.delete();
	}

	
	@Test
	public void testAddMenadzer() {
		int id = menadzeriMng.getMenadzeriLastID() + 1;
		assertTrue(id == 3);
		assertTrue(menadzeriMng.getMenadzeri().size() == 2);
		
		menadzeriMng.add("Proba", "Proba", "m", "0657777125", "Cara Lazara 68", "proba", "proba123", false);
		Menadzer k3 = menadzeriMng.pronadjiMenadzeraPoId(3);
		Menadzer k4 = menadzeriMng.pronadjiMenadzeraPoId(4);
		assertTrue((k4 == null) == true);
		assertTrue(k3.getIme().equals("Proba") == true);
		assertTrue(k3.getIme().equals("Živko") == false);
		
		menadzeriMng.getMenadzeri().remove(k3);
	}
	
	@Test
	public void testPronadji() {
		Menadzer k1 = menadzeriMng.pronadjiMenadzeraPoId(1);
		Menadzer k3 = menadzeriMng.pronadjiMenadzeraPoId(3);
		assertTrue((k3 == null) == true);
		assertTrue(k1.getIme().equals("Milica") == true);
		assertTrue(k1.getIme().equals("Živko") == false);
	}
	
	@Test
	public void testEdit() {	
		menadzeriMng.add("Proba", "Proba", "m", "0657777125", "Cara Lazara 68", "proba", "proba123", false);
		Menadzer k3 = menadzeriMng.pronadjiMenadzeraPoId(3);

		menadzeriMng.edit(k3, "Mirko", k3.getPrezime(), k3.getPol(), k3.getTelefon(), k3.getAdresa(), k3.getKorisnickoIme(), k3.getLozinka(), false);
		assertTrue(k3.getIme().equals("Proba") == false);
		assertTrue(k3.getIme().equals("Mirko") == true);
		
		menadzeriMng.getMenadzeri().remove(k3);
	}	
	
	@Test
	public void testRemove() {
		Menadzer k2 = menadzeriMng.pronadjiMenadzeraPoId(2);
		menadzeriMng.remove(k2);
		assertTrue((k2.getObrisan() == true) == true);
	}
	
	@Test
	public void testFiles() {
		menadzeriMng.add("Veselin", "Roganović", "m", "0651234123", "Cara Lazara 68", "vesa_vesa", "vesa123", false);
		menadzeriMng.add("Tamara", "Cvjetković", "ž", "0651231234", "Cara Lazara 68", "taca_taca", "taca123", false);
		Menadzer k1 = menadzeriMng.pronadjiMenadzeraPoId(1);
		menadzeriMng.remove(k1);
		menadzeriMng.saveData();
		menadzeriMng2.loadData();
		assertTrue(menadzeriMng2.getMenadzeri().size() == 4);
		assertTrue(menadzeriMng2.pronadjiMenadzeraPoId(1).getObrisan() == true);
		assertTrue(menadzeriMng2.pronadjiMenadzeraPoId(2).getObrisan() == false);
		assertTrue(menadzeriMng2.pronadjiMenadzeraPoId(1).getIme().equals("Milica") == true);
		assertTrue(menadzeriMng2.pronadjiMenadzeraPoId(2).getIme().equals("Živko") == true);
		assertTrue(menadzeriMng2.pronadjiMenadzeraPoId(3).getIme().equals("Veselin") == true);
		assertTrue(menadzeriMng2.pronadjiMenadzeraPoId(4).getIme().equals("Tamara") == true);
		
		Menadzer k3 = menadzeriMng.pronadjiMenadzeraPoId(3);
		Menadzer k4 = menadzeriMng.pronadjiMenadzeraPoId(4);
		menadzeriMng.getMenadzeri().remove(k3);
		menadzeriMng.getMenadzeri().remove(k4);
	}	

}
