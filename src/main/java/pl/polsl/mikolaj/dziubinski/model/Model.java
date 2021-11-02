package pl.polsl.mikolaj.dziubinski.model;

/**
 *Model allows to perform all of the needed calculations 
 * 
 * @author mikol
 * @version 1.2
 */
public class Model {
    /**All specific roman signs */
    private final String[] romanNums = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV","I"};
    /**Arabic representation of Roman numeral */
    private final int[] arabicNums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1}; 
    /**Final Roman numeral as a result */
    private String finalRoman = "";
    /**Current numeral */
    private int currentNum = 0;
    /**Final Arabic numeral as a result */
    private int finalArabic = 0;
    /**Flag to check valid array input*/
    private Boolean error = false;
    
    /**
     * Verifies the correctness of the input
     * 
     * @param num passed input argument
     * @return integer which marks if the input is correct and distinguish if it is an Arabic numeral or Roman numeral 
     */
    public int validateInput(String num) 
    {
        int inputVal = 0;
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
     * Verifies if passed input array is valid 
     * 
     * @param inputArray array passed by main function arguments or user input
     * @return Boolean value marks if array is empty or not
     */
    public Boolean validateInputArray(String[] inputArray)
    {
        return error = inputArray.length == 0;
    }
    
    /**
     * Converts Arabic numeral into roman numeral
     * 
     * @param num Arabic number
     */
    public void getRomanNumeral(int num)
    {
        for (int i = 0; i < arabicNums.length; i++)
        {
            currentNum = num / arabicNums[i];
            if (currentNum == 0)
            {
                continue; //skips current iteration of the loop
            }
            for(int j = 0; j <currentNum; j++)
            {
                finalRoman += romanNums[i];
            }
            num = num % arabicNums[i];
        }
    }
    
    /**
     * Converts roman numeral into Arabic numeral
     * 
     * @param romanNum roman numeral 
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
