package guiFrames;

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

import manage.KlijentiManager;
import manage.KozmeticariManager;
import manage.ManageAll;
import manage.MenadzeriManager;
import manage.RecepcioneriManager;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import users.Klijent;
import users.Kozmeticar;
import users.Menadzer;
import users.Recepcioner;


public class LoginFrame {

	private ManageAll manageAll;
	private MenadzeriManager menadzeriMng;
	private KlijentiManager klijentiMng;
	private KozmeticariManager kozmeticariMng;
	private RecepcioneriManager recepcioneriMng;
	private String nazivSalona;
	private int[] radnoVreme;
	private JDialog loginFrame;	
	
	
	public LoginFrame(ManageAll manageAll) {
		this.manageAll = manageAll;
		this.menadzeriMng = manageAll.getMenadzeriMng();
		this.klijentiMng = manageAll.getKlijentiMng();
		this.kozmeticariMng = manageAll.getKozmeticariMng();
		this.recepcioneriMng = manageAll.getRecepcionariMng();		
		this.nazivSalona = manageAll.getImeSalona();
		this.radnoVreme = manageAll.getRadnoVreme();
		initialize();
	}
	
	
	private void initialize() {
		loginFrame = new JDialog();
		loginFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		loginFrame.setTitle(" " + nazivSalona + " - PRIJAVA");
		loginFrame.setSize(450, 290);
		loginFrame.setResizable(false);
		loginFrame.setLayout(new MigLayout("fillx, insets 20 30 20 30"));
		loginFrame.setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());
				
		String st1 = "<html> <p style = 'font-size: 14px; font-weight: 500;'> '";
		st1 += nazivSalona;
		st1 += "' </p> </html>";
		JLabel nazivSalonaLabel = new JLabel(st1);
		JLabel vremeLabel = new JLabel("<html> <p style = 'font-size: 11px; margin-top: 7px; margin-bottom: 15px'> RADNO VREME: 0" + radnoVreme[0] + " - " + radnoVreme[1] + "h </p> </html>");
		JLabel dobrodosliLabel = new JLabel("<html> <p style = 'margin-bottom: 25px; font-size: 13px; font-weight: 400;'> Dobrodošli! Molimo da se logujete. </p> </html>");
		nazivSalonaLabel.setBackground(loginFrame.getBackground());
		vremeLabel.setBackground(loginFrame.getBackground());
		dobrodosliLabel.setBackground(loginFrame.getBackground());
		
		Font font1 = new Font("Arial", Font.PLAIN, 14);
		JTextField korisnickoInput = new JTextField(23);
		JPasswordField lozinkaInput = new JPasswordField(23);
		korisnickoInput.setMargin(new Insets(2, 4, 2, 4));
		korisnickoInput.setFont(font1);
		lozinkaInput.setMargin(new Insets(2, 4, 2, 4));
		lozinkaInput.setFont(font1);
		
		JLabel korisnickoLabel = new JLabel("Korisničko ime:");
		JLabel lozinkaLabel = new JLabel("Lozinka:");
		korisnickoLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lozinkaLabel.setFont(new Font("Arial", Font.BOLD, 14));
		korisnickoLabel.setBackground(loginFrame.getBackground());
		lozinkaLabel.setBackground(loginFrame.getBackground());
		
		JButton btnOk = new JButton("Prijavi se");
		JButton btnCancel = new JButton("Exit");
		btnOk.setPreferredSize(new Dimension(20, 30));
		btnOk.setMargin(new Insets(5, 9, 5, 9));
		btnCancel.setPreferredSize(new Dimension(30, 30));
		btnCancel.setMargin(new Insets(5, 9, 5, 9));
		loginFrame.getRootPane().setDefaultButton(btnOk);
		
		JButton btnRegister = new JButton("Registruj se");
		btnRegister.setMargin(new Insets(5, 8, 5, 8));
		btnRegister.setPreferredSize(new Dimension(20, 30));
		
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginFrame.setVisible(false);
				loginFrame.dispose();
			}
		});
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnickoIme = korisnickoInput.getText().trim();
				String lozinka = new String(lozinkaInput.getPassword());
				if (korisnickoIme.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli korisničko ime!");
					return;
				}
				if (lozinka.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli lozinku!");
					return;
				}
				Boolean ok = false;
				for (Menadzer m : menadzeriMng.getMenadzeri()) {
					if (m.getKorisnickoIme().equals(korisnickoIme)) {
						if (m.getLozinka().equals(lozinka)) {
							ok = true;
							loginFrame.setVisible(false);
							loginFrame.dispose();
							loginAsMenadzer(m);
							return;
						} else {
							JOptionPane.showMessageDialog(null, "Pogrešno unesena lozinka!");
							return;
						}
					}
				}
				if (ok == false) {
					for (Klijent k : klijentiMng.getKlijenti()) {
						if (k.getKorisnickoIme().equals(korisnickoIme)) {
							if (k.getLozinka().equals(lozinka)) {
								ok = true;
								loginFrame.setVisible(false);
								loginFrame.dispose();
								loginAsKlijent(k);
								return;
							} else {
								JOptionPane.showMessageDialog(null, "Pogrešno unesena lozinka!");
								return;
							}
						}
					}
				}
				if (ok == false) {
					for (Kozmeticar k : kozmeticariMng.getKozmeticari()) {
						if (k.getKorisnickoIme().equals(korisnickoIme)) {
							if (k.getLozinka().equals(lozinka)) {
								ok = true;
								loginFrame.setVisible(false);
								loginFrame.dispose();
								loginAsKozmeticar(k);
								return;
							} else {
								JOptionPane.showMessageDialog(null, "Pogrešno unesena lozinka!");
								return;
							}
						}
					}
				}
				if (ok == false) {
					for (Recepcioner r : recepcioneriMng.getRecepcioneri()) {
						if (r.getKorisnickoIme().equals(korisnickoIme)) {
							if (r.getLozinka().equals(lozinka)) {
								ok = true;
								loginFrame.setVisible(false);
								loginFrame.dispose();
								loginAsRecepcioner(r);
								return;
							} else {
								JOptionPane.showMessageDialog(null, "Pogrešno unesena lozinka!");
								return;
							}
						}
					}
				}
				if (ok == false) {
					JOptionPane.showMessageDialog(null, "Pogrešno uneseni podaci!");
					return;
				}
	
			}
		});
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginFrame.setVisible(false);
				loginFrame.dispose();
				RegisterFrame registerFrame = new RegisterFrame(manageAll);
				registerFrame.toString();
				return;
			}
		});
			
		CC centerSpanXWrapCC = new CC();
		centerSpanXWrapCC.alignX("center").spanX().wrap();
		CC rightSplit2SpanXCC = new CC();
		rightSplit2SpanXCC.alignX("right").split(2).spanX();
		CC centerSplit2SpanXCC = new CC();
		centerSplit2SpanXCC.alignX("center").split(2).spanX();
		
		loginFrame.add(nazivSalonaLabel, centerSpanXWrapCC);
		loginFrame.add(vremeLabel, centerSpanXWrapCC);	
		loginFrame.add(dobrodosliLabel, centerSpanXWrapCC);
		loginFrame.add(korisnickoLabel);
		loginFrame.add(korisnickoInput, "wrap");
		loginFrame.add(new JLabel(""), centerSpanXWrapCC);
		loginFrame.add(lozinkaLabel);
		loginFrame.add(lozinkaInput, "wrap");
		loginFrame.add(new JLabel(""), centerSpanXWrapCC);
		loginFrame.add(new JLabel(""), centerSpanXWrapCC);
		loginFrame.add(new JLabel(""), centerSpanXWrapCC);
		loginFrame.add(btnOk, centerSplit2SpanXCC);
		loginFrame.add(btnCancel, "wrap");
		loginFrame.add(new JLabel(""), centerSpanXWrapCC);
		loginFrame.add(new JLabel(""), centerSpanXWrapCC);
		loginFrame.add(new JLabel(""), centerSpanXWrapCC);
		loginFrame.add(new JLabel("Registrujte se klikom na dugme. "), rightSplit2SpanXCC);
		loginFrame.add(btnRegister, "wrap");
			
		center(loginFrame, 0);
		loginFrame.pack();
		loginFrame.setVisible(true);		
	}
	
	
	private void loginAsMenadzer(Menadzer m) {
		MenadzerFrame menadzerFrame = new MenadzerFrame(manageAll, m);
		menadzerFrame.toString();
	}
	
	private void loginAsKlijent(Klijent k) {
		KlijentFrame klijentFrame = new KlijentFrame(manageAll, k);
		klijentFrame.toString();
	}
	
	private void loginAsKozmeticar(Kozmeticar k) {
		KozmeticarFrame kozmeticarFrame = new KozmeticarFrame(manageAll, k);
		kozmeticarFrame.toString();
	}
	
	private void loginAsRecepcioner(Recepcioner r) {
		RecepcionerFrame recepcionerFrame = new RecepcionerFrame(manageAll, r);
		recepcionerFrame.toString();
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
