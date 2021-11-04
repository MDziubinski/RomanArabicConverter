package pl.polsl.mikolaj.dziubinski.model;

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
     */
    @Test
    public void testValidateInput() {
    }

    /**
     * Test of checkInput method, of class Model.
     */
    @Test
    public void testCheckInput() throws Exception {
    }

    /**
     * Test of validateInputList method, of class Model.
     */
    @Test
    public void testValidateInputList() {
    }

    /**
     * Parameterized test of getRomanNumeralList method, of class Model.
     */
    @ParameterizedTest
    @CsvSource({"50, L", "19, XIX", "55, LV", "5, 5"})
    public void testGetRomanNumeral(int input, String output) {
        model.getRomanNumeral(input);
        assertEquals(model.passResultArabic(), output, "Obtained values are not correct");
    }

    /**
     * Parameterized test of getArabicNumeral method, of class Model.
     */
    @ParameterizedTest
    @CsvSource({"L, 50", "XIX, 19", "LV, 55", "5, 5"})
    public void testGetArabicNumeral(String input, int output) {
        model.getArabicNumeral(input);
        assertEquals(model.passResultRoman(), output, "Obtained values are not correct");
        
    }

    /**
     * Test of stringToInt method, of class Model.
     */
    @Test
    public void testStringToInt() {
    }
    
}
