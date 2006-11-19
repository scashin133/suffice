package controller;

public class SheetBuilder extends Builder {
	private Sheet s;
	
	public SheetBuilder()
	{
	}

	public void buildPart()
	{
		s = new Sheet ("Untitled");
		
		// I thought about implementing this instead:
		//   s = new Sheet ("Untitled" + currentVersion++);
		// but can't work under this scope (builder pattern)
	}
	public Sheet getResult()
	{
		return s;
	}

}
