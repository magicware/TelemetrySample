package com.profilog.telemetry.sample.app.home.samples.rawSteps

import com.profilog.telemetry.records.types.HeartRateRecord
import com.profilog.telemetry.records.types.StepsRecord

data class Steps(
    val items: List<StepsRecord> = emptyList(),
    val hasMore: Boolean = false
)