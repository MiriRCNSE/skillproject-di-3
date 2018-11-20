package main.java.model;

/*
 * Enum for the units of the ingredients.
 */
public enum Unit {
	MILLILITER("Milliliter"), 
	GRAMM("Gramm"), 
	STUECK("St�k"),
	TEELOEFFEL("Teel�ffel"), 
	ESSLOEFFEL("Essl�ffel"), 
	BRISE("Brise"), 
	SCHUSS("Schuss"),
	TASSE("Tasse");
	
	String unit;
	
	Unit(String unit) {
		this.unit = unit; 
	}
	
	public String getUnit() {
		return this.unit;
	}
}