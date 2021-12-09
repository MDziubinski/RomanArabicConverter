package pl.polsl.mikolaj.dziubinski.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pl.polsl.mikolaj.dziubinski.model.Model;
import pl.polsl.mikolaj.dziubinski.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

/**
 * Controller class allows to separate model and view classes
 *
 * @author Mikolaj Dziubinski
 * @version 1.4
 */
public class Controller {
    /**View object */
    private final View controllerView;
    /**Model object */
    private final Model controllerModel; 
    /**Roman numeral input */
    private String romanInput = "";
    /**Input list of strings */
    private List<String> inputList = new ArrayList<String>();
    /**Input list of strings with correct part of input*/
    private List<String> inputListCorrect = new ArrayList<String>();
    /**Input list of strings with incorrect part of input*/
    private List<String> inputListInCorrect = new ArrayList<String>();
    /**Output list of strings */
    private List<String> outputListCorrect = new ArrayList<String>();
    
    /**Whole input text */
    private String inputText = "";
    
    /**Mode of conversion */
    private String mode = "";
    
    /**
     * Controller class constructor
     *
     * @param view View class object
     * @param model Model class object
     */
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
    public void initialize(String[] num)
    {
        controllerView.initializeGUI(); //TO DIVIDE FROM CONSTRUCTOR
        //Passing input array to array list with use of stream
        initButtons();
    }
    
    public void initButtons()
    {
        controllerView.getConvertButton().addActionListener(event -> verifyInput());
    }
    
   
    public void verifyInput()
    {
     inputText = controllerView.getInputString();
     inputList = new ArrayList<>(Arrays.asList(inputText.split("\\W+")));
     System.out.println(inputList.size());
    int inputVerif = 0;
     if((inputList.size() == 1) && inputList.get(0).isEmpty())
     {
         controllerView.getPopupEmptyInput();
         controllerView.setLogTable("Empty input.");
         
     }
     else
     {
        mode = controllerView.getComboBoxOption();
        if(mode.equals("Arabic to Roman"))
        {
            for(String temp: inputList)
            {
                inputVerif = controllerModel.validateInputGUI(temp, 0);
            }
           inputListInCorrect = controllerModel.getInputListInCorrect();
           if(inputListInCorrect.size() == 0)
           {
               
               inputListCorrect = controllerModel.getInputListCorrect();
               for(String temp: inputListCorrect)
                    {
                       controllerModel.getRomanNumeral(Integer.parseInt(temp));
                       outputListCorrect.add(controllerModel.passResultArabic());
                    }
               controllerView.cleanOut();
               controllerView.setLogTable("Chosen mode:" + mode + "\n" + "Input " + inputListCorrect
                       + "\n" + "Output " + outputListCorrect);
               controllerView.showResultGUI(inputListCorrect, outputListCorrect);
               controllerModel.cleanOut();
               outputListCorrect.clear();
               inputListCorrect.clear();
           }
           else
           {
               String incorrectInput = String.join(" ", inputListInCorrect);
               controllerView.getPopupWrongArabicInput(incorrectInput);
               inputListInCorrect.clear();
           }
        }
        else if (mode.equals("Roman to Arabic"))
        {
            for(String temp: inputList)
            {
                inputVerif = controllerModel.validateInputGUI(temp, 1);
            }
           inputListInCorrect = controllerModel.getInputListInCorrect();
           if(inputListInCorrect.size() == 0)
           {
               inputListCorrect = controllerModel.getInputListCorrect();
               for(String temp: inputListCorrect)
               {
                   controllerModel.getArabicNumeral(temp);
                   outputListCorrect.add(String.valueOf(controllerModel.passResultRoman()));
               }
               controllerView.cleanOut();
               controllerView.setLogTable("Chosen mode:" + mode + "\n" + "Input " + inputListCorrect
                       + "\n" + "Output " + outputListCorrect);
               controllerView.showResultGUI(inputListCorrect, outputListCorrect);
               controllerModel.cleanOut();
               outputListCorrect.clear();
               inputListCorrect.clear();
           }
           else
           {
               String incorrectInput = String.join(" ", inputListInCorrect);
               controllerView.getPopupWrongRomanInput(incorrectInput);
               inputListInCorrect.clear();
           }
        }
        else if (mode.equals("Inteligent"))
        {
            List<Integer> inputModeList = new ArrayList<Integer>();
            for(String temp: inputList)
            {
                inputModeList.add(controllerModel.validateInputGUI(temp, 2)); 
            }
           inputListInCorrect = controllerModel.getInputListInCorrect();
           if(inputListInCorrect.size() == 0)
           {
               inputListCorrect = controllerModel.getInputListCorrect();
               for(int i = 0; i<inputListCorrect.size(); i++)
               {
                    if(inputModeList.get(i) == 1)
                    {
                        controllerModel.getRomanNumeral(Integer.parseInt(inputListCorrect.get(i)));
                        outputListCorrect.add(controllerModel.passResultArabic());
                    }
                    else if(inputModeList.get(i) == 2)
                    {
                        controllerModel.getArabicNumeral(inputListCorrect.get(i));
                        outputListCorrect.add(String.valueOf(controllerModel.passResultRoman()));
                    } 
               }
               controllerView.cleanOut();
               controllerView.setLogTable("Chosen mode: " + mode + " " + " Input " + inputListCorrect
                       + " " + " Output " + outputListCorrect);
               controllerView.showResultGUI(inputListCorrect, outputListCorrect);
               controllerModel.cleanOut();
               outputListCorrect.clear();
               inputListCorrect.clear();
               inputModeList.clear();
           }
           else
           {
               String incorrectInput = String.join(" ", inputListInCorrect);
               controllerView.getPopupWrongIntelligentInput(incorrectInput);
               
               inputModeList.clear();
               inputListInCorrect.clear();
           }
           inputModeList.clear();
        }
        
        
        inputListInCorrect.clear();
     } 
    
    }
    
}
