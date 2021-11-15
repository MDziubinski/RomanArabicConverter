package pl.polsl.mikolaj.dziubinski.model;

import java.util.ArrayList;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Class containg input data for tests 
 *
 * @author Mikolaj Dziubinski
 */
public class testData {
    private static ArrayList<String> inputArabicListTest;
    private static ArrayList<String> inputRomanListTest;
    private static ArrayList<String> outputArabicListTest;
    private static ArrayList<Integer> outputRomanListTest;
    
    public testData()
    {
        inputArabicListTest = new ArrayList<>();
        inputRomanListTest = new ArrayList<>();
        outputArabicListTest = new ArrayList<>();
        outputRomanListTest = new ArrayList<>();
    }
    
    public static void initializeDataLists()
    {
        //Arabic to Roman input
        inputArabicListTest.add("123");
        inputArabicListTest.add("999");
        inputArabicListTest.add("19");
        inputArabicListTest.add("3333");
        inputArabicListTest.add("555");
        
        //Arabic to Roman output
        outputArabicListTest.add("CXXIII");
        outputArabicListTest.add("CMXCIX");
        outputArabicListTest.add("XIX");
        outputArabicListTest.add("MMMCCCXXXIII");
        outputArabicListTest.add("DLV");
        
        //Roman to Arabic input
        inputRomanListTest.add("DCLXVI");
        inputRomanListTest.add("MMXXI");
        inputRomanListTest.add("VIII");
        inputRomanListTest.add("XIX");
        inputRomanListTest.add("XLIX");
        
        //Roman to Arabic output
        outputRomanListTest.add(666);
        outputRomanListTest.add(2021);
        outputRomanListTest.add(8);
        outputRomanListTest.add(19);
        outputRomanListTest.add(69);
        
    }   
    
//    static Stream<Arguments> passArguments()
//    {
//        initializeDataLists();
//    return Stream.of(
//        arguments.(inputArabicListTest, outputArabicListTest));
//    }
}
