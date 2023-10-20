/*
 * Author: Abhay Manoj
 * Purpose:
 * Date of Creation: October 20, 2023
 */

package com.ekakii.recursionProject;
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
     * @Parameters valueIterator - goes through the values from its hashmap, hasOddCount - if a character with an odd number of occurrences has already been found
     * @Returns If the character counts can be a palindrome, Data Type: Boolean
     * Dependencies: HashMap, Iterator
     * Throws/Exceptions: N/A
     */

    private static boolean checkCharacterCounts(Iterator<Integer> valueIterator, boolean hasOddCount) {
        if (!valueIterator.hasNext()) return true;
        if (valueIterator.next() % 2 == 1) {
            if (hasOddCount) return false;
            else return checkCharacterCounts(valueIterator, true);
        } return checkCharacterCounts(valueIterator, hasOddCount);
    }

    /** Method Name: isPalindrome
     * @Author Abhay Manoj
     * @Date October 20, 2023
     * @Modified October 20, 2023
     * @Description Checks if it is possible that the given string can be a palindrome
     * @Parameters givenString - string that is being checked
     * @Returns If the string can be a palindrome, Data Type: Boolean
     * Dependencies: HashMap, Iterator
     * Throws/Exceptions: N/A
     */

    public static boolean isPalindrome(String givenString) {
        HashMap<Character, Integer> countOfCharacters = new HashMap<>(); // contains each character of the string along with its number of occurrences
        fillHashMapWithCount(countOfCharacters, givenString, givenString.length() - 1);
        Iterator<Integer> valueIterator = countOfCharacters.values().iterator(); // used to access values of hashmap
        return checkCharacterCounts(valueIterator, false);
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

    public static void main(String[] args) {
        System.out.println(isPalindrome("racecar"));
    }
}