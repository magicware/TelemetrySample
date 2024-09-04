package com.profilog.telemetry.sample.app.home.samples.devices

import com.profilog.telemetry.devices.Device

data class AddDeviceModel(
    val supportedDevices: List<Device<*,*,*,*>> = emptyList(),
    val nearbyDevices: List<Device<*,*,*,*>> = emptyList()
)