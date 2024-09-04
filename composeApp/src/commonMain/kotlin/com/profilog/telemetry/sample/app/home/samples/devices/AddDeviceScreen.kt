package com.profilog.telemetry.sample.app.home.samples.devices

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLink
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.profilog.telemetry.sample.app.SampleScreen
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun AddDeviceScreen(
    model: AddDeviceViewModel = viewModel { AddDeviceViewModel() },
    onBackClick: () -> Unit
) {
    SampleScreen("Add device", onBackClick) {

        var successDialog by rememberSaveable {
            mutableStateOf(false)
        }

        val data by model.model.collectAsStateWithLifecycle()

        var connectionInProgress by rememberSaveable {
            mutableStateOf(false)
        }

        LazyColumn(
            contentPadding = it,
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Turn pairing mode on your device. Device will be shown in the list bellow automatically.")

                    Text("supported devices", style = MaterialTheme.typography.titleSmall)
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(data.supportedDevices) {
                            Card {
                                Text(
                                    modifier = Modifier.padding(4.dp),
                                    text = "${it.family} ${it.displayName}"
                                )
                            }
                        }
                    }
                }
            }

            item {
                if (connectionInProgress) {
                    Text("Connecting in progress...")
                }
            }

            items(data.nearbyDevices) {
                Card(
                    onClick = {
                        connectionInProgress = true
                        model.connect(it) { successDialog = true }
                    },
                    enabled = !connectionInProgress,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("${it.family} ${it.displayName}")
                        Icon(Icons.Default.AddLink, null)
                    }
                }
            }

        }

        fun closeDialog() {
            successDialog = false
            onBackClick()
        }

        LaunchedEffect(successDialog) {
            if (successDialog) {
                delay(5.seconds)
                closeDialog()
            }
        }

        if (successDialog) {
            AlertDialog(
                onDismissRequest = { closeDialog() },
                title = { Text("Device connected") },
                text = {
                    Icon(
                        modifier = Modifier.size(128.dp),
                        imageVector = Icons.Default.Done,
                        contentDescription = null
                    )
                },
                confirmButton = {
                    TextButton(onClick = { closeDialog() }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}