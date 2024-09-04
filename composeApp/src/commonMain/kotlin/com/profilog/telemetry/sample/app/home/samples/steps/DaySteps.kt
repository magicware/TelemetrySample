package com.profilog.telemetry.sample.app.home.samples.steps

import kotlinx.datetime.LocalDate

data class DaySteps(
    val date: LocalDate,
    val count: Long
)