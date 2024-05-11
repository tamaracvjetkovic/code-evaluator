package gui;

import javax.swing.JFrame;

import guiFrames.LoginFrame;
//import guiWindows.LoginWindow;
import manage.ManageAll;


public class guiSettings extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private ManageAll manageAll;
	
	
	public guiSettings(ManageAll manageAll) {
		this.manageAll = manageAll;
		loginDialog(this.manageAll);
	}
	 
	
	private void loginDialog(ManageAll manageAll) {
		LoginFrame loginFrame = new LoginFrame(manageAll);
		loginFrame.toString();
	}

}
