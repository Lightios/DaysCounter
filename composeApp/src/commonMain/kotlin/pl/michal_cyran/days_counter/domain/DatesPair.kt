package pl.michal_cyran.days_counter.domain

import org.jetbrains.exposed.v1.core.Table

const val MAX_VARCHAR_LENGTH = 128

object DatesPair : Table("dates_pair") {
    val id = integer("id").autoIncrement()
    val title = varchar("name", MAX_VARCHAR_LENGTH)
    val description = varchar("description", MAX_VARCHAR_LENGTH)
    val isCompleted = bool("completed").default(false)
}