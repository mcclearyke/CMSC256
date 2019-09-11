/********************************
This was LAB 4
- line/block comments -> MY code
- java doc comments -> GIVEN code
********************************/

import bridges.connect.Bridges;
import bridges.base.Array1D;
import bridges.base.Color;
import bridges.base.Element;

public class Setup {
	
	public static void main(String[] args) throws Exception {

	    /* Initialize a Bridges connection with your credentials */
	    /* TODO: plug your own BRIDGES credentials */
	    Bridges bridges = new Bridges(0, "mcclearyke", "67178589394"); //CHANGED

	    /* Set an assignment title */
	    bridges.setTitle("Kendall McCleary"); //CHANGED

	    /* Set up the array dimensions, allocate an Array of Elements */
	    /* TODO: Make an array of size 10 */
	    int arraySize = 10; //CHANGED
	    
	    Array1D<Integer> arr = new Array1D<Integer> (arraySize);
	    
		//for loop initialize each array entry to store a square number
	   	for (int i = 0; i < arraySize; i++) {
	    		//creating a variable to contain the square numbers
	    		int ele = (int) Math.pow(i, 2); //cast Math.pow as an int becuase the method is a double and the elements in the array need to be ints
		
			//Assigning the arrays elements - helpful code is given below w/ java doc comments above them	
			arr.getElement(i).setLabel(String.valueOf(ele)); //CHANGED
			 /***************************************************************************************
			 .getElement(i) - This will fill in the array at i index
			 .setLabel(...) - This will add the number into the index box
			 String.valueOf(ele) - Since setLabel() is a String method we need to make the value 
			    			(given by valueOf(ele)) a String as well because it is now an int
			 ****************************************************************************************/
		}

	   	
 	    /************************************************************
	    Lab Bonus - Change the color of the boxes
	    .getElement(#) -> Access an individual element in the array
	    .setColor(" ") -> To change color
	    Helpful links -> http://bridgesuncc.github.io/doc/java-api/current/html/classbridges_1_1base_1_1_array.html
	    		     http://bridgesuncc.github.io/doc/java-api/current/html/classbridges_1_1base_1_1_element.html
	    ************************************************************/
	    arr.getElement(0).setColor("deeppink");
	    arr.getElement(1).setColor("mediumseagreen");
	    arr.getElement(2).setColor("beige");
	    arr.getElement(3).setColor("orchid");
	    arr.getElement(4).setColor("lightsalmon");
	    arr.getElement(5).setColor("paleturquoise");
	    arr.getElement(6).setColor("violet");
	    arr.getElement(7).setColor("wheat");
	    arr.getElement(8).setColor("lavenderblush");
	       
	    /* Populate the array with integers */
	    /* TODO: Make the array store square numbers*/
	    arr.getElement(0).setValue(0);
	    
	    /* set the value as a Label */
	    arr.getElement(0).setLabel(String.valueOf(0));
	    
	    /* Tell BRIDGES which data structure to visualize */
	    bridges.setDataStructure(arr);

	    /* Visualize the Array */
	    bridges.visualize();
		
	}
}
