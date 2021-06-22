package database;

public class Bruker {
	int brukerID;
	String navn;
	
	public Bruker(int brukerID, String navn) {
		this.brukerID = brukerID;
		this.navn = navn;
	}
	
	public String toString() {
		String string = "Navn: " + navn + " ID: " + brukerID;
		return string;
	}
}
