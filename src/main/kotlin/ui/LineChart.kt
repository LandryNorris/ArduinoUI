package ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun LineGraph(modifier: Modifier = Modifier, title: String, values: List<Double>, min: Double = 0.0, max: Double = 100.0) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(title)
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            val coordinates = values.mapIndexed { index, value ->
                val x = map(index.toDouble(), 0.0, values.size.toDouble(), 0.0, w.toDouble())
                val y = map(value, min, max, h.toDouble(), 0.0)

                x to y
            }

            coordinates.windowed(2) {
                val first = it.first()
                val second = it.last()

                drawLine(Color.Black, Offset(first.first.toFloat(), first.second.toFloat()),
                    Offset(second.first.toFloat(), second.second.toFloat()))
            }
        }
    }
}

fun map(value: Double, minIn: Double, maxIn: Double, minOut: Double, maxOut: Double): Double {
    return (value - minIn) * (maxOut - minOut) / (maxIn - minIn) + minOut
}
