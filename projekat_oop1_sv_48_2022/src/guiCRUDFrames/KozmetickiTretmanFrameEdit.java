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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import guiFrames.KozmetickiTretmaniCRUDFrame;
import guiTableModels.KozmetickiTretmaniCRUDTableModel;
import manage.KozmetickiTretmaniManager;
import manage.ManageAll;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import treatments.KozmetickiTretman;


public class KozmetickiTretmanFrameEdit extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private KozmetickiTretmaniManager kozmetickiTretmaniMng;
	private Boolean okNaziv;
	private KozmetickiTretmaniCRUDFrame kozmetickiTretmaniCRUDFrame;
	private KozmetickiTretman kt;
	private JTable tabela;
	
	
	public KozmetickiTretmanFrameEdit(ManageAll manageAll, KozmetickiTretmaniCRUDFrame kozmetickiTretmaniCRUDFrame, KozmetickiTretman kt, JTable tabela) {
		this.kozmetickiTretmaniMng = manageAll.getKozmetickiTretmaniMng();	
		this.okNaziv = true;
		this.kozmetickiTretmaniCRUDFrame = kozmetickiTretmaniCRUDFrame;
		this.kt = kt;
		this.tabela = tabela;
		initialize();
	}
	
	
	private void initialize() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle(" EDIT TRETMAN");
		setSize(410, 260);
		setResizable(false);
		setLayout(new MigLayout("fillx,  insets 20 50 20 50"));
		setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
            	((KozmetickiTretmaniCRUDFrame) kozmetickiTretmaniCRUDFrame).refreshData();
            	kozmetickiTretmaniCRUDFrame.validate();
            	kozmetickiTretmaniCRUDFrame.repaint();
            }
        });
		
		JLabel addMenadzerTekst = new JLabel("<html> <p style = 'font-size: 13px; font-weight: 500;'> EDIT TRETMAN </p> </html>");
		addMenadzerTekst.setBackground(getBackground());
		JLabel dobrodosliLabel = new JLabel("<html> <p style = 'margin-bottom: 5px; font-size: 12px; font-weight: 400;'> Molimo unesite sledeće podatke. </p> </html>");
		dobrodosliLabel.setBackground(getBackground());
		
		JLabel nazivLabel = new JLabel("Naziv:");
		nazivLabel.setFont(new Font("Arial", Font.BOLD, 14));
		nazivLabel.setBackground(getBackground());
	
		Font font1 = new Font("Arial", Font.PLAIN, 14);	
		
		JTextField nazivInput = new JTextField(20);
		nazivInput.setMargin(new Insets(2, 4, 2, 4));
		nazivInput.setFont(font1);
		nazivInput.setText(kt.getNaziv());
		JLabel nazivGreska = new JLabel("npr. masaža");
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
			      for (KozmetickiTretman kt : kozmetickiTretmaniMng.getKozmetickiTretmani()) {
			    	  if (naziv.equals(kt.getNaziv())) {
			    		  nazivGreska.setText("'Naziv' je zauzet!");
				    	  nazivGreska.setForeground(Color.RED);
				    	  nazivGreska.setVisible(true);
				    	  okNaziv = false;
				     	  return;
			    	  }
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
					int id = kt.getId();	
					KozmetickiTretmaniCRUDTableModel sm = (KozmetickiTretmaniCRUDTableModel) tabela.getModel();
					sm.edit(id, naziv);				
					JOptionPane.showMessageDialog(null, "USPEŠNO IZMENJEN TRETMAN!");
					((KozmetickiTretmaniCRUDFrame) kozmetickiTretmaniCRUDFrame).refreshData();
					kozmetickiTretmaniCRUDFrame.validate();
					kozmetickiTretmaniCRUDFrame.repaint();
					dispose();
				}
			}
		});
		
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(btnOk, centerSpanXWrapCC);
		
		center(KozmetickiTretmanFrameEdit.this, 1);
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
