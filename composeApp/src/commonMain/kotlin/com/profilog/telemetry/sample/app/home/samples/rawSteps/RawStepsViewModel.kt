package com.profilog.telemetry.sample.app.home.samples.rawSteps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profilog.telemetry.records.PagingSettings
import com.profilog.telemetry.records.RecordsClient
import com.profilog.telemetry.records.create
import com.profilog.telemetry.records.raw.TimeRangeFilter
import com.profilog.telemetry.records.readRecords
import com.profilog.telemetry.records.types.StepsRecord
import cz.magicware.time.addDays
import cz.magicware.time.today
import cz.magicware.utils.paging.Paging
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock.System.now
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class RawStepsViewModel : ViewModel() {
    private val recordsClient by lazy { RecordsClient.create() }

    private var page = 1

    private val paging = PagingSettings(Paging.upTo(page, 100))

    val model = recordsClient.readRecords<StepsRecord>(
        timeRangeFilter = TimeRangeFilter.between(today(), today().addDays(1)),
        desc = true,
        paging = paging
    ).pagination.map { pagination ->
        Steps(
            items = pagination.pages.map { it.items }.flatten(),
            hasMore = pagination.hasMore
        )
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5.seconds), Steps()
    )

    fun nextPage() = viewModelScope.launch {
        page++
        paging.update(Paging.upTo(page, 100))
    }

    fun addSteps(steps: Long, onDone: () -> Unit) = viewModelScope.launch {
        recordsClient.save(
            listOf(
                StepsRecord(startTime = now() - 5.minutes, endTime = now(), count = steps)
            )
        )
        onDone()
    }
}