package guiFrames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;

import manage.ManageAll;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import users.Menadzer;


public class MenadzerFrame {
	private ManageAll manageAll;
	private String nazivSalona;
	private Menadzer m;
	private JFrame menadzerFrame;
	
	
	public MenadzerFrame(ManageAll manageAll, Menadzer m) {
		this.manageAll = manageAll;	
		this.nazivSalona = manageAll.getImeSalona();
		this.m = m;
		initialize();
	}
	
	
	private void initialize() {
		menadzerFrame = new JFrame();
		menadzerFrame.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		menadzerFrame.setTitle(" " + nazivSalona + " - MENADŽER MENI");
		menadzerFrame.setSize(580, 620);
		menadzerFrame.setResizable(false);
		menadzerFrame.setLayout(new MigLayout("fillx, insets 15 25 15 25"));
		menadzerFrame.setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());

		JMenuBar meni = new JMenuBar();
		JMenu meniMenadzer = new JMenu("Meni");
		JMenuItem dijagramiMeni = new JMenuItem("Prikaži dijagrame");
		meniMenadzer.add(dijagramiMeni);
		meni.add(meniMenadzer);
		menadzerFrame.setJMenuBar(meni);
		
		dijagramiMeni.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	DijagramiFrame dijagramiFrame = new DijagramiFrame(manageAll);
		    	dijagramiFrame.toString(); 
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
		CC centerSplit3SpanXCC = new CC();
		centerSplit3SpanXCC.spanX().split(3).alignX("center");
		CC centerSpanXCC = new CC();
		centerSpanXCC.alignX("center").spanX();
		
		JLabel dobrodosliLabel = new JLabel("<html> <p style = 'margin-top: 0px; margin-bottom: 5px;'> Dobrodošli, " + m.getIme() + " " + m.getPrezime() + "! </html> </p>");
		JLabel podaciLabel = new JLabel("PODACI O MENADŽERU:");
		JLabel imeLabel = new JLabel("Ime: " + m.getIme());
		JLabel prezimeLabel = new JLabel("Prezime: " + m.getPrezime());
		JLabel polLabel = new JLabel("Pol: " + m.getPol());
		JLabel telefonLabel = new JLabel("Telefon: " + m.getTelefon());
		JLabel adresaLabel = new JLabel("Adresa: " + m.getAdresa());
		JLabel korisnickoLabel = new JLabel("Korisničko ime: " + m.getKorisnickoIme());
	
		JPanel korisniciPanel = new JPanel();
		korisniciPanel.setLayout(new GridLayout(1, 4, 20, 20));
		Border blackline = BorderFactory.createLineBorder(Color.GRAY);
		JPanel box1 = new JPanel();
		JPanel box2 = new JPanel();
		JPanel box3 = new JPanel();
		JPanel box4 = new JPanel();
		box1.setLayout(new MigLayout("fillx"));
		box2.setLayout(new MigLayout("fillx"));
		box3.setLayout(new MigLayout("fillx"));
		box4.setLayout(new MigLayout("fillx"));
		box1.setPreferredSize(new Dimension(105, 60));
		box2.setPreferredSize(new Dimension(105, 60));
		box3.setPreferredSize(new Dimension(105, 60));
		box4.setPreferredSize(new Dimension(105, 60));
		box1.setBorder(blackline);
		box2.setBorder(blackline);
		box3.setBorder(blackline);
		box4.setBorder(blackline);
		box1.setBackground(new Color(250, 250, 250));
		box2.setBackground(new Color(250, 250, 250));
		box3.setBackground(new Color(250, 250, 250));
		box4.setBackground(new Color(250, 250, 250));
		JLabel menadzeriLabel = new JLabel("<html> <p style = 'font-size: 11px; margin-top: 2px;'> Menadžeri </p> </html>");
		JLabel klijentiLabel = new JLabel("<html> <p style = 'font-size: 11px; margin-top: 2px;'> Klijenti </p> </html>");
		JLabel kozmeticariLabel = new JLabel("<html> <p style = 'font-size: 11px; margin-top: 2px;'> Kozmetičari </p> </html>");
		JLabel reepcioneriLabel = new JLabel("<html> <p style = 'font-size: 11px; margin-top: 2px;'> Recepcioneri </p> </html>");
		JButton btn1 = new JButton("<html> <p style = 'font-size: 8px; font-weight: 500;'> IZABERI </p> </html>");
		btn1.setPreferredSize(new Dimension(20, 20));
		btn1.setMargin(new Insets(2, 3, 2, 3));
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenadzeriCRUDFrame menadzeriCRUDFrame = new MenadzeriCRUDFrame(manageAll, nazivSalona, m);
				menadzeriCRUDFrame.toString();
				return;
			}
		});
		JButton btn2 = new JButton("<html> <p style = 'font-size: 8px; font-weight: 500;'> IZABERI </p> </html>");
		btn2.setPreferredSize(new Dimension(20, 20));
		btn2.setMargin(new Insets(2, 3, 2, 3));
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KlijentiCRUDFrame klijentiCRUDFrame = new KlijentiCRUDFrame(manageAll, nazivSalona, m);
				klijentiCRUDFrame.toString();
				return;
			}
		});
		JButton btn3 = new JButton("<html> <p style = 'font-size: 8px; font-weight: 500;'> IZABERI </p> </html>");
		btn3.setPreferredSize(new Dimension(20, 20));
		btn3.setMargin(new Insets(2, 3, 2, 3));
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KozmeticariCRUDFrame kozmeticariCRUDFrame = new KozmeticariCRUDFrame(manageAll, nazivSalona, m);
				kozmeticariCRUDFrame.toString();
				return;
			}
		});
		JButton btn4 = new JButton("<html> <p style = 'font-size: 8px; font-weight: 500;'> IZABERI </p> </html>");
		btn4.setPreferredSize(new Dimension(20, 20));
		btn4.setMargin(new Insets(2, 3, 2, 3));
		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RecepcioneriCRUDFrame recepcioneriCRUDFrame = new RecepcioneriCRUDFrame(manageAll, nazivSalona, m);
				recepcioneriCRUDFrame.toString();
				return;
			}
		});
		box1.add(menadzeriLabel, centerSpanXWrapCC);
		box1.add(btn1, centerSpanXWrapCC);
		box2.add(klijentiLabel, centerSpanXWrapCC);
		box2.add(btn2, centerSpanXWrapCC);
		box3.add(kozmeticariLabel, centerSpanXWrapCC);
		box3.add(btn3, centerSpanXWrapCC);
		box4.add(reepcioneriLabel, centerSpanXWrapCC);
		box4.add(btn4, centerSpanXWrapCC);
		
		korisniciPanel.add(box1);
		korisniciPanel.add(box2);
		korisniciPanel.add(box3);
		korisniciPanel.add(box4);
		
		JPanel tretmaniPanel = new JPanel();
		tretmaniPanel.setLayout(new GridLayout(1, 3, 20, 20));
		JPanel box5 = new JPanel();
		JPanel box6 = new JPanel();
		JPanel box7 = new JPanel();
		box5.setLayout(new MigLayout("fillx"));
		box6.setLayout(new MigLayout("fillx"));
		box7.setLayout(new MigLayout("fillx"));
		box5.setPreferredSize(new Dimension(155, 60));
		box6.setPreferredSize(new Dimension(155, 60));
		box7.setPreferredSize(new Dimension(155, 60));
		box5.setBorder(blackline);
		box6.setBorder(blackline);
		box7.setBorder(blackline);
		box5.setBackground(new Color(250, 250, 250));
		box6.setBackground(new Color(250, 250, 250));
		box7.setBackground(new Color(250, 250, 250));
		JLabel tretmaniLabel = new JLabel("<html> <p style = 'font-size: 11px; margin-top: 2px;'> Kozmetički tretmani </p> </html>");
		JLabel uslugeLabel = new JLabel("<html> <p style = 'font-size: 11px; margin-top: 2px;'> Kozmetičke Usluge </p> </html>");
		JLabel zakazaniLabel = new JLabel("<html> <p style = 'font-size: 11px; margin-top: 2px;'> Zakazani Tretmani </p> </html>");
		JButton btn5 = new JButton("<html> <p style = 'font-size: 8px; font-weight: 500;'> IZABERI </p> </html>");
		btn5.setPreferredSize(new Dimension(20, 20));
		btn5.setMargin(new Insets(2, 3, 2, 3));
		btn5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KozmetickiTretmaniCRUDFrame kozmetickiTretmaniCRUDFrame = new KozmetickiTretmaniCRUDFrame(manageAll, m);
				kozmetickiTretmaniCRUDFrame.toString();
				return;
			}
		});
		JButton btn6 = new JButton("<html> <p style = 'font-size: 8px; font-weight: 500;'> IZABERI </p> </html>");
		btn6.setPreferredSize(new Dimension(20, 20));
		btn6.setMargin(new Insets(2, 3, 2, 3));
		btn6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KozmetickeUslugeCRUDFrame kozmetickeUslugeCRUDFrame = new KozmetickeUslugeCRUDFrame(manageAll, m);
				kozmetickeUslugeCRUDFrame.toString();
				return;
			}
		});
		JButton btn7 = new JButton("<html> <p style = 'font-size: 8px; font-weight: 500;'> IZABERI </p> </html>");
		btn7.setPreferredSize(new Dimension(20, 20));
		btn7.setMargin(new Insets(2, 3, 2, 3));
		btn7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				ZakazaniTretmaniCRUDFrame zakazaniTretmaniCRUDFrame = new ZakazaniTretmaniCRUDFrame(manageAll, 0);
				zakazaniTretmaniCRUDFrame.toString();
				return;
			}
		});
		box5.add(tretmaniLabel, centerSpanXWrapCC);
		box5.add(btn5, centerSpanXWrapCC);
		box6.add(uslugeLabel, centerSpanXWrapCC);
		box6.add(btn6, centerSpanXWrapCC);
		box7.add(zakazaniLabel, centerSpanXWrapCC);
		box7.add(btn7, centerSpanXWrapCC);
		tretmaniPanel.add(box5);
		tretmaniPanel.add(box6);
		tretmaniPanel.add(box7);
		
		JPanel salonPanel = new JPanel();
		salonPanel.setLayout(new GridLayout(1, 3, 20, 20));
		JPanel box8 = new JPanel();
		JPanel box9 = new JPanel();
		JPanel box10 = new JPanel();
		JPanel box11 = new JPanel();
		box8.setLayout(new MigLayout("fillx"));
		box9.setLayout(new MigLayout("fillx"));
		box10.setLayout(new MigLayout("fillx"));
		box11.setLayout(new MigLayout("fillx"));
		box8.setPreferredSize(new Dimension(105, 60));
		box9.setPreferredSize(new Dimension(105, 60));
		box10.setPreferredSize(new Dimension(105, 60));
		box11.setPreferredSize(new Dimension(105, 60));
		box8.setBorder(blackline);
		box9.setBorder(blackline);
		box10.setBorder(blackline);
		box11.setBorder(blackline);
		box8.setBackground(new Color(250, 250, 250));
		box9.setBackground(new Color(250, 250, 250));
		box10.setBackground(new Color(250, 250, 250));
		box11.setBackground(new Color(250, 250, 250));
		JLabel salonLabel = new JLabel("<html> <p style = 'font-size: 11px; margin-top: 2px;'> Salon </p> </html>");
		JLabel prihodLabel = new JLabel("<html> <p style = 'font-size: 11px; margin-top: 2px;'> Prihodi </p> </html>");
		JLabel rashodLabel = new JLabel("<html> <p style = 'font-size: 11px; margin-top: 2px;'> Rashodi </p> </html>");
		JLabel izvestajiLabel = new JLabel("<html> <p style = 'font-size: 11px; margin-top: 2px;'> Izveštaji </p> </html>");
		JButton btn8 = new JButton("<html> <p style = 'font-size: 8px; font-weight: 500;'> IZABERI </p> </html>");
		btn8.setPreferredSize(new Dimension(20, 20));
		btn8.setMargin(new Insets(2, 3, 2, 3));
		btn8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SalonCRUDFrame salonCRUDFrame = new SalonCRUDFrame(manageAll, m);
				salonCRUDFrame.toString();
				return;
			}
		});
		JButton btn9 = new JButton("<html> <p style = 'font-size: 8px; font-weight: 500;'> IZABERI </p> </html>");
		btn9.setPreferredSize(new Dimension(20, 20));
		btn9.setMargin(new Insets(2, 3, 2, 3));
		btn9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrihodiCRUDFrame prihodiCRUDFrame = new PrihodiCRUDFrame(manageAll, m);
				prihodiCRUDFrame.toString();
				return;
			}
		});
		JButton btn10 = new JButton("<html> <p style = 'font-size: 8px; font-weight: 500;'> IZABERI </p> </html>");
		btn10.setPreferredSize(new Dimension(20, 20));
		btn10.setMargin(new Insets(2, 3, 2, 3));
		btn10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RashodiCRUDFrame rashodiCRUDFrame = new RashodiCRUDFrame(manageAll, m);
				rashodiCRUDFrame.toString();
				return;
			}
		});
		JButton btn11 = new JButton("<html> <p style = 'font-size: 8px; font-weight: 500;'> IZABERI </p> </html>");
		btn11.setPreferredSize(new Dimension(20, 20));
		btn11.setMargin(new Insets(2, 3, 2, 3));
		btn11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IzvestajiCRUDFrame izvestajiCRUDFrame = new IzvestajiCRUDFrame(manageAll, m);
				izvestajiCRUDFrame.toString();
				return;
			}
		});
		box8.add(salonLabel, centerSpanXWrapCC);
		box8.add(btn8, centerSpanXWrapCC);
		box9.add(prihodLabel, centerSpanXWrapCC);
		box9.add(btn9, centerSpanXWrapCC);
		box10.add(rashodLabel, centerSpanXWrapCC);
		box10.add(btn10, centerSpanXWrapCC);
		box11.add(izvestajiLabel, centerSpanXWrapCC);
		box11.add(btn11, centerSpanXWrapCC);
		salonPanel.add(box8);
		salonPanel.add(box9);
		salonPanel.add(box10);
		salonPanel.add(box11);
		
		dobrodosliLabel.setFont(new Font("Arial", Font.BOLD, 17));
		dobrodosliLabel.setBackground(menadzerFrame.getBackground());
		podaciLabel.setFont(new Font("Arial", Font.BOLD, 15));
		podaciLabel.setBackground(menadzerFrame.getBackground());
		imeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		imeLabel.setBackground(menadzerFrame.getBackground());
		prezimeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		prezimeLabel.setBackground(menadzerFrame.getBackground());
		polLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		polLabel.setBackground(menadzerFrame.getBackground());
		telefonLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		telefonLabel.setBackground(menadzerFrame.getBackground());
		adresaLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		adresaLabel.setBackground(menadzerFrame.getBackground());
		korisnickoLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		korisnickoLabel.setBackground(menadzerFrame.getBackground());
		
		JButton btnLogOff = new JButton("Odloguj se");
		btnLogOff.setMargin(new Insets(5, 9, 5, 9));
		btnLogOff.setPreferredSize(new Dimension(20, 30));
		btnLogOff.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menadzerFrame.setVisible(false);
				menadzerFrame.dispose();
				LoginFrame loginFrame = new LoginFrame(manageAll);
				loginFrame.toString();
			}
		});
		
		menadzerFrame.add(dobrodosliLabel, centerSpanXWrapCC);
		menadzerFrame.add(new JLabel(""), centerSpanXWrapCC);
		menadzerFrame.add(podaciLabel, leftWrapCC);
		menadzerFrame.add(new JLabel(""), centerSpanXWrapCC);
		menadzerFrame.add(imeLabel, leftWrapCC);
		menadzerFrame.add(prezimeLabel, leftWrapCC);
		menadzerFrame.add(polLabel, leftWrapCC);
		menadzerFrame.add(telefonLabel, leftWrapCC);
		menadzerFrame.add(adresaLabel, leftWrapCC);
		menadzerFrame.add(new JLabel(""), centerSpanXWrapCC);
		menadzerFrame.add(new JLabel(""), centerSpanXWrapCC);
		menadzerFrame.add(korisniciPanel, centerSpanXWrapCC);
		menadzerFrame.add(new JLabel(""), centerSpanXWrapCC);
		menadzerFrame.add(tretmaniPanel, centerSpanXWrapCC);
		menadzerFrame.add(new JLabel(""), centerSpanXWrapCC);
		menadzerFrame.add(salonPanel, centerSpanXWrapCC);
		menadzerFrame.add(new JLabel(""), centerSpanXWrapCC);
		menadzerFrame.add(new JLabel(""), centerSpanXWrapCC);
		menadzerFrame.add(new JLabel(""), centerSpanXWrapCC);
		menadzerFrame.add(btnLogOff, centerSpanXWrapCC);
		
		center(menadzerFrame, 1);
		menadzerFrame.setVisible(true);
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