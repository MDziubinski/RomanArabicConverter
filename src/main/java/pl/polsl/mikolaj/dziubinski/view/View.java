package pl.polsl.mikolaj.dziubinski.view;

import java.util.*;

/**
 *View represents and manages the user interface
 * 
 * @author Mikolaj Dziubinski
 * @version 1.3
 */
public class View {
    
    /**
     * Takes input from user 
     * 
     * @param wrongInput invalid input 
     * @return new input
     */
    public String getNewInput(String wrongInput)
    {
        System.out.println("Please provide correct input as " + wrongInput + " is neither arabic number or roman numeral.");
        Scanner scanner = new Scanner(System.in);
        String num = scanner.next();
        return num;
    }

    /**
     * Takes new input list from user
     * 
     * @return new input list
     */
    public List<String> getInputList()
    {
        System.out.println("There is no arguments passed, please provide arabic number or valid roman numeral.");
        Scanner scanner = new Scanner (System.in);
        String inputLine = scanner.nextLine();
        List<String> inputList = new ArrayList<>(Arrays.asList(inputLine.split(" ")));

        return inputList;
    }
    /**
     * Prints the result on the screen
     * 
     * @param arabicNum Arabic number as result or input value
     * @param romanNum Roman numeral as result or input value
     * @param romanArabicFlag flags which argument is input and output and vice versa
     */
    public void showResult(String arabicNum, int romanNum, int romanArabicFlag)
    {
        if(romanArabicFlag == 0)
        {
           System.out.println(romanNum + "->" + arabicNum); 
        }
        else
        {
           System.out.println(arabicNum + "->" + romanNum); 
        }
    }
}
