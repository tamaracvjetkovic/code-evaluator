package guiFrames;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JOptionPane;
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
import guiTableModels.RasporedTableModel;
import manage.ManageAll;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import treatments.KozmetickiTretman;
import treatments.ZakazaniTretman;
import users.Kozmeticar;


public class KozmeticarFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private ManageAll manageAll;
	private String nazivSalona;
	private Kozmeticar k;
	private JTable tabela;
	
	
	public KozmeticarFrame(ManageAll manageAll, Kozmeticar k) {
		this.manageAll = manageAll;	
		this.nazivSalona = manageAll.getImeSalona();
		this.k = k;
		initialize();
	}
	
	
	private void initialize() {
		setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		setTitle(" " + nazivSalona + " - KOZMETIČARI MENI");
		setSize(450, 490);
		setResizable(false);
		setLayout(new MigLayout("fillx, insets 15 25 15 25"));
		setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());
		
		JLabel dobrodosliLabel = new JLabel("<html> <p style = 'margin-top: 5px; margin-bottom: 10px;'> Dobrodošli, " + k.getIme() + " " + k.getPrezime() + "! </html> </p>");
		JLabel podaciLabel = new JLabel("PODACI O KOZMETIČARU:");
		JLabel imeLabel = new JLabel("Ime: " + k.getIme());
		JLabel prezimeLabel = new JLabel("Prezime: " + k.getPrezime());
		JLabel polLabel = new JLabel("Pol: " + k.getPol());
		JLabel telefonLabel = new JLabel("Telefon: " + k.getTelefon());
		JLabel adresaLabel = new JLabel("Adresa: " + k.getAdresa());
		JLabel spremaLabel = new JLabel("Stručna sprema: " + k.getNivoStrucneSpreme());
		JLabel stazLabel = new JLabel("Staž: " + k.getStaz());
		String tret = "";
		int n = k.getKozmetickiTretmani().size();
		for (KozmetickiTretman kt : k.getKozmetickiTretmani()) {
			n--;
			tret += kt.getNaziv();
			if (n != 0) {
				tret += ", ";
			}	
		}
		JLabel tretmaniLabel = new JLabel("Tretmani: " + tret);

		dobrodosliLabel.setFont(new Font("Arial", Font.BOLD, 17));
		dobrodosliLabel.setBackground(getBackground());
		JButton btnRaspored = new JButton("RASPORED");
		btnRaspored.setMargin(new Insets(5, 9, 5, 9));
		btnRaspored.setPreferredSize(new Dimension(20, 30));
		getRootPane().setDefaultButton(btnRaspored);
		btnRaspored.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame rasporedFrame = new JFrame();
				rasporedFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				rasporedFrame.setTitle(" " + nazivSalona + " | " + k.getIme() + " " + k.getPrezime() + " - RASPORED");
				rasporedFrame.setSize(840, 430);
				rasporedFrame.setResizable(false);
				rasporedFrame.setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());		

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
					
		        JPanel tabelaPanel = (JPanel) getContentPane();
				tabelaPanel.setLayout(new BorderLayout());
				tabela = new JTable(new RasporedTableModel(manageAll, k));		
				tabela.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tabela.getTableHeader().setReorderingAllowed(false);
				TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
				JTextField tfSearch = new JTextField(20);
				tableSorter.setModel((AbstractTableModel) tabela.getModel());
				tabela.setRowSorter(tableSorter);
				JScrollPane sc = new JScrollPane(tabela);
				rasporedFrame.add(sc, BorderLayout.CENTER);
				
				DefaultTableCellRenderer centerRenderer = new CenterRenderer();
			    for (int i = 0; i < tabela.getColumnCount(); i++) {
			    	tabela.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			    }
			    
			    JButton btnIzvrsen = new JButton("IZVRŠI TRETMAN");
				btnIzvrsen.setPreferredSize(new Dimension(20, 20));
				btnIzvrsen.setMargin(new Insets(5, 9, 5, 9));
				btnIzvrsen.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e2) {
						if (tabela.getSelectedRow() != -1) {
							int row = tabela.getSelectedRow();
							int id = Integer.parseInt(tabela.getValueAt(row, 0).toString()) - 1;
							ZakazaniTretman zt2 = k.getRaspored().get(id);
							if (zt2 != null) {
								if (!zt2.getStanje().equals("ZAKAZAN")) {
									JOptionPane.showMessageDialog(null, "TRETMAN NIJE ZAKAZAN!", "GREŠKA", JOptionPane.WARNING_MESSAGE);
									return;
								} else {
									int rb = id + 1;
									int choice = JOptionPane.showConfirmDialog(null, "Potvrdite izvršavanje zakazanog tretmana sa rednim brojem " + rb + "'. ");
									if (choice == JOptionPane.YES_OPTION) {
										manageAll.izvrsiTretman(zt2);
										refreshData();
									} 
								}	
							} else {
								JOptionPane.showMessageDialog(null, "NIJE MOGUĆE PRONAĆI ZAKAZANI TRETMAN!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				});
				
				JPanel searchPanel = new JPanel();
				searchPanel.setLayout(new MigLayout("fillx, insets 20 20 15 20"));
				JPanel pSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));		
				pSearch.add(new JLabel("Search:"));
				pSearch.add(tfSearch);
				searchPanel.add(new JLabel(""), centerSpanXWrapCC);
				searchPanel.add(btnIzvrsen, centerSpanXWrapCC);
				searchPanel.add(pSearch, "wrap, span");
				searchPanel.add(new JLabel(""), centerSpanXWrapCC);
						
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
				rasporedFrame.add(searchPanel, BorderLayout.SOUTH);    
				
				center(rasporedFrame, 1);
				rasporedFrame.setVisible(true);	
			}
		});
		podaciLabel.setFont(new Font("Arial", Font.BOLD, 15));
		podaciLabel.setBackground(getBackground());
		imeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		imeLabel.setBackground(getBackground());
		prezimeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		prezimeLabel.setBackground(getBackground());
		polLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		polLabel.setBackground(getBackground());
		telefonLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		telefonLabel.setBackground(getBackground());
		adresaLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		adresaLabel.setBackground(getBackground());
		spremaLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		spremaLabel.setBackground(getBackground());
		stazLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		stazLabel.setBackground(getBackground());
		tretmaniLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		tretmaniLabel.setBackground(getBackground());
		 

		JButton btnLogOff = new JButton("Odloguj se");
		btnLogOff.setMargin(new Insets(5, 9, 5, 9));
		btnLogOff.setPreferredSize(new Dimension(20, 30));
		btnLogOff.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
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
		
		add(dobrodosliLabel, centerSpanXWrapCC);
		add(btnRaspored, centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(podaciLabel, leftWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(imeLabel, leftWrapCC);
		add(prezimeLabel, leftWrapCC);
		add(polLabel, leftWrapCC);
		add(telefonLabel, leftWrapCC);
		add(adresaLabel, leftWrapCC);
		add(spremaLabel, leftWrapCC);
		add(stazLabel, leftWrapCC);
		add(tretmaniLabel, leftWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(btnLogOff, centerSpanXWrapCC);
		
		
		center(KozmeticarFrame.this, 1);
		setVisible(true);
	}	
	
	
	public void refreshData() {
		RasporedTableModel sm = (RasporedTableModel)this.tabela.getModel();
		sm.fireTableDataChanged();
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
