package models

import kotlinx.serialization.Serializable

@Serializable
data class DataPacket(val solarVoltage: Double = 0.0,
                      val solarCurrent: Double = 0.0,
                      val batteryVoltage: Double = 0.0,
                      val batteryCurrent: Double = 0.0,
                      val motorVoltage: Double = 0.0,
                      val motorCurrent: Double = 0.0)
