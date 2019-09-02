/**
 * Klasse fuer Fahrzeuge
 * @author egoesche
 */

package ssur;

public class Fahrzeug 
{
	private float geschwindigkeit; //in km/h
	private int fahrtrichtung;
	private float gewicht; //in kg
	private float startpunkt; //in m
	private String farbcode;
	private String fahrzeugtyp;
	private float impuls;
	private float ekin;
	
	public Fahrzeug()
	{
		geschwindigkeit = 0;
		fahrtrichtung = 0;
		gewicht = 0;
		startpunkt = 0;
		farbcode = "schwarz";
		fahrzeugtyp = "PKW";
		impuls = 0;
		ekin = 0;		
	}
	
	public Fahrzeug(float geschwindigkeit, int fahrtrichtung, float gewicht, float startpunkt, String farbcode, String fahrzeugtyp, float impuls, float ekin)
	{
		this.geschwindigkeit = geschwindigkeit;
		this.fahrtrichtung = fahrtrichtung;
		this.gewicht = gewicht;
		this.startpunkt = startpunkt;
		this.farbcode = farbcode;
		this.fahrzeugtyp = fahrzeugtyp;
		this.impuls = impuls;
		this.ekin = ekin;
	}
	
	//Getter und Setter
	public float getGeschwindigkeit() {return geschwindigkeit;}
	public void setGeschwindigkeit(float geschwindigkeit) {this.geschwindigkeit = geschwindigkeit;}
	
	public int getFahrtrichtung() {return fahrtrichtung;}
	public void setFahrtrichtung(int fahrtrichtung) {this.fahrtrichtung = fahrtrichtung;}
	
	public float getGewicht() {return gewicht;}
	public void setGewicht(float gewicht) {this.gewicht = gewicht;}
	
	public float getStartpunkt() {return startpunkt;}
	public void setStartpunkt(float startpunkt) {this.startpunkt = startpunkt;}
	
	public String getFarbcode() {return farbcode;}
	public void setFarbcode(String farbcode) {this.farbcode = farbcode;}
	
	public String getFahrzeugtyp() {return fahrzeugtyp;}
	public void setFahrzeugtyp(String fahrzeugtyp) {this.fahrzeugtyp = fahrzeugtyp;}
	
	public float getImpuls() {return impuls;}
	public void setImpuls(float impuls) {this.impuls = impuls;}
	
	public float getEkin() {return ekin;}
	public void setEkin(float ekin) {this.ekin = ekin;}
	
	
}