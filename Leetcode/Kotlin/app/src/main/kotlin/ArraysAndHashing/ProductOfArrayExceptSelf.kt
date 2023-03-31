/*
Given an integer array nums, 
return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.

Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]

Example 2:

Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]

Resolution
https://andrewsullivan.co/articles/2021/9/2/algorithms-product-of-array-except-self
*/

fun productOfArrayExceptSelf(nums: IntArray): IntArray {
    val lefts = readyToLeft(nums)
    val rights = readyToRight(nums)
    return lefts.mapIndexed { index , value -> rights[index] * value }.toIntArray()
}

fun readyToLeft(nums:IntArray) : List<Int> {
    val lefts = mutableListOf(1) // 最初に1を入れておく
    nums.forEachIndexed { index , value ->
        when(index) {
            nums.lastIndex -> Unit
            else -> lefts.add(index + 1, lefts[index] * value)
        }
    }
    return lefts
}

fun readyToRight(nums:IntArray) : List<Int> {
    var right = mutableListOf<Int>()
    nums.reversed().forEachIndexed { index, value ->
        when(index) {
            0 ->  right.add(0, value)
            nums.lastIndex -> right = (right.asReversed() + listOf(1)).toMutableList()
            else -> right.add(index, right[index-1] * value)
        }
    }
    return right
}