import javafx.application.Application
import javafx.scene.control.Button
import javafx.scene.layout.Pane

class AlgoVisualizer(
    application: AlgoApplication
){
    companion object {
        const val DELAY: Int = 20
        const val N: Int = 100
    }

    private val data: SelectionSortData by lazy {
        SelectionSortData(N, application.sceneHeight)
    }

    private val run = {
        setData(0, -1, -1)

        for (i in 0 until data.N()) {
            // 寻找[i, n)区间里的最小值的索引
            var minIndex = i
            setData(i, -1, minIndex)
            for (j in i + 1 until data.N()) {
                setData(i, j, minIndex)
                if (data.get(j) < data.get(minIndex)) {
                    minIndex = j
                    setData(i, j, minIndex)
                }
            }
            data.swap(i, minIndex)
            setData(i + 1, -1, -1)
        }

        setData(data.N(), -1, -1)
    }

    private val setData = {
        orderedIndex: Int, currentCompareIndex: Int, currentMinIndex: Int ->

        data.orderedIndex = orderedIndex;
        data.currentCompareIndex = currentCompareIndex;
        data.currentMinIndex = currentMinIndex;

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