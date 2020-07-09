package virtualAirport;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Conter extends Room{
	
	private String description;
    private HashMap<String, Room> exits = new HashMap<String, Room>();
    private static Conter singletonInstance = null;
    
    private Client client;
    private Catalog catalog;
    private Scanner in;
    
    private  Conter(String description) 
    {	
    	super(description);
        this.description = description;
    }
    
    public static Conter getConter(String description)
    {	
    	if (singletonInstance == null)
    		singletonInstance = new Conter(description);
    	 return singletonInstance;
    }
    
    public String getDescription()
    {
    	return description;
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
				System.out.println(catalog.displayCatalog());
			} else if (choice == 2)  {
				System.out.println("����������Ļ�Ʊ����");
				String code = in.nextLine();
				buyTicket(code);
			} else if (choice == 3)  {
				unsubscribeTicket();
			} else if (choice == 4)  {
				showMap();
			}

			choice = getChoice(in);
		}
	}
    
    public void showMap()
    {
    	System.out.println("��������һ¥��\n"
    			+ "�������ࣺ��Ϣ�����������ࣺ�����䣻�������ࣺ����̨��\n"
    			+ "����ԴӴ���ǰ����¥��\n"
    			+ "�������ࣺС�ưɣ��������ࣺ�����ң��������ࣺ����ң������ϲࣺ������\n"
    			+ "������Դ���ޣ������ǰ�����г���\n"
    			+ "���ǽ���ʹ�ã�����̨�������䡢��¥�����Ĺ��ܣ�"
    			+ "ע�⣬�������������鿴�Լ��ı�����"
    			+ "Have a good time!");
    }
    
    public void buyTicket(String code)
    {
    	int choice = 0;
    	PlaneTicket ticket = catalog.getPlaneTicket(code);
    	if (ticket != null)
    	{
    		System.out.println("��Ҫ��Ļ�Ʊ�ǣ�" + ticket.toString());
    		double d1 = client.payMoney(ticket.getPrice());
    		if (d1 < 0)
    		{	
    		do {
    				choice = 0;
    				if(d1 < 0)
    				{
    				d1 = 0 - d1;
    				System.out.println("����������" + d1 + "Ԫ");
    				}
    				d1 = client.getMoney() - ticket.getPrice();
    				System.out.println("�����Ʊʣ��"+ d1 +"Ԫ");
    				System.out.println("\nѡ��1��ֵ��0�˳���");
    				System.out.println("\n�����ʣ�����ֱ���˳�Ŷ��");
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
    			client.setMoney(d1);
    			client.addProduct(ticket);
    			System.out.println("��ɹ������˴�" + ticket.getDeparture()
    			 + "��ɣ���" + ticket.getDestination() + "�Ļ�Ʊ��");
    			System.out.println("��ȥ�����俴������Ļ�Ʊ�ɣ�");
    			}
    	}
    	else
    	{
    		System.out.println("���޴�Ʊ������ϸ�Ķ���Ʊ���룡");
    	}
    }
    
    public void unsubscribeTicket()
    {
    	System.out.println("������Ҫ�˶��Ļ�Ʊʼ����");
    	String dep = in.nextLine();
    	String yTicket = client.findTicket(dep);
    	System.out.println("�Ѿ��ҵ����Ż�Ʊ��" + yTicket);
    	
    	StringTokenizer st = new StringTokenizer(yTicket, "_");
    	String code = st.nextToken();
    	dep = st.nextToken();
    	System.out.println("����Ϊ" + dep + "�Ļ�Ʊ���˶���");
    	
    	PlaneTicket pt = catalog.getPlaneTicket(code);
    	System.out.println("�˻�" + pt.getPrice() +"Ԫ");
    	client.inputMoney(pt.getPrice());
    	System.out.println("�������˻�����" + client.getMoney() + "Ԫ");
    	System.out.println("��ȥһ¥�����俴����Ʊ���ڲ��ڰɣ�");
    	
    	client.removeProduct(pt);
    }
    
    private int  getChoice(Scanner in) throws IOException  {

		int  input;

		do  {
			try  {
				System.out.println();
				System.out.println("[0]  Quit\n"
				             + "[1]  Display TicketCatalog\n"
				             + "[2]  Book Your Ticket\n"
				             + "[3]  Unsubscribe Your Ticket\n"
				             + "[4]  Shoe Me the Map\n"
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
