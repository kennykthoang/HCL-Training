package emailvalidate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
	
	final String regex = ".+\\@.+(\\..+)+";
	
	final Pattern regexPattern = Pattern.compile(regex, Pattern.MULTILINE);
	
	public boolean validate(String stringToValidate)
	{
		Matcher emailMatch = regexPattern.matcher(stringToValidate);
		return emailMatch.find();
	}

}
