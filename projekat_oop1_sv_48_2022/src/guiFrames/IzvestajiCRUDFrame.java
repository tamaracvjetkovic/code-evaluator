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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

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

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import guiTableModels.CenterRenderer;
import guiTableModels.Izvestaji1TableModel;
import guiTableModels.Izvestaji3TableModel;
import guiTableModels.KlijentiCRUDTableModel;
import manage.ManageAll;
import manage.ZakazaniTretmaniManager;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import treatments.ZakazaniTretman;
import users.Menadzer;


public class IzvestajiCRUDFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private ManageAll manageAll;
	private String nazivSalona;
	private Menadzer m;
	private ZakazaniTretmaniManager zakazaniTretmaniMng;
	private Boolean okDatum1;
	private Boolean okDatum2;
	
	
	public IzvestajiCRUDFrame(ManageAll manageAll, Menadzer m) {
		this.manageAll = manageAll;
		this.nazivSalona = manageAll.getImeSalona();
		this.m = m;
		this.zakazaniTretmaniMng = manageAll.getZakazaniTretmaniMng();
		this.okDatum1 = false;
		this.okDatum2 = false;
		initialize();
	}
	
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(" " + nazivSalona + " | " + m.getIme() + " " + m.getPrezime() + " - IZVEŠTAJI");
		setSize(770, 530);
		setLayout(new MigLayout("fillx, insets 10 30 10 20"));
		setResizable(false);
		setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());
	
		JLabel izvestajiLabel = new JLabel("IZVEŠTAJI");
		izvestajiLabel.setFont(new Font("Arial", Font.BOLD, 17));
		izvestajiLabel.setBackground(getBackground());

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
		
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(izvestajiLabel, centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		
		JPanel izv1 =  new JPanel();
		izv1.setLayout(new MigLayout("fillx, insets 15 25 15 25"));
		izv1.setPreferredSize(new Dimension(350, 250));
		JPanel izv2 =  new JPanel();
		izv2.setLayout(new MigLayout("fillx, insets 15 25 15 25"));
		izv2.setPreferredSize(new Dimension(350, 250));
		JPanel izv3 =  new JPanel();
		izv3.setLayout(new MigLayout("fillx, insets 15 25 15 25"));
		izv3.setPreferredSize(new Dimension(350, 250));
		JPanel izv4 =  new JPanel();
		izv4.setLayout(new MigLayout("fillx, insets 15 25 15 25"));
		izv4.setPreferredSize(new Dimension(350, 250));
		
		
		JButton izv1Btn = new JButton("VIDI");
		izv1Btn.setMargin(new Insets(5, 9, 5, 9));
		izv1Btn.setPreferredSize(new Dimension(20, 30));
		izv1Btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame izv1Frame = new JFrame();
				izv1Frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				izv1Frame.setTitle(" " + nazivSalona + " | " + m.getIme() + " " + m.getPrezime() + " - IZVEŠTAJ 1");
				izv1Frame.setSize(640, 330);
				izv1Frame.setLayout(new MigLayout("fillx, insets 20 20 20 20"));
				izv1Frame.setResizable(false);
				izv1Frame.setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());
				izv1Frame.addWindowListener(new WindowAdapter() {
		            @Override
		            public void windowClosing(WindowEvent e) {
		                okDatum1 = false;
		                okDatum2 = false;
		            }
		        });

				
				JLabel prihodiLabel = new JLabel("IZVEŠTAJ 1");
				prihodiLabel.setFont(new Font("Arial", Font.BOLD, 17));
				prihodiLabel.setBackground(getBackground());

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
				
				izv1Frame.add(new JLabel(""), centerSpanXWrapCC);
				izv1Frame.add(prihodiLabel, centerSpanXWrapCC);
				izv1Frame.add(new JLabel(""), centerSpanXWrapCC);
				
				UtilDateModel dateModel1 = new UtilDateModel();
				Properties p1 = new Properties();
				p1.put("text.day", "Day");
				p1.put("text.month", "Month");
				p1.put("text.year", "Year");
				
				JPanel d1PanelWhole = new JPanel();
				d1PanelWhole.setLayout(new MigLayout("fillx, insets 15 25 15 25"));
				
		        JLabel dateLabel1 = new JLabel("Izaberite prvi datum:");
		        dateLabel1.setFont(new Font("Arial", Font.BOLD, 13));
				JDatePanelImpl datePanel1 = new JDatePanelImpl(dateModel1, p1);
				datePanel1.setPreferredSize(new Dimension(230, 180));
				JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1, null);
				
				d1PanelWhole.add(new JLabel(""), centerSpanXWrapCC);
				d1PanelWhole.add(new JLabel("<html> <p style = 'font-size: 11px; margin-top: 2px; font-weight: 400'> OD </p> </html>"), centerSpanXWrapCC);
				d1PanelWhole.add(new JLabel(""), centerSpanXWrapCC);
				d1PanelWhole.add(dateLabel1, centerSpanXWrapCC);
				d1PanelWhole.add(datePicker1, centerSpanXWrapCC);
				
				datePicker1.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				    	okDatum1 = true;
				        Date selectedDate = (Date) datePicker1.getModel().getValue();
				        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
				        String formattedDate = format.format(selectedDate);
				        dateLabel1.setForeground(Color.BLACK);
				        dateLabel1.setText("Izabrani datum: " + formattedDate);
					}
				});
				
				
				UtilDateModel dateModel2 = new UtilDateModel();
				Properties p2 = new Properties();
				p2.put("text.day", "Day");
				p2.put("text.month", "Month");
				p2.put("text.year", "Year");
				
				JPanel d2PanelWhole = new JPanel();
				d2PanelWhole.setLayout(new MigLayout("fillx, insets 15 25 15 25"));
				
				JLabel dateLabel2 = new JLabel("Izaberite drugi datum:");
				dateLabel2.setFont(new Font("Arial", Font.BOLD, 13));
				JDatePanelImpl datePanel2 = new JDatePanelImpl(dateModel2, p2);
				datePanel2.setPreferredSize(new Dimension(230, 180));
				JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2, null);
				
				d2PanelWhole.add(new JLabel(""), centerSpanXWrapCC);
				d2PanelWhole.add(new JLabel("<html> <p style = 'font-size: 11px; margin-top: 2px; font-weight: 400'> DO </p> </html>"), centerSpanXWrapCC);
				d2PanelWhole.add(new JLabel(""), centerSpanXWrapCC);
				d2PanelWhole.add(dateLabel2, centerSpanXWrapCC);
				d2PanelWhole.add(datePicker2, centerSpanXWrapCC);
				
				datePicker2.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				    	okDatum2 = true;
				        Date selectedDate = (Date) datePicker2.getModel().getValue();
				        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
				        String formattedDate = format.format(selectedDate);
				        dateLabel2.setForeground(Color.BLACK);
				        dateLabel2.setText("Izabrani datum: " + formattedDate);
					}
				});
				
				izv1Frame.add(d1PanelWhole, right);
				izv1Frame.add(d2PanelWhole, leftWrapCC);
				
				JButton btnOk = new JButton("<html> <p style = 'font-size: 10px; font-weight: 500;'> PRIKAŽI </p> </html>");
				btnOk.setPreferredSize(new Dimension(140, 40));
				btnOk.setMargin(new Insets(4, 4, 4, 4));
				
				izv1Frame.add(new JLabel(""), centerSpanXWrapCC);
				izv1Frame.add(new JLabel(""), centerSpanXWrapCC);
				izv1Frame.add(new JLabel(""), centerSpanXWrapCC);
				izv1Frame.add(btnOk, centerSpanXWrapCC);
				izv1Frame.add(new JLabel(""), centerSpanXWrapCC);
						
				btnOk.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (okDatum1 == false || okDatum2 == false) {
							JOptionPane.showMessageDialog(null, "Molimo unesite oba datuma!");
							return;
						}
						Date d1 = (Date) datePicker1.getModel().getValue();
				        LocalDateTime datum11 = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
				        LocalDateTime datum1 = LocalDateTime.of(datum11.getYear(), datum11.getMonthValue(), datum11.getDayOfMonth(), 0, 0);
				        Date d2 = (Date) datePicker2.getModel().getValue();
				        LocalDateTime datum22 = d2.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(); 
				        LocalDateTime datum2 = LocalDateTime.of(datum22.getYear(), datum22.getMonthValue(), datum22.getDayOfMonth(), 0, 0);
				        
				        if (datum2.isBefore(datum1)) {
				        	JOptionPane.showMessageDialog(null, "Molimo unesite pravilno datume!");
							return;
				        }
				        izv1Frame.dispose();
				        
				        JDialog tabelaIzvestaj1 = new JDialog();
				        tabelaIzvestaj1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				        tabelaIzvestaj1.setTitle(" " + nazivSalona + " | " + m.getIme() + " " + m.getPrezime() + " - IZVEŠTAJ 1");
				        tabelaIzvestaj1.setSize(670, 420);
				        tabelaIzvestaj1.setResizable(false);
						tabelaIzvestaj1.setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());
						
				        JPanel tabelaPanel = (JPanel) getContentPane();
						tabelaPanel.setLayout(new BorderLayout());
						JTable tabela = new JTable(new Izvestaji1TableModel(manageAll, datum1, datum2));		
						tabela.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						tabela.getTableHeader().setReorderingAllowed(false);
						TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
						JTextField tfSearch = new JTextField(20);
						tableSorter.setModel((AbstractTableModel) tabela.getModel());
						tabela.setRowSorter(tableSorter);
						JScrollPane sc = new JScrollPane(tabela);
						tabelaIzvestaj1.add(sc, BorderLayout.CENTER);
						
						DefaultTableCellRenderer centerRenderer = new CenterRenderer();
					    for (int i = 0; i < tabela.getColumnCount(); i++) {
					    	tabela.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
					    }
						
						JPanel searchPanel = new JPanel();
						searchPanel.setLayout(new MigLayout("fillx, insets 20 20 15 20"));
						JPanel pSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));		
						pSearch.add(new JLabel("Search:"));
						pSearch.add(tfSearch);
						searchPanel.add(pSearch, "wrap, span");
						
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
				        String datum1Formatted = datum1.format(formatter);
				        String datum2Formatted = datum2.format(formatter);
				        
						searchPanel.add(new JLabel(""), centerSpanXWrapCC); 
						searchPanel.add(new JLabel("<html> <p style = 'margin-top: 7px; margin-bottom: 7px; font-size: 11px; font-weight: 400;'> OD  <b>" + datum1Formatted + "</b>  DO  <b>" + datum2Formatted + " </b> </p> </html>"), centerSpanXWrapCC); 
						searchPanel.add(new JLabel(""), centerSpanXWrapCC); 
						tabelaIzvestaj1.add(searchPanel, BorderLayout.SOUTH);
						
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
						center(tabelaIzvestaj1, 1);
						tabelaIzvestaj1.setVisible(true);	    
					}
				});
				
				
				center(izv1Frame, 1);
				izv1Frame.setVisible(true);	
			}
		});
		JButton izv2Btn = new JButton("VIDI");
		izv2Btn.setMargin(new Insets(5, 9, 5, 9));
		izv2Btn.setPreferredSize(new Dimension(20, 30));
		izv2Btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame izv2Frame = new JFrame();
				izv2Frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				izv2Frame.setTitle(" " + nazivSalona + " | " + m.getIme() + " " + m.getPrezime() + " - IZVEŠTAJ 2");
				izv2Frame.setSize(640, 330);
				izv2Frame.setLayout(new MigLayout("fillx, insets 20 20 20 20"));
				izv2Frame.setResizable(false);
				izv2Frame.setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());
				izv2Frame.addWindowListener(new WindowAdapter() {
		            @Override
		            public void windowClosing(WindowEvent e) {
		                okDatum1 = false;
		                okDatum2 = false;
		            }
		        });

				
				JLabel prihodiLabel = new JLabel("IZVEŠTAJ 2");
				prihodiLabel.setFont(new Font("Arial", Font.BOLD, 17));
				prihodiLabel.setBackground(getBackground());

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
				
				izv2Frame.add(new JLabel(""), centerSpanXWrapCC);
				izv2Frame.add(prihodiLabel, centerSpanXWrapCC);
				izv2Frame.add(new JLabel(""), centerSpanXWrapCC);
				
				UtilDateModel dateModel1 = new UtilDateModel();
				Properties p1 = new Properties();
				p1.put("text.day", "Day");
				p1.put("text.month", "Month");
				p1.put("text.year", "Year");
				
				JPanel d1PanelWhole = new JPanel();
				d1PanelWhole.setLayout(new MigLayout("fillx, insets 15 25 15 25"));
				
		        JLabel dateLabel1 = new JLabel("Izaberite prvi datum:");
		        dateLabel1.setFont(new Font("Arial", Font.BOLD, 13));
				JDatePanelImpl datePanel1 = new JDatePanelImpl(dateModel1, p1);
				datePanel1.setPreferredSize(new Dimension(230, 180));
				JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1, null);
				
				d1PanelWhole.add(new JLabel(""), centerSpanXWrapCC);
				d1PanelWhole.add(new JLabel("<html> <p style = 'font-size: 11px; margin-top: 2px; font-weight: 400'> OD </p> </html>"), centerSpanXWrapCC);
				d1PanelWhole.add(new JLabel(""), centerSpanXWrapCC);
				d1PanelWhole.add(dateLabel1, centerSpanXWrapCC);
				d1PanelWhole.add(datePicker1, centerSpanXWrapCC);
				
				datePicker1.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				    	okDatum1 = true;
				        Date selectedDate = (Date) datePicker1.getModel().getValue();
				        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
				        String formattedDate = format.format(selectedDate);
				        dateLabel1.setForeground(Color.BLACK);
				        dateLabel1.setText("Izabrani datum: " + formattedDate);
					}
				});
				
				
				UtilDateModel dateModel2 = new UtilDateModel();
				Properties p2 = new Properties();
				p2.put("text.day", "Day");
				p2.put("text.month", "Month");
				p2.put("text.year", "Year");
				
				JPanel d2PanelWhole = new JPanel();
				d2PanelWhole.setLayout(new MigLayout("fillx, insets 15 25 15 25"));
				
				JLabel dateLabel2 = new JLabel("Izaberite drugi datum:");
				dateLabel2.setFont(new Font("Arial", Font.BOLD, 13));
				JDatePanelImpl datePanel2 = new JDatePanelImpl(dateModel2, p2);
				datePanel2.setPreferredSize(new Dimension(230, 180));
				JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2, null);
				
				d2PanelWhole.add(new JLabel(""), centerSpanXWrapCC);
				d2PanelWhole.add(new JLabel("<html> <p style = 'font-size: 11px; margin-top: 2px; font-weight: 400'> DO </p> </html>"), centerSpanXWrapCC);
				d2PanelWhole.add(new JLabel(""), centerSpanXWrapCC);
				d2PanelWhole.add(dateLabel2, centerSpanXWrapCC);
				d2PanelWhole.add(datePicker2, centerSpanXWrapCC);
				
				datePicker2.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				    	okDatum2 = true;
				        Date selectedDate = (Date) datePicker2.getModel().getValue();
				        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
				        String formattedDate = format.format(selectedDate);
				        dateLabel2.setForeground(Color.BLACK);
				        dateLabel2.setText("Izabrani datum: " + formattedDate);
					}
				});
				
				izv2Frame.add(d1PanelWhole, right);
				izv2Frame.add(d2PanelWhole, leftWrapCC);
				
				JButton btnOk = new JButton("<html> <p style = 'font-size: 10px; font-weight: 500;'> PRIKAŽI </p> </html>");
				btnOk.setPreferredSize(new Dimension(140, 40));
				btnOk.setMargin(new Insets(4, 4, 4, 4));
				
				izv2Frame.add(new JLabel(""), centerSpanXWrapCC);
				izv2Frame.add(new JLabel(""), centerSpanXWrapCC);
				izv2Frame.add(new JLabel(""), centerSpanXWrapCC);
				izv2Frame.add(btnOk, centerSpanXWrapCC);
				izv2Frame.add(new JLabel(""), centerSpanXWrapCC);
						
				btnOk.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (okDatum1 == false || okDatum2 == false) {
							JOptionPane.showMessageDialog(null, "Molimo unesite oba datuma!");
							return;
						}
						Date d1 = (Date) datePicker1.getModel().getValue();
				        LocalDateTime datum11 = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
				        LocalDateTime datum1 = LocalDateTime.of(datum11.getYear(), datum11.getMonthValue(), datum11.getDayOfMonth(), 0, 0);
				        Date d2 = (Date) datePicker2.getModel().getValue();
				        LocalDateTime datum22 = d2.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(); 
				        LocalDateTime datum2 = LocalDateTime.of(datum22.getYear(), datum22.getMonthValue(), datum22.getDayOfMonth(), 0, 0);
				        	
				        if (datum2.isBefore(datum1)) {
				        	JOptionPane.showMessageDialog(null, "Molimo unesite pravilno datume!");
							return;
				        }
				        izv2Frame.dispose();
				        
				        JDialog rez = new JDialog();
				        rez.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				        rez.setTitle(" " + nazivSalona + " | " + m.getIme() + " " + m.getPrezime() + " - IZVEŠTAJ 2");
				        rez.setSize(670, 395);
				        rez.setResizable(false);
				        rez.setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());
				        
				        JPanel panel = new JPanel();
				        panel.setLayout(new MigLayout("fillx, insets 20 20 15 20"));
					
				        int p1 = 0;
				        int p2 = 0;
				        int p3 = 0;
				        int p4 = 0;
				        int p5 = 0;
				        for (ZakazaniTretman zt : zakazaniTretmaniMng.getZakazaniTretmani()) {
				        	if (zt.getDatumVreme().isAfter(datum1) && zt.getDatumVreme().isBefore(datum2)) {
				        		if (zt.getStanje().equals("ZAKAZAN")) {
					        		p1++;
					        	}
					        	if (zt.getStanje().equals("IZVRŠEN")) {
					        		p2++;
					        	}
					        	if (zt.getStanje().equals("OTKAZAO SALON")) {
					        		p3++;
					        	}
					        	if (zt.getStanje().equals("OTKAZAO KLIJENT")) {
					        		p4++;
					        	}
					        	if (zt.getStanje().equals("NIJE SE POJAVIO")) {
					        		p5++;
					        	}
				        	}		        	
				        }
				        
				        JPanel uvod =  new JPanel();
				        uvod.setLayout(new MigLayout("fillx, insets 20 20 15 20"));

				        uvod.add(new JLabel("<html> <p style = 'font-size: 13px; font-weight: 500;'> Broj izvršenih kozmetičkih tretmana po stanjima: </p> </html>"), centerSpanXWrapCC);
				        uvod.add(new JLabel(""), centerSpanXWrapCC);
				        
				        panel.add(uvod, centerSpanXWrapCC);
				        panel.add(new JLabel("<html> <p style = 'font-size: 13px; font-weight: 400;'> ZAKAZANO: <b>" + p1 + "</b> </p> </html>"), centerSpanXWrapCC);
				        panel.add(new JLabel("<html> <p style = 'font-size: 13px; font-weight: 400;'> IZVRŠENO: <b>" + p2 + "</b> </p> </html>"), centerSpanXWrapCC);
				        panel.add(new JLabel("<html> <p style = 'font-size: 13px; font-weight: 400;'> OTKAZAO SALON: <b>" + p3 + "</b> </p> </html>"), centerSpanXWrapCC);
				        panel.add(new JLabel("<html> <p style = 'font-size: 13px; font-weight: 400;'> OTKAZAO KLIJENT: <b>" + p4 + "</b> </p> </html>"), centerSpanXWrapCC);
				        panel.add(new JLabel("<html> <p style = 'font-size: 13px; font-weight: 400;'> NIJE SE POJAVIO: <b>" + p5 + "</b> </p> </html>"), centerSpanXWrapCC);
				        panel.add(new JLabel(""), centerSpanXWrapCC);
				        
				        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
				        String datum1Formatted = datum1.format(formatter);
				        String datum2Formatted = datum2.format(formatter);
				        
				        panel.add(new JLabel(""), centerSpanXWrapCC); 
				        panel.add(new JLabel(""), centerSpanXWrapCC); 
				        panel.add(new JLabel(""), centerSpanXWrapCC); 
				        panel.add(new JLabel("<html> <p style = 'margin-top: 7px; margin-bottom: 7px; font-size: 13px; font-weight: 400;'> OD  <b>" + datum1Formatted + "</b>  DO  <b>" + datum2Formatted + " </b> </p> </html>"), centerSpanXWrapCC); 
				        panel.add(new JLabel(""), centerSpanXWrapCC);
				        
				        rez.add(panel);
				        center(rez, 1);
				        rez.setVisible(true);	
					}
				});
				
				
				center(izv2Frame, 1);
				izv2Frame.setVisible(true);	
			}
		});
		JButton izv3Btn = new JButton("VIDI");
		izv3Btn.setMargin(new Insets(5, 9, 5, 9));
		izv3Btn.setPreferredSize(new Dimension(20, 30));
		izv3Btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame izv3Frame = new JFrame();
				izv3Frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				izv3Frame.setTitle(" " + nazivSalona + " | " + m.getIme() + " " + m.getPrezime() + " - IZVEŠTAJ 3");
				izv3Frame.setSize(640, 330);
				izv3Frame.setLayout(new MigLayout("fillx, insets 20 20 20 20"));
				izv3Frame.setResizable(false);
				izv3Frame.setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());
				izv3Frame.addWindowListener(new WindowAdapter() {
		            @Override
		            public void windowClosing(WindowEvent e) {
		                okDatum1 = false;
		                okDatum2 = false;
		            }
		        });

				
				JLabel prihodiLabel = new JLabel("IZVEŠTAJ 3");
				prihodiLabel.setFont(new Font("Arial", Font.BOLD, 17));
				prihodiLabel.setBackground(getBackground());

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
				
				izv3Frame.add(new JLabel(""), centerSpanXWrapCC);
				izv3Frame.add(prihodiLabel, centerSpanXWrapCC);
				izv3Frame.add(new JLabel(""), centerSpanXWrapCC);
				
				UtilDateModel dateModel1 = new UtilDateModel();
				Properties p1 = new Properties();
				p1.put("text.day", "Day");
				p1.put("text.month", "Month");
				p1.put("text.year", "Year");
				
				JPanel d1PanelWhole = new JPanel();
				d1PanelWhole.setLayout(new MigLayout("fillx, insets 15 25 15 25"));
				
		        JLabel dateLabel1 = new JLabel("Izaberite prvi datum:");
		        dateLabel1.setFont(new Font("Arial", Font.BOLD, 13));
				JDatePanelImpl datePanel1 = new JDatePanelImpl(dateModel1, p1);
				datePanel1.setPreferredSize(new Dimension(230, 180));
				JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1, null);
				
				d1PanelWhole.add(new JLabel(""), centerSpanXWrapCC);
				d1PanelWhole.add(new JLabel("<html> <p style = 'font-size: 11px; margin-top: 2px; font-weight: 400'> OD </p> </html>"), centerSpanXWrapCC);
				d1PanelWhole.add(new JLabel(""), centerSpanXWrapCC);
				d1PanelWhole.add(dateLabel1, centerSpanXWrapCC);
				d1PanelWhole.add(datePicker1, centerSpanXWrapCC);
				
				datePicker1.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				    	okDatum1 = true;
				        Date selectedDate = (Date) datePicker1.getModel().getValue();
				        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
				        String formattedDate = format.format(selectedDate);
				        dateLabel1.setForeground(Color.BLACK);
				        dateLabel1.setText("Izabrani datum: " + formattedDate);
					}
				});
				
				
				UtilDateModel dateModel2 = new UtilDateModel();
				Properties p2 = new Properties();
				p2.put("text.day", "Day");
				p2.put("text.month", "Month");
				p2.put("text.year", "Year");
				
				JPanel d2PanelWhole = new JPanel();
				d2PanelWhole.setLayout(new MigLayout("fillx, insets 15 25 15 25"));
				
				JLabel dateLabel2 = new JLabel("Izaberite drugi datum:");
				dateLabel2.setFont(new Font("Arial", Font.BOLD, 13));
				JDatePanelImpl datePanel2 = new JDatePanelImpl(dateModel2, p2);
				datePanel2.setPreferredSize(new Dimension(230, 180));
				JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2, null);
				
				d2PanelWhole.add(new JLabel(""), centerSpanXWrapCC);
				d2PanelWhole.add(new JLabel("<html> <p style = 'font-size: 11px; margin-top: 2px; font-weight: 400'> DO </p> </html>"), centerSpanXWrapCC);
				d2PanelWhole.add(new JLabel(""), centerSpanXWrapCC);
				d2PanelWhole.add(dateLabel2, centerSpanXWrapCC);
				d2PanelWhole.add(datePicker2, centerSpanXWrapCC);
				
				datePicker2.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				    	okDatum2 = true;
				        Date selectedDate = (Date) datePicker2.getModel().getValue();
				        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
				        String formattedDate = format.format(selectedDate);
				        dateLabel2.setForeground(Color.BLACK);
				        dateLabel2.setText("Izabrani datum: " + formattedDate);
					}
				});
				
				izv3Frame.add(d1PanelWhole, right);
				izv3Frame.add(d2PanelWhole, leftWrapCC);
				
				JButton btnOk = new JButton("<html> <p style = 'font-size: 10px; font-weight: 500;'> PRIKAŽI </p> </html>");
				btnOk.setPreferredSize(new Dimension(140, 40));
				btnOk.setMargin(new Insets(4, 4, 4, 4));
				
				izv3Frame.add(new JLabel(""), centerSpanXWrapCC);
				izv3Frame.add(new JLabel(""), centerSpanXWrapCC);
				izv3Frame.add(new JLabel(""), centerSpanXWrapCC);
				izv3Frame.add(btnOk, centerSpanXWrapCC);
				izv3Frame.add(new JLabel(""), centerSpanXWrapCC);
						
				btnOk.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (okDatum1 == false || okDatum2 == false) {
							JOptionPane.showMessageDialog(null, "Molimo unesite oba datuma!");
							return;
						}
						Date d1 = (Date) datePicker1.getModel().getValue();
				        LocalDateTime datum11 = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
				        LocalDateTime datum1 = LocalDateTime.of(datum11.getYear(), datum11.getMonthValue(), datum11.getDayOfMonth(), 0, 0);
				        Date d2 = (Date) datePicker2.getModel().getValue();
				        LocalDateTime datum22 = d2.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(); 
				        LocalDateTime datum2 = LocalDateTime.of(datum22.getYear(), datum22.getMonthValue(), datum22.getDayOfMonth(), 0, 0);
				        
				        if (datum2.isBefore(datum1)) {
				        	JOptionPane.showMessageDialog(null, "Molimo unesite pravilno datume!");
							return;
				        }
				        izv3Frame.dispose();
				        
				        JDialog rez = new JDialog();
				        rez.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				        rez.setTitle(" " + nazivSalona + " | " + m.getIme() + " " + m.getPrezime() + " - IZVEŠTAJ 3");
				        rez.setSize(670, 395);
				        rez.setResizable(false);
				        rez.setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());
				        
				        JDialog tabelaIzvestaj1 = new JDialog();
				        tabelaIzvestaj1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				        tabelaIzvestaj1.setTitle(" " + nazivSalona + " | " + m.getIme() + " " + m.getPrezime() + " - IZVEŠTAJ 1");
				        tabelaIzvestaj1.setSize(670, 420);
				        tabelaIzvestaj1.setResizable(false);
						tabelaIzvestaj1.setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());
						
				        JPanel tabelaPanel = (JPanel) getContentPane();
						tabelaPanel.setLayout(new BorderLayout());
						JTable tabela = new JTable(new Izvestaji3TableModel(manageAll, datum1, datum2));		
						tabela.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						tabela.getTableHeader().setReorderingAllowed(false);
						TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
						JTextField tfSearch = new JTextField(20);
						tableSorter.setModel((AbstractTableModel) tabela.getModel());
						tabela.setRowSorter(tableSorter);
						JScrollPane sc = new JScrollPane(tabela);
						rez.add(sc, BorderLayout.CENTER);
						
						DefaultTableCellRenderer centerRenderer = new CenterRenderer();
					    for (int i = 0; i < tabela.getColumnCount(); i++) {
					    	tabela.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
					    }
						
						JPanel searchPanel = new JPanel();
						searchPanel.setLayout(new MigLayout("fillx, insets 20 20 15 20"));
						JPanel pSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));		
						pSearch.add(new JLabel("Search:"));
						pSearch.add(tfSearch);
						searchPanel.add(pSearch, "wrap, span");
						
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
				        String datum1Formatted = datum1.format(formatter);
				        String datum2Formatted = datum2.format(formatter);
				        
						searchPanel.add(new JLabel(""), centerSpanXWrapCC); 
						searchPanel.add(new JLabel("<html> <p style = 'margin-top: 7px; margin-bottom: 7px; font-size: 11px; font-weight: 400;'> OD  <b>" + datum1Formatted + "</b>  DO  <b>" + datum2Formatted + " </b> </p> </html>"), centerSpanXWrapCC); 
						searchPanel.add(new JLabel(""), centerSpanXWrapCC); 
						rez.add(searchPanel, BorderLayout.SOUTH);
						
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
						center(rez, 1);
						rez.setVisible(true);
					}
				});
				
				
				center(izv3Frame, 1);
				izv3Frame.setVisible(true);	
			}
		});
		JButton izv4Btn = new JButton("VIDI");
		izv4Btn.setMargin(new Insets(5, 9, 5, 9));
		izv4Btn.setPreferredSize(new Dimension(20, 30));
		izv4Btn.addActionListener(new ActionListener() {
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
		
		izv1.add(new JLabel("<html> <p style = 'margin-bottom: 5px; font-size: 11px;'> IZVEŠTAJ 1 </html> </p>"), centerSpanXWrapCC);
		izv1.add(new JLabel("<html> <p style = 'margin-bottom: 5px; font-size: 10px;'> (Koliko je kozmetičkih tretmana svaki kozmetičar izvršio i koliko je prihodovao za izabrani opseg datuma) </html> </p>"), centerSpanXWrapCC);
		izv1.add(izv1Btn, centerSpanXWrapCC);
		izv2.add(new JLabel("<html> <p style = 'margin-bottom: 5px; font-size: 11px;'> IZVEŠTAJ 2 </html> </p>"), centerSpanXWrapCC);
		izv2.add(new JLabel("<html> <p style = 'margin-bottom: 5px; font-size: 10px;'> (Koliko kozmetičkih tretmana je potvrđeno, a koliko otkazano (po razlozima) za odabrani opseg datuma) </html> </p>"), centerSpanXWrapCC);
		izv2.add(izv2Btn, centerSpanXWrapCC);
		izv3.add(new JLabel("<html> <p style = 'margin-bottom: 5px; font-size: 11px;'> IZVEŠTAJ 3 </html> </p>"), centerSpanXWrapCC);
		izv3.add(new JLabel("<html> <p style = 'margin-bottom: 5px; font-size: 10px;'> (Prikaz kozmetičke usluge:  podaci o samoj usluzi i njenom tipu, ukupan broj zakazanih tretmana za tu uslugu i ostvareni prihodi za izabrani opseg datuma) </html> </p>"), centerSpanXWrapCC);
		izv3.add(izv3Btn, centerSpanXWrapCC);
		izv4.add(new JLabel("<html> <p style = 'margin-bottom: 5px; font-size: 11px;'> IZVEŠTAJ 4 </html> </p>"), centerSpanXWrapCC);
		izv4.add(new JLabel("<html> <p style = 'margin-bottom: 5px; font-size: 10px;'> (Klijenti koji ispunjavaju uslove za karticu lojalnosti - potrošili su na tretmane više novca od iznosa koji zadaje menadžer) </html> </p>"), centerSpanXWrapCC);
		izv4.add(izv4Btn, centerSpanXWrapCC);
		
		add(new JLabel(""), centerSpanXWrapCC);
		add(izv1, right);
		add(izv2, leftWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(izv3, right);
		add(izv4, leftWrapCC);
		
		center(IzvestajiCRUDFrame.this, 1);
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
