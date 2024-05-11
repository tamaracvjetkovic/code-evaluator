package main;

import manage.ManageAll;
import utils.AppSettings;


public class KozmetickiSalon {

	private AppSettings appSettings;
	private ManageAll manageApp;
	
	
	public KozmetickiSalon() {
		this.appSettings = new AppSettings("./data/menadzeri.csv", "./data/kozmeticari.csv", "./data/recepcioneri.csv", "./data/klijenti.csv", "./data/kozmetickeUsluge.csv", "./data/kozmetickiTretmani.csv", "./data/zakazaniTretmani.csv");
		this.manageApp = new ManageAll(appSettings);	
	}


	public AppSettings getAppSettings() {
		return appSettings;
	}
	public void setAppSettings(AppSettings appSettings) {
		this.appSettings = appSettings;
	}

	public ManageAll getManageApp() {
		return manageApp;
	}
	public void setManageApp(ManageAll manageApp) {
		this.manageApp = manageApp;
	}
}
