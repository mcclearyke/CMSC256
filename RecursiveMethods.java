package cmsc256;
//LAB 3
/*
*	Kendall McCleary:
*	Rudy Barua:
*/

public class RecursiveMethods {
	
	/* 
	 * @returns a String of character, ch. The length is determined
	 * 			by the second parameter, length.
	 */
	public static String buildStringOfCharacters(char ch, int length) {
		
		if (length == 0) {
			
			return "";	
		}
		
		return ch + buildStringOfCharacters(ch, length - 1);
	}
	
	/*
	 * returns an int array that has the elements in reverse order
	 * 			of the parameter array, nums. 
	 * Process this recursively beginning with the last element.
	 */
	public static int[] reverseNumArray(int[] nums, int backIndex) {
		
		int frontIndex = nums.length - (backIndex + 1);

		int mid = nums.length / 2;
		
		if (backIndex > mid) {
			
			int temp = nums[backIndex];
			nums[backIndex] = nums[frontIndex];
			nums[frontIndex] = temp;
			
			return reverseNumArray(nums, backIndex - 1); 		
		
		}
			
		return nums;		
	}
	
	/*
	 * returns true if the int array parameter is sorted from smallest
	 * 			to largest, false otherwise.
	 * Process this recursively beginning with the first element.
	 */
	public static boolean isSmallestToLargest(int[] values, int firstIndex) {
		
		int secondIndex = firstIndex + 1;
		
		
		if (values[firstIndex] > values[secondIndex]) {
			
			return false;
			
		}
		
		if (firstIndex < secondIndex) {
			
			return true; 
		
		}
		
			if (values[firstIndex] < values[secondIndex]) {
			
				return isSmallestToLargest(values, firstIndex + 1);
				
			}
			
		
		return false;
	}
	
	/* 
	 * @returns true if the parameter String, str is a palindrome
	 * 			false otherwise
	 */
	public static boolean isPalindrome(String str, int begin, int end) {
		
		if (begin >= end) {
			
			return true;
		}
		
			
		if (str.charAt(begin) == str.charAt(end)) {
			
			return isPalindrome(str, begin + 1, end - 1);
		
		}
			return false;
	}
}
