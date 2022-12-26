package HastaneOtomasyon;

import java.sql.Statement;
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
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.Toolkit;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.ListSelectionModel;

public class Hastalar extends AbstractFrame {

	DefaultTableModel tableModelHasta = new DefaultTableModel();
	DefaultTableModel tableModelHastaninRandevulari = new DefaultTableModel();

	public Hastalar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Hastalar.class.getResource("/Resimler/gazilogo.png")));
		setTitle("Kayseri Eğitim ve Araştırma Hastanesi - HASTALAR");
		initComponents();
		ArrayList<String> hastaArrayList = kolonBelirle("id", "tckimlik", "email", "adi", "soyadi", "sifre", "cinsiyet",
				"dogum_tarihi", "dogum_yeri", "adres", "randevu_alabilir", "onceligi_var", "durum");
		String hastaWhere = "";
		if(kullaniciRolu.equals("hasta")) {
			hastaWhere = " and tckimlik = '" + tckimlik + "'"; 
		}
		tabloyaVeriDoldur(hastaArrayList, "kullanicilar", "rol='hasta'" + hastaWhere, tableModelHasta, jTableHasta);
		yetkiKontrol();
	}

	protected void yetkiKontrol() {
		if (isAdmin()) {
			chckbxDurum.setEnabled(true);
			TxtAdi.setEnabled(true);
			TxtSoyadi.setEnabled(true);
			TxtDogumYeri.setEnabled(true);
			TxtDogumTarihi.setEnabled(true);
			TxtTcKimlik.setEnabled(true);
			rdbtnErkek.setEnabled(true);
			rdbtnKadin.setEnabled(true);
			jButtonKullaniciEkle.setEnabled(true);
			jButtonKullaniciSil.setEnabled(true);
		}
		if (!isHasta()) {
			chckbxRandevuAlabilir.setEnabled(true);
			chckbxOnceligiVar.setEnabled(true);
		}
		if (isDoktor()) {
			TxtAdres.setEnabled(false);
			TxtEmail.setEnabled(false);
			TxtSifre.setEnabled(false);
		}
	}

	private void initComponents() {

		jLabelHastaID = new JLabel();
		jPanel1 = new JPanel();
		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		jLabel3 = new JLabel();
		jLabel4 = new JLabel();
		TxtEmail = new JTextField();
		TxtSoyadi = new JTextField();
		TxtSoyadi.setEnabled(false);
		TxtAdi = new JTextField();
		TxtAdi.setEnabled(false);
		TxtSifre = new JTextField();
		jButtonKullaniciEkle = new JButton();
		jButtonKullaniciEkle.setEnabled(false);
		jButton2 = new JButton();
		jButtonKullaniciSil = new JButton();
		jButtonKullaniciSil.setEnabled(false);
		jScrollPane3 = new JScrollPane();
		jTableHasta = new JTable();

		jLabelHastaID.setText("jLabel5");

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		jPanel1.setBackground(new Color(102, 102, 102));
		jPanel1.setForeground(new Color(255, 255, 255));

		jLabel1.setFont(new Font("Tahoma", 1, 14));
		jLabel1.setForeground(new Color(255, 255, 255));
		jLabel1.setText("Adı:");

		jLabel2.setFont(new Font("Tahoma", 1, 14));
		jLabel2.setForeground(new Color(255, 255, 255));
		jLabel2.setText("Soyadı:");

		jLabel3.setFont(new Font("Tahoma", 1, 14));
		jLabel3.setForeground(new Color(255, 255, 255));
		jLabel3.setText("Şifre");

		jLabel4.setFont(new Font("Tahoma", 1, 14));
		jLabel4.setForeground(new Color(255, 255, 255));
		jLabel4.setText("Email:");

		jButtonKullaniciEkle.setFont(new Font("Tahoma", 1, 13));
		jButtonKullaniciEkle.setText("Kullanıcı Ekle");
		jButtonKullaniciEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setText("Güncelle");
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jButtonKullaniciSil.setText("Sil");
		jButtonKullaniciSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		jTableHasta.setFont(new Font("Tahoma", 1, 12));
		jTableHasta.setModel(tableModelHasta);
		jTableHasta.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				jTable1MouseClicked(evt);
			}
		});
		jScrollPane3.setViewportView(jTableHasta);

		jtableHastaRandevu = new JTable();
		jtableHastaRandevu.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "doktor", "not", "recete", "tarih", "saat", "dakika" }));
		jscrollPaneHastaRandevu = new JScrollPane();
		jscrollPaneHastaRandevu.setViewportView(jtableHastaRandevu);

		JPanel jPanel2 = new JPanel();

		chckbxDurum = new JCheckBox("Aktif");
		chckbxDurum.setEnabled(false);

		lblDurum = new JLabel();
		lblDurum.setText("Durum:");
		lblDurum.setForeground(Color.WHITE);
		lblDurum.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblAdres = new JLabel();
		lblAdres.setText("Adres:");
		lblAdres.setForeground(Color.WHITE);
		lblAdres.setFont(new Font("Tahoma", Font.BOLD, 14));

		TxtAdres = new JTextField();

		JLabel lblDogumYeri = new JLabel();
		lblDogumYeri.setText("Doğum Yeri:");
		lblDogumYeri.setForeground(Color.WHITE);
		lblDogumYeri.setFont(new Font("Tahoma", Font.BOLD, 14));

		TxtDogumYeri = new JTextField();
		TxtDogumYeri.setEnabled(false);

		JLabel lblDogumTarihi = new JLabel();
		lblDogumTarihi.setText("Doğum Tarihi:");
		lblDogumTarihi.setForeground(Color.WHITE);
		lblDogumTarihi.setFont(new Font("Tahoma", Font.BOLD, 14));

		TxtDogumTarihi = new JTextField();
		TxtDogumTarihi.setEnabled(false);

		lblRandevuAlabilir = new JLabel();
		lblRandevuAlabilir.setText("Randevu Alabilir:");
		lblRandevuAlabilir.setForeground(Color.WHITE);
		lblRandevuAlabilir.setFont(new Font("Tahoma", Font.BOLD, 14));

		chckbxRandevuAlabilir = new JCheckBox("Aktif");
		chckbxRandevuAlabilir.setEnabled(false);

		lblOnceligiVar = new JLabel();
		lblOnceligiVar.setText("Önceliği Var:");
		lblOnceligiVar.setForeground(Color.WHITE);
		lblOnceligiVar.setFont(new Font("Tahoma", Font.BOLD, 14));

		chckbxOnceligiVar = new JCheckBox("Aktif");
		chckbxOnceligiVar.setEnabled(false);

		lblTcKimlikNumarasi = new JLabel();
		lblTcKimlikNumarasi.setText("TC Kimlik Numarası:");
		lblTcKimlikNumarasi.setForeground(Color.WHITE);
		lblTcKimlikNumarasi.setFont(new Font("Tahoma", Font.BOLD, 14));

		TxtTcKimlik = new JTextField();
		TxtTcKimlik.setEnabled(false);

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(20)
						.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
								.addComponent(jScrollPane3, GroupLayout.DEFAULT_SIZE, 1300, Short.MAX_VALUE)
								.addGroup(jPanel1Layout.createSequentialGroup().addGroup(
										jPanel1Layout.createParallelGroup(Alignment.TRAILING).addGroup(jPanel1Layout
												.createSequentialGroup()
												.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(
														jPanel1Layout
																.createSequentialGroup()
																.addGroup(jPanel1Layout
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(jLabel2,
																				GroupLayout.PREFERRED_SIZE, 78,
																				GroupLayout.PREFERRED_SIZE)
																		.addGroup(jPanel1Layout.createSequentialGroup()
																				.addGap(2).addComponent(jLabel3,
																						GroupLayout.PREFERRED_SIZE, 84,
																						GroupLayout.PREFERRED_SIZE)))
																.addGap(36).addGroup(
																		jPanel1Layout
																				.createParallelGroup(Alignment.TRAILING)
																				.addComponent(TxtSoyadi,
																						GroupLayout.PREFERRED_SIZE, 138,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(TxtSifre,
																						GroupLayout.PREFERRED_SIZE, 138,
																						GroupLayout.PREFERRED_SIZE)))
														.addGroup(jPanel1Layout
																.createParallelGroup(Alignment.TRAILING, false)
																.addGroup(jPanel1Layout.createSequentialGroup()
																		.addComponent(jLabel4)
																		.addPreferredGap(ComponentPlacement.RELATED,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(TxtEmail,
																				GroupLayout.PREFERRED_SIZE, 138,
																				GroupLayout.PREFERRED_SIZE))
																.addGroup(jPanel1Layout.createSequentialGroup()
																		.addComponent(jLabel1,
																				GroupLayout.PREFERRED_SIZE, 86,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(34).addComponent(TxtAdi,
																				GroupLayout.PREFERRED_SIZE, 138,
																				GroupLayout.PREFERRED_SIZE))))
												.addGap(27)).addComponent(jButtonKullaniciEkle,
														GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
										.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 275,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(jPanel1Layout.createSequentialGroup().addPreferredGap(
														ComponentPlacement.RELATED)
														.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
																.addComponent(
																		jButton2, GroupLayout.DEFAULT_SIZE, 127,
																		Short.MAX_VALUE)
																.addGroup(jPanel1Layout.createSequentialGroup()
																		.addComponent(lblDurum,
																				GroupLayout.PREFERRED_SIZE, 63,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(53))
																.addGroup(jPanel1Layout
																		.createParallelGroup(Alignment.TRAILING)
																		.addComponent(lblOnceligiVar,
																				GroupLayout.PREFERRED_SIZE, 115,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(lblRandevuAlabilir,
																				Alignment.LEADING)))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
																.addComponent(chckbxOnceligiVar,
																		GroupLayout.PREFERRED_SIZE, 47,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(chckbxRandevuAlabilir,
																		GroupLayout.PREFERRED_SIZE, 47,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(chckbxDurum).addComponent(
																		jButtonKullaniciSil, GroupLayout.PREFERRED_SIZE,
																		130, GroupLayout.PREFERRED_SIZE))))
										.addGap(18)
										.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
												.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
														.addComponent(lblDogumTarihi, Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(lblDogumYeri, Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
												.addComponent(lblAdres, GroupLayout.PREFERRED_SIZE, 78,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblTcKimlikNumarasi, GroupLayout.DEFAULT_SIZE, 137,
														Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED, 16, GroupLayout.PREFERRED_SIZE)
										.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
												.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
														.addComponent(TxtDogumYeri, GroupLayout.PREFERRED_SIZE, 138,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(TxtDogumTarihi, GroupLayout.PREFERRED_SIZE, 138,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(jPanel1Layout.createSequentialGroup().addGap(2).addComponent(
														TxtAdres, GroupLayout.PREFERRED_SIZE, 138,
														GroupLayout.PREFERRED_SIZE))
												.addGroup(jPanel1Layout.createSequentialGroup().addGap(2).addComponent(
														TxtTcKimlik, GroupLayout.PREFERRED_SIZE, 138,
														GroupLayout.PREFERRED_SIZE)))
										.addGap(28).addComponent(jscrollPaneHastaRandevu, GroupLayout.PREFERRED_SIZE,
												399, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout
				.createSequentialGroup().addGap(36)
				.addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE).addGap(48)
				.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addComponent(TxtDogumYeri, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addGroup(jPanel1Layout.createSequentialGroup().addGap(20).addComponent(TxtDogumTarihi,
										GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
								.addGap(20)
								.addComponent(TxtAdres, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(TxtTcKimlik, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
						.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addComponent(jscrollPaneHastaRandevu, GroupLayout.DEFAULT_SIZE, 408,
												Short.MAX_VALUE)
										.addContainerGap())
								.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
										.createParallelGroup(Alignment.LEADING)
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
														.addComponent(TxtEmail, GroupLayout.PREFERRED_SIZE, 29,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel4, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addGap(18)
												.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
														.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 28,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(TxtAdi, GroupLayout.DEFAULT_SIZE, 29,
																Short.MAX_VALUE)
														.addComponent(lblDurum, GroupLayout.PREFERRED_SIZE, 17,
																GroupLayout.PREFERRED_SIZE)))
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(18).addComponent(chckbxDurum).addGap(24)))
										.addGap(20)
										.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
												.addGroup(jPanel1Layout.createSequentialGroup()
														.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
																.addComponent(TxtSoyadi, GroupLayout.DEFAULT_SIZE, 29,
																		Short.MAX_VALUE)
																.addComponent(
																		jLabel2, GroupLayout.PREFERRED_SIZE, 23,
																		GroupLayout.PREFERRED_SIZE))
														.addGap(26))
												.addGroup(jPanel1Layout.createSequentialGroup().addGap(1)
														.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
																.addComponent(lblRandevuAlabilir,
																		GroupLayout.PREFERRED_SIZE, 17,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(chckbxRandevuAlabilir))
														.addGap(37)))
										.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
												.addGroup(jPanel1Layout.createSequentialGroup()
														.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
																.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 24,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(TxtSifre, GroupLayout.DEFAULT_SIZE, 29,
																		Short.MAX_VALUE))
														.addGap(11))
												.addGroup(jPanel1Layout.createSequentialGroup().addGap(1)
														.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
																.addComponent(lblOnceligiVar,
																		GroupLayout.PREFERRED_SIZE, 17,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(chckbxOnceligiVar))
														.addGap(22)))
										.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
												.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
														.addComponent(jButtonKullaniciSil, GroupLayout.PREFERRED_SIZE,
																38, GroupLayout.PREFERRED_SIZE)
														.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 38,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(jPanel1Layout.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(jButtonKullaniciEkle, GroupLayout.PREFERRED_SIZE,
																35, GroupLayout.PREFERRED_SIZE)))
										.addGap(59))
								.addGroup(jPanel1Layout.createSequentialGroup().addGap(4)
										.addComponent(lblDogumYeri, GroupLayout.PREFERRED_SIZE, 17,
												GroupLayout.PREFERRED_SIZE)
										.addGap(26)
										.addComponent(lblDogumTarihi, GroupLayout.PREFERRED_SIZE, 28,
												GroupLayout.PREFERRED_SIZE)
										.addGap(21)
										.addComponent(lblAdres, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE)
										.addGap(24).addComponent(lblTcKimlikNumarasi, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap())))));

		lblCinsiyet = new JLabel();
		lblCinsiyet.setText("Cinsiyet");
		lblCinsiyet.setForeground(new Color(0, 0, 0));
		lblCinsiyet.setFont(new Font("Tahoma", Font.BOLD, 14));

		rdbtnErkek = new JRadioButton("Erkek");
		rdbtnErkek.setEnabled(false);
		rdbtnErkek.setSelected(true);
		buttonGroup.add(rdbtnErkek);

		rdbtnKadin = new JRadioButton("Kadın");
		rdbtnKadin.setEnabled(false);
		buttonGroup.add(rdbtnKadin);
		jPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		jPanel2.add(lblCinsiyet);
		jPanel2.add(rdbtnErkek);
		jPanel2.add(rdbtnKadin);
		jPanel1.setLayout(jPanel1Layout);
		jButton4 = new JButton();

		jButton4.setForeground(new Color(255, 0, 0));
		jButton4.setText("Ana Menü");
		jButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(1209).addComponent(jButton4,
										GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jPanel1,
										GroupLayout.DEFAULT_SIZE, 1330, Short.MAX_VALUE)))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 657, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(15, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);

		setSize(new Dimension(1366, 768));
		setLocationRelativeTo(null);
	}

	private void jButton1ActionPerformed(ActionEvent evt) {

		try {

			String uye_kadi = TxtEmail.getText();
			String adi = TxtAdi.getText();
			String Soyadi = TxtSoyadi.getText();
			String Sifre = TxtSifre.getText();
			String cinsiyet = this.cinsiyetTipBelirle();

			if (uye_kadi.equals("") || adi.equals("") || Soyadi.equals("") || Sifre.equals("") || cinsiyet.equals("")) {

				JOptionPane.showMessageDialog(this, "Lütfen Boş Alanları Doldurunuz!");

			} else {
				ConnectDatabase connectDatabase = new ConnectDatabase();
				Connection connection = connectDatabase.getConnection();
				Statement statement = (Statement) connection.createStatement();
				ResultSet rs = statement.executeQuery("select id from kullanicilar order by id desc limit 1");
				int maxID = 0;
				if (rs.next()) {
					maxID = rs.getInt(1);
				}
				maxID++;
				String sql = "INSERT INTO kullanicilar ( adi,soyadi,sifre,email, id, cinsiyet, durum, tckimlik, adres, dogum_yeri, dogum_tarihi, randevu_alabilir, onceligi_var,rol)"
						+ " VALUES ('" + TxtAdi.getText() + "', '" + TxtSoyadi.getText() + "', '" + TxtSifre.getText()
						+ "','" + TxtEmail.getText() + "', " + maxID + ",'" + cinsiyet + "', '"
						+ chckbxDurum.isSelected() + "', '" + TxtTcKimlik.getText() + "', '" + TxtAdres.getText()
						+ "', '" + TxtDogumYeri.getText() + "', '" + TxtDogumTarihi.getText() + "', '"
						+ chckbxRandevuAlabilir.isSelected() + "', '" + chckbxOnceligiVar.isSelected() + "', 'hasta'"
						+ ")";

				statement.executeUpdate(sql);
				this.reload();
				JOptionPane.showMessageDialog(null, "Kayıt işlemi başarılı bir şekilde gerçekleştirildi");

			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, "Veritabanına bağlantı sağlanamadı! " + ex.toString());
		}
	}

	private String cinsiyetTipBelirle() {
		String cinsiyet = "";
		if (rdbtnErkek.isSelected()) {
			cinsiyet = "Erkek";
		} else if (rdbtnKadin.isSelected()) {
			cinsiyet = "Kadın";
		}
		return cinsiyet;
	}

	private void cinsiyetButonBelirle(String cinsiyet) {
		if (cinsiyet.equals(rdbtnErkek.getText())) {
			rdbtnErkek.setSelected(true);
		} else if (cinsiyet.equals(rdbtnKadin.getText())) {
			rdbtnKadin.setSelected(true);
		}
	}

	private void jButton2ActionPerformed(ActionEvent evt) {

		try {
			String uye_kadi = TxtEmail.getText();
			String adi = TxtAdi.getText();
			String Soyadi = TxtSoyadi.getText();
			String Sifre = TxtSifre.getText();
			String cinsiyet = this.cinsiyetTipBelirle();

			if (uye_kadi.equals("") || adi.equals("") || Soyadi.equals("") || Sifre.equals("") || cinsiyet.equals("")) {

				JOptionPane.showMessageDialog(this, "Lütfen Boş Alanları Doldurunuz!");

			} else {
				ConnectDatabase connectDatabase = new ConnectDatabase();
				Connection connection = connectDatabase.getConnection();
				Statement statement = (Statement) connection.createStatement();
				String sql = "Update kullanicilar set email='" + TxtEmail.getText() + "', adi='" + TxtAdi.getText()
						+ "', soyadi='" + TxtSoyadi.getText() + "', sifre='" + TxtSifre.getText() + "', cinsiyet='"
						+ cinsiyet + "', durum='" + chckbxDurum.isSelected() + "', dogum_tarihi='"
						+ TxtDogumTarihi.getText() + "', dogum_yeri='" + TxtDogumYeri.getText() + "', adres='"
						+ TxtAdres.getText() + "', randevu_alabilir='" + chckbxRandevuAlabilir.isSelected()
						+ "', onceligi_var='" + chckbxOnceligiVar.isSelected() + "', tckimlik='" + TxtTcKimlik.getText()
						+ "' where ID='" + jLabelHastaID.getText() + "'";

				statement.executeUpdate(sql);
				this.reload();
				JOptionPane.showMessageDialog(null, "Kayıt güncelleme işlemi başarılı bir şekilde gerçekleştirildi");

			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, "Veritabanına bağlantı sağlanamadı! " + ex.toString());
		}

	}

	private void reload() {
		Hastalar frame = new Hastalar();
		frame.setVisible(true);
		this.dispose();
	}

	private void jTable1MouseClicked(MouseEvent evt) {
		TxtEmail.setText(String.valueOf(tableModelHasta.getValueAt(jTableHasta.getSelectedRow(), 2)));
		TxtAdi.setText(String.valueOf(tableModelHasta.getValueAt(jTableHasta.getSelectedRow(), 3)));
		TxtSoyadi.setText(String.valueOf(tableModelHasta.getValueAt(jTableHasta.getSelectedRow(), 4)));
		TxtSifre.setText(String.valueOf(tableModelHasta.getValueAt(jTableHasta.getSelectedRow(), 5)));
		jLabelHastaID.setText(String.valueOf(tableModelHasta.getValueAt(jTableHasta.getSelectedRow(), 0)));
		TxtAdres.setText(String.valueOf(tableModelHasta.getValueAt(jTableHasta.getSelectedRow(), 9)));
		TxtDogumYeri.setText(String.valueOf(tableModelHasta.getValueAt(jTableHasta.getSelectedRow(), 8)));
		TxtDogumTarihi.setText(String.valueOf(tableModelHasta.getValueAt(jTableHasta.getSelectedRow(), 7)));
		TxtTcKimlik.setText(String.valueOf(tableModelHasta.getValueAt(jTableHasta.getSelectedRow(), 1)));
		this.cinsiyetButonBelirle(String.valueOf(tableModelHasta.getValueAt(jTableHasta.getSelectedRow(), 6)));
		if (String.valueOf(tableModelHasta.getValueAt(jTableHasta.getSelectedRow(), 12)).equals("true")) {
			chckbxDurum.setSelected(true);
		} else {
			chckbxDurum.setSelected(false);
		}
		if (String.valueOf(tableModelHasta.getValueAt(jTableHasta.getSelectedRow(), 11)).equals("true")) {
			chckbxOnceligiVar.setSelected(true);
		} else {
			chckbxOnceligiVar.setSelected(false);
		}
		if (String.valueOf(tableModelHasta.getValueAt(jTableHasta.getSelectedRow(), 10)).equals("true")) {
			chckbxRandevuAlabilir.setSelected(true);
		} else {
			chckbxRandevuAlabilir.setSelected(false);
		}

		randevuGoster();

	}

	private void randevuGoster() {

		String hastaID = String.valueOf(tableModelHasta.getValueAt(jTableHasta.getSelectedRow(), 0));
		ArrayList<String> kolonlar = kolonBelirle("adi as doktor", "note", "recete", "tarih", "saat", "dakika");
		tableModelHastaninRandevulari = new DefaultTableModel();
		
		tabloyaVeriDoldur(kolonlar, "randevular as rand left join kullanicilar on rand.doktor = kullanicilar.id" ,
				"hasta=" + hastaID, tableModelHastaninRandevulari, jtableHastaRandevu);

	}

	private void jButton3ActionPerformed(ActionEvent evt) {

		try {
			ConnectDatabase connectDatabase = new ConnectDatabase();
			Connection connection = connectDatabase.getConnection();
			Statement statement = (Statement) connection.createStatement();

			String sql = " Delete from kullanicilar where ID='" + jLabelHastaID.getText() + "'";
			statement.executeUpdate(sql);
			tableModelHasta.removeRow(jTableHasta.getSelectedRow());
			JOptionPane.showMessageDialog(null, "Kayıt silme işlemi başarılı bir şekilde gerçekleştirildi");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, "Veritabanına bağlantı sağlanamadı! " + ex.toString());
		}
	}

	private void jButton4ActionPerformed(ActionEvent evt) {

		Anasayfa frame = new Anasayfa();
		frame.setVisible(true);
		this.dispose();
	}

	public static void main(String args[]) {

		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Hastalar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(Hastalar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(Hastalar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			Logger.getLogger(Hastalar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Hastalar().setVisible(true);
			}
		});
	}

	private JCheckBox chckbxDurum;
	private JRadioButton rdbtnErkek;
	private JRadioButton rdbtnKadin;
	private JTextField TxtAdi;
	private JTextField TxtSifre;
	private JTextField TxtSoyadi;
	private JTextField TxtEmail;
	private JButton jButtonKullaniciEkle;
	private JButton jButton2;
	private JButton jButtonKullaniciSil;
	private JButton jButton4;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabelHastaID;
	private JPanel jPanel1;
	private JScrollPane jScrollPane3;
	private JTable jTableHasta;
	private JLabel lblCinsiyet;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblDurum;
	private JTextField TxtAdres;
	private JTextField TxtDogumYeri;
	private JTextField TxtDogumTarihi;
	private JLabel lblRandevuAlabilir;
	private JCheckBox chckbxRandevuAlabilir;
	private JLabel lblOnceligiVar;
	private JCheckBox chckbxOnceligiVar;
	private JLabel lblTcKimlikNumarasi;
	private JTextField TxtTcKimlik;
	private JTable jtableHastaRandevu;
	private JScrollPane jscrollPaneHastaRandevu;
}
