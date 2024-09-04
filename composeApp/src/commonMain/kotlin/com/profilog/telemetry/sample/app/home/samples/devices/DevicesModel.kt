package com.profilog.telemetry.sample.app.home.samples.devices

import com.profilog.telemetry.devices.BondedDevice
import com.profilog.telemetry.devices.Connection

data class MyDevice(
    val device: BondedDevice<*,*>,
    val ongoingConnection: Connection<*, *, *, *>?
)

data class DevicesModel(
    val devices: List<MyDevice> = emptyList(),
    val permissionsAllowed: Boolean = false,
    val lastRecords: List<LastRecord> = emptyList()
)