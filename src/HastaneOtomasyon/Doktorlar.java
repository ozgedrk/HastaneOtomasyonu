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

public class Doktorlar extends AbstractFrame {

	DefaultTableModel tableModel = new DefaultTableModel();

	public Doktorlar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Doktorlar.class.getResource("/Resimler/gazilogo.png")));
		setTitle("Kayseri Eğitim ve Araştırma Hastanesi - DOKTORLAR");
		initComponents();
		try {
			ConnectDatabase connectDatabase = new ConnectDatabase();
			Connection connection = connectDatabase.getConnection();
			Statement statement = (Statement) connection.createStatement();

			try (ResultSet resultSet = statement.executeQuery(
					"Select id, sicilno, email, adi, soyadi, sifre, cinsiyet, brans, akademik_unvan, calistigi_poliklinik, maas, durum from kullanicilar where rol = 'doktor'")) {
				int colcount = resultSet.getMetaData().getColumnCount();

				for (int i = 1; i <= colcount; i++)
					tableModel.addColumn(resultSet.getMetaData().getColumnName(i));
				while (resultSet.next()) {
					Object[] row = new Object[colcount];
					for (int i = 1; i <= colcount; i++)
						row[i - 1] = resultSet.getObject(i);
					tableModel.addRow(row);
				}
				jTable1.setModel(tableModel);
			}
			connection.close();
		} catch (Exception hata) {
			JOptionPane.showMessageDialog(null, "Hata!!" + hata);
		}

		yetkiKontrol();
	}

	protected void yetkiKontrol() {
		if (isAdmin()) {
			TxtAdi.setEnabled(true);
			TxtAdi.setEditable(true);
			TxtSoyadi.setEnabled(true);
			TxtSoyadi.setEditable(true);
			TxtBrans.setEnabled(true);
			TxtBrans.setEditable(true);
			TxtAkademikUnvan.setEditable(true);
			TxtAkademikUnvan.setEnabled(true);
			TxtCalistigiPoliklinik.setEditable(true);
			TxtCalistigiPoliklinik.setEnabled(true);
			TxtSicilNo.setEditable(true);
			TxtSicilNo.setEnabled(true);
			txtMaas.setEnabled(true);
			txtMaas.setEditable(true);
			chckbxDurum.setEnabled(true);
			jButtonKullaniciEkle.setEnabled(true);
			jButtonKullaniciSil.setEnabled(true);
			rdbtnErkek.setEnabled(true);
			rdbtnKadin.setEnabled(true);
		}
	}

	private void initComponents() {

		jLabel5 = new JLabel();
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
		jTable1 = new JTable();

		jLabel5.setText("jLabel5");

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

		jTable1.setFont(new Font("Tahoma", 1, 12));
		jTable1.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null } },
				new String[] { "id", "email", "adi", "soyadi", "sifre", "rol", "durum" }));
		jTable1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				jTable1MouseClicked(evt);
			}
		});
		jScrollPane3.setViewportView(jTable1);

		jPanel2 = new JPanel();

		chckbxDurum = new JCheckBox("Aktif");
		chckbxDurum.setEnabled(false);

		lblDurum = new JLabel();
		lblDurum.setText("Durum:");
		lblDurum.setForeground(Color.WHITE);
		lblDurum.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblCalistigiPoliklinik = new JLabel();
		lblCalistigiPoliklinik.setText("Çalıştığı Poliklinik:");
		lblCalistigiPoliklinik.setForeground(Color.WHITE);
		lblCalistigiPoliklinik.setFont(new Font("Tahoma", Font.BOLD, 14));

		TxtCalistigiPoliklinik = new JTextField();
		TxtCalistigiPoliklinik.setEnabled(false);

		JLabel lblBrans = new JLabel();
		lblBrans.setText("Branş:");
		lblBrans.setForeground(Color.WHITE);
		lblBrans.setFont(new Font("Tahoma", Font.BOLD, 14));

		TxtBrans = new JTextField();
		TxtBrans.setEnabled(false);

		JLabel lblAkademikUnvan = new JLabel();
		lblAkademikUnvan.setText("Akademik Unvan:");
		lblAkademikUnvan.setForeground(Color.WHITE);
		lblAkademikUnvan.setFont(new Font("Tahoma", Font.BOLD, 14));

		TxtAkademikUnvan = new JTextField();
		TxtAkademikUnvan.setEnabled(false);

		JScrollPane scrollPane = new JScrollPane();

		lblMaas = new JLabel();
		lblMaas.setText("Maaş:");
		lblMaas.setForeground(Color.WHITE);
		lblMaas.setFont(new Font("Tahoma", Font.BOLD, 14));

		lblSicilNoNumarasi = new JLabel();
		lblSicilNoNumarasi.setText("Sicil Numarası:");
		lblSicilNoNumarasi.setForeground(Color.WHITE);
		lblSicilNoNumarasi.setFont(new Font("Tahoma", Font.BOLD, 14));

		TxtSicilNo = new JTextField();
		TxtSicilNo.setEnabled(false);

		txtMaas = new JTextField();
		txtMaas.setEnabled(false);
		txtMaas.setEditable(false);

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
																.addComponent(lblMaas))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
																.addComponent(chckbxDurum)
																.addGroup(jPanel1Layout
																		.createParallelGroup(Alignment.TRAILING)
																		.addComponent(txtMaas,
																				GroupLayout.PREFERRED_SIZE, 138,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(jButtonKullaniciSil,
																				GroupLayout.PREFERRED_SIZE, 130,
																				GroupLayout.PREFERRED_SIZE)))))
										.addGap(18)
										.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
												.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
														.addComponent(lblAkademikUnvan, Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(lblBrans, Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
												.addComponent(lblSicilNoNumarasi, GroupLayout.DEFAULT_SIZE, 136,
														Short.MAX_VALUE)
												.addComponent(lblCalistigiPoliklinik, GroupLayout.DEFAULT_SIZE, 136,
														Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED, 15, GroupLayout.PREFERRED_SIZE)
										.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
												.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
														.addComponent(TxtBrans, GroupLayout.PREFERRED_SIZE, 138,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(TxtAkademikUnvan, GroupLayout.PREFERRED_SIZE, 138,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(jPanel1Layout.createSequentialGroup().addGap(2).addComponent(
														TxtCalistigiPoliklinik, GroupLayout.PREFERRED_SIZE, 138,
														GroupLayout.PREFERRED_SIZE))
												.addGroup(jPanel1Layout.createSequentialGroup().addGap(2).addComponent(
														TxtSicilNo, GroupLayout.PREFERRED_SIZE, 138,
														GroupLayout.PREFERRED_SIZE)))
										.addGap(28).addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 399,
												GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout
				.createSequentialGroup().addGap(36)
				.addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE).addGap(48)
				.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout
						.createSequentialGroup()
						.addComponent(TxtBrans, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE).addGap(20)
						.addComponent(TxtAkademikUnvan, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addGap(20)
						.addComponent(TxtCalistigiPoliklinik, GroupLayout.PREFERRED_SIZE, 29,
								GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(TxtSicilNo, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
						.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
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
										.addGap(20).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
												.addGroup(jPanel1Layout.createSequentialGroup().addGroup(
														jPanel1Layout.createParallelGroup(Alignment.BASELINE)
																.addComponent(TxtSoyadi, GroupLayout.DEFAULT_SIZE, 29,
																		Short.MAX_VALUE)
																.addComponent(
																		jLabel2, GroupLayout.PREFERRED_SIZE, 23,
																		GroupLayout.PREFERRED_SIZE))
														.addGap(26))
												.addGroup(jPanel1Layout.createSequentialGroup().addGap(1)
														.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
																.addComponent(lblMaas, GroupLayout.PREFERRED_SIZE, 17,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(txtMaas, GroupLayout.PREFERRED_SIZE, 29,
																		GroupLayout.PREFERRED_SIZE))
														.addGap(37)))
										.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
												.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 24,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(TxtSifre, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
										.addGap(11)
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
										.addComponent(lblBrans, GroupLayout.PREFERRED_SIZE, 17,
												GroupLayout.PREFERRED_SIZE)
										.addGap(26)
										.addComponent(lblAkademikUnvan, GroupLayout.PREFERRED_SIZE, 28,
												GroupLayout.PREFERRED_SIZE)
										.addGap(21)
										.addComponent(lblCalistigiPoliklinik, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE)
										.addGap(24).addComponent(lblSicilNoNumarasi, GroupLayout.PREFERRED_SIZE, 23,
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
				String sql = "INSERT INTO kullanicilar ( adi,soyadi,sifre,email, id, cinsiyet, durum, sicilno, brans, akademik_unvan, calistigi_poliklinik, maas, rol)"
						+ " VALUES ('" + TxtAdi.getText() + "', '" + TxtSoyadi.getText() + "', '" + TxtSifre.getText()
						+ "','" + TxtEmail.getText() + "', " + maxID + ",'" + cinsiyet + "', '"
						+ chckbxDurum.isSelected() + "', '" + TxtSicilNo.getText() + "', '" + TxtBrans.getText()
						+ "', '" + TxtAkademikUnvan.getText() + "', '" + TxtCalistigiPoliklinik.getText() + "', 0"
						+ txtMaas.getText().replace(",", ".") + ", 'doktor')";
				System.out.println(sql);

				statement.executeUpdate(sql);
				tableModel.insertRow(tableModel.getRowCount(), new Object[] { "", TxtAdi.getText(), TxtAdi.getText(),
						TxtSoyadi.getText(), TxtSifre.getText() });
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
						+ cinsiyet + "', durum='" + chckbxDurum.isSelected() + "', akademik_unvan='"
						+ TxtAkademikUnvan.getText() + "', brans='" + TxtBrans.getText() + "', calistigi_poliklinik='"
						+ TxtCalistigiPoliklinik.getText() + "', maas=0" + txtMaas.getText().replace(",", ".")
						+ ", sicilno='" + TxtSicilNo.getText() + "' where ID='" + jLabel5.getText() + "'";

				statement.executeUpdate(sql);
				this.reload();
				JOptionPane.showMessageDialog(null, "Kayıt güncelleme işlemi başarılı bir şekilde gerçekleştirildi");

			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, "Veritabanına bağlantı sağlanamadı! " + ex.toString());
		}

	}

	private void reload() {
		Doktorlar frame = new Doktorlar();
		frame.setVisible(true);
		this.dispose();
	}

	private void jTable1MouseClicked(MouseEvent evt) {
		TxtEmail.setText(String.valueOf(tableModel.getValueAt(jTable1.getSelectedRow(), 2)));
		TxtAdi.setText(String.valueOf(tableModel.getValueAt(jTable1.getSelectedRow(), 3)));
		TxtSoyadi.setText(String.valueOf(tableModel.getValueAt(jTable1.getSelectedRow(), 4)));
		TxtSifre.setText(String.valueOf(tableModel.getValueAt(jTable1.getSelectedRow(), 5)));
		jLabel5.setText(String.valueOf(tableModel.getValueAt(jTable1.getSelectedRow(), 0)));
		TxtCalistigiPoliklinik.setText(String.valueOf(tableModel.getValueAt(jTable1.getSelectedRow(), 9)));
		TxtBrans.setText(String.valueOf(tableModel.getValueAt(jTable1.getSelectedRow(), 7)));
		TxtAkademikUnvan.setText(String.valueOf(tableModel.getValueAt(jTable1.getSelectedRow(), 8)));
		TxtSicilNo.setText(String.valueOf(tableModel.getValueAt(jTable1.getSelectedRow(), 1)));
		txtMaas.setText(String.valueOf(tableModel.getValueAt(jTable1.getSelectedRow(), 10)));
		this.cinsiyetButonBelirle(String.valueOf(tableModel.getValueAt(jTable1.getSelectedRow(), 6)));
		if (String.valueOf(tableModel.getValueAt(jTable1.getSelectedRow(), 11)).equals("true")) {
			chckbxDurum.setSelected(true);
		} else {
			chckbxDurum.setSelected(false);
		}

	}

	private void jButton3ActionPerformed(ActionEvent evt) {

		try {
			ConnectDatabase connectDatabase = new ConnectDatabase();
			Connection connection = connectDatabase.getConnection();
			Statement statement = (Statement) connection.createStatement();

			String sql = " Delete from kullanicilar where ID='" + jLabel5.getText() + "'";
			statement.executeUpdate(sql);
			tableModel.removeRow(jTable1.getSelectedRow());
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
			Logger.getLogger(Doktorlar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(Doktorlar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(Doktorlar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			Logger.getLogger(Doktorlar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Doktorlar().setVisible(true);
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
	private JLabel jLabel5;
	private JPanel jPanel1;
	private JScrollPane jScrollPane3;
	private JTable jTable1;
	private JLabel lblCinsiyet;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblDurum;
	private JTextField TxtCalistigiPoliklinik;
	private JTextField TxtBrans;
	private JTextField TxtAkademikUnvan;
	private JLabel lblMaas;
	private JLabel lblSicilNoNumarasi;
	private JTextField TxtSicilNo;
	private JTextField txtMaas;
	private JPanel jPanel2;
}
