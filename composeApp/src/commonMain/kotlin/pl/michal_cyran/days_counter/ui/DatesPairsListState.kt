package pl.michal_cyran.days_counter.ui

import androidx.compose.runtime.Immutable

@Immutable
data class DatesPairsListState(
    val isLoading: Boolean = false,
    val datesPairs: List<DatesPairUI> = emptyList(),
)