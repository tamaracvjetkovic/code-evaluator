package main;

import javax.swing.SwingUtilities;

import gui.guiSettings;
import manage.ManageAll;


public class Main {

	public static void main(String[] args) {

		KozmetickiSalon salon = new KozmetickiSalon();
		ManageAll manageApp = salon.getManageApp();
		
		//manageApp.deleteData();		
		//manageApp.podesiPodatke();
		//manageApp.testScenarioKT3();
		//manageApp.zakaziTretmane();
		//manageApp.saveData();
		
		manageApp.loadData();
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				guiSettings main = new guiSettings(manageApp);
				main.toString();
			}
		});	
	}
}