package virtualAirport;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class RestRoom extends Room {
	
	private String description;
    private HashMap<String, Room> exits = new HashMap<String, Room>();
    private static RestRoom singletonInstance = null;
    
    private Client client;
    private Catalog catalog;
    private Scanner in;
    
    private  RestRoom(String description) 
    {	
    	super(description);
        this.description = description;
    }
    
    public static RestRoom getRestRoom(String description)
    {	
    	if (singletonInstance == null)
    		singletonInstance = new RestRoom(description);
    	 return singletonInstance;
    }
    public String getDescription()
    {
    	return description;
    }
    public void run(Scanner in, Catalog catalog, Client client) throws IOException, DataFormatException, virtualAirport.DataFormatException 
    {	
    	this.catalog = catalog;
    	this.client = client;
    	this.in = in;
    	System.out.println("You're refreshed.Check bag?");
    	RestRoomRun(in, catalog, client);
    }
    
    private void RestRoomRun(Scanner in, Catalog catalog, Client client)  throws IOException, DataFormatException, virtualAirport.DataFormatException  {

		int  choice = getChoice(in);

		while (choice != 0)  {
			if (choice == 1)  {
				//System.out.println(catalog.displayProductCatalog());
				checkAllItems();
			} else if (choice == 2)  {
				checkAllTickets();
			} else if (choice == 3)  {
				checkAllProducts();
			} else if (choice == 4)  {
				checkSpecificItem();
			}

			choice = getChoice(in);
		}
	}
    
    public void checkAllItems() throws DataFormatException, virtualAirport.DataFormatException
    {	
    	String items =
    	client.handlerCommand("All_Item");
    	if (items == null)
    	{
    		System.out.println("������տ���Ҳ");
    	}
    	else
    	{
    		System.out.println("�����ж������£�");
    		System.out.println(items);
    	}
    }
    
    public void checkSpecificItem() throws DataFormatException, virtualAirport.DataFormatException
    {
    	System.out.println("��ͨ��ʳ����������Ʊʼ���ز����ض���Ʒ��");
    	System.out.print("����������/ʼ����>");
    	String des = in.nextLine();
    	String items = client.handlerCommand("Specific_Item_" + des);
    	if (items == null)
    	{
    		System.out.println("����������û�У�" + des);
    	}
    	else 
    	{
    		System.out.println("���ҵ���"+ des + "!");
    		System.out.println(items);
    	}
    }
    
    public void checkAllTickets() throws DataFormatException, virtualAirport.DataFormatException
    {
    	String items =
    	    	client.handlerCommand("All_Ticket");
    	    	if (items == null)
    	    	{
    	    		System.out.println("������û�л�Ʊ��");
    	    	}
    	    	else
    	    	{
    	    		System.out.println("�����л�Ʊ���£�");
    	    		System.out.println(items);
    	    	}
    }
    
    public void checkAllProducts() throws DataFormatException, virtualAirport.DataFormatException
    {
    	String items =
    	    	client.handlerCommand("All_Product");
    	    	if (items == null)
    	    	{
    	    		System.out.println("����û��ʳ�");
    	    	}
    	    	else
    	    	{
    	    		System.out.println("������ʳ�����£�");
    	    		System.out.println(items);
    	    	}
    }
    
    private int  getChoice(Scanner in) throws IOException  {

		int  input;

		do  {
			try  {
				System.out.println();
				System.out.print("[0]  Quit\n"
				             + "[1] Check AllItems\n"
				             + "[2] Check AllTickets\n"
				             + "[3] Check AllFoodProducts\n"
				             + "[4] Check SpecificItem\n"
				             + "choice> ");
				System.out.flush();

				input = Integer.parseInt(in.nextLine());

				System.out.println();

				if (0 <= input && 4 >= input)  {
					break;
				} else {
					System.out.println("Invalid choice:  " + input);
				}
			} catch (NumberFormatException  nfe)  {
				System.out.println(nfe);
			}
		}  while (true);

		return  input;
	}
    
    
    @Override
    public String toString()
    {
        return description;
    }
    
    public String getExitDesc()
    {	
    	StringBuffer sb = new StringBuffer();
    	for(String dir : exits.keySet())
    	{
    		sb.append(dir);
    		sb.append(" ");
    	}
    	return sb.toString();
    }
    
    public Room getExit(String direction)
    {
    	return exits.get(direction);
    }

}
