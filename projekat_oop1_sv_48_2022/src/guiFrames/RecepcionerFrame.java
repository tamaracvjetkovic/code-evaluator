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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
import guiTableModels.KlijentiCRUDTableModel;
import guiTableModels.RasporedTableModel;
import manage.KlijentiManager;
import manage.ManageAll;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import users.Klijent;
import users.Recepcioner;


public class RecepcionerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private ManageAll manageAll;
	private KlijentiManager klijentiMng;
	private String nazivSalona;
	private Recepcioner r;
	private JTable tabela;
	
	
	public RecepcionerFrame(ManageAll manageAll, Recepcioner r) {
		this.manageAll = manageAll;	
		this.klijentiMng = manageAll.getKlijentiMng();
		this.nazivSalona = manageAll.getImeSalona();
		this.r = r;
		initialize();
	}
	
	
	private void initialize() {
		setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		setTitle(" " + nazivSalona + " - RECEPCIONER MENI");
		setSize(450, 480);
		setResizable(false);
		setLayout(new MigLayout("fillx, insets 15 25 15 25"));
		setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());
		
		JLabel dobrodosliLabel = new JLabel("<html> <p style = 'margin-top: 5px; margin-bottom: 10px;'> Dobrodošli, " + r.getIme() + " " + r.getPrezime() + "! </html> </p>");
		JLabel podaciLabel = new JLabel("PODACI O RECEPCIONERU:");
		JLabel imeLabel = new JLabel("Ime: " + r.getIme());
		JLabel prezimeLabel = new JLabel("Prezime: " + r.getPrezime());
		JLabel polLabel = new JLabel("Pol: " + r.getPol());
		JLabel telefonLabel = new JLabel("Telefon: " + r.getTelefon());
		JLabel adresaLabel = new JLabel("Adresa: " + r.getAdresa());
		JLabel spremaLabel = new JLabel("Stručna sprema: " + r.getNivoStrucneSpreme());
		JLabel stazLabel = new JLabel("Staž: " + r.getStaz());

		JMenuBar meni = new JMenuBar();
		JMenu meniRecepcioner = new JMenu("Meni");
		JMenuItem zakazaniTretmaniMeni = new JMenuItem("Prikaži zakazane tretmane");
		meniRecepcioner.add(zakazaniTretmaniMeni);
		meni.add(meniRecepcioner);
		setJMenuBar(meni);
		zakazaniTretmaniMeni.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	ZakazaniTretmaniCRUDFrame zakazaniTretmaniCRUDFrame = new ZakazaniTretmaniCRUDFrame(manageAll, 1);
				zakazaniTretmaniCRUDFrame.toString();
				return;
		    }
		});
		
		dobrodosliLabel.setFont(new Font("Arial", Font.BOLD, 17));
		dobrodosliLabel.setBackground(getBackground());
		
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

		CC centerSpanXWrapCC = new CC();
		centerSpanXWrapCC.alignX("center").spanX().wrap();
		
		JButton btnZakaziTretman = new JButton("ZAKAŽI TRETMAN");
		btnZakaziTretman.setMargin(new Insets(5, 9, 5, 9));
		btnZakaziTretman.setPreferredSize(new Dimension(20, 30));
		getRootPane().setDefaultButton(btnZakaziTretman);
		btnZakaziTretman.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				JFrame tabelaKlijentiFrame = new JFrame();
				tabelaKlijentiFrame.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
				tabelaKlijentiFrame.setTitle(" " + nazivSalona + " ZAKAZIVANJE TRETMANA - ODABIR KLIJENTA");
				tabelaKlijentiFrame.setSize(750, 500);
				tabelaKlijentiFrame.setResizable(false);
				tabelaKlijentiFrame.setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());
				
				JPanel tabelaPanel = (JPanel) getContentPane();
				JTable tabela;
				TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
				tabelaPanel.setLayout(new BorderLayout());
				tabela = new JTable(new KlijentiCRUDTableModel(manageAll, 2));		
				tabela.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tabela.getTableHeader().setReorderingAllowed(false);
				tableSorter.setModel((AbstractTableModel) tabela.getModel());
				tabela.setRowSorter(tableSorter);
				JScrollPane sc = new JScrollPane(tabela);
				tabelaKlijentiFrame.add(sc, BorderLayout.CENTER);
				
				DefaultTableCellRenderer centerRenderer = new CenterRenderer();
			    for (int i = 0; i < tabela.getColumnCount(); i++) {
			    	tabela.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			    }
			    
			    JPanel crudPanel = new JPanel();
				crudPanel.setLayout(new MigLayout("fillx, insets 20 20 15 20"));
				
				JButton btnIzaberi = new JButton("IZABERI KLIJENTA");
				btnIzaberi.setPreferredSize(new Dimension(20, 20));
				btnIzaberi.setMargin(new Insets(5, 9, 5, 9));
				btnIzaberi.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e2) {
						if (tabela.getSelectedRow() != -1) {
							int row = tabela.getSelectedRow();
							int id = Integer.parseInt(tabela.getValueAt(row, 0).toString());
							Klijent k2 = klijentiMng.pronadjiKlijentaPoId(id);
							System.out.println(k2.getId());
							tabelaKlijentiFrame.dispose();
							ZakaziTretmanFrame zakaziTretmanFrame = new ZakaziTretmanFrame(manageAll, k2, r, 2);
							zakaziTretmanFrame.toString();
						}
					}
				});	
				
			    JPanel pSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
			    JTextField tfSearch = new JTextField(20);
				pSearch.add(new JLabel("Search:"));
				pSearch.add(tfSearch);
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
				
				crudPanel.add(new JLabel(""), centerSpanXWrapCC);
				crudPanel.add(btnIzaberi, centerSpanXWrapCC);	
				crudPanel.add(new JLabel(""), centerSpanXWrapCC);
				crudPanel.add(new JLabel(""), centerSpanXWrapCC);
				crudPanel.add(pSearch, "wrap, span");
				crudPanel.add(new JLabel(""), centerSpanXWrapCC);
				crudPanel.add(new JLabel(""), centerSpanXWrapCC);
				
				JButton btnBack = new JButton("<html> <p style = 'font-size: 9px; font-weight: 500;'> NAZAD NA MENI </p> </html>");
				btnBack.setMargin(new Insets(4, 6, 4, 6));	
				btnBack.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						tabelaKlijentiFrame.setVisible(false);
						tabelaKlijentiFrame.dispose();
						RecepcionerFrame recepcionerFrame = new RecepcionerFrame(manageAll, r);
						recepcionerFrame.toString();
						return;
					}
				});
				crudPanel.add(btnBack, centerSpanXWrapCC);
				crudPanel.add(new JLabel(""), centerSpanXWrapCC);
					    			
				tabelaKlijentiFrame.add(crudPanel, BorderLayout.SOUTH);
				
				center(tabelaKlijentiFrame, 1);
				tabelaKlijentiFrame.setVisible(true);
			}
		});
		
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
		
		CC rightSplit2SpanXCC = new CC();
		rightSplit2SpanXCC.alignX("right").split(2).spanX();
		CC centerSplit2SpanXCC = new CC();
		centerSplit2SpanXCC.alignX("center").split(2).spanX();
		CC right = new CC();
		right.alignX("right");
		CC leftWrapCC = new CC();
		leftWrapCC.alignX("left").wrap();
		
		add(dobrodosliLabel, centerSpanXWrapCC);
		add(btnZakaziTretman, centerSpanXWrapCC);
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
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(btnLogOff, centerSpanXWrapCC);
		
		
		center(RecepcionerFrame.this, 1);
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
