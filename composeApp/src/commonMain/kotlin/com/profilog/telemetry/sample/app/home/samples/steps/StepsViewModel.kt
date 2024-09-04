package com.profilog.telemetry.sample.app.home.samples.steps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profilog.telemetry.records.RecordsClient
import com.profilog.telemetry.records.aggregations.TimeRangeFilter
import com.profilog.telemetry.records.aggregations.TimeRangeSlicer
import com.profilog.telemetry.records.create
import com.profilog.telemetry.records.types.StepsRecord
import cz.magicware.time.addDays
import cz.magicware.time.toLocalDateTime
import cz.magicware.time.today
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlin.time.Duration.Companion.seconds

class StepsViewModel : ViewModel() {
    private val recordsClient by lazy { RecordsClient.create() }

    val model = recordsClient.aggregate(
        metrics = setOf(StepsRecord.COUNT_TOTAL),
        timeRangeFilter = TimeRangeFilter.between(today().addDays(-31), today().addDays(1)),
        timeRangeSlicer = TimeRangeSlicer.ofDays()
    ).map { aggregations ->
        aggregations.mapNotNull {
            it[StepsRecord.COUNT_TOTAL]?.value?.let { steps ->
                DaySteps(
                    date = it.start.toLocalDateTime().date,
                    count = steps
                )
            }
        }.reversed()
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5.seconds), emptyList()
    )
}