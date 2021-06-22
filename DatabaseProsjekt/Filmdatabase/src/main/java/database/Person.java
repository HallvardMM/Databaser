package database;

public class Person {
	String navn;
	int personID;
	
	public Person(String navn, int personID) {
		this.navn = navn;
		this.personID = personID;
	}
	
	public String toString() {
		String string = "Navn: " + navn + " ID: " + personID;
		return string;
	}
	
}
