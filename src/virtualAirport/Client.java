package virtualAirport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.zip.DataFormatException;

public class Client extends Product {
		
	private String name;
	private String password;
	private double money;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassWord(String passWord) {
		this.password = passWord;
	}

	public double getMoney() {
		return money;
	}
	
	public String toString()
	{
		return "Client"+"_"+name +"_"+password+"_"+money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public Client(String name, String password, Double money) {
		super(name, password, money);
		this.name = name;
		this.password = password;
		this.money = money;
	}
	
	public double payMoney(double price)
	{
		double nowMoney = this.getMoney() - price;
		double m1 = 0 - nowMoney;
		if (nowMoney < 0)
			{
				System.out.println("��û����ô��Ǯ������" + m1 + "Ԫ");
				
			}
		else 
		{
			System.out.println("����ɹ����˻���ʣ" + nowMoney + "Ԫ");
			this.setMoney(nowMoney);
		}
		return nowMoney;
	}
	
	public double inputMoney(double money)
	{	
		this.setMoney(money + this.money);
		double cMoney = this.getMoney();
		return cMoney;
	}
	
	private static final String ALL_PREFIX = "All";
	private static final String SPECIFIC_PREFIX = "Specific";
	
	private static final String ITEM_PREFIX = "Item";
	private static final String PRODUCT_PREFIX = "Product";
	private static final String TICKET_PREFIX = "Ticket";
	private static final String PLANETICKET_PREFIX = "Ticket";
	private static final String CLIENT_PREFIX = "Client";
	
	
	private static final String DELIM = "_";
	//�ݶ�Ϊpublic����
	 public ClientsItems cI = new ClientsItems();
	
	
	public String handlerCommand(String command) throws DataFormatException, virtualAirport.DataFormatException
	{	
		String headCode = null;
		String tailCode = null;
		boolean handled = false;
		
		StringTokenizer st = new StringTokenizer(command, DELIM);
		headCode = st.nextToken();
		tailCode = st.nextToken();
//		System.out.println(headCode);
//		System.out.println(tailCode);
		
		boolean format = handlerFormat(headCode,tailCode);

		String result = null;
//		System.out.println("����");
		if (format)
		{	
			handled = true;
			if (headCode.equals(ALL_PREFIX)) {
				//System.out.println("����");
				result = this.handlerAllTailCommand(tailCode, handled);
			}
			else if (headCode.equals(SPECIFIC_PREFIX)) {
				result = this.handlerSpecificTailCommand(st.nextToken(), handled);
			}
			//���������Ҫͨ������Ҫ�ض�λ��Ʒ
		}else
		{
			throw new DataFormatException(command);
		}
		
		if (handled)
		return result;
		else 
			throw new DataFormatException(command);
	}
	
	
		public void addProduct(Product product)
		{
			cI.addItem(product);
		}
		
		public String findTicket(String dep)
		{
			return cI.getSpecificItem(dep);
		}
		
		public void removeProduct(Product product)
		{
			cI.removeItem(product);
		}
	
		
		public String handlerAllTailCommand(String tailCode, boolean handled)
		{
			handled = true;
			String s = null;
				if (tailCode.equals(TICKET_PREFIX))
				{	
					 s = cI.getAllTickets();
				}
				else if (tailCode.equals(PRODUCT_PREFIX))
				{	
					 s = cI.getAllProducts();
				}
				else if (tailCode.equals(ITEM_PREFIX))
				{	
//					System.out.println("������");
					s = cI.getAllItems();
//					System.out.println(s);
				}
			return s;
		}
		
		
		public String handlerSpecificTailCommand( String lastCode, boolean handled) throws virtualAirport.DataFormatException
		{
			handled = true;
			String s = null;	
				s =
				cI.getSpecificItem(lastCode);
			
			return s;
		}
		
		public boolean handlerFormat(String headCode, String tailCode)
		{		
			boolean head = false;
			boolean tail = false;
			head = (headCode.equals(ALL_PREFIX) || 
				    headCode.equals(SPECIFIC_PREFIX));
//			System.out.println(headCode.equals(ALL_PREFIX));
//			System.out.println(headCode.equals(SPECIFIC_PREFIX));
			tail = (tailCode.equals(TICKET_PREFIX) || 
							tailCode.equals(PRODUCT_PREFIX)||
							         tailCode.equals(ITEM_PREFIX));
//			System.out.println(tailCode.equals(TICKET_PREFIX));
//			System.out.println(tailCode.equals(PRODUCT_PREFIX));
//			System.out.println(tailCode.equals(ITEM_PREFIX));
			
//			System.out.println("--------------");
//			System.out.println(headCode.equals(ALL_PREFIX)||headCode.equals(SPECIFIC_PREFIX));
//			System.out.println(tailCode.equals(TICKET_PREFIX)||tailCode.equals(PRODUCT_PREFIX)||tailCode.equals(ITEM_PREFIX));
//			System.out.println(head&&tail);
			return head && tail;
		}
		
		//ʹ�ÿͻ������load��
		public void packageLoader() throws IOException, DataFormatException
		{
		BufferedReader reader = 
				new BufferedReader(new FileReader(this.name + ".txt"));
		String line = reader.readLine();
		//��������
		//System.out.println(line);
		System.out.println("������ʷ��¼Ϊ��");
		while (line != null)
		{
			Product product = null;
			
			System.out.println(line);
			if(line.startsWith(PLANETICKET_PREFIX)) {
				product = readPlaneTicket(line);
			}
			else if(line.startsWith(PRODUCT_PREFIX)) {
				product = readProduct(line);
			}
//			else if(line.startsWith(PRODUCT_PREFIX)) {
//				product = readProduct(line);
//			}
//			else if(line.startsWith(COFFEEBREWER_PREFIX)) {
//				product = readCoffeeBrewer(line);
//			}
			else 
			{	
				System.out.println("����س���");
			}
			
			cI.addItem(product);//��δ���ͬ�ࣿ����
			
			line = reader.readLine();//�ò�ͬ�ļ����뷽ʽ��������
		}
		
		reader.close();
		//����Ϊ���������������
		//System.out.println(clientCatalog.displayClientCatalog());
		}
		
		private PlaneTicket readPlaneTicket(String line)throws DataFormatException
		{	
			StringTokenizer tokenizer = new StringTokenizer(line, DELIM);
			
			if (tokenizer.countTokens() != 7)
			{
				throw new DataFormatException(line);
			}
			else
			{
				try
				{
					tokenizer.nextToken();
					
					return new PlaneTicket(
							tokenizer.nextToken(), 
							tokenizer.nextToken(), 
							Double.parseDouble(tokenizer.nextToken()), 
							tokenizer.nextToken(),
							tokenizer.nextToken(),
							tokenizer.nextToken()
							);
				}
				catch (NumberFormatException nfe){//��double���쳣�������
					throw new DataFormatException(line);
				}
			}
			
		}
		
		private Product readProduct(String line) throws DataFormatException
		{

			StringTokenizer tokenizer = new StringTokenizer(line, DELIM);
			
			if (tokenizer.countTokens() != 4)
			{
				throw new DataFormatException(line);
			}
			else
			{
				try
				{
					tokenizer.nextToken();//prefix--ǰ׺
					
					return new Product(
							tokenizer.nextToken(), 
							tokenizer.nextToken(), 
							Double.parseDouble(tokenizer.nextToken()) 
							);
				}
				catch (NumberFormatException nfe){//��double���쳣�������
					throw new DataFormatException(line);
				}
			}
			
		}
		

}
