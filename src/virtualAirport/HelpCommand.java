package virtualAirport;

import java.util.Scanner;

public class HelpCommand extends Commands{
	public HelpCommand(Airport ap)
	{
		super(ap);
	}
	@Override
	public void doCmd(String word, String room, Scanner in, Catalog catalog)  {
		
	        System.out.println("��֪����β�������������������У�go bye help");
	        System.out.println("�磺go east");
	    
	}

}
