package guiFrames;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import manage.KozmeticariManager;
import manage.KozmetickeUslugeManager;
import manage.KozmetickiTretmaniManager;
import manage.ManageAll;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import treatments.KozmetickaUsluga;
import treatments.KozmetickiTretman;
import treatments.ZakazaniTretman;
import users.Klijent;
import users.Kozmeticar;
import users.Recepcioner;

public class ZakaziTretmanFrame {
	private ManageAll manageAll;
	private KozmeticariManager kozmeticariMng;
	private KozmetickeUslugeManager kozmetickeUslugeMng;
	private KozmetickiTretmaniManager kozmetickiTretmaniMng;
	private String nazivSalona;
	private int[] radnoVreme;
	private Klijent k;
	private Recepcioner r;
	private JFrame zakaziTretmanFrame;
	private LocalDateTime datumTermina;
	private int opcija;
	
	
	public ZakaziTretmanFrame(ManageAll manageAll, Klijent k, Recepcioner r, int opcija) {
		this.manageAll = manageAll;
		this.kozmeticariMng = manageAll.getKozmeticariMng();
		this.kozmetickeUslugeMng = manageAll.getKozmetickeUslugeMng();
		this.kozmetickiTretmaniMng = manageAll.getKozmetickiTretmaniMng();
		this.nazivSalona = manageAll.getImeSalona();
		this.radnoVreme = manageAll.getRadnoVreme();
		this.k = k;
		this.r = r;
		this.opcija = opcija;
		initialize();
	}
	

	private void izaberiUslugu() {
		zakaziTretmanFrame.setSize(690, 600);
		center(zakaziTretmanFrame, 1);
		JLabel zakazivanjeLabel = new JLabel("<html> <p style = 'font-size: 13px; font-weight: 500;'> ZAKAZIVANJE TRETMANA - KLIJENT '" + k.getIme() + " " + k.getPrezime() + "' </p> </html>");
		zakazivanjeLabel.setBackground(zakaziTretmanFrame.getBackground());
		JLabel dobrodosliLabel = new JLabel("<html> <p style = 'margin-bottom: 5px; font-size: 12px; font-weight: 400;'> 1. Molimo izaberite uslugu. </p> </html>");
		dobrodosliLabel.setBackground(zakaziTretmanFrame.getBackground());
		
		CC centerSpanXWrapCC = new CC();
		centerSpanXWrapCC.alignX("center").spanX().wrap();
		CC centerSplit3SpanXCC = new CC();
		centerSplit3SpanXCC.spanX().split(3).alignX("center");
		CC centerSpanXCC = new CC();
		centerSpanXCC.alignX("center").spanX();
		
		zakaziTretmanFrame.add(zakazivanjeLabel, centerSpanXWrapCC);
		zakaziTretmanFrame.add(dobrodosliLabel, centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		
		JPanel pretragaPanel = new JPanel(new GridLayout(0, 4, 5, 20));
		pretragaPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		
		JLabel tipTretmanaLabel = new JLabel("Tip tretmana:");
		tipTretmanaLabel.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField tipTretmanaInput = new JTextField(20);
		tipTretmanaInput.setMargin(new Insets(2, 4, 2, 4));
		JPanel tipTretmanaBox = new JPanel();
		tipTretmanaBox.setLayout(new MigLayout("fillx"));
		tipTretmanaBox.setPreferredSize(new Dimension(190, 85));
		tipTretmanaBox.add(tipTretmanaLabel, centerSpanXCC);	
		tipTretmanaBox.add(tipTretmanaInput, centerSpanXCC);	
		
		JLabel trajanjetretmanaLabel = new JLabel("Trajanje:");
		trajanjetretmanaLabel.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField trajanjetretmanaInput = new JTextField(20);
		trajanjetretmanaInput.setMargin(new Insets(2, 4, 2, 4));
		JPanel trajanjetretmanaBox = new JPanel();
		trajanjetretmanaBox.setLayout(new MigLayout("fillx"));
		trajanjetretmanaBox.setPreferredSize(new Dimension(190, 85));
		trajanjetretmanaBox.add(trajanjetretmanaLabel, centerSpanXCC);
		trajanjetretmanaBox.add(trajanjetretmanaInput, centerSpanXCC);
		
		JLabel cenaTretmanaLabel = new JLabel("Cena:");
		cenaTretmanaLabel.setFont(new Font("Arial", Font.BOLD, 13));
		JTextField cenaTretmanaInput = new JTextField(20);
		cenaTretmanaInput.setMargin(new Insets(2, 4, 2, 4));
		JPanel cenaTretmanaBox = new JPanel();
		cenaTretmanaBox.setLayout(new MigLayout("fillx"));
		cenaTretmanaBox.setPreferredSize(new Dimension(190, 85));
		cenaTretmanaBox.add(cenaTretmanaLabel, centerSpanXCC);
		cenaTretmanaBox.add(cenaTretmanaInput, centerSpanXCC);
		
		JButton btnSearch = new JButton("PRETRAŽI");
		btnSearch.setMargin(new Insets(5, 9, 5, 9));
		btnSearch.setPreferredSize(new Dimension(25, 25));
		JPanel buttonBox = new JPanel();
		buttonBox.setLayout(new MigLayout("fillx"));
		buttonBox.setPreferredSize(new Dimension(50, 85));
		buttonBox.add(new JLabel(""), centerSpanXCC);
		buttonBox.add(new JLabel(""), centerSpanXCC);
		buttonBox.add(btnSearch, centerSpanXCC);
	
		pretragaPanel.add(tipTretmanaBox);
		pretragaPanel.add(trajanjetretmanaBox);
		pretragaPanel.add(cenaTretmanaBox);
		pretragaPanel.add(buttonBox); 
		zakaziTretmanFrame.add(pretragaPanel, centerSpanXWrapCC);
        
		JPanel tretmaniPanel = new JPanel();
		//tretmaniPanel.setPreferredSize(new Dimension(600, 320));
		int n = kozmetickeUslugeMng.getKozmetickeUsluge().size();
		if (n > 6) {
			tretmaniPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		} else {
			tretmaniPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		}
		tretmaniPanel.setLayout(new GridLayout(0, 3, 25, 20));
		JScrollPane jScrollPane = new JScrollPane(tretmaniPanel);
		jScrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		zakaziTretmanFrame.add(jScrollPane, centerSpanXWrapCC);
		
		if (kozmetickeUslugeMng.getKozmetickeUsluge().size() <= 3) {
			zakaziTretmanFrame.setSize(690, 480);
			center(zakaziTretmanFrame, 1);
		}
		for (KozmetickaUsluga ku : kozmetickeUslugeMng.getKozmetickeUsluge()) {
			if (ku.getObrisan() == true) {
				continue;
			}
			JPanel box = new JPanel();
			Border blackline = BorderFactory.createLineBorder(Color.GRAY);
			box.setLayout(new MigLayout("fillx"));
			box.setPreferredSize(new Dimension(180, 154));
			box.setBorder(blackline);
			box.setBackground(new Color(250, 250, 250));
			JLabel uslugaLabel = new JLabel("<html> <p style = 'font-size: 11px; margin-top: 2px;'> " + ku.getNaziv() + " </p> </html>");
			box.add(uslugaLabel, centerSpanXWrapCC);
			String st2 = "-" + kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(ku.getIdTretmana()).getNaziv() + "-";
			JLabel tipLabel = new JLabel("<html> <p style = 'font-size: 8.8px; color: gray;'> " + st2 + " </p> </html>");
			box.add(tipLabel, centerSpanXWrapCC);
			JLabel trajanjeLabel = new JLabel("<html> <p style = 'font-size: 9px; margin-top: 5px;'> Trajanje: " + ku.getTrajanje() + "min </p> </html>");
			box.add(trajanjeLabel, centerSpanXWrapCC);
			JLabel cenaLabel = new JLabel("<html> <p style = 'font-size: 9px;'> Cena: " + ku.getCena() + "RSD </p> </html>");
			box.add(cenaLabel, centerSpanXWrapCC);
			JButton btnOk = new JButton("<html> <p style = 'font-size: 8px; font-weight: 500;'> IZABERI </p> </html>");
			btnOk.setPreferredSize(new Dimension(20, 20));
			btnOk.setMargin(new Insets(2, 3, 2, 3));
			btnOk.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					zakaziTretmanFrame.getContentPane().removeAll();
					zakaziTretmanFrame.validate();
					zakaziTretmanFrame.repaint();
					izaberiKozmeticara(ku);
					return;
				}
			});
			box.add(new JLabel(""), centerSpanXWrapCC);
			box.add(btnOk, centerSpanXWrapCC);
			tretmaniPanel.add(box);	
		}
		
		zakaziTretmanFrame.validate();
		zakaziTretmanFrame.repaint();
		
		JButton btnCancel = new JButton("<html> <p style = 'font-size: 9px; font-weight: 500;'> NAZAD NA MENI </p> </html>");
		btnCancel.setMargin(new Insets(4, 6, 4, 6));
		if (opcija == 1) {
			btnCancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					zakaziTretmanFrame.setVisible(false);
					zakaziTretmanFrame.dispose();
					KlijentFrame klijentFrame = new KlijentFrame(manageAll, k);
					klijentFrame.toString();
					return;
				}
			});
		} else {
			btnCancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					zakaziTretmanFrame.setVisible(false);
					zakaziTretmanFrame.dispose();
					RecepcionerFrame recepcionerFrame = new RecepcionerFrame(manageAll, r);
					recepcionerFrame.toString();
					return;
				}
			});
		}
		
		
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String tipTretmana2 = tipTretmanaInput.getText().trim();
				String tipTretmana = tipTretmana2.toLowerCase();
				String trajanjeTretmana = trajanjetretmanaInput.getText().trim();
				String cenaTretmana = cenaTretmanaInput.getText().trim();
				int trajanjeTretm = 0;
				if (trajanjeTretmana != "") {
					try {
						trajanjeTretm = Integer.parseInt(trajanjeTretmana);
					} catch (Exception e1) {}
				} 
				double cenaTretm = 0;
				if (cenaTretmana != "") {
					try {
						cenaTretm = Double.parseDouble(cenaTretmana);
					} catch (Exception e1) {}
				} 
				ArrayList<KozmetickaUsluga> kus = new ArrayList<KozmetickaUsluga>();
				for (KozmetickaUsluga ku : kozmetickeUslugeMng.getKozmetickeUsluge()) {
					if (tipTretmana.equals("")) {
						if (trajanjeTretm == 0) {
							if (cenaTretm != 0) {
								if (ku.getCena() == cenaTretm) {
									kus.add(ku);
								}
							}
						}
					}
					if (tipTretmana.equals("")) {
						if (trajanjeTretm != 0) {
							if (cenaTretm == 0) {
								if (ku.getTrajanje() == trajanjeTretm) {
									kus.add(ku);
								}
							}
						}
					}
					if (!tipTretmana.equals("")) {
						if (trajanjeTretm == 0) {
							if (cenaTretm == 0) {
								int idTretm = ku.getIdTretmana();
								KozmetickiTretman kt = kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(idTretm);	
								String tip2 = kt.getNaziv();
								String tip = tip2.toLowerCase();
								if (tip.equals(tipTretmana)) {
									kus.add(ku);
								}
							}
						}
					}
					if (!tipTretmana.equals("")) {
						if (trajanjeTretm != 0) {
							if (cenaTretm == 0) {
								int idTretm = ku.getIdTretmana();
								KozmetickiTretman kt = kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(idTretm);	
								String tip2 = kt.getNaziv();
								String tip = tip2.toLowerCase();
								if (tip.equals(tipTretmana) && ku.getTrajanje() == trajanjeTretm) {
									kus.add(ku);
								}
							}
						}
					}
					if (!tipTretmana.equals("")) {
						if (trajanjeTretm == 0) {
							if (cenaTretm != 0) {
								int idTretm = ku.getIdTretmana();
								KozmetickiTretman kt = kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(idTretm);	
								String tip2 = kt.getNaziv();
								String tip = tip2.toLowerCase();
								if (tip.equals(tipTretmana) && ku.getCena() == cenaTretm) {
									kus.add(ku);
								}
							}
						}
					}
					if (tipTretmana.equals("")) {
						if (trajanjeTretm != 0) {
							if (cenaTretm != 0) {
								if (ku.getTrajanje() == trajanjeTretm && ku.getCena() == cenaTretm) {
									kus.add(ku);
								}
							}
						}
					}
					if (!tipTretmana.equals("")) {
						if (trajanjeTretm != 0) {
							if (cenaTretm != 0) {
								int idTretm = ku.getIdTretmana();
								KozmetickiTretman kt = kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(idTretm);	
								String tip2 = kt.getNaziv();
								String tip = tip2.toLowerCase();
								if (tip.equals(tipTretmana) && ku.getTrajanje() == trajanjeTretm && ku.getCena() == cenaTretm) {
									kus.add(ku);
								}
							}
						}
					}
				}
				int s = 1;
				if (tipTretmana.equals("") && trajanjeTretmana.equals("") && cenaTretmana.equals("")) {
					s = 0;
					if (kozmetickeUslugeMng.getKozmetickeUsluge().size() <= 3) {
						zakaziTretmanFrame.setSize(690, 480);
					} else {
						zakaziTretmanFrame.setSize(690, 600);
					}
					tretmaniPanel.setLayout(new GridLayout(0, 3, 25, 20));
					tretmaniPanel.removeAll();
					tretmaniPanel.validate();
					tretmaniPanel.repaint();
					jScrollPane.validate();
					jScrollPane.repaint();	
					for (KozmetickaUsluga ku : kozmetickeUslugeMng.getKozmetickeUsluge()) {
						JPanel box = new JPanel();
						Border blackline = BorderFactory.createLineBorder(Color.GRAY);
						box.setLayout(new MigLayout("fillx"));
						box.setPreferredSize(new Dimension(180, 154));
						box.setBorder(blackline);
						box.setBackground(new Color(250, 250, 250));
						JLabel uslugaLabel = new JLabel("<html> <p style = 'font-size: 11px; margin-top: 2px;'> " + ku.getNaziv() + " </p> </html>");
						box.add(uslugaLabel, centerSpanXWrapCC);
						String st2 = "-" + kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(ku.getIdTretmana()).getNaziv() + "-";
						JLabel tipLabel = new JLabel("<html> <p style = 'font-size: 8.8px; color: gray;'> " + st2 + " </p> </html>");
						box.add(tipLabel, centerSpanXWrapCC);
						JLabel trajanjeLabel = new JLabel("<html> <p style = 'font-size: 9px; margin-top: 5px;'> Trajanje: " + ku.getTrajanje() + "min </p> </html>");
						box.add(trajanjeLabel, centerSpanXWrapCC);
						JLabel cenaLabel = new JLabel("<html> <p style = 'font-size: 9px;'> Cena: " + ku.getCena() + "RSD </p> </html>");
						box.add(cenaLabel, centerSpanXWrapCC);
						JButton btnOk = new JButton("<html> <p style = 'font-size: 8px; font-weight: 500;'> IZABERI </p> </html>");
						btnOk.setPreferredSize(new Dimension(20, 20));
						btnOk.setMargin(new Insets(2, 3, 2, 3));
						btnOk.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								zakaziTretmanFrame.getContentPane().removeAll();
								zakaziTretmanFrame.validate();
								zakaziTretmanFrame.repaint();
								izaberiKozmeticara(ku);
								return;
							}
						});
						box.add(new JLabel(""), centerSpanXWrapCC);
						box.add(btnOk, centerSpanXWrapCC);
						tretmaniPanel.add(box);	
					}
					tretmaniPanel.validate();
					tretmaniPanel.repaint();
					jScrollPane.validate();
					jScrollPane.repaint();
					zakaziTretmanFrame.validate();
					zakaziTretmanFrame.repaint();
				}
				if (kus.size() == 0 && s == 1) {
					tretmaniPanel.removeAll();
					tretmaniPanel.add(new JLabel("Nema rezultata pretrage!"));
					tretmaniPanel.add(new JLabel(""));
					tretmaniPanel.add(new JLabel(""));
					tretmaniPanel.add(new JLabel(""));
					//tretmaniPanel.add(new JLabel(""));
					tretmaniPanel.validate();
					tretmaniPanel.repaint();
					jScrollPane.validate();
					jScrollPane.repaint();
					zakaziTretmanFrame.setSize(690, 380);
					zakaziTretmanFrame.validate();
					zakaziTretmanFrame.repaint();
				}
				if (kus.size() != 0) {
					tretmaniPanel.removeAll();
					if (kus.size() <= 3) {
						zakaziTretmanFrame.setSize(690, 480);
					} else {
						zakaziTretmanFrame.setSize(690, 600);
					}
					for (KozmetickaUsluga ku : kus) {
						JPanel box = new JPanel();
						Border blackline = BorderFactory.createLineBorder(Color.GRAY);
						box.setLayout(new MigLayout("fillx"));
						box.setPreferredSize(new Dimension(180, 154));
						box.setBorder(blackline);
						box.setBackground(new Color(250, 250, 250));
						JLabel uslugaLabel = new JLabel("<html> <p style = 'font-size: 11px; margin-top: 2px;'> " + ku.getNaziv() + " </p> </html>");
						box.add(uslugaLabel, centerSpanXWrapCC);
						String st2 = "-" + kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(ku.getIdTretmana()).getNaziv() + "-";
						JLabel tipLabel = new JLabel("<html> <p style = 'font-size: 8.8px; color: gray;'> " + st2 + " </p> </html>");
						box.add(tipLabel, centerSpanXWrapCC);
						JLabel trajanjeLabel = new JLabel("<html> <p style = 'font-size: 9px; margin-top: 5px;'> Trajanje: " + ku.getTrajanje() + "min </p> </html>");
						box.add(trajanjeLabel, centerSpanXWrapCC);
						JLabel cenaLabel = new JLabel("<html> <p style = 'font-size: 9px;'> Cena: " + ku.getCena() + "RSD </p> </html>");
						box.add(cenaLabel, centerSpanXWrapCC);
						JButton btnOk = new JButton("<html> <p style = 'font-size: 8px; font-weight: 500;'> IZABERI </p> </html>");
						btnOk.setPreferredSize(new Dimension(20, 20));
						btnOk.setMargin(new Insets(2, 3, 2, 3));
						btnOk.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								zakaziTretmanFrame.getContentPane().removeAll();
								zakaziTretmanFrame.validate();
								zakaziTretmanFrame.repaint();
								izaberiKozmeticara(ku);
								return;
							}
						});
						box.add(new JLabel(""), centerSpanXWrapCC);
						box.add(btnOk, centerSpanXWrapCC);
						tretmaniPanel.add(box);	
					}	
					tretmaniPanel.validate();
					tretmaniPanel.repaint();
					jScrollPane.validate();
					jScrollPane.repaint();
					if (kus.size() <= 3) {
						zakaziTretmanFrame.setSize(690, 480);
					} else {
						zakaziTretmanFrame.setSize(690, 580);
					}
					zakaziTretmanFrame.validate();
					zakaziTretmanFrame.repaint();
				}
					
			}
		});
		
		zakaziTretmanFrame.getRootPane().setDefaultButton(btnSearch);
		//zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(btnCancel, centerSpanXWrapCC);
		zakaziTretmanFrame.validate();
		zakaziTretmanFrame.repaint();
		
	}
	
	private void zakaziTretman() {
		izaberiUslugu();
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
