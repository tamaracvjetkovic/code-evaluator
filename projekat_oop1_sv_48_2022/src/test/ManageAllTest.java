package test;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDateTime;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import manage.KlijentiManager;
import manage.KozmeticariManager;
import manage.KozmetickeUslugeManager;
import manage.KozmetickiTretmaniManager;
import manage.ManageAll;
import manage.MenadzeriManager;
import manage.RecepcioneriManager;
import manage.ZakazaniTretmaniManager;
import treatments.KozmetickaUsluga;
import treatments.KozmetickiTretman;
import treatments.ZakazaniTretman;
import users.Klijent;
import users.Kozmeticar;
import utils.AppSettings;


public class ManageAllTest {
	public static AppSettings appSettings;
	public static ManageAll manageAll;
	public static MenadzeriManager menadzeriMng;
	public static KozmeticariManager kozmeticariMng;
	public static RecepcioneriManager recepcioneriMng;
	public static KlijentiManager klijentiMng;
	public static KozmetickeUslugeManager kozmetickeUslugeMng;
	public static KozmetickiTretmaniManager kozmetickiTretmaniMng;
	public static ZakazaniTretmaniManager zakazaniTretmaniMng;
	
	public static MenadzeriManager menadzeriMng2;
	public static KozmeticariManager kozmeticariMng2;
	public static RecepcioneriManager recepcioneriMng2;
	public static KlijentiManager klijentiMng2;
	public static KozmetickeUslugeManager kozmetickeUslugeMng2;
	public static KozmetickiTretmaniManager kozmetickiTretmaniMng2;
	public static ZakazaniTretmaniManager zakazaniTretmaniMng2;
	
	public static String pathUsluge = "./data/kozmetickeUsluge_dummy.csv";
	public static String pathTretmani = "./data/kozmetickiTretmani_dummy.csv";
	public static String pathKlijenti = "./data/klijenti_dummy.csv";
	public static String pathZakazani = "./data/zakazaniTretmani_dummy.csv";
	public static String pathRecepcioneri = "./data/recepcioneri_dummy.csv";
	public static String pathMenadzeri = "./data/menadzeri_dummy.csv";
	public static String pathKozmeticari = "./data/kozmeticari_dummy.csv";
	public static LocalDateTime datum1 = LocalDateTime.of(2023, 6, 7, 13, 0);
	public static LocalDateTime datum2 = datum1.plusDays(1).plusMonths(1).plusHours(1);
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		appSettings = new AppSettings(pathMenadzeri, pathKozmeticari, pathRecepcioneri, pathKlijenti, pathUsluge, pathTretmani, pathZakazani);
		manageAll = new ManageAll(appSettings);
		
		kozmetickeUslugeMng = manageAll.getKozmetickeUslugeMng();
		recepcioneriMng = manageAll.getRecepcionariMng();
		zakazaniTretmaniMng = manageAll.getZakazaniTretmaniMng();
		klijentiMng = manageAll.getKlijentiMng();
		kozmetickiTretmaniMng = manageAll.getKozmetickiTretmaniMng();
		kozmeticariMng = manageAll.getKozmeticariMng();
		menadzeriMng = manageAll.getMenadzeriMng();
		
		kozmetickeUslugeMng2 = manageAll.getKozmetickeUslugeMng();
		recepcioneriMng2 = manageAll.getRecepcionariMng();
		zakazaniTretmaniMng2 = manageAll.getZakazaniTretmaniMng();
		klijentiMng2 = manageAll.getKlijentiMng();
		kozmetickiTretmaniMng2 = manageAll.getKozmetickiTretmaniMng();
		kozmeticariMng2 = manageAll.getKozmeticariMng();
		menadzeriMng2 = manageAll.getMenadzeriMng();
		
		menadzeriMng.add("Nikola", "Nikolić", "m", "0651234123", "Bulevar 1", "nikola_nikolic", "menadzer123", false);
		menadzeriMng.add("Tamara", "Cvjetkovic", "z", "0651234132", "Bulevar 1", "tr", "t", false);
		recepcioneriMng.add("Pera", "Perić", "m", "0661234123", "Bulevar 2", "pera_peric", "recepcioner123", 1, 10, 0, 70000, false);
		kozmeticariMng.add("Sima", "Simić", "m", "0651111999", "Bulevar 31", "sima_simic", "kozmeticar123", 2, 6, 0, 85000, false);
		kozmeticariMng.add("Žika", "Žikić", "m", "0652222999", "Bulevar 32", "zika_zikic", "kozmeticar123", 3, 10, 0, 98000, false);
		kozmeticariMng.add("Jadranka", "Jovanović", "ž", "0653333999", "Bulevar 33", "jadranka_jovanovic", "kozmeticar123", 2, 4, 0, 85000, false);
		kozmeticariMng.add("Jadranka2", "Jovanović", "ž", "0653333991", "Bulevar 34", "jadranka_jovanovic1", "kozmeticar123", 2, 4, 0, 85000, false);
		kozmeticariMng.add("Jadranka3", "Jovanović", "ž", "0653333992", "Bulevar 35", "jadranka_jovanovic2", "kozmeticar123", 2, 4, 0, 85000, false);

		klijentiMng.add("Milica", "Milić", "ž", "0657777123", "Cara Lazara 68", "milica_milic", "milica123", false);
		klijentiMng.add("Mika", "Mikić", "m", "0658888123", "Cara Lazara 12", "mika_mikic", "mika123", false);
		klijentiMng.add("T", "T", "z", "0658888124", "Cara Lazara 12", "t", "t", false);
		
		kozmetickiTretmaniMng.add("masaža", false); // id = 1
		kozmetickiTretmaniMng.add("manikir", false); // id = 
		kozmetickiTretmaniMng.add("pedikir", false); // id = 32
		kozmetickeUslugeMng.add("Relaks masaža", 2000, 45, 1); // id = 1
		kozmetickeUslugeMng.add("Sportska masaža", 3400, 75, 1); // id = 2
		kozmetickeUslugeMng.add("Francuski manikir", 2800, 50, 2); // id = 3
		kozmetickeUslugeMng.add("Gel lak", 2100, 80, 2); // id = 4
		kozmetickeUslugeMng.add("Spa manikir", 2300, 90, 2); // id = 5
		kozmetickeUslugeMng.add("Spa pedikir", 1400, 45, 3); // id = 6
	
		kozmeticariMng.dodajTretmanKozmeticaru(1, kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(1));
		kozmeticariMng.dodajTretmanKozmeticaru(1, kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(2));
		kozmeticariMng.dodajTretmanKozmeticaru(2, kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(1));
		kozmeticariMng.dodajTretmanKozmeticaru(2, kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(2));
		kozmeticariMng.dodajTretmanKozmeticaru(2, kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(3));
		
		File file1 = new File(pathUsluge); file1.toString();
		File file2 = new File(pathTretmani); file2.toString();
		File file3 = new File(pathKlijenti); file3.toString();
		File file4 = new File(pathZakazani); file4.toString();
		File file5 = new File(pathRecepcioneri); file5.toString();
		File file6 = new File(pathMenadzeri); file6.toString();
		File file7 = new File(pathKozmeticari); file7.toString();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		File file1 = new File(pathUsluge); 
		File file2 = new File(pathTretmani); 
		File file3 = new File(pathKlijenti); 
		File file4 = new File(pathZakazani);
		File file5 = new File(pathRecepcioneri); 
		File file6 = new File(pathMenadzeri); 
		File file7 = new File(pathKozmeticari); 
		file1.delete();
		file2.delete();
		file3.delete();
		file4.delete();
		file5.delete();
		file6.delete();
		file7.delete();
	}


	@Test
	public void zakaziTretmanTest() {
		manageAll.setUkupanPrihod(0);
		
		Klijent k = klijentiMng.pronadjiKlijentaPoId(1);
		Kozmeticar kozm = kozmeticariMng.pronadjiKozmeticaraPoId(1);
		KozmetickaUsluga ku1 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(1);
	
		ZakazaniTretman zt = manageAll.zakaziTretman(k, ku1, kozm, datum1);
		
		assertTrue(zt != null);
		assertTrue(manageAll.getUkupanPrihod() == ku1.getCena());
		assertTrue(zt.getCena() == ku1.getCena());
		assertTrue(zt.getDatumVreme().equals(datum1));
		assertTrue(k.getNerealizovaniTretmani().size() == 1);
		assertTrue(kozm.getRaspored().size() == 1);
		assertTrue(zakazaniTretmaniMng.pronadjiZakazaniTretmanPoId(1).equals(zt));

		k.getNerealizovaniTretmani().remove(zt);
		kozm.getRaspored().remove(zt);
		zakazaniTretmaniMng.getZakazaniTretmani().remove(zt);
	}
	
	
	@Test
	public void zakaziTretmanPreklapanjeTerminaTest() {
		manageAll.setUkupanPrihod(0);
		
		Klijent k = klijentiMng.pronadjiKlijentaPoId(1);
		Klijent k2 = klijentiMng.pronadjiKlijentaPoId(2);
	
		Kozmeticar kozm = kozmeticariMng.pronadjiKozmeticaraPoId(1);
		KozmetickaUsluga ku1 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(1);
	
		ZakazaniTretman zt = manageAll.zakaziTretman(k, ku1, kozm, datum1);
		ZakazaniTretman zt2 = manageAll.zakaziTretman(k2, ku1, kozm, datum1);
		assertTrue(zt != null);
		assertTrue(zt2 == null);
		assertTrue(manageAll.getUkupanPrihod() == ku1.getCena());
		assertTrue(k2.getNerealizovaniTretmani().size() == 0);
		assertTrue(kozm.getRaspored().size() == 1);

		k.getNerealizovaniTretmani().remove(zt);
		kozm.getRaspored().remove(zt);
		zakazaniTretmaniMng.getZakazaniTretmani().remove(zt);	
	}
	
	@Test
	public void zakaziTretmanPreklapanjeTerminaTrajanjeTest() {
		manageAll.setUkupanPrihod(0);

		Klijent k = klijentiMng.pronadjiKlijentaPoId(1);
		Klijent k2 = klijentiMng.pronadjiKlijentaPoId(2);
		
		LocalDateTime datum3 = datum1.plusHours(1);
		
		Kozmeticar kozm = kozmeticariMng.pronadjiKozmeticaraPoId(1);
		// ku1 traje 75min
		KozmetickaUsluga ku1 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(2);
	
		ZakazaniTretman zt = manageAll.zakaziTretman(k, ku1, kozm, datum1);
		ZakazaniTretman zt2 = manageAll.zakaziTretman(k2, ku1, kozm, datum3);
		assertTrue(zt != null);
		assertTrue(zt2 == null);
		assertTrue(manageAll.getUkupanPrihod() == ku1.getCena());
		assertTrue(k2.getNerealizovaniTretmani().size() == 0);
		assertTrue(kozm.getRaspored().size() == 1);

		k.getNerealizovaniTretmani().remove(zt);
		kozm.getRaspored().remove(zt);
		zakazaniTretmaniMng.getZakazaniTretmani().remove(zt);	
	}
	
	@Test
	public void zakaziTretmanKarticaLojalnostiTest() {
		manageAll.setUkupanPrihod(0);
		
		Klijent k = klijentiMng.pronadjiKlijentaPoId(1);
		k.setKarticaLojalnosti(true);
		Kozmeticar kozm = kozmeticariMng.pronadjiKozmeticaraPoId(1);
		KozmetickaUsluga ku1 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(1);
	
		ZakazaniTretman zt = manageAll.zakaziTretman(k, ku1, kozm, datum1);
		
		assertTrue(zt != null);
		assertTrue(manageAll.getUkupanPrihod() == ku1.getCena() - ku1.getCena() * 1 / 10);
		assertTrue(zt.getCena() == ku1.getCena() - ku1.getCena() * 1 / 10);
		assertTrue(zt.getDatumVreme().equals(datum1));
		assertTrue(k.getNerealizovaniTretmani().size() == 1);
		assertTrue(kozm.getRaspored().size() == 1);
		assertTrue(zakazaniTretmaniMng.pronadjiZakazaniTretmanPoId(1).equals(zt));

		k.setKarticaLojalnosti(false);
		k.getNerealizovaniTretmani().remove(zt);
		kozm.getRaspored().remove(zt);
		zakazaniTretmaniMng.getZakazaniTretmani().remove(zt);	
	}
	
	@Test
	public void otkaziTretmanKaoKlijentTest() {
		manageAll.setUkupanPrihod(0);
		manageAll.setUkupanRashod(0);
		
		Klijent k = klijentiMng.pronadjiKlijentaPoId(1);
		Kozmeticar kozm = kozmeticariMng.pronadjiKozmeticaraPoId(1);
		KozmetickaUsluga ku1 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(1);
	
		ZakazaniTretman zt = manageAll.zakaziTretman(k, ku1, kozm, datum1);
		
		manageAll.otkaziTretman("klijent", zt);
		assertTrue(zt.getStanje().equals("OTKAZAO KLIJENT"));
		assertTrue(manageAll.getUkupanPrihod() == ku1.getCena());
		assertTrue(manageAll.getUkupanRashod() == ku1.getCena() * 9 / 10);
		assertTrue(zt.getCena() == ku1.getCena() * 1 / 10);
		assertTrue(k.getNerealizovaniTretmani().size() == 1);
		assertTrue(kozm.getRaspored().size() == 0);
		assertTrue(zakazaniTretmaniMng.pronadjiZakazaniTretmanPoId(1).equals(zt));

		k.getNerealizovaniTretmani().remove(zt);
		kozm.getRaspored().remove(zt);
		zakazaniTretmaniMng.getZakazaniTretmani().remove(zt);
	}
	
	@Test
	public void otkaziTretmanKaoSalonTest() {
		manageAll.setUkupanPrihod(0);
		manageAll.setUkupanRashod(0);
		
		Klijent k = klijentiMng.pronadjiKlijentaPoId(1);
		Kozmeticar kozm = kozmeticariMng.pronadjiKozmeticaraPoId(1);
		KozmetickaUsluga ku1 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(1);
	
		ZakazaniTretman zt = manageAll.zakaziTretman(k, ku1, kozm, datum1);
		
		manageAll.otkaziTretman("salon", zt);
		
		assertTrue(zt.getStanje().equals("OTKAZAO SALON"));
		assertTrue(manageAll.getUkupanPrihod() == ku1.getCena());
		assertTrue(manageAll.getUkupanRashod() == ku1.getCena());
		assertTrue(zt.getCena() == 0);
		assertTrue(k.getNerealizovaniTretmani().size() == 1);
		assertTrue(kozm.getRaspored().size() == 0);
		assertTrue(zakazaniTretmaniMng.pronadjiZakazaniTretmanPoId(1).equals(zt));

		k.getNerealizovaniTretmani().remove(zt);
		kozm.getRaspored().remove(zt);
		zakazaniTretmaniMng.getZakazaniTretmani().remove(zt);
	}
	
	@Test
	public void otkaziTretmanNijeDosaoTest() {
		manageAll.setUkupanPrihod(0);
		manageAll.setUkupanRashod(0);
		
		Klijent k = klijentiMng.pronadjiKlijentaPoId(1);
		Kozmeticar kozm = kozmeticariMng.pronadjiKozmeticaraPoId(1);
		KozmetickaUsluga ku1 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(1);
	
		ZakazaniTretman zt = manageAll.zakaziTretman(k, ku1, kozm, datum1);
		
		manageAll.otkaziTretman("nedolazak", zt);
		
		assertTrue(zt.getStanje().equals("NIJE SE POJAVIO"));
		assertTrue(manageAll.getUkupanPrihod() == ku1.getCena());
		assertTrue(manageAll.getUkupanRashod() == 0);
		assertTrue(zt.getCena() == ku1.getCena());
		assertTrue(k.getNerealizovaniTretmani().size() == 1);
		assertTrue(kozm.getRaspored().size() == 0);
		assertTrue(zakazaniTretmaniMng.pronadjiZakazaniTretmanPoId(1).equals(zt));

		k.getNerealizovaniTretmani().remove(zt);
		kozm.getRaspored().remove(zt);
		zakazaniTretmaniMng.getZakazaniTretmani().remove(zt);
	}
	
	@Test
	public void izvrsiTretmanTest() {
		manageAll.setUkupanPrihod(0);
		manageAll.setUkupanRashod(0);
		
		Klijent k = klijentiMng.pronadjiKlijentaPoId(1);
		Kozmeticar kozm = kozmeticariMng.pronadjiKozmeticaraPoId(1);
		KozmetickaUsluga ku1 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(1);
	
		ZakazaniTretman zt = manageAll.zakaziTretman(k, ku1, kozm, datum1);
		
		manageAll.izvrsiTretman(zt);
		
		assertTrue(zt.getStanje().equals("IZVRŠEN"));
		assertTrue(manageAll.getUkupanPrihod() == ku1.getCena());
		assertTrue(manageAll.getUkupanRashod() == 0);
		assertTrue(zt.getCena() == ku1.getCena());
		assertTrue(k.getNerealizovaniTretmani().size() == 0);
		assertTrue(k.getRealizovaniTretmani().size() == 1);
		assertTrue(kozm.getRaspored().size() == 0);
		assertTrue(zakazaniTretmaniMng.pronadjiZakazaniTretmanPoId(1).equals(zt));

		k.getNerealizovaniTretmani().remove(zt);
		kozm.getRaspored().remove(zt);
		zakazaniTretmaniMng.getZakazaniTretmani().remove(zt);
	}
	
	@Test
	public void randomKozmeticarTest() {
		Klijent k = klijentiMng.pronadjiKlijentaPoId(1);
		KozmetickaUsluga ku1 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(1);
	
		Kozmeticar kozm = manageAll.randomKozmeticar(k, ku1);
		
		assertTrue(kozm != null);
		int ok = 0;
		for (KozmetickiTretman kt : kozm.getKozmetickiTretmani()) {
			if (kt.getId() == ku1.getIdTretmana()) {
				ok = 1;
			}
		}
		assertTrue(ok == 1);
	}
	
	@Test
	public void dodeliKarticeTest() {
		manageAll.setVrKarticeLojalnosti(1);
		
		Klijent k = klijentiMng.pronadjiKlijentaPoId(1);
		Kozmeticar kozm = kozmeticariMng.pronadjiKozmeticaraPoId(1);
		KozmetickaUsluga ku1 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(1);
	
		ZakazaniTretman zt = manageAll.zakaziTretman(k, ku1, kozm, datum1);
		
		manageAll.dodeliKartice();
		
		assertTrue(k.getKarticaLojalnosti() == true);
		
		for (Klijent k2 : klijentiMng.getKlijenti()) {
			k2.setKarticaLojalnosti(false);
		}
		k.getNerealizovaniTretmani().remove(zt);
		kozm.getRaspored().remove(zt);
		zakazaniTretmaniMng.getZakazaniTretmani().remove(zt);
		manageAll.setVrKarticeLojalnosti(-1);
	}
	
	@Test
	public void potrosnjaKlijentaTest() {
		Klijent k = klijentiMng.pronadjiKlijentaPoId(1);
		KozmetickaUsluga ku1 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(1);

		zakazaniTretmaniMng.add(ku1, datum1, ku1.getTrajanje(), ku1.getCena(), "ZAKAZAN", 1, 5);
		zakazaniTretmaniMng.add(ku1, datum2, ku1.getTrajanje(), ku1.getCena() * 1 / 10, "OTKAZAO KLIJENT", 1, 3);	
		ZakazaniTretman zt = zakazaniTretmaniMng.pronadjiZakazaniTretmanPoId(1);
		ZakazaniTretman zt2 = zakazaniTretmaniMng.pronadjiZakazaniTretmanPoId(2);
		
		k.addNerealizovaniTretman(zt);
		k.addRealizovaniTretman(zt2);
		
		assertTrue(manageAll.potrosnjaKlijenta(k) == 2200.0);

		k.getRealizovaniTretmani().remove(zt2);
		k.getNerealizovaniTretmani().remove(zt);

		zakazaniTretmaniMng.getZakazaniTretmani().remove(zt);
		zakazaniTretmaniMng.getZakazaniTretmani().remove(zt2);
	}

}
