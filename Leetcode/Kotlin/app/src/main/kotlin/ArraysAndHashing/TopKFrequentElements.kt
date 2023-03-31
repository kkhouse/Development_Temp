/*
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
 */

 fun topKFrequentElems(nums: IntArray, k: Int): List<Int> {
    val map = nums.groupBy { it }.mapValues { it.value.size }.toMutableMap()
    val ans = mutableListOf<Int>()
    (0 until k).forEach {
        val target = map.maxByOrNull { it.value }!!.key
        ans.add(target)
        map.remove(target)
    }
    return ans
 }