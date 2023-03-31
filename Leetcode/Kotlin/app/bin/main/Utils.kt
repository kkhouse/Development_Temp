/**
 * convert String to sortedCharArray
 */
fun String.alphabetized() = String(toCharArray().apply { sort() })
