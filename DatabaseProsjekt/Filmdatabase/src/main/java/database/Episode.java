package database;

public class Episode {
	int filmID;
	String tittel;
	
	public Episode(int filmID, String tittel) {
		this.filmID = filmID;
		this.tittel = tittel;
	}
	
	public String toString() {
		String string = "Tittel: " + tittel + " ID: " + filmID;
		return string;
	}
	
}
