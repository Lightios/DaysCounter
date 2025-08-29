package pl.michal_cyran.days_counter.data

import org.jetbrains.exposed.v1.core.Table

const val MAX_VARCHAR_LENGTH = 128

object DatesPairExposed : Table("dates_pair") {
    val id = integer("id").autoIncrement()
    val startDate = long("start_date")
    val endDate = long("end_date")
    val title = varchar("name", MAX_VARCHAR_LENGTH)
}
