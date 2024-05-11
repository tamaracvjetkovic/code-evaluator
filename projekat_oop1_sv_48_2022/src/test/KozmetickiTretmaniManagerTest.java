package test;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import manage.KozmetickeUslugeManager;
import manage.KozmetickiTretmaniManager;
import treatments.KozmetickaUsluga;
import treatments.KozmetickiTretman;


public class KozmetickiTretmaniManagerTest {
	public static String pathTretmani = "./data/kozmetickiTretmani_dummy.csv";
	public static String pathUsluge = "./data/kozmetickeUsluge_dummy.csv";
	public static KozmetickeUslugeManager kozmetickeUslugeMng;
	public static KozmetickiTretmaniManager kozmetickiTretmaniMng;
	public static KozmetickiTretmaniManager kozmetickiTretmaniMng2;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File file1 = new File(pathUsluge); file1.toString();
		File file2 = new File(pathTretmani); file2.toString();
		kozmetickeUslugeMng = new KozmetickeUslugeManager(pathUsluge);	
		kozmetickiTretmaniMng = new KozmetickiTretmaniManager(pathTretmani, kozmetickeUslugeMng);
		kozmetickiTretmaniMng2 = new KozmetickiTretmaniManager(pathTretmani, kozmetickeUslugeMng);
		kozmetickiTretmaniMng.add("manikir", false);
		kozmetickiTretmaniMng.add("pedikir", false);
		kozmetickeUslugeMng.add("Relaks masaža", 2000, 45, 1); // id = 1
		kozmetickeUslugeMng.add("Sportska masaža", 2000, 75, 1); // id = 2
		kozmetickeUslugeMng.add("Francuski manikir", 2000, 50, 2); // id = 3
		kozmetickeUslugeMng.add("Gel lak", 1600, 80, 2); // id = 4
		kozmetickeUslugeMng.add("Spa manikir", 2000, 90, 2); // id = 5
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		File file1 = new File(pathUsluge);
		File file2 = new File(pathTretmani);
		file1.delete();
		file2.delete();
	}


	@Test
	public void testAdd() {
		int id = kozmetickiTretmaniMng.getKozmetickiTretmaniLastID() + 1;
		assertTrue(id == 3);
		
		kozmetickiTretmaniMng.add("probaAdd", false);
		assertTrue(kozmetickiTretmaniMng.getKozmetickiTretmani().size() == 3);
		KozmetickiTretman k2 = kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(3);
		assertTrue(k2.getNaziv().equals("probaAdd") == true);
		
		kozmetickiTretmaniMng.getKozmetickiTretmani().remove(k2);
	}
	
	@Test
	public void testPronadji() {
		KozmetickiTretman k1 = kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(1);
		KozmetickiTretman k3 = kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(3);
		assertTrue((k3 == null) == true);
		assertTrue(k1.getNaziv().equals("manikir") == true);
		assertTrue(k1.getNaziv().equals("Manikir") == false);
		assertTrue(k1.getNaziv().equals("pedikir") == false);
	}
	
	@Test
	public void testEdit() {
		kozmetickiTretmaniMng.add("probaAdd", false);
		KozmetickiTretman k3 = kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(3);

		kozmetickiTretmaniMng.edit(k3.getId(), "masaža2"); // probaAdd -> masaža2
		assertTrue(k3.getNaziv().equals("manikir") == false);
		assertTrue(k3.getNaziv().equals("masaža2") == true);
		assertTrue(k3.getNaziv().equals("pedikir") == false);
		assertTrue((k3.getObrisan() == false) == true);
		
		kozmetickiTretmaniMng.getKozmetickiTretmani().remove(k3);
	}	
	
	@Test
	public void testRemove() {
		KozmetickiTretman k2 = kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(2);
		
		kozmetickiTretmaniMng.remove(k2);
		assertTrue((k2.getObrisan() == true) == true);
		for (KozmetickaUsluga ku : kozmetickeUslugeMng.getKozmetickeUsluge()) {
			if (ku.getIdTretmana() == k2.getId()) {
				assertTrue((ku.getObrisan() == true) == true);
			} else {
				assertTrue((ku.getObrisan() == true) == false);
			}
		}
	}
	
	@Test
	public void testFiles() {
		kozmetickiTretmaniMng.add("kosa", false);
		kozmetickiTretmaniMng.add("trepavice", false);
		kozmetickiTretmaniMng.saveData();
		kozmetickiTretmaniMng2.loadData();
		assertTrue(kozmetickiTretmaniMng2.getKozmetickiTretmani().size() == 4);
		assertTrue(kozmetickiTretmaniMng2.pronadjiKozmetickiTretmanPoId(2).getObrisan() == false);
		assertTrue(kozmetickiTretmaniMng2.pronadjiKozmetickiTretmanPoId(3).getObrisan() == false);
		assertTrue(kozmetickiTretmaniMng2.pronadjiKozmetickiTretmanPoId(1).getNaziv().equals("manikir") == true);
		assertTrue(kozmetickiTretmaniMng2.pronadjiKozmetickiTretmanPoId(2).getNaziv().equals("pedikir") == true);
		assertTrue(kozmetickiTretmaniMng2.pronadjiKozmetickiTretmanPoId(3).getNaziv().equals("kosa") == true);
		assertTrue(kozmetickiTretmaniMng2.pronadjiKozmetickiTretmanPoId(4).getNaziv().equals("trepavice") == true);
	}
}
