import javafx.application.Application
import javafx.scene.layout.Pane

class AlgoVisualizer(
    application: AlgoApplication,
    root: Pane
){
    companion object {
        const val DELAY: Int = 20
        const val N: Int = 100
    }

    private val data: TwoWayQuickSortData by lazy {
        //TwoWayQuickSortData(N, application.sceneHeight)
        TwoWayQuickSortData(N, application.sceneHeight, TwoWayQuickSortData.Type.Identical)
    }

    private val run = {
        setData(-1, -1, -1, -1, -1, -1)
        quickSort2Ways(0, data.N() - 1)
        setData(-1, -1, -1, -1, -1, -1)
    }

    private fun quickSort2Ways(l: Int, r: Int) {
        if (l > r) return
        if (l == r) {
            setData(l, r, l, -1, -1, -1)
            return
        }

        setData(l, r, -1, -1, -1, -1)

        val p = partition(l, r)
        quickSort2Ways(l, p - 1)
        quickSort2Ways(p + 1, r)
    }

    private val partition = { l: Int, r: Int ->
        val p = (Math.random() * (r - l + 1)).toInt() + l
        setData(l, r, -1, p, -1, -1)

        data.swap(l, p)
        val v = data.get(l)
        setData(l, r, -1, l, -1, -1)

        var i = l + 1
        var j = r
        setData(l, r, -1, l, i, j)
        while (true) {
            while (i <= r && data.get(i) < v){
                i ++
                setData(l, r, -1, l, i, j)
            }

            while (j >= l + 1 && data.get(j) > v){
                j --
                setData(l, r, -1, l, i, j)
            }

            if (i > j)
                break

            data.swap(i, j);
            i++
            j--
            setData(l, r, -1, l, i, j)
        }

        data.swap(l, j)
        setData(l, r, j, -1, -1, -1)

        j
    }

    private val setData = {
        l: Int, r: Int, fixedPivot: Int, curPivot: Int, curL: Int, curR: Int ->
        data.l = l
        data.r = r
        if(fixedPivot != -1) {
            data.fixedPivots[fixedPivot] = true
        }
        data.curPivot = curPivot;
        data.curL = curL
        data.curR = curR

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