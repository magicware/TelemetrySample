package com.profilog.telemetry.sample.app.home.samples.sleep

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.profilog.telemetry.records.types.HeartRateRecord
import com.profilog.telemetry.records.types.SleepRecord
import com.profilog.telemetry.sample.app.SampleScreen
import cz.magicware.time.toLocalDateTime

@Composable
fun SleepScreen(
    model: SleepViewModel = viewModel { SleepViewModel() },
    onBackClick: () -> Unit
) {
    val data by model.model.collectAsStateWithLifecycle()

    SampleScreen("Sleep", onBackClick) {
        LazyColumn(
            modifier = Modifier.consumeWindowInsets(it),
            contentPadding = it,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text( modifier = Modifier.padding(horizontal = 16.dp), text = "Last sleeps")
            }
            items(data.lastSleeps) {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("${it.startTime.toLocalDateTime()} - ${it.endTime.toLocalDateTime()}")
                        Text("sources: ${it.metadata.source.nodes.joinToString { it.provider }}")
                        Text("sleep stages count: ${it.stages.size}")
                    }
                }
            }

            item {
                Text(modifier = Modifier.padding(horizontal = 16.dp), text = "Summary")
            }

            items(data.aggregations) {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("${it.start.toLocalDateTime()} - ${it.end.toLocalDateTime()}")
                        it[SleepRecord.OVERALL_TOTAL]?.also {
                            Text("total: ${it.value}")
                        }

                        it[HeartRateRecord.BPM_AVG]?.also {
                            Text("bpm avg: ${it.value}")
                        }
                    }
                }
            }
        }
    }
}