package pl.polsl.mikolaj.dziubinski.controller;

import pl.polsl.mikolaj.dziubinski.model.Model;
import pl.polsl.mikolaj.dziubinski.view.View;

/**
 *Program that converts Arabic number provided by the user to roman numerals, and vice versa.
 * 
 * @author Mikolaj Dziubinski
 * @version 1.1
 */
public class RomanArabicConverter {
    
   /**
    * Main method of the application
    * 
    * @param args the command line arguments
    */
    public static void main(String args[])
    {
        View view = new View();
        Model model = new Model();
        
        Controller controller = new Controller(view, model);
        controller.processInput(args);
        
    }
}
