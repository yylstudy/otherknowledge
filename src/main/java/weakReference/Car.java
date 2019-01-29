package weakReference;

public class Car {
	private double price;  
    private String colour;
	public Car(double price, String colour) {
		super();
		this.price = price;
		this.colour = colour;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	} 
    
}
