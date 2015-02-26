package testCases;

public class MVPD 
{
	private Accounts accounts = new Accounts();
	private String name;
	
	public MVPD()
	{
		this.name = "";
	}
	public MVPD(String name, String value)
	{
		this.name = name;
		initializeAccounts(value);
	}
	
	//how does it know which accounts .property beleongs to which provider we would have to have a different accounts property 
	//for every mvpd so key could be mvpd and the value could be the title of the .property file that has the list of username and 
	//passwords or we can have one config property and break it up with commas and white spaces 
	
	private void initializeAccounts(String value) 
	{
			//parts below will be in this class 
			String usrPass[] = value.split(",");
			accounts = new Accounts(usrPass[0], usrPass[1]);
	}
	
	public Accounts getAccount()
	{
		return accounts;
	}
	public String getName()
	{
		return name;
	}
}
