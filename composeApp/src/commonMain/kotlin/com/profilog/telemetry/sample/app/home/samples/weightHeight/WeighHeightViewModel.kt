package com.profilog.telemetry.sample.app.home.samples.weightHeight

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profilog.telemetry.records.RecordsClient
import com.profilog.telemetry.records.create
import com.profilog.telemetry.records.readRecordsBeforeDate
import com.profilog.telemetry.records.types.HeightRecord
import com.profilog.telemetry.records.types.Record
import com.profilog.telemetry.records.types.WeightRecord
import com.profilog.telemetry.records.units.centimeters
import com.profilog.telemetry.records.units.kilograms
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock.System.now
import kotlinx.datetime.Instant
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class WeighHeightViewModel : ViewModel() {
    private val recordsClient by lazy { RecordsClient.create() }

    private inline fun <reified T : Record> lastRecords(date: Instant) =
        recordsClient.readRecordsBeforeDate<T>(
            date = date,
            count = 10
        )

    private val timeFlow = flow {
        while (true) {
            emit(now() + 10.minutes)
            delay(10.minutes)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val model = timeFlow.flatMapLatest {
        combine(
            lastRecords<WeightRecord>(it),
            lastRecords<HeightRecord>(it)
        ) { weightRecords, heightRecords ->
            WeightHeightModel(
                weights = weightRecords,
                heights = heightRecords
            )
        }
    }.distinctUntilChanged().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5.seconds),
        null
    )

    private suspend fun Record.save() = recordsClient.save(listOf(this))

    fun addWeight(weight: Int, onDone: () -> Unit) = viewModelScope.launch {
        WeightRecord(time = now(), weight = weight.kilograms).save()
        onDone()
    }

    fun addHeight(height: Int, onDone: () -> Unit) = viewModelScope.launch {
        HeightRecord(time = now(), height = height.toDouble().centimeters).save()
        onDone()
    }
}