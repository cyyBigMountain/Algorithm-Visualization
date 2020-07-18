import javafx.application.Application
import javafx.scene.control.Button
import javafx.scene.layout.Pane

class AlgoVisualizer(
    application: AlgoApplication,
    root: Pane
){
    companion object {
        private const val DELAY = 20
        private const val N = 100
    }

    private val data: InsertionSortData by lazy {
        InsertionSortData(N, application.sceneHeight) // 无序
        //InsertionSortData(N, application.sceneHeight, InsertionSortData.Type.NearlyOrdered) //近乎有序
    }


    private val run = {
        setData(0, -1)

        for (i in 0 until data.N()) {
            setData(i, i)
            var j = i
            while (j > 0 && data.get(j) < data.get(j - 1)) {
                data.swap(j, j - 1)
                setData(i + 1, j - 1)
                j--
            }
        }

        setData(data.N(), -1)
    }

    private val setData = { orderedIndex: Int, currentIndex: Int ->
        data.orderedIndex = orderedIndex
        data.currentIndex = currentIndex

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