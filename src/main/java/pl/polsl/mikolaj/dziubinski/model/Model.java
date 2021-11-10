package pl.polsl.mikolaj.dziubinski.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *Model allows to perform all the needed calculations
 * 
 * @author Mikolaj Dziubinski
 * @version 1.2
 */
public class Model {

    /**Final Roman numeral as a result */
    private String finalRoman = "";
    /**Current numeral */
    private int currentNum = 0;
    /**Final Arabic numeral as a result */
    private int finalArabic = 0;
    /**Flag to check valid array input*/
    private Boolean error = false;

    public enum romanNumerals {
        M(1000),
        CM(900),
        D(500),
        CD(400),
        C(100),
        XC(90),
        L(50),
        XL(40),
        X(10),
        IX(9),
        V(5),
        IV(4),
        I(1);

        int arabicValue;

        romanNumerals(int value)
        {
            this.arabicValue = value;
        }
        public int getValue()
        {
            return arabicValue;
        }
    }

    /**
     * Validates the input
     * 
     * @param num passed input argument
     * @return integer which marks if the input is correct and distinguish if it is an Arabic numeral or Roman numeral 
     */
    public int validateInput(String num) 
    {
        int inputVal;
        try 
        {
           inputVal = checkInput(num);
        }
        catch(InputException ex)
        {
           inputVal = 0;
        }
        return inputVal;
    }
    
    /**
     * Verifies if input is valid Arabic number or Roman numeral
     * 
     * @param num input string 
     * @return int flag describing type of input
     * @throws InputException exception is input is not valid
     */
    public int checkInput(String num) throws InputException
    {
        if(num.matches("\\d+") && !num.matches("0"))
        {   
            return 1;
        }
        else if(num.matches("^M{0,6}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$"))
        {
            return 2;
        }
        else
        {
            throw new InputException();
        }
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
        for(romanNumerals romanNumeral: romanNumerals.values())
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
     * Parses the number from string to integer
     * 
     * @param num Arabic number in string
     * @return parsed integer
     */
    public int stringToInt(String num)
    {
        return Integer.parseInt(num);
    }
    
    /**
     * Exception class for objects when passed input is invalid
     */
    public class InputException extends Exception
    {
        /**
         * Non parameter constructor
         */
        public InputException()
        {}
    }
}

