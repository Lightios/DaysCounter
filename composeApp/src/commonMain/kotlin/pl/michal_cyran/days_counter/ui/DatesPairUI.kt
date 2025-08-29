package pl.michal_cyran.days_counter.ui

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import pl.michal_cyran.days_counter.domain.DatesPair
import kotlin.math.abs
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
val today: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

data class DatesPairUI(
    val id: Int,
    val startDate: String,
    val endDate: String,
    val title: String,
    val daysPassed: Int,
    val daysLeft: Int,
)


fun DatesPair.toDatePairUI(): DatesPairUI {
    return DatesPairUI(
        id = id,
        startDate = startDate.toString(),
        endDate = endDate.toString(),
        title = title,
        daysPassed = abs(today.daysUntil(startDate)),
        daysLeft = abs(today.daysUntil(endDate)),

    )
}