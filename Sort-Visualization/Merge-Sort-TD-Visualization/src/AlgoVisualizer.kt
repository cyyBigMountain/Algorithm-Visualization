import javafx.application.Application
import javafx.scene.layout.Pane

class AlgoVisualizer(
    application: AlgoApplication,
    root: Pane
){
    companion object {
        const val DELAY: Int = 40
        const val N: Int = 100
    }

    private val data: MergeSortData by lazy {
        MergeSortData(N, application.sceneHeight)
    }

    private val run = {
        setData(-1, -1, -1)
        mergeSort(0, data.N() - 1)
        setData(0, data.N() - 1, data.N() - 1)
    }

    private fun mergeSort(l: Int, r: Int) {
        require(r > l){ return }

        setData(l, r, -1)
        val mid = (l + r) / 2
        mergeSort(l, mid)
        mergeSort(mid + 1, r)
        merge(l, mid, r)
    }

    private val merge = { l: Int, mid: Int, r: Int ->
        val aux = data.numbers.copyOfRange(l, r + 1)

        var i = l
        var j = mid + 1
        for (k in l .. r) {
            when (true) {
                i > mid -> {
                    data.numbers[k] = aux[j - l];
                    j++
                }
                j > r -> {
                    data.numbers[k] = aux[i - l]
                    i++
                }
                aux[i - l] < aux[j - l] -> {
                    data.numbers[k] = aux[i - l]
                    i++
                }
                else -> {
                    data.numbers[k] = aux[j - l]
                    j++
                }
            }

            setData(l, r, k)
        }
    }

    private val setData = { l: Int, r: Int, mergeIndex: Int ->
        data.l = l
        data.r = r
        data.mergeIndex = mergeIndex

        application.render(data)
        AlgoVisHelper.pause(DELAY)
    }

    init {
        Thread(run).start()
    }
}


fun main(args: Array<String>) {
    Application.launch(AlgoApplication::class.java, *args)
}