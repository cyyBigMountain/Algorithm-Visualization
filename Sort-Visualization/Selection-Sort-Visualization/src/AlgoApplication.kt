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
    val title: String = "Selection Sort Visualization"

    // 画布大小设置
    private val canvasWidth: Int = sceneWidth
    private val canvasHeight: Int = sceneHeight

    // 画布器件初始化
    private val canvas: Canvas = Canvas(canvasWidth.toDouble(), canvasHeight.toDouble())
    private val gc: GraphicsContext = canvas.graphicsContext2D
    private val root: Pane = Pane(canvas)

    init {
        AlgoVisualizer(this)
    }

    private lateinit var data: SelectionSortData
    val render =  { data: SelectionSortData ->
        this.data = data
        gc.clearRect(0.0, 0.0, canvasWidth.toDouble(), canvasHeight.toDouble())
        paint()
    }

    private val paint = {
        // 具体绘制
        val w = canvasWidth / data.N()
        for (i in 0 until data.N()) {
            if (i < data.orderedIndex) {
                AlgoVisHelper.setFillColor(gc, AlgoVisHelper.RED)
            }else {
                AlgoVisHelper.setFillColor(gc, AlgoVisHelper.GREY)
            }
            if (i == data.currentCompareIndex) {
                AlgoVisHelper.setFillColor(gc, AlgoVisHelper.LIGHT_BLUE)
            }
            if (i == data.currentMinIndex) {
                AlgoVisHelper.setFillColor(gc, AlgoVisHelper.INDIGO)
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