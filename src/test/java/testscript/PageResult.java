package testscript;

public class PageResult{
	String regNumber;
	String make;
	String colour;
	public PageResult(){
		
	}
	public PageResult(String regNumber, String make, String colour) {
		this.regNumber = regNumber;
		this.make = make;
		this.colour = colour;
	}	
	public String getRegNumber() {
		return regNumber;
	}
	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
}
