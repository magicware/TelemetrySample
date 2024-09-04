package com.profilog.telemetry.sample.app.home.samples.weightHeight

import com.profilog.telemetry.records.types.HeightRecord
import com.profilog.telemetry.records.types.WeightRecord

data class WeightHeightModel(
    val weights: List<WeightRecord> = emptyList(),
    val heights: List<HeightRecord> = emptyList()
)