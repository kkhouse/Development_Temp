package TwoPointer

/*
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.



Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation:
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.
Example 2:

Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.
Example 3:

Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.
 */
fun threeSum(nums: IntArray): List<List<Int>> {
    if (nums.size < 3) return emptyList()
    nums.sort()
    val ans = mutableSetOf<List<Int>>()
    for(curr in 0..nums.size - 2) {
        var left = curr + 1
        var right = nums.lastIndex
        while(left < right) {
            val sum = nums[curr] + nums[left] + nums[right]
            when {
                sum == 0 -> ans.add(listOf(nums[curr],  nums[left++], nums[right--]))
                sum < 0 -> left++
                sum > 0 -> right--
            }
        }
    }
    return ans.toList()
}