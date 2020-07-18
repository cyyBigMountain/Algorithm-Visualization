class TwoWayQuickSortData(
        N: Int,
        randomBound: Int,
        dataType: Type = Type.Default
) {
    enum class Type{
        Default,
        NearlyOrdered,
        Identical
    }

    private val numbers: IntArray = IntArray(N)
    var l: Int = -1
    var r: Int = -1
    var curPivot: Int = -1
    var curL: Int = -1
    var curR: Int = -1
    val fixedPivots: BooleanArray = BooleanArray(N)

    val swap = { i: Int, j: Int ->
        require(i > 0 || i <= numbers.size || j > 0 || j <= numbers.size) {
            "Invalid index to access Sort Data."
        }

        val t = numbers[i]
        numbers[i] = numbers[j]
        numbers[j] = t
    }

    init {
        var lBound = 1
        var rBound = randomBound
        if (dataType == Type.Identical) {
            val avgNumber = (lBound + rBound) / 2
            lBound = avgNumber
            rBound = avgNumber
        }

        for (i in 0 until N) {
            numbers[i] = (Math.random() * (rBound - lBound + 1)).toInt() + lBound
            fixedPivots[i] = false
        }

        if (dataType == Type.NearlyOrdered) {
            numbers.sort()
            val swapTime = (0.01 * N).toInt()
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

        numbers[index]
    }
}