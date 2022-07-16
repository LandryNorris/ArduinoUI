package components

import Arduino
import awaitArduino
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import launch
import models.DataPacket
import org.jetbrains.skia.Data
import java.util.*
import java.util.concurrent.LinkedBlockingQueue

interface DataLogic {
    val state: StateFlow<DataState>

    fun initialize() {}
}

class DataComponent: DataLogic {
    override val state = MutableStateFlow(DataState())
    private lateinit var arduino: Arduino
    private var packetQueue = listOf<DataPacket>()

    override fun initialize() {
        launch {
            arduino = awaitArduino() ?: error("Did not get a valid arduino")
            state.update { it.copy(arduinoReady = true) }

            arduino.packetFlow.collectLatest { packet ->
                println("Got new packet")
                state.update {
                    packetQueue += packet

                    it.copy(dataPackets = packetQueue.toList())
                }
            }
        }
    }
}

data class DataState(val arduinoReady: Boolean = false, val dataPackets: List<DataPacket> = listOf())
