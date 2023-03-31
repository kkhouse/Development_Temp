package ArraysAndHashing


/**
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 *
 * You must write an algorithm that runs in O(n) time.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * Example 2:
 *
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 */
//　これだとタイムオーバー
fun solutionSequence(nums: IntArray): Int {
    val sortedList = nums.sortedArray().toSet().toList()
    return findMaxLength(
        hash = createSequenceMap(
            sortedList = sortedList
        )
    )
}

fun createSequenceMap(sortedList: List<Int>): Map<Int, List<Int>> {
    return sortedList.groupBy { it - sortedList.indexOf(it) }
}

fun findMaxLength(hash: Map<Int, List<Int>>): Int {
    return hash.values.maxOfOrNull { it.size } ?: 0
}

// こっちならOK
fun longestConsecutive(nums: IntArray): Int {
    val numsSet = nums.toSet()
    return numsSet.fold(0) { maxLen, num ->
        if (num - 1 !in numsSet) { // 現在のnumがnumsSetないシーケンスの先頭（最小の値）かどうか
            val currentLen = generateSequence(num) { it + 1 } // 無限配列
                .takeWhile { it in numsSet } // numsSetから連続した配列を作成
                .count()
            maxOf(maxLen, currentLen)
        } else {
            maxLen
        }
    }
}

