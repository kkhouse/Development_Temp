package TwoPointer

fun waterSolution(array : IntArray): Int {
    var l = 0
    var r = array.lastIndex
    var max = 0

    while (l < r) {
        val area = minOf(array[l], array[r]) * (r-l)
        max = maxOf(area, max)
        when {
            array[l] > array[r] -> r--
            else -> l++
        }
    }
    return max
}