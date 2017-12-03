package game;

public class InvalidEntryException extends RuntimeException{

	public InvalidEntryException()
	{
		super("Invalid entry");
	}

	public InvalidEntryException(String msg)
	{
		super(msg);
	}
}
