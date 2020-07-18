import javafx.scene.canvas.GraphicsContext
import javafx.scene.image.Image
import javafx.scene.paint.Color
import javafx.scene.shape.StrokeLineCap
import javafx.scene.shape.StrokeLineJoin


object AlgoVisHelper {
    @JvmField val RED: Color = Color.web("0xF44336")
    @JvmField val PINK: Color = Color.web("0xE91E63")
    @JvmField val PURPLE: Color = Color.web("0x9C27B0")
    @JvmField val DEEP_PURPLE: Color = Color.web("0x673AB7")
    @JvmField val INDIGO: Color = Color.web("0x3F51B5")
    @JvmField val BLUE: Color = Color.web("0x2196F3")
    @JvmField val LIGHT_BLUE: Color = Color.web("0x03A9F4")
    @JvmField val CYAN: Color = Color.web("0x00BCD4")
    @JvmField val TEAL: Color = Color.web("0x009688")
    @JvmField val GREEN: Color = Color.web("0x4CAF50")
    @JvmField val LIGHT_GREEN: Color = Color.web("0x8BC34A")
    @JvmField val LIME: Color = Color.web("0xCDDC39")
    @JvmField val YELLOW: Color = Color.web("0xFFEB3B")
    @JvmField val AMBER: Color = Color.web("0xFFC107")
    @JvmField val ORANGE: Color = Color.web("0xFF9800")
    @JvmField val DEEP_ORANGE: Color = Color.web("0xFF5722")
    @JvmField val BROWN: Color = Color.web("0x795548")
    @JvmField val GREY: Color = Color.web("0x9E9E9E")
    @JvmField val BLUE_GREY: Color = Color.web("0x607D8B")
    @JvmField val BLACK: Color = Color.web("0x000000")
    @JvmField val WHITE: Color = Color.web("0xFFFFFF")

    @JvmStatic
    fun strokeCircle(gc: GraphicsContext, x: Int, y: Int, r: Int) {
        gc.strokeOval((x - r).toDouble(), (y - r).toDouble(), (2 * r).toDouble(), (2 * r).toDouble())
    }

    @JvmStatic
    fun fillCircle(gc: GraphicsContext, x: Int, y: Int, r: Int) {
        gc.fillOval((x - r).toDouble(), (y - r).toDouble(), (2 * r).toDouble(), (2 * r).toDouble())
    }

    @JvmStatic
    fun strokeRectangle(gc: GraphicsContext, x: Int, y: Int, w: Int, h: Int) {
        gc.strokeRect(x.toDouble(), y.toDouble(), w.toDouble(), h.toDouble())
    }

    @JvmStatic
    fun fillRectangle(gc: GraphicsContext, x: Int, y: Int, w: Int, h: Int) {
        gc.fillRect(x.toDouble(), y.toDouble(), w.toDouble(), h.toDouble())
    }

    @JvmStatic
    fun setStrokeColor(gc: GraphicsContext, color: Color?) {
        gc.stroke = color
    }

    @JvmStatic
    fun setFillColor(gc: GraphicsContext, color: Color?) {
        gc.fill = color
    }

    @JvmStatic
    fun setStrokeWidth(gc: GraphicsContext, w: Int) {
        gc.lineWidth = w.toDouble()
        gc.lineCap = StrokeLineCap.ROUND
        gc.lineJoin = StrokeLineJoin.ROUND
    }

    @JvmStatic
    fun pause(t: Int) {
        Thread.sleep(t.toLong())
    }

    @JvmStatic
    fun putImage(gc: GraphicsContext, x: Int, y: Int, imageURL: String?) {
        gc.drawImage(Image(imageURL), x.toDouble(), y.toDouble())
    }

    @JvmStatic
    fun drawText(gc: GraphicsContext, text: String?, centerx: Int, centery: Int) {
        requireNotNull(text) { "Text is null in drawText function!" }
        gc.fillText(text, centerx.toDouble(), centery.toDouble())
    }
}