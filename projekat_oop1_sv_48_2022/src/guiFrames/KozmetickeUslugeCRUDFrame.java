package guiFrames;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
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

import guiCRUDFrames.KozmetickaUslugaFrameAdd;
import guiCRUDFrames.KozmetickaUslugaFrameEdit;
import guiTableModels.CenterRenderer;
import guiTableModels.KozmetickeUslugeCRUDTableModel;
import manage.KozmetickeUslugeManager;
import manage.ManageAll;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import treatments.KozmetickaUsluga;
import users.Menadzer;


public class KozmetickeUslugeCRUDFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private ManageAll manageAll;
	private KozmetickeUslugeManager kozmetickeUslugeMng;
	private String nazivSalona;
	private Menadzer m;
	protected JTable tabela;
	protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
	protected JTextField tfSearch = new JTextField(20);

	
	public KozmetickeUslugeCRUDFrame(ManageAll manageAll, Menadzer m) {
		this.manageAll = manageAll;
		this.kozmetickeUslugeMng = manageAll.getKozmetickeUslugeMng();	
		this.nazivSalona = manageAll.getImeSalona();
		this.m = m;
		initialize();
	}

	
	private void initialize() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle(" " + nazivSalona + " | " + m.getIme() + " " + m.getPrezime() + " - USLUGE OPCIJE");
		setSize(880, 410);
		setResizable(false);
		setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());
		
		JPanel tabelaPanel = (JPanel) getContentPane();
		tabelaPanel.setLayout(new BorderLayout());
		tabela = new JTable(new KozmetickeUslugeCRUDTableModel(manageAll));		
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
	     
	    CC centerSplit5SpanXCC = new CC();
		centerSplit5SpanXCC.spanX().split(5).alignX("center");
		CC centerSpanXWrapCC = new CC();
		centerSpanXWrapCC.alignX("center").spanX().wrap();
		
		JPanel crudPanel = new JPanel();
		crudPanel.setLayout(new MigLayout("fillx, insets 20 20 15 20"));
		
		Icon addIcon = new ImageIcon("images/icons/addIcon.png");
        Image originalImage1 = ((ImageIcon) addIcon).getImage();
        Image resizedImage1 = originalImage1.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon1 = new ImageIcon(resizedImage1);
		JButton btnAdd = new JButton(resizedIcon1);
		btnAdd.setPreferredSize(new Dimension(20, 20));
		btnAdd.setMargin(new Insets(5, 9, 5, 9));
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) {
				KozmetickaUslugaFrameAdd kozmetickaUslugaFrameAdd = new KozmetickaUslugaFrameAdd(manageAll, KozmetickeUslugeCRUDFrame.this, tabela);
				kozmetickaUslugaFrameAdd.toString();
				return;
			}
		});
		
		Icon editIcon = new ImageIcon("images/icons/editIcon.png");
        Image originalImage2 = ((ImageIcon) editIcon).getImage();
        Image resizedImage2 = originalImage2.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);
		JButton btnEdit = new JButton(resizedIcon2);
		btnEdit.setPreferredSize(new Dimension(20, 20));
		btnEdit.setMargin(new Insets(5, 9, 5, 9));
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) {		
				if (tabela.getSelectedRow() != -1) {
					int row = tabela.getSelectedRow();
					int id = Integer.parseInt(tabela.getValueAt(row, 0).toString());
					KozmetickaUsluga ku2 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(id);
					if (ku2 != null) {
						KozmetickaUslugaFrameEdit kozmetickaUslugaFrameEdit = new KozmetickaUslugaFrameEdit(manageAll, KozmetickeUslugeCRUDFrame.this, ku2, tabela);
						kozmetickaUslugaFrameEdit.toString();
						return;
					} else {
						JOptionPane.showMessageDialog(null, "NIJE MOGUĆE PRONAĆI USLUGU!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
					}
				} 
			}
		});
		
		
		Icon removeIcon = new ImageIcon("images/icons/removeIcon.png");
        Image originalImage3 = ((ImageIcon) removeIcon).getImage();
        Image resizedImage3 = originalImage3.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon3 = new ImageIcon(resizedImage3);
		JButton btnRemove = new JButton(resizedIcon3);
		btnRemove.setPreferredSize(new Dimension(20, 20));
		btnRemove.setMargin(new Insets(5, 9, 5, 9));
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) {
				if (tabela.getSelectedRow() != -1) {
					int row = tabela.getSelectedRow();
					int id = Integer.parseInt(tabela.getValueAt(row, 0).toString());
					KozmetickaUsluga ku2 = kozmetickeUslugeMng.pronadjiKozmetickuUsluguPoId(id);
					if (ku2.getObrisan() == true) {
						JOptionPane.showMessageDialog(null, "USLUGA JE VEĆ OBRISANA!", "GREŠKA", JOptionPane.WARNING_MESSAGE);
						return;
					}
					int choice = JOptionPane.showConfirmDialog(null, "Potvrdite brisanje usluge '" + ku2.getNaziv() + "'. ");
					if (choice == JOptionPane.YES_OPTION) {
						kozmetickeUslugeMng.remove(ku2);
						refreshData();
					} 
				}
			}
		});
			
		JPanel pSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));		
		pSearch.add(new JLabel("Search:"));
		pSearch.add(tfSearch);
		crudPanel.add(pSearch, "wrap, span");
		
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
		

		crudPanel.add(btnAdd, centerSplit5SpanXCC);
		crudPanel.add(new JLabel("   "));
		crudPanel.add(btnEdit);
		crudPanel.add(new JLabel("   "));
		crudPanel.add(btnRemove, "wrap");
		
		tabelaPanel.add(crudPanel, BorderLayout.SOUTH);
		
		center(KozmetickeUslugeCRUDFrame.this, 1);
		setVisible(true);
	}
	
	
	public void refreshData() {
		KozmetickeUslugeCRUDTableModel sm = (KozmetickeUslugeCRUDTableModel)this.tabela.getModel();
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
