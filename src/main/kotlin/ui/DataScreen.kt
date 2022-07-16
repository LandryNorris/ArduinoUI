package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import components.DataLogic

@Composable
fun DataScreen(logic: DataLogic) {
    val state by logic.state.collectAsState()
    LaunchedEffect(Unit) {
        logic.initialize()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
            val vsList = state.dataPackets.map { it.solarVoltage }
            val vbList = state.dataPackets.map { it.batteryVoltage }
            val vmList = state.dataPackets.map { it.motorVoltage }
            LineGraph(Modifier.weight(1f), "Solar Voltage", vsList)
            LineGraph(Modifier.weight(1f), "Battery Voltage", vbList)
            LineGraph(Modifier.weight(1f), "Motor Voltage", vmList)
        }

        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
            val isList = state.dataPackets.map { it.solarCurrent }
            val ibList = state.dataPackets.map { it.batteryCurrent }
            val imList = state.dataPackets.map { it.motorCurrent }
            LineGraph(Modifier.weight(1f), "Solar Current", isList)
            LineGraph(Modifier.weight(1f), "Battery Current", ibList)
            LineGraph(Modifier.weight(1f), "Motor Current", imList)
        }
    }
}
