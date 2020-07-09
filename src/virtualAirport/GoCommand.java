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
		if (room.equals("一楼卫生间")||room.equals("一楼服务总台")||room.equals("二楼餐厅"))
			{
			  ap.function(room, in, catalog);
			}
		ap.goRoom(word);
	}
}
