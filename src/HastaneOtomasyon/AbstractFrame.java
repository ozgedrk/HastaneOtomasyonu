package HastaneOtomasyon;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public abstract class AbstractFrame extends JFrame {
	public static String kullaniciRolu = "";
	public static String tckimlik = "";

	public AbstractFrame() {

	}

	public boolean isAdmin() {
		if (kullaniciRolu.equals("admin"))
			return true;
		else
			return false;
	}

	public boolean isDoktor() {
		if (kullaniciRolu.equals("doktor"))
			return true;
		else
			return false;
	}

	public boolean isHasta() {
		if (kullaniciRolu.equals("hasta"))
			return true;
		else
			return false;
	}

	protected abstract void yetkiKontrol();

	protected ArrayList<String> kolonBelirle(String... args) {
		ArrayList<String> arr = new ArrayList<String>();
		for (String string : args) {
			arr.add(string);
		}
		return arr;
	}

	protected void tabloyaVeriDoldur(ArrayList<String> kolonlar, String tabloAdi, String condition,
			DefaultTableModel tableModel, JTable jtable) {
		try {
			ConnectDatabase connectDatabase = new ConnectDatabase();
			Connection connection = connectDatabase.getConnection();
			Statement statement = (Statement) connection.createStatement();
			String sql = "Select ";
			if (kolonlar.contains("")) {
				sql += "*";
			}
			if (!tabloAdi.isEmpty()) {
				for (String string : kolonlar) {
					sql += string + ",";
				}
			}
			sql = sql.substring(0, sql.length() - 1);
			sql += " from " + tabloAdi;
			if (!condition.equals("")) {
				sql += " where " + condition;
			}
			try (ResultSet resultSet = statement.executeQuery(sql)) {
				int colcount = resultSet.getMetaData().getColumnCount();

				for (int i = 1; i <= colcount; i++)
					tableModel.addColumn(resultSet.getMetaData().getColumnName(i));
				while (resultSet.next()) {
					Object[] row = new Object[colcount];
					for (int i = 1; i <= colcount; i++)
						row[i - 1] = resultSet.getObject(i);
					tableModel.addRow(row);
				}
				jtable.setModel(tableModel);
			}
			connection.close();
		} catch (Exception hata) {
			JOptionPane.showMessageDialog(null, "Hata!!" + hata);
		}
	}
}
