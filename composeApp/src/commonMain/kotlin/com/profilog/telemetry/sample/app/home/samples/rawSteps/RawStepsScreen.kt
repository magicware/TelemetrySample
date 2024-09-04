package com.profilog.telemetry.sample.app.home.samples.rawSteps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.profilog.telemetry.sample.app.SampleScreen
import cz.magicware.time.toLocalDateTime

@Composable
fun RawStepsScreen(
    model: RawStepsViewModel = viewModel { RawStepsViewModel() },
    onBackClick: () -> Unit
) {
    val data by model.model.collectAsStateWithLifecycle()
    var dialogVisible by remember {
        mutableStateOf(false)
    }

    SampleScreen(
        title = "Raw steps",
        actions = {
            Button(
                onClick = { dialogVisible = true }
            ) {
                Icon(Icons.Default.Add, null)
                Text("Add Steps")
            }
        },
        onBackClick = onBackClick
    ) {
        LazyColumn(
            modifier = Modifier.consumeWindowInsets(it),
            contentPadding = it,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(data.items) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(it.startTime.toLocalDateTime().toString())
                        Text(
                            it.endTime.toLocalDateTime().toString()
                        )
                    }

                    Text(it.count.toString())

                }
            }

            if (data.hasMore) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("Loading...")
                        LaunchedEffect(Unit) {
                            model.nextPage()
                        }
                    }
                }
            }
        }
    }

    if (dialogVisible) {
        var value by remember {
            mutableStateOf("")
        }
        AlertDialog(
            onDismissRequest = { dialogVisible = false },
            title = {
                Text("Steps adding")
            },
            text = {
                TextField(
                    value = value,
                    onValueChange = { value = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        model.addSteps(value.toLongOrNull() ?: 100) {
                            dialogVisible = false
                        }
                    },
                    enabled = (value.toLongOrNull() ?: 0L) != 0L
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { dialogVisible = false }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}