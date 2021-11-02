package pl.polsl.mikolaj.dziubinski.controller;

import pl.polsl.mikolaj.dziubinski.model.Model;
import pl.polsl.mikolaj.dziubinski.view.View;

/**
 * Controller class allows to separate model and view classes
 *
 * @author Mikolaj Dziubinski
 * @version 1.1
 */
public class Controller {
    /**View object */
    private final View controllerView;
    /**Model object */
    private final Model controllerModel; 
    /**Roman numeral input */
    private String romanInput = "";
    /**Input array of strings */
    private String[] inputArray = {};
    /**Arabic numeral input */
    private int arabicInput = 0;
    /**Flag for input verifiaction */
    private int inputVerif = 0;
    
    public Controller(View view, Model model)
    {
        this.controllerView = view;
        this.controllerModel = model;
    }
    
    /**
     * Processes the input passed by main function arguments or from view console if the one provided by in arguments was invalid
     * 
     * @param num argument array passed to main 
     */
    public void processInput(String[] num)
    {   
        inputArray = num;
        //Verifying if the passed array is empty or not
        while(controllerModel.validateInputArray(inputArray) == true)
        {
                inputArray = controllerView.getInputArray();
        }
        //Verifying if array elements are valid 
        for (String temp : inputArray) {
            do
            {
                temp = temp.toUpperCase();
                try
                {
                    inputVerif = controllerModel.validateInput(temp);
                }
                catch(Exception ex)
                {
                    inputVerif = 0;
                    System.out.println(ex.toString());
                }

                if (inputVerif == 0)
                {
                    temp = temp.replaceAll(temp, controllerView.getNewInput(temp));
                }
            }
            while(inputVerif == 0);
            
            //Counting validated input
            switch(inputVerif)
            {
                case 1:
                    arabicInput = controllerModel.stringToInt(temp);
                    countArabicToRomanNum(arabicInput);
                    break;
                case 2:
                    romanInput = romanInput.replaceAll(romanInput, temp);
                    countRomanToArabicNum (romanInput);
                    break;
            }
        }
    }
    
    /**
     * Passes verified input to be converted from Arabic numeral to Roman numeral
     * 
     * @param num validated Arabic number
     */
    public void countArabicToRomanNum(int num)
    {
       controllerModel.getRomanNumeral(num);
       updateView(controllerModel.passResultArabic(), arabicInput, 0);
    }
    
    /**
     * Passes verified input to be converted from Roman numeral to Arabic numeral
     * 
     * @param num validated Roman numeral
     */
    public void countRomanToArabicNum(String num)
    {
       controllerModel.getArabicNumeral(num);
       updateView(romanInput, controllerModel.passResultRoman(), 1);
    }
    
    /**
     * Updates view class to pass the calculated results
     * 
     * @param numArabic Arabic number as result or input value
     * @param numRoman Roman numeral as result or input value
     * @param romanArabicFlag flags which argument is input and output and vice versa
     */
    public void updateView(String numArabic, int numRoman, int romanArabicFlag)
    {
        controllerView.showResult(numArabic, numRoman, romanArabicFlag);
    }
}
