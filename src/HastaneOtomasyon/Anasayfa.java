package HastaneOtomasyon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Label;

public class Anasayfa extends AbstractFrame {
	public Anasayfa() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				jButton2.setSize(280, 220);
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Anasayfa.class.getResource("/Resimler/gazilogo.png")));
		
		initComponents();
		this.setExtendedState(this.MAXIMIZED_BOTH);
		yetkiKontrol();
		jButton2.setSize(400, 300);
	}

	protected void yetkiKontrol() {
		if (isHasta()) {
			btnDoktorlar.setVisible(false);
		}
		if (!isAdmin()) {
			btnYonetim.setVisible(false);
		}
	}

	private void initComponents() {

		btnHastalar = new JButton();
		btnDoktorlar = new JButton();
		btnYonetim = new JButton();
		btnRandevular = new JButton();
		jButton2 = new JButton();
		jLabel1 = new JLabel();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Kayseri Eğitim ve Araştırma Hastanesi");
		setBackground(new Color(0, 0, 204));
		setForeground(Color.red);
		setLocationByPlatform(true);

		btnHastalar.setBackground(new Color(51, 102, 255));
		btnHastalar.setFont(new Font("Tahoma", 0, 35));
		btnHastalar.setIcon(new ImageIcon(Anasayfa.class.getResource("/Resimler/hastalar.jpg")));
		btnHastalar.setText("Hastalar");
		btnHastalar.setMaximumSize(new Dimension(400, 400));
		btnHastalar.setMinimumSize(new Dimension(300, 300));
		btnHastalar.setPreferredSize(new Dimension(100, 90));
		btnHastalar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		btnDoktorlar.setBackground(new Color(51, 102, 255));
		btnDoktorlar.setFont(new Font("Tahoma", 0, 35));
		btnDoktorlar.setIcon(new ImageIcon(getClass().getResource("/Resimler/doktorlar.jpg")));
		btnDoktorlar.setText("Doktorlar");
		btnDoktorlar.setMaximumSize(new Dimension(400, 400));
		btnDoktorlar.setMinimumSize(new Dimension(300, 300));
		btnDoktorlar.setPreferredSize(new Dimension(93, 100));
		btnDoktorlar.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton6ActionPerformed(evt);
			}
		});

		btnYonetim.setBackground(new Color(51, 102, 255));
		btnYonetim.setFont(new Font("Tahoma", 0, 35));
		btnYonetim.setIcon(new ImageIcon(getClass().getResource("/Resimler/yonetim.jpg")));
		btnYonetim.setText("Yönetim");
		btnYonetim.setHorizontalTextPosition(SwingConstants.LEFT);
		btnYonetim.setIconTextGap(-4);
		btnYonetim.setMaximumSize(new Dimension(400, 400));
		btnYonetim.setMinimumSize(new Dimension(300, 300));
		btnYonetim.setPreferredSize(new Dimension(93, 100));
		btnYonetim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton7ActionPerformed(evt);
			}
		});

		btnRandevular.setBackground(new Color(51, 102, 255));
		btnRandevular.setFont(new Font("Tahoma", 0, 35));
		btnRandevular.setIcon(new ImageIcon(Anasayfa.class.getResource("/Resimler/randevular.jpg")));
		btnRandevular.setText("Randevular");
		btnRandevular.setMaximumSize(new Dimension(400, 400));
		btnRandevular.setMinimumSize(new Dimension(300, 300));
		btnRandevular.setPreferredSize(new Dimension(93, 100));
		btnRandevular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton8ActionPerformed(evt);
			}
		});

		jButton2.setFont(new Font("Tahoma", 0, 35));
		jButton2.setIcon(new ImageIcon(Anasayfa.class.getResource("/Resimler/cikis.png")));
		jButton2.setMaximumSize(new Dimension(400, 400));
		jButton2.setMinimumSize(new Dimension(300, 300));
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jLabel1.setBackground(new Color(255, 255, 255));
		jLabel1.setIcon(new ImageIcon(Anasayfa.class.getResource("/Resimler/saglik_bakanligi.png")));

		JLabel gaziLogo = new JLabel("");
		gaziLogo.setIcon(new ImageIcon(Anasayfa.class.getResource("/Resimler/gazilogo.png")));

		JLabel lblOzgeDirik = new JLabel("Özge DİRİK - 191816023");

		JLabel lblIlkerOrtatepe = new JLabel("191816041 - İlker ORTATEPE");

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(75)
				.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnDoktorlar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnRandevular, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE))
				.addGap(30)
				.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnYonetim, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnHastalar, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
						.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(28))
				.addGroup(layout.createSequentialGroup().addGap(200).addComponent(lblOzgeDirik).addGap(107)
						.addComponent(gaziLogo).addGap(115).addComponent(lblIlkerOrtatepe)
						.addContainerGap(641, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addContainerGap(88, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
								.addGap(5).addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnRandevular, GroupLayout.PREFERRED_SIZE, 229,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnHastalar, GroupLayout.PREFERRED_SIZE, 230,
												GroupLayout.PREFERRED_SIZE))
								.addGap(24)
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnDoktorlar, GroupLayout.PREFERRED_SIZE, 230,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnYonetim, GroupLayout.PREFERRED_SIZE, 230,
												GroupLayout.PREFERRED_SIZE))))
				.addGap(18).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(lblIlkerOrtatepe)
						.addComponent(gaziLogo).addComponent(lblOzgeDirik))
				.addGap(109)));
		getContentPane().setLayout(layout);

		pack();
	}

	private void jButton7ActionPerformed(ActionEvent evt) {
		Yonetim frame = new Yonetim();
		frame.setVisible(true);
		this.dispose();
	}

	private void jButton1ActionPerformed(ActionEvent evt) {
		Hastalar frame = new Hastalar();
		frame.setVisible(true);
		this.setVisible(false);

	}

	private void jButton2ActionPerformed(ActionEvent evt) {
		this.kullaniciRolu = "";
		KullaniciGirisi frame = new KullaniciGirisi();
		frame.setVisible(true);
		this.setVisible(false);

	}

	private void jButton6ActionPerformed(ActionEvent evt) {
		Doktorlar frame = new Doktorlar();
		frame.setVisible(true);
		this.setVisible(false);

	}

	private void jButton8ActionPerformed(ActionEvent evt) {
		// randevular
		Randevular frame = new Randevular();
		frame.setVisible(true);
		this.setVisible(false);

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
			Logger.getLogger(Anasayfa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(Anasayfa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(Anasayfa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			Logger.getLogger(Anasayfa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Anasayfa().setVisible(true);

			}
		});
	}

	private JButton btnHastalar;
	private JButton jButton2;
	private JButton btnDoktorlar;
	private JButton btnYonetim;
	private JButton btnRandevular;
	private JLabel jLabel1;
}
