package HastaneOtomasyon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectDatabase {
	private Connection connection;

	public ConnectDatabase() {
		try {
			Class.forName("org.postgresql.Driver");
			this.connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/hastane?useUnicode=true&characterEncoding=UTF-8", "postgres",
					"1234");
		} catch (ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Hata: " + ex.toString());
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Veritabanına bağlantı sağlanamadı " + ex.toString());
		}
	}

	public Connection getConnection() {
		return this.connection;
	}
}
