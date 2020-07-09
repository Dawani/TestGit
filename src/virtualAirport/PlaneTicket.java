package virtualAirport;

public class PlaneTicket extends Product{
	
	private String departure;
	private String destination;
	private String departTime;
	
	public PlaneTicket(String code, String description, 
			Double price, 
			String departure, String destination,
			String departTime) {
		super(code, description, price);
		this.departure = departure;
		this.destination = destination;
		this.departTime = departTime;
	}


	public String getDestination() {
		return destination;
	}

	public String getDepartTime() {
		return departTime;
	}
	public String getDeparture() {
		return departure;
	}
	
	public String toString() 
	{
		return super.toString() + "_" 
	+ departure + "_" + destination + "_"  + departTime;
	}

}
