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
	
	
	public LocalDateTime getDatumTermina() {
		return datumTermina;
	}
	public void setDatumTermina(LocalDateTime datumTermina) {
		this.datumTermina = datumTermina;
	}


	private void initialize() {
		zakaziTretmanFrame = new JFrame();
		zakaziTretmanFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		zakaziTretmanFrame.setTitle(" " + nazivSalona + " - ZAKAŽI TRETMAN");
		zakaziTretmanFrame.setSize(690, 580);
		zakaziTretmanFrame.setResizable(false);
		zakaziTretmanFrame.setLayout(new MigLayout("fillx,  insets 15 20 15 20"));
		zakaziTretmanFrame.setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());
		manageAll.checkKarticaLojalnosti(k);
		
		zakaziTretman();
		
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
		
		center(zakaziTretmanFrame, 1);
		zakaziTretmanFrame.setVisible(true);
	}
	
	
	
	private void zakaziTretman(KozmetickaUsluga ku, Kozmeticar kozm, LocalDateTime t) {
		zakaziTretmanFrame.setSize(530, 480);
		center(zakaziTretmanFrame, 1);
		zakaziTretmanFrame.validate();
		zakaziTretmanFrame.repaint();
		JLabel zakazivanjeLabel = new JLabel("<html> <p style = 'font-size: 14px; font-weight: 500;'> ZAKAZIVANJE TRETMANA </p> </html>");
		zakazivanjeLabel.setBackground(zakaziTretmanFrame.getBackground());
		JLabel dobrodosliLabel = new JLabel("<html> <p style = 'margin-bottom: 0px; font-size: 13px; font-weight: 400;'> 4. Molimo da potvrdite zakazivanje tretmana. </p> </html>");
		dobrodosliLabel.setBackground(zakaziTretmanFrame.getBackground());
		
		CC centerSpanXWrapCC = new CC();
		centerSpanXWrapCC.alignX("center").spanX().wrap();
		CC rightSplit2SpanXCC = new CC();
		rightSplit2SpanXCC.alignX("right").split(2).spanX();
		CC centerSplit3SpanXCC = new CC();
		centerSplit3SpanXCC.alignX("center").split(3).spanX();
		
		JLabel kuLabel = new JLabel("<html> <p style = 'margin-top: 15px; font-size: 14px; font-weight: 400;'> Naziv tretmana: " + ku.getNaziv() + " </p> </html>");
		kuLabel.setBackground(zakaziTretmanFrame.getBackground());
		JLabel kuCenaLabel = new JLabel("<html> <p style = 'font-size: 14px; font-weight: 400;'> Cena tretmana: " + ku.getCena() + " </p> </html>");
		kuCenaLabel.setBackground(zakaziTretmanFrame.getBackground());
		JLabel kuTrajanjeLabel = new JLabel("<html> <p style = 'font-size: 14px; font-weight: 400;'> Trajanje tretmana: " + ku.getTrajanje() + "min </p> </html>");
		kuTrajanjeLabel.setBackground(zakaziTretmanFrame.getBackground());
		JLabel kLabel = new JLabel("<html> <p style = 'font-size: 14px; font-weight: 400;'> Kozmetičar: " + kozm.getIme() + " " + kozm.getPrezime() + " </p> </html>");
		kLabel.setBackground(zakaziTretmanFrame.getBackground());
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy.  HH:mm");
		String formattedDate = format.format(t);
		JLabel tLabel = new JLabel("<html> <p style = 'font-size: 14px; font-weight: 400;'> Termin: " + formattedDate + "h </p> </html>");
		tLabel.setBackground(zakaziTretmanFrame.getBackground());
		
		zakaziTretmanFrame.add(zakazivanjeLabel, centerSpanXWrapCC);
		zakaziTretmanFrame.add(dobrodosliLabel, centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(kuLabel, centerSpanXWrapCC);
		zakaziTretmanFrame.add(kuCenaLabel, centerSpanXWrapCC);
		zakaziTretmanFrame.add(kuTrajanjeLabel, centerSpanXWrapCC);
		zakaziTretmanFrame.add(kLabel, centerSpanXWrapCC);
		zakaziTretmanFrame.add(tLabel, centerSpanXWrapCC);
		
		JButton btnOk = new JButton("<html> <p style = 'font-size: 9px; font-weight: 500;'> ZAKAŽI </p> </html>");
		JButton btnBack = new JButton("<html> <p style = 'font-size: 9px; font-weight: 500;'> NAZAD </p> </html>");
		btnOk.setPreferredSize(new Dimension(20, 30));
		btnOk.setMargin(new Insets(5, 9, 5, 9));
		btnBack.setPreferredSize(new Dimension(30, 30));
		btnBack.setMargin(new Insets(5, 9, 5, 9));
		zakaziTretmanFrame.getRootPane().setDefaultButton(btnOk);
				
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageAll.zakaziTretman(k, ku, kozm, t);
				zakaziTretmanFrame.getContentPane().removeAll();
				zakaziTretmanFrame.validate();
				zakaziTretmanFrame.repaint();
				izaberiTermin(ku, kozm);
				return;
			}
		});
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) {
				manageAll.zakaziTretman(k, ku, kozm, t);
				JOptionPane.showMessageDialog(null, "TRETMAN JE USPEŠNO ZAKAZAN!");	
				zakaziTretmanFrame.setVisible(false);
				zakaziTretmanFrame.dispose();
				if (opcija == 1) {
					KlijentFrame klijentFrame = new KlijentFrame(manageAll, k);
					klijentFrame.toString();
				} else {
					RecepcionerFrame recepcionerFrame = new RecepcionerFrame(manageAll, r);
					recepcionerFrame.toString();
				}
				
			}
		});
		
		JButton btnCancel = new JButton("<html> <p style = 'font-size: 9px; font-weight: 500;'> ODUSTANI </p> </html>");
		btnCancel.setPreferredSize(new Dimension(20, 30));
		btnCancel.setMargin(new Insets(5, 9, 5, 9));
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) {
				zakaziTretmanFrame.setVisible(false);
				zakaziTretmanFrame.dispose();
				KlijentFrame klijentFrame = new KlijentFrame(manageAll, k);
				klijentFrame.toString();
				return;
			}
		});
		
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(btnOk, centerSplit3SpanXCC);
		zakaziTretmanFrame.add(new JLabel(" "));
		zakaziTretmanFrame.add(btnBack, "wrap");
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(btnCancel, centerSpanXWrapCC);			
	}
	
	private void izaberiTermin(KozmetickaUsluga ku, Kozmeticar kozm) {	
		zakaziTretmanFrame.setSize(530, 330);
		center(zakaziTretmanFrame, 1);
		zakaziTretmanFrame.validate();
		zakaziTretmanFrame.repaint();
		JLabel zakazivanjeLabel = new JLabel("<html> <p style = 'font-size: 14px; font-weight: 500;'> ZAKAZIVANJE TRETMANA </p> </html>");
		zakazivanjeLabel.setBackground(zakaziTretmanFrame.getBackground());
		JLabel dobrodosliLabel = new JLabel("<html> <p style = 'margin-bottom: 0px; font-size: 13px; font-weight: 400;'> 3. Molimo izaberite datum, pa satnicu termina. </p> </html>");
		dobrodosliLabel.setBackground(zakaziTretmanFrame.getBackground());
		
		CC centerSpanXWrapCC = new CC();
		centerSpanXWrapCC.alignX("center").spanX().wrap();
		CC centerSplit3SpanXCC = new CC();
		centerSplit3SpanXCC.alignX("center").split(3).spanX();
			
		JPanel terminiPanel = new JPanel();
		terminiPanel.setLayout(new MigLayout("fillx, insets 20 20 15 20"));
	
		UtilDateModel dateModel = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.day", "Day");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
        JLabel dateLabel = new JLabel();
        dateLabel.setFont(new Font("Arial", Font.BOLD, 13));
		JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, p);
		datePanel.setPreferredSize(new Dimension(230, 180));
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, null);
		
		zakaziTretmanFrame.add(zakazivanjeLabel, centerSpanXWrapCC);
		zakaziTretmanFrame.add(dobrodosliLabel, centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(terminiPanel, centerSpanXWrapCC);
		terminiPanel.add(dateLabel, centerSpanXWrapCC);
		terminiPanel.add(datePicker, centerSpanXWrapCC);
		
		JPanel vremenaPanel = new JPanel();
		vremenaPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		vremenaPanel.setLayout(new GridLayout(0, 5, 10, 9));
		zakaziTretmanFrame.add(vremenaPanel, centerSpanXWrapCC);
		
		datePicker.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	LocalDateTime now = LocalDateTime.now();
		        Date selectedDate = (Date) datePicker.getModel().getValue();
		        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
		        String formattedDate = format.format(selectedDate);
		        LocalDateTime odabraniDatum = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		        LocalDateTime d1 = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), 0, 0);
		        d1 = d1.plusDays(1);
		        if (odabraniDatum.isBefore(d1)) {
		        	dateLabel.setForeground(Color.RED);
		        	dateLabel.setText("GREŠKA: datum iz prošlosti!");
		        	zakaziTretmanFrame.setSize(530, 350);
		        	center(zakaziTretmanFrame, 1);
		        	vremenaPanel.removeAll();
			        vremenaPanel.validate();
			        vremenaPanel.repaint();
			        zakaziTretmanFrame.validate();
					zakaziTretmanFrame.repaint();
		        	return;
		        }
		        dateLabel.setForeground(Color.BLACK);
		        dateLabel.setText("Izabrani datum: " + formattedDate);
		        ArrayList<LocalDateTime> vremena = new ArrayList<LocalDateTime>();		
				zakaziTretmanFrame.setSize(530, 580);
				center(zakaziTretmanFrame, 1);
				for (int i = radnoVreme[0]; i < radnoVreme[1]; i++) {
					vremena.add(LocalDateTime.of(odabraniDatum.getYear(), odabraniDatum.getMonthValue(), odabraniDatum.getDayOfMonth(), i, 0));
				}
				
				for (ZakazaniTretman zt : kozm.getRaspored()) {
					if (zt.getStanje().equals("ZAKAZAN")) {
						LocalDateTime zd = zt.getDatumVreme();
						if (zt.getTrajanje() > 60) {
							vremena.remove(zd.plusHours(1));
						}
						if (zt.getTrajanje() > 120) {
							vremena.remove(zd.plusHours(2));
						}
						vremena.remove(zd);
					}
				}
				
				ArrayList<LocalDateTime> ukloniti = new ArrayList<LocalDateTime>();
				for (LocalDateTime l : vremena) {
					int min = ku.getTrajanje();
					if (min > 60) {
						LocalDateTime s = LocalDateTime.of(l.getYear(), l.getMonthValue(), l.getDayOfMonth(), l.getHour() + 1, 0);
						if (!vremena.contains(s)) {
							ukloniti.add(l);
						}
					}
					if (min > 120) {
						LocalDateTime s = LocalDateTime.of(l.getYear(), l.getMonthValue(), l.getDayOfMonth(), l.getHour() + 1, 0);
						if (!vremena.contains(s)) {
							ukloniti.add(l);
						}
						LocalDateTime s2 = LocalDateTime.of(l.getYear(), l.getMonthValue(), l.getDayOfMonth(), l.getHour() + 2, 0);
						if (!vremena.contains(s2)) {
							ukloniti.add(l);
						}
					}		
				}
					
				for (LocalDateTime l : ukloniti) {	
					vremena.remove(l);
				}
				
			    vremenaPanel.removeAll();
		        vremenaPanel.validate();
		        vremenaPanel.repaint();
		        zakaziTretmanFrame.validate();
				zakaziTretmanFrame.repaint();
			    
				if (vremena.size() <= 15) {
					zakaziTretmanFrame.setSize(530, 590);
				}
				if (vremena.size() <= 10) {
					zakaziTretmanFrame.setSize(530, 500);
				}
				if (vremena.size() <= 5) {
					zakaziTretmanFrame.setSize(530, 400);
				}
				zakaziTretmanFrame.validate();
				zakaziTretmanFrame.repaint();
				center(zakaziTretmanFrame, 1);
				for (LocalDateTime ldt2 : vremena) {
					JPanel box = new JPanel();
					box.setLayout(new MigLayout("fillx"));
					box.setPreferredSize(new Dimension(60, 80));
					String tajm = "";
					if (ldt2.getHour() < 10) {
						tajm = "0" + ldt2.getHour();
					} else {
						tajm = "" + ldt2.getHour();
					}
					JLabel vremeLabel = new JLabel("<html> <p style = 'font-size: 11px; margin-top: 2px;'> " + tajm + ":00h" + " </p> </html>");
					box.add(vremeLabel, centerSpanXWrapCC);
					JButton btnOk = new JButton("<html> <p style = 'font-size: 8px; font-weight: 500;'> IZABERI </p> </html>");
					btnOk.setPreferredSize(new Dimension(20, 20));
					btnOk.setMargin(new Insets(2, 3, 2, 3));
					btnOk.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							zakaziTretmanFrame.getContentPane().removeAll();
							zakaziTretmanFrame.validate();
							zakaziTretmanFrame.repaint();
							zakaziTretman(ku, kozm, ldt2);
							return;
						}
					});
					box.add(new JLabel(""), centerSpanXWrapCC);
					box.add(btnOk, centerSpanXWrapCC);
					vremenaPanel.add(box);	
				}
		    }
		});
			
		JButton btnCancel = new JButton("<html> <p style = 'font-size: 9px; font-weight: 500;'> NAZAD </p> </html>");
		//btnCancel.setPreferredSize(new Dimension(20, 20));
		btnCancel.setPreferredSize(new Dimension(20, 30));
		btnCancel.setMargin(new Insets(5, 12, 5, 12));
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				zakaziTretmanFrame.getContentPane().removeAll();
				zakaziTretmanFrame.validate();
				zakaziTretmanFrame.repaint();
				izaberiKozmeticara(ku);
				return;
			}
		});
		
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(btnCancel, centerSpanXWrapCC);

		zakaziTretmanFrame.validate();
		zakaziTretmanFrame.repaint();
		
	}
	
	
	
	private void izaberiKozmeticara(KozmetickaUsluga ku) {
		JLabel zakazivanjeLabel = new JLabel("<html> <p style = 'font-size: 14px; font-weight: 500;'> ODABIR KOZMETIČARA </p> </html>");
		zakazivanjeLabel.setBackground(zakaziTretmanFrame.getBackground());
		JLabel dobrodosliLabel = new JLabel("<html> <p style = 'margin-bottom: 5px; font-size: 13px; font-weight: 400;'> 2. Molimo odaberite kozmetičara. </p> </html>");
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
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		
		JButton btnRandomKozm = new JButton("<html> <p style = 'font-size: 9px; font-weight: 500;'> RANDOM KOZMETIČAR </p> </html>");
		btnRandomKozm.setPreferredSize(new Dimension(160, 20));
		btnRandomKozm.setMargin(new Insets(6, 8, 6, 8));
		btnRandomKozm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Kozmeticar kozm = manageAll.randomKozmeticar(k, ku);
				zakaziTretmanFrame.getContentPane().removeAll();
				zakaziTretmanFrame.validate();
				zakaziTretmanFrame.repaint();
				izaberiTermin(ku, kozm);
				return;
			}
		});
			
		zakaziTretmanFrame.add(btnRandomKozm, centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		
		JPanel kozmeticarPanel = new JPanel();
		ArrayList<Kozmeticar> kozmeticariAll = new ArrayList<Kozmeticar>();
		for (Kozmeticar k : kozmeticariMng.getKozmeticari()) {
			if (k.getObrisan() == true) {
				continue;
			}
			for (KozmetickiTretman kt : k.getKozmetickiTretmani()) {
				if (kt.getId() == ku.getIdTretmana()) {
					kozmeticariAll.add(k);
				}
			}
			 
		}
		int n = kozmeticariAll.size();
		if (n > 6) {
			kozmeticarPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		} else {
			kozmeticarPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		}
		if (n <= 3) {
			zakaziTretmanFrame.setSize(690, 430);
		} else if (n <= 6) {
			zakaziTretmanFrame.setSize(690, 580);
		} else {
			zakaziTretmanFrame.setSize(690, 600);
		}
		center(zakaziTretmanFrame, 1);
		kozmeticarPanel.setLayout(new GridLayout(0, 3, 25, 20));
		JScrollPane jScrollPane = new JScrollPane(kozmeticarPanel);
		jScrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		zakaziTretmanFrame.add(jScrollPane, centerSpanXWrapCC);
		
		for (Kozmeticar kozm : kozmeticariAll) {
			JPanel box = new JPanel();
			Border blackline = BorderFactory.createLineBorder(Color.GRAY);
			box.setLayout(new MigLayout("fillx"));
			box.setPreferredSize(new Dimension(180, 120));
			box.setBorder(blackline);
			box.setBackground(new Color(250, 250, 250));
			JLabel kkk = new JLabel("<html> <p style = 'margin-top: 5px; font-size: 9px;'> Kozmetičar: </p> </html>");
			box.add(kkk, centerSpanXWrapCC);
			JLabel kozmeticarLabel = new JLabel("<html> <p style = 'margin-top: 5px; font-size: 11px;'> " + kozm.getIme() + " " + kozm.getPrezime() + " </p> </html>");
			box.add(kozmeticarLabel, centerSpanXWrapCC);
			
			JButton btnOk = new JButton("<html> <p style = 'font-size: 8px; font-weight: 500;'> IZABERI </p> </html>");
			btnOk.setPreferredSize(new Dimension(20, 20));
			btnOk.setMargin(new Insets(2, 3, 2, 3));
			btnOk.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					zakaziTretmanFrame.getContentPane().removeAll();
					zakaziTretmanFrame.validate();
					zakaziTretmanFrame.repaint();
					izaberiTermin(ku, kozm);
					return;
				}
			});
			box.add(new JLabel(""), centerSpanXWrapCC);
			box.add(btnOk, centerSpanXWrapCC);
			kozmeticarPanel.add(box);	
		}
		
		JButton btnCancel = new JButton("<html> <p style = 'font-size: 9px; font-weight: 500;'> NAZAD </p> </html>");
		//btnRandomKozm.setPreferredSize(new Dimension(160, 20));
		btnCancel.setMargin(new Insets(4, 8, 4, 8));
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				zakaziTretmanFrame.getContentPane().removeAll();
				zakaziTretmanFrame.validate();
				zakaziTretmanFrame.repaint();
				izaberiUslugu();
				return;
			}
		});
		
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(new JLabel(""), centerSpanXWrapCC);
		zakaziTretmanFrame.add(btnCancel, centerSpanXWrapCC);
		zakaziTretmanFrame.validate();
		zakaziTretmanFrame.repaint();	
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
