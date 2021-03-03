package emailvalidate;

public class EmailValidation {
	
	public static void main(String[] args)
	{
		if(args.length < 1)
		{
			System.out.println("Error! Missing input email ID! Email Validation requires user to input email ID.");
			System.exit(0);;
		}
		
		boolean valid = false;
		
		for(int i = 0; i < args.length; i++)
		{
			String email = args[i]; // take the arguments from command line
			
			System.out.println("Entered email: " + email);
			
			// Validate Email
			// Using regex101 Pattern: .+\@.+(\..+)+
			
			EmailValidator emailValidator = new EmailValidator();
			if(emailValidator.validate(email))
			{
				System.out.println(email + " is valid.");
				valid = true;
			}
			else
			{
				System.out.println(email + " is invalid.");
				valid = false;
			}
			
			// Search for Email in the array and print results
			
			if(valid) // if the email was valid, perform the email search
			{
				EmailSearcher emailSearcher = new EmailSearcher();
				
				
				if(emailSearcher.searchEmail(email))
				{
					System.out.println(email + " was found in the array of email IDs.");
				}
				else
				{
					System.out.println(email + " was not found in the array of email IDs.");
				}
			}
		}
		
	}

}
