package cmsc256;

/**
 * Name: Kendall McCleary
 * Class: CMSC 256 - 001
 * Project: Project 2 -> With this project we are implementing an interface and manipulating String and chars. 
 * It will take a string of numbers or characters and use mutator methods to create that methods
 * particular return value
 * Date: 9/11/2019
 */

//Java class RamString that implements WackyStringInterface
public class RamString implements WackyStringInterface {
	
	//Instance Variables
	private String string = " ";
	
	//Default Constructor
	public RamString() { //no arguments
		
		try {
		
			//set the instance variable to given String
			string = "Rodney the Ram";
		}
	
		//Throwing an exception if the string is null
		catch (NullPointerException ex) {
	 
			System.out.print("The string is null!");
		}
	}
	
	//Parameterized Constructor
	public RamString(String string) { //single string arguments
	
		try {
		
			setWackyString(string); //passing the string to setWackyString() method
		}
	
		//Throwing an exception if the string is null
		catch (NullPointerException ex) {
			 
			System.out.print("The string is null!");
		}
	}
	
	/**
	* Sets the value of the current string.
	* @param string	The value to be set
	*/
	public void setWackyString(String string) {
		
		//Exception throwing if setWackyString is passed a null parameter
		if (string == null) throw new IllegalArgumentException("The string is null!"); { 
			
			this.string = string; //Setting the value of the string that was passed in
		}
	}

	/**
	* Returns the current string
	* @return Current string
	*/
	public String getWackyString()  {
		
		//if the String is null
		if (string == null) throw new IllegalArgumentException("The string is null!"); {
		
			return string; //return current String
		}
	}

	/**
	* Returns a string that consists of only the characters
	* in the first, middle and last positions in
	* the current string, in the same order and with the same case as
	* in the current string. The first character in the string is
	* considered to be in Position 1.
	*
	* @return String 	made of characters in first, middle and last 
	* 					positions in the current string
	*/
	public String getFirstMiddleLast() {
		
		//Creating the variables that will be needed 
		int startIndex = 0; //First index for finding MIDDLE
		int firstIndex= 0; //First index for finding LAST
		int endIndex = 0; //Last index for finding MIDDLE
		int lastIndex = 0; //Last index for finding LAST
		String firstChar = ""; //String for the first char
		String middleChar = ""; //String for the middle char
		String lastChar = ""; //String for the last char
		String strChar = ""; //Variable to contain the chars
		    
		    //If the string IS empty
		    if (string.length() == 0) {
		      
		      strChar = string; //return the string
		    }
		    
		    //If the string is NOT empty
		    if (string.length() > 0) {
		    	
		    	/************
				finding FIRST
				*************/
					
		    	firstChar = string.substring(0, 1);
				
				/**************
				finding MIDDLE
				***************/
				
					//if the String length is 2
					if (string.length() == 2) {
					
						middleChar = "";
					}
				
					//EVEN string length
					else if (string.length() % 2 == 0) {
					
						startIndex = (string.length() / 2) - 1;//will find the position/number of the middle value
						endIndex = (string.length() / 2); //(endIndex - 1) the position/number of the last read element in the String
					}
				
					//ODD string length
					else {
					
						startIndex = string.length() / 2;     //will find the position/number of the middle value
						endIndex = (string.length() / 2) + 1; //(endIndex - 1) the position/number of the last read element in the String
					}
				
				//need to put an end index so that it doesn't return the full String staring from the startIndex
				middleChar = string.substring(startIndex, endIndex);
				
				/***********
				finding LAST
				************/
				
					firstIndex = string.length() - 1;
					lastIndex = string.length();
				
				lastChar = string.substring(firstIndex, lastIndex);
				 
				/********************
				Putting them together
				*********************/
				
				strChar = firstChar + middleChar + lastChar; //assign all the characters to strChar variable
		    }
		    
				return strChar; //Return a String made of characters in first, middle and last positions in the current string
	}
	
	/**
	* Returns a string that consists of all and only the characters
	* in the "every third" positions (i.e., third, sixth, ninth and so on)
	* current string, in the same order and with the same case as in
	*  in the current string. The first character in the string is
	* considered to be in Position 1.
	*
	* @return String 	made of characters in every third position 
	* 					in the current string
	*/
	public String getEveryThirdCharacter() {
		
		//Creating a variable to add every third char to
		String everyThirdChar = "";
		
		//For loop to go through the String starting at index 2 (0,1,2 -> third char)
		for (int i = 2; i < string.length(); i += 3) { //increment i by 3 
			
			everyThirdChar = everyThirdChar + string.charAt(i); //add the char to the String	
		}
		
		return everyThirdChar;
	}
	
	/**
	* Returns the number of characters that are even digits
	*  in the current string
	* @return Number of even digits in the current string
	*/
	public int countEvenDigits() {
		
		//Creating a variable to count the # of chars that are even
		int countEven = 0;
		
		//Creating a array of characters from the String so that I can used the Character.isDigit() method
		char[] digitStringArray = string.toCharArray();
	
		//for loop to go through the new array
		for (int i = 0; i < digitStringArray.length; i++) {
			
			//If the char at index i is a number 
			if (Character.isDigit(digitStringArray[i])) { //This is needed because if the String contains letters, this part will exclude them so they're not counted
				
				//See if the number is even
				if (digitStringArray[i] % 2 == 0) { 
				
					countEven++; //increment countEven
				}
			}
		}
		
		return countEven;
	}
	
	/**
	* Returns true if the current string has a capital V as it's first 
	* character, followed by two zero characters (00), and then 
    * 6 additional digits. 
	*	For example, V00123456 a valid VCU eID.
	*
	* @return true 	if current string is formated as valid VCU eID,
	* 					false otherwise.
	*/
	public boolean isValidEID() {
		
		
		if (string.charAt(0) == 'V' && string.charAt(1) == '0' && string.charAt(2) == '0' && string.length() == 9) {
				
			//Creating a array of characters from the String so that I can used the Character.isDigit() method to check what the elements after V00 
			char[] strArray = string.toCharArray();
				
			//boolean variable to return true if it is a number after V00
			boolean isNum = false;
			
				//for loop to go through the new array
				for (int i = 3; i < strArray.length; i++) {
					
					//If the char at index i is a number 
					if (Character.isDigit(strArray[i])) { //If it's a digit
				
						isNum = true; //return true
					}
					
					else if (Character.isAlphabetic(strArray[i])) { //If it's a letter
						
						return false; //return false
					}
				}
				
				return isNum;
			}
		
			//If the string doesn't meet the criteria of the if statement above, return false
			return false;
		}
	
	/**
	* Replace the _individual_ digits in the current string, between
	* startPosition and endPosition (included), with the corresponding
	* word. The first character in the string is
	* considered to be in Position 1. Digits are converted individually,
	* even if contiguous, and digit "0" is not converted (e.g., 460 is
	* converted to foursix0). This method changes the instance variable.
	*
	* @param startPosition		Position of the first character to consider
	* @param endPosition		Position of the last character to consider
	* @throws MyIndexOutOfBoundsException
	*            	If either "startPosition" or "endPosition" are out of bounds
	*             	(i.e., either less than 1 or greater than the length of the string)
	* @throws IllegalArgumentException
	*            If "startPosition" > "endPosition" (but both are within bounds)
	*/
	public void convertDigitsToWordsInSubstring(int startPosition, int endPosition)  { //subtract 1 from each 1
	
		/*********	
		Exceptions
		*********/	
		
		//Throwing MyIndexOutOfBoundsException
		if (startPosition < 1 || startPosition > string.length())  { //If startingPosition is out of bounds
						
			throw new MyIndexOutOfBoundsException("Index is out of bounds!");
		}
				
		if (endPosition < 1 || endPosition > string.length())  { //If endingPosition is out of bounds
					
			throw new MyIndexOutOfBoundsException("Index is out of bounds!");	
		}
				
		//Throwing IllegalArgumentException
		if (startPosition > endPosition)  { //If "startPosition" > "endPosition" (but both are within bounds)
					
			throw new IllegalArgumentException("Starting position exceeds the ending position!");
		}
		
		
		/**************************	
		Creating a Temporary String
		***************************/	
		
		//Temporary String value -> have the new Strings in all caps for Gradescope
		String tempStr = ""; 
				
				//for loop to go through the array
				for (int i = (startPosition - 1); i < endPosition; i++) { //startPosition needs -1 to get the actual index in the string for when the iterations start 
					
					//Keeping 0 as 0
					if (string.charAt(i) == '0') { 
					 
						tempStr = tempStr + 0;
					}
					  
					//Making 1 to one
					else if (string.charAt(i) == '1') {
					    
						tempStr = tempStr + "ONE"; //adding the spelled number to the temporary String
					}
					  
					//Making 2 to two
					else if (string.charAt(i) == '2') {
					    
						tempStr = tempStr + "TWO"; //adding the spelled number to the temporary String
					}
					  
					//Making 3 to three
					else if (string.charAt(i) == '3') {
					    
						tempStr = tempStr + "THREE"; //adding the spelled number to the temporary String
					}
					  
					//Making 4 to four
					else if (string.charAt(i) == '4') {
					    
						tempStr = tempStr + "FOUR"; //adding the spelled number to the temporary String
					}
					  
					//Making 5 to five
					else if (string.charAt(i) == '5') {
					    
						tempStr = tempStr + "FIVE"; //adding the spelled number to the temporary String
					}
					  
					//Making 6 to six
					else if (string.charAt(i) == '6') {
					    
						tempStr = tempStr + "SIX"; //adding the spelled number to the temporary String
					}
					  
					//Making 7 to seven
					else if (string.charAt(i) == '7') {
					    
						tempStr = tempStr + "SEVEN"; //adding the spelled number to the temporary String
					}
					  
					//Making 8 to eight
					else if (string.charAt(i) == '8') {
					    
						tempStr = tempStr + "EIGHT"; //adding the spelled number to the temporary String
					}
					  
					//Making 9 to nine
					else if (string.charAt(i) == '9') {
					    
						tempStr = tempStr + "NINE"; //adding the spelled number to the temporary String
					}
							
					//If the element in the String is a LETTER
					else {
								
						tempStr = tempStr + string.charAt(i); //adding the char at index i to the temporary String
					}
				}
					
			/****************************	
			Changing the instance variable
			*****************************/	
			
			//Getting the elements in the string before the temporary string	
			String toSPostition = string.substring(0, startPosition - 1);
			
			//Getting the elements in the string after the temporary string to the end of the string	
			String toEPostition = string.substring(endPosition, string.length());
			
			//Piecing the String back together
			String newStr = toSPostition + tempStr + toEPostition;
			
			//Assign the new string to the instance variable 
			string = newStr;
	}	
}