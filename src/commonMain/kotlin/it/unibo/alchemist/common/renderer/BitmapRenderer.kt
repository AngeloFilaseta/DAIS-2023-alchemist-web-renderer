package it.unibo.alchemist.common.renderer

import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.bitmap.Bitmap32
import com.soywiz.korim.bitmap.context2d
import com.soywiz.korim.color.Colors
import com.soywiz.korim.paint.Paint
import com.soywiz.korim.vector.Context2d
import com.soywiz.korma.geom.Point
import com.soywiz.korma.geom.vector.circle
import it.unibo.alchemist.common.model.surrogate.EnvironmentSurrogate
import it.unibo.alchemist.common.model.surrogate.NodeSurrogate
import it.unibo.alchemist.common.model.surrogate.PositionSurrogate

class BitmapRenderer<in TS : Any, in PS : PositionSurrogate> : Renderer<TS, PS, Bitmap> {

    companion object {
        private const val defaultNodeRadius = 0.1
        private const val defaultHeight = 400
        private const val defaultWidth = 400
        private const val defaultScaleFactor = 35
    }

    override fun render(environmentSurrogate: EnvironmentSurrogate<TS, PS>): Bitmap {
        require(environmentSurrogate.dimensions == 2)
        return Bitmap32(defaultWidth, defaultHeight, premultiplied = false).context2d {
            cartesianPlane()
            translate(width / 2.0, height / 2.0)
            scale(defaultScaleFactor, defaultScaleFactor)
            environmentSurrogate.nodes.forEach {
                alchemistNode(it, defaultNodeRadius)
            }
        }
    }

    private fun Context2d.cartesianPlane() {
        fill(Colors.WHITE)
        beginPath()
        moveTo((width / 2).toDouble(), 0.0)
        lineTo((width / 2).toDouble(), height.toDouble())
        moveTo(0.0, (height / 2).toDouble())
        lineTo(width.toDouble(), (height / 2).toDouble())
        stroke()
    }

    private fun Context2d.alchemistNode(node: NodeSurrogate<TS, PS>, radius: Double) {
        beginPath()
        circle(node.position.toPoint(), radius)
        fill(color(node))
    }

    private fun PositionSurrogate.toPoint(): Point {
        require(dimensions == 2)
        return Point(coordinates[0], coordinates[1])
    }
}

fun <TS : Any, PS : PositionSurrogate> color(node: NodeSurrogate<TS, PS>): Paint = if (node.contents.keys.size == 1) {
    when (node.contents.keys.first().name.filter { it.isDigit() }.toInt()) {
        in 0..5 -> Colors.DARKGREEN
        in 6..10 -> Colors.GREEN
        in 11..15 -> Colors.GREENYELLOW
        in 16..20 -> Colors.YELLOWGREEN
        in 21..30 -> Colors.YELLOW
        in 31..40 -> Colors.ORANGE
        in 41..50 -> Colors.ORANGERED
        in 51..60 -> Colors.RED
        else -> Colors.DARKRED
    }
} else {
    Colors.BLACK
}
