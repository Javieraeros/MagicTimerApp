package es.fjruiz.magictimer.ui.component.card

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp

/**
 * Draws a rounded rectangle border that gets consumed clockwise starting from the top center,
 * based on [progress] (1f = full border, 0f = fully consumed).
 */
fun Modifier.depletingBorder(
    progress: Float,
    color: Color,
    trackColor: Color,
    cornerRadius: Dp,
    strokeWidth: Dp
): Modifier = drawWithContent {
    drawContent()

    val strokePx = strokeWidth.toPx()
    val inset = strokePx / 2f
    val left = inset
    val top = inset
    val right = size.width - inset
    val bottom = size.height - inset

    if (right <= left || bottom <= top) return@drawWithContent

    val radius = cornerRadius.toPx()
        .coerceAtMost(minOf(right - left, bottom - top) / 2f)
        .coerceAtLeast(0f)
    val centerX = (left + right) / 2f

    val path = Path().apply {
        // Start at the top center and walk the rounded rectangle clockwise.
        moveTo(centerX, top)
        lineTo(right - radius, top)
        arcTo(Rect(right - 2 * radius, top, right, top + 2 * radius), -90f, 90f, false)
        lineTo(right, bottom - radius)
        arcTo(Rect(right - 2 * radius, bottom - 2 * radius, right, bottom), 0f, 90f, false)
        lineTo(left + radius, bottom)
        arcTo(Rect(left, bottom - 2 * radius, left + 2 * radius, bottom), 90f, 90f, false)
        lineTo(left, top + radius)
        arcTo(Rect(left, top, left + 2 * radius, top + 2 * radius), 180f, 90f, false)
        lineTo(centerX, top)
        close()
    }

    val stroke = Stroke(width = strokePx)
    val clampedProgress = progress.coerceIn(0f, 1f)

    if (trackColor != Color.Transparent) {
        drawPath(path, trackColor, style = stroke)
    }

    if (clampedProgress <= 0f) return@drawWithContent
    if (clampedProgress >= 1f || color == Color.Transparent) {
        if (color != Color.Transparent) drawPath(path, color, style = stroke)
        return@drawWithContent
    }

    val measure = PathMeasure().apply { setPath(path, false) }
    val segment = Path()
    val length = measure.length
    // The consumed part advances clockwise from the top center, so the remaining
    // border keeps its end anchored at the top center and grows its gap clockwise.
    measure.getSegment(length * (1f - clampedProgress), length, segment, true)
    drawPath(segment, color, style = stroke)
}
