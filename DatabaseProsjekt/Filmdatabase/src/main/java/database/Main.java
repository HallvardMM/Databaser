package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import database.*;
import java.sql.*;

public class Main {

	public static void fetch()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection conn = DBConn.getDBConnection();
		Scanner myObj = new Scanner(System.in);
		System.out.println(
				"Ønsker du å finne navnet på alle rollene en gitt skuespiller har(X),\nfinne hvilke filmer som en gitt skuespiller opptrer i(Y),\neller finne hvilke filmselskap som lager flest filmer innen hver sjanger(Z)?");
		String answer = myObj.nextLine();

		if (answer.equals("X")) {
			fetch_roles(conn);
		} else if (answer.equals("Y")) {
			fetch_actors_movies(conn);
		} else if (answer.equals("Z")) {
			fetch_companies(conn);
		} else {
			System.out.println("Vennligst svar med enten X, Y eller Z");
			myObj.close();
			System.out.println("Scanner Closed.");
		}

	}

	private static void fetch_actors_movies(Connection co) throws SQLException {
		Scanner myObj = new Scanner(System.in);

		System.out.println(ResultatController.getPersons(co));

		System.out.println("Hvilken skuespiller ønsker du å finne filmene til?");
		int answer = myObj.nextInt();

		System.out.println(ResultatController.getFilms(co, answer));
	}

	private static void fetch_companies(Connection co) throws SQLException {
		Scanner myObj = new Scanner(System.in);
		System.out.println("Hvilken sjanger?");
		String answer = myObj.nextLine();

		System.out.println(ResultatController.getFilmselskap(co, answer));
	}

	private static void fetch_roles(Connection co) throws SQLException {
		Scanner myObj = new Scanner(System.in);

		System.out.println(ResultatController.getPersons(co));

		System.out.println("Hvilken skuespiller ønsker du å finne rollene til? Svar med personid.");
		int answer = myObj.nextInt();

		System.out.println(ResultatController.getRoller(co, answer));
	}

	public static void add()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Scanner myObj = new Scanner(System.in);
		System.out.println("Ønsker du å legge inn ny film(X) eller legge inn ny anmeldelse(Y)?");
		String answer = myObj.nextLine();

		if (answer.equals("X")) {
			add_film();
		} else if (answer.equals("Y")) {
			add_recommendation();
		} else {
			System.out.println("Vennligst svar med enten X eller Y");
			add();
		}

	}

	private static void eksistererPerson(String eksisterer, Connection co, Scanner myObj) throws SQLException {
		if (eksisterer.equals("Y")) {
			return;
		} else if (eksisterer.equals("N")) {
			addPerson(co, myObj);
		} else {
			System.out.println("Wrong input, start all over");
			myObj.close();
			System.out.println("Scanner Closed.");
		}
	}

	private static void addPerson(Connection co, Scanner myObj) throws SQLException {
		System.out.println("Skriv inn navn: ");
		String navn = myObj.nextLine();
		System.out.println("Skriv inn fødselsaar: ");
		int foedselsaar = Integer.parseInt(myObj.nextLine());
		System.out.println("Skriv inn fødselsland: ");
		String foedselsland = myObj.nextLine();
		AddController.addPerson(co, navn, foedselsaar, foedselsland);
		System.out.println("Nåværende personer: ");
		System.out.println(ResultatController.getPersons(co));
	}

	private static void add_skuespiller(Connection co, int filmID) throws SQLException {
		Scanner myObj = new Scanner(System.in);
		System.out.println("Ønsker du å legge til skuespiller? Y/N");
		String answer = myObj.nextLine();

		if (answer.equals("Y")) {
			System.out.println(ResultatController.getPersons(co));

			// Check if person exists
			System.out.println("Personen eksisterer(Y), Personen finnes ikke ikke(N):");
			String eksisterer = myObj.nextLine();
			eksistererPerson(eksisterer, co, myObj);

			System.out.println("Skriv inn personID (som et tall):");
			int personid = Integer.parseInt(myObj.nextLine());

			System.out.println("Skriv inn rolle:");
			String rolle = myObj.nextLine();

			AddController.addSkuespiller(co, personid, filmID, rolle);
		} else if (answer.equals("N")) {
			return;
		} else {
			System.out.println("Vennligst svar med Y eller N");
		}

	}

	private static void add_regissoer(Connection co, int filmID) throws SQLException {
		Scanner myObj = new Scanner(System.in);
		System.out.println("Ønsker du å legge til regissør? Y/N");
		String answer = myObj.nextLine();

		if (answer.equals("Y")) {
			System.out.println(ResultatController.getPersons(co));
			System.out.println("Personen eksisterer(Y), Personen finnes ikke ikke(N):");
			String eksisterer = myObj.nextLine();
			eksistererPerson(eksisterer, co, myObj);

			System.out.println("Skriv inn personID (som et tall):");
			int personid = Integer.parseInt(myObj.nextLine());

			AddController.addRegissoer(co, personid, filmID);

		} else if (answer.equals("N")) {
			return;
		} else {
			System.out.println("Vennligst svar med Y eller N");
		}
	}

	private static void add_manusforfatter(Connection co, int filmID) throws SQLException {
		Scanner myObj = new Scanner(System.in);
		System.out.println("Ønsker du å legge til manusforfatter? Y/N");
		String answer = myObj.nextLine();

		if (answer.equals("Y")) {
			System.out.println(ResultatController.getPersons(co));
			System.out.println("Personen eksisterer(Y), Personen finnes ikke ikke(N):");
			String eksisterer = myObj.nextLine();
			eksistererPerson(eksisterer, co, myObj);

			System.out.println("Skriv inn personID (som et tall):");
			int personid = Integer.parseInt(myObj.nextLine());

			AddController.addManusforfatter(co, personid, filmID);

		} else if (answer.equals("N")) {
			return;
		} else {
			System.out.println("Vennligst svar med Y eller N");
		}
	}

	private static void add_recommendation()
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Connection co = DBConn.getDBConnection();
		Scanner myObj = new Scanner(System.in);
		System.out.println(ResultatController.getSeries(co));
		System.out.println("Velg serie (med serieID): ");
		int serieID = Integer.parseInt(myObj.nextLine());

		System.out.println(ResultatController.getEpisodes(co, serieID));
		System.out.println("Velg episode du vil anmelde med (ID): ");
		int filmID = Integer.parseInt(myObj.nextLine());

		System.out.println("Nåværende brukere: ");
		System.out.println(ResultatController.getBrukere(co));

		// Check if user exists
		System.out.println("Brukeren eksisterer(Y), Brukeren eksisterer ikke(N):");
		String eksisterer = myObj.nextLine();
		eksistererBruker(eksisterer, co, myObj);

		System.out.println("Skriv inn brukerID: ");
		int brukerID = Integer.parseInt(myObj.nextLine());

		System.out.println("Skriv inn anmeldelsen her: ");
		String tekst = myObj.nextLine();

		System.out.println("Skriv inn rating(1-6)");
		int rating = Integer.parseInt(myObj.nextLine());

		AddController.addFilmAnmeldelse(co, brukerID, filmID, tekst, rating);
		myObj.close();
		System.out.println("Scanner Closed.");
	}

	private static void eksistererBruker(String eksisterer, Connection co, Scanner myObj) throws SQLException {
		if (eksisterer.equals("Y")) {
			return;
		} else if (eksisterer.equals("N")) {
			addBruker(co, myObj);
		} else {
			System.out.println("Wrong input, start all over");
			myObj.close();
			System.out.println("Scanner Closed.");
		}
	}

	private static void addBruker(Connection co, Scanner myObj) throws SQLException {
		System.out.println("Skriv inn navnet ditt: ");
		String navn = myObj.nextLine();
		System.out.println("Skriv inn eposten din: ");
		String epost = myObj.nextLine();
		System.out.println("Skriv inn passordet ditt: ");
		String passord = myObj.nextLine();
		AddController.addBruker(co, navn, epost, passord);
		System.out.println("Nåværende brukere: ");
		System.out.println(ResultatController.getBrukere(co));
	}

	private static void add_film()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		Scanner myObj = new Scanner(System.in);
		System.out.println("Hva er tittelen på filmen?");
		String tittel = myObj.nextLine();

		System.out.println("Hva er lengden på filmen?");
		int lengde = Integer.parseInt(myObj.nextLine());

		System.out.println("Hvilket år blir filmen gitt ut?");
		int utgivelsesaar = Integer.parseInt(myObj.nextLine());

		System.out.println("Når er lanseringsdato? (YYYY-MM-DD");
		String date = myObj.nextLine();
		Date lanseringsdato = Date.valueOf(date);

		System.out.println("Gi en kort beskrivelse av hva filmen handler om?");
		String storyline = myObj.nextLine();

		System.out.println("Hva er filmen laget for?");
		String lagetfor = myObj.nextLine();

		System.out.println("Er filmen utgitt på video? 0/1");
		int utgittpaavideo = Integer.parseInt(myObj.nextLine());

		Connection co = DBConn.getDBConnection();
		AddController.addFilm(co, tittel, lengde, utgivelsesaar, lanseringsdato, storyline, lagetfor, utgittpaavideo);

		List<Integer> filmIDs = new ArrayList<>();
		String statemnt = "SELECT * FROM Film WHERE Tittel=?";
		PreparedStatement prepStat = co.prepareStatement(statemnt);
		prepStat.setString(1, tittel);
		ResultSet result = prepStat.executeQuery();
		while (result.next()) {
			int filmID = result.getInt("FilmID");
			filmIDs.add(filmID);
		}
		int film = filmIDs.get(0);

		add_skuespiller(co, film);
		add_regissoer(co, film);
		add_manusforfatter(co, film);
		myObj.close();
		System.out.println("Scanner Closed.");
	}

	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Scanner myObj = new Scanner(System.in); // Create a Scanner object
		System.out.println("Ønsker du å hente info fra databasen(X), eller å legge til(Y)? ");
		String answer = myObj.nextLine(); // Read user input

		if (answer.equals("X")) {
			fetch();
		} else if (answer.equals("Y")) {
			add();
		} else {
			System.out.println("Vennligst svar med enten X eller Y");
			main(args);
		}

	}

}
