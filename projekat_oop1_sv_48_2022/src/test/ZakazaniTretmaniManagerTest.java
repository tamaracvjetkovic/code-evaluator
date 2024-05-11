package test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.time.LocalDateTime;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import manage.KozmetickeUslugeManager;
import manage.ZakazaniTretmaniManager;
import treatments.KozmetickaUsluga;
import treatments.ZakazaniTretman;


public class ZakazaniTretmaniManagerTest {
	public static String pathUsluge = "./data/kozmetickeUsluge_dummy.csv";
	public static String pathZakazani = "./data/zakazaniTretmani_dummy.csv";
	public static KozmetickeUslugeManager kozmetickeUslugeMng;
	public static ZakazaniTretmaniManager zakazaniTretmaniMng;
	public static ZakazaniTretmaniManager zakazaniTretmaniMng2;
	public static LocalDateTime datum1 = LocalDateTime.of(2023, 6, 7, 13, 0);
	public static LocalDateTime datum2 = datum1.plusDays(2).plusMonths(2).plusHours(3);
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File file1 = new File(pathUsluge); file1.toString();
		File file2 = new File(pathZakazani); file2.toString();
		kozmetickeUslugeMng = new KozmetickeUslugeManager(pathUsluge);	
		zakazaniTretmaniMng = new ZakazaniTretmaniManager(pathZakazani, kozmetickeUslugeMng);
		zakazaniTretmaniMng2 = new ZakazaniTretmaniManager(pathZakazani, kozmetickeUslugeMng);
		kozmetickeUslugeMng.add("Relaks masaža", 2000, 45, 1); // id = 1
		kozmetickeUslugeMng.add("Sportska masaža", 2000, 75, 1); // id = 2
		kozmetickeUslugeMng.add("Francuski manikir", 2000, 50, 2); // id = 3
		kozmetickeUslugeMng.add("Gel lak", 1600, 80, 2); // id = 4
		kozmetickeUslugeMng.add("Spa manikir", 2000, 90, 2); // id = 5
		KozmetickaUsluga ku1 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(1);
		KozmetickaUsluga ku2 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(2);
		
		zakazaniTretmaniMng.add(ku1, datum1, ku1.getTrajanje(), ku1.getCena(), "ZAKAZAN", 1, 5);
		zakazaniTretmaniMng.add(ku2, datum2, ku2.getTrajanje(), ku2.getCena(), "IZVRŠEN", 2, 3);	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		File file1 = new File(pathUsluge);
		File file2 = new File(pathZakazani);
		file1.delete();
		file2.delete();
	}


	@Test
	public void testAdd() {
		int id = zakazaniTretmaniMng.getZakazaniTretmaniLastID() + 1;
		assertTrue(id == 3);
		
		KozmetickaUsluga ku1 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(1);
		zakazaniTretmaniMng.add(ku1, datum1, ku1.getTrajanje(), ku1.getCena(), "ZAKAZAN", 1, 5);
		ZakazaniTretman zt2 = zakazaniTretmaniMng.pronadjiZakazaniTretmanPoId(3);

		assertTrue(zakazaniTretmaniMng.getZakazaniTretmani().size() == 3);
		assertTrue(zt2.getZakazanaKozmetickaUsluga().equals(ku1) == true);
		assertTrue(zt2.getStanje().equals("ZAKAZAN") == true);
		assertTrue(zt2.getCena() == ku1.getCena() == true);
		assertTrue(zt2.getIdKlijenta() == 1 == false);

		zakazaniTretmaniMng.getZakazaniTretmani().remove(zt2);
	}
	
	@Test
	public void testPronadji() {
		ZakazaniTretman zt1 = zakazaniTretmaniMng.pronadjiZakazaniTretmanPoId(1);
		ZakazaniTretman zt3 = zakazaniTretmaniMng.pronadjiZakazaniTretmanPoId(3);
		assertTrue((zt3 == null) == true);
		
		KozmetickaUsluga ku1 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(1);
		assertTrue(zt1.getZakazanaKozmetickaUsluga().equals(ku1) == true);
		assertTrue(zt1.getStanje().equals("ZAKAZAN") == true);
		assertTrue(zt1.getDatumVreme().equals(datum1) == true);
		assertTrue(zt1.getCena() == ku1.getCena() == true);
		assertTrue(zt1.getIdKlijenta() == 1 == false);
	}

	@Test
	public void testEdit() {
		KozmetickaUsluga ku1 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(1);
		KozmetickaUsluga ku2 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(2);

		zakazaniTretmaniMng.add(ku1, datum1, ku1.getTrajanje(), ku1.getCena(), "ZAKAZAN", 1, 5);
		ZakazaniTretman zt2 = zakazaniTretmaniMng.pronadjiZakazaniTretmanPoId(3);

		zakazaniTretmaniMng.edit(zt2, ku2, datum2, ku2.getTrajanje(), ku2.getCena(), "OTKAZAO KLIJENT", 2, 5);
		assertTrue(zt2.getZakazanaKozmetickaUsluga().equals(ku2) == true);
		assertTrue(zt2.getStanje().equals("OTKAZAO KLIJENT") == true);
		assertTrue(zt2.getDatumVreme().equals(datum2) == true);
		assertTrue((zt2.getCena() == ku2.getCena()) == true);
		assertTrue((zt2.getIdKlijenta() == 1) == false);

		zakazaniTretmaniMng.getZakazaniTretmani().remove(zt2);
	}	
	
	@Test
	public void testRemove() {
		KozmetickaUsluga ku1 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(1);
		zakazaniTretmaniMng.add(ku1, datum1, ku1.getTrajanje(), ku1.getCena(), "ZAKAZAN", 1, 5);
		ZakazaniTretman zt2 = zakazaniTretmaniMng.pronadjiZakazaniTretmanPoId(3);
		zakazaniTretmaniMng.remove(zt2);
		
		assertTrue(zakazaniTretmaniMng.pronadjiZakazaniTretmanPoId(3) == null);
	}
	
	@Test
	public void testFiles() {
		KozmetickaUsluga ku1 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(1);
		KozmetickaUsluga ku2 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(2);
		zakazaniTretmaniMng.add(ku1, datum2, ku1.getTrajanje(), ku1.getCena(), "ZAKAZAN", 1, 5);
		zakazaniTretmaniMng.add(ku2, datum2, ku2.getTrajanje(), ku2.getCena(), "OTKAZAO SALON", 3, 3);
		zakazaniTretmaniMng.saveData();
		zakazaniTretmaniMng2.loadData();
		assertTrue(zakazaniTretmaniMng2.getZakazaniTretmani().size() == 4);
		assertTrue(zakazaniTretmaniMng2.pronadjiZakazaniTretmanPoId(1).getZakazanaKozmetickaUsluga().equals(ku1) == true);
		assertTrue(zakazaniTretmaniMng2.pronadjiZakazaniTretmanPoId(4).getZakazanaKozmetickaUsluga().equals(ku2) == true);
		assertTrue((zakazaniTretmaniMng2.pronadjiZakazaniTretmanPoId(2).getCena() == ku2.getCena()) == true);
		assertTrue((zakazaniTretmaniMng2.pronadjiZakazaniTretmanPoId(3).getCena() == ku1.getCena()) == true);
		assertTrue((zakazaniTretmaniMng2.pronadjiZakazaniTretmanPoId(3).getTrajanje() == ku1.getTrajanje()) == true);
		assertTrue((zakazaniTretmaniMng2.pronadjiZakazaniTretmanPoId(4).getTrajanje() == ku2.getTrajanje()) == true);
		assertTrue((zakazaniTretmaniMng2.pronadjiZakazaniTretmanPoId(1).getStanje().equals("ZAKAZAN")) == true);
		assertTrue((zakazaniTretmaniMng2.pronadjiZakazaniTretmanPoId(2).getStanje().equals("IZVRŠEN")) == true);
		assertTrue((zakazaniTretmaniMng2.pronadjiZakazaniTretmanPoId(3).getStanje().equals("ZAKAZAN")) == true);
		assertTrue((zakazaniTretmaniMng2.pronadjiZakazaniTretmanPoId(4).getStanje().equals("OTKAZAO SALON")) == true);
	}
}
