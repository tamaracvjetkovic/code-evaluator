package guiFrames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import javax.swing.text.NumberFormatter;

import guiTableModels.CenterRenderer;
import guiTableModels.KlijentiCRUDTableModel;
import manage.ManageAll;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import users.Menadzer;


public class SalonCRUDFrame extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private ManageAll manageAll;
	private String nazivSalona;
	private Menadzer m;
	private Boolean okNaziv;
	private int[] radnoVreme;
	
	
	public SalonCRUDFrame(ManageAll manageAll, Menadzer m) {
		this.manageAll = manageAll;
		this.nazivSalona = manageAll.getImeSalona();
		this.radnoVreme = manageAll.getRadnoVreme();
		this.m = m;
		this.okNaziv = true;
		initialize();
	}
	
	
	private void initialize() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle(" " + nazivSalona + " | " + m.getIme() + " " + m.getPrezime() + " - SALON OPCIJE");
		setSize(720, 550);
		setLayout(new MigLayout("fillx,  insets 20 50 20 50"));
		setResizable(false);
		setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());
		
		JLabel addMenadzerTekst = new JLabel("<html> <p style = 'font-size: 13px; font-weight: 500;'> SALON OPCIJE </p> </html>");
		addMenadzerTekst.setBackground(getBackground());
		JLabel dobrodosliLabel = new JLabel("<html> <p style = 'margin-bottom: 0px; font-size: 12px; font-weight: 400;'> Molimo unesite sledeće podatke. </p> </html>");
		dobrodosliLabel.setBackground(getBackground());
		
		JLabel nazivLabel = new JLabel("Naziv:");
		nazivLabel.setFont(new Font("Arial", Font.BOLD, 14));
		nazivLabel.setBackground(getBackground());
		JLabel radnoVremeLabel = new JLabel("Radno vreme:");
		nazivLabel.setFont(new Font("Arial", Font.BOLD, 14));
		nazivLabel.setBackground(getBackground());
		
		JLabel karticaLojalnostiLabel = new JLabel("Uslov kartice lojalnosti (RSD):");
		karticaLojalnostiLabel.setFont(new Font("Arial", Font.BOLD, 14));
		karticaLojalnostiLabel.setBackground(getBackground());
		
		CC centerSpanXWrapCC = new CC();
		centerSpanXWrapCC.alignX("center").spanX().wrap();
		CC rightSplit2SpanXCC = new CC();
		rightSplit2SpanXCC.alignX("right").split(2).spanX();
		CC centerSplit3SpanXCC = new CC();
		centerSplit3SpanXCC.alignX("center").split(3).spanX();
		CC right = new CC();
		right.alignX("right");
		CC left = new CC();
		left.alignX("left");
		CC leftWrapCC = new CC();
		leftWrapCC.alignX("left").wrap();
		
		add(addMenadzerTekst, centerSpanXWrapCC);
		add(dobrodosliLabel, centerSpanXWrapCC);
		
		Font font1 = new Font("Arial", Font.PLAIN, 14);	
		
		JTextField nazivInput = new JTextField(20);
		nazivInput.setMargin(new Insets(2, 4, 2, 4));
		nazivInput.setFont(font1);
		nazivInput.setText(nazivSalona);
		JLabel nazivGreska = new JLabel("npr. 'Moj Salon'");
		nazivGreska.setForeground(Color.GRAY);
		nazivInput.getDocument().addDocumentListener(new DocumentListener() {
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
				  String naziv2 = nazivInput.getText();
				  if (naziv2.length() > 30) {
					  nazivInput.setText("'Ime' sadrži više od 30 karaktera!");
					  nazivGreska.setForeground(Color.RED);
					  nazivGreska.setVisible(true);
					  okNaziv = false;
			     	  return;
				  }
				  String naziv = naziv2.trim().toLowerCase();
				  Boolean ok2 = true;
				  int spaces = 0;
				  for (int i = 0; i < naziv.length(); i++) {
					  char c = naziv.charAt(i);
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
					  if (a >= 48 || a <= 57) {
						  continue;
					  }
					  if (a < 97 || a > 122) {
						  ok2 = false;
					  }		  
				  }
			      if (naziv.equals("")) {
			    	  nazivGreska.setText("'Naziv' je prazno!");
			    	  nazivGreska.setForeground(Color.RED);
			    	  nazivGreska.setVisible(true);
			    	  okNaziv = false;
			     	  return;
			      } else if (ok2 == false) {
			    	  nazivGreska.setText("'Naziv' sadrži pogrešne karaktere!");
			    	  nazivGreska.setForeground(Color.RED);
			    	  nazivGreska.setVisible(true);
			    	  okNaziv = false;
			     	  return;
			      } else if (spaces > 1) {
			    	  nazivGreska.setText("'Naziv' sadrži više spejsova!");
			    	  nazivGreska.setForeground(Color.RED);
			    	  nazivGreska.setVisible(true);
			    	  okNaziv = false;
			     	  return;
			      } else {
			    	  nazivGreska.setVisible(false);
			    	  nazivGreska.setForeground(Color.GRAY);
			    	  okNaziv = true;
			      }
			  }
		});
		JPanel nazivSalona = new JPanel();
		nazivSalona.setLayout(new MigLayout("fillx,  insets 20 20 20 20"));
		nazivSalona.add(nazivGreska, centerSpanXWrapCC);
		nazivSalona.add(nazivLabel, right);
		nazivSalona.add(nazivInput, leftWrapCC);
		
        SpinnerModel spinnerModel1 = new SpinnerNumberModel(radnoVreme[0], 7, 10, 1); // Initial value: 0, Minimum: 0, Maximum: 100, Step: 1
        JSpinner rv1Input = new JSpinner(spinnerModel1);    
        rv1Input.setValue(radnoVreme[0]);
        SpinnerModel spinnerModel2 = new SpinnerNumberModel(radnoVreme[1], 19, 22, 1); // Initial value: 0, Minimum: 0, Maximum: 100, Step: 1
        JSpinner rv2Input = new JSpinner(spinnerModel2);    
        rv2Input.setValue(radnoVreme[1]);
        add(new JLabel(""), centerSpanXWrapCC);
        JPanel radnoVreme = new JPanel();
        radnoVreme.setLayout(new MigLayout("fillx,  insets 20 20 20 20"));
        JLabel radnoVremeGreska = new JLabel("7 - 22h");
        radnoVremeGreska.setForeground(Color.GRAY);
        radnoVreme.add(radnoVremeGreska, centerSpanXWrapCC);
        radnoVreme.add(radnoVremeLabel);
        radnoVreme.add(rv1Input);
        radnoVreme.add(new JLabel(" - "));
        radnoVreme.add(rv2Input);
        add(nazivSalona, right);
		add(radnoVreme, leftWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		
		JLabel bonusLabel = new JLabel("<html> <p style = 'font-size: 12px; font-weight: 500;'> Postavite uslove za bonus (mesečni uslovi): </p> </html>");
		bonusLabel.setFont(new Font("Arial", Font.BOLD, 14));
		bonusLabel.setBackground(getBackground());
		JLabel brIzvrsenihLabel = new JLabel("Br. izvršenih tretmana:");
		brIzvrsenihLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		brIzvrsenihLabel.setBackground(getBackground());
		JLabel prihodLabel = new JLabel("Mesečni prihod (RSD):");
		prihodLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		prihodLabel.setBackground(getBackground());
		JLabel cifraBonusLabel = new JLabel("Vrednost bonusa (RSD):");
		cifraBonusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		cifraBonusLabel.setBackground(getBackground());
		
		radnoVreme.add(new JLabel(""), centerSpanXWrapCC);
		add(bonusLabel, centerSpanXWrapCC);
		radnoVreme.add(new JLabel(""), centerSpanXWrapCC);
		
		DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        NumberFormat formatter = new DecimalFormat("#,##0");
        formatter.setMinimumFractionDigits(0);
        formatter.setParseIntegerOnly(true);
        NumberFormatter numberFormatter = new NumberFormatter(decimalFormat);
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setAllowsInvalid(false);
        numberFormatter.setMinimum(0);
        JFormattedTextField brIzvrsenihPragInput = new JFormattedTextField(numberFormatter);
        brIzvrsenihPragInput.setColumns(20);
        brIzvrsenihPragInput.setMargin(new Insets(2, 4, 2, 4));
        brIzvrsenihPragInput.setValue(manageAll.getBonusUslovBrIzvrsenih());
               
        DecimalFormat decimalFormat2 = new DecimalFormat("#,##0.00");
        NumberFormatter formatter2 = new NumberFormatter(decimalFormat2);
        formatter2.setValueClass(Double.class);
        formatter2.setAllowsInvalid(false);
        formatter2.setMinimum(0.0);
        JFormattedTextField zaradjenoPragInput = new JFormattedTextField(formatter2);
        zaradjenoPragInput.setColumns(20);
        zaradjenoPragInput.setMargin(new Insets(2, 4, 2, 4));
        zaradjenoPragInput.setValue(manageAll.getBonusUslovZarada());
        
        JPanel brIzvrsenih = new JPanel();
        brIzvrsenih.setLayout(new MigLayout("fillx,  insets 20 20 20 20"));
        brIzvrsenih.add(brIzvrsenihLabel, centerSpanXWrapCC);
        brIzvrsenih.add(brIzvrsenihPragInput, centerSpanXWrapCC);
        add(brIzvrsenih, centerSplit3SpanXCC);
		
        JPanel prihod = new JPanel();
        prihod.setLayout(new MigLayout("fillx,  insets 20 20 20 20"));
        prihod.add(prihodLabel, centerSpanXWrapCC);
        prihod.add(zaradjenoPragInput, centerSpanXWrapCC);
        add(prihod);
        
        JFormattedTextField bonusInput = new JFormattedTextField(formatter2);
        bonusInput.setColumns(20);
        bonusInput.setMargin(new Insets(2, 4, 2, 4));
        bonusInput.setValue(manageAll.getBonusVr());
        
        
        JPanel bonus = new JPanel();
        bonus.setLayout(new MigLayout("fillx,  insets 20 20 20 20"));
        bonus.add(cifraBonusLabel, centerSpanXWrapCC);
        bonus.add(bonusInput, centerSpanXWrapCC);
        add(bonus, "wrap");
        
        JFormattedTextField karticaInput = new JFormattedTextField(formatter2);
        karticaInput.setColumns(20);
        karticaInput.setMargin(new Insets(2, 4, 2, 4));
        karticaInput.setValue(manageAll.getVrKarticeLojalnosti());
        
        add(new JLabel(""), centerSpanXWrapCC);
        add(new JLabel(""), centerSpanXWrapCC);
        add(new JLabel(""), centerSpanXWrapCC);
        add(karticaLojalnostiLabel, right);
        add(karticaInput, leftWrapCC);
        add(new JLabel(""), centerSpanXWrapCC);
        
        JButton btnSpisakKartica = new JButton("VIDI KLIJENTE SA KARTICOM");
        btnSpisakKartica.setMargin(new Insets(5, 9, 5, 9));
        btnSpisakKartica.setPreferredSize(new Dimension(20, 30));
		btnSpisakKartica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JFrame klijenti = new JFrame();
				klijenti.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				klijenti.setTitle(" " + nazivSalona + " - KLIJENT SA KARTICOM LOJALNOSTI");
				klijenti.setSize(750, 510);
				klijenti.setResizable(false);
				klijenti.setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());
				JPanel tabelaPanel = (JPanel) getContentPane();
				tabelaPanel.setLayout(new BorderLayout());
				JTable tabela = new JTable(new KlijentiCRUDTableModel(manageAll, 1));		
				tabela.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tabela.getTableHeader().setReorderingAllowed(false);
				TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
				tableSorter.setModel((AbstractTableModel) tabela.getModel());
				tabela.setRowSorter(tableSorter);
				JScrollPane sc = new JScrollPane(tabela);
				
				DefaultTableCellRenderer centerRenderer = new CenterRenderer();
			    for (int i = 0; i < tabela.getColumnCount(); i++) {
			    	tabela.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			    }
			    	
			    klijenti.add(sc, BorderLayout.CENTER);
			    
			    JPanel searchPanel =  new JPanel();
			    searchPanel.setLayout(new MigLayout("fillx, insets 15 25 15 25"));
			    JTextField tfSearch = new JTextField(20);
			    JPanel pSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));		
				pSearch.add(new JLabel("Search:"));
				pSearch.add(tfSearch);
				searchPanel.add(new JLabel(""), centerSpanXWrapCC);
				searchPanel.add(pSearch, "wrap");
				searchPanel.add(new JLabel(""), centerSpanXWrapCC);
				
				klijenti.add(searchPanel, BorderLayout.SOUTH);
				tfSearch.getDocument().addDocumentListener(new DocumentListener() {	
					@Override
					public void removeUpdate(DocumentEvent e) {
						changedUpdate(e);
					}	
					@Override
					public void insertUpdate(DocumentEvent e) {
						changedUpdate(e);
					}	
					@Override
					public void changedUpdate(DocumentEvent e) {
						if (tfSearch.getText().trim().length() == 0) {
						     tableSorter.setRowFilter(null);
						  } else {
							  tableSorter.setRowFilter(RowFilter.regexFilter("(?i)" + tfSearch.getText().trim()));
						  }
					}
				});
				
			    center(klijenti, 1);
			    klijenti.setVisible(true);
			}
		});
		add(btnSpisakKartica, centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		
		JButton btnOk = new JButton("IZMENI");
		btnOk.setMargin(new Insets(5, 9, 5, 9));
		btnOk.setPreferredSize(new Dimension(20, 30));
		getRootPane().setDefaultButton(btnOk);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (okNaziv == false) {
					JOptionPane.showMessageDialog(null, "Molimo unesite pravilno podatke!");
					return;
				} else {
					String naziv = nazivInput.getText().trim();
					int radnoVreme1 = (int) rv1Input.getValue();
					int radnoVreme2 = (int) rv2Input.getValue();
					if (radnoVreme1 < 7) {
						radnoVreme1 = 7;
					}
					if (radnoVreme1 > 10) {
						radnoVreme1 = 10;
					}
					if (radnoVreme2 > 22) {
						radnoVreme2 = 22;
					}
					if (radnoVreme2 < 19) {
						radnoVreme2 = 19;
					}
					int brIzvrsenihPrag = (int) brIzvrsenihPragInput.getValue();
					if (brIzvrsenihPrag < 0) {
						brIzvrsenihPrag = 0;
					}
					double zaradjenoPrag = (double) zaradjenoPragInput.getValue();
					if (zaradjenoPrag < 0) {
						zaradjenoPrag = 0;
					}
					double bonus = (double) bonusInput.getValue();
					if (bonus < 0) {
						bonus = 0;
					}
					double karticaLo = (double) karticaInput.getValue();
					if (karticaLo < 0) {
						karticaLo = 0;
					}	
					int[] r = {radnoVreme1, radnoVreme2};
					manageAll.setRadnoVreme(r);
					manageAll.setImeSalona(naziv);
					manageAll.setBonusUslovBrIzvrsenih(brIzvrsenihPrag);
					manageAll.setBonusUslovZarada(zaradjenoPrag);
					manageAll.setBonusVr(bonus);
					manageAll.setVrKarticeLojalnosti(karticaLo);
					manageAll.dodeliKartice();				
					manageAll.postaviBonus(brIzvrsenihPrag, zaradjenoPrag, bonus);
					manageAll.saveData();
					JOptionPane.showMessageDialog(null, "USPEŠNO IZMENJENI PODACI!");
				}
			}
		});
		
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(btnOk, centerSpanXWrapCC);
		
		center(SalonCRUDFrame.this, 1);
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
