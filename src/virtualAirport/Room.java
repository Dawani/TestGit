package virtualAirport;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.zip.DataFormatException;


public  class Room {
	
//抽象类中定义实列化的哈希表子类进行的操作会不会改变父类中属性？？
	//实验预定
	private String description;
    private HashMap<String, Room> exits = new HashMap<String, Room>();

    public Room(String description) 
    {
        this.description = description;
    }
    
    public void run(Scanner in, Catalog catalog, Client client)  throws IOException, DataFormatException, virtualAirport.DataFormatException 
    {
    	System.out.println("----当前场景功能未开发----");
    }
    
    public String getDescription()
    {
    	return description;
    }
    public void setExit(String dir, Room room)
    {
    	exits.put(dir, room);
    }
    
    
    @Override
    public String toString()
    {
        return description;
    }
    
    public String getExitDesc()
    {	
    	StringBuffer sb = new StringBuffer();
    	for(String dir : exits.keySet())
    	{
    		sb.append(dir);
    		sb.append(" ");
    	}
    	return sb.toString();
    }
    
    public Room getExit(String direction)
    {
    	return exits.get(direction);
    }
}
