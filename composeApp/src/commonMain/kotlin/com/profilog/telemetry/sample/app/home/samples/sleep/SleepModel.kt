package com.profilog.telemetry.sample.app.home.samples.sleep

import com.profilog.telemetry.records.aggregations.AggregationResult
import com.profilog.telemetry.records.types.SleepRecord

data class SleepModel(
    val lastSleeps: List<SleepRecord> = emptyList(),
    val aggregations: List<AggregationResult> = emptyList()
)