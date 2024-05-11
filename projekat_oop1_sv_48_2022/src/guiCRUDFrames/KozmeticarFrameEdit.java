package guiCRUDFrames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;

import guiFrames.KozmeticariCRUDFrame;
import guiTableModels.KozmeticariCRUDTableModel;
import manage.KlijentiManager;
import manage.KozmeticariManager;
import manage.KozmetickiTretmaniManager;
import manage.ManageAll;
import manage.MenadzeriManager;
import manage.RecepcioneriManager;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import treatments.KozmetickiTretman;
import users.Klijent;
import users.Kozmeticar;
import users.Menadzer;
import users.Recepcioner;


public class KozmeticarFrameEdit extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private MenadzeriManager menadzeriMng;
	private KlijentiManager klijentiMng;
	private KozmeticariManager kozmeticariMng;
	private RecepcioneriManager recepcioneriMng;
	private KozmetickiTretmaniManager kozmetickiTretmaniMng;
	private Boolean okIme;
	private Boolean okPrezime;
	private Boolean okPol;
	private Boolean okTelefon;
	private Boolean okAdresa;
	private Boolean okKorisnicko;
	private Boolean okLozinka;
	private Boolean okTretmani;
	private KozmeticariCRUDFrame kozmeticariCRUDFrame;
	private Kozmeticar k;
	private JTable tabela;
	
	
	public KozmeticarFrameEdit(ManageAll manageAll, KozmeticariCRUDFrame kozmeticariCRUDFrame, Kozmeticar k, JTable tabela) {
		this.menadzeriMng = manageAll.getMenadzeriMng();
		this.klijentiMng = manageAll.getKlijentiMng();
		this.kozmeticariMng = manageAll.getKozmeticariMng();
		this.recepcioneriMng = manageAll.getRecepcionariMng();
		this.kozmetickiTretmaniMng = manageAll.getKozmetickiTretmaniMng();
		this.okIme = true;
		this.okPrezime = true;
		this.okPol = true;
		this.okTelefon = true;
		this.okAdresa = true;
		this.okKorisnicko = true;
		this.okLozinka = true;
		this.okTretmani = true;
		this.kozmeticariCRUDFrame = kozmeticariCRUDFrame;
		this.k = k;
		this.tabela = tabela;
		initialize();
	}
	
	
	private void initialize() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle(" EDIT KOZMETIČAR");
		setSize(660, 620);
		setResizable(false);
		setLayout(new MigLayout("fillx, insets 20 50 20 50"));
		setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
            	((KozmeticariCRUDFrame) kozmeticariCRUDFrame).refreshData();
            	kozmeticariCRUDFrame.validate();
            	kozmeticariCRUDFrame.repaint();
            }
        });
		
		JLabel addMenadzerTekst = new JLabel("<html> <p style = 'font-size: 13px; font-weight: 500;'> EDIT KOZMETIČAR </p> </html>");
		addMenadzerTekst.setBackground(getBackground());
		JLabel dobrodosliLabel = new JLabel("<html> <p style = 'margin-bottom: 5px; font-size: 12px; font-weight: 400;'> Molimo unesite sledeće podatke. </p> </html>");
		dobrodosliLabel.setBackground(getBackground());
		
		JLabel imeLabel = new JLabel("Ime:");
		JLabel prezimeLabel = new JLabel("Prezime:");
		JLabel polLabel = new JLabel("Pol:");
		JLabel telefonLabel = new JLabel("Telefon:");
		JLabel adresaLabel = new JLabel("Adresa:");
		JLabel korisnickoLabel = new JLabel("Korisničko ime:");
		JLabel lozinkaLabel = new JLabel("Lozinka:");
		JLabel spremaLabel = new JLabel("Nivo stručne sprema:");
		JLabel stazLabel = new JLabel("Staž:");
		JLabel bonusLabel = new JLabel("Bonus:");
		JLabel plataLabel = new JLabel("Plata:");
		JLabel tretmaniLabel = new JLabel("Tretmani:");
		imeLabel.setFont(new Font("Arial", Font.BOLD, 14));
		imeLabel.setBackground(getBackground());
		prezimeLabel.setFont(new Font("Arial", Font.BOLD, 14));
		prezimeLabel.setBackground(getBackground());
		polLabel.setFont(new Font("Arial", Font.BOLD, 14));
		polLabel.setBackground(getBackground());
		telefonLabel.setFont(new Font("Arial", Font.BOLD, 14));
		telefonLabel.setBackground(getBackground());
		adresaLabel.setFont(new Font("Arial", Font.BOLD, 14));
		adresaLabel.setBackground(getBackground());
		korisnickoLabel.setFont(new Font("Arial", Font.BOLD, 14));
		korisnickoLabel.setBackground(getBackground());
		lozinkaLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lozinkaLabel.setBackground(getBackground());
		spremaLabel.setFont(new Font("Arial", Font.BOLD, 14));
		spremaLabel.setBackground(getBackground());
		stazLabel.setFont(new Font("Arial", Font.BOLD, 14));
		stazLabel.setBackground(getBackground());
		bonusLabel.setFont(new Font("Arial", Font.BOLD, 14));
		bonusLabel.setBackground(getBackground());
		plataLabel.setFont(new Font("Arial", Font.BOLD, 14));
		plataLabel.setBackground(getBackground());
		tretmaniLabel.setFont(new Font("Arial", Font.BOLD, 14));
		tretmaniLabel.setBackground(getBackground());
		
		Font font1 = new Font("Arial", Font.PLAIN, 14);	
		
		JTextField imeInput = new JTextField(20);
		imeInput.setMargin(new Insets(2, 4, 2, 4));
		imeInput.setFont(font1);
		imeInput.setText(k.getIme());
		JLabel imeGreska = new JLabel("npr. Pera");
		imeGreska.setForeground(Color.GRAY);
		imeInput.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void warn() {
				  String ime2 = imeInput.getText();
				  if (ime2.length() > 30) {
					  imeGreska.setText("'Ime' sadrži više od 30 karaktera!");
					  imeGreska.setForeground(Color.RED);
					  imeGreska.setVisible(true);
					  okIme = false;
			     	  return;
				  }
				  String ime = ime2.trim().toLowerCase();
				  Boolean ok2 = true;
				  int spaces = 0;
				  for (int i = 0; i < ime.length(); i++) {
					  char c = ime.charAt(i);
					  int a = c;
					  if (c == 'ć') {
						  continue;
					  }
					  if (c == 'č') {
						  continue;
					  }
					  if (c == 'ž') {
						  continue;
					  }
					  if (c == 'š') {
						  continue;
					  }
					  if (c == 'đ') {
						  continue;
					  }
					  if (a == 32) {
						  spaces++;
						  continue;
					  }
					  if (a < 97 || a > 122) {
						  ok2 = false;
					  }
				  }
			      if (ime.equals("")) {
			    	  imeGreska.setText("'Ime' je prazno!");
			    	  imeGreska.setForeground(Color.RED);
			    	  imeGreska.setVisible(true);
			    	  okIme = false;
			     	  return;
			      } else if (ok2 == false) {
			    	  imeGreska.setText("'Ime' sadrži pogrešne karaktere!");
			    	  imeGreska.setForeground(Color.RED);
			    	  imeGreska.setVisible(true);
			    	  okIme = false;
			     	  return;
			      } else if (spaces > 1) {
			    	  imeGreska.setText("'Ime' sadrži više spejsova!");
			    	  imeGreska.setForeground(Color.RED);
			    	  imeGreska.setVisible(true);
			    	  okIme = false;
			     	  return;
			      } else {
			    	  imeGreska.setVisible(false);
			    	  imeGreska.setForeground(Color.GRAY);
			    	  okIme = true;
			      }
			  }
		});
		JTextField prezimeInput = new JTextField(20);
		prezimeInput.setMargin(new Insets(2, 4, 2, 4));
		prezimeInput.setFont(font1);
		prezimeInput.setText(k.getPrezime());
		JLabel prezimeGreska = new JLabel("npr. Perić");
		prezimeGreska.setForeground(Color.GRAY);
		prezimeInput.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void warn() {
				  String prezime2 = prezimeInput.getText();
				  if (prezime2.length() > 30) {
					  prezimeGreska.setText("'Prezime' sadrži više od 30 karaktera!");
					  prezimeGreska.setForeground(Color.RED);
					  prezimeGreska.setVisible(true);
					  okPrezime = false;
			     	  return;
				  }
				  String prezime = prezime2.trim().toLowerCase();
				  Boolean ok2 = true;
				  int spaces = 0;
				  for (int i = 0; i < prezime.length(); i++) {
					  char c = prezime.charAt(i);
					  int a = c;
					  if (c == 'ć') {
						  continue;
					  } else if (c == 'č') {
						  continue;
					  } else if (c == 'ž') {
						  continue;
					  } else if (c == 'š') {
						  continue;
					  } else if (c == 'đ') {
						  continue;
					  } else if (a == 32) {
						  spaces++;
						  continue;
					  }
					  if (a < 97 || a > 122) {
						  ok2 = false;
						  break;
					  }
				  }
			      if (prezime.equals("")) {
			    	  prezimeGreska.setText("'Prezime' je prazno!");
			    	  prezimeGreska.setForeground(Color.RED);
			    	  prezimeGreska.setVisible(true);
			    	  okPrezime = false;
			     	  return;
			      } else if (ok2 == false) {
			    	  prezimeGreska.setText("'Prezime' sadrži pogrešne karaktere!");
			    	  prezimeGreska.setForeground(Color.RED);
			    	  prezimeGreska.setVisible(true);
			    	  okPrezime = false;
			     	  return;
			      } else if (spaces > 1) {
			    	  prezimeGreska.setText("'Prezime' sadrži više spejsova!");
			    	  prezimeGreska.setForeground(Color.RED);
			    	  prezimeGreska.setVisible(true);
			    	  okPrezime = false;
			     	  return;
			      } else {
			    	  prezimeGreska.setVisible(false);
			    	  prezimeGreska.setForeground(Color.GRAY);
			    	  okPrezime = true;
			      }
			  }
		});
		JTextField polInput = new JTextField(20);
		polInput.setMargin(new Insets(2, 4, 2, 4));
		polInput.setFont(font1);
		polInput.setText(k.getPol());
		JLabel polGreska = new JLabel("npr. žensko");
		polGreska.setForeground(Color.GRAY);
		polInput.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void warn() {
				  String pol2 = polInput.getText();
				  if (pol2.length() > 10) {
					  polGreska.setText("'Pol' sadrži više od 10 karaktera!");
					  prezimeGreska.setForeground(Color.RED);
					  polGreska.setVisible(true);
					  okPol = false;
			     	  return;
				  }
				  String pol = pol2.trim().toLowerCase();
				  Boolean ok2 = true;		  
				  for (int i = 0; i < pol.length(); i++) {
					  char c = pol.charAt(i);
					  int a = c;
					  if (c == 'ć') {
						  continue;
					  } else if (c == 'č') {
						  continue;
					  } else if (c == 'ž') {
						  continue;
					  } else if (c == 'š') {
						  continue;
					  } else if (c == 'đ') {
						  continue;
					  } else if (a == 32) {
						  continue;
					  }
					  if (a < 97 || a > 122) {
						  ok2 = false;
						  break;
					  }
				  }
			      if (pol.equals("")) {
			    	  polGreska.setText("'Pol' je prazno!");
			    	  polGreska.setForeground(Color.RED);
			    	  polGreska.setVisible(true);
			    	  okPol = false;
			     	  return;
			      } else if (ok2 == false) {
			    	  polGreska.setText("'Pol' sadrži pogrešne karaktere!");
			    	  polGreska.setForeground(Color.RED);
			    	  polGreska.setVisible(true);
			    	  okPol = false;
			     	  return;
			      } else {
			    	  polGreska.setVisible(false);
			    	  polGreska.setForeground(Color.GRAY);
			    	  okPol = true;
			      }
			  }
		});
		JTextField telefonInput = new JTextField(20);
		telefonInput.setMargin(new Insets(2, 4, 2, 4));
		telefonInput.setFont(font1);
		telefonInput.setText(k.getTelefon());
		JLabel telefonGreska = new JLabel("npr. 0651231234");
		telefonGreska.setForeground(Color.GRAY);
		telefonInput.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void warn() {
				  String telefon2 = telefonInput.getText();
				  if (telefon2.length() > 15) {
					  telefonGreska.setText("'Telefon' sadrži više od 15 karaktera!");
					  telefonGreska.setForeground(Color.RED);
					  telefonGreska.setVisible(true);
					  okTelefon = false;
			     	  return;
				  }
				  String telefon = telefon2.trim(); 
				  for (Menadzer m2 : menadzeriMng.getMenadzeri()) {
					  if (m2.getTelefon().equals(telefon)) {
						  telefonGreska.setText("Telefon je zauzet!");
						  telefonGreska.setForeground(Color.RED);
						  telefonGreska.setVisible(true);
				    	  okKorisnicko = false;
				     	  return;
					  }
				  }
				  for (Klijent k2 : klijentiMng.getKlijenti()) {
					  if (k2.getTelefon().equals(telefon)) {
						  telefonGreska.setText("Telefon je zauzet!");
						  telefonGreska.setForeground(Color.RED);
						  telefonGreska.setVisible(true);
				    	  okKorisnicko = false;
				     	  return;
					  }
				  }
				  for (Kozmeticar k2 : kozmeticariMng.getKozmeticari()) {
					  if (k2.getTelefon().equals(telefon)) {
						  telefonGreska.setText("Telefon je zauzet!");
						  telefonGreska.setForeground(Color.RED);
						  telefonGreska.setVisible(true);
				    	  okKorisnicko = false;
				     	  return;
					  }
				  }
				  for (Recepcioner r2 : recepcioneriMng.getRecepcioneri()) {
					  if (r2.getTelefon().equals(telefon)) {
						  telefonGreska.setText("Telefon je zauzet!");
						  telefonGreska.setForeground(Color.RED);
						  telefonGreska.setVisible(true);
				    	  okKorisnicko = false;
				     	  return;
					  }
				  }
				  Boolean ok2 = true;		  
				  for (int i = 0; i < telefon.length(); i++) {
					  char c = telefon.charAt(i);
					  int a = c;
					  if (i == 0) {
						  if (a != 48) {
							  ok2 = false;
							  break;
						  }
					  }
					  if (i == 1) {
						  if (a != 54) {
							  ok2 = false;
							  break;
						  }
					  }
					  if (a < 48 || a > 57) {
						  ok2 = false;
						  break;
					  }
				  }
			      if (telefon.equals("")) {
			    	  telefonGreska.setText("'Telefon' je prazno!");
			    	  telefonGreska.setForeground(Color.RED);
			    	  telefonGreska.setVisible(true);
			    	  okTelefon = false;
			     	  return;
			      } else if (ok2 == false) {
			    	  telefonGreska.setText("'Telefon' sadrži pogrešne karaktere!");
			    	  telefonGreska.setForeground(Color.RED);
			    	  telefonGreska.setVisible(true);
			    	  okTelefon = false;
			     	  return;
			      } else if (telefon.length() != 10) {
			    	  telefonGreska.setText("'Telefon' mora biti dužine 10!");
			    	  telefonGreska.setForeground(Color.RED);
			    	  telefonGreska.setVisible(true);
			    	  okTelefon = false;
			     	  return;
			      } else {
			    	  telefonGreska.setVisible(false);
			    	  prezimeGreska.setForeground(Color.GRAY);
			    	  okTelefon = true;
			      }
			  }
		});
		JTextField adresaInput = new JTextField(20);
		adresaInput.setMargin(new Insets(2, 4, 2, 4));
		adresaInput.setFont(font1);
		adresaInput.setText(k.getAdresa());
		JLabel adresaGreska = new JLabel("npr. Cara Dušana 5");
		adresaGreska.setForeground(Color.GRAY);
		adresaInput.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void warn() {
				  String adresa2 = adresaInput.getText();
				  if (adresa2.length() > 30) {
					  adresaGreska.setText("'Adresa' sadrži više od 30 karaktera!");
					  adresaGreska.setForeground(Color.RED);
					  adresaGreska.setVisible(true);
					  okAdresa = false;
			     	  return;
				  }
				  String adresa = adresa2.trim().toLowerCase();
				  Boolean ok2 = true;		  
				  for (int i = 0; i < adresa.length(); i++) {
					  char c = adresa.charAt(i);
					  int a = c;
					  if (c == 'ć') {
						  continue;
					  } else if (c == 'č') {
						  continue;
					  } else if (c == 'ž') {
						  continue;
					  } else if (c == 'š') {
						  continue;
					  } else if (c == 'đ') {
						  continue;
					  } else if (a == 32) {
						  continue;
					  } else if (a >= 97 && a <= 122) {
						  continue;
					  } else if (a >= 48 && a <= 57) {
						  continue;
					  } else {
						  ok2 = false;
						  break;
					  }
				  }
			      if (adresa.equals("")) {
			    	  adresaGreska.setText("'Adresa' je prazno!");
			    	  adresaGreska.setForeground(Color.RED);
			    	  adresaGreska.setVisible(true);
			    	  okAdresa = false;
			     	  return;
			      } else if (ok2 == false) {
			    	  adresaGreska.setText("'Adresa' sadrži pogrešne karaktere!");
			    	  adresaGreska.setForeground(Color.RED);
			    	  adresaGreska.setVisible(true);
			    	  okAdresa = false;
			     	  return;
			      } else {
			    	  adresaGreska.setVisible(false);
			    	  adresaGreska.setForeground(Color.GRAY);
			    	  okAdresa = true;
			      }
			  }
		});
		JTextField korisnickoInput = new JTextField(20);
		korisnickoInput.setMargin(new Insets(2, 4, 2, 4));
		korisnickoInput.setFont(font1);
		korisnickoInput.setText(k.getKorisnickoIme());
		JLabel korisnickoGreska = new JLabel("npr. pera_peric12");
		korisnickoGreska.setForeground(Color.GRAY);
		korisnickoInput.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void warn() {
				  String korisnicko2 = korisnickoInput.getText();
				  if (korisnicko2.length() > 15) {
					  korisnickoGreska.setText("'Korisničko' sadrži više od 15 karaktera!");
					  korisnickoGreska.setForeground(Color.RED);
					  korisnickoGreska.setVisible(true);
					  okKorisnicko = false;
			     	  return;
				  }	  
				  String korisnicko = korisnicko2.trim().toLowerCase();
				  for (Menadzer m2 : menadzeriMng.getMenadzeri()) {
					  if (m2.getKorisnickoIme().equals(korisnicko)) {
						  korisnickoGreska.setText("Korisničko ime je zauzeto!");
				    	  korisnickoGreska.setForeground(Color.RED);
				    	  korisnickoGreska.setVisible(true);
				    	  okKorisnicko = false;
				     	  return;
					  }
				  }
				  for (Klijent k2 : klijentiMng.getKlijenti()) {
					  if (k2.getKorisnickoIme().equals(korisnicko)) {
						  korisnickoGreska.setText("Korisničko ime je zauzeto!");
				    	  korisnickoGreska.setForeground(Color.RED);
				    	  korisnickoGreska.setVisible(true);
				    	  okKorisnicko = false;
				     	  return;
					  }
				  }
				  for (Kozmeticar k2 : kozmeticariMng.getKozmeticari()) {
					  if (k2.getKorisnickoIme().equals(korisnicko)) {
						  korisnickoGreska.setText("Korisničko ime je zauzeto!");
				    	  korisnickoGreska.setForeground(Color.RED);
				    	  korisnickoGreska.setVisible(true);
				    	  okKorisnicko = false;
				     	  return;
					  }
				  }
				  for (Recepcioner r2 : recepcioneriMng.getRecepcioneri()) {
					  if (r2.getKorisnickoIme().equals(korisnicko)) {
						  korisnickoGreska.setText("Korisničko ime je zauzeto!");
				    	  korisnickoGreska.setForeground(Color.RED);
				    	  korisnickoGreska.setVisible(true);
				    	  okKorisnicko = false;
				     	  return;
					  }
				  }
				  int spaces = 0;
				  for (int i = 0; i < korisnicko.length(); i++) {
					  char c = korisnicko.charAt(i);
					  int a = c;
					  if (a == 32) {
						  spaces++;
						  break;
					  } 
				  }
			      if (korisnicko.equals("")) {
			    	  korisnickoGreska.setText("'Korisničko' je prazno!");
			    	  korisnickoGreska.setForeground(Color.RED);
			    	  korisnickoGreska.setVisible(true);
			    	  okKorisnicko = false;
			     	  return;
			      } else if (spaces > 0) {
			    	  korisnickoGreska.setText("'Korisničko' sadrži spejs!");
			    	  korisnickoGreska.setForeground(Color.RED);
			    	  korisnickoGreska.setVisible(true);
			    	  okKorisnicko = false;
			     	  return;
			      } else {
			    	  korisnickoGreska.setVisible(false);
			    	  korisnickoGreska.setForeground(Color.GRAY);
			    	  okKorisnicko = true;
			      }
			  }
		});
		JPasswordField lozinkaInput = new JPasswordField(20);
		lozinkaInput.setMargin(new Insets(2, 4, 2, 4));
		lozinkaInput.setFont(font1);
		lozinkaInput.setText(k.getLozinka());
		JLabel lozinkaGreska = new JLabel("npr. perica123");
		lozinkaGreska.setForeground(Color.GRAY);
		lozinkaInput.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void warn() {
				  char[] lozinka = lozinkaInput.getPassword();
				  if (lozinka.length > 20) {
					  lozinkaGreska.setText("'Lozinka' sadrži više od 20 karaktera!");
					  lozinkaGreska.setForeground(Color.RED);
					  lozinkaGreska.setVisible(true);
					  okLozinka = false;
			     	  return;
				  }	  
				  int spaces = 0;
				  for (int i = 0; i < lozinka.length; i++) {
					  char c = lozinka[i];
					  int a = c;
					  if (a == 32) {
						  spaces++;
						  break;
					  } 
				  }
			      if (lozinka.length == 0) {
			    	  lozinkaGreska.setText("'Lozinka' je prazno!");
			    	  lozinkaGreska.setForeground(Color.RED);
			    	  lozinkaGreska.setVisible(true);
			    	  okLozinka = false;
			     	  return;
			      } else if (spaces > 0) {
			    	  lozinkaGreska.setText("'Lozinka' sadrži spejs!");
			    	  lozinkaGreska.setForeground(Color.RED);
			    	  lozinkaGreska.setVisible(true);
			    	  okLozinka = false;
			     	  return;
			      } else if (lozinka.length < 6) {
			    	  lozinkaGreska.setText("'Lozinka' mora biti duža od 6 karaktera!");
			    	  lozinkaGreska.setForeground(Color.RED);
			    	  lozinkaGreska.setVisible(true);
			    	  okLozinka = false;
			     	  return;
			      } else {
			    	  lozinkaGreska.setVisible(false);
			    	  lozinkaGreska.setForeground(Color.GRAY);
			    	  okLozinka = true;
			      }
			  }
		});
		
		JLabel spremaGreska = new JLabel("{1, 2, 3, 4, 5}");
		spremaGreska.setForeground(Color.GRAY);
        Integer[] values = {1, 2, 3, 4, 5};
        JComboBox<Integer> list = new JComboBox<>(values);    
        list.setSelectedItem(k.getNivoStrucneSpreme());
        
        JLabel stazGreska = new JLabel("0 - 40");
        stazGreska.setForeground(Color.GRAY);
        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 40, 1); // Initial value: 0, Minimum: 0, Maximum: 100, Step: 1
        JSpinner spinner = new JSpinner(spinnerModel);    
        spinner.setValue(k.getStaz());
        
        JLabel bonusGreska = new JLabel("(RSD)");
        bonusGreska.setForeground(Color.GRAY);
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        NumberFormatter formatter = new NumberFormatter(decimalFormat);
        formatter.setValueClass(Double.class);
        formatter.setAllowsInvalid(false);
        formatter.setMinimum(0.0);
        JFormattedTextField bonusInput = new JFormattedTextField(formatter);
        bonusInput.setColumns(20);
        bonusInput.setValue(k.getBonus());
        
        JLabel plataGreska = new JLabel("(RSD)");
        JFormattedTextField plataInput = new JFormattedTextField(formatter);
        plataInput.setColumns(20);
        plataInput.setValue(k.getPlata());
        
        JTextField tretmaniInput = new JTextField(20);
        tretmaniInput.setMargin(new Insets(2, 4, 2, 4));
        tretmaniInput.setFont(font1);
        String t = "";
        for (KozmetickiTretman kt : k.getKozmetickiTretmani()) {
        	t += kt.getNaziv();
        	t += ",";
        }
        tretmaniInput.setText(t);
		JLabel tretmaniGreska = new JLabel("npr. 'masaža,manikir,pedikir'");
		tretmaniGreska.setForeground(Color.GRAY);
		tretmaniInput.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void warn() {
				  String tretmani2 = tretmaniInput.getText();
				  String tretmani = tretmani2.trim().toLowerCase();
				  Boolean ok2 = true;
				  for (int i = 0; i < tretmani.length(); i++) {
					  char c = tretmani.charAt(i);
					  int a = c;
					  if (c == ',') {
						  continue;
					  }
					  if (c == 'ć') {
						  continue;
					  }
					  if (c == 'č') {
						  continue;
					  }
					  if (c == 'ž') {
						  continue;
					  }
					  if (c == 'š') {
						  continue;
					  }
					  if (c == 'đ') {
						  continue;
					  }
					  if (a < 97 || a > 122) {
						  ok2 = false;
					  }
				  }
			      if (tretmani.equals("")) {
			    	  tretmaniGreska.setText("'Tretmani' je prazno!");
			    	  tretmaniGreska.setForeground(Color.RED);
			    	  tretmaniGreska.setVisible(true);
			    	  okTretmani = false;
			     	  return;
			      } else if (ok2 == false) {
			    	  tretmaniGreska.setText("'Tretmani' sadrži pogrešne karaktere!");
			    	  tretmaniGreska.setForeground(Color.RED);
			    	  tretmaniGreska.setVisible(true);
			    	  okTretmani = false;
			     	  return;
			      }  else {
			    	  tretmaniGreska.setVisible(false);
			    	  tretmaniGreska.setForeground(Color.GRAY);
			    	  okTretmani = true;
			      }
			      try {
			    	  String[] parts = tretmani.split(",");
			          for (String part : parts) {
			        	  int ima = 0;
			              for (KozmetickiTretman kt : kozmetickiTretmaniMng.getKozmetickiTretmani()) {
			            	  if (part.equals(kt.getNaziv())) {
			            		 ima = 1;
			            	  }
			              }
			              if (ima == 0) {
			            	  tretmaniGreska.setText("'Tretman' ne postoji!");
					    	  tretmaniGreska.setForeground(Color.RED);
					    	  tretmaniGreska.setVisible(true);
					    	  okTretmani = false;
					     	  return;
			              }
			          }
			          tretmaniGreska.setVisible(false);
			    	  tretmaniGreska.setForeground(Color.GRAY);
			    	  okTretmani = true;
			      } catch (Exception e) {
			    	  tretmaniGreska.setText("'Tretmani' nisu odvojeni zarezom!");
			    	  tretmaniGreska.setForeground(Color.RED);
			    	  tretmaniGreska.setVisible(true);
			    	  okTretmani = false;
			     	  return;
			      }
			  }
		});
        

		CC centerSpanXWrapCC = new CC();
		centerSpanXWrapCC.alignX("center").spanX().wrap();
		CC rightSplit2SpanXCC = new CC();
		rightSplit2SpanXCC.alignX("right").split(2).spanX();
		CC centerSplit2SpanXCC = new CC();
		centerSplit2SpanXCC.alignX("center").split(2).spanX();
		CC right = new CC();
		right.alignX("right");
		CC center = new CC();
		center.alignX("center");
		CC left = new CC();
		left.alignX("left");
		CC leftWrapCC = new CC();
		leftWrapCC.alignX("left").wrap();
		
		add(addMenadzerTekst, centerSpanXWrapCC);
		add(dobrodosliLabel, centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		
		JPanel imePanel = new JPanel();
		imePanel.setLayout(new MigLayout("fillx"));
		imePanel.add(imeGreska, centerSpanXWrapCC);
		imePanel.add(imeLabel, right);
		imePanel.add(imeInput, left);
		JPanel prezimePanel = new JPanel();
		prezimePanel.setLayout(new MigLayout("fillx"));
		prezimePanel.add(prezimeGreska, centerSpanXWrapCC);
		prezimePanel.add(prezimeLabel, right);
		prezimePanel.add(prezimeInput, left);
		add(imePanel, right);
		add(prezimePanel, leftWrapCC);
		
		JPanel polPanel = new JPanel();
		polPanel.setLayout(new MigLayout("fillx"));
		polPanel.add(polGreska, centerSpanXWrapCC);
		polPanel.add(polLabel, right);
		polPanel.add(polInput, left);
		JPanel telefonPanel = new JPanel();
		telefonPanel.setLayout(new MigLayout("fillx"));
		telefonPanel.add(telefonGreska, centerSpanXWrapCC);
		telefonPanel.add(telefonLabel, right);
		telefonPanel.add(telefonInput, left);
		add(polPanel, right);
		add(telefonPanel, leftWrapCC);
		
		JPanel korisnickoPanel = new JPanel();
		korisnickoPanel.setLayout(new MigLayout("fillx"));
		korisnickoPanel.add(korisnickoGreska, centerSpanXWrapCC);
		korisnickoPanel.add(korisnickoLabel, right);
		korisnickoPanel.add(korisnickoInput, left);
		JPanel lozinkaPanel = new JPanel();
		lozinkaPanel.setLayout(new MigLayout("fillx"));
		lozinkaPanel.add(lozinkaGreska, centerSpanXWrapCC);
		lozinkaPanel.add(lozinkaLabel, right);
		lozinkaPanel.add(lozinkaInput, left);
		add(korisnickoPanel, right);
		add(lozinkaPanel, leftWrapCC);
			
		JPanel adresaPanel = new JPanel();
		adresaPanel.setLayout(new MigLayout("fillx"));
		adresaPanel.add(adresaGreska, centerSpanXWrapCC);
		adresaPanel.add(adresaLabel, right);
		adresaPanel.add(adresaInput, left);
		add(adresaPanel, centerSpanXWrapCC);
		JPanel tretmaniPanel = new JPanel();
		tretmaniPanel.setLayout(new MigLayout("fillx"));
		tretmaniPanel.add(tretmaniGreska, centerSpanXWrapCC);
		tretmaniPanel.add(tretmaniLabel, right);
		tretmaniPanel.add(tretmaniInput, left);
		add(adresaPanel, right);
		add(tretmaniPanel, leftWrapCC);
		
		JPanel spremaPanel = new JPanel();
		spremaPanel.setLayout(new MigLayout("fillx"));
		spremaPanel.add(spremaGreska, centerSpanXWrapCC);
		spremaPanel.add(spremaLabel, right);
		spremaPanel.add(list, left);
		JPanel stazPanel = new JPanel();
		stazPanel.setLayout(new MigLayout("fillx"));
		stazPanel.add(stazGreska, centerSpanXWrapCC);
		stazPanel.add(stazLabel, right);
		stazPanel.add(spinner, left);	
		add(spremaPanel, right);
		add(stazPanel, leftWrapCC);
		
		JPanel bonusPanel = new JPanel();
		bonusPanel.setLayout(new MigLayout("fillx"));
		bonusPanel.add(bonusGreska, centerSpanXWrapCC);
		bonusPanel.add(bonusLabel, right);
		bonusPanel.add(bonusInput, left);
		JPanel plataPanel = new JPanel();
		plataPanel.setLayout(new MigLayout("fillx"));
		plataPanel.add(plataGreska, centerSpanXWrapCC);
		plataPanel.add(plataLabel, right);
		plataPanel.add(plataInput, left);	
		add(plataPanel, right);
		add(bonusPanel, leftWrapCC);
	
		
		JButton btnOk = new JButton("IZMENI");
		btnOk.setMargin(new Insets(5, 9, 5, 9));
		btnOk.setPreferredSize(new Dimension(20, 30));
		getRootPane().setDefaultButton(btnOk);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (okIme == false || okIme == false || okPrezime == false || okPol == false || okTelefon == false || okAdresa == false || okKorisnicko == false || okLozinka == false || okTretmani == false) {
					JOptionPane.showMessageDialog(null, "Molimo unesite pravilno podatke!");
					return;
				} else {
					String ime = imeInput.getText().trim();
					String prezime = prezimeInput.getText().trim();
					String pol = polInput.getText().trim();
					String telefon = telefonInput.getText().trim();
					String adresa = adresaInput.getText().trim();
					String korisnicko = korisnickoInput.getText().trim();
					char[] lozinka2 = lozinkaInput.getPassword();
					String lozinka = "";
					for (int i = 0; i < lozinka2.length; i++) {
						lozinka += lozinka2[i];
					}
					int nivoStrucneSpreme = (int) list.getSelectedItem();
					int staz = (int) spinner.getValue();
					if (staz < 0) {
						staz = 0;
					}
					if (staz > 40) {
						staz = 40;
					}
					double bonus = (double) bonusInput.getValue();
					double plata = (double) plataInput.getValue();
					String tretmani2 = tretmaniInput.getText();
					String [] tretmaniAll = tretmani2.split(",");
					ArrayList <KozmetickiTretman> tretmani = new ArrayList<KozmetickiTretman>();
					for (String s : tretmaniAll) {
			              for (KozmetickiTretman kt : kozmetickiTretmaniMng.getKozmetickiTretmani()) {
			            	  if (s.equals(kt.getNaziv())) {
			            		  tretmani.add(kt);
			            	  }
			              }
			        }	
					KozmeticariCRUDTableModel sm = (KozmeticariCRUDTableModel) tabela.getModel();
					sm.edit(k, ime, prezime, pol, telefon, adresa, korisnicko, lozinka, nivoStrucneSpreme, staz, bonus, plata);
					JOptionPane.showMessageDialog(null, "USPEŠNO IZMENJEN KOZMETIČAR!");
					((KozmeticariCRUDFrame) kozmeticariCRUDFrame).refreshData();
					kozmeticariCRUDFrame.validate();
					kozmeticariCRUDFrame.repaint();
					dispose();
				}
			}
		});
		
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(btnOk, centerSpanXWrapCC);
		
		center(KozmeticarFrameEdit.this, 1);
		setVisible(true);	
	}
	

	private static void center(Component component, int k) {
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    int w = component.getSize().width;
	    int h = component.getSize().height;
	    int x = (dim.width - w) / 2;
	    int y = (dim.height - h) / 2;
	    if (k == 0) {
	    	y -= 55;
	    }
	    // set the new location for the component
	    component.setLocation(x, y);
	}

}
