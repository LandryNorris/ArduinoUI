import com.fazecast.jSerialComm.SerialPort
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import models.DataPacket

class Arduino(private val port: SerialPort) {
    val packetFlow = port.packetFlow()
}

suspend fun awaitArduino(): Arduino? = withContext(Dispatchers.Default) {
    while(true) {
        val ports = SerialPort.getCommPorts()
        println(ports.joinToString(", "))
        if(ports.isEmpty()) continue

        val arduinoPort = ports.first()

        arduinoPort.openPort()

        return@withContext Arduino(arduinoPort)
    }

    return@withContext null
}

fun SerialPort.packetFlow() = callbackFlow {
    addDataListener(SerialListener { line ->
        println(line)
        if(!line.contains(", ")) return@SerialListener
        val data = line.split(", ")

        println("Processing numbers")
        val numbers = data.mapNotNull { it.toDoubleOrNull() }
        val packet = DataPacket(numbers[0], numbers[1], numbers[2], numbers[3], numbers[4], numbers[5])
        trySendBlocking(packet)
    })

    awaitClose { removeDataListener() }
}
