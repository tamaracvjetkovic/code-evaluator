package guiFrames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import manage.ManageAll;
import manage.ZakazaniTretmaniManager;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import treatments.ZakazaniTretman;
import users.Menadzer;


public class PrihodiCRUDFrame extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private ManageAll manageAll;
	private String nazivSalona;
	private Menadzer m;
	private Boolean okDatum1;
	private Boolean okDatum2;
	private ZakazaniTretmaniManager zakazaniTretmaniMng;
	
	
	public PrihodiCRUDFrame(ManageAll manageAll, Menadzer m) {
		this.manageAll = manageAll;
		this.nazivSalona = manageAll.getImeSalona();
		this.m = m;
		this.okDatum1 = false;
		this.okDatum2 = false;
		this.zakazaniTretmaniMng = manageAll.getZakazaniTretmaniMng();
		initialize();
	}
	
	
	private void initialize() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle(" " + nazivSalona + " | " + m.getIme() + " " + m.getPrezime() + " - PRIHODI");
		setSize(640, 510);
		setLayout(new MigLayout("fillx, insets 20 20 20 20"));
		setResizable(false);
		setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());
	
		JLabel prihodiLabel = new JLabel("UKUPAN PRIHOD: " + manageAll.getUkupanPrihod());
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
		
		add(new JLabel(""), centerSpanXWrapCC);
		add(prihodiLabel, centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		
		JLabel pLabel = new JLabel("Prihodi u određenom periodu: ");
		pLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		pLabel.setBackground(getBackground());
			
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(pLabel, centerSpanXWrapCC);
		
		
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
		
		add(d1PanelWhole, right);
		add(d2PanelWhole, leftWrapCC);
		
		JButton btnOk = new JButton("<html> <p style = 'font-size: 10px; font-weight: 500;'> PRIKAŽI PRIHODE </p> </html>");
		btnOk.setPreferredSize(new Dimension(140, 40));
		btnOk.setMargin(new Insets(4, 4, 4, 4));
		
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(btnOk, centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		
		JLabel s = new JLabel("<html> <p style = 'font-size: 13px; font-weight: 500; margin-top: 8px;'> PRIHODI U ZADATOM PERIODU: </p> </html>");
		JLabel rezultat = new JLabel("<html> <p style = 'margin-top: 5px; font-size: 12px; font-weight: 500;'> 0.0 RSD </p> </html>");

		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(s, centerSpanXWrapCC);
		add(new JLabel(""), centerSpanXWrapCC);
		add(rezultat, centerSpanXWrapCC);
		
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
		        double prihodi = 0;
		        for (ZakazaniTretman zt: zakazaniTretmaniMng.getZakazaniTretmani()) {
					LocalDateTime dd = zt.getDatumVreme();
			        LocalDateTime d = LocalDateTime.of(dd.getYear(), dd.getMonthValue(), dd.getDayOfMonth(), 0, 0);
		        	if ((d.isAfter(datum1) && d.isBefore(datum2)) || (d.equals(datum1) && d.isBefore(datum2)) || (d.isAfter(datum1) && d.equals(datum2)) || (d.equals(datum1) && d.equals(datum2))) {
						double cena = zt.getCena();
						prihodi += cena;					
					}
		        }
		        rezultat.setText("<html> <p style = 'font-size: 13px; font-weight: 500;'>" + prihodi + " RSD </p> </html>");
			}
		});
		
		
		center(PrihodiCRUDFrame.this, 1);
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
