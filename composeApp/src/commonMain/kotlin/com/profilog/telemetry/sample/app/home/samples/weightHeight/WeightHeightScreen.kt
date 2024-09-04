package com.profilog.telemetry.sample.app.home.samples.weightHeight

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import com.profilog.telemetry.records.types.InstantaneousRecord
import com.profilog.telemetry.sample.app.SampleScreen
import cz.magicware.time.toLocalDateTime

enum class Dialog {
    Weight,
    Height
}

@Composable
fun WeightHeightScreen(
    model: WeighHeightViewModel = viewModel { WeighHeightViewModel() },
    onBackClick: () -> Unit
) {
    val data by model.model.collectAsStateWithLifecycle()

    var dialog: Dialog? by remember {
        mutableStateOf(null)
    }

    dialog?.also {
        var value by remember {
            mutableStateOf("")
        }
        AlertDialog(
            onDismissRequest = { dialog = null },
            title = {
                Text(dialog?.name ?: "")
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
                        when (dialog) {
                            Dialog.Weight -> model.addWeight(value.toIntOrNull() ?: 100) {
                                dialog = null
                            }

                            Dialog.Height -> model.addHeight(value.toIntOrNull() ?: 100) {
                                dialog = null
                            }

                            else -> {
                                dialog = null
                            }
                        }
                    },
                    enabled = (value.toIntOrNull() ?: 0) != 0
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { dialog = null }
                ) {
                    Text("Cancel")
                }
            }
        )
    }

    SampleScreen(
        title = "Weight + Height",
        onBackClick = onBackClick
    ) {
        Row(
            modifier = Modifier.padding(it)
        ) {
            DataColumn(
                title = "Weight",
                items = data?.weights ?: emptyList(),
                onAdd = { dialog = Dialog.Weight }
            ) {
                "${weight.inKilograms} kg"
            }

            DataColumn(
                title = "Height",
                items = data?.heights ?: emptyList(),
                onAdd = { dialog = Dialog.Height }
            ) {
                "${height.inCentimeters} cm"
            }
        }
    }
}

@Composable
private inline fun <T : InstantaneousRecord> RowScope.DataColumn(
    title: String,
    items: List<T>,
    noinline onAdd: () -> Unit,
    crossinline value: T.() -> String
) {
    LazyColumn(
        modifier = Modifier.weight(1f).fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(title, style = MaterialTheme.typography.titleLarge)
                FilledTonalIconButton(onClick = onAdd) {
                    Icon(Icons.Default.Add, null)
                }
            }
        }
        items(items) {
            Column {
                Text(it.time.toLocalDateTime().toString())
                Text(it.value())
            }
        }
    }
}