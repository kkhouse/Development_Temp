package ArraysAndHashing

fun sudokuSolution(board : Array<Array<String>>): Boolean {
    val hashSet = HashSet<String>()
    board.forEachIndexed { index , row ->
        row.forEachIndexed { index2, value ->
            if(value == ".") return@forEachIndexed
            if (!hashSet.add("$index row $value")
                || !hashSet.add("$index2 column $value")
                || !hashSet.add("${index/3} ${index2/3} sq $value") ) {
                return false
            }
        }
    }
    return true
}
