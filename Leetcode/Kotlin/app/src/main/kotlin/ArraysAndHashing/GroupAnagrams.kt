
import alphabetized

/** 
Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Example 2:

Input: strs = [""]
Output: [[""]]

Example 3:

Input: strs = ["a"]
Output: [["a"]]
 */
fun groupAnagram(str: Array<String>): List<List<String>> {
    val hashMap = mutableMapOf<String, List<String>>()
    str.forEach { 
        val valueList = hashMap.getOrElse(it.alphabetized()) { mutableListOf<String>() }
        hashMap.put(it.alphabetized(), valueList + listOf(it))
     }
     return hashMap.values.toList()
}
