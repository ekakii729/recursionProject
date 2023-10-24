/*
 * Author: Abhay Manoj
 * Purpose:
 * Date of Creation: October 20, 2023
 */

package com.ekakii.recursionProject;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class Recursion {

    /** Method Name: fillHashMapWithCount
     * @Author Abhay Manoj
     * @Date October 20, 2023
     * @Modified October 20, 2023
     * @Description Fills provided hashmap with the number of occurrences of each character in the string
     * @Parameters countOfCharacters - each character along with its number of occurrences, givenString - string that is being checked, i - index for the string
     * @Returns N/A, Data Type: Void
     * Dependencies: HashMap
     * Throws/Exceptions: N/A
     */

    private static void fillHashMapWithCount(HashMap<Character, Integer> countOfCharacters, String givenString, int i) {
        if (i == -1) return;
        Integer currentCount = countOfCharacters.get(givenString.charAt(i)); // current count of the character being accessed
        if (currentCount == null) currentCount = 1;
        else currentCount++;
        countOfCharacters.put(givenString.charAt(i), currentCount);
        fillHashMapWithCount(countOfCharacters, givenString, i - 1);
    }

    /** Method Name: checkCharacterCounts
     * @Author Abhay Manoj
     * @Date October 20, 2023
     * @Modified October 20, 2023
     * @Description Checks if it is possible for the character counts to be a palindrome
     * @Parameters valueIterator - goes through the values from its hashmap, hasOddCountBeenFound - if a character with an odd number of occurrences has already been found
     * @Returns If the character counts can be a palindrome, Data Type: Boolean
     * Dependencies: HashMap, Iterator
     * Throws/Exceptions: N/A
     */

    private static boolean checkCharacterCounts(Iterator<Integer> valueIterator, boolean hasOddCountBeenFound) {
        if (!valueIterator.hasNext()) return true;
        if (valueIterator.next() % 2 == 1) {
            if (hasOddCountBeenFound) return false;
            else return checkCharacterCounts(valueIterator, true);
        } return checkCharacterCounts(valueIterator, hasOddCountBeenFound);
    }

    /** Method Name: possiblePal
     * @Author Abhay Manoj
     * @Date October 20, 2023
     * @Modified October 20, 2023
     * @Description Checks if it is possible that the given string can be a palindrome
     * @Parameters givenString - string that is being checked
     * @Returns If the string can be a palindrome, Data Type: Boolean
     * Dependencies: HashMap, Iterator
     * Throws/Exceptions: N/A
     */

    public static boolean possiblePal(String givenString) {
        HashMap<Character, Integer> countOfCharacters = new HashMap<>(); // contains each character of the string along with its number of occurrences
        fillHashMapWithCount(countOfCharacters, givenString, givenString.length() - 1);
        Iterator<Integer> valueIterator = countOfCharacters.values().iterator(); // used to access values of hashmap
        return checkCharacterCounts(valueIterator, false);
    }

    /** Method Name: printChar
     * @Author Abhay Manoj
     * @Date October 21, 2023
     * @Modified October 21, 2023
     * @Description Prints out a character the specified number of times
     * @Parameters character - character to be printed, i - how many times to print it out
     * @Returns N/A, Data Type: Void
     * Dependencies: N/A
     * Throws/Exceptions: N/A
     */

    private static void printChar(char character, int i) {
        if (i == 0) return;
        System.out.print(character);
        printChar(character, i - 1);
    }

    /** Method Name: pattern
     * @Author Abhay Manoj
     * @Date October 21, 2023
     * @Modified October 21, 2023
     * @Description Prints out a fractal pattern
     * @Parameters numberOfStars - number of stars to be printed, buffer - the amount of empty characters before the stars
     * @Returns N/A, Data Type: Void
     * Dependencies: N/A
     * Throws/Exceptions: N/A
     */

    public static void pattern(int numberOfStars, int buffer) {
        if (numberOfStars == 0) return;
        pattern(numberOfStars / 2, buffer);
        printChar(' ', buffer);
        printChar('*', numberOfStars);
        System.out.println();
        pattern(numberOfStars / 2, buffer + numberOfStars / 2);
    }

    /** Method Name: readingShapeInnerLoop
     * @Author Abhay Manoj
     * @Date October 24, 2023
     * @Modified October 24, 2023
     * @Description Reads the columns of the provided shape
     * @Parameters line - line of the shape that was read, characterArray - array that is being written to, currentRow - current row of the shape, currentColumn - current column of the shape
     * @Returns N/A, Data Type: Void
     * Dependencies: N/A
     * Throws/Exceptions: N/A
     */

    private static void readingShapeInnerLoop(String line, char[][] characterArray, int currentRow, int currentColumn) {
        if (currentColumn == characterArray[currentRow].length) return;
        characterArray[currentRow][currentColumn] = line.charAt(currentColumn);
        readingShapeInnerLoop(line, characterArray, currentRow, currentColumn + 1);
    }

    /** Method Name: readingShapeInnerLoop
     * @Author Abhay Manoj
     * @Date October 24, 2023
     * @Modified October 24, 2023
     * @Description Reads the rows of the provided shape
     * @Parameters reader - reader used to read the file, characterArray - array that is being written to, currentRow - current row of the shape
     * @Returns N/A, Data Type: Void
     * Dependencies: BufferedReader
     * Throws/Exceptions: IOException
     */

    private static void readingShapeOuterLoop(BufferedReader reader, char[][] characterArray, int currentRow) throws IOException {
        if (currentRow == characterArray.length) return;
        String line = reader.readLine();
        readingShapeInnerLoop(line, characterArray, currentRow, 0);
        readingShapeOuterLoop(reader, characterArray, currentRow + 1);
    }

    /** Method Name: createCharArray
     * @Author Abhay Manoj
     * @Date October 23, 2023
     * @Modified October 24, 2023
     * @Description Returns array of chars filled with shape
     * @Parameters reader - used to read the txt file
     * @Returns An array of characters which contains the shape in the file, Data Type: Char[][]
     * Dependencies: BufferedReader, IOException
     * Throws/Exceptions: RuntimeException
     */

    private static char[][] createCharArray(BufferedReader reader) {
        try {
            int numberOfColumns = Integer.parseInt(reader.readLine());
            int numberOfRows = Integer.parseInt(reader.readLine());
            char[][] characterArray = new char[numberOfRows][numberOfColumns];
            readingShapeOuterLoop(reader, characterArray, 0);
            reader.close();
            return characterArray;
        } catch (IOException e) {
            System.out.println("IO ERROR --> " + e);
            throw new RuntimeException();
        }
    }

    /** Method Name: main
     * @Author Abhay Manoj
     * @Date October 20, 2023
     * @Modified October 20, 2023
     * @Description main method of the program
     * @Parameters args - arguments to be passed in
     * @Returns N/A, Data Type: Void
     * Dependencies: N/A
     * Throws/Exceptions: N/A
     */

    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("data41.txt"));
        char[][] arr = createCharArray(reader);
        for(char[] x : arr) {
            for (char y : x) {
                System.out.print(y);
            }
            System.out.println();
        }
    }
}