package virtualAirport;

import java.util.ArrayList;
import java.util.Iterator;



public class ClientsItems implements Iterable<Product>{
	//暂定为公共链表
	public ArrayList<Product> products = new ArrayList();
	
	public void addItem(Product prouct)
	{
		products.add(prouct);
	}
	public void removeItem(Product prouct)
	{
		products.remove(prouct);
	}

	
	public String getAllItems()
	{	
		StringBuffer s = new StringBuffer();
		for (Product p : products)
		{
			if (p instanceof Product)
			{
				p = (Product) p;	
				s.append(p.toString()).append("\n");		
			}		
			
		}
		return s.toString();
	}
	
	public String getAllTickets()
	{
		StringBuffer s = new StringBuffer();
		for (Product p : products)
		{
			if (p instanceof PlaneTicket)
			{
				p = (PlaneTicket) p;	
				s.append(p.toString()).append("\n");		
			}
		}
		return s.toString();
	}
	
	
	public String getAllProducts()
	{
		StringBuffer s = new StringBuffer();
		for (Product p : products)
		{
			if (p!= null) {
				if (p.toString().startsWith("P"))
					s.append(p.toString()).append("\n");
			}
		}
		//System.out.print(s.toString() + "\n");

		return s.toString();
	}


	public String getSpecificItem(String des)
	{	
		String s = null;
		for (Product p : products)
		{
			if (p instanceof PlaneTicket)
			{
				if(des.equals(((PlaneTicket) p).getDeparture()))
					{
						s = p.toString();
					}

			}
			else 
			{
				s = p.toString();
			}
		}

	 return s;
	}
	@Override
	public Iterator<Product> iterator() {
		return this.products.iterator();
	}
}
