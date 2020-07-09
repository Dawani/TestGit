package virtualAirport;

import java.util.Scanner;

public class HelpCommand extends Commands{
	public HelpCommand(Airport ap)
	{
		super(ap);
	}
	@Override
	public void doCmd(String word, String room, Scanner in, Catalog catalog)  {
		
	        System.out.println("不知道如何操作？你可以做的命令有：go bye help");
	        System.out.println("如：go east");
	    
	}

}
