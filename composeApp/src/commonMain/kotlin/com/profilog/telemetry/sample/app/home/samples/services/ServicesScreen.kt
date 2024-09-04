package com.profilog.telemetry.sample.app.home.samples.services

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.profilog.telemetry.records.services.connected
import com.profilog.telemetry.sample.app.SampleScreen

@Composable
fun ServicesScreen(
    model: ServicesViewModel = viewModel { ServicesViewModel() },
    onBackClick: () -> Unit
) {

    val services by model.model.collectAsStateWithLifecycle()

    SampleScreen("Services", onBackClick) { padding ->
        LazyColumn(
            modifier = Modifier.consumeWindowInsets(padding),
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(services.services) { service ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(service.type.identifier)
                    Switch(
                        checked = service.connected,
                        onCheckedChange = {
                            if (it) model.connect(service.type)
                            else model.disconnect(service.type)
                        }
                    )
                }
            }
        }
    }
}