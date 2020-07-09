package virtualAirport;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Canteen extends Room{
	
	
	private String description;
    private HashMap<String, Room> exits = new HashMap<String, Room>();
    private static Canteen singletonInstance = null;
    
    private Client client;
    private Catalog catalog;
    private Scanner in;
    
    private  Canteen(String description) 
    {	
    	super(description);
        this.description = description;
    }
    
    public static Canteen getCanteen(String description)
    {	
    	if (singletonInstance == null)
    		singletonInstance = new Canteen(description);
    	 return singletonInstance;
    }
    
    public void run(Scanner in, Catalog catalog, Client client)  throws IOException
    {	
    	this.catalog = catalog;
    	this.client = client;
    	this.in = in;
    	System.out.println("----��ǰ������������----");
    	conterRun(in, catalog, client);
    }
    
    
    private void conterRun(Scanner in, Catalog catalog, Client client)  throws IOException  {

		int  choice = getChoice(in);

		while (choice != 0)  {
			if (choice == 1)  {
				System.out.println(catalog.displayProductCatalog());
			} else if (choice == 2)  {
				System.out.println("�������빺���ʳ����룡");
				String des = in.nextLine();
				this.buyProduct(des);
			} 
//			else if (choice == 3)  {
//			} else if (choice == 4)  {
//			}

			choice = getChoice(in);
		}
	}
    
    public void buyProduct(String code)
    {
    	int choice = 0;
    	Product product = catalog.getProduct(code);
    	if (product != null)
    	{
    		System.out.println("��Ҫ���ʳ���ǣ�" + product.toString());
    		double d1 = client.payMoney(product.getPrice());
    		if (d1 < 0)
    		{	
    		do {
    				choice = 0;
    				if(d1 < 0)
    				{
    				d1 = 0 - d1;
    				System.out.println("����������" + d1 + "Ԫ");
    				}
    				d1 = client.getMoney() - product.getPrice();
    				System.out.println("����ʳ��ʣ��"+ d1 +"Ԫ");
    				System.out.println("ѡ��1��ֵ��0�˳���");
    				choice = Integer.parseInt(in.nextLine());
    				if (choice == 1) 
    				{
    				System.out.println("�����");
    				double money = Double.parseDouble(in.nextLine());
    				client.inputMoney(money);
    				}
    			}while(choice == 1);
    		}
    		if (d1 > 0 || d1 == 0)
    			{
    			client.addProduct(product);
    			System.out.println("��ɹ���������Ϊ" + product.getDescription() +  "��ʳ�");
    			System.out.println("��ȥһ¥�����䳢�������ʳ��ɣ�");
    			}
    	}
    	else
    	{
    		System.out.println("���޴˲ˣ�����ϸ�Ķ�ʳ����룡");
    	}
    }
    
    private int  getChoice(Scanner in) throws IOException  {

		int  input;

		do  {
			try  {
				System.out.println();
				System.out.print("[0]  Quit\n"
				             + "[1]  Display Menu\n"
				             + "[2]  Buy Food by Code\n"
//				             + "[3] \n"
//				             + "[4] \n"
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
    
    public String getDescription()
    {
    	return description;
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
