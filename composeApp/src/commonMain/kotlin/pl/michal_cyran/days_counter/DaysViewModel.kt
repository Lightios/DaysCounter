package pl.michal_cyran.days_counter

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import pl.michal_cyran.days_counter.utils.convertMillisToDate
import kotlin.math.abs
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class DaysViewModel: ViewModel() {
    @OptIn(ExperimentalTime::class)
    val today = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .date

    private val _startDate = MutableStateFlow(today)
    val startDate = _startDate.asStateFlow()

    private val _endDate = MutableStateFlow(today + DatePeriod(days = 1))
    val currentDate = _endDate.asStateFlow()

    private val _numberOfDays = MutableStateFlow(1)
    val numberOfDays = _numberOfDays.asStateFlow()

    fun setStartDate(millis: Long?) {
        if (millis == null) return

        val date = convertMillisToDate(millis)

        _startDate.value = date
        updateNumberOfDays()
    }

    fun setEndDate(millis: Long?) {
        if (millis == null) return

        val date = convertMillisToDate(millis)

        _endDate.value = date
        updateNumberOfDays()
    }

    private fun updateNumberOfDays() {
        _numberOfDays.update { abs(_endDate.value.daysUntil(_startDate.value)) }
    }

    init {
        updateNumberOfDays()
    }
}