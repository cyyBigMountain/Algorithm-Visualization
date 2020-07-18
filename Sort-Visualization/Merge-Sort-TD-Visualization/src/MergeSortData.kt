class MergeSortData(
        N: Int,
        randomBound: Int
) {
    val numbers: IntArray = IntArray(N)
    var l: Int = 0
    var r: Int = 0
    var mergeIndex: Int = 0

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

        numbers[index]
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