package com.profilog.telemetry.sample.app.home.samples.sleep

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profilog.telemetry.records.RecordsClient
import com.profilog.telemetry.records.aggregations.TimeRangeFilter
import com.profilog.telemetry.records.aggregations.TimeRangeSlicer
import com.profilog.telemetry.records.create
import com.profilog.telemetry.records.readRecordsBeforeDate
import com.profilog.telemetry.records.types.HeartRateRecord
import com.profilog.telemetry.records.types.SleepRecord
import cz.magicware.time.addDays
import cz.magicware.time.toInstant
import cz.magicware.time.toLocalDateTime
import cz.magicware.time.today
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.atTime
import kotlin.time.Duration.Companion.seconds

class SleepViewModel : ViewModel() {
    private val recordsClient by lazy { RecordsClient.create() }

    private fun lastSleeps() = recordsClient.readRecordsBeforeDate<SleepRecord>(
        date = today().addDays(1),
        count = 10
    )

    private fun sleepDuration() = recordsClient.aggregate(
        metrics = setOf(
            SleepRecord.OVERALL_TOTAL,
            SleepRecord.DEEP_TOTAL,
            HeartRateRecord.BPM_AVG
        ),
        timeRangeFilter = TimeRangeFilter.between(
            today().addDays(-7).toLocalDateTime().date.atTime(22, 0).toInstant(),
            today().addDays(1).toLocalDateTime().date.atTime(22, 0).toInstant()
        ),
        timeRangeSlicer = TimeRangeSlicer.ofDays()
    )

    val model = combine(
        lastSleeps(),
        sleepDuration()
    ) { lastSleeps, sleepDuration ->
        SleepModel(
            lastSleeps = lastSleeps,
            aggregations = sleepDuration
        )
    }.distinctUntilChanged().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5.seconds), SleepModel()
    )
}