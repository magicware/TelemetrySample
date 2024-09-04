package com.profilog.telemetry.sample.app.home.samples.steps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.profilog.telemetry.sample.app.SampleScreen

@Composable
fun StepsScreen(
    model: StepsViewModel = viewModel { StepsViewModel() },
    onBackClick: () -> Unit
) {
    val steps by model.model.collectAsStateWithLifecycle()

    SampleScreen("Steps", onBackClick) {
        LazyColumn(
            modifier = Modifier.consumeWindowInsets(it),
            contentPadding = it,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(steps) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(it.date.toString())
                    Text(it.count.toString())
                }
            }
        }
    }
}