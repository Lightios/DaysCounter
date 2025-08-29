package pl.michal_cyran.days_counter.domain

import kotlinx.datetime.LocalDate

data class DatesPair (
    val id: Int,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val title: String,
)