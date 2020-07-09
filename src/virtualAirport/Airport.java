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
    //���˵ǳ���
	private Client client;
	
    public Airport() 
    {
    	handlers.put("go", new GoCommand(this));
    	handlers.put("bye", new ByeCommand(this));
    	handlers.put("help", new HelpCommand(this));
    	
    	functions.put("һ¥������̨", Conter.getConter("һ¥������̨"));
    	functions.put("��¥����", Canteen.getCanteen("��¥����"));
    	functions.put("һ¥������", RestRoom.getRestRoom("һ¥������"));
        createRooms();
        //������û���ʼ����ʽ
        //clent = new Client();
		System.out.println("���Խ��е�¼����");

        try {
			catalog =
					(new FileCatalogLoader()).loadCatalog("Product.txt");
			System.out.println("���Ի�û�����Ϣ����");
			clientCatalog =
					(new FileCatalogLoader()).loadClients("Client.txt");
			System.out.println("���Ի���û���Ϣ����");
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

        //	���췿��
        outside = new Room("������㳡");
        lobbyF1 = new Room("����һ¥����");
        lobbyF2 = new Room("������¥����");
        waitingArea = new Room("һ¥��Ϣ��");
        pub = new Room("��¥С�ư�");
        study = new Room("������¥������");
        VIPHall = new Room("��¥�����");
        restRoom = new Room("һ¥������");
        canteen = new Room("��¥����");
        conter = new Room("һ¥������̨");
//         restRoom = RestRoom.getRestRoom("һ¥������");
//         canteen = Canteen.getCanteen("��¥����");
//         conter = Conter.getConter("һ¥������̨");
        //	��ʼ������ĳ���
        outside.setExit( "north", lobbyF1);
        lobbyF1.setExit("west", waitingArea);
        lobbyF1.setExit("north", conter);
        lobbyF1.setExit("east", restRoom);
        lobbyF1.setExit("up", lobbyF2);
        lobbyF1.setExit("south", outside);
        waitingArea.setExit("east", lobbyF1);
        conter.setExit("south", lobbyF1);
        restRoom.setExit("west", lobbyF1);
        //һ¥������
        lobbyF2.setExit("west", pub);
        lobbyF2.setExit("north", VIPHall);
        lobbyF2.setExit("east", study);
        lobbyF2.setExit("south", canteen);
        lobbyF2.setExit("down", lobbyF1);
        pub.setExit("east", lobbyF2);
        study.setExit("west", lobbyF2);
        VIPHall.setExit("south", lobbyF2);
        canteen.setExit("north", lobbyF2);
        //��¥������
        currentRoom = outside;  
        //��ʼ��Ϊ�����㳡	
    }
//����������ɵķ�λ�ж���Ϊ����ֵ��Room�����
    private boolean printWelcome() {
    	
        System.out.println();
        System.out.println("��ӭ��������ɽ�ֺ��������������");
        //System.out.print("�������������>");
        System.out.println("����һ�������൱���ܵ��ִ��������������ź�"
        		+ "���������յķ�չ������");
        System.out.println("��¼����������ԣ�"
        		+ "\t1��������Ʊ2���˶���Ʊ3����������\n"
        		+ "\t\t4�����ļ���ʽ����˻���Ϣ5��̽������\n"
        		);
        System.out.println("��������������ע��/��¼");
       //�����ǵ�½����
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
        System.out.println("��ǰ��ID��" + client.toString() + "��½ϵͳ��");
        System.out.println("����֮�ÿ�ʼ��");
        System.out.println("�����Ҫ������������"
        		+ "������������ 'help' ��");
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
    		 System.out.println("���" + cName + "�������������>");
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
    		 System.out.println("�㻹û���˺ţ�ʹ�ô��û�������һ����");
    		 System.out.println("����1����������0�˳���");

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
    	System.out.println("����1�ٴγ��ԣ�����2�һ����룻����0�˳�");
    	i = Integer.parseInt(in.nextLine());
    	return i;
    }
    
    public Client newAccont(String name)
    {	
    	Client c = null;
    	System.out.println("�������������>");
    	String password = in.nextLine();
    	System.out.println("�������ʼ���>");
		double money = Double.parseDouble(in.nextLine());
		c = new Client(name, password, money);
		clientCatalog.addProduct(c);
		return c;
    }
    
    public void showPrompt()
    {
    	// ����Ϊ�û�����
    	

//    	Room r = functions.get(currentRoom.getDescription());
    	
//    	if (r == null)
    		
//      	else
//      		System.out.println(r.getExitDesc());
		System.out.println("���������bye�˳�����������help����������go ����̽��������");
		System.out.println("Bye��help���������һ¥����¥������ʹ�ã�");
		System.out.println("\n������������ļ���β�г��ֶ���س�������\n"
				+ "���������ȷ��������ش򿪾����ļ�����ĩβ�س���ɾ��\n"
				+ "���д˲���������ظ����д˳���ȷ�����гɹ���");
		System.out.println("��ע�⣬�����쳣�˳��ᵼ���ļ����ܱ��棬����ؽ���������");
	    System.out.print("\n����ش����·�����ѡ�� ");
	    System.out.println(currentRoom.getExitDesc());
    	System.out.println("Ԥף��;��죡");
    	System.out.print("Your Choice>");
    	System.out.println();
    }
    
    

    
	public void travel()
	{	
		//ʹ��ȫ�־�̬Scanner���п���̨����
		//Scanner in = new Scanner(System.in);
		while ( true ) {
    		String line = in.nextLine();
    		//in.close();
    		String[] words = line.split(" ");
    		Commands handler = handlers.get(words[0]);
    		//����������Ĺ�ϣ�����ڻ�ȡ�ض�����
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
    			System.out.println("��������" + nextRoom);
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
		//ͨ���û�����ķ���ֱ��returnһ����Ӧ��room����
		//room�������nextExit���Ա�����currentRoom����
		//����ֻ��Ҫ�ж��ַ���������Ӧ��ֵ
		if (nextRoom == null) {
			System.out.println("��·��ͨ������ǰ����������>");
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
        
		
		
		System.out.println("�˴λ���֮�н��������Ǹ����������˻����;\n��"
				+ "Ŀ¼���������������������Ķ�����Ϣ��");
		System.out.println("��ע�⣬�����û�й����Ʒ������ֻ��Ϊ�������˻���"
				+ "\n������Ϊ�����ɶ����ļ���");
		System.out.println("��л���Ĺ��١��ټ���");
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
        



