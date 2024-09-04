package com.profilog.telemetry.sample

import com.profilog.telemetry.GrpcClientFactory
import com.profilog.telemetry.TelemetryInitializer
import com.profilog.telemetry.devices.beurer.BeurerPluginInitializer

fun initiateSdk(grpcClientFactory: GrpcClientFactory) {
    TelemetryInitializer.initialize(
        grpcClientFactory = grpcClientFactory,
        devicePluginInitializers = setOf(BeurerPluginInitializer())
    )
}