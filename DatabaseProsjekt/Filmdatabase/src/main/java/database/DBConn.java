package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import database.*;

public final class DBConn {

	// Statisk funksjon som kan brukes for aa koble seg opp mot databasen.
	// Passord og brukernavn ligger i koden, mulig det skal gaa inn som startargs
	// eller env. variabler
	public static Connection getDBConnection()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://mysql.stud.ntnu.no/sverrery_112?autoReconnect=true&useSSL=false";
		// Spør meg om tilgang til DB
		String user = "sverrery";
		String pass = "data112";

		Properties p = new Properties();
		p.put("user", user);
		p.put("password", pass);

		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection conn = DriverManager.getConnection(url, p);
			return conn;
		} catch (Exception e) {
			throw e;
		}

	}

	public static void main(String[] args)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Integer> filmIDs = new ArrayList<>();
		Connection co = DBConn.getDBConnection();
		String statemnt = "SELECT * FROM Film WHERE Tittel=?";
		PreparedStatement prepStat = co.prepareStatement(statemnt);
		prepStat.setString(1, "Birds");
		ResultSet result = prepStat.executeQuery();
		while (result.next()) {
			int filmID = result.getInt("FilmID");
			filmIDs.add(filmID);
		}
		int film = filmIDs.get(0);
		System.out.println(filmIDs.get(0));
	}
}