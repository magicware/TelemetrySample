package com.profilog.telemetry.sample.app.home.samples.devices

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.profilog.telemetry.devices.Connection
import com.profilog.telemetry.devices.terminating
import com.profilog.telemetry.sample.app.SampleScreen
import cz.magicware.permissions.BluetoothPermission
import cz.magicware.permissions.CollectGranted
import cz.magicware.permissions.State
import cz.magicware.permissions.rememberPermissionState

@Composable
fun DevicesScreen(
    model: DevicesViewModel = viewModel { DevicesViewModel() },
    onAddDeviceClick: () -> Unit,
    onBackClick: () -> Unit
) {

    val myDevices by model.model.collectAsStateWithLifecycle()

    SampleScreen(
        title = "Devices",
        onBackClick = onBackClick,
        floatingActionButton = {
            AnimatedVisibility(myDevices.permissionsAllowed) {
                ExtendedFloatingActionButton(onClick = onAddDeviceClick) {
                    Icon(Icons.Default.Add, null)
                    Text("Add device")
                }
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp),
            contentPadding = it,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Permissions(model)
            }

            item {
                Text("My devices", style = MaterialTheme.typography.titleLarge)
            }

            if (myDevices.devices.isEmpty()) {
                item { Text("No devices") }
            }

            items(myDevices.devices) { device ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(16.dp).animateContentSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("${device.device.family} ${device.device.displayName}")

                            AnimatedVisibility(device.ongoingConnection == null || device.ongoingConnection.terminating) {
                                FilledTonalIconButton(onClick = {
                                    model.deleteDevice(device.device)
                                }) {
                                    Icon(Icons.Default.DeleteForever, null)
                                }
                            }
                        }

                        AnimatedVisibility(device.ongoingConnection != null) {
                            CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.bodySmall) {
                                device.ongoingConnection?.also { connection ->
                                    when (connection) {
                                        is Connection.Searching -> Text("Searching...")
                                        is Connection.Connecting -> Text("Connecting...")
                                        is Connection.Connected -> Text("Connected")
                                        is Connection.Disconnecting -> Text("Disconnecting...")
                                        is Connection.Disconnected -> Text("Disconnected")
                                        is Connection.Progress -> Text("Progress: ${connection.progress}")
                                        is Connection.DataLoaded -> Text("Reading data...")
                                        is Connection.Properties -> Text("Properties received ${connection.properties}")
                                        is Connection.BluetoothInvalidState -> Text("Bluetooth invalid state")
                                        is Connection.ConnectError -> Text("Connect error")
                                        is Connection.Timeout -> Text("Timeout")
                                        is Connection.ConnectionConfiguration -> Text("Connection configuration is required ${connection.configuration}")
                                        is Connection.WaitingForResources -> Text("Waiting for system resources")
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (myDevices.lastRecords.isNotEmpty()) item {
                Text("Last records", style = MaterialTheme.typography.titleLarge)
            }

            items(myDevices.lastRecords) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(it.name)
                        Text(it.time.toString(), style = MaterialTheme.typography.bodySmall)
                    }

                    Text("${it.value} ${it.unit}")
                }
            }
        }
    }
}

@Composable
fun Permissions(model: DevicesViewModel) {
    val state = rememberPermissionState(BluetoothPermission)

    state.CollectGranted {
        model.permissionsGranted(it)
    }

    AnimatedVisibility(!state.granted && state.state != State.NOT_DETERMINED) {
        Button(
            onClick = {
                state.launchPermissionRequest()
            }
        ) {
            Text("Grant permissions")
        }
    }
}
