package pl.polsl.mikolaj.dziubinski.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test model class 
 * 
 * @author Mikolaj Dziubinski
 * @version 1.2
 */
public class ModelTest {
    
    private Model model;
    
    @BeforeEach
    public void createModel()
    {
        model = new Model();
    }

    /**
     * Test of validateInput method, of class Model.
     * @param input
     */
    @ParameterizedTest
    @ValueSource(strings = {"1221", "MMMXX", "50"})
    public void testValidateInput(String input) {
        int testResult = model.validateInput(input);
        assertNotEquals(testResult, 0, "Passed strings are not Roman or Arabic");
    }

    /**
     * Test of checkInput method, of class Model.
     * @throws java.lang.Exception
     */
    @Test
    public void testCheckInput() throws Exception {
    }

    /**
     * Test of validateInputList method, of class Model.
     */
    @Test
    public void testValidateInputList() {
        List<String> inputTestList = new ArrayList<>();
        Boolean testBool = model.validateInputList(inputTestList);
        assertEquals(testBool, true, "Input Array should not be empty");
        assertNotEquals(testBool, false, "InputArray should be empty");
    }

    /** 
     * Parameterized test of getRomanNumeralList method, of class Model.
     * @param input
     * @param output
     */
    @ParameterizedTest
    @CsvSource({"50, L", "19, XIX", "55, LV"})
    public void testGetRomanNumeral(int input, String output) {
        model.getRomanNumeral(input);
        assertEquals(model.passResultArabic(), output, "Obtained values are not correct");
    }

    /**
     * Parameterized test of getArabicNumeral method, of class Model.
     * @param input
     * @param output
     */
    @ParameterizedTest
    @CsvSource({"L, 50", "XIX, 19", "LV, 55"})
    public void testGetArabicNumeral(String input, int output) {
        model.getArabicNumeral(input);
        assertEquals(model.passResultRoman(), output, "Obtained values are not correct");
        
    }

    /**
     * Test of stringToInt method, of class Model.
     */
    @Test
    public void testStringToInt() {
        String testStringInteger = "100";
        int testIntegerCorrectResult = 100;
        int testIntegerResult = model.stringToInt(testStringInteger);
        
        assertEquals(testIntegerCorrectResult, testIntegerResult, "Variables are not the same");
    }
    
}

