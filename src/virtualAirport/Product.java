package virtualAirport;

public class Product {
	private String code;
	private Double price;
	private String description;
	
	public Product(String code,  String description, Double price) {
		this.code = code;
		this.description = description;
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public Double getPrice() {
		return price;
	}

	
	public String toString()
	{
		return code + "_" + description + "_" + price;
	}
	
}
