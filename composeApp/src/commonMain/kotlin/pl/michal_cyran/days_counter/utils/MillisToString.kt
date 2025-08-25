package pl.michal_cyran.days_counter.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.number

fun convertMillisToString(millis: Long): String {
    LocalDate.fromEpochDays(
        (millis / (1000 * 60 * 60 * 24)).toInt()
    ).let {
        return "${it.month.number}/${it.day}/${it.year}"
    }
}