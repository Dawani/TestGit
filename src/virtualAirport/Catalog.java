package virtualAirport;

import java.util.ArrayList;
import java.util.Iterator;

public class Catalog implements Iterable<Product>{
	
	private ArrayList<Product> products = new ArrayList();

	public void addProduct(Product var1) {
		this.products.add(var1);
	}

	public Iterator<Product> iterator() {
		return this.products.iterator();
	}

//	public Product getProduct(String var1) {
//		Iterator var2 = this.products.iterator();
//
//		Product var3;
//		do {
//			if (!var2.hasNext()) {
//				return null;
//			}
//
//			var3 = (Product) var2.next();
//		} while (!var3.getCode().equals(var1));
//
//		return var3;
//	}

//	public boolean findClient(String name)
//	{	
//		String password = findClientPassword(name);
//		if (password != null)
//		{
//			
//		}
//		else 
//		{
//			
//		}
//		return false;
//	}
	
	public String findClientPassword(String name)
	{	
		String password = null;
		for (Product p : products)
		{	if(p instanceof Client)
			if (((Client)p).getName().equals(name))
			{
				password = ((Client)p).getPassword();
			}
		}
		return password;
	}
	
	public int getNumberOfProducts() {
		return this.products.size();
	}
	
	public String displayClientCatalog()
	{
		StringBuffer s = new StringBuffer();
		for (Product p : products)
		{	
			//下面为测试用例，请忽略
			//System.out.println("外面");
			if (p instanceof Client)
			{	
				//System.out.println("里面");
				s.append(p.toString()).append("\n");		
			}
		}
		return s.toString();
	}
	
	public String displayCatalog()
	{
		StringBuffer s = new StringBuffer();
		for (Product p : products)
		{
			if(p instanceof PlaneTicket)
			{	
				p = (PlaneTicket)p;
				s.append(p.toString());
				s.append("\n");		
			}
			s.append(p.toString()).append("\n");
		}
		return s.toString();
	}
	
	public String displayProductCatalog()
	{
		StringBuffer s = new StringBuffer();
		for (Product p : products)
		{
			if (!(p instanceof PlaneTicket))
			{
				s.append(p.toString()).append("\n");		
			}
		}
		return s.toString();
	}
	
	public Client getClient(String cName)
	{	
		Client c = null;
		for (Product p : products)
		{
			if (p instanceof Client)
			{
				if (((Client)p).getName().equals(cName))
				{
					c = (Client)p;
				    break;
				}
			}
		}
		return c;
	}
	
	public Product getProduct(String code)
	{	
		Product product = null;
		for (Product p : products)
		{
			if (p instanceof Product)
			{
				if (((Product)p).getCode().equals(code))
				{
					product = (Product)p;
				    break;
				}
			}
		}
		return product;
	}
	
	public PlaneTicket getPlaneTicket(String code)
	{	
		PlaneTicket ticket = null;
		for (Product p : products)
		{
			if (p instanceof PlaneTicket)
			{
				if (((PlaneTicket)p).getCode().equals(code))
				{
					ticket = (PlaneTicket)p;
				    break;
				}
			}
		}
		return ticket;
	}
}
