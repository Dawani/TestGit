package virtualAirport;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.zip.DataFormatException;


public  class Room {
	
//�������ж���ʵ�л��Ĺ�ϣ��������еĲ����᲻��ı丸�������ԣ���
	//ʵ��Ԥ��
	private String description;
    private HashMap<String, Room> exits = new HashMap<String, Room>();

    public Room(String description) 
    {
        this.description = description;
    }
    
    public void run(Scanner in, Catalog catalog, Client client)  throws IOException, DataFormatException, virtualAirport.DataFormatException 
    {
    	System.out.println("----��ǰ��������δ����----");
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
