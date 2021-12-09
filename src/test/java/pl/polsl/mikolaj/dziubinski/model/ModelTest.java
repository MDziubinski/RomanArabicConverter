package pl.polsl.mikolaj.dziubinski.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test model class 
 * 
 * @author Mikolaj Dziubinski
 * @version 1.4
 */
public class ModelTest {
    
    /**Model object */
    private Model model;
    
    /**
     * Method with before each annotation to create new model before each test
     */
    @BeforeEach
    public void createModel()
    {
        model = new Model();
    }

    /**
     * Test of validateInputList method, of class Model.
     */
    @Test
    public void testValidateInputList() {
        List<String> emptyInputList = new ArrayList<>();
        List<String> notEmptyInputList = new ArrayList<>();
        notEmptyInputList.add("Test");

        assertEquals(model.validateInputList(emptyInputList), true, "Input Array should not be empty");
        assertTrue(emptyInputList.isEmpty(), "Input Array should not be empty");
        assertEquals(model.validateInputList(notEmptyInputList), false, "InputArray should be empty");
        assertFalse(notEmptyInputList.isEmpty(), "InputArray should be empty");
    }

    /** 
     * Parameterized test of getRomanNumeralList method, of class Model.
     * @param output supposed Roman numeral as result in string 
     * @param input Arabic number as input
     */
    @ParameterizedTest
    @MethodSource("provideTestValues")
    public void testGetRomanNumeral(String output, int input) {
        model.getRomanNumeral(input);
        assertEquals(model.passResultArabic(), output, "Obtained values are not correct");
    }

    /**
     * Parameterized test of getArabicNumeral method, of class Model.
     * @param input Roman numeral as input
     * @param output supposed Arabic number as result 
     */
    @ParameterizedTest
    @MethodSource("provideTestValues")
    public void testGetArabicNumeral(String input, int output) {
        model.getArabicNumeral(input);
        assertEquals(model.passResultRoman(), output, "Obtained values are not correct");
    }
    
    /**
     * Stream method to pass values to unit parametrized unit tests
     * 
     * @return stream of arguments consisting of Roman numeral of type string and Arabic number of type int
     */
    public static Stream<Arguments> provideTestValues()
    {
        return Stream.of(
        Arguments.of("L", 50),
        Arguments.of("XXVI", 26),
        Arguments.of("I", 1),
        Arguments.of("LV", 55),
        Arguments.of("MMMCCXCV", 3295),
        Arguments.of("MMMDCCCXLIX", 3849),
        Arguments.of("MCCCLXXV", 1375),
        Arguments.of("XIX", 19),
        Arguments.of("CXL", 140),
        Arguments.of("CXX", 120),
        Arguments.of("MMMCCCXXXVIII", 3338),
        Arguments.of("XXXIX", 39),
        Arguments.of("CCCXII", 312),
        Arguments.of("XCVIII", 98)
        );  
    }
}

