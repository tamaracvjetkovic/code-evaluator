package guiFrames;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

import guiTableModels.CenterRenderer;
import guiTableModels.ZakazaniTretmaniKlijentTableModel;
import manage.ManageAll;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import users.Klijent;


public class KlijentZakazaniTretmaniFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private ManageAll manageAll;
	private String nazivSalona;
	private Klijent k;
	private JLabel karticaLojalnostiLabel;
	private JLabel stanjeLabel;
	private JFrame klijentFrame;
	
	
	public KlijentZakazaniTretmaniFrame(ManageAll manageAll, Klijent k, JFrame klijentFrame, JLabel karticaLojalnostiLabel, JLabel stanjeLabel) {
		this.manageAll = manageAll;
		manageAll.getMenadzeriMng();
		manageAll.getKlijentiMng();
		manageAll.getKozmeticariMng();
		manageAll.getRecepcionariMng();		
		this.nazivSalona = manageAll.getImeSalona();
		this.k = k;
		this.klijentFrame = klijentFrame;
		this.karticaLojalnostiLabel = karticaLojalnostiLabel;
		this.stanjeLabel = stanjeLabel;
		initialize();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle(" " + nazivSalona + " | " + k.getIme() + " " + k.getPrezime() + " - ZAKAZANI TRETMANI MENI");
		setSize(850, 510);
		setResizable(false);
		setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
            	manageAll.checkKarticaLojalnosti(k);
            	String kl = "";
        		if (k.getKarticaLojalnosti() == true) {
        			kl += "IMA";
        		} else {
        			kl += "NEMA";
        		}
        		karticaLojalnostiLabel.setText("Kartica lojalnosti: " + kl);
        		double potroseno = manageAll.potrosnjaKlijenta(k);
        		stanjeLabel.setText("Stanje na kartici: " + potroseno);
            	klijentFrame.validate();
            	klijentFrame.repaint();	
            }
        });
		
		JPanel tabelaPanel = (JPanel) getContentPane();
		tabelaPanel.setLayout(new BorderLayout());
		JTable tabela = new JTable(new ZakazaniTretmaniKlijentTableModel(manageAll, k));		
		tabela.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabela.getTableHeader().setReorderingAllowed(false);
		TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
		JTextField tfSearch = new JTextField(20);
		tableSorter.setModel((AbstractTableModel) tabela.getModel());
		tabela.setRowSorter(tableSorter);
		JScrollPane sc = new JScrollPane(tabela);
		add(sc, BorderLayout.CENTER);
		
		DefaultTableCellRenderer centerRenderer = new CenterRenderer();
	    for (int i = 0; i < tabela.getColumnCount(); i++) {
	    	tabela.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	    }
	    
	    CC centerSpanXWrapCC = new CC();
		centerSpanXWrapCC.alignX("center").spanX().wrap();
		
	    JPanel searchPanel = new JPanel();
	    searchPanel.setLayout(new MigLayout("fillx, insets 20 20 15 20"));
	    JPanel pSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));		
		pSearch.add(new JLabel("Search:"));
		pSearch.add(tfSearch);
		searchPanel.add(pSearch, "wrap, span");
	
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
				//System.out.println("~ "+tfSearch.getText());
				if (tfSearch.getText().trim().length() == 0) {
				     tableSorter.setRowFilter(null);
				  } else {
					  tableSorter.setRowFilter(RowFilter.regexFilter("(?i)" + tfSearch.getText().trim()));
				  }
			}
		});
		
		JPanel otkaziPanel = new JPanel();
		otkaziPanel.setLayout(new MigLayout("fillx, insets 20 20 15 20"));
		
		JLabel trosakLabel = new JLabel("<html> <p style = 'font-size: 13px;'> Ukupni trošak: " + manageAll.potrosnjaKlijenta(k) + " </html> </p>");
		otkaziPanel.add(trosakLabel, centerSpanXWrapCC);
		otkaziPanel.add(new JLabel(""), centerSpanXWrapCC);
		otkaziPanel.add(new JLabel(""), centerSpanXWrapCC);
		otkaziPanel.add(new JLabel(""), centerSpanXWrapCC);
		
		JButton btnOtkazi = new JButton("<html> <p style = 'font-size: 9px; font-weight: 500;'> OTKAŽI TRETMAN </p> </html>");
		btnOtkazi.setPreferredSize(new Dimension(130, 30));
		btnOtkazi.setMargin(new Insets(5, 9, 5, 9));
		btnOtkazi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) {
				if (tabela.getSelectedRow() != -1) {
					ZakazaniTretmaniKlijentTableModel mdl = (ZakazaniTretmaniKlijentTableModel) tabela.getModel();
					int viewRow = tabela.getSelectedRow();
					int modelRow = tabela.convertRowIndexToModel(viewRow);
					mdl.otkaziZakazaniTretman(modelRow);
					trosakLabel.setText("<html> <p style = 'font-size: 13px;'> Ukupni trošak: " + manageAll.potrosnjaKlijenta(k) + " </html> </p>");
					tabelaPanel.validate();
					tabelaPanel.repaint();		
					manageAll.checkKarticaLojalnosti(k);
	            	String kl = "";
	        		if (k.getKarticaLojalnosti() == true) {
	        			kl += "IMA";
	        		} else {
	        			kl += "NEMA";
	        		}
	        		karticaLojalnostiLabel.setText("Kartica lojalnosti: " + kl);
	        		double potroseno = manageAll.potrosnjaKlijenta(k);
	        		stanjeLabel.setText("Stanje na kartici: " + potroseno);
	            	klijentFrame.validate();
	            	klijentFrame.repaint();	
				}
			}
		});
		
		otkaziPanel.add(btnOtkazi, centerSpanXWrapCC);
		otkaziPanel.add(searchPanel, "wrap");
		tabelaPanel.add(otkaziPanel, BorderLayout.SOUTH);

		center(KlijentZakazaniTretmaniFrame.this, 1);
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
