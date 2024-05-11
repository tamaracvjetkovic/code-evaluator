package guiFrames;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import manage.KozmeticariManager;
import manage.KozmetickiTretmaniManager;
import manage.ManageAll;
import manage.ZakazaniTretmaniManager;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import treatments.KozmetickiTretman;
import treatments.ZakazaniTretman;
import users.Kozmeticar;


public class DijagramiFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private String nazivSalona;
	private KozmetickiTretmaniManager kozmetickiTretmaniMng;
	private ZakazaniTretmaniManager zakazaniTretmaniMng;
	private KozmeticariManager kozmeticariMng;
	
	
	public DijagramiFrame(ManageAll manageAll) {
		this.nazivSalona = manageAll.getImeSalona();
		this.kozmetickiTretmaniMng = manageAll.getKozmetickiTretmaniMng();
		this.zakazaniTretmaniMng = manageAll.getZakazaniTretmaniMng();
		this.kozmeticariMng = manageAll.getKozmeticariMng();
		initialize();
	}
	
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(" " + nazivSalona + " - DIJAGRAMI");
		setSize(740, 470);
		setLayout(new MigLayout("fillx, insets 10 30 10 20"));
		setResizable(false);
		setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());
	
		JLabel izvestajiLabel = new JLabel("DIJAGRAMI");
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
		
		JButton izv1Btn = new JButton("VIDI");
		izv1Btn.setMargin(new Insets(5, 9, 5, 9));
		izv1Btn.setPreferredSize(new Dimension(20, 30));
		izv1Btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {		
				JFrame dijagramFrame1 = new JFrame();
				dijagramFrame1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dijagramFrame1.setSize(980, 530);
				dijagramFrame1.setTitle(" " + nazivSalona + " - DIJAGRAM 1");
				dijagramFrame1.setLayout(new MigLayout("fillx, insets 20 0 20 0"));
				dijagramFrame1.setResizable(false);
				dijagramFrame1.setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());

				LocalDateTime now = LocalDateTime.now();
				LocalDateTime danas = LocalDateTime.of(now.getYear(), now.getMonth(), 1, 0, 0);
		
				int m = now.getMonthValue();
				Date d1 = new Date(2023 - 1900, m - 12, 1);
				Date d2 = new Date(2023 - 1900, m - 11, 1);
				Date d3 = new Date(2023 - 1900, m - 10, 1);
				Date d4 = new Date(2023 - 1900, m - 9, 1);
				Date d5 = new Date(2023 - 1900, m - 8, 1);
				Date d6 = new Date(2023 - 1900, m - 7, 1);
				Date d7 = new Date(2023 - 1900, m - 6, 1);
				Date d8 = new Date(2023 - 1900, m - 5, 1);
				Date d9 = new Date(2023 - 1900, m - 4, 1);
				Date d10 = new Date(2023 - 1900, m - 3, 1);
				Date d11 = new Date(2023 - 1900, m - 2, 1);
				Date d12 = new Date(2023 - 1900, m - 1, 1);
				
				Date[] xData = new Date[] {d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12 };
				List<Date> xList = Arrays.asList(xData);
				
				HashMap<String, Integer> used = new HashMap<>();
				HashMap<Integer, String> nazivi = new HashMap<>();
				int n = 0;
				for (KozmetickiTretman kt : kozmetickiTretmaniMng.getKozmetickiTretmani()) {
					if (kt.getObrisan() == true) {
						continue;
					}
					nazivi.put(n, kt.getNaziv());
					used.put(kt.getNaziv(), n);
					n++;
				}
				
				double[][] yData = new double[n + 1][12];
				
				LocalDateTime prag = danas.minusYears(1).minusMonths(1);
				prag = prag.minusSeconds(1);
				
				double prihod = 0;
				for (ZakazaniTretman zt : zakazaniTretmaniMng.getZakazaniTretmani()) {
					if (zt.getDatumVreme().isBefore(prag)) {
						continue;
					}
					if (zt.getDatumVreme().isAfter(danas)) {
						continue;
					}
					int idTretmana = zt.getZakazanaKozmetickaUsluga().getIdTretmana();
					LocalDateTime d = zt.getDatumVreme();
					int m2 = d.getMonthValue();
					if (m2 > m) {
						m2 -= m;
					} else if (m2 == m) {
						if (d.getYear() < now.getYear()) {
							m2 = 0;
						} else {
							m2 = 11;
						}
					} else {
						m2 += m - 2;
					}
					String nazivTretmana = kozmetickiTretmaniMng.pronadjiKozmetickiTretmanPoId(idTretmana).getNaziv();
					int pos = used.get(nazivTretmana);		
					System.out.println("dsada:" + pos);
					prihod += zt.getCena();
				}								
				
                final XYChart chart = new XYChartBuilder().width(800).height(500).title("Prihod tretmana u poslednjih 12 meseci").xAxisTitle("Mesec").yAxisTitle("Prihod").build();
                
                for (int i = 0; i < n; i++) {
					for (int j = 0; j < 12; j++) {
						yData[n][j] += yData[i][j];
					}
				}              
				for (int i = 0; i <= n; i++) {
					String naziv;
					if (i == n) {
						naziv = "ukupno";
					} else {
						naziv = nazivi.get(i);
					}
					List<Double> yList = new ArrayList<>();
					for (int j = 0; j < yData[i].length; j++) {
						yList.add(yData[i][j]);
					}
					chart.addSeries(naziv, xList, yList);
				}
				
		        JPanel chartPanel = new XChartPanel<>(chart);
		        dijagramFrame1.add(chartPanel, centerSpanXWrapCC);
		        dijagramFrame1.add(new JLabel("<html> <p style = 'margin-top: 15px; font-size: 13px; font-weight: 500;'> Ukupan prihod: " + prihod + "</p> </html>"), centerSpanXWrapCC);

		        center(dijagramFrame1, 1);
		        dijagramFrame1.setVisible(true);		
			}
		});
		JButton izv2Btn = new JButton("VIDI");
		izv2Btn.setMargin(new Insets(5, 9, 5, 9));
		izv2Btn.setPreferredSize(new Dimension(20, 30));
		izv2Btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame dijagramFrame2 = new JFrame();
				dijagramFrame2.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dijagramFrame2.setSize(600, 400);
				dijagramFrame2.setTitle(" " + nazivSalona + " - DIJAGRAM 2");
				dijagramFrame2.setLayout(new MigLayout("fillx, insets 0 0 0 0"));
				dijagramFrame2.setResizable(false);
				dijagramFrame2.setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());

				LocalDateTime now = LocalDateTime.now();
				LocalDateTime prag = LocalDateTime.now();
				prag = prag.minusMonths(1);
				
				HashMap<Integer, Integer> used = new HashMap<>();
				for (Kozmeticar k : kozmeticariMng.getKozmeticari()) {
					used.put(k.getId(), 0);
				}
				for (ZakazaniTretman zt : zakazaniTretmaniMng.getZakazaniTretmani()) {
					if (zt.getDatumVreme().isBefore(prag)) {
						continue;
					}
					if (zt.getDatumVreme().isAfter(now)) {				
						continue;
					}
					if (zt.getStanje().equals("IZVRŠEN")) {
						int br = used.get(zt.getIdKozmeticara());
						br++;
						used.put(zt.getIdKozmeticara(), br);
					}
				}
				
				PieChart chart = new PieChartBuilder().width(600).height(400).title("Opterećenje kozmetičara u prethodnih 30 dana").build();
				chart.getStyler().setCircular(false);
				
				for (Kozmeticar k : kozmeticariMng.getKozmeticari()) {
					String s = k.getIme() + " " + k.getPrezime();
					int b = used.get(k.getId());
					chart.addSeries(s, b);
				}

		        JPanel chartPanel = new XChartPanel<>(chart);
		        dijagramFrame2.add(chartPanel);

		        center(dijagramFrame2, 1);
		        dijagramFrame2.setVisible(true);
			}
		});
		JButton izv3Btn = new JButton("VIDI");
		izv3Btn.setMargin(new Insets(5, 9, 5, 9));
		izv3Btn.setPreferredSize(new Dimension(20, 30));
		izv3Btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame dijagramFrame3 = new JFrame();
				dijagramFrame3.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dijagramFrame3.setSize(600, 400);
				dijagramFrame3.setTitle(" " + nazivSalona + " - DIJAGRAM 3");
				dijagramFrame3.setLayout(new MigLayout("fillx, insets 0 0 0 0"));
				dijagramFrame3.setResizable(false);
				dijagramFrame3.setIconImage(new ImageIcon("images/icons/beauty_salon.png").getImage());

				LocalDateTime now = LocalDateTime.now();
				LocalDateTime prag = LocalDateTime.now();
				prag = prag.minusMonths(1);
				
				HashMap<String, Integer> used = new HashMap<>();
				for (ZakazaniTretman zt : zakazaniTretmaniMng.getZakazaniTretmani()) {
					used.put(zt.getStanje(), 0);
				}

				for (ZakazaniTretman zt : zakazaniTretmaniMng.getZakazaniTretmani()) {
					if (zt.getDatumVreme().isBefore(prag)) {
						continue;
					}
					if (zt.getDatumVreme().isAfter(now)) {				
						continue;
					}
					int br = used.get(zt.getStanje());
					used.put(zt.getStanje(), br + 1);
				}
				
				PieChart chart = new PieChartBuilder().width(600).height(400).title("Status kozmetičkih tretmana u prethodnih 30 dana").build();
				chart.getStyler().setCircular(false);
				
				for (ZakazaniTretman zt : zakazaniTretmaniMng.getZakazaniTretmani()) {
					int br = used.get(zt.getStanje());
					if (br == -1) {
						continue;
					}
					chart.addSeries(zt.getStanje(), br);
					used.put(zt.getStanje(), -1);
				}

		        JPanel chartPanel = new XChartPanel<>(chart);
		        dijagramFrame3.add(chartPanel);

		        center(dijagramFrame3, 1);
		        dijagramFrame3.setVisible(true);
			}
		});
		
		izv1.add(new JLabel("<html> <p style = 'margin-bottom: 5px; font-size: 11px;'> DIJAGRAM 1 </html> </p>"), centerSpanXWrapCC);
		izv1.add(new JLabel("<html> <p style = 'margin-bottom: 5px; font-size: 10px;'> (Prikaz prihoda za prethodnih 12 meseci po tipu tretmana, kao i ukupan prihod) </html> </p>"), centerSpanXWrapCC);
		izv1.add(izv1Btn, centerSpanXWrapCC);
		izv2.add(new JLabel("<html> <p style = 'margin-bottom: 5px; font-size: 11px;'> DIJAGRAM 2 </html> </p>"), centerSpanXWrapCC);
		izv2.add(new JLabel("<html> <p style = 'margin-bottom: 5px; font-size: 10px;'> (Prikaz angažovanja po kozmetičarima - broj realizovanih kozmetičkih tretmana u poslednjih 30 dana) </html> </p>"), centerSpanXWrapCC);
		izv2.add(izv2Btn, centerSpanXWrapCC);
		izv3.add(new JLabel("<html> <p style = 'margin-bottom: 5px; font-size: 11px;'> DIJAGRAM 3 </html> </p>"), centerSpanXWrapCC);
		izv3.add(new JLabel("<html> <p style = 'margin-bottom: 5px; font-size: 10px;'> (Prikaz zastupljenosti pojedinačnih tretmana po statusu u odnosu na ukupan broj izvedenih kozmetičkih tretmana) </html> </p>"), centerSpanXWrapCC);
		izv3.add(izv3Btn, centerSpanXWrapCC);
	
		
		add(new JLabel(""), centerSpanXWrapCC);
		add(izv1, right);
		add(izv2, leftWrapCC);
		add(izv3, centerSpanXWrapCC);
		
		center(DijagramiFrame.this, 1);
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
