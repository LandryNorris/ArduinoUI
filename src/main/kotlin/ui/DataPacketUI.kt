package ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import models.DataPacket
import java.lang.Math.cos
import java.lang.Math.sin

@Composable
fun DataPacketUI(packet: DataPacket) {
    Column {
        Row {
            Gauge(packet.solarVoltage.toFloat()/100f)
            Gauge(packet.batteryVoltage.toFloat()/100f)
            Gauge(packet.motorVoltage.toFloat()/100f)
        }

        Row {
            Gauge(packet.solarCurrent.toFloat()/100f)
            Gauge(packet.batteryCurrent.toFloat()/100f)
            Gauge(packet.motorCurrent.toFloat()/100f)
        }
    }
}

@Composable
fun Gauge(progress: Float) {
    Canvas(modifier = Modifier.size(100.dp).background(Color.White)) {
        drawArc(color = Color.Black, 0f, -180f, false,
            size = size, style = Stroke(2f))

        val theta = Math.PI - progress*Math.PI
        val cx = size.width/2
        val cy = size.height/2

        val radius = size.width/2

        val endX = cx + radius*cos(theta).toFloat()
        val endY = cy - radius*sin(theta).toFloat()
        drawLine(color = Color.Black, start = Offset(cx, cy), end = Offset(endX, endY))
    }
}

@Preview
@Composable
fun DataPacketPreview() {
    DataPacketUI(DataPacket())
}

@Preview
@Composable
fun GaugePreview() {
    Row {
        Spacer(Modifier.width(10.dp))
        Gauge(0.5f)
        Spacer(Modifier.width(10.dp))
        Gauge(0.25f)
        Spacer(Modifier.width(10.dp))
        Gauge(0.9f)
        Spacer(Modifier.width(10.dp))
        Gauge(0.0f)
        Spacer(Modifier.width(10.dp))
        Gauge(1.0f)
        Spacer(Modifier.width(10.dp))
    }
}
