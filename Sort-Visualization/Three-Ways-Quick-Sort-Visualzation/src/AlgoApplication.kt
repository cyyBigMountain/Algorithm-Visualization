import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.layout.Pane
import javafx.stage.Stage
import kotlin.system.exitProcess

class AlgoApplication: Application() {
    // 桌面窗口大小和标题设置
    val sceneWidth: Int = 800
    val sceneHeight: Int = 800
    val title: String = "ThreeWayQuick Sort Visualization"

    // 画布大小设置
    private val canvasWidth: Int = sceneWidth
    private val canvasHeight: Int = sceneHeight

    // 画布器件初始化
    private val canvas: Canvas = Canvas(canvasWidth.toDouble(), canvasHeight.toDouble())
    private val gc: GraphicsContext = canvas.graphicsContext2D
    private val root: Pane = Pane(canvas)


    private lateinit var data: ThreeWayQuickSortData
    val render =  { dataThreeWay: ThreeWayQuickSortData ->
        this.data = dataThreeWay
        paint()
    }

    init {
        AlgoVisualizer(this, root)
    }

    private val paint = {
        // 清屏
        gc.clearRect(0.0, 0.0, canvasWidth.toDouble(), canvasHeight.toDouble())
        // 具体绘制
        val w = canvasWidth / data.N()
        for (i in 0 until data.N()) {
            if (i >= data.l && i <= data.r) {
                AlgoVisHelper.setFillColor(gc, AlgoVisHelper.GREEN)
            } else {
                AlgoVisHelper.setFillColor(gc, AlgoVisHelper.GREY)
            }
            if (i == data.curPivot) {
                AlgoVisHelper.setFillColor(gc, AlgoVisHelper.INDIGO)
            }
            if (i >= data.l + 1 && i <= data.curL) {
                AlgoVisHelper.setFillColor(gc, AlgoVisHelper.LIGHT_BLUE)
            }
            if (i >= data.curR + 1 && i <= data.r) {
                AlgoVisHelper.setFillColor(gc, AlgoVisHelper.LIGHT_BLUE)
            }
            if (data.fixedPivots[i]) {
                AlgoVisHelper.setFillColor(gc, AlgoVisHelper.RED)
            }
            AlgoVisHelper.fillRectangle(gc, i * w, canvasHeight - data.get(i), w - 1, data.get(i))
        }
    }

    override fun start(primaryStage: Stage?) {
        primaryStage!!.title = title
        primaryStage.scene = Scene(root, sceneWidth.toDouble(), sceneHeight.toDouble())
        primaryStage.setOnCloseRequest { exitProcess(0); }
        primaryStage.show()
    }
}