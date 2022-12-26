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

public class Yonetim extends AbstractFrame {

	DefaultTableModel tableModel = new DefaultTableModel();

	public Yonetim() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Yonetim.class.getResource("/Resimler/gazilogo.png")));
		setTitle("Kayseri Eğitim ve Araştırma Hastanesi - YÖNETİM");
		initComponents();
		try {
			ConnectDatabase connectDatabase = new ConnectDatabase();
			Connection connection = connectDatabase.getConnection();
			Statement statement = (Statement) connection.createStatement();

			try (ResultSet resultSet = statement
					.executeQuery("Select id, email, adi, soyadi, sifre, rol, durum from kullanicilar")) {
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
		TxtAdi = new JTextField();
		TxtSifre = new JTextField();
		jButton1 = new JButton();
		jButton2 = new JButton();
		jButton3 = new JButton();
		jScrollPane3 = new JScrollPane();
		jTable1 = new JTable();
		jButton4 = new JButton();

		jLabel5.setText("jLabel5");

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

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

		jButton1.setFont(new Font("Tahoma", 1, 13));
		jButton1.setText("Kullanıcı Ekle");
		jButton1.addActionListener(new ActionListener() {
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

		jButton3.setText("Sil");
		jButton3.addActionListener(new ActionListener() {
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

		JPanel jPanel2 = new JPanel();

		chckbxDurum = new JCheckBox("Aktif");

		lblDurum = new JLabel();
		lblDurum.setText("Durum:");
		lblDurum.setForeground(Color.WHITE);
		lblDurum.setFont(new Font("Tahoma", Font.BOLD, 14));

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(20)
						.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
								.addComponent(jScrollPane3, GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
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
												.addGap(27)).addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 138,
														GroupLayout.PREFERRED_SIZE))
										.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 275,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
														.createParallelGroup(Alignment.TRAILING)
														.addGroup(Alignment.LEADING, jPanel1Layout
																.createSequentialGroup()
																.addComponent(lblDurum, GroupLayout.PREFERRED_SIZE, 63,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(chckbxDurum))
														.addComponent(jButton2, GroupLayout.DEFAULT_SIZE, 127,
																Short.MAX_VALUE))
														.addGap(18).addComponent(jButton3, GroupLayout.PREFERRED_SIZE,
																130, GroupLayout.PREFERRED_SIZE)))))
						.addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout
				.createSequentialGroup().addGap(36)
				.addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE).addGap(48)
				.addGroup(jPanel1Layout
						.createParallelGroup(
								Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(TxtEmail, GroupLayout.PREFERRED_SIZE, 29,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
								.addGap(18)
								.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 28,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(TxtAdi, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
										.addComponent(lblDurum, GroupLayout.PREFERRED_SIZE, 17,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(chckbxDurum))
								.addGap(20)
								.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(TxtSoyadi, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
										.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE))
								.addGap(26)
								.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 24,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(TxtSifre, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
								.addGap(11))
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)))
				.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
				.addGap(59)));

		lblRol = new JLabel();
		lblRol.setText("ROL:");
		lblRol.setForeground(new Color(0, 0, 0));
		lblRol.setFont(new Font("Tahoma", Font.BOLD, 14));

		rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setSelected(true);
		buttonGroup.add(rdbtnAdmin);

		rdbtnHasta = new JRadioButton("Hasta");
		buttonGroup.add(rdbtnHasta);

		rdbtnDoktor = new JRadioButton("Doktor");
		buttonGroup.add(rdbtnDoktor);
		jPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		jPanel2.add(lblRol);
		jPanel2.add(rdbtnAdmin);
		jPanel2.add(rdbtnHasta);
		jPanel2.add(rdbtnDoktor);
		jPanel1.setLayout(jPanel1Layout);

		jButton4.setForeground(new Color(255, 0, 0));
		jButton4.setText("Ana Menü");
		jButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap(19, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 852, GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(28, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(22)
						.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 528, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jButton4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);

		setSize(new Dimension(915, 636));
		setLocationRelativeTo(null);
	}

	private void jButton1ActionPerformed(ActionEvent evt) {

		try {

			String uye_kadi = TxtEmail.getText();
			String adi = TxtAdi.getText();
			String Soyadi = TxtSoyadi.getText();
			String Sifre = TxtSifre.getText();
			String rol = this.rolTipiBelirle();

			if (uye_kadi.equals("") || adi.equals("") || Soyadi.equals("") || Sifre.equals("") || rol.equals("")) {

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
				String sql = "INSERT INTO kullanicilar ( adi,soyadi,sifre,email, id, rol, durum)" + " VALUES ('"
						+ TxtAdi.getText() + "', '" + TxtSoyadi.getText() + "', '" + TxtSifre.getText() + "','"
						+ TxtEmail.getText() + "', " + maxID + ",'" + rol + "', '" + chckbxDurum.isSelected() + "'"
						+ ")";

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

	private String rolTipiBelirle() {
		String rol = "";
		if (rdbtnAdmin.isSelected()) {
			rol = "admin";
		} else if (rdbtnHasta.isSelected()) {
			rol = "hasta";
		} else if (rdbtnDoktor.isSelected()) {
			rol = "doktor";
		}
		return rol;
	}

	private void rolButonuBelirle(String rol) {
		if (rol.equals(rdbtnAdmin.getText().toLowerCase())) {
			rdbtnAdmin.setSelected(true);
		} else if (rol.equals(rdbtnHasta.getText().toLowerCase())) {
			rdbtnHasta.setSelected(true);
		} else if (rol.equals(rdbtnDoktor.getText().toLowerCase())) {
			rdbtnDoktor.setSelected(true);
		}
	}

	private void jButton2ActionPerformed(ActionEvent evt) {

		try {
			String uye_kadi = TxtEmail.getText();
			String adi = TxtAdi.getText();
			String Soyadi = TxtSoyadi.getText();
			String Sifre = TxtSifre.getText();
			String rol = this.rolTipiBelirle();

			if (uye_kadi.equals("") || adi.equals("") || Soyadi.equals("") || Sifre.equals("") || rol.equals("")) {

				JOptionPane.showMessageDialog(this, "Lütfen Boş Alanları Doldurunuz!");

			} else {
				ConnectDatabase connectDatabase = new ConnectDatabase();
				Connection connection = connectDatabase.getConnection();
				Statement statement = (Statement) connection.createStatement();
				String sql = "Update kullanicilar set email='" + TxtEmail.getText() + "', adi='" + TxtAdi.getText()
						+ "', soyadi='" + TxtSoyadi.getText() + "', sifre='" + TxtSifre.getText() + "', rol='" + rol
						+ "', durum='" + chckbxDurum.isSelected() + "' where ID='" + jLabel5.getText() + "'";

				statement.executeUpdate(sql);
				this.reload();
				JOptionPane.showMessageDialog(null, "Kayıt güncelleme işlemi başarılı bir şekilde gerçekleştirildi");

			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, "Veritabanına bağlantı sağlanamadı! " + ex.toString());
		}

	}

	private void reload() {
		Yonetim frame = new Yonetim();
		frame.setVisible(true);
		this.dispose();
	}

	private void jTable1MouseClicked(MouseEvent evt) {
		TxtEmail.setText(String.valueOf(tableModel.getValueAt(jTable1.getSelectedRow(), 1)));
		TxtAdi.setText(String.valueOf(tableModel.getValueAt(jTable1.getSelectedRow(), 2)));
		TxtSoyadi.setText(String.valueOf(tableModel.getValueAt(jTable1.getSelectedRow(), 3)));
		TxtSifre.setText(String.valueOf(tableModel.getValueAt(jTable1.getSelectedRow(), 4)));
		jLabel5.setText(String.valueOf(tableModel.getValueAt(jTable1.getSelectedRow(), 0)));
		this.rolButonuBelirle(String.valueOf(tableModel.getValueAt(jTable1.getSelectedRow(), 5)));
		if (String.valueOf(tableModel.getValueAt(jTable1.getSelectedRow(), 6)).equals("true")) {
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
			Logger.getLogger(Yonetim.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(Yonetim.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(Yonetim.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			Logger.getLogger(Yonetim.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Yonetim().setVisible(true);
			}
		});
	}

	private JCheckBox chckbxDurum;
	private JRadioButton rdbtnAdmin;
	private JRadioButton rdbtnHasta;
	private JRadioButton rdbtnDoktor;
	private JTextField TxtAdi;
	private JTextField TxtSifre;
	private JTextField TxtSoyadi;
	private JTextField TxtEmail;
	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton3;
	private JButton jButton4;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JPanel jPanel1;
	private JScrollPane jScrollPane3;
	private JTable jTable1;
	private JLabel lblRol;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblDurum;
}
