package virtualAirport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class FileCatalogLoader implements CatalogLoader{

	private static final String PLANETICKET_PREFIX = "Ticket";
	private static final String PRODUCT_PREFIX = "Product";
	private static final String CLIENT_PREFIX = "Client";

	private static final String DELIM = "_";
	
	@Override
	public Catalog loadCatalog(String fileName) throws FileNotFoundException, 
						IOException, DataFormatException {
		Catalog catalog = new Catalog();
		
		BufferedReader reader = 
				new BufferedReader(new FileReader(fileName));
		String line = reader.readLine();
		
		while (line != null)
		{
			Product product = null;
			
			if(line.startsWith(PLANETICKET_PREFIX)) {
				product = readPlaneTicket(line);
			}
			else if(line.startsWith(PRODUCT_PREFIX)) {
				product = readProduct(line);
			}
//			else if(line.startsWith(COFFEEBREWER_PREFIX)) {
//				product = readCoffeeBrewer(line);
//			}
			else 
			{	
				reader.close();
				throw new DataFormatException(line);
			}
			
			catalog.addProduct(product);//��δ���ͬ�ࣿ����
			
			line = reader.readLine();//�ò�ͬ�ļ����뷽ʽ��������
		}
		System.out.println(catalog.displayCatalog());
		reader.close();
		return catalog;
	}
	
	public Catalog loadClients(String fileName) throws FileNotFoundException, 
	IOException, DataFormatException {
		Catalog clientCatalog = new Catalog();
		
		BufferedReader reader = 
				new BufferedReader(new FileReader(fileName));
		String line = reader.readLine();
		//��������
		//System.out.println(line);
		
		while (line != null)
		{
			Product client = null;
			System.out.println(line);
			if(line.startsWith(CLIENT_PREFIX)) {
				client = readClient(line);
			
			}
//			else if(line.startsWith(PRODUCT_PREFIX)) {
//				product = readProduct(line);
//			}
//			else if(line.startsWith(COFFEEBREWER_PREFIX)) {
//				product = readCoffeeBrewer(line);
//			}
			else 
			{	
				reader.close();
				throw new DataFormatException(line);
			}
			
			clientCatalog.addProduct(client);//��δ���ͬ�ࣿ����
			
			line = reader.readLine();//�ò�ͬ�ļ����뷽ʽ��������
		}
		
		reader.close();
		//����Ϊ���������������
		//System.out.println(clientCatalog.displayClientCatalog());
		return clientCatalog;
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
	
	private Product readClient(String line) throws DataFormatException
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
				
				return new Client(
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
