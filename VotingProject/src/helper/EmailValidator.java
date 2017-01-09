package helper;
/*
 * Author :Arzum
 * Date : November 27, 2017
 * 
 * email validation is done by GUI!
 */

public final class EmailValidator {
/*
 * This class is for checking e-mail address validity
 */
	 public static boolean isValidEmailAddress(String email) {
   /*
    * Generic e-mail validity.
    * It is taken form web, not my code
    */
		 String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
         java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
         java.util.regex.Matcher m = p.matcher(email);
         return m.matches();
    }
	
	 public static boolean isIyteStudentEmailAddress(String email){
		 /*
		  * This method is responsible for checking validity of IYTE student mail addresses syntactically.
		  * If the returning result is  true : mail address is valid
		  *                             false: invalid mail address
		  */
		 String pattern = "^[a-z]+@std.iyte.edu.tr";
		 java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
         java.util.regex.Matcher m = p.matcher(email);
         return m.matches();
	 }
	 
	 public static boolean isIyteEmailAddress(String email){
		 /*
		  * This method is responsible for checking validity of IYTE student mail addresses syntactically.
		  * If the returning result is  true : mail address is valid
		  *                             false: invalid mail address
		  */
		 String pattern = "^[a-z]+@iyte.edu.tr";
		 java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
         java.util.regex.Matcher m = p.matcher(email);
         return m.matches();
	 }
	 
}
