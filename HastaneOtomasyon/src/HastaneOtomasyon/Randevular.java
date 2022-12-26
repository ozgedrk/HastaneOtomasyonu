package HastaneOtomasyon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import java.awt.Toolkit;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;

public class Randevular extends AbstractFrame implements Formatter {

	DefaultTableModel tableModelHasta = new DefaultTableModel();
	DefaultTableModel tableModelDoktor = new DefaultTableModel();
	DefaultTableModel tableModelRandevular = new DefaultTableModel();

	public Randevular() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Randevular.class.getResource("/Resimler/gazilogo.png")));
		setTitle("Kayseri Eðitim ve Araþtýrma Hastanesi Hekim Randevu Sistemi");
		initComponents();
		Date simdikiZaman = new Date();
		jLabel1.setText(simdikiZaman.toString());
		jLabel1.setText(dateFormat.format(simdikiZaman));
		jLabel2.setVisible(false);

		ArrayList<String> hastaArrayList = kolonBelirle("id", "tckimlik", "adi", "soyadi", "cinsiyet", "dogum_tarihi",
				"dogum_yeri", "randevu_alabilir", "onceligi_var", "durum");
		tabloyaVeriDoldur(hastaArrayList, "kullanicilar", "rol='hasta'", tableModelHasta, jTableHasta);

		ArrayList<String> doktorArrayList = kolonBelirle("id", "sicilno", "adi", "soyadi", "cinsiyet", "brans",
				"akademik_unvan", "calistigi_kurum", "durum");
		tabloyaVeriDoldur(doktorArrayList, "kullanicilar", "rol='doktor'", tableModelDoktor, jTableDoktor);

		ArrayList<String> randevularArrayList = kolonBelirle("");
		tabloyaVeriDoldur(randevularArrayList, "randevular", "", tableModelRandevular, jTableRandevular);
	}

	public void yetkiKontrol() {
		if (isHasta()) {
			textAreaRecete.setEditable(false);
			lblRandevuyaGeldiMi.setVisible(false);
			chckbxGeldimi.setVisible(false);
		}
	}

	private void initComponents() {

		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		jPanel1 = new JPanel();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

		jLabel1.setFont(new Font("Tahoma", 1, 12));

		jLabel2.setFont(new Font("Tahoma", 1, 12));
		jLabel2.setForeground(new Color(255, 0, 0));
		jLabel2.setText("Kayseri Eðitim Ve Araþtýrma Hastanesi Hekim Randevu Sistemi");

		jPanel1.setBackground(new Color(102, 102, 102));

		jTableDoktor = new JTable();
		jTableDoktor.setFont(new Font("Tahoma", 1, 12));
		jTableDoktor.setModel(this.tableModelDoktor);

		jScrollPaneDoktor = new JScrollPane();
		jScrollPaneDoktor.setViewportView(jTableDoktor);

		utilDateModel = new UtilDateModel();
		jdatePanel = new JDatePanelImpl(utilDateModel);
		datePickerTarih = new JDatePickerImpl(jdatePanel);

		JLabel lblTarih = new JLabel();
		lblTarih.setText("Tarih:");
		lblTarih.setForeground(Color.WHITE);
		lblTarih.setFont(new Font("Tahoma", Font.BOLD, 14));

		lblSaat = new JLabel();
		lblSaat.setText("Saat");
		lblSaat.setForeground(Color.WHITE);
		lblSaat.setFont(new Font("Tahoma", Font.BOLD, 14));

		comboBoxSaat = new JComboBox();
		for (int i = 9; i < 18; i++) {
			comboBoxSaat.addItem(i + ":00");
		}

		JLabel lblNot = new JLabel();
		lblNot.setText("Not:");
		lblNot.setForeground(Color.WHITE);
		lblNot.setFont(new Font("Tahoma", Font.BOLD, 14));

		textAreaNot = new JTextArea();
		textAreaNot.setLineWrap(true);

		chckbxGeldimi = new JCheckBox("Geldi");

		lblRandevuyaGeldiMi = new JLabel();
		lblRandevuyaGeldiMi.setText("Randevuya Geldi mi?");
		lblRandevuyaGeldiMi.setForeground(Color.WHITE);
		lblRandevuyaGeldiMi.setFont(new Font("Tahoma", Font.BOLD, 14));

		lblRecete = new JLabel();
		lblRecete.setText("Reçete:");
		lblRecete.setForeground(Color.WHITE);
		lblRecete.setFont(new Font("Tahoma", Font.BOLD, 14));

		textAreaRecete = new JTextArea();

		textAreaRecete.setLineWrap(true);

		jTableHasta = new JTable();
		jTableHasta.setFont(new Font("Tahoma", 1, 12));
		jTableHasta.setModel(this.tableModelHasta);
		jTableHasta.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				// jTable1MouseClicked(evt);
			}
		});

		JScrollPane jScrollPaneHasta = new JScrollPane();
		jScrollPaneHasta.setViewportView(jTableHasta);

		JLabel lblHastalar = new JLabel();
		lblHastalar.setText("Hastalar");
		lblHastalar.setForeground(Color.WHITE);
		lblHastalar.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblDoktorlar = new JLabel();
		lblDoktorlar.setText("Doktorlar");
		lblDoktorlar.setForeground(Color.WHITE);
		lblDoktorlar.setFont(new Font("Tahoma", Font.BOLD, 14));

		comboBoxDakika = new JComboBox();

		for (int i = 0; i < 6; i++) {
			comboBoxDakika.addItem(i + "0");
		}

		JLabel lblDakika = new JLabel();
		lblDakika.setText("Dakika");
		lblDakika.setForeground(Color.WHITE);
		lblDakika.setFont(new Font("Tahoma", Font.BOLD, 14));

		JButton jButtonRandevuEkle = new JButton();
		jButtonRandevuEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonRandevuOlusturActionPerformed(e);
			}
		});
		jButtonRandevuEkle.setText("Randevu Ekle");
		jButtonRandevuEkle.setFont(new Font("Tahoma", Font.BOLD, 13));

		JButton jButtonGuncelle = new JButton();
		jButtonGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonGuncelleActionPerformed(e);
			}
		});
		jButtonGuncelle.setText("Güncelle");

		JButton jButtonSil = new JButton();
		jButtonSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonSilActionPerformed(e);
			}
		});
		jButtonSil.setText("Sil");

		jTableRandevular = new JTable();
		jTableRandevular.setFont(new Font("Tahoma", 1, 12));
		jTableRandevular.setModel(this.tableModelRandevular);
		jTableRandevular.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				jTableRandevularMouseClicked(evt);
			}
		});

		jScrollPaneRandevular = new JScrollPane();
		jScrollPaneRandevular.setViewportView(jTableRandevular);

		lblRandevular = new JLabel();
		lblRandevular.setText("Randevular");
		lblRandevular.setForeground(Color.WHITE);
		lblRandevular.setFont(new Font("Tahoma", Font.BOLD, 14));

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING).addGroup(jPanel1Layout
				.createSequentialGroup().addContainerGap()
				.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHastalar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
								.createParallelGroup(Alignment.LEADING, false)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addComponent(lblNot, GroupLayout.PREFERRED_SIZE, 100,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textAreaNot, 0, 0, Short.MAX_VALUE))
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addComponent(lblTarih, GroupLayout.PREFERRED_SIZE, 100,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(datePickerTarih,
												GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)))
								.addGap(18)
								.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addComponent(lblSaat, GroupLayout.PREFERRED_SIZE, 100,
														GroupLayout.PREFERRED_SIZE)
												.addGap(4)
												.addComponent(comboBoxSaat, GroupLayout.PREFERRED_SIZE, 116,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18).addComponent(lblDakika, GroupLayout.PREFERRED_SIZE, 100,
														GroupLayout.PREFERRED_SIZE))
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addComponent(lblRecete, GroupLayout.PREFERRED_SIZE, 100,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(textAreaRecete, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(lblRandevular, GroupLayout.PREFERRED_SIZE, 100,
														GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addComponent(comboBoxDakika, GroupLayout.PREFERRED_SIZE, 116,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(lblRandevuyaGeldiMi).addGap(18)
												.addComponent(chckbxGeldimi, GroupLayout.PREFERRED_SIZE, 69,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(jButtonRandevuEkle, GroupLayout.PREFERRED_SIZE, 143,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(jButtonGuncelle, GroupLayout.PREFERRED_SIZE, 113,
														GroupLayout.PREFERRED_SIZE)
												.addGap(6).addComponent(jButtonSil, GroupLayout.PREFERRED_SIZE, 100,
														GroupLayout.PREFERRED_SIZE))
										.addComponent(jScrollPaneRandevular, GroupLayout.PREFERRED_SIZE, 741,
												GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblDoktorlar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(jScrollPaneHasta, Alignment.LEADING).addComponent(jScrollPaneDoktor,
										Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1319, Short.MAX_VALUE)))
				.addGap(253)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING).addGroup(jPanel1Layout
				.createSequentialGroup().addGap(19)
				.addComponent(lblHastalar, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(jScrollPaneHasta, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addComponent(lblDoktorlar, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(jScrollPaneDoktor, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
				.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
												.addComponent(
														lblTarih, GroupLayout.PREFERRED_SIZE, 17,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18))
										.addGroup(jPanel1Layout.createSequentialGroup().addGap(18).addGroup(
												jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(
														jPanel1Layout.createParallelGroup(Alignment.BASELINE)
																.addComponent(jButtonRandevuEkle,
																		GroupLayout.PREFERRED_SIZE, 35,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(
																		jButtonGuncelle, GroupLayout.PREFERRED_SIZE, 38,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(jButtonSil, GroupLayout.PREFERRED_SIZE,
																		38, GroupLayout.PREFERRED_SIZE))
														.addGroup(jPanel1Layout.createSequentialGroup().addGap(1)
																.addGroup(jPanel1Layout
																		.createParallelGroup(Alignment.LEADING)
																		.addGroup(jPanel1Layout
																				.createParallelGroup(Alignment.BASELINE)
																				.addComponent(comboBoxSaat,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(lblSaat,
																						GroupLayout.PREFERRED_SIZE, 17,
																						GroupLayout.PREFERRED_SIZE))
																		.addGroup(jPanel1Layout
																				.createParallelGroup(Alignment.BASELINE)
																				.addComponent(lblDakika,
																						GroupLayout.PREFERRED_SIZE, 17,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(comboBoxDakika,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(lblRandevuyaGeldiMi,
																						GroupLayout.PREFERRED_SIZE, 17,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(chckbxGeldimi)))))
												.addGap(11))))
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(23)
								.addComponent(datePickerTarih, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)))
				.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(jScrollPaneRandevular, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
						.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textAreaRecete, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
								.addComponent(lblRandevular, GroupLayout.PREFERRED_SIZE, 17,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNot, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(textAreaNot, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
								.addComponent(lblRecete, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		jPanel1.setLayout(jPanel1Layout);

		JButton jButton3 = new JButton();
		jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton3ActionPerformed(e);
			}
		});
		jButton3.setText("Ana Menü");
		jButton3.setForeground(Color.RED);

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(Alignment.TRAILING)
										.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(layout.createSequentialGroup()
												.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 156,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 701, Short.MAX_VALUE)
												.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 222,
														GroupLayout.PREFERRED_SIZE)
												.addGap(504)))
								.addContainerGap()).addGroup(
										Alignment.TRAILING,
										layout.createSequentialGroup().addComponent(jButton3,
												GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
												.addGap(262)))));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(10)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 605, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);

		setSize(new Dimension(1366, 768));
		setLocationRelativeTo(null);
	}

	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}

	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}

		return "";
	}

	public String secilenTarih(JDatePickerImpl datePicker) {
		Date selectedDate = (Date) datePicker.getModel().getValue();
		if (selectedDate != null)
			return String.valueOf(selectedDate);
		else
			new Exception("Tarih Seçin");
		return "";
	}

	public void jButtonRandevuOlusturActionPerformed(ActionEvent evt) {
		try {
			String tarih = "";
			Date selectedValue = (Date) datePickerTarih.getModel().getValue();
			if (selectedValue != null) {
				java.sql.Date sqlDate = new java.sql.Date(selectedValue.getTime());
				tarih = String.valueOf(sqlDate);
			}
			String saat = comboBoxSaat.getSelectedItem().toString().replace(":00", "");
			String dakika = comboBoxDakika.getSelectedItem().toString();
			String not = textAreaNot.getText();
			String recete = textAreaRecete.getText();
			String doktorID = "";
			if (jTableDoktor.getSelectedRow() >= 0)
				doktorID = String.valueOf(tableModelDoktor.getValueAt(jTableDoktor.getSelectedRow(), 0));
			String hastaID = "";
			if (jTableHasta.getSelectedRow() >= 0)
				hastaID = String.valueOf(tableModelHasta.getValueAt(jTableHasta.getSelectedRow(), 0));

			if (saat.equals("") || dakika.equals("")) {

				JOptionPane.showMessageDialog(this, "Lütfen Boþ Alanlarý Doldurunuz!");

			} else if (hastaID.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Lütfen Hasta Seçin!");
			} else if (doktorID.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Lütfen Doktor Seçin!");
			} else if (tarih.equals("")) {

				JOptionPane.showMessageDialog(this, "Lütfen Tarih Seçin!");

			} else {
				ConnectDatabase connectDatabase = new ConnectDatabase();
				Connection connection = connectDatabase.getConnection();
				Statement statement = (Statement) connection.createStatement();
				ResultSet rs = statement.executeQuery("select id from randevular order by id desc limit 1");
				int maxID = 0;
				if (rs.next()) {
					maxID = rs.getInt(1);
				}
				maxID++;
				String sql = "INSERT INTO randevular ( tarih,saat,dakika, id, note, recete, doktor, hasta, geldimi)"
						+ " VALUES ('" + tarih + "', '" + saat + "','" + dakika + "', " + maxID + ",'" + not + "', '"
						+ recete + "', " + doktorID + ", " + hastaID + "," + chckbxGeldimi.isSelected() + ")";

				statement.executeUpdate(sql);
				this.reload();
				JOptionPane.showMessageDialog(null, "Randevu Baþarýyla Eklendi");

			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, "Veritabanýna baðlantý saðlanamadý! " + ex.toString());
		}
	}

	public void jButtonGuncelleActionPerformed(ActionEvent evt) {
		try {
			String tarih = "";
			Date selectedValue = (Date) datePickerTarih.getModel().getValue();
			if (selectedValue != null) {
				java.sql.Date sqlDate = new java.sql.Date(selectedValue.getTime());
				tarih = String.valueOf(sqlDate);
			}
			String saat = comboBoxSaat.getSelectedItem().toString().replace(":00", "");
			String dakika = comboBoxDakika.getSelectedItem().toString();
			String not = textAreaNot.getText();
			String recete = textAreaRecete.getText();
			String randevuID = String.valueOf(tableModelRandevular.getValueAt(jTableRandevular.getSelectedRow(), 0));
			String doktorID = "";
			if (jTableDoktor.getSelectedRow() >= 0)
				doktorID = String.valueOf(tableModelDoktor.getValueAt(jTableDoktor.getSelectedRow(), 0));
			String hastaID = "";
			if (jTableHasta.getSelectedRow() >= 0)
				hastaID = String.valueOf(tableModelHasta.getValueAt(jTableHasta.getSelectedRow(), 0));

			if (saat.equals("") || dakika.equals("")) {

				JOptionPane.showMessageDialog(this, "Lütfen Boþ Alanlarý Doldurunuz!");

			} else if (hastaID.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Lütfen Hasta Seçin!");
			} else if (doktorID.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Lütfen Doktor Seçin!");
			} else if (tarih.equals("")) {

				JOptionPane.showMessageDialog(this, "Lütfen Tarih Seçin!");

			} else {
				ConnectDatabase connectDatabase = new ConnectDatabase();
				Connection connection = connectDatabase.getConnection();
				Statement statement = (Statement) connection.createStatement();
				String sql = "Update randevular set tarih='" + tarih + "', saat='" + saat + "', dakika='" + dakika
						+ "', note='" + not + "', recete='" + recete + "', doktor=" + doktorID + ", hasta=" + hastaID
						+ ", geldimi=" + chckbxGeldimi.isSelected() + " where ID='" + randevuID + "'";

				statement.executeUpdate(sql);
				this.reload();
				JOptionPane.showMessageDialog(null, "Randevu Baþarýyla Güncellendi");

			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, "Veritabanýna baðlantý saðlanamadý! " + ex.toString());
		}
	}
	private void jButtonSilActionPerformed(ActionEvent evt) {

		try {
			ConnectDatabase connectDatabase = new ConnectDatabase();
			Connection connection = connectDatabase.getConnection();
			Statement statement = (Statement) connection.createStatement();
			if (jTableRandevular.getSelectedRow() >= 0) {
				String randevuID = String
						.valueOf(tableModelRandevular.getValueAt(jTableRandevular.getSelectedRow(), 0));
				String sql = " Delete from randevular where ID='" + randevuID + "'";
				statement.executeUpdate(sql);
				tableModelRandevular.removeRow(jTableRandevular.getSelectedRow());
				JOptionPane.showMessageDialog(null, "Randevu silme iþlemi baþarýlý bir þekilde gerçekleþtirildi");
			} else {
				JOptionPane.showMessageDialog(null, "Randevu Seçilmedi!");
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, "Veritabanýna baðlantý saðlanamadý! " + ex.toString());
		}
	}

	private void jTableRandevularMouseClicked(MouseEvent evt) {
		textAreaNot.setText(String.valueOf(tableModelRandevular.getValueAt(jTableRandevular.getSelectedRow(), 3)));
		textAreaRecete.setText(String.valueOf(tableModelRandevular.getValueAt(jTableRandevular.getSelectedRow(), 4)));
		if (String.valueOf(tableModelRandevular.getValueAt(jTableRandevular.getSelectedRow(), 8)).equals("true")) {
			chckbxGeldimi.setSelected(true);
		} else {
			chckbxGeldimi.setSelected(false);
		}
		String tarih = String.valueOf(tableModelRandevular.getValueAt(jTableRandevular.getSelectedRow(), 5));

		Date date = null;
		try {
			date = dateFormatter.parse(tarih);
			datePickerTarih.getModel().setDay(utilDateModel.getDay());
			datePickerTarih.getModel().setMonth(utilDateModel.getMonth());
			datePickerTarih.getModel().setYear(utilDateModel.getYear());
			datePickerTarih.getJFormattedTextField().setText(tarih);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String dakika = String.valueOf(tableModelRandevular.getValueAt(jTableRandevular.getSelectedRow(), 7));
		comboBoxDakika.setSelectedIndex(Integer.valueOf(dakika) / 10);

		String saat = String.valueOf(tableModelRandevular.getValueAt(jTableRandevular.getSelectedRow(), 6));
		comboBoxSaat.setSelectedIndex(Integer.valueOf(saat) - 9);

		String randevuHastasi = String.valueOf(tableModelRandevular.getValueAt(jTableRandevular.getSelectedRow(), 1));
		int hastaSatir = getRowByValue(tableModelHasta, randevuHastasi);
		jTableHasta.setRowSelectionInterval(hastaSatir, hastaSatir);
		String randevuDoktoru = String.valueOf(tableModelRandevular.getValueAt(jTableRandevular.getSelectedRow(), 2));
		int doktorSatir = getRowByValue(tableModelDoktor, randevuDoktoru);
		jTableDoktor.setRowSelectionInterval(doktorSatir, doktorSatir);

	}

	int getRowByValue(DefaultTableModel model, Object value) {
		for (int i = model.getRowCount() - 1; i >= 0; --i) {
			if (model.getValueAt(i, 0).toString().equals(value)) {
				return i;
			}
		}
		return -1;
	}

	private void reload() {
		Randevular frame = new Randevular();
		frame.setVisible(true);
		this.dispose();
	}

	public static void main(String args[]) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Randevular().setVisible(true);
			}
		});
	}

	private void jButton3ActionPerformed(ActionEvent evt) {

		Anasayfa frame = new Anasayfa();
		frame.setVisible(true);
		this.dispose();
	}

	private static UtilDateModel utilDateModel;
	private static JDatePanelImpl jdatePanel;
	private JTable jTableHasta;
	private JTable jTableDoktor;
	private JTable jTableRandevular;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JPanel jPanel1;
	private JScrollPane jScrollPaneDoktor;
	private JLabel lblSaat;
	private JLabel lblRandevuyaGeldiMi;
	private JLabel lblRecete;
	private JTextArea textAreaRecete;
	private JComboBox comboBoxDakika;
	private JComboBox comboBoxSaat;
	private JTextArea textAreaNot;
	private static JDatePickerImpl datePickerTarih;
	private JScrollPane jScrollPaneRandevular;
	private JCheckBox chckbxGeldimi;
	private JLabel lblRandevular;
}
