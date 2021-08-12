package classes.models;

public class Item {
	private static int count=0;
	private String name;
	private double price;
	private int id;
	
	public Item(String name, double price) {
		this.name = name;
		this.price = price;
		id=count;
		count++;
	}
	
	public Item() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("name:");
		builder.append(name);
		builder.append(", price:");
		builder.append(price);
		builder.append(", id:");
		builder.append(id);
		return builder.toString();
	}
	
	
}
