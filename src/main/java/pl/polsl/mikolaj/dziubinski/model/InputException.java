package pl.polsl.mikolaj.dziubinski.model;

/**
 * Exception class for objects when passed input is invalid
 * 
 * @author Mikolaj Dziubinski
 * @version 1.3
 */
 public class InputException extends Exception
   {
    /**
    * Non parameter constructor
    */
    public InputException() {}
        
     /**
      * Exception class parametrized constructor
      * 
      * @param message display message
      */
    public InputException(String message)
        {
            super(message);
        }
    }
