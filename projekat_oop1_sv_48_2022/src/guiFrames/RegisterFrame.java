package guiFrames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import manage.KlijentiManager;
import manage.ManageAll;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import users.Klijent;


public class RegisterFrame {

	private ManageAll manageAll;
	private KlijentiManager klijentiMng;
	private JDialog registerFrame;
	private Boolean okIme;
	private Boolean okPrezime;
	private Boolean okPol;
	private Boolean okTelefon;
	private Boolean okAdresa;
	private Boolean okKorisnicko;
	private Boolean okLozinka;
	
	
	public RegisterFrame(ManageAll manageAll) {
		this.manageAll = manageAll;
		this.klijentiMng = manageAll.getKlijentiMng();
		this.okIme = false;
		this.okPrezime = false;
		this.okPol = false;
		this.okTelefon = false;
		this.okAdresa = false;
		this.okKorisnicko = false;
		this.okLozinka = false;
		initialize();
	}
	
	
	private void initialize() {
		registerFrame = new JDialog();
		registerFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		registerFrame.setTitle(" REGISTRACIJA");
		registerFrame.setSize(430, 590);
		registerFrame.setResizable(false);
		registerFrame.setLayout(new MigLayout("fillx,  insets 20 50 20 50"));
		registerFrame.setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());
		
		JLabel registracijaTekst = new JLabel("<html> <p style = 'font-size: 13px; font-weight: 500;'> REGISTRACIJA </p> </html>");
		registracijaTekst.setBackground(registerFrame.getBackground());
		JLabel dobrodosliLabel = new JLabel("<html> <p style = 'margin-bottom: 5px; font-size: 12px; font-weight: 400;'> Molimo unesite sledeće podatke. </p> </html>");
		dobrodosliLabel.setBackground(registerFrame.getBackground());
		
		JLabel imeLabel = new JLabel("Ime:");
		JLabel prezimeLabel = new JLabel("Prezime:");
		JLabel polLabel = new JLabel("Pol:");
		JLabel telefonLabel = new JLabel("Telefon:");
		JLabel adresaLabel = new JLabel("Adresa:");
		JLabel korisnickoLabel = new JLabel("Korisničko ime:");
		JLabel lozinkaLabel = new JLabel("Lozinka:");
		imeLabel.setFont(new Font("Arial", Font.BOLD, 14));
		imeLabel.setBackground(registerFrame.getBackground());
		prezimeLabel.setFont(new Font("Arial", Font.BOLD, 14));
		prezimeLabel.setBackground(registerFrame.getBackground());
		polLabel.setFont(new Font("Arial", Font.BOLD, 14));
		polLabel.setBackground(registerFrame.getBackground());
		telefonLabel.setFont(new Font("Arial", Font.BOLD, 14));
		telefonLabel.setBackground(registerFrame.getBackground());
		adresaLabel.setFont(new Font("Arial", Font.BOLD, 14));
		adresaLabel.setBackground(registerFrame.getBackground());
		lozinkaLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lozinkaLabel.setBackground(registerFrame.getBackground());
		korisnickoLabel.setFont(new Font("Arial", Font.BOLD, 14));
		korisnickoLabel.setBackground(registerFrame.getBackground());
		
		Font font1 = new Font("Arial", Font.PLAIN, 14);	
		
		JTextField imeInput = new JTextField(20);
		imeInput.setMargin(new Insets(2, 4, 2, 4));
		imeInput.setFont(font1);
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
					  polGreska.setForeground(Color.RED);
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
				  for (Klijent k : klijentiMng.getKlijenti()) {
					  if (k.getTelefon().equals(telefon)) {
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
				  for (Klijent k : klijentiMng.getKlijenti()) {
					  if (k.getKorisnickoIme().equals(korisnicko)) {
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
		
		CC centerSpanXWrapCC = new CC();
		centerSpanXWrapCC.alignX("center").spanX().wrap();
		CC rightSplit2SpanXCC = new CC();
		rightSplit2SpanXCC.alignX("right").split(2).spanX();
		CC centerSplit2SpanXCC = new CC();
		centerSplit2SpanXCC.alignX("center").split(2).spanX();
		CC right = new CC();
		right.alignX("right");
		CC leftWrapCC = new CC();
		leftWrapCC.alignX("left").wrap();
		
		registerFrame.add(registracijaTekst, centerSpanXWrapCC);
		registerFrame.add(dobrodosliLabel, centerSpanXWrapCC);
		registerFrame.add(new JLabel(""), centerSpanXWrapCC);
		
		registerFrame.add(imeGreska, centerSpanXWrapCC);
		registerFrame.add(imeLabel, right);
		registerFrame.add(imeInput, leftWrapCC);
		registerFrame.add(prezimeGreska, centerSpanXWrapCC);
		registerFrame.add(prezimeLabel, right);
		registerFrame.add(prezimeInput, leftWrapCC);
		registerFrame.add(polGreska, centerSpanXWrapCC);
		registerFrame.add(polLabel, right);
		registerFrame.add(polInput, leftWrapCC);
		registerFrame.add(telefonGreska, centerSpanXWrapCC);
		registerFrame.add(telefonLabel, right);
		registerFrame.add(telefonInput, leftWrapCC);
		registerFrame.add(adresaGreska, centerSpanXWrapCC);
		registerFrame.add(adresaLabel, right);
		registerFrame.add(adresaInput, leftWrapCC);
		registerFrame.add(korisnickoGreska, centerSpanXWrapCC);
		registerFrame.add(korisnickoLabel, right);
		registerFrame.add(korisnickoInput, leftWrapCC);
		registerFrame.add(lozinkaGreska, centerSpanXWrapCC);
		registerFrame.add(lozinkaLabel, right);
		registerFrame.add(lozinkaInput, leftWrapCC);
		
		JButton btnOk = new JButton("Registruj se");
		btnOk.setMargin(new Insets(5, 9, 5, 9));
		JButton btnBack = new JButton("Nazad");
		btnOk.setPreferredSize(new Dimension(20, 30));
		btnBack.setPreferredSize(new Dimension(30, 30));
		btnBack.setMargin(new Insets(5, 9, 5, 9));
		registerFrame.getRootPane().setDefaultButton(btnOk);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (okIme == false || okIme == false || okPrezime == false || okPol == false || okTelefon == false || okAdresa == false || okKorisnicko == false || okLozinka == false) {
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
					int id = klijentiMng.getKlijentiLastID() + 1;
					Klijent k = new Klijent(id, ime, prezime, pol, telefon, adresa, korisnicko, lozinka, false);
					klijentiMng.add(k);
					JOptionPane.showMessageDialog(null, "USPEŠNO STE SE REGISTROVALI!");
					
					registerFrame.setVisible(false);
					registerFrame.dispose();
					KlijentFrame klijentFrame = new KlijentFrame(manageAll, k);
					klijentFrame.toString();
					return;
				}
			}
		});
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				registerFrame.setVisible(false);
				registerFrame.dispose();
				LoginFrame loginFrame = new LoginFrame(manageAll);
				loginFrame.toString();
				return;
			}
		});
		
		registerFrame.add(new JLabel(""), centerSpanXWrapCC);
		registerFrame.add(new JLabel(""), centerSpanXWrapCC);
		registerFrame.add(new JLabel(""), centerSpanXWrapCC);
		registerFrame.add(btnOk, centerSplit2SpanXCC);
		registerFrame.add(btnBack, "wrap");
		
		center(registerFrame, 1);
		//registerFrame.pack();
		registerFrame.setVisible(true);
		
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
