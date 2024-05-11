package guiFrames;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import javax.swing.text.NumberFormatter;

import guiTableModels.CenterRenderer;
import guiTableModels.ZakazaniTretmaniCRUDTableModel;
import manage.ManageAll;
import manage.ZakazaniTretmaniManager;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import treatments.ZakazaniTretman;


public class ZakazaniTretmaniCRUDFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private ManageAll manageAll;
	private ZakazaniTretmaniManager zakazaniTretmaniMng;
	private String nazivSalona;
	protected JTable tabela;
	protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
	private int opcija;
	
	
	public ZakazaniTretmaniCRUDFrame(ManageAll manageAll, int opcija) {
		this.manageAll = manageAll;
		this.zakazaniTretmaniMng = manageAll.getZakazaniTretmaniMng();	
		this.nazivSalona = manageAll.getImeSalona();
		this.opcija = opcija;
		initialize();
	}

	
	private void initialize() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle(" " + nazivSalona + " - ZAKAZANI TRETMANI OPCIJE");
		setSize(880, 500);
		setResizable(false);
		setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());
		
		JPanel tabelaPanel = (JPanel) getContentPane();
		tabelaPanel.setLayout(new BorderLayout());
		tabela = new JTable(new ZakazaniTretmaniCRUDTableModel(manageAll, opcija));		
		tabela.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabela.getTableHeader().setReorderingAllowed(false);
		tableSorter.setModel((AbstractTableModel) tabela.getModel());
		tabela.setRowSorter(tableSorter);
		JScrollPane sc = new JScrollPane(tabela);
		add(sc, BorderLayout.CENTER);
		
		DefaultTableCellRenderer centerRenderer = new CenterRenderer();
	    for (int i = 0; i < tabela.getColumnCount(); i++) {
	    	tabela.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	    }
	     	
		JPanel crudPanel = new JPanel();
		crudPanel.setLayout(new MigLayout("fillx, insets 20 20 15 20"));
		
		
		JButton btnIzvrsen = new JButton("IZVRŠEN");
		btnIzvrsen.setPreferredSize(new Dimension(20, 20));
		btnIzvrsen.setMargin(new Insets(5, 9, 5, 9));
		btnIzvrsen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) {
				if (tabela.getSelectedRow() != -1) {
					int row = tabela.getSelectedRow();
					int id = Integer.parseInt(tabela.getValueAt(row, 0).toString());
					ZakazaniTretman zt2 = zakazaniTretmaniMng.pronadjiZakazaniTretmanPoId(id);
					if (zt2 != null) {
						if (!zt2.getStanje().equals("ZAKAZAN")) {
							JOptionPane.showMessageDialog(null, "TRETMAN NIJE ZAKAZAN!", "GREŠKA", JOptionPane.WARNING_MESSAGE);
							return;
						} else {
							int choice = JOptionPane.showConfirmDialog(null, "Potvrdite izvršavanje zakazanog tretmana 'id = " + zt2.getId() + "'. ");
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
		
		JButton btnOtkazaoSalon = new JButton("OTKAZAO SALON");
		btnOtkazaoSalon.setPreferredSize(new Dimension(20, 20));
		btnOtkazaoSalon.setMargin(new Insets(5, 9, 5, 9));
		btnOtkazaoSalon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) {
				if (tabela.getSelectedRow() != -1) {
					int row = tabela.getSelectedRow();
					int id = Integer.parseInt(tabela.getValueAt(row, 0).toString());
					ZakazaniTretman zt2 = zakazaniTretmaniMng.pronadjiZakazaniTretmanPoId(id);
					if (zt2 != null) {
						if (!zt2.getStanje().equals("ZAKAZAN")) {
							JOptionPane.showMessageDialog(null, "TRETMAN NIJE ZAKAZAN!", "GREŠKA", JOptionPane.WARNING_MESSAGE);
							return;
						} else {
							int choice = JOptionPane.showConfirmDialog(null, "Potvrdite otkazivanje zakazanog tretmana 'id = " + zt2.getId() + "' sa 'OTKAZAO SALON'. ");
							if (choice == JOptionPane.YES_OPTION) {
								manageAll.otkaziTretman("salon", zt2);
								refreshData();
							} 
						}	
					} else {
						JOptionPane.showMessageDialog(null, "NIJE MOGUĆE PRONAĆI ZAKAZANI TRETMAN!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		JButton btnOtkazaoKlijent = new JButton("OTKAZAO KLIJENT");
		btnOtkazaoKlijent.setPreferredSize(new Dimension(20, 20));
		btnOtkazaoKlijent.setMargin(new Insets(5, 9, 5, 9));
		btnOtkazaoKlijent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) {		
				if (tabela.getSelectedRow() != -1) {
					int row = tabela.getSelectedRow();
					int id = Integer.parseInt(tabela.getValueAt(row, 0).toString());
					ZakazaniTretman zt2 = zakazaniTretmaniMng.pronadjiZakazaniTretmanPoId(id);
					if (zt2 != null) {
						if (!zt2.getStanje().equals("ZAKAZAN")) {
							JOptionPane.showMessageDialog(null, "TRETMAN NIJE ZAKAZAN!", "GREŠKA", JOptionPane.WARNING_MESSAGE);
							return;
						} else {
							int choice = JOptionPane.showConfirmDialog(null, "Potvrdite otkazivanje zakazanog tretmana 'id = " + zt2.getId() + "' sa 'OTKAZAO KLIJENT'. ");
							if (choice == JOptionPane.YES_OPTION) {
								manageAll.otkaziTretman("klijent", zt2);
								refreshData();
							} 
						}	
					} else {
						JOptionPane.showMessageDialog(null, "NIJE MOGUĆE PRONAĆI ZAKAZANI TRETMAN!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		JButton btnNijeSePojavio = new JButton("NIJE SE POJAVIO");
		btnNijeSePojavio.setPreferredSize(new Dimension(20, 20));
		btnNijeSePojavio.setMargin(new Insets(5, 9, 5, 9));
		btnNijeSePojavio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) {		
				if (tabela.getSelectedRow() != -1) {
					int row = tabela.getSelectedRow();
					int id = Integer.parseInt(tabela.getValueAt(row, 0).toString());
					ZakazaniTretman zt2 = zakazaniTretmaniMng.pronadjiZakazaniTretmanPoId(id);
					if (zt2 != null) {
						if (!zt2.getStanje().equals("ZAKAZAN")) {
							JOptionPane.showMessageDialog(null, "TRETMAN NIJE ZAKAZAN!", "GREŠKA", JOptionPane.WARNING_MESSAGE);
							return;
						} else {
							int choice = JOptionPane.showConfirmDialog(null, "Potvrdite otkazivanje zakazanog tretmana 'id = " + zt2.getId() + "' sa 'NIJE SE POJAVIO'. ");
							if (choice == JOptionPane.YES_OPTION) {
								manageAll.otkaziTretman("nedolazak", zt2);
								refreshData();
							} 
						}	
					} else {
						JOptionPane.showMessageDialog(null, "NIJE MOGUĆE PRONAĆI ZAKAZANI TRETMAN!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		CC centerSplit7SpanXCC = new CC();
	    centerSplit7SpanXCC.spanX().split(7).alignX("center");
		CC centerSpanXWrapCC = new CC();
		centerSpanXWrapCC.alignX("center").wrap();
			
		crudPanel.add(new JLabel("<html> <p style = 'font-size: 11px; font-weight: 500;'> OPCIJE OTKAZIVANJA: </p> </html>"), centerSpanXWrapCC);
		crudPanel.add(new JLabel(""), centerSpanXWrapCC);
		crudPanel.add(new JLabel(""), centerSpanXWrapCC);
		crudPanel.add(btnIzvrsen, centerSplit7SpanXCC);
		crudPanel.add(new JLabel("   "));
		crudPanel.add(btnOtkazaoSalon);
		crudPanel.add(new JLabel("   "));
		crudPanel.add(btnOtkazaoKlijent);
		crudPanel.add(new JLabel("   "));
		crudPanel.add(btnNijeSePojavio, "wrap");
		crudPanel.add(new JLabel(""), centerSpanXWrapCC);
		crudPanel.add(new JLabel(""), centerSpanXWrapCC);
		crudPanel.add(new JLabel(""), centerSpanXWrapCC);
		crudPanel.add(new JLabel(""), centerSpanXWrapCC);
		
		tabelaPanel.add(crudPanel, BorderLayout.SOUTH);
		
		DecimalFormat decimalFormat2 = new DecimalFormat("#,##0.00");
        NumberFormatter formatter2 = new NumberFormatter(decimalFormat2);
        formatter2.setValueClass(Double.class);
        formatter2.setAllowsInvalid(false);
        formatter2.setMinimum(0.0);
        
	    JPanel pSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    pSearch.setLayout(new MigLayout("fillx, insets 15 25 15 25"));
	    JTextField tretmanFilter = new JTextField(15);
	    tretmanFilter.setMargin(new Insets(2, 4, 2, 4));
	    JTextField tipFilter = new JTextField(15);
	    tipFilter.setMargin(new Insets(2, 4, 2, 4));
	    JTextField cenaFilter1 = new JTextField(15);
	    tretmanFilter.setMargin(new Insets(2, 4, 2, 4));
	    JTextField cenaFilter2 = new JTextField(15);
	    tretmanFilter.setMargin(new Insets(2, 4, 2, 4));
        
		pSearch.add(new JLabel("Tip: "));
		pSearch.add(tipFilter);
		pSearch.add(new JLabel(" "));
		pSearch.add(new JLabel("Tretman: "));
		pSearch.add(tretmanFilter);
		pSearch.add(new JLabel(" "));
		pSearch.add(new JLabel("Min. cena: "));
		pSearch.add(cenaFilter1);
		pSearch.add(new JLabel(" "));
		pSearch.add(new JLabel("Max. cena: "));
		pSearch.add(cenaFilter2);
		
		JButton btnFilter = new JButton("FILTER");
		btnFilter.setMargin(new Insets(4, 6, 4, 6));
		btnFilter.setPreferredSize(new Dimension(20, 30));
		getRootPane().setDefaultButton(btnFilter);
		btnFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int l1 = tretmanFilter.getText().trim().length();
				int l2 = tipFilter.getText().trim().length();
				int l3 = cenaFilter1.getText().trim().length();
				int l4 = cenaFilter2.getText().trim().length();
				if (l1 == 0 && l2 == 0 && l3 == 0 && l4 == 0) {
					tableSorter.setRowFilter(null);
				  } else {
					String cena1 = cenaFilter1.getText();
					String cena2 = cenaFilter2.getText(); 
					String tip = tipFilter.getText();
					String tretman = tretmanFilter.getText();
					ArrayList<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>();
					if (l3 != 0 && l4 != 0) {
						double minCena = Double.valueOf(cena1);
						double maxCena = Double.valueOf(cena2);
						filters.add(RowFilter.numberFilter(RowFilter.ComparisonType.AFTER, minCena - 10e-10, 5));
						filters.add(RowFilter.numberFilter(RowFilter.ComparisonType.BEFORE, maxCena + 10e-10, 5));
					}
					if (l2 != 0) {
						filters.add(RowFilter.regexFilter("(?i)" + tip, 1)); 
					}
					if (l1 != 0) {
						filters.add(RowFilter.regexFilter("(?i)" + tretman, 2)); 
					}  
					RowFilter<Object, Object> filter = RowFilter.andFilter(filters);
					tableSorter.setRowFilter(filter);
				  }
			}
		});
		pSearch.add(new JLabel(" "));
		pSearch.add(new JLabel(" "));
		pSearch.add(new JLabel(" "));
		pSearch.add(btnFilter);
		crudPanel.add(pSearch, "wrap, span");
		
		center(ZakazaniTretmaniCRUDFrame.this, 1);
		setVisible(true);
	}
	
	
	public void refreshData() {
		ZakazaniTretmaniCRUDTableModel sm = (ZakazaniTretmaniCRUDTableModel)this.tabela.getModel();
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
	    component.setLocation(x, y);
	}
}
