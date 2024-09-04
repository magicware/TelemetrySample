package com.profilog.telemetry.sample.app.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profilog.telemetry.config.ConfigurationRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class HomeScreenViewModel : ViewModel() {

    data class User(
        val name: String,
        val isLogged: Boolean
    )

    private val configurationRepository by lazy { ConfigurationRepository.create() }

    val user = configurationRepository.configuration().map {
        User(
            name = it.userName,
            isLogged = it.isLogged
        )
    }.distinctUntilChanged().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5.seconds), null
    )

    fun logout() = viewModelScope.launch {
        configurationRepository.logout()
    }
}