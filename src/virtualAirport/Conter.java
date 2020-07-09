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
    	System.out.println("----当前场景功能如下----");
    	conterRun(in, catalog, client);
    }
    
    
    private void conterRun(Scanner in, Catalog catalog, Client client)  throws IOException  {

		int  choice = getChoice(in);

		while (choice != 0)  {
			if (choice == 1)  {
				System.out.println(catalog.displayCatalog());
			} else if (choice == 2)  {
				System.out.println("输入你想买的机票编码");
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
    	System.out.println("现在你在一楼！\n"
    			+ "大厅西侧：休息区；大厅东侧：卫生间；大厅北侧：服务台。\n"
    			+ "你可以从大堂前往二楼，\n"
    			+ "大厅西侧：小酒吧；大厅东侧：阅览室；大厅北侧：贵宾室；大厅南侧：餐厅。\n"
    			+ "由于资源有限，你可以前往所有场所\n"
    			+ "但是仅能使用：服务台、卫生间、二楼餐厅的功能！"
    			+ "注意，你仅能在卫生间查看自己的背包！"
    			+ "Have a good time!");
    }
    
    public void buyTicket(String code)
    {
    	int choice = 0;
    	PlaneTicket ticket = catalog.getPlaneTicket(code);
    	if (ticket != null)
    	{
    		System.out.println("你要买的机票是：" + ticket.toString());
    		double d1 = client.payMoney(ticket.getPrice());
    		if (d1 < 0)
    		{	
    		do {
    				choice = 0;
    				if(d1 < 0)
    				{
    				d1 = 0 - d1;
    				System.out.println("请至少输入" + d1 + "元");
    				}
    				d1 = client.getMoney() - ticket.getPrice();
    				System.out.println("买完机票剩余"+ d1 +"元");
    				System.out.println("\n选择1充值；0退出。");
    				System.out.println("\n如果有剩余金额可直接退出哦。");
    				choice = Integer.parseInt(in.nextLine());
    				if (choice == 1) 
    				{
    				System.out.println("输入金额：");
    				double money = Double.parseDouble(in.nextLine());
    				client.inputMoney(money);
    				}
    			}while(choice == 1);
    		}
    		if (d1 > 0 || d1 == 0)
    			{
    			client.setMoney(d1);
    			client.addProduct(ticket);
    			System.out.println("你成功购买了从" + ticket.getDeparture()
    			 + "起飞，到" + ticket.getDestination() + "的机票！");
    			System.out.println("快去卫生间看看新买的机票吧！");
    			}
    	}
    	else
    	{
    		System.out.println("查无此票！请仔细阅读机票编码！");
    	}
    }
    
    public void unsubscribeTicket()
    {
    	System.out.println("输入你要退订的机票始发地");
    	String dep = in.nextLine();
    	String yTicket = client.findTicket(dep);
    	System.out.println("已经找到这张机票：" + yTicket);
    	
    	StringTokenizer st = new StringTokenizer(yTicket, "_");
    	String code = st.nextToken();
    	dep = st.nextToken();
    	System.out.println("类型为" + dep + "的机票已退订。");
    	
    	PlaneTicket pt = catalog.getPlaneTicket(code);
    	System.out.println("退还" + pt.getPrice() +"元");
    	client.inputMoney(pt.getPrice());
    	System.out.println("你现在账户上有" + client.getMoney() + "元");
    	System.out.println("快去一楼卫生间看看机票还在不在吧！");
    	
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
