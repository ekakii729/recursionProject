/*
 * Author: Abhay Manoj
 * Purpose: A program to see if a string can be a palindrome, print a fractal pattern, and find amount of blobs in a file. Contains NO loops at all.
 * Date of Creation: October 20, 2023
 */

package com.ekakii.recursionProject;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Recursion {

    /** Method Name: fillHashMapWithCount
     * @Author Abhay Manoj
     * @Date October 20, 2023
     * @Modified October 20, 2023
     * @Description Fills provided hashmap with the number of occurrences of each character in the string
     * @Parameters countOfCharacters - each character along with its number of occurrences, givenString - string that is being checked, characterCount - index for the string
     * @Returns N/A, Data Type: Void
     * Dependencies: HashMap
     * Throws/Exceptions: N/A
     */

    private static void fillHashMapWithCount(HashMap<Character, Integer> countOfCharacters, String givenString, int characterCount) {
        if (characterCount == -1) return;
        Integer currentCount = countOfCharacters.get(givenString.charAt(characterCount)); // current count of the character being accessed
        if (currentCount == null) currentCount = 1;
        else currentCount++;
        countOfCharacters.put(givenString.charAt(characterCount), currentCount);
        fillHashMapWithCount(countOfCharacters, givenString, characterCount - 1);
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

    private static boolean possiblePal(String givenString) {
        if (givenString.length() == 2) return true;
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
     * @Parameters character - character to be printed, timesToPrint - how many times to print it out
     * @Returns N/A, Data Type: Void
     * Dependencies: N/A
     * Throws/Exceptions: N/A
     */

    private static void printChar(char character, int timesToPrint) {
        if (timesToPrint == 0) return;
        System.out.print(character);
        printChar(character, timesToPrint - 1);
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

    private static void pattern(int numberOfStars, int buffer) {
        if (numberOfStars == 0) return;
        pattern(numberOfStars / 2, buffer);
        printChar(' ', buffer);
        printChar('*', numberOfStars);
        System.out.println();
        pattern(numberOfStars / 2, buffer + numberOfStars / 2);
    }

    /** Method Name: readingShapeColumns
     * @Author Abhay Manoj
     * @Date October 24, 2023
     * @Modified October 24, 2023
     * @Description Reads the columns of the provided shape
     * @Parameters line - line of the shape that was read, characterArray - array that is being written to, currentRow - current row of the shape, currentColumn - current column of the shape
     * @Returns N/A, Data Type: Void
     * Dependencies: N/A
     * Throws/Exceptions: N/A
     */

    private static void readingShapeColumns(String line, char[][] characterArray, int currentRow, int currentColumn) {
        if (currentColumn == characterArray[currentRow].length) return;
        characterArray[currentRow][currentColumn] = line.charAt(currentColumn);
        readingShapeColumns(line, characterArray, currentRow, currentColumn + 1);
    }

    /** Method Name: readDataToCharArray
     * @Author Abhay Manoj
     * @Date October 24, 2023
     * @Modified October 24, 2023
     * @Description Reads the rows of the provided shape
     * @Parameters reader - reader used to read the file, characterArray - array that is being written to, currentRow - current row of the shape
     * @Returns N/A, Data Type: Void
     * Dependencies: BufferedReader
     * Throws/Exceptions: IOException
     */

    private static void readDataToCharArray(BufferedReader reader, char[][] characterArray, int currentRow) throws IOException {
        if (currentRow == characterArray.length) return;
        String line = reader.readLine(); // reading line from file
        readingShapeColumns(line, characterArray, currentRow, 0);
        readDataToCharArray(reader, characterArray, currentRow + 1);
    }

    /** Method Name: createCharArray
     * @Author Abhay Manoj
     * @Date October 23, 2023
     * @Modified October 24, 2023
     * @Description Returns array of chars filled with shape
     * @Parameters reader - used to read the txt file
     * @Returns An array of characters which contains the shape in the file, Data Type: Char[][]
     * Dependencies: BufferedReader
     * Throws/Exceptions: IOException
     */

    private static char[][] createCharArray(BufferedReader reader) throws IOException {
        int numberOfColumns = Integer.parseInt(reader.readLine()); // first number in file is columns
        int numberOfRows = Integer.parseInt(reader.readLine()); // second line in file is rows
        char[][] characterArray = new char[numberOfRows][numberOfColumns]; // making character array from above info
        readDataToCharArray(reader, characterArray, 0);
        return characterArray;
    }

    /** Method Name: getBlobSize
     * @Author Abhay Manoj
     * @Date October 24, 2023
     * @Modified October 25, 2023
     * @Description Gets the size of a blob
     * @Parameters currentRow - current row that is being looked at, currentColumn - current column that is being looked at, characterArray - array of characters that is being read, charHasBeenVisited - if the character has been visited already
     * @Returns The size of the blob, Data Type: Integer
     * Dependencies: N/A
     * Throws/Exceptions: N/A
     */

    private static int getBlobSize(int currentRow, int currentColumn, char[][] characterArray, boolean[][] charHasBeenVisited) {
        if (currentRow < 0 || currentRow >= characterArray.length || currentColumn < 0 || currentColumn >= characterArray[0].length || charHasBeenVisited[currentRow][currentColumn] || characterArray[currentRow][currentColumn] != 'X') return 0;
        charHasBeenVisited[currentRow][currentColumn] = true;
        int blobSize = 1; // amount of characters within the 'blob'
        blobSize += getBlobSize(currentRow + 1, currentColumn, characterArray, charHasBeenVisited);
        blobSize += getBlobSize(currentRow - 1, currentColumn, characterArray, charHasBeenVisited);
        blobSize += getBlobSize(currentRow, currentColumn + 1, characterArray, charHasBeenVisited);
        blobSize += getBlobSize(currentRow, currentColumn - 1, characterArray, charHasBeenVisited);
        return blobSize;
    }

    /** Method Name: checkShapeColumns
     * @Author Abhay Manoj
     * @Date October 25, 2023
     * @Modified October 25, 2023
     * @Description Gets the number of blobs in shape
     * @Parameters currentRow - current row that is being looked at, currentColumn - current column that is being looked at, characterArray - array of characters that is being read, charHasBeenVisited - if the character has been visited already
     * @Returns The number of blobs within one shape, Data Type: Integer
     * Dependencies: N/A
     * Throws/Exceptions: N/A
     */

    private static int checkShapeColumns(int currentRow, int currentColumn, char[][] characterArray, boolean[][] charHasBeenVisited) {
        if (currentColumn == characterArray[0].length) return 0;
        if (charHasBeenVisited[currentRow][currentColumn] || characterArray[currentRow][currentColumn] != 'X') return checkShapeColumns(currentRow, currentColumn + 1, characterArray, charHasBeenVisited);
        getBlobSize(currentRow, currentColumn, characterArray, charHasBeenVisited);
        return checkShapeColumns(currentRow, currentColumn + 1, characterArray, charHasBeenVisited) + 1;
    }

    /** Method Name: checkShapeRows
     * @Author Abhay Manoj
     * @Date October 25, 2023
     * @Modified October 25, 2023
     * @Description Gets the number of blobs in shape
     * @Parameters characterArray - array of characters that is being read, charHasBeenVisited - if the character has been visited already, currentRow - current row that is being looked at
     * @Returns The number of blobs within one shape, Data Type: Integer
     * Dependencies: N/A
     * Throws/Exceptions: N/A
     */

    private static int checkShapeRows(char[][] characterArray, boolean[][] charHasBeenVisited, int currentRow) {
        if (currentRow == characterArray.length) return 0;
        return checkShapeColumns(currentRow, 0, characterArray, charHasBeenVisited) + checkShapeRows(characterArray, charHasBeenVisited, currentRow + 1);
    }

    /** Method Name: getNumberOfBlobs
     * @Author Abhay Manoj
     * @Date October 25, 2023
     * @Modified October 25, 2023
     * @Description Gets the number of blobs within one shape
     * @Parameters reader - used to read the file
     * @Returns The number of blobs within one shape, Data Type: Integer
     * Dependencies: BufferedReader
     * Throws/Exceptions: IOException
     */

    private static int getNumberOfBlobs(BufferedReader reader) throws IOException {
        char[][] characterArray = createCharArray(reader); // creates an array of the shape
        boolean[][] charHasBeenVisited = new boolean[characterArray.length][characterArray[0].length]; // for checking if individual characters have been visited
        return checkShapeRows(characterArray, charHasBeenVisited, 0);
    }

    /** Method Name: printShapeData
     * @Author Abhay Manoj
     * @Date October 25, 2023
     * @Modified October 25, 2023
     * @Description Prints out the number of blobs within one shape
     * @Parameters reader - used to read the file, currentShapeIndex - the shape that is being looked at, numberOfShapes - how many shapes are in the file
     * @Returns N/A, Data Type: N/A
     * Dependencies: BufferedReader
     * Throws/Exceptions: IOException
     */

    private static void printShapeData(BufferedReader reader, int currentShapeIndex, int numberOfShapes) throws IOException {
        if (currentShapeIndex == numberOfShapes) {
            System.out.println();
            return;
        } System.out.printf("In shape #%d, there is %d blobs.\n", currentShapeIndex + 1, getNumberOfBlobs(reader));
        printShapeData(reader, currentShapeIndex + 1, numberOfShapes);
    }

    /** Method Name: countShapes
     * @Author Abhay Manoj
     * @Date October 25, 2023
     * @Modified October 25, 2023
     * @Description Prints out the number of blobs in all shapes in a file
     * @Parameters input - used to take user input, fileName - name of the file
     * @Returns N/A, Data Type: N/A
     * Dependencies: BufferedReader, Scanner
     * Throws/Exceptions: RuntimeException
     */

    private static void countShapes(Scanner input, String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName)); // to read the file
            System.out.print("Enter number of shapes within the file: ");
            printShapeData(reader, 0, Integer.parseInt(input.nextLine()));
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found! Try retyping it.\n");
        } catch (IOException e) {
            System.out.println("IO ERROR --> " + e);
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            System.out.println("You have entered an incorrect number of shapes... recount next time.\n");
        }
    }

    /** Method Name: menuLoop
     * @Author Abhay Manoj
     * @Date October 25, 2023
     * @Modified October 25, 2023
     * @Description The menu loop of the program
     * @Parameters input - used to take user input, canRun - determines if the program can run
     * @Returns N/A, Data Type: N/A
     * Dependencies: Scanner
     * Throws/Exceptions: N/A
     */

    private static void menuLoop(Scanner input, boolean canRun) {
        if (!canRun) System.exit(0);
        System.out.println("Please select an option.\n1. Check if a String can be a palindrome\n2. Print a cool fractal pattern\n3. Get the number of blobs from a file\n4. Quit\n");
        switch (Integer.parseInt(input.nextLine())) {
            case 1 -> {
                System.out.print("Enter the word you wish to check: ");
                String response = possiblePal(input.nextLine()) ? "Yes, that can be a palindrome.\n" : "No, that cannot be a palindrome.\n"; // if the string can be palindrome
                System.out.println(response);
            } case 2 -> {
                System.out.print("For the length of the fractal, enter a number that is a power of two: "); // works even if it isn't power of two
                int numberOfStars = Integer.parseInt(input.nextLine()); // number of stars in the longest line in the fractal
                System.out.print("Enter the size of buffer between the console and the left hand side of the fractal: ");
                int bufferSize = Integer.parseInt(input.nextLine()); // description is right above
                pattern(numberOfStars, bufferSize);
            } case 3 -> {
                System.out.print("Enter EXACT name of the file (ex. myFile.txt): ");
                countShapes(input, input.nextLine());
            } case 4 -> {
                System.out.println("Program has been closed.");
                menuLoop(input, false);
            }
        } menuLoop(input, true);
    }

    /** Method Name: main
     * @Author Abhay Manoj
     * @Date October 20, 2023
     * @Modified October 20, 2023
     * @Description main method of the program
     * @Parameters args - arguments to be passed in
     * @Returns N/A, Data Type: Void
     * Dependencies: Scanner
     * Throws/Exceptions: N/A
     */

     public static void main(String[] args) {
         System.out.println("Welcome to the recursion app.");
         menuLoop(new Scanner(System.in), true);
     }
}