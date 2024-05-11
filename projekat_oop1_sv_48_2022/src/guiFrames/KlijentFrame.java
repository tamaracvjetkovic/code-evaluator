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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import manage.ManageAll;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import users.Klijent;


public class KlijentFrame {
	private ManageAll manageAll;
	private String nazivSalona;
	private Klijent k;
	private JFrame klijentFrame;
	
	
	public KlijentFrame(ManageAll manageAll, Klijent k) {
		this.manageAll = manageAll;	
		this.nazivSalona = manageAll.getImeSalona();
		this.k = k;
		initialize();
	}
	
	
	private void initialize() {
		klijentFrame = new JFrame();
		klijentFrame.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		klijentFrame.setTitle(" " + nazivSalona + " - KLIJENT MENI");
		klijentFrame.setSize(450, 490);
		klijentFrame.setResizable(false);
		klijentFrame.setLayout(new MigLayout("fillx, insets 15 25 15 25"));
		klijentFrame.setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());

		JMenuBar meni = new JMenuBar();
		JMenu meniKlijent = new JMenu("Meni");
		JMenuItem zakazaniTretmaniMeni = new JMenuItem("Prikaži zakazane tretmane");
		meniKlijent.add(zakazaniTretmaniMeni);
		meni.add(meniKlijent);
		klijentFrame.setJMenuBar(meni);
		
		JLabel dobrodosliLabel = new JLabel("<html> <p style = 'margin-top: 5px; margin-bottom: 10px;'> Dobrodošli, " + k.getIme() + " " + k.getPrezime() + "! </html> </p>");
		JLabel podaciLabel = new JLabel("PODACI O KLIJENTU:");
		JLabel imeLabel = new JLabel("Ime: " + k.getIme());
		JLabel prezimeLabel = new JLabel("Prezime: " + k.getPrezime());
		JLabel polLabel = new JLabel("Pol: " + k.getPol());
		JLabel telefonLabel = new JLabel("Telefon: " + k.getTelefon());
		JLabel adresaLabel = new JLabel("Adresa: " + k.getAdresa());
		JLabel korisnickoLabel = new JLabel("Korisničko ime: " + k.getKorisnickoIme());
		String kl = "";
		manageAll.checkKarticaLojalnosti(k);
		if (k.getKarticaLojalnosti() == true) {
			kl += "IMA";
		} else {
			kl += "NEMA";
		}
		JLabel karticaLojalnostiLabel = new JLabel("Kartica lojalnosti: " + kl);
		double potroseno = manageAll.potrosnjaKlijenta(k);
		JLabel stanjeLabel = new JLabel("Stanje na kartici: " + potroseno);
		
		zakazaniTretmaniMeni.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	KlijentZakazaniTretmaniFrame klijentZakazaniTretmaniFrame = new KlijentZakazaniTretmaniFrame(manageAll, k, klijentFrame, karticaLojalnostiLabel, stanjeLabel);
		    	klijentZakazaniTretmaniFrame.toString(); 
		    }
		});
		
		dobrodosliLabel.setFont(new Font("Arial", Font.BOLD, 17));
		dobrodosliLabel.setBackground(klijentFrame.getBackground());
		JButton btnZakaziTretman = new JButton("ZAKAŽI TRETMAN");
		btnZakaziTretman.setMargin(new Insets(5, 9, 5, 9));
		btnZakaziTretman.setPreferredSize(new Dimension(20, 30));
		klijentFrame.getRootPane().setDefaultButton(btnZakaziTretman);
		btnZakaziTretman.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				klijentFrame.setVisible(false);
				klijentFrame.dispose();
				ZakaziTretmanFrame zakaziTretmanFrame = new ZakaziTretmanFrame(manageAll, k, null, 1);
				zakaziTretmanFrame.toString();
			}
		});
		podaciLabel.setFont(new Font("Arial", Font.BOLD, 15));
		podaciLabel.setBackground(klijentFrame.getBackground());
		imeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		imeLabel.setBackground(klijentFrame.getBackground());
		prezimeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		prezimeLabel.setBackground(klijentFrame.getBackground());
		polLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		polLabel.setBackground(klijentFrame.getBackground());
		telefonLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		telefonLabel.setBackground(klijentFrame.getBackground());
		adresaLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		adresaLabel.setBackground(klijentFrame.getBackground());
		korisnickoLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		korisnickoLabel.setBackground(klijentFrame.getBackground());
		karticaLojalnostiLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		karticaLojalnostiLabel.setBackground(klijentFrame.getBackground());
		stanjeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		stanjeLabel.setBackground(klijentFrame.getBackground());
		
		JButton btnLogOff = new JButton("Odloguj se");
		btnLogOff.setMargin(new Insets(5, 9, 5, 9));
		btnLogOff.setPreferredSize(new Dimension(20, 30));
		btnLogOff.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				klijentFrame.setVisible(false);
				klijentFrame.dispose();
				LoginFrame loginFrame = new LoginFrame(manageAll);
				loginFrame.toString();
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
		
		klijentFrame.add(dobrodosliLabel, centerSpanXWrapCC);
		klijentFrame.add(btnZakaziTretman, centerSpanXWrapCC);
		klijentFrame.add(new JLabel(""), centerSpanXWrapCC);
		klijentFrame.add(new JLabel(""), centerSpanXWrapCC);
		klijentFrame.add(new JLabel(""), centerSpanXWrapCC);
		klijentFrame.add(podaciLabel, leftWrapCC);
		klijentFrame.add(new JLabel(""), centerSpanXWrapCC);
		klijentFrame.add(imeLabel, leftWrapCC);
		klijentFrame.add(prezimeLabel, leftWrapCC);
		klijentFrame.add(polLabel, leftWrapCC);
		klijentFrame.add(telefonLabel, leftWrapCC);
		klijentFrame.add(adresaLabel, leftWrapCC);
		klijentFrame.add(new JLabel(""), centerSpanXWrapCC);
		klijentFrame.add(new JLabel(""), centerSpanXWrapCC);
		klijentFrame.add(karticaLojalnostiLabel, leftWrapCC);
		klijentFrame.add(stanjeLabel, leftWrapCC);
		klijentFrame.add(new JLabel(""), centerSpanXWrapCC);
		klijentFrame.add(new JLabel(""), centerSpanXWrapCC);
		klijentFrame.add(new JLabel(""), centerSpanXWrapCC);
		klijentFrame.add(btnLogOff, centerSpanXWrapCC);
		
		
		center(klijentFrame, 1);
		klijentFrame.setVisible(true);
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
