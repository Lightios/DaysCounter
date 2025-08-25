package pl.michal_cyran.days_counter.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.number

fun convertMillisToDate(millis: Long): LocalDate {
    val days = (millis / (1000 * 60 * 60 * 24)).toInt()
    val date = LocalDate.fromEpochDays(days)

    return date
}
