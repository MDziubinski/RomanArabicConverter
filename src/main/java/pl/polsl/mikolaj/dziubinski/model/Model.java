package pl.polsl.mikolaj.dziubinski.model;


import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Model allows to perform all the needed calculations
 * 
 * @author Mikolaj Dziubinski
 * @version 1.4
 */
public class Model {

    /**Final Roman numeral as a result */
    private String finalRoman = "";
    /**Current numeral */
    private int currentNum = 0;
    /**Final Arabic numeral as a result */
    private int finalArabic = 0;
    /**Input array of strings with correct part of input*/
    private List<String> inputListCorrect = new ArrayList<String>();
    /**Input array of strings with incorrect part of input*/
    private List<String> inputListInCorrect = new ArrayList<String>();
    
    /**Enum for roman numerals*/
    public enum RomanNumerals {
        /**Roman representation of Arabic 1000 */
        M(1000),
        /**Roman representation of Arabic 900 */
        CM(900),
        /**Roman representation of Arabic 500 */
        D(500),
        /**Roman representation of Arabic 400 */
        CD(400),
        /**Roman representation of Arabic 100 */
        C(100),
        /**Roman representation of Arabic 90 */
        XC(90),
        /**Roman representation of Arabic 50 */
        L(50),
        /**Roman representation of Arabic 40 */
        XL(40),
        /**Roman representation of Arabic 10 */
        X(10),
        /**Roman representation of Arabic 9 */
        IX(9),
        /**Roman representation of Arabic 5 */
        V(5),
        /**Roman representation of Arabic 4 */
        IV(4),
        /**Roman representation of Arabic 1 */
        I(1);
        
        /**Integer value of enum element */
        int arabicValue;
        
        /**
         *enum constructor 
         *
         *@param value element value
         */
        RomanNumerals(int value)
        {
            this.arabicValue = value;
            
        }
        
        /**
         *enum getter to pass values 
         *
         * @return passed value pointed by the iterator
         */
        public int getValue()
        {
            return arabicValue;
        }
    }

    /**
     * Validates if passed input is valid roman or Arabic numeral taking mode of conversion into consideration
     * @param num input string
     * @param mode mode of conversion
     * @return flag that determines if output is correct or incorrect
     */
    public int validateInputGUI(String num, int mode) 
    {
        int inputVal;
        try 
        {
           inputVal = checkInputGUI(num, mode);
        }
        catch(InputException ex)
        {
           inputVal = 0;
        }
        return inputVal;
    }
    

    /**
     * Checks if passed input is valid roman or Arabic numeral taking mode of conversion into consideration
     * @param num input value
     * @param mode conversion mode flag
     * @return int flag describing what type of input was passed
     * @throws InputException for incorrect input, meaning that passed input is not valid Arabic number nor roman numeral
     */
    public int checkInputGUI(String num, int mode) throws InputException
    {
        if(num.matches("\\d+") && !num.matches("0") && (mode == 0 || mode == 2))
        {   
            inputListCorrect.add(num);
            return 1;
        }
        else if(num.matches("^M{0,9}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$")&& (mode == 1 || mode == 2))
        {
            inputListCorrect.add(num);
            return 2;
        }
        else
        {
            inputListInCorrect.add(num);
            throw new InputException("Input value is neither a valid Roman numeral or Arabic number");
        }
    }
    
    /**
     * Getter for correct part of input
     * @return list of correct input values
     */
    public List<String> getInputListCorrect()
    {
        return inputListCorrect;
    }
    
    /**
     * Getter for incorrect part of input
     * @return list of incorrect input values
     */
    public List<String> getInputListInCorrect()
    {
        return inputListInCorrect;
    }
           
    /**
     * Checks if input list is empty
     * 
     * @param inputList input list of strings
     * @return Boolean accordingly if empty or not
     */
    public Boolean validateInputList(List<String> inputList)
    {
        return inputList.isEmpty();
    }

    /**
     * Converts Arabic numeral into roman numeral
     * 
     * @param num Arabic numeral
     */
    public void getRomanNumeral(int num)
    {
        for(RomanNumerals romanNumeral: RomanNumerals.values())
        {
            currentNum = num/romanNumeral.getValue();
            if (currentNum == 0)
            {
                continue; //skips current iteration of the loop
            }
            for(int j = 0; j <currentNum; j++)
            {
                finalRoman = finalRoman + romanNumeral;
            }
            num = num % romanNumeral.getValue();
        }
    }

    /**
     * Converts roman numeral into Arabic numeral
     * 
     * @param romanNum Roman numeral 
     */
    public void getArabicNumeral (String romanNum)
    {
        int currentNumber = 0;
        int previousNumber = 0;
        int arabicNumber = 0;
        for (int i = 0; i < romanNum.length(); i ++)
        {
            char romanSymbol = romanNum.charAt(i);
            
            switch(romanSymbol)
            {
            case ('I'):
                currentNumber = 1;
                break;
            case ('V'):
                currentNumber = 5;
                break;
            case ('X'):
                currentNumber = 10;
                break;
            case ('L'):
                currentNumber = 50;
                break;
            case ('C'):
                currentNumber = 100;
                break;
            case ('D'):
                currentNumber = 500;
                break;
            case ('M'):
                currentNumber = 1000;
                break;
            }
            
            if (currentNumber > previousNumber) {

                arabicNumber += currentNumber - (previousNumber * 2);
            } 
            else {
                arabicNumber += currentNumber;
            }
            previousNumber = currentNumber;
        }
        finalArabic = arabicNumber;
    }
    
    /**
     * Passes saved result from previous calculations of Arabic to Roman conversion
     * 
     * @return String calculated Roman numeral
     */
    public String passResultArabic()
    {
        String temp = finalRoman;
        finalRoman = "";
        return temp;
    }
    
    /**
     * Passes saved result from previous calculations of Roman to Arabic conversion
     * 
     * @return integer calculated Arabic numeral
     */
    public int passResultRoman()
    {
        return finalArabic;
    }
    
    /**
     * Clears table so they can be used again 
     */
    public void cleanOut()
    {
        inputListCorrect.clear();
        inputListInCorrect.clear();
    }
}

