
public class BinString {

	public static String convert(String s) {
		return decimalToBinary(sum(s));
	}

	//returns the integer sum of the ASCII characters in the string
	public static int sum(String s) {
		if(s == ""){
			return 0;
		}
		if(s.length() == 1){
			return ((int)(s.charAt(0)));
		}

		return ((int)(s.charAt(0))) + sum(s.substring(1));
	}

	//converts the parameter from decimal to ASCII
	public static String decimalToBinary(int x) {
		if(x == 0){
			return "";
		}
		if(x % 2 == 1){
			return "1" + decimalToBinary(x/2);
		}
		return "0" + decimalToBinary(x/2);
	}
	
	// converts the parameter string of 0 or 1 characters that
	// represents a binary number to a decimal integer
	public static int binaryToDecimal(String binaryNum) {
	  int sum = 0;
	  int twoPower = 1;

	  for (int i = binaryNum.length() - 1; i >= 0; i--) {
	 	if (binaryNum.charAt(i) == '1') 
	 		sum = sum + twoPower;
	 	else if (binaryNum.charAt(i) != '0') 
	 		throw new IllegalArgumentException("Invalid binary number.");
	 	twoPower = 2 * twoPower;
	  }
	   return sum;
	}
}