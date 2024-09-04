package com.profilog.telemetry.sample.app.home.samples.services

import com.profilog.telemetry.records.services.ExternalService

data class ServicesModel(
    val services: List<ExternalService> = emptyList()
)