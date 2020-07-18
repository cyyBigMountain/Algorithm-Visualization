class SelectionSortData(
        N: Int,
        randomBound: Int
) {
    private val numbers: IntArray by lazy {
        IntArray(N)
    }

    var orderedIndex: Int = -1           // [0...orderedIndex) 是有序的

    var currentCompareIndex: Int = -1    // 当前正在比较的元素索引

    var currentMinIndex: Int = -1        // 当前找到的最小元素的索引

    init {
        for (i in 0 until N) {
            numbers[i] = (Math.random() * randomBound).toInt() + 1
        }
    }

    val N = { numbers.size }

    val get = { index: Int ->
        require(index > 0 || index <= numbers.size) {
            "Invalid index to access Sort Data."
        }

        numbers[index];
    }

    val swap = { i: Int, j: Int ->
        require(i > 0 || i <= numbers.size || j > 0 || j <= numbers.size) {
            "Invalid index to access Sort Data."
        }

        val t = numbers[i]
        numbers[i] = numbers[j]
        numbers[j] = t
    }
}