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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;

import guiFrames.KozmetickeUslugeCRUDFrame;
import guiTableModels.KozmetickeUslugeCRUDTableModel;
import manage.KozmetickiTretmaniManager;
import manage.ManageAll;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import treatments.KozmetickaUsluga;
import treatments.KozmetickiTretman;


public class KozmetickaUslugaFrameEdit extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private KozmetickiTretmaniManager kozmetickiTretmaniMng;
	private Boolean okNaziv;
	private Boolean okTretman;
	private KozmetickaUsluga ku;
	private KozmetickeUslugeCRUDFrame kozmetickeUslugeCRUDFrame;
	private JTable tabela;
	
	
	public KozmetickaUslugaFrameEdit(ManageAll manageAll, KozmetickeUslugeCRUDFrame kozmetickeUslugeCRUDFrame, KozmetickaUsluga ku, JTable tabela) {
		this.kozmetickiTretmaniMng = manageAll.getKozmetickiTretmaniMng();	
		this.okNaziv = true;
		this.okTretman = true;
		this.kozmetickeUslugeCRUDFrame = kozmetickeUslugeCRUDFrame;
		this.ku = ku;
		this.tabela = tabela;
		initialize();
	}
	
	
	private void initialize() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle(" EDIT USLUGA");
		setSize(410, 420);
		setResizable(false);
		setLayout(new MigLayout("fillx,  insets 20 50 20 50"));
		setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
            	((KozmetickeUslugeCRUDFrame) kozmetickeUslugeCRUDFrame).refreshData();
            	kozmetickeUslugeCRUDFrame.validate();
            	kozmetickeUslugeCRUDFrame.repaint();
            }
        });
		
		JLabel addMenadzerTekst = new JLabel("<html> <p style = 'font-size: 13px; font-weight: 500;'> EDIT USLUGA </p> </html>");
		addMenadzerTekst.setBackground(getBackground());
		JLabel dobrodosliLabel = new JLabel("<html> <p style = 'margin-bottom: 5px; font-size: 12px; font-weight: 400;'> Molimo unesite sledeće podatke. </p> </html>");
		dobrodosliLabel.setBackground(getBackground());
		
		Font font1 = new Font("Arial", Font.PLAIN, 14);	
		
		JLabel nazivLabel = new JLabel("Naziv:");
		nazivLabel.setFont(new Font("Arial", Font.BOLD, 14));
		nazivLabel.setBackground(getBackground());
		JLabel cenaLabel = new JLabel("Cena:");
		cenaLabel.setFont(new Font("Arial", Font.BOLD, 14));
		cenaLabel.setBackground(getBackground());
		JLabel trajanjeLabel = new JLabel("Trajanje:");
		trajanjeLabel.setFont(new Font("Arial", Font.BOLD, 14));
		trajanjeLabel.setBackground(getBackground());
		JLabel tretmanLabel = new JLabel("Tretman:");
		tretmanLabel.setFont(new Font("Arial", Font.BOLD, 14));
		tretmanLabel.setBackground(getBackground());
		
		JTextField nazivInput = new JTextField(20);
		nazivInput.setMargin(new Insets(2, 4, 2, 4));
		nazivInput.setFont(font1);
		nazivInput.setText(ku.getNaziv());
		JLabel nazivGreska = new JLabel("npr. sportka masaža");
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
					  nazivGreska.setText("'Naziv' sadrži više od 30 karaktera!");
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
		
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        NumberFormatter formatter = new NumberFormatter(decimalFormat);
        formatter.setValueClass(Double.class);
        formatter.setAllowsInvalid(false);
        formatter.setMinimum(0.0);
        JLabel cenaGreska = new JLabel("(RSD)");
        cenaGreska.setForeground(Color.GRAY);
        JFormattedTextField cenaInput = new JFormattedTextField(formatter);
        cenaInput.setColumns(20);
        cenaInput.setValue(ku.getCena());
		
        JLabel trajanjeGreska = new JLabel("10 - 100min");
        trajanjeGreska.setForeground(Color.GRAY);
        SpinnerModel spinnerModel = new SpinnerNumberModel(10, 10, 100, 5); // Initial value: 0, Minimum: 0, Maximum: 100, Step: 1
        JSpinner trajanjeInput = new JSpinner(spinnerModel);    
        trajanjeInput.setValue(ku.getTrajanje());
        
        JTextField tretmanInput = new JTextField(20);
        tretmanInput.setMargin(new Insets(2, 4, 2, 4));
        tretmanInput.setFont(font1);
        tretmanInput.setText(kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(ku.getIdTretmana()).getNaziv());
		JLabel tretmanGreska = new JLabel("npr. 'masaža'");
		tretmanGreska.setForeground(Color.GRAY);
		tretmanInput.getDocument().addDocumentListener(new DocumentListener() {
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
				  String tretman2 = tretmanInput.getText();
				  String tretman = tretman2.trim().toLowerCase();
				  Boolean ok2 = true;
				  for (int i = 0; i < tretman.length(); i++) {
					  char c = tretman.charAt(i);
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
					  if (a < 97 || a > 122) {
						  ok2 = false;
					  }
				  }
			      if (tretman.equals("")) {
			    	  tretmanGreska.setText("'Tretman' je prazno!");
			    	  tretmanGreska.setForeground(Color.RED);
			    	  tretmanGreska.setVisible(true);
			    	  okTretman = false;
			     	  return;
			      } else if (ok2 == false) {
			    	  tretmanGreska.setText("'Tretman' sadrži pogrešne karaktere!");
			    	  tretmanGreska.setForeground(Color.RED);
			    	  tretmanGreska.setVisible(true);
			    	  okTretman = false;
			     	  return;
			      }  else {
			    	  tretmanGreska.setVisible(false);
			    	  tretmanGreska.setForeground(Color.GRAY);
			    	  okTretman = true;
			      }
			      try {
		        	  int ima = 0;
		              for (KozmetickiTretman kt : kozmetickiTretmaniMng.getKozmetickiTretmani()) {
		            	  if (tretman.equals(kt.getNaziv())) {
		            		 ima = 1;
		            	  }
		              }
		              if (ima == 0) {
		            	  tretmanGreska.setText("'Tretman' ne postoji!");
		            	  tretmanGreska.setForeground(Color.RED);
		            	  tretmanGreska.setVisible(true);
				    	  okTretman = false;
				     	  return;
		              }           
		              tretmanGreska.setVisible(false);
		              tretmanGreska.setForeground(Color.GRAY);
			    	  okTretman = true;
			      } catch (Exception e) {
			    	  tretmanGreska.setText("'Tretman' nije u dobrom formatu!");
			    	  tretmanGreska.setForeground(Color.RED);
			    	  tretmanGreska.setVisible(true);
			    	  okTretman = false;
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
		CC leftWrapCC = new CC();
		leftWrapCC.alignX("left").wrap();
		
		add(addMenadzerTekst, centerSpanXWrapCC);
		add(dobrodosliLabel, centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		
		add(nazivGreska, centerSpanXWrapCC);
		add(nazivLabel, right);
		add(nazivInput, leftWrapCC);	
		
		add(cenaGreska, centerSpanXWrapCC);
		add(cenaLabel, right);
		add(cenaInput, leftWrapCC);
		
		add(trajanjeGreska, centerSpanXWrapCC);
		add(trajanjeLabel, right);
		add(trajanjeInput, leftWrapCC);
		
		add(tretmanGreska, centerSpanXWrapCC);
		add(tretmanLabel, right);
		add(tretmanInput, leftWrapCC);
		
		JButton btnOk = new JButton("DODAJ");
		btnOk.setMargin(new Insets(5, 9, 5, 9));
		btnOk.setPreferredSize(new Dimension(20, 30));
		getRootPane().setDefaultButton(btnOk);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (okNaziv == false || okTretman == false) {
					JOptionPane.showMessageDialog(null, "Molimo unesite pravilno podatke!");
					return;
				} else {
					String naziv = nazivInput.getText().trim();	
					double cena = (double) cenaInput.getValue();
					if (cena < 0) {
						cena = 0;
					}
					int trajanje = (int) trajanjeInput.getValue();
					if (trajanje < 10) {
						trajanje = 10;
					}
					if (trajanje > 90) {
						trajanje = 90;
					}
					String tretman = tretmanInput.getText();
					int id2 = -1;
					for (KozmetickiTretman kt2 : kozmetickiTretmaniMng.getKozmetickiTretmani()) {
						if (kt2.getNaziv().equals(tretman)) {
							id2 = kt2.getId();
						}
					}
					int id = ku.getId();
					KozmetickeUslugeCRUDTableModel sm = (KozmetickeUslugeCRUDTableModel) tabela.getModel();
					sm.edit(id, naziv, cena, trajanje, id2, false);
					JOptionPane.showMessageDialog(null, "USPEŠNO IZMENJENA USLUGA!");
					((KozmetickeUslugeCRUDFrame) kozmetickeUslugeCRUDFrame).refreshData();
					kozmetickeUslugeCRUDFrame.validate();
					kozmetickeUslugeCRUDFrame.repaint();
					dispose();
				}
			}
		});
		
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(btnOk, centerSpanXWrapCC);
		
		center(KozmetickaUslugaFrameEdit.this, 1);
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
	    component.setLocation(x, y);
	}

}
