package virtualAirport;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Airport {
    private Room currentRoom;
    private HashMap<String, Commands> handlers = new 
    		HashMap<String, Commands>();    
    private HashMap<String, Room> functions= new 
    		HashMap<String, Room>();    
    private static Scanner in = new Scanner(System.in);
	private Catalog  catalog;
	private Catalog  clientCatalog;
    //客人登场！
	private Client client;
	
    public Airport() 
    {
    	handlers.put("go", new GoCommand(this));
    	handlers.put("bye", new ByeCommand(this));
    	handlers.put("help", new HelpCommand(this));
    	
    	functions.put("一楼服务总台", Conter.getConter("一楼服务总台"));
    	functions.put("二楼餐厅", Canteen.getCanteen("二楼餐厅"));
    	functions.put("一楼卫生间", RestRoom.getRestRoom("一楼卫生间"));
        createRooms();
        //错误的用户初始化方式
        //clent = new Client();
		System.out.println("尝试进行登录……");

        try {
			catalog =
					(new FileCatalogLoader()).loadCatalog("Product.txt");
			System.out.println("尝试获得机场信息……");
			clientCatalog =
					(new FileCatalogLoader()).loadClients("Client.txt");
			System.out.println("尝试获得用户信息……");
			//System.out.println(clientCatalog.displayClientCatalog());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		} catch (DataFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
    }

    private void createRooms()
    {
        Room outside, lobbyF1, lobbyF2,
        waitingArea,  pub, study, 
        VIPHall, canteen, restRoom,
        conter;

        //	制造房间
        outside = new Room("机场外广场");
        lobbyF1 = new Room("机场一楼大堂");
        lobbyF2 = new Room("机场二楼大堂");
        waitingArea = new Room("一楼休息区");
        pub = new Room("二楼小酒吧");
        study = new Room("机场二楼阅览室");
        VIPHall = new Room("二楼贵宾厅");
        restRoom = new Room("一楼卫生间");
        canteen = new Room("二楼餐厅");
        conter = new Room("一楼服务总台");
//         restRoom = RestRoom.getRestRoom("一楼卫生间");
//         canteen = Canteen.getCanteen("二楼餐厅");
//         conter = Conter.getConter("一楼服务总台");
        //	初始化房间的出口
        outside.setExit( "north", lobbyF1);
        lobbyF1.setExit("west", waitingArea);
        lobbyF1.setExit("north", conter);
        lobbyF1.setExit("east", restRoom);
        lobbyF1.setExit("up", lobbyF2);
        lobbyF1.setExit("south", outside);
        waitingArea.setExit("east", lobbyF1);
        conter.setExit("south", lobbyF1);
        restRoom.setExit("west", lobbyF1);
        //一楼设计完毕
        lobbyF2.setExit("west", pub);
        lobbyF2.setExit("north", VIPHall);
        lobbyF2.setExit("east", study);
        lobbyF2.setExit("south", canteen);
        lobbyF2.setExit("down", lobbyF1);
        pub.setExit("east", lobbyF2);
        study.setExit("west", lobbyF2);
        VIPHall.setExit("south", lobbyF2);
        canteen.setExit("north", lobbyF2);
        //二楼设计完毕
        currentRoom = outside;  
        //初始化为机场广场	
    }
//调用属性完成的方位判断作为返回值由Room类完成
    private boolean printWelcome() {
    	
        System.out.println();
        System.out.println("欢迎来到日照山字河网络虚拟机场！");
        //System.out.print("请输入你的姓名>");
        System.out.println("这是一个具有相当功能的现代化机场，彰显着海"
        		+ "滨城市日照的发展活力。");
        System.out.println("登录本界面你可以："
        		+ "\t1，订购机票2，退订机票3，订购餐厅\n"
        		+ "\t\t4，以文件方式输出账户信息5，探索机场\n"
        		);
        System.out.println("请输入姓名进行注册/登录");
       //下面是登陆界面
        String cName = in.nextLine();
        boolean logged = logIn(cName);
        
        if(logged)
        {
        	this.client = clientCatalog.getClient(cName);
        	File cFile = new File(cName + ".txt");
        	if(cFile.length() != 0)
	        	{
        		try {
					client.packageLoader();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (java.util.zip.DataFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        System.out.println("当前以ID：" + client.toString() + "登陆系统。");
        System.out.println("机场之旅开始：");
        System.out.println("如果需要机场智能助手"
        		+ "帮助，请输入 'help' 。");
        System.out.println();
        showPrompt();
        }
        return logged;
    }

    public boolean logIn(String cName)
    {	
    	 boolean logged = false;
    	
    	 String password = clientCatalog.findClientPassword(cName);
    	 if (password != null)
    	 {	
    		 String pW = null;
    		 System.out.println("你好" + cName + "请输入你的密码>");
     		 pW = in.nextLine();
     	
     		 boolean In = pW.equals(password);
     		 int choice = -2;
     		 
     		 while (!pW.equals(password))
     		 {
     			 System.out.print("Wrong Password!");
         		 
     			 choice = getChoice();
         		 
         		 if (choice == 0)break;
         		 
     			 pW = in.nextLine();
     		 }
     		 if (!In)
     		 {
     		 if (choice == 0) logged = false;
     		 else if (pW.equals(password)) 
     			 logged = true;
     		 }
     		 else 
     		 {
     			 logged = true;
     			 //client.
     		 }
    	 }
    	 else if (password ==  null)
    	 {
    		 System.out.println("你还没有账号，使用此用户名创建一个？");
    		 System.out.println("输入1继续，输入0退出。");

    		 String choice = in.nextLine();
    		 
    		 //int yChoice = Integer.parseInt(choice);
    		 if (choice == "1")
    		 {
    			 this.client = newAccont(cName);
    			 logged = true;
    		 }
    	 }
    	 return logged;
    }
    
    public int getChoice()
    {	
    	int i = 0;
    	System.out.println("输入1再次尝试；输入2找回密码；输入0退出");
    	i = Integer.parseInt(in.nextLine());
    	return i;
    }
    
    public Client newAccont(String name)
    {	
    	Client c = null;
    	System.out.println("请输入你的密码>");
    	String password = in.nextLine();
    	System.out.println("请输入初始金额>");
		double money = Double.parseDouble(in.nextLine());
		c = new Client(name, password, money);
		clientCatalog.addProduct(c);
		return c;
    }
    
    public void showPrompt()
    {
    	// 以下为用户命令
    	

//    	Room r = functions.get(currentRoom.getDescription());
    	
//    	if (r == null)
    		
//      	else
//      		System.out.println(r.getExitDesc());
		System.out.println("你可以输入bye退出，可以输入help求助，可以go 方向探索机场！");
		System.out.println("Bye和help命令仅可在一楼、二楼大厅中使用！");
		System.out.println("\n本程序可能因文件结尾中出现多余回车符出错\n"
				+ "如果不能正确运行请务必打开具体文件进行末尾回车符删除\n"
				+ "进行此操作后务必重复运行此程序，确保运行成功！");
		System.out.println("请注意，程序异常退出会导致文件不能保存，请务必谨慎操作！");
	    System.out.print("\n请务必从以下方向中选择： ");
	    System.out.println(currentRoom.getExitDesc());
    	System.out.println("预祝旅途愉快！");
    	System.out.print("Your Choice>");
    	System.out.println();
    }
    
    

    
	public void travel()
	{	
		//使用全局静态Scanner进行控制台读入
		//Scanner in = new Scanner(System.in);
		while ( true ) {
    		String line = in.nextLine();
    		//in.close();
    		String[] words = line.split(" ");
    		Commands handler = handlers.get(words[0]);
    		//这是命令类的哈希表，用于获取特定命令
    		String value = "";
    		if (words.length > 1)
    			value = words[1];
    		if (words[0].equals("help"))
    			handler.doCmd("help", null, in, catalog);
    		else
    		if((handler != null)&&!words[0].equals("bye"))
				{
    			Room nextRoom = currentRoom.getExit(value);
    			//handler.
    			System.out.println("你现在在" + nextRoom);
    			handler.doCmd(value, nextRoom.getDescription(), in, catalog);
    			
    		}
    		
		else if (handler.isBye())
				break;
    		}
            in.close();
    }
	
	
	public void goRoom(String direction) 
	{
		Room nextRoom = currentRoom.getExit(direction);
		//通过用户输入的方向，直接return一个相应的room对象
		//room类自身的nextExit属性被赋给currentRoom自身
		//这里只需要判断字符串进行相应赋值
		if (nextRoom == null) {
			System.out.println("此路不通！尝试前往其他方向>");
		}
		else {
			currentRoom = nextRoom;
			showPrompt();
		}
	}
	public void function(String word, Scanner in, Catalog catalog) {
		// TODO Auto-generated method stub
		Room fRoom = functions.get(word);
		try {
			try {
				fRoom.run(in, catalog, client);
			} catch (java.util.zip.DataFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DataFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void reloadClient()
	{
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("Client.txt"));
			for (Product p : clientCatalog)
			{
				if (p instanceof Client)
				{	
					p = (Client)p;
					pw.println(p.toString());
					
				}
			}
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		Airport ap = new Airport();
		
		if(ap.printWelcome())
		{
			ap.travel();
		}
        
		
		
		System.out.println("此次机场之行结束；我们更新了您的账户余额;\n在"
				+ "目录下生成了以您姓名命名的订单信息！");
		System.out.println("请注意，如果你没有购买产品，我们只会为您开辟账户；"
				+ "\n将不会为您生成订单文件！");
		System.out.println("感谢您的光临。再见！");
		ap.reloadClient();
		
		if(!ap.client.cI.products.isEmpty())
		{     
			StringBuffer s1 = new StringBuffer();
			StringBuffer s2 = new StringBuffer();
			for (Product p : ap.client.cI.products)
			{
				if (p instanceof PlaneTicket)
				{	
					p = (PlaneTicket)p;
					s1.append("Ticket_" + p.toString()).append("\n");
				}
				else s1.append("Product_" + p.toString()).append("\n");
			}
        	s1.append(s2);
			PrintWriter pw;
			try {
				pw = new PrintWriter(new FileWriter(ap.client.getName()+ ".txt"));
				pw.println(s1.toString());
				pw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
	}
		}
}
        



