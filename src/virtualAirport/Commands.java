package virtualAirport;

import java.util.Scanner;

public class Commands {
	protected Airport ap;
	public Commands(Airport ap)
	{
		this.ap = ap;
	}
	public void doCmd(String word, String room, Scanner in, Catalog catalog) {	}
	public boolean isBye() {return false;}
}
