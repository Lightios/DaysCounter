package pl.michal_cyran.days_counter

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import pl.michal_cyran.days_counter.domain.DatesPair
import pl.michal_cyran.days_counter.domain.LocalStorage
import pl.michal_cyran.days_counter.ui.DatesPairsListState
import pl.michal_cyran.days_counter.ui.toDatePairUI
import pl.michal_cyran.days_counter.utils.convertMillisToDate
import kotlin.math.abs
import kotlin.properties.Delegates
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class DaysViewModel(
    val localStorage: LocalStorage,
): ViewModel() {

    @OptIn(ExperimentalTime::class)
    val today = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .date

    private var _datesPairs by Delegates.observable(listOf<DatesPair>()) { _, _, _ ->
        updateState()
    }

    private val _state = MutableStateFlow(
        DatesPairsListState()
    )

    val state = _state
        .onStart {
            loadDatesPairs()
        }
        .stateIn(
            viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5_000),
            initialValue = DatesPairsListState()
        )

    fun setStartDate(millis: Long?, id: Int) {
        if (millis == null) return

        val date = convertMillisToDate(millis)

        _datesPairs = _datesPairs.map {
            if (it.id == id) {
                it.copy(
                    startDate = date
                )
            } else {
                it
            }
        }

        saveToLocalStorage(
            _datesPairs[id]
        )
    }

    fun setEndDate(millis: Long?, id: Int) {
        print("???")
        if (millis == null) return

        val date = convertMillisToDate(millis)

        _datesPairs = _datesPairs.map {
            if (it.id == id) {
                it.copy(
                    endDate = date
                )
            } else {
                it
            }
        }

        saveToLocalStorage(
            _datesPairs[id]
        )
    }

    fun setTitle(title: String, id: Int) {
        _datesPairs = _datesPairs.map {
            if (it.id == id) {
                it.copy(
                    title = title
                )
            } else {
                it
            }
        }

        saveToLocalStorage(
            _datesPairs[id]
        )
    }

    fun loadDatesPairs() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            _datesPairs = localStorage
                .getAllDatesPairs()

            _state.update {
                it.copy(
                    datesPairs = _datesPairs.map { it.toDatePairUI() },
                    isLoading = false
                )
            }
        }
    }

    private fun saveToLocalStorage(
        datesPair: DatesPair
    ) {
        localStorage.saveDatesPair(
            datesPair
        )
    }

    fun addEmptyDatesPair() {
        val newId = if (_datesPairs.isEmpty()) 0 else (_datesPairs.maxOf { it.id } + 1)

        val newDatesPair = DatesPair(
            id = newId,
            startDate = today,
            endDate = today,
            title = "Title $newId"
        )

        _datesPairs = _datesPairs + newDatesPair

        saveToLocalStorage(
            newDatesPair
        )
    }

    fun clearAllData() {
        localStorage.clearDatabase()

        _datesPairs = emptyList()
    }

    fun deleteDatesPair(id: Int) {
        localStorage.deleteDatesPair(id)

        _datesPairs = _datesPairs.filter { it.id != id }
    }

    fun updateState() {
        _state.update {
            it.copy(
                datesPairs = _datesPairs.map { it.toDatePairUI() }
            )
        }
    }
}