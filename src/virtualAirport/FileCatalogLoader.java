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
			
			catalog.addProduct(product);//如何处理不同类？？？
			
			line = reader.readLine();//用不同文件读入方式会怎样？
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
		//测试用例
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
			
			clientCatalog.addProduct(client);//如何处理不同类？？？
			
			line = reader.readLine();//用不同文件读入方式会怎样？
		}
		
		reader.close();
		//以下为测试用例，请忽略
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
			catch (NumberFormatException nfe){//给double的异常处理机制
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
				tokenizer.nextToken();//prefix--前缀
				
				return new Product(
						tokenizer.nextToken(), 
						tokenizer.nextToken(), 
						Double.parseDouble(tokenizer.nextToken()) 
						);
			}
			catch (NumberFormatException nfe){//给double的异常处理机制
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
				tokenizer.nextToken();//prefix--前缀
				
				return new Client(
						tokenizer.nextToken(), 
						tokenizer.nextToken(), 
						Double.parseDouble(tokenizer.nextToken()) 
						);
			}
			catch (NumberFormatException nfe){//给double的异常处理机制
				throw new DataFormatException(line);
			}
		}
		
	}
}
