package musicinstruments;

public abstract class MusicInstrument implements IInstrument{
	
	private String name;
	private double price;
	private int quantity;
	private int numberOfSales;
	
	public MusicInstrument(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.numberOfSales=0;
		System.out.println("New instrument created: " + name);
	}
	
	
	 @Override
		public String toString() {
			return name + ": price=" + price + ", quantity=" + quantity;
		}

	public String getName() {
		return this.name;
	}
	
	public double getPrice() {
		return price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public int getQuantity() {
		return quantity;
	}

	public int getNumberOfSales() {
		return numberOfSales;
	}


	public void setNumberOfSales(int soldUnits) {
		this.numberOfSales +=soldUnits;
	}


	
	
	
}