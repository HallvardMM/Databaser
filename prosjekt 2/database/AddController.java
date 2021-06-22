package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

//Legge inn i database
public class AddController {

	public static void addFilm(Connection co, String tittel, int lengde, int utgivelsesAar,
			Date lanseringsdato, String storyline, String lagetFor, int utgittPaaVideo) throws SQLException {
		
		String preQueryStatement = "INSERT INTO Film (Tittel, Lengde, Utgivelsesaar, Lanseringsdato, Storyline,"
				+ "LagetFor, UtgittPaaVideo) VALUES (?,?,?,?,?,?,?)";
		
		PreparedStatement prepStat = co.prepareStatement(preQueryStatement);
		
		prepStat.setString(1, tittel);
		prepStat.setInt(2, lengde);
		prepStat.setInt(3, utgivelsesAar);
		prepStat.setDate(4, lanseringsdato);
		prepStat.setString(5, storyline);
		prepStat.setString(6, lagetFor);
		prepStat.setInt(7, utgittPaaVideo);
		prepStat.execute();
		
		System.out.println("Added film");
	}
	
	public static void addPerson(Connection co, String navn, int foedselsaar, String foedselsland) throws SQLException {
		String preQueryStatement = "INSERT INTO Person (Navn, Foedselsaar, Foedselsland) VALUES (?,?,?)";
		PreparedStatement prepStat = co.prepareStatement(preQueryStatement);
		
		prepStat.setString(1, navn);
		prepStat.setInt(2, foedselsaar);
		prepStat.setString(3, foedselsland);
		prepStat.execute();
		
		System.out.println("Added person");
		
	}
	
	public static void addRegissoer(Connection co, int personID, int filmID) throws SQLException {
		String preQueryStatement = "INSERT INTO Regissoer (PersonID, FilmID) VALUES (?,?)";
		PreparedStatement prepStat = co.prepareStatement(preQueryStatement);
		
		prepStat.setInt(1, personID);
		prepStat.setInt(2, filmID);
		prepStat.execute();
		
		System.out.println("Regissør lagt til");
	}

	
	public static void addManusforfatter(Connection co, int personID, int filmID) throws SQLException {
		String preQueryStatement = "INSERT INTO Manusforfatter (PersonID, FilmID) VALUES (?,?)";
		PreparedStatement prepStat = co.prepareStatement(preQueryStatement);
		
		prepStat.setInt(1, personID);
		prepStat.setInt(2, filmID);
		prepStat.execute();
		
		System.out.println("Manusforfatter lagt til");
	}
	
	public static void addSkuespiller(Connection co, int personID, int filmID, String rolle) throws SQLException {
		String preQueryStatement = "INSERT INTO Skuespiller (PersonID, FilmID, Rolle) VALUES (?,?,?)";
		PreparedStatement prepStat = co.prepareStatement(preQueryStatement);
		
		prepStat.setInt(1, personID);
		prepStat.setInt(2, filmID);
		prepStat.setString(3, rolle);
		prepStat.execute();
		
		System.out.println("Skuespiller lagt til");
	}
	
	public static void addSelskap(Connection co, String navn, int filmID, String url, String land, String adresse) throws SQLException {
		String preQueryStatement = "INSERT INTO Selskap(Navn, URL, Land, Adresse) VALUES (?,?,?,?)";
		PreparedStatement prepStat = co.prepareStatement(preQueryStatement);
		
		prepStat.setString(1, navn);
		prepStat.setInt(2, filmID);
		prepStat.setString(3, url);
		prepStat.setString(4, adresse);
		prepStat.execute();
	}
	
	public static void addFilmSelskap(Connection co, int selskapsID, int filmID) throws SQLException {
		String preQueryStatement = "INSERT INTO FilmSelskap(SelskapsID, FilmID) VALUES (?,?)";
		PreparedStatement prepStat = co.prepareStatement(preQueryStatement);
		
		prepStat.setInt(1, selskapsID);
		prepStat.setInt(2, filmID);
		prepStat.execute();
	}
	
	public static void addSang(Connection co, String tittel, String skrevetAv, String fremfoertAv) throws SQLException {
		String preQueryStatement = "INSERT INTO Sang(Tittel, SkrevetAv, FremfoertAv) VALUES (?,?,?)";
		PreparedStatement prepStat = co.prepareStatement(preQueryStatement);
		
		prepStat.setString(1, tittel);
		prepStat.setString(2, skrevetAv);
		prepStat.setString(3, fremfoertAv);
		prepStat.execute();
	}
	
	public static void addSangIFilm(Connection co, int selskapsID, int filmID) throws SQLException {
		String preQueryStatement = "INSERT INTO SangIFilm(SelskapsID, FilmID) VALUES (?,?)";
		PreparedStatement prepStat = co.prepareStatement(preQueryStatement);
		
		prepStat.setInt(1, selskapsID);
		prepStat.setInt(2, filmID);
		prepStat.execute();
	}
	
	//Nederst høyre del av ER-diagrammet
	
	public static void addBruker(Connection co, String navn, String epost, String passord) throws SQLException {
		String preQueryStatement = "INSERT INTO Bruker(Navn, Epost, Passord) VALUES (?,?,?)";
		PreparedStatement prepStat = co.prepareStatement(preQueryStatement);
		
		prepStat.setString(1, navn);
		prepStat.setString(2, epost);
		prepStat.setString(3, passord);
		prepStat.execute();
		
		System.out.println("User added!");
	}
	
	public static void addSerie(Connection co, String navn) throws SQLException {
		String preQueryStatement = "INSERT INTO Serie(Navn) VALUES (?)";
		PreparedStatement prepStat = co.prepareStatement(preQueryStatement);
		
		prepStat.setString(1, navn);
		prepStat.execute();
	}
	
	public static void addKategori(Connection co, String navn) throws SQLException {
		String preQueryStatement = "INSERT INTO Kategori(Navn) VALUES (?,?,?)";
		PreparedStatement prepStat = co.prepareStatement(preQueryStatement);
		
		prepStat.setString(1, navn);
		prepStat.execute();
	}
	
	public static void addEpisodeISerie(Connection co, int serieID, int filmID, String episode, String sesong) throws SQLException {
		String preQueryStatement = "INSERT INTO EpisodeISerie(SerieID, FilmID, Episode, Sesong) VALUES (?,?,?,?)";
		PreparedStatement prepStat = co.prepareStatement(preQueryStatement);
		
		prepStat.setInt(1, serieID);
		prepStat.setInt(2, filmID);
		prepStat.setString(3, episode);
		prepStat.setString(4, sesong);
		prepStat.execute();
	}
	
	public static void addFilmAnmeldelse(Connection co, int brukerID, int filmID, String tekst, int rating) throws SQLException {
		String preQueryStatement = "INSERT INTO FilmAnmeldelse(BrukerID, FilmID, Tekst, Rating) VALUES (?,?,?,?)";
		PreparedStatement prepStat = co.prepareStatement(preQueryStatement);
		
		prepStat.setInt(1, brukerID);
		prepStat.setInt(2, filmID);
		prepStat.setString(3, tekst);
		prepStat.setInt(4, rating);
		prepStat.execute();
		
		System.out.println("Vurdering lagt til!");
	}
	
	public static void addFilmensKategori(Connection co, int kategoriID, int filmID) throws SQLException {
		String preQueryStatement = "INSERT INTO FilmensKategori(KategoriID, FilmID) VALUES (?,?)";
		PreparedStatement prepStat = co.prepareStatement(preQueryStatement);
		
		prepStat.setInt(1, kategoriID);
		prepStat.setInt(2, filmID);
		prepStat.execute();
	}
	
	public static void addSerieKategori(Connection co, int serieID, int kategoriID) throws SQLException {
		String preQueryStatement = "INSERT INTO SerieKategori(SerieID, KategoriID) VALUES (?,?)";
		PreparedStatement prepStat = co.prepareStatement(preQueryStatement);
		
		prepStat.setInt(1, serieID);
		prepStat.setInt(2, kategoriID);
		prepStat.execute();
	}
	
	public static void addSerieAnmeldelse(Connection co,int brukerID, int serieID, String tekst, int rating) throws SQLException {
		String preQueryStatement = "INSERT INTO SerieAnmeldelse(BrukerID, SerieID, Tekst, Rating) VALUES (?,?,?,?)";
		PreparedStatement prepStat = co.prepareStatement(preQueryStatement);
		
		prepStat.setInt(1, brukerID);
		prepStat.setInt(2, serieID);
		prepStat.setString(3, tekst);
		prepStat.setInt(4, rating);
		prepStat.execute();
	}
		
}
