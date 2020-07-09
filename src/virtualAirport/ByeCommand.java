package virtualAirport;


public class ByeCommand extends Commands{
	public ByeCommand(Airport ap)
	{
		super(ap);
	}
	@Override
	public boolean isBye() {
		return true;
	}

}
