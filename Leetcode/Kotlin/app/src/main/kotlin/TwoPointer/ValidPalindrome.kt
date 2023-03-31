import java.util.*

/*
A phrase is a palindrome if, 
after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, 
it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.

 

Example 1:

Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
Example 2:

Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.
Example 3:

Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.
*/
fun isPalindrome(s: String): Boolean {
    val filteredString = s.filter { it.isLetterOrDigit() }.lowercase(Locale.getDefault())
    return filteredString == filteredString.reversed()
}

fun isPalindrome2(s: String): Boolean {
    var start = 0
    var last = s.length - 1
    while (start < last) {
        if(s[start].isLetterOrDigit().not()) {
            start++
        } else if(s[last].isLetterOrDigit().not()) {
            last--
        } else {
            if (s[start].lowercase() != s[last].lowercase()) {
                return false
            }
            start++
            last--
        }
    }
    return true
}