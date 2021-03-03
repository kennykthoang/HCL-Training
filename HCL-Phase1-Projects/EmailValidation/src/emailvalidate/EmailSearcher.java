package emailvalidate;

public class EmailSearcher {
	
	String[] emails = {"ken@yahoo.com", "ken@google.com", "ken@microsoft.com", "ken@school.edu", "ken@abc.org"};
	
	public boolean searchEmail(String emailToSearch)
	{
		for(String email : emails) // for each email in array of Strings emails
		{
			if(email.equals(emailToSearch))
				return true;
		}
		return false;
	}

}
