package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Hente ut fra databasen
public class ResultatController {

	public static List<String> getRoller(Connection co, int personID) throws SQLException {
		List<String> roller = new ArrayList<>();
		String statemnt = "SELECT * FROM Skuespiller WHERE PersonID=?";
		PreparedStatement prepStat = co.prepareStatement(statemnt);
		prepStat.setInt(1, personID);
		ResultSet result = prepStat.executeQuery();
		while (result.next()) {
			String r = result.getString("Rolle");
			roller.add(r);
		}
		return roller;
	}

	public static List<Person> getPersons(Connection co) throws SQLException {
		List<Person> persons = new ArrayList<>();
		String statement = "SELECT Navn, PersonID FROM Person";
		PreparedStatement prepStat = co.prepareStatement(statement);
		ResultSet result = prepStat.executeQuery();
		while (result.next()) {
			Person p = new Person(result.getString("Navn"), result.getInt("PersonID"));
			persons.add(p);
		}
		return persons;
	}

	public static List<Serie> getSeries(Connection co) throws SQLException {
		List<Serie> serie = new ArrayList<>();
		String statement = "SELECT Navn, SerieID FROM Serie";
		PreparedStatement prepStat = co.prepareStatement(statement);
		ResultSet result = prepStat.executeQuery();
		while (result.next()) {
			Serie s = new Serie(result.getInt("SerieID"), result.getString("Navn"));
			serie.add(s);
		}
		return serie;
	}

	public static List<Episode> getEpisodes(Connection co, int serieID) throws SQLException {
		List<Episode> episoder = new ArrayList<>();
		String statement = "SELECT eis.FilmID, f.Tittel " + "FROM EpisodeISerie eis "
				+ "JOIN Film f ON eis.FilmID = f.FilmID " + "WHERE eis.SerieID = ?";
		PreparedStatement prepStat = co.prepareStatement(statement);
		prepStat.setInt(1, serieID);
		ResultSet result = prepStat.executeQuery();
		while (result.next()) {
			Episode e = new Episode(result.getInt("FilmID"), result.getString("Tittel"));
			episoder.add(e);
		}
		return episoder;
	}

	public static List<Bruker> getBrukere(Connection co) throws SQLException {
		List<Bruker> brukere = new ArrayList<>();
		String statemnt = "SELECT BrukerID, Navn FROM Bruker";
		PreparedStatement prepStat = co.prepareStatement(statemnt);
		ResultSet result = prepStat.executeQuery();
		while (result.next()) {
			Bruker b = new Bruker(result.getInt("BrukerID"), result.getString("Navn"));
			brukere.add(b);
		}
		return brukere;
	}

	public static List<String> getFilms(Connection co, int personID) throws SQLException {
		List<Integer> filmIDs = new ArrayList<>();
		List<String> films = new ArrayList<>();
		String statement = "SELECT FilmID FROM Skuespiller WHERE PersonID=?";
		PreparedStatement prepStat = co.prepareStatement(statement);
		prepStat.setInt(1, personID);
		ResultSet result = prepStat.executeQuery();
		while (result.next()) {
			filmIDs.add(result.getInt("FilmID"));
		}
		for (int i = 0; i < filmIDs.size(); i++) {
			String statement2 = "SELECT Tittel FROM Film WHERE FilmID=?";
			PreparedStatement prepStat2 = co.prepareStatement(statement2);
			prepStat2.setInt(1, filmIDs.get(i));
			ResultSet result2 = prepStat2.executeQuery();
			while (result2.next()) {
				films.add(result2.getString("Tittel"));
			}
		}
		return films;
	}

	public static List<String> getFilmselskap(Connection co, String sjanger) throws SQLException {
		List<String> filmselskap = new ArrayList<>();
		String statement = "SELECT s.Navn " + "FROM Kategori k "
				+ "JOIN FilmensKategori fk ON fk.KategoriID = k.KategoriID " + "JOIN Film f ON f.FilmID = fk.FilmID "
				+ "JOIN FilmSelskap fs ON fs.FilmID = f.FilmID " + "JOIN Selskap s ON s.SelskapsID = fs.SelskapsID "
				+ "WHERE k.navn =?";
		PreparedStatement prepStat = co.prepareStatement(statement);
		prepStat.setString(1, sjanger);
		ResultSet result = prepStat.executeQuery();
		while (result.next()) {
			filmselskap.add(result.getString("Navn"));
		}
		return filmselskap;
	}

	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection conn = DBConn.getDBConnection();
		System.out.println(getFilmselskap(conn, "Dokumentar"));
	}

}
