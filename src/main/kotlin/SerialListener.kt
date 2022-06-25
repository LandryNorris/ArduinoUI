import com.fazecast.jSerialComm.SerialPort
import com.fazecast.jSerialComm.SerialPortDataListener
import com.fazecast.jSerialComm.SerialPortEvent

class SerialListener: SerialPortDataListener {
    private val stringBuilder = StringBuilder()
    override fun getListeningEvents(): Int {
        return SerialPort.LISTENING_EVENT_DATA_AVAILABLE or SerialPort.LISTENING_EVENT_DATA_RECEIVED
    }

    override fun serialEvent(event: SerialPortEvent?) {
        val bytes = event?.receivedData ?: return
        val text = String(bytes)
        stringBuilder.append(text)

        if(stringBuilder.contains('\n')) {
            val line = stringBuilder.toString()
            stringBuilder.clear()
            println(line)
        }
    }

}
