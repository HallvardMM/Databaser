package database;

public class Serie {
	int serieID;
	String navn;

	public Serie(int serieID, String navn) {
		this.navn = navn;
		this.serieID = serieID;
	}

	public String toString() {
		String string = "Navn: " + navn + " ID: " + serieID;
		return string;
	}

}
