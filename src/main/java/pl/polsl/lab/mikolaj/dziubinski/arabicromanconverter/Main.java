/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab.mikolaj.dziubinski.arabicromanconverter;

/**
 *
 * @author mikol
 */
public class Main 
{
    /**
     * Main method of writing out the parameters.
     * 
     * @param args program call parameters
     */
    public static void main(String args[]) {
        // number of parameters passed to the progra
        //System.out.println("Program parameters: ");
        for (int j = 1;j<4000;j++) {
    System.out.println("i="+j+" -> "+arabicRoman(j));
        }
  }

    private static String arabicRoman(int n) {
    String[] romanNumerals = { "M",  "CM", "D", "CD", "C", "XC", "L","XL"  , "X", "IX", "V", "IV","I" };
    String[] romanNums = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "CM", "M"};
    int[] romanNumeralNums = {  1000, 900, 500,  400 , 100,  90,  50,  40, 10, 9, 5,  4 ,1 };
    int[] arabicNums = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
    String finalRomanNum = "";
    //String romanNum = "";

    for (int i = 0; i < romanNumeralNums.length; i ++) {
            int currentNum = n /romanNumeralNums[i];
            if (currentNum==0) {
                continue;
            }

            for (int j = 0; j < currentNum; j++) {
                finalRomanNum +=romanNumerals[i];
            }

            n = n%romanNumeralNums[i];
    }
    
    for (int i = 0; i < arabicNums.length; i ++) {
            int currentNum = n /arabicNums[i];
            if (currentNum==0) {
                continue;
            }

            for (int j = 0; j < currentNum; j++) {
                finalRomanNum +=romanNumerals[i];
            }

            n = n%arabicNums[i];
    }
    return finalRomanNum;
    }
}

