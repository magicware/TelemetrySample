package com.profilog.telemetry.sample.app.home.samples.devices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profilog.telemetry.devices.BondedDevice
import com.profilog.telemetry.devices.Connection
import com.profilog.telemetry.devices.DeviceManager
import com.profilog.telemetry.devices.NearbyDevicesResult
import com.profilog.telemetry.records.RecordsClient
import com.profilog.telemetry.records.create
import com.profilog.telemetry.records.readRecordsBeforeDate
import com.profilog.telemetry.records.types.BodyTemperatureRecord
import com.profilog.telemetry.records.types.HeartRateRecord
import com.profilog.telemetry.records.types.OxygenSaturationRecord
import com.profilog.telemetry.records.types.Record
import cz.magicware.time.toLocalDateTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock.System.now
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlin.time.Duration.Companion.INFINITE
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class LastRecord(
    val time: LocalDateTime,
    val measuredAt: Instant,
    val name: String,
    val value: Double,
    val unit: String
)

class DevicesViewModel : ViewModel() {
    private val deviceManager by lazy {
        DeviceManager.create()
    }
    private val permissions = MutableStateFlow(false)
    private val recordsClient by lazy { RecordsClient.create() }

    private inline fun <reified T : Record> lastRecordByType(
        crossinline transform: (T) -> LastRecord
    ) = recordsClient.readRecordsBeforeDate<T>(date = now() + 10.minutes, count = 10).map {
        it.map { r -> transform(r) }
    }

    private fun lastRecords() = combine(
        lastRecordByType<HeartRateRecord> {
            LastRecord(
                time = it.time.toLocalDateTime(),
                measuredAt = it.metadata.recordedAt ?: it.time,
                name = "Heart rate",
                value = it.beatsPerMinute.toDouble(),
                unit = "bpm"
            )
        },
        lastRecordByType<OxygenSaturationRecord> {
            LastRecord(
                time = it.time.toLocalDateTime(),
                measuredAt = it.metadata.recordedAt ?: it.time,
                name = "Oxygen saturation",
                value = it.percentage.value,
                unit = "%"
            )
        },
        lastRecordByType<BodyTemperatureRecord> {
            LastRecord(
                time = it.time.toLocalDateTime(),
                measuredAt = it.metadata.recordedAt ?: it.time,
                name = "Body temperature",
                value = it.temperature.inCelsius,
                unit = "°C"
            )
        }

    ) { array ->
        array.flatMap { it }.sortedByDescending { it.measuredAt }.take(10)
    }

    private fun autoConnection(): Flow<Map<String, Connection<*, *, *, *>>> = channelFlow {
        launch {
            val ongoing = mutableSetOf<String>()

            deviceManager.nearbyDevices(
                INFINITE
            ).filterIsInstance<NearbyDevicesResult.Discovered>().map {
                it.devices.mapNotNull { d -> d as? BondedDevice<*, *> }
            }.collect { devices ->
                devices.forEach { device ->
                    if (ongoing.add(device.uid)) {
                        launch {
                            device.connect().collect {
                                send(Pair(device, it))
                            }
                        }.invokeOnCompletion {
                            ongoing.remove(device.uid)
                        }
                    }
                }
            }
        }
    }.scan(mutableMapOf()) { acc, pair ->
        acc[pair.first.uid] = pair.second
        acc
    }

    val model = combine(
        deviceManager.bondedDevices(),
        autoConnection(),
        permissions,
        lastRecords()
    ) { myDevices, connections, permissions, lastRecords ->

        DevicesModel(
            devices = myDevices.map {
                MyDevice(
                    device = it,
                    ongoingConnection = connections[it.uid]
                )
            },
            permissionsAllowed = permissions,
            lastRecords = lastRecords
        )
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5.seconds), DevicesModel()
    )

    fun permissionsGranted(granted: Boolean) = viewModelScope.launch {
        permissions.emit(granted)
    }

    fun deleteDevice(device: BondedDevice<*, *>) = viewModelScope.launch {
        deviceManager.delete(device)
    }
}