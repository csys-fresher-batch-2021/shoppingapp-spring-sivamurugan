package in.siva.vegapp.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class UtilValidator {

	/**
	 * This method is used to check the entered product has alphabets characters
	 * only If yes it returns - true, else - False;
	 * 
	 * @param productName
	 * @return
	 */
	public boolean isStringValid(String productName) {
		String regx = "^[a-zA-z]+([\\s][a-zA-Z]+)*$";
		Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(productName);
		return matcher.find();
	}

	/**
	 * This method is used to check whether the entered quantity type is positive or
	 * not If Positive - true, Negative - false
	 * 
	 * @param productQuantity
	 * @return
	 */
	public boolean isNumberValid(int number) {
		boolean valid = true;
		if (number < 0) {
			valid = false;
		}
		return valid;
	}
}
