import javafx.application.Application
import javafx.scene.layout.Pane

fun main(args: Array<String>) {
    Application.launch(AlgoApplication::class.java, *args)
}

class AlgoVisualizer(
    application: AlgoApplication,
    root: Pane
){
    private val data: QuickSortData by lazy {
        QuickSortData(N, application.sceneHeight, QuickSortData.Type.Default)
    }

    companion object {
        const val DELAY: Int = 20
        const val N: Int = 100
    }

    private val run = {
        setData(-1, -1, -1, -1, -1)
        quickSort(0, data.N() - 1)
        setData(-1, -1, -1, -1, -1)
    }

    private fun quickSort(l: Int, r: Int) {
        if (l > r) return
        if (l == r) {
            setData(l, r, l, -1, -1)
            return
        }

        setData(l, r, -1, -1, -1)
        val p = partition(l, r) // 获取分割点
        quickSort(l, p - 1)
        quickSort(p + 1, r)
    }

    private val partition = { l: Int, r: Int ->
        val p = (Math.random() * (r - l + 1)).toInt() + l
        setData(l, r, -1, p, -1)
        data.swap(l, p)

        val v = data.get(l)
        setData(l, r, -1, l, -1)

        var j = l
        for (i in l + 1 .. r) {
            setData(l, r, -1, l, i)
            if (data.get(i) < v) {
                j++
                data.swap(j, i)
                setData(l, r, -1, l, i)
            }
        }

        data.swap(l, j)
        setData(l, r, j, -1, -1)
        j
    }

    private val setData = {
        l: Int, r: Int, fixedPivot: Int, curPivot: Int, curElement: Int ->
        data.l = l
        data.r = r
        if (fixedPivot != -1) {
            data.fixedPivot[fixedPivot] = true
        }
        data.curPivot = curPivot
        data.curElement = curElement

        application.render(data)
        AlgoVisHelper.pause(DELAY)
    }

    init {
        Thread(run).start()
    }
}
