package com.profilog.telemetry.sample.app.home.samples.devices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profilog.telemetry.devices.BondedDevice
import com.profilog.telemetry.devices.Device
import com.profilog.telemetry.devices.DeviceManager
import com.profilog.telemetry.devices.NearbyDevicesResult
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class AddDeviceViewModel : ViewModel() {

    private val deviceManager by lazy { DeviceManager.create() }

    private fun nearbyDevices() = deviceManager.nearbyDevices(
        scanDuration = Duration.INFINITE
    ).filterIsInstance<NearbyDevicesResult.Discovered>().map {
        it.devices.filterNot { d -> d is BondedDevice<*, *> }
    }

    val model = combine(
        nearbyDevices(),
        deviceManager.supportedDevices()
    ) { nearby, supported ->
        AddDeviceModel(
            nearbyDevices = nearby,
            supportedDevices = supported
        )
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5.seconds), AddDeviceModel()
    )

    fun connect(
        device: Device<*, *, *, *>,
        onDone: () -> Unit
    ) = viewModelScope.launch {
        device.connect().onCompletion {
            onDone()
        }.collect()
    }
}