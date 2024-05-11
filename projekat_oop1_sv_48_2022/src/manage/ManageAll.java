package manage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import treatments.KozmetickaUsluga;
import treatments.KozmetickiTretman;
import treatments.ZakazaniTretman;
import users.Klijent;
import users.Kozmeticar;
import users.Recepcioner;
import utils.AppSettings;


public class ManageAll {
	
	private AppSettings appSettings;
	private MenadzeriManager menadzeriMng;
	private KozmeticariManager kozmeticariMng;
	private RecepcioneriManager recepcioneriMng;
	private KlijentiManager klijentiMng;
	private KozmetickeUslugeManager kozmetickeUslugeMng;
	private KozmetickiTretmaniManager kozmetickiTretmaniMng;
	private ZakazaniTretmaniManager zakazaniTretmaniMng;
	
	private String imeSalona = "Moj Salon";
	private int[] radnoVreme = {7, 22};
	private double ukupanPrihod = 0.0;
	private double ukupanRashod = 0.0;
	private int brIzvrsenihTretmana = 0;
	private double vrKarticeLojalnosti = -1;
	private int bonusUslovBrIzvrsenih = 0;
	private double bonusUslovZarada = 0.0;
	private double bonusVr = 0.0;
	
	
	public ManageAll() {}
	public ManageAll(AppSettings appSettings) {
		this.appSettings = appSettings;
		this.kozmetickeUslugeMng = new KozmetickeUslugeManager(this.appSettings.getKozmetickeUslugeFilename());
		this.recepcioneriMng = new RecepcioneriManager(this.appSettings.getRecepcioneriFilename());
		this.zakazaniTretmaniMng = new ZakazaniTretmaniManager(this.appSettings.getZakazaniTretmaniFilename(), kozmetickeUslugeMng);
		this.klijentiMng = new KlijentiManager(this.appSettings.getKlijentiFilename(), zakazaniTretmaniMng);
		this.kozmetickiTretmaniMng = new KozmetickiTretmaniManager(this.appSettings.getKozmetickiTretmaniFilename(), kozmetickeUslugeMng);
		this.kozmeticariMng = new KozmeticariManager(this.appSettings.getKozmeticariFilename(), kozmetickiTretmaniMng, zakazaniTretmaniMng);
		this.menadzeriMng = new MenadzeriManager(this.appSettings.getMenadzeriFilename());
	}

	
	public MenadzeriManager getMenadzeriMng() {
		return menadzeriMng;
	}
	public KozmeticariManager getKozmeticariMng() {
		return kozmeticariMng;
	}
	public RecepcioneriManager getRecepcionariMng() {
		return recepcioneriMng;
	}
	public KlijentiManager getKlijentiMng() {
		return klijentiMng;
	}
	public KozmetickeUslugeManager getKozmetickeUslugeMng() {
		return kozmetickeUslugeMng;
	}
	public void setKozmetickeUslugeMng(KozmetickeUslugeManager kozmetickeUslugeMng) {
		this.kozmetickeUslugeMng = kozmetickeUslugeMng;
	}
	public KozmetickiTretmaniManager getKozmetickiTretmaniMng() {
		return kozmetickiTretmaniMng;
	}
	public void setKozmetickiTretmaniMng(KozmetickiTretmaniManager kozmetickiTretmaniMng) {
		this.kozmetickiTretmaniMng = kozmetickiTretmaniMng;
	}
	public ZakazaniTretmaniManager getZakazaniTretmaniMng() {
		return zakazaniTretmaniMng;
	}
	public void setZakazaniTretmaniMng(ZakazaniTretmaniManager zakazaniTretmaniMng) {
		this.zakazaniTretmaniMng = zakazaniTretmaniMng;
	}
	

	public String getImeSalona() {
		return imeSalona;
	}
	public void setImeSalona(String imeSalona) {
		this.imeSalona = imeSalona;
	}
	public int[] getRadnoVreme() {
		return radnoVreme;
	}
	public void setRadnoVreme(int[] radnoVreme) {
		this.radnoVreme = radnoVreme;
	}
	public double getUkupanPrihod() {
		return ukupanPrihod;
	}
	public void setUkupanPrihod(double ukupanPrihod) {
		this.ukupanPrihod = ukupanPrihod;
	}
	public double getUkupanRashod() {
		return ukupanRashod;
	}
	public void setUkupanRashod(double ukupanRashod) {
		this.ukupanRashod = ukupanRashod;
	}
	public int getBrIzvrsenihTretmana() {
		return brIzvrsenihTretmana;
	}
	public void setBrIzvrsenihTretmana(int brIzvrsenihTretmana) {
		this.brIzvrsenihTretmana = brIzvrsenihTretmana;
	}
	public double getVrKarticeLojalnosti() {
		return vrKarticeLojalnosti;
	}
	public void setVrKarticeLojalnosti(double vrKarticeLojalnosti) {
		this.vrKarticeLojalnosti = vrKarticeLojalnosti;
	}
	public int getBonusUslovBrIzvrsenih() {
		return bonusUslovBrIzvrsenih;
	}
	public void setBonusUslovBrIzvrsenih(int bonusUslovBrIzvrsenih) {
		this.bonusUslovBrIzvrsenih = bonusUslovBrIzvrsenih;
	}
	public double getBonusUslovZarada() {
		return bonusUslovZarada;
	}
	public void setBonusUslovZarada(double bonusUslovZarada) {
		this.bonusUslovZarada = bonusUslovZarada;
	}
	public double getBonusVr() {
		return bonusVr;
	}
	public void setBonusVr(double bonusVr) {
		this.bonusVr = bonusVr;
	}
	

	public void isplatiZaposlene() {
		for (Kozmeticar k : kozmeticariMng.getKozmeticari()) {
			this.ukupanRashod += k.getPlata();
		}
		for (Recepcioner r : recepcioneriMng.getRecepcioneri()) {
			this.ukupanRashod += r.getPlata();
		}
	}
	
	public void izracunajPlate() {
		double osnova = 50000;
		for (Kozmeticar k : kozmeticariMng.getKozmeticari()) {
			double plata = osnova + k.getNivoStrucneSpreme() * 8000;
			plata += (plata - plata / k.getStaz());
			plata += k.getBonus();
			k.setPlata(plata);
		}
		for (Recepcioner r : recepcioneriMng.getRecepcioneri()) {
			double plata = osnova + r.getNivoStrucneSpreme() * 8000;
			plata += (plata - plata / r.getStaz());
			plata += r.getBonus();
			r.setPlata(plata);
		}
		this.saveData();
	}
	
	public void postaviBonus(int brPrag, double zaradjenoPrag, double bonus) {
		this.bonusUslovBrIzvrsenih = brPrag;
		this.bonusUslovZarada = zaradjenoPrag;
		this.bonusVr = bonus;
		int n = kozmeticariMng.getKozmeticariLastID() + 1;
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime d1 = LocalDateTime.of(now.getYear(), now.getMonth(), 1, 0, 0);
		LocalDateTime d2 = LocalDateTime.of(now.getYear(), now.getMonth(), 1, 0, 0);
		d2 = d2.plusMonths(1);
		double[] brIvrsenihTretmana = new double[n];
		for (int i = 1; i < n; i++) {
			brIvrsenihTretmana[i] = 0.0;
		}
		double[] ukupnaZarada = new double[n];
		for (int i = 1; i < n; i++) {
			ukupnaZarada[i] = 0.0;
		}
		for (ZakazaniTretman zt : zakazaniTretmaniMng.getZakazaniTretmani()) {
			if (zt.getDatumVreme().isAfter(d1) && zt.getDatumVreme().isBefore(d2)) {
				int id = zt.getIdKozmeticara();
				if (zt.getStanje().equals("IZVRŠEN")) {
					brIvrsenihTretmana[id]++;
				}
				double cena = zt.getCena();
				if (zt.getStanje().equals("IZVRŠEN")) {
					ukupnaZarada[id] += cena;
				}
				if (zt.getStanje().equals("NIJE SE POJAVIO")) {
					ukupnaZarada[id] += cena;
				}
				if (zt.getStanje().equals("OTKAZAO KLIJENT")) {
					ukupnaZarada[id] += (cena * 1/10);
				}
			}	
		}

		for (int i = 1; i < n; i++) {
			if (brIvrsenihTretmana[i] >= brPrag && ukupnaZarada[i] >= zaradjenoPrag) {
				kozmeticariMng.pronadjiKozmeticaraPoId(i).setBonus(bonus);
			} else {
				kozmeticariMng.pronadjiKozmeticaraPoId(i).setBonus(0.0);
			}
		}
		this.izracunajPlate();
		this.saveData();
	}
	
	public void checkKarticaLojalnosti(Klijent kl) {
		//System.out.println(this.vrKarticeLojalnosti);
		// menadzer nije jos postavio vr. => niko nema uslove za karticu trenutno
		if (this.vrKarticeLojalnosti == -1) {
			return;
		}
		double potroseno = potrosnjaKlijenta(kl);
		if (potroseno >= this.vrKarticeLojalnosti) {
			kl.setKarticaLojalnosti(true);
		} else {
			kl.setKarticaLojalnosti(false);
		}
		this.saveData();
	}
	
	public void dodeliKartice() {
		for (Klijent k : klijentiMng.getKlijenti()) {
			checkKarticaLojalnosti(k);
		}
	}
	
	public Kozmeticar randomKozmeticar(Klijent kl, KozmetickaUsluga ku) {
		int ok = 0;
		Kozmeticar k = null;
		for (Kozmeticar ko : kozmeticariMng.getKozmeticari()) {
			if (ok == 1) {
				break;
			}
			for (KozmetickiTretman ktt : ko.getKozmetickiTretmani()) {
				if (ktt.getId() == ku.getIdTretmana()) {
					k = ko;
				}
			}
		}
		return k;
	}
	
	public ZakazaniTretman zakaziTretman(Klijent kl, KozmetickaUsluga ku, Kozmeticar k, LocalDateTime datumVreme) {
		checkKarticaLojalnosti(kl);
		int trajanje = ku.getTrajanje();
		if (k == null) {
			int ok = 0;
			for (Kozmeticar ko : kozmeticariMng.getKozmeticari()) {
				if (ok == 1) {
					break;
				}
				for (KozmetickiTretman ktt : ko.getKozmetickiTretmani()) {
					if (ktt.getId() == ku.getIdTretmana()) {
						if (ko.getRaspored().size() == 0) {
							k = ko;
							ok = 1;
							break;
						}
						for (ZakazaniTretman ztt : ko.getRaspored()) {
							if (ok == 1) {
								break;
							}
							LocalDateTime datumVremee = ztt.getDatumVreme();
							int trajanjee = ztt.getTrajanje();
							if (datumVreme.isEqual(datumVremee)) {
								continue;
							}
							else if (datumVreme.isAfter(datumVremee) && datumVreme.isBefore(datumVremee.plusMinutes(trajanjee))) {
								continue;
							} 
							else if (datumVreme.plusMinutes(trajanje).isAfter(datumVremee) && datumVreme.plusMinutes(trajanje).isBefore(datumVremee.plusMinutes(trajanjee))) {
								continue;
							} else {
								k = ko;
								ok = 1;
								break;
							}
						}
					}
				}
			}
		} else {
			for (ZakazaniTretman zt : k.getRaspored()) {
				LocalDateTime dateTime = zt.getDatumVreme();
				int minutes = zt.getTrajanje();
				if (datumVreme.isEqual(dateTime)) {
					return null;
				}
				else if (datumVreme.isAfter(dateTime) && datumVreme.isBefore(dateTime.plusMinutes(minutes))) {
					return null;
				} 
				else if (datumVreme.plusMinutes(trajanje).isAfter(dateTime) && datumVreme.plusMinutes(trajanje).isBefore(dateTime.plusMinutes(minutes))) {
					return null;
				} 
			}
		}
		if (k == null) {
			return null;
		}
		int id = zakazaniTretmaniMng.getZakazaniTretmaniLastID() + 1;
		double cena = ku.getCena();
		if (kl.getKarticaLojalnosti()) {
			cena = cena - (cena * 10/100);
		}
		String stanje = "ZAKAZAN";
		int idKozmeticara = k.getId();
		int idKlijenta = kl.getId();
		ZakazaniTretman zt = new ZakazaniTretman(id, ku, datumVreme, trajanje, cena, stanje, idKozmeticara, idKlijenta);
		zakazaniTretmaniMng.add(zt);
		kl.addNerealizovaniTretman(zt);
		k.addToRaspored(zt);
	    this.ukupanPrihod += cena;	
	    this.saveData();
		return zt;	
	}
	
	
	public void otkaziTretman(String uloga, ZakazaniTretman zt) {
		if (uloga.equalsIgnoreCase("klijent")) {
			zt.setStanje("OTKAZAO KLIJENT");
			double cena = zt.getCena();
			zt.setCena(cena * 10 / 100);
			int idKozmeticara = zt.getIdKozmeticara();
			Kozmeticar k = kozmeticariMng.pronadjiKozmeticaraPoId(idKozmeticara);
			k.getRaspored().remove(zt);
			this.ukupanRashod += (cena * 9/10);
			this.saveData();
		}
		if (uloga.equalsIgnoreCase("salon")) {
			zt.setStanje("OTKAZAO SALON");	
			double cena = zt.getCena();
			int idKozmeticara = zt.getIdKozmeticara();
			Kozmeticar k = kozmeticariMng.pronadjiKozmeticaraPoId(idKozmeticara);
			k.getRaspored().remove(zt);
			this.ukupanRashod += cena;
			zt.setCena(0);
			this.saveData();
		}
		if (uloga.equalsIgnoreCase("nedolazak")) {
			zt.setStanje("NIJE SE POJAVIO");
			int idKozmeticara = zt.getIdKozmeticara();
			Kozmeticar k = kozmeticariMng.pronadjiKozmeticaraPoId(idKozmeticara);
			k.getRaspored().remove(zt);
			this.saveData();
		}
	}
	
	public void izvrsiTretman(ZakazaniTretman zt) {
		Klijent kl = klijentiMng.pronadjiKlijentaPoId(zt.getIdKlijenta());
		Kozmeticar k = kozmeticariMng.pronadjiKozmeticaraPoId(zt.getIdKozmeticara());
		zt.setStanje("IZVRŠEN");
		kl.getNerealizovaniTretmani().remove(zt);
		kl.getRealizovaniTretmani().add(zt);
		k.getRaspored().remove(zt);
		this.saveData();
		this.postaviBonus(this.bonusUslovBrIzvrsenih, this.bonusUslovZarada, this.bonusVr);
		return;
	}

	public double potrosnjaKlijenta(Klijent kl) {
		double potroseno = 0;
		for (ZakazaniTretman zt : kl.getNerealizovaniTretmani()) {
			double cena = zt.getCena();
			potroseno += cena;
		}
		for (ZakazaniTretman zt : kl.getRealizovaniTretmani()) {
			double cena = zt.getCena();
			potroseno += cena;
		}
		return potroseno;
	}

	
	public void zakaziTretmane() {
		
		setVrKarticeLojalnosti(2000);
		
		Klijent k = klijentiMng.pronadjiKlijentaPoId(1);
		Kozmeticar kozm = kozmeticariMng.pronadjiKozmeticaraPoId(1);
		Kozmeticar kozm2 = kozmeticariMng.pronadjiKozmeticaraPoId(2);
		Kozmeticar kozm3 = kozmeticariMng.pronadjiKozmeticaraPoId(3);
		KozmetickaUsluga ku1 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(1);
		KozmetickaUsluga ku2 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(2);
		KozmetickaUsluga ku3 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(3);
		KozmetickaUsluga ku4 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(4);
		KozmetickaUsluga ku5 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(5);
		KozmetickaUsluga ku6 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(6);

		LocalDateTime now = LocalDateTime.now();
		int m = now.getMonthValue();
		for (int i = 1; i <= m; i++) {
			LocalDateTime d1 = LocalDateTime.of(2023, i, 1, 10, 0);
			LocalDateTime d2 = LocalDateTime.of(2023, i, 6, 10, 0);
			LocalDateTime d3 = LocalDateTime.of(2023, i, 15, 10, 0);
			LocalDateTime d4 = LocalDateTime.of(2023, i, 23, 10, 0);
			LocalDateTime d5 = LocalDateTime.of(2023, i, 12, 10, 0);
			LocalDateTime d6 = LocalDateTime.of(2023, i, 27, 10, 0);
			ZakazaniTretman zt1 = zakaziTretman(k, ku1, kozm, d1);
			zt1.toString();
			zakaziTretman(k, ku2, kozm, d2);
			zakaziTretman(k, ku3, kozm2, d3);
			zakaziTretman(k, ku4, kozm3, d4);
			zakaziTretman(k, ku5, kozm2, d5);
			zakaziTretman(k, ku6, kozm, d6);
			//System.out.println(zt1);
			
		}
		
	}
	
	public void testScenarioKT3() {
		Klijent milicaMilicKL = klijentiMng.pronadjiKlijentaPoId(1);
		Klijent mikaMikicKL = klijentiMng.pronadjiKlijentaPoId(2);
		//Recepcioner peraPericR = recepcioneriMng.pronadjiRecepcioneraPoId(1);
		Kozmeticar simaSimicK = kozmeticariMng.pronadjiKozmeticaraPoId(1);
		Kozmeticar zikaZikicK = kozmeticariMng.pronadjiKozmeticaraPoId(2);
		Kozmeticar jovanaJovanovicK = kozmeticariMng.pronadjiKozmeticaraPoId(3);
		
		// 1. ZAKAZANI TRETMAN	
		KozmetickaUsluga francuskiManikirKU = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(3);
		//Termin t1 = new Termin(, );
		LocalDateTime datumVreme = LocalDateTime.of(2023, 6, 14, 10, 0);
		ZakazaniTretman zt1 = zakaziTretman(milicaMilicKL, francuskiManikirKU, jovanaJovanovicK, datumVreme);
		if (zt1 == null) {
			System.out.println("\n* GRESKA: Kreiranje 1. zakazanog termina neuspešno!\n");
		}
		
		// 2. ZAKAZANI TRETMAN	
		KozmetickaUsluga spaPedikirKU = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(6);
		LocalDateTime datumVreme2 = LocalDateTime.of(2023, 6, 15, 9, 0);
		ZakazaniTretman zt2 = zakaziTretman(milicaMilicKL, spaPedikirKU, zikaZikicK, datumVreme2);
		if (zt2 == null) {
			System.out.println("\n* GRESKA: Kreiranje 2. zakazanog termina neuspešno!\n");
		}
		
		// 3. ZAKAZANI TRETMAN	
		KozmetickaUsluga sportskaMasazaKU = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(2);
		LocalDateTime datumVreme3 = LocalDateTime.of(2023, 6, 16, 8, 0);
		ZakazaniTretman zt3 = zakaziTretman(mikaMikicKL, sportskaMasazaKU, simaSimicK, datumVreme3);
		if (zt3 == null) {
			System.out.println("\n* GRESKA: Kreiranje 3. zakazanog termina neuspešno!\n");
		}
		
		// 4. ZAKAZANI TRETMAN
		KozmetickaUsluga relaksMasazaKU = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(1);
		LocalDateTime datumVreme4 = LocalDateTime.of(2023, 6, 27, 10, 0);
		ZakazaniTretman zt4 = zakaziTretman(mikaMikicKL, relaksMasazaKU, zikaZikicK, datumVreme4);
		if (zt4 == null) {
			System.out.println("\n* GRESKA: Kreiranje 4. zakazanog termina neuspešno!\n");
		}
		
		// 5. ZAKAZANI TRETMAN
		LocalDateTime datumVreme5 = LocalDateTime.of(2023, 6, 10, 18, 0);
		ZakazaniTretman zt5 = zakaziTretman(mikaMikicKL, francuskiManikirKU, jovanaJovanovicK, datumVreme5);
		if (zt5 == null) {
			System.out.println("\n* GRESKA: Kreiranje 5. zakazanog termina neuspešno!\n");
		}
		
		System.out.println("\n* Podaci za Zika Zikic, kozmeticar ID = 2:");
		System.out.println(zikaZikicK);
		
		this.setVrKarticeLojalnosti(3500);
		
		//Klijent milicaMilicKL = klijentiMng.pronadjiKlijentaPoId(1);
		//Klijent mikaMikicKL = klijentiMng.pronadjiKlijentaPoId(2);
		
		izvrsiTretman(zt1);
		System.out.println("\n1. KLIJENT 1 IZVRSIO 1. TRETMAN (idTretmana = 1)\n" + zt1);
		System.out.println(klijentiMng.pronadjiKlijentaPoId(zt1.getIdKlijenta()));
		System.out.println(kozmeticariMng.pronadjiKozmeticaraPoId(zt1.getIdKlijenta()));
		System.out.println("KLIJENT POTROSIO: " + potrosnjaKlijenta(milicaMilicKL));
		System.out.println("PRIHOD: " + this.ukupanPrihod + " | RASHOD: " + this.ukupanRashod + "\n");
		
		otkaziTretman("klijent", zt2);
		System.out.println("\n2. KLIJENT 1 OTKAZAO 2. TRETMAN (idTretmana = 2)\n" + zt2);
		System.out.println(klijentiMng.pronadjiKlijentaPoId(zt2.getIdKlijenta()));
		System.out.println(kozmeticariMng.pronadjiKozmeticaraPoId(zt2.getIdKlijenta()));
		System.out.println("KLIJENT POTROSIO: " + potrosnjaKlijenta(milicaMilicKL));
		System.out.println("PRIHOD: " + this.ukupanPrihod + " | RASHOD: " + this.ukupanRashod + "\n");
		
		otkaziTretman("salon", zt3);
		System.out.println("\n3. SALON OTKAZAO 1. TRETMAN KLIJENTA 2 (idTretmana = 3)\n" + zt3);
		System.out.println(klijentiMng.pronadjiKlijentaPoId(zt3.getIdKlijenta()));
		System.out.println(kozmeticariMng.pronadjiKozmeticaraPoId(zt3.getIdKlijenta()));
		System.out.println("KLIJENT POTROSIO: " + potrosnjaKlijenta(mikaMikicKL));
		System.out.println("PRIHOD: " + this.ukupanPrihod + " | RASHOD: " + this.ukupanRashod + "\n");
		
		otkaziTretman("nedolazak", zt4);
		System.out.println("\n4. KLIJENT 2 NIJE SE POJAVIO NA 2. TRETMAN (idTretmana = 4)\n" + zt4);
		System.out.println(klijentiMng.pronadjiKlijentaPoId(zt4.getIdKlijenta()));
		System.out.println(kozmeticariMng.pronadjiKozmeticaraPoId(zt4.getIdKlijenta()));
		System.out.println("KLIJENT POTROSIO: " + potrosnjaKlijenta(mikaMikicKL));
		System.out.println("PRIHOD: " + this.ukupanPrihod + " | RASHOD: " + this.ukupanRashod + "\n");
		
		
		// 6. ZAKAZANI TRETMAN	
		KozmetickaUsluga gelLakKU = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(4);
		//Termin t1 = new Termin(, );
		LocalDateTime datumVreme6 = LocalDateTime.of(2023, 6, 14, 9, 0);
		ZakazaniTretman zt6 = zakaziTretman(milicaMilicKL, gelLakKU, simaSimicK, datumVreme6);
		if (zt6 == null) {
			System.out.println("\n* GRESKA: Kreiranje 6. zakazanog termina neuspešno!\n");
		}
		
		
		// 7. ZAKAZANI TRETMAN	
		KozmetickaUsluga spaManikirKU = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(5);
		//Termin t1 = new Termin(, );
		LocalDateTime datumVreme7 = LocalDateTime.of(2023, 6, 14, 9, 0);
		ZakazaniTretman zt7 = zakaziTretman(mikaMikicKL, spaManikirKU, null, datumVreme7);
		System.out.println("\n* SVI ZAKAZANI TRETMANI DO SADA:");
		for (ZakazaniTretman zt : zakazaniTretmaniMng.getZakazaniTretmani()) {
			System.out.println(zt);
		}System.out.println();
		if (zt7 == null) {
			System.out.println("\n* GRESKA: Kreiranje 7. zakazanog termina neuspešno!\n");
		}
		
		izvrsiTretman(zt7);
		System.out.println("\n* KLIJENT 2 IZVRSIO 3. TRETMAN (idTretmana = 6) (onaj sa neodabranim kozmeticarom)\n" + zt7);
		System.out.println(klijentiMng.pronadjiKlijentaPoId(zt7.getIdKlijenta()));
		// U kozmeticaru se obrisao izvrsen tretman iz rasporeda: 
		//System.out.println(kozmeticariMng.pronadjiKozmeticaraPoId(zt7.getIdKlijenta()));
		System.out.println("KLIJENT POTROSIO: " + potrosnjaKlijenta(mikaMikicKL));
		System.out.println("PRIHOD: " + this.ukupanPrihod + " | RASHOD: " + this.ukupanRashod + "\n");
				
		
		System.out.println("\n* PRIKAZ: KLIJENT MIKA MIKIC");
		System.out.println(klijentiMng.pronadjiKlijentaPoId(2));
		
		
		//postaviBonus();
		//izracunajPlate();
		System.out.println("\n\n* BONUSI (bonus nije veliki jer nema puno izvrsenih tretmana!):");
		for (Kozmeticar k : kozmeticariMng.getKozmeticari()) {
			System.out.println("Kozmeticar " + k.getIme() + " " + k.getPrezime() + " (id = " + k.getId() + "): " + k.getBonus());
		}
		System.out.println("\n* PLATE:");
		for (Kozmeticar k : kozmeticariMng.getKozmeticari()) {
			System.out.println("Kozmeticar " + k.getIme() + " " + k.getPrezime() + " (id = " + k.getId() + "): " + k.getPlata());
		}
		for (Recepcioner r : recepcioneriMng.getRecepcioneri()) {
			System.out.println("Recepcioner " + r.getIme() + " " + r.getPrezime() + " (id = " + r.getId() + "): " + r.getPlata());
		}
		System.out.println();
		
		LocalDateTime datumVreme8 = LocalDateTime.of(2023, 6, 28, 17, 0);
		LocalDateTime datumVreme9 = LocalDateTime.of(2023, 6, 28, 18, 0);
		ZakazaniTretman zt8 = zakaziTretman(milicaMilicKL, spaPedikirKU, zikaZikicK, datumVreme8);
		if (zt8 == null) {
			System.out.println("\n* GRESKA: Kreiranje 8. zakazanog termina neuspešno!\n");
		}
		ZakazaniTretman zt9 = zakaziTretman(milicaMilicKL, sportskaMasazaKU, zikaZikicK, datumVreme9);
		if (zt8 == null) {
			System.out.println("\n* GRESKA: Kreiranje 9. zakazanog termina neuspešno!\n");
		}
		zt9.toString();
		
		this.saveData();
		return;
	}
	

	
	public void podesiPodatke() {
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
		kozmetickeUslugeMng.add("Relaks masaža", 1600, 45, 1); // id = 1
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
		
		this.saveData();
	}

	
	public void ispisiSveDodano() {
		System.out.println("\nMENADZERI: ");
		for (Object o : menadzeriMng.prikaziMenadzere()) {
			System.out.println(o);
		}
		System.out.println("\nRECEPCIONERI: ");
		for (Object o : recepcioneriMng.prikaziRecepcionere()) {
			System.out.println(o);
		}
		System.out.println("\nKOZMETICARI: ");
		for (Object o : kozmeticariMng.prikaziKozmeticare()) {
			System.out.println(o);
		}
		System.out.println("\nKLIJENTI: ");
		for (Object o : klijentiMng.prikaziKlijente()) {
			System.out.println(o);
		}
		System.out.println("\nTRETMANI: ");
		for (int i = 1; i <= kozmetickiTretmaniMng.getKozmetickiTretmaniLastID(); i++) {
			System.out.println(kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(i));
		} System.out.println();
		for (int i = 1; i <= kozmetickeUslugeMng.getKozmetickeUslugeLastID(); i++) {
			int idTretmana = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(i).getIdTretmana();
			System.out.println(kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(idTretmana).getNaziv() + ": " + kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(i));
		}
	
	}
	
	

	
	
	
	
	public void loadData() {
		this.kozmetickeUslugeMng.loadData();
		this.recepcioneriMng.loadData();
		this.zakazaniTretmaniMng.loadData();
		this.klijentiMng.loadData();	
		this.kozmetickiTretmaniMng.loadData();
		this.kozmeticariMng.loadData();
		this.menadzeriMng.loadData();
		this.loadSalon();
	}
	
	public boolean loadSalon() {
		try { 
			BufferedReader br = new BufferedReader(new FileReader("./data/salon.csv"));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				String[] tokeni = linija.split(",");	
				String imeSalona = tokeni[0];
				String[] radnoVreme2 = tokeni[1].split(";");
				int[] radnoVreme = {Integer.valueOf(radnoVreme2[0]), Integer.valueOf(radnoVreme2[1])};
				double ukupanPrihod = Double.valueOf(tokeni[2]);
				double ukupanRashod = Double.valueOf(tokeni[3]);
				this.imeSalona = imeSalona;
				this.radnoVreme = radnoVreme;
				this.ukupanPrihod = ukupanPrihod;
				this.ukupanRashod = ukupanRashod;
				this.brIzvrsenihTretmana = Integer.valueOf(tokeni[4]);
				this.vrKarticeLojalnosti = Double.valueOf(tokeni[5]);
				this.bonusUslovBrIzvrsenih = Integer.valueOf(tokeni[6]);
				this.bonusUslovZarada = Double.valueOf(tokeni[7]);
				this.bonusVr = Double.valueOf(tokeni[8]);
			}
			br.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public void saveData() {
		this.kozmetickeUslugeMng.saveData();
		this.recepcioneriMng.saveData();
		this.zakazaniTretmaniMng.saveData();
		this.klijentiMng.saveData();	
		this.kozmetickiTretmaniMng.saveData();
		this.kozmeticariMng.saveData();
		this.menadzeriMng.saveData();
		this.saveSalon();
	}
	

	public String toFileString() {
		String radnoVreme = this.radnoVreme[0] + ";" + this.radnoVreme[1];
		return this.imeSalona + "," + radnoVreme + "," + this.ukupanPrihod + "," +
		       this.ukupanRashod + "," + this.brIzvrsenihTretmana + "," + 
		       this.vrKarticeLojalnosti + "," + this.bonusUslovBrIzvrsenih + "," +
		       this.bonusUslovZarada + "," + this.bonusVr + ",";
	}
	
	public boolean saveSalon() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter("./data/salon.csv", false));
			pw.println(this.toFileString());
			pw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public void deleteData() {
		this.kozmetickeUslugeMng.deleteData();
		this.recepcioneriMng.deleteData();
		this.zakazaniTretmaniMng.deleteData();
		this.klijentiMng.deleteData();	
		this.kozmetickiTretmaniMng.deleteData();
		this.kozmeticariMng.deleteData();
		this.menadzeriMng.deleteData();
		this.deleteSalon();
	}

	public boolean deleteSalon() {
		PrintWriter pw = null;
	    try {
	        pw = new PrintWriter(new FileWriter("./data/salon.csv", false));
	        pw.write("");
	        pw.close();
	        return true;
	    } catch (IOException e) {
	        return false;
	    }
	}

	
//-------------------------------------------------------------------------------------------------

/*
	public void runTest1() {
		System.out.println("\nTEST 1");
		
		menadzeriMng.add("Nikola", "Nikolić", "m", "0651234123", "Bulevar 1", "nikola_nikolic", "menadzer123", false);
		recepcioneriMng.add("Pera", "Perić", "m", "0661234123", "Bulevar 2", "pera_peric", "recepcioner123", 1, 15, 10, 70000, false);
		kozmeticariMng.add("Sima", "Simić", "m", "0651111999", "Bulevar 31", "sima_simic", "kozmeticar123", 2, 10, 15, 85000, false);;
		kozmeticariMng.add("Žika", "Žikić", "m", "0652222999", "Bulevar 32", "zika_zikic", "kozmeticar123", 3, 20, 30, 98000, false);;
		kozmeticariMng.add("Jadranka", "Jovanović", "ž", "0653333999", "Bulevar 33", "jadranka_jovanovic", "kozmeticar123", 2, 13, 11, 85000, false);
		klijentiMng.add("Milica", "Milić", "ž", "0657777123", "Cara Lazara 68", "milica_milic", "milica123", false);
		klijentiMng.add("Mika", "Mikić", "m", "0658888123", "Cara Lazara 12", "mika_mikic", "mika123", false);
		kozmeticariMng.edit(3, "Jovana", "Jovanović", "ž", "0653333999", "Bulevar 33", "jadranka_jovanovic", "kozmeticar123", false, 2, 13, 11, 85000);
		
		System.out.println("\nMENADZERI: ");
		for (Object o : menadzeriMng.prikaziMenadzere()) {
			System.out.println(o);
		}
		System.out.println("\nRECEPCIONERI: ");
		for (Object o : recepcioneriMng.prikaziRecepcionere()) {
			System.out.println(o);
		}
		System.out.println("\nKOZMETICARI: ");
		for (Object o : kozmeticariMng.prikaziKozmeticare()) {
			System.out.println(o);
		}
		System.out.println("\nKLIJENTI: ");
		for (Object o : klijentiMng.prikaziKlijente()) {
			System.out.println(o);
		}
		
		kozmeticariMng.remove(2);
		
		System.out.println("\n\nNakon brisanja: ");
		System.out.println("\nMENADZERI: ");
		for (Object o : menadzeriMng.prikaziMenadzere()) {
			System.out.println(o);
		}
		System.out.println("\nRECEPCIONERI: ");
		for (Object o : recepcioneriMng.prikaziRecepcionere()) {
			System.out.println(o);
		}
		System.out.println("\nKOZMETICARI: ");
		for (Object o : kozmeticariMng.prikaziKozmeticare()) {
			System.out.println(o);
		}
		System.out.println("\nKLIJENTI: ");
		for (Object o : klijentiMng.prikaziKlijente()) {
			System.out.println(o);
		}
		System.out.println("\n______________________________________________________________________________________________");
	}
	
	public void runTest2() {
		System.out.println("\nTEST 2\n");
		
		kozmetickiTretmaniMng.add("masaža", false); // id = 1
		kozmetickiTretmaniMng.add("manikir", false); // id = 2
		kozmetickiTretmaniMng.add("pedikir", false); // id = 3
		kozmetickeUslugeMng.add("Relaks masaža", 2000, 45, 1); // id = 1
		kozmetickeUslugeMng.add("Sportska masaža", 2500, 75, 1); // id = 2
		kozmetickeUslugeMng.add("Francuski manikir", 1500, 50, 2); // id = 3
		kozmetickeUslugeMng.add("Gel lak", 1600, 80, 2); // id = 4
		kozmetickeUslugeMng.add("Spa manikir", 2000, 90, 2); // id = 5
		kozmetickeUslugeMng.add("Spa pedikir", 1600, 45, 1); // id = 6
	
		System.out.println("\nTRETMANI: ");
		for (int i = 1; i <= kozmetickiTretmaniMng.getKozmetickiTretmaniLastID(); i++) {
			System.out.println(kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(i));
		} System.out.println();
		for (int i = 1; i <= kozmetickeUslugeMng.getKozmetickeUslugeLastID(); i++) {
			int idTretmana = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(i).getIdTretmana();
			System.out.println(kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(idTretmana).getNaziv() + ": " + kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(i));
		}
		
		// vrijemeTrajanja: 50min -> 55min, fr. manikir
		kozmetickeUslugeMng.edit(3, "Francuski manikir", 1500, 55, 2, false);
		// promijeni tip usluge, prvo obrisi iz tipa tretmana
		kozmetickeUslugeMng.edit(6, "Spa pedikir", 1600, 45, 3, false);

		
		System.out.println("\n\nNakon editovanja\n");
		System.out.println("\nTRETMANI: ");
		for (int i = 1; i <= kozmetickiTretmaniMng.getKozmetickiTretmaniLastID(); i++) {
			System.out.println(kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(i));
		} System.out.println();
		for (int i = 1; i <= kozmetickeUslugeMng.getKozmetickeUslugeLastID(); i++) {
			int idTretmana = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(i).getIdTretmana();
			System.out.println(kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(idTretmana).getNaziv() + ": " + kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(i));
		}
		
		kozmetickiTretmaniMng.remove(3);
	
		System.out.println("\n\nNakon brisanja");
		System.out.println("\nTRETMANI: ");
		for (int i = 1; i <= kozmetickiTretmaniMng.getKozmetickiTretmaniLastID(); i++) {
			System.out.println(kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(i));
		} System.out.println();
		for (int i = 1; i <= kozmetickeUslugeMng.getKozmetickeUslugeLastID(); i++) {
			int idTretmana = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(i).getIdTretmana();
			System.out.println(kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(idTretmana).getNaziv() + ": " + kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(i));
		}
		
		System.out.println("\n\n!!! NAPOMENA: sledeci ispis usluga ima istu svrhu, samo malo drugaciji prikaz.\n");
		System.out.println("Prikazivanje SAMO kozmetickih usluga koje NISU obrisane (nemaju 'obrisan = true'):");
		for (int i = 1; i <= kozmetickeUslugeMng.prikaziKozmetickeUsluge().size(); i++) {
			int idTretmana = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(i).getIdTretmana();
			System.out.println(kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(idTretmana).getNaziv() + ": " + kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(i));
		}
		
		System.out.println("\n______________________________________________________________________________________________");
	}

	public void runTest3() {
		System.out.println("\nTEST 3\n"); 
		
		kozmeticariMng.dodajTretmanKozmeticaru(1, kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(1));
		kozmeticariMng.dodajTretmanKozmeticaru(1, kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(3));
		kozmeticariMng.dodajTretmanKozmeticaru(3, kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(2));

		Termin t1 = new Termin(LocalDateTime.of(2023, 5, 7, 8, 0), 30);
		KozmetickaUsluga ku1 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(1);
		zakazaniTretmaniMng.add(ku1, t1, ku1.getCena(), "ZAKAZAN", 1, 1);
		
		Termin t2 = new Termin(LocalDateTime.of(2023, 5, 7, 10, 0), 50);
		KozmetickaUsluga ku2 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(4);
		zakazaniTretmaniMng.add(ku2, t2, ku2.getCena(), "ZAKAZAN", 1, 2);
		
		Termin t3 = new Termin(LocalDateTime.of(2023, 5, 7, 12, 0), 100);
		KozmetickaUsluga ku3 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(5);
		zakazaniTretmaniMng.add(ku3, t3, ku3.getCena(), "ZAKAZAN", 3, 2);
		
		for (ZakazaniTretman zk : zakazaniTretmaniMng.getZakazaniTretmani()) {
			System.out.println(zk);
			System.out.println("KLIJENT PO ID " + zk.getIdKlijenta() + ": " + klijentiMng.pronadjiKlijentaPoId(zk.getIdKlijenta()).getIme() + " " + klijentiMng.pronadjiKlijentaPoId(zk.getIdKlijenta()).getPrezime());
			System.out.println("KOZMETICAR PO ID " + zk.getIdKozmeticara() + ": " + kozmeticariMng.pronadjiKozmeticaraPoId(zk.getIdKozmeticara()).getIme() + " " + kozmeticariMng.pronadjiKozmeticaraPoId(zk.getIdKozmeticara()).getPrezime() + "\n");
		}
				
		Klijent k = klijentiMng.pronadjiKlijentaPoId(1);
		System.out.println("\n\nTOFILE: \n");
		System.out.println(k.toFileString());
		System.out.println("\n\n");
		

		KozmetickaUsluga ku4 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(3);
		zakazaniTretmaniMng.edit(2, ku4, t2, ku4.getCena(), "ZAKAZAN", 1, 2);
		
		System.out.println("\n\nNAKON EDITOVANJA USLUGE (FRANCUSKI MANIKIR) TRETMANA BR. 2: \n");
		for (ZakazaniTretman zk : zakazaniTretmaniMng.getZakazaniTretmani()) {
			System.out.println(zk);
			System.out.println("KLIJENT PO ID " + zk.getIdKlijenta() + ": " + klijentiMng.pronadjiKlijentaPoId(zk.getIdKlijenta()).getIme() + " " + klijentiMng.pronadjiKlijentaPoId(zk.getIdKlijenta()).getPrezime());
			System.out.println("KOZMETICAR PO ID " + zk.getIdKozmeticara() + ": " + kozmeticariMng.pronadjiKozmeticaraPoId(zk.getIdKozmeticara()).getIme() + " " + kozmeticariMng.pronadjiKozmeticaraPoId(zk.getIdKozmeticara()).getPrezime() + "\n");
		}
		
		System.out.println("\n\nNAKON PROMJENE CIJENE RELAKS MASAZE: \n");
		kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(1).setCena(1700);
		for (ZakazaniTretman zk : zakazaniTretmaniMng.getZakazaniTretmani()) {
			System.out.println(zk);
			System.out.println("KLIJENT PO ID " + zk.getIdKlijenta() + ": " + klijentiMng.pronadjiKlijentaPoId(zk.getIdKlijenta()).getIme() + " " + klijentiMng.pronadjiKlijentaPoId(zk.getIdKlijenta()).getPrezime());
			System.out.println("KOZMETICAR PO ID " + zk.getIdKozmeticara() + ": " + kozmeticariMng.pronadjiKozmeticaraPoId(zk.getIdKozmeticara()).getIme() + " " + kozmeticariMng.pronadjiKozmeticaraPoId(zk.getIdKozmeticara()).getPrezime() + "\n");
		}	
	}	
	public void runTests() {	
		runTest1();
		System.out.println("\n");
		runTest2();
		System.out.println("\n");
		runTest3();
	}*/
	
}


//-------------------------------------------------------------------------------------------------


