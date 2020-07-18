class InsertionSortData(
        N: Int,
        randomBound: Int,
        dataType: Type = Type.Default
) {
    enum class Type{
        Default,
        NearlyOrdered
    }
    private val numbers: IntArray = IntArray(N)

    var orderedIndex: Int = -1      // [0...orderedIndex)是有序的
    var currentIndex: Int = -1      // 当前正在处理的索引

    val swap = { i: Int, j: Int ->
        require(i > 0 || i <= numbers.size || j > 0 || j <= numbers.size) {
            "Invalid index to access Sort Data."
        }

        val t = numbers[i]
        numbers[i] = numbers[j]
        numbers[j] = t
    }

    init {
        for (i in 0 until N) {
            numbers[i] = (Math.random() * randomBound).toInt() + 1
        }
        if (dataType == Type.NearlyOrdered) {
            numbers.sort()
            val swapTime = (0.02 * N).toInt()
            for (i in 0 until swapTime) {
                val a = (Math.random() * N).toInt()
                val b = (Math.random() * N).toInt()
                swap(a, b)
            }
        }
    }

    val N = { numbers.size }

    val get = { index: Int ->
        require(index > 0 || index <= numbers.size) {
            "Invalid index to access Sort Data."
        }

        numbers[index];
    }
}