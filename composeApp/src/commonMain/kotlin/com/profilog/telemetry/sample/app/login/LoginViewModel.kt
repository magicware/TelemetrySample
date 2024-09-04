package com.profilog.telemetry.sample.app.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profilog.telemetry.config.ConfigurationRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val configurationRepository by lazy { ConfigurationRepository.create() }

    fun login(userName: String) = viewModelScope.launch {
        configurationRepository.setUser(userName)
    }
}