import javafx.application.Application
import javafx.scene.layout.Pane

fun main(args: Array<String>) {
    Application.launch(AlgoApplication::class.java, *args)
}

class AlgoVisualizer(
    application: AlgoApplication,
    root: Pane
){
    private val data: ThreeWayQuickSortData by lazy {
        //ThreeWayQuickSortData(N, application.sceneHeight)
        ThreeWayQuickSortData(N, application.sceneHeight, ThreeWayQuickSortData.Type.Identical)
    }

    companion object {
        const val DELAY: Int = 20
        const val N: Int = 100
    }

    private val run = {
        setData(-1, -1, -1, -1, -1, -1)
        quickSort3Ways(0, data.N() - 1)
        setData(-1, -1, -1, -1, -1, -1)
    }

    private fun quickSort3Ways(l: Int, r: Int) {
        if (l > r) return
        if (l == r) {
            setData(l, r, l, -1, -1, -1)
            return
        }

        val p = (Math.random() * (r - l + 1)).toInt() + l
        setData(l, r, -1, p, -1, -1)

        data.swap(l, p)
        val v = data.get(l)
        setData(l, r, -1, l, -1, -1)

        var lt = l
        var gt = r + 1
        var i = l + 1
        setData(l, r, -1, l, lt, gt)

        while (i < gt) {
            when (true) {
                data.get(i) < v -> {
                    data.swap(i, lt + 1)
                    i++
                    lt++
                }
                data.get(i) > v -> {
                    data.swap(i, gt - 1)
                    gt--
                }
                else -> i++
            }
            setData(l, r, -1, l, i, gt)
        }

        data.swap(l, lt)
        setData(l, r, lt, -1, -1, -1)

        quickSort3Ways(l, lt - 1)
        quickSort3Ways(gt, r)
    }


    private val setData = {
        l: Int, r: Int, fixedPivot: Int, curPivot: Int, curL: Int, curR: Int ->
        data.l = l
        data.r = r
        if(fixedPivot != -1) {
            data.fixedPivots[fixedPivot] = true
            var i = fixedPivot
            while (i < data.N() && data.get(i) == data.get(fixedPivot)){
                data.fixedPivots[i] = true
                i++
            }
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

