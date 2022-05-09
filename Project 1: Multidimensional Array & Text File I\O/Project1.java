package cmsc256;  // do not remove or comment out this statement

/**
 *  CMSC 256 Fall 2019
 *  Project 1
 *  McCleary, Kendall
 */

// place any import statements here
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Project1 {

    public static void main(String[] args) {

       Project1 obj = new Project1(); //Use this to call the methods
        
        //Calling the checkArgs method to get the command line argument
        String fileName = obj.checkArgs(args);
        
        //Creating the array that will be passed into the methods
        String[][] matrix = null;
        
        //Variable to get the rows
        int numRecords = 0;
        
        //Getting the upper and lower bound from the user for the findAvgHeightByAgeRange method
        Scanner in = new Scanner(System.in);
       
            int lowerBound = 0;
            int upperBound = 0;
        
         /*******************
         * Exception Handling
         *******************/
        try {
            
            //Calling the getFile method
            File input = obj.getFile(fileName);

            //Getting the numRecords
            System.out.print("Please enter the length of the file: ");
            numRecords = in.nextInt();

            //calling readFile to get the array to be used
            matrix = obj.readFile(input, numRecords);
            
            // Display appropriately labeled information for the following:
            System.out.print("Please enter the lower bound in your age range: ");
            lowerBound = in.nextInt();
             
            System.out.print("Please enter the upper bound in your age range: ");
            upperBound = in.nextInt();
            System.out.println();

            // What is tallest height?
            int tallestHeight = obj.findTallest(matrix); //Calling findTallest to get tallestHeight
            System.out.println("The tallest height is: " + tallestHeight);
            System.out.println();
            
            // Which row has the lowest weight?
            String[] lowestWeight = obj.findLightestRecord(matrix); //Calling findLightestRecord to get lowestWeight
            System.out.print("The row with the lowest weight is: ");
            System.out.println(Arrays.deepToString(lowestWeight));
            System.out.println();

            // Calculate average height of 20-30 year age range in the data.
            double averageHeight = obj.findAvgHeightByAgeRange(matrix, lowerBound, upperBound); //Calling findAvgHeightByAgeRangeto get averageHeight
            System.out.println("The average height beween the ages " + lowerBound + "-" + upperBound + " is: " + averageHeight);
            System.out.println();
        }
        
        catch(FileNotFoundException ex) {
            
            System.out.println("Error. File not found!");
        }
        
        catch(IOException exc) {
            
            System.out.println("Error reading the file.");
        }

    }

    /**
    *   Gets the file name from command line argument;
    *   If parameter is empty, call promptForFileName() method
    * @param argv  String array from command line argument
    * @return      the name of the data file
    */
    public String checkArgs(String[] argv) {
        //Return value
        String fileName;
                
        //If there is command line arguments
        if (argv.length > 0) {
               
            //Input name
            fileName = argv[0];
        }
        
        //If there are no command line arguments
        else {
                   
            fileName = promptForFileName(); //Call promptForFileName method
        }
          
        return fileName;
    }

    /**
    * Prompt user to enter a file name
    * @return user entered file name
    */
    public String promptForFileName() {
        //Creating a Scanner
        Scanner console = new Scanner(System.in);
        
        //Have the console ask the user to enter a file name
        System.out.println("Please enter file name: ");
            
            String inputFileName = console.next(); //Getting the name

        //Returning whatever was entered
        return inputFileName;
    }

    /**
    * Retrieve file with the given file name.
    * Prompts user if file cannot be opened or does not exist.
    * @param fileName  The name of the data file
    * @return          File object
    * @throws java.io.FileNotFoundException
    */
    public File getFile(String fileName) throws FileNotFoundException {
        //Return value
        File file; 

        //making the file name into a file 
        file = new File(fileName); 
        
        //prompting the user if the file name can't be found / is empty
        if (fileName.equals(null)) {
            
            String noFileName = promptForFileName();
            
            file = new File(noFileName); //making name they entered a file
        }
       
        return file;  
    }

    /**
    * Reads the comma delimited file to extract the number data elements
    * provided in the second argument.
    * @param file          The File object
    * @param numRecords    The number of values to read from the input file
    * @return              2D array of data from the File
    * @throws IOException if any lines are missing data
    */
    public String[][] readFile(File file, int numRecords) throws IOException {
        //Return value
        String[][] matrix = new String[numRecords][3]; //Shapes the array
        
        Scanner in = new Scanner(file); //Scans the file

        String header = in.nextLine(); //Line will be the first line
    
        int numRecCount = 0; //Making sure the while loop doesn't go past numRecords

        //While loop that goes through the file as long as it has another line and the number of rows is less than the numRecords
        while (in.hasNext() && numRecCount < numRecords) {
            
            for (int row = 0; row < matrix.length; row++) {
            
                header = in.nextLine(); //Skips over the first line
            
                    Scanner console = new Scanner(header); //Creates a scanner class with header variable
                    console.useDelimiter(",");// Tells the code to ignore the commas
                
                    //For loop that goes through the columns
                    for (int col = 0; col < matrix[row].length; col++) {
                
                        /*******************
                        * Exception Handling
                        *******************/
                        if (!console.hasNext()) {

                            console.close(); //Close scanner
                    
                            throw new IOException("Error reading data file."); //Throw the exception
                        }
                        
                        //If there IS a next line
                        else {
                   
                            matrix[row][col] = console.next().trim(); //set what's in the file equal to their spots in the array
                        }
                    }

                numRecCount++;
            
                console.close(); //Close scanner 
            }
        }
        
        in.close(); //Close file
        
        return matrix; //return the array matrix 
    }

    /**
    * Determines the tallest height in the data set
    * Height is the second field in each row
    * @param db        2D array of data containing [age] [height] [weight]
    * @return          Maximum height value
    */
    public int findTallest(String[][] db) {
        //Initializing the return value before looping
        int tallestHeight = Integer.parseInt(db[0][1]);  

            //Loop through each row
            for ( int row = 0; row < db.length; row++) {

                for ( int col = 0; col < db[row].length; col++) {

                    //If statement for the comparison if the value at db[row][1] is greater than the tallest height value
                    if (Integer.parseInt(db[row][1]) > tallestHeight) {
             
                      tallestHeight = Integer.parseInt(db[row][1]); //Set that value equal to the tallestHeight variable
                    
                    }
                }
                
                //If the values match each other then return that value
                if (Integer.parseInt(db[0][1]) == tallestHeight) {
                        
                    tallestHeight = Integer.parseInt(db[row][1]);  
                }  
            }
                  
        return tallestHeight;
    }

    /**
    * Returns the values in the record that have the lowest weight
    * @param db        2D array of data containing [age] [height] [weight]
    * @return          Smallest weight value
    */
    public String[] findLightestRecord(String[][] db) {
        //Variable to find the smallest weight
        int lightest = Integer.parseInt(db[0][2]);
        
        //Variable for getting the row with the smallest weight   
        int rowMin = 0;

            //finding the lightest weight and the row that that weight belonged to
            for (int i = 0; i < db.length; i++) {
                    
                for(int j = 0; j < db[i].length; j++) {
                       
                    if (lightest > Integer.parseInt(db[i][2])) { //[2] because thats the column that has WEIGHT
                       
                        lightest = Integer.parseInt(db[i][2]);

                            if (Integer.parseInt(db[i][2]) == lightest) {
                               
                                rowMin = i;  
                            }   
                    }   
                }       
            }

        //Array to change db to an int array
        int[] lowestInt = new int[db[0].length];
        
        //Return array value
        String[] lowestWeight = new String[db[0].length];
                
            //Filling the arrays to get the row with the lowest weight
            for (int row = 0; row < db[0].length; row++) {

                lowestInt[row] += Integer.parseInt(db[rowMin][row]);
                
                lowestWeight[row] = String.valueOf(lowestInt[row]);
            }
                   
        return lowestWeight;  
    }

    /**
    * Calculates the average height for all records with the given age range.
    * @param db            2D array of data containing [age] [height] [weight]
    * @param lowerBound    youngest age to include in the average
    * @param upperBound    oldest age to include in the average
    * @return              The average height for the given range or 0 if no
    *                      records match the filter criteria
    */
    public double findAvgHeightByAgeRange(String[][] db, int lowerBound, int upperBound) {
        //return value
        double averageHeight = 0.0; 
        
        //count the loops for divison in the end
        int loops = 0;
            
            //Loop to go through the array
            for (int i = 0; i < db.length; i ++) {
                   
                // if  20 is <= or equal to value in the AGE column ([0]) and 30 is >= or equal to a value in the AGE column
                if (lowerBound <= Integer.parseInt(db[i][0])  && upperBound >= Integer.parseInt(db[i][0])) {
                     
                    //add the value of the height to averageHeight
                    averageHeight += Integer.parseInt(db[i][1]); //[1] becuase thats the column with the height
                        
                    loops++;
                }     
            }

            //Return 0 when no records match the filter criteria
            if (loops == 0) {
 
                averageHeight = 0.0; //return zero
            }

            else {
    
                averageHeight = averageHeight / loops; //return avg
            }
        
        return averageHeight; 
    }          
}