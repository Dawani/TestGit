package virtualAirport;

import java.util.Scanner;

public class GoCommand extends Commands{
	public GoCommand(Airport ap)
	{
		super(ap);
	}
	@Override
	public void doCmd(String word, String room, Scanner in, Catalog catalog) {
		//System.out.println(room);
		if (room.equals("һ¥������")||room.equals("һ¥������̨")||room.equals("��¥����"))
			{
			  ap.function(room, in, catalog);
			}
		ap.goRoom(word);
	}
}
