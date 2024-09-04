package com.profilog.telemetry.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profilog.telemetry.config.ConfigurationRepository
import cz.magicware.uri.toUri
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class AppViewModel : ViewModel() {

    private val configurationRepository by lazy {
        ConfigurationRepository.create()
    }

    init {
        viewModelScope.launch {
            configurationRepository.setup(
                project = PROJECT_CODE.ifBlank { throw IllegalArgumentException() },
                uri = PROJECT_URI.ifBlank { throw IllegalArgumentException() }.toUri(),
                secret = PROJECT_SECRET.ifBlank { throw IllegalArgumentException() }
            )
        }
    }

    val user = configurationRepository.configuration().map {
        it.userName
    }.distinctUntilChanged().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5.seconds),
        null
    )

    companion object {
        const val PROJECT_CODE = ""
        const val PROJECT_URI = ""
        const val PROJECT_SECRET = ""
    }
}