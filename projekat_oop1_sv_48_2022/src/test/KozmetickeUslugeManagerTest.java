package test;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import manage.KozmetickeUslugeManager;
import manage.KozmetickiTretmaniManager;
import treatments.KozmetickaUsluga;


public class KozmetickeUslugeManagerTest {
	public static String pathUsluge = "./data/kozmetickeUsluge_dummy.csv";
	public static String pathTretmani = "./data/kozmetickiTretmani_dummy.csv";
	public static KozmetickeUslugeManager kozmetickeUslugeMng;
	public static KozmetickeUslugeManager kozmetickeUslugeMng2;
	public static KozmetickiTretmaniManager kozmetickiTretmaniMng;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File file1 = new File(pathUsluge); file1.toString();
		File file2 = new File(pathTretmani); file2.toString();
		kozmetickeUslugeMng = new KozmetickeUslugeManager(pathUsluge);	
		kozmetickeUslugeMng2 = new KozmetickeUslugeManager(pathUsluge);	
		kozmetickiTretmaniMng = new KozmetickiTretmaniManager(pathTretmani, kozmetickeUslugeMng);
		kozmetickiTretmaniMng.add("manikir", false);
		kozmetickiTretmaniMng.add("pedikir", false);
		kozmetickeUslugeMng.add("Relaks masaža", 2000, 45, 1); // id = 1
		kozmetickeUslugeMng.add("Sportska masaža", 2200, 75, 1); // id = 2
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
		int id = kozmetickeUslugeMng.getKozmetickeUslugeLastID() + 1;
		assertTrue(id == 6);
		
		kozmetickeUslugeMng.add("proba manikir", 1350, 45, 1);
		assertTrue(kozmetickeUslugeMng.getKozmetickeUsluge().size() == 6);
		KozmetickaUsluga ku2 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(6);
		assertTrue(ku2.getNaziv().equals("proba manikir") == true);
		assertTrue(ku2.getObrisan() == false);
	
		kozmetickeUslugeMng.getKozmetickeUsluge().remove(ku2);
	}
	
	@Test
	public void testPronadji() {
		KozmetickaUsluga ku1 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(2);
		KozmetickaUsluga ku3 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(6);
		assertTrue((ku3 == null) == true);
		assertTrue(ku1.getNaziv().equals("Sportska masaža") == true);
		assertTrue(ku1.getNaziv().equals("Manikir") == false);
		assertTrue(ku1.getNaziv().equals("pedikir") == false);
	}

	@Test
	public void testEdit() {
		kozmetickeUslugeMng.add("proba manikir", 1350, 45, 1);
		KozmetickaUsluga ku2 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(6);

		kozmetickeUslugeMng.edit(ku2, "edit manikir", 1550, 35, 0, false);
		assertTrue(ku2.getNaziv().equals("proba manikir") == false);
		assertTrue(ku2.getNaziv().equals("edit manikir") == true);
		assertTrue(ku2.getNaziv().equals("manikir") == false);
		assertTrue(ku2.getObrisan() == false);
		assertTrue((ku2.getCena() == 1550) == true);
		assertTrue((ku2.getTrajanje() == 35) == true);

		kozmetickeUslugeMng.getKozmetickeUsluge().remove(ku2);
	}	
	
	@Test
	public void testRemove() {
		KozmetickaUsluga ku2 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(2);
		
		kozmetickeUslugeMng.remove(ku2);
		assertTrue(ku2.getObrisan() == true);
	}
	
	@Test
	public void testFiles() {
		kozmetickeUslugeMng.add("proba1 manikir", 1650, 35, 1);
		kozmetickeUslugeMng.add("proba2 pedikir", 1350, 55, 2);
		kozmetickeUslugeMng.saveData();
		kozmetickeUslugeMng2.loadData();
		assertTrue(kozmetickeUslugeMng2.getKozmetickeUsluge().size() == 7);
		assertTrue(kozmetickeUslugeMng2.pronadjiKozmetickuUsluguPoId(2).getObrisan() == false);
		assertTrue(kozmetickeUslugeMng2.pronadjiKozmetickuUsluguPoId(7).getObrisan() == false);
		assertTrue(kozmetickeUslugeMng2.pronadjiKozmetickuUsluguPoId(4).getNaziv().equals("Gel lak") == true);
		assertTrue(kozmetickeUslugeMng2.pronadjiKozmetickuUsluguPoId(5).getNaziv().equals("Spa manikir") == true);
		assertTrue(kozmetickeUslugeMng2.pronadjiKozmetickuUsluguPoId(6).getNaziv().equals("proba1 manikir") == true);
		assertTrue(kozmetickeUslugeMng2.pronadjiKozmetickuUsluguPoId(7).getNaziv().equals("proba2 pedikir") == true);
		assertTrue((kozmetickeUslugeMng2.pronadjiKozmetickuUsluguPoId(6).getCena() == 1650) == true);
		assertTrue((kozmetickeUslugeMng2.pronadjiKozmetickuUsluguPoId(7).getCena() == 1350) == true);
		assertTrue((kozmetickeUslugeMng2.pronadjiKozmetickuUsluguPoId(6).getTrajanje() == 35) == true);
		assertTrue((kozmetickeUslugeMng2.pronadjiKozmetickuUsluguPoId(7).getTrajanje() == 55) == true);

	}
}
