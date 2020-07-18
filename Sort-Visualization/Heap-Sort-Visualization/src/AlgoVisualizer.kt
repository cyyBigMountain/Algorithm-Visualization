import javafx.application.Application

fun main(args: Array<String>) {
    Application.launch(AlgoApplication::class.java, *args)
}

class AlgoVisualizer(
    application: AlgoApplication
){
    companion object {
        const val DELAY: Int = 20
        const val N: Int = 100
    }

    private val data: HeapSortData by lazy {
        HeapSortData(N, application.sceneHeight)
    }

    private val run = {
        setData(data.N())

        for (i in (data.N() - 1 - 1) / 2 downTo  0) {
            shiftDown(data.N(), i)
        }

        for (i in data.N() - 1 downTo 1) {
            data.swap(0, i)
            shiftDown(i, 0)
            setData(i)
        }

        setData(0)
    }

    private val shiftDown = { n: Int, k: Int ->
        var i = k
        while (2 * i + 1 < n) {
            var j = 2 * i + 1
            if (j + 1 < n && data.get(j + 1) > data.get(j)) {
                j++
            }

            if (data.get(i) >= data.get(j)) break

            data.swap(i, j)
            setData(data.heapIndex)

            i = j
        }
    }

    private val setData = { heapIndex: Int ->
        data.heapIndex = heapIndex

        application.render(data)
        AlgoVisHelper.pause(DELAY)
    }

    init {
        Thread(run).start()
    }
}
