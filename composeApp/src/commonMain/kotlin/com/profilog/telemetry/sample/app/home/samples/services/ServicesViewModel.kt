package com.profilog.telemetry.sample.app.home.samples.services

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profilog.telemetry.records.services.ExternalServiceRepository
import com.profilog.telemetry.records.services.ExternalServiceType
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class ServicesViewModel : ViewModel() {
    private val externalServiceRepository by lazy { ExternalServiceRepository.create() }

    val model = externalServiceRepository.services().map {
        ServicesModel(
            services = it
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5.seconds),
        initialValue = ServicesModel()
    )

    fun connect(externalServiceType: ExternalServiceType) = viewModelScope.launch {
        externalServiceRepository.connect(externalServiceType)
    }

    fun disconnect(externalServiceType: ExternalServiceType) = viewModelScope.launch {
        externalServiceRepository.disconnect(externalServiceType)
    }
}