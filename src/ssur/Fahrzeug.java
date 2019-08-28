package ssur;

public class Fahrzeug 
{
	private float geschwindigkeit; //in km/h
	private float gewicht; //in kg
	private int farbcode;
	private int fahrzeugtyp;
	private float impuls;
	private float ekin;
	
	public Fahrzeug()
	{
		geschwindigkeit = 0;
		gewicht = 0;
		farbcode = 0;
		fahrzeugtyp = 0;
		impuls = 0;
		ekin = 0;		
	}
	
	public Fahrzeug(float geschwindigkeit, float gewicht, int farbcode, int fahrzeugtyp, float impuls, float ekin)
	{
		this.geschwindigkeit = geschwindigkeit;
		this.gewicht = gewicht;
		this.farbcode = farbcode;
		this.fahrzeugtyp = fahrzeugtyp;
		this.impuls = impuls;
		this.ekin = ekin;
	}
	
	public float getGeschwindigkeit()
	{
		return geschwindigkeit;
	}
	
	public void setGeschwindigkeit(float geschwindigkeit)
	{
		this.geschwindigkeit = geschwindigkeit;
	}
	
	public float getGewicht()
	{
		return gewicht;
	}
	
	public void setGewicht(float gewicht)
	{
		this.gewicht = gewicht;
	}
	
	public int getFarbcode()
	{
		return farbcode;
	}
	
	public void setFarbcode(int farbcode)
	{
		this.farbcode = farbcode;
	}
	
	public int getFahrzeugtyp()
	{
		return fahrzeugtyp;
	}
	
	public void setFahrzeugtyp(int fahrzeugtyp)
	{
		this.fahrzeugtyp = fahrzeugtyp;
	}
	
	public float getImpuls()
	{
		return impuls;
	}
	
	public void setImpuls(float impuls)
	{
		this.impuls = impuls;
	}
	
	public float getEkin()
	{
		return ekin;
	}
	
	public void setEkin(float ekin)
	{
		this.ekin = ekin;
	}
	
}
