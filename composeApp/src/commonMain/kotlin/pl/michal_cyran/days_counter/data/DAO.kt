package pl.michal_cyran.days_counter.data

import kotlinx.datetime.LocalDate
import org.jetbrains.exposed.v1.core.SqlExpressionBuilder.eq
import org.jetbrains.exposed.v1.jdbc.deleteAll
import org.jetbrains.exposed.v1.jdbc.deleteWhere
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.jetbrains.exposed.v1.jdbc.update
import pl.michal_cyran.days_counter.domain.DatesPair
import pl.michal_cyran.days_counter.domain.LocalStorage


object DAO: LocalStorage {
    val database = DatabaseFactory.init()

    override fun saveDatesPair(datesPair: DatesPair) {
        transaction(db = database) {
            val rowsAffected = DatesPairExposed.update({ DatesPairExposed.id eq datesPair.id }) {
                it[startDate] = datesPair.startDate.toEpochDays()
                it[endDate] = datesPair.endDate.toEpochDays()
                it[title] = datesPair.title
            }

            if (rowsAffected == 0) {
                DatesPairExposed.insert {
                    it[id] = datesPair.id
                    it[startDate] = datesPair.startDate.toEpochDays()
                    it[endDate] = datesPair.endDate.toEpochDays()
                    it[title] = datesPair.title
                }
            }
        }
    }

    override fun getAllDatesPairs(): List<DatesPair> {
        var result: List<DatesPair> = emptyList()
        transaction {
            result = DatesPairExposed.selectAll().map {
                DatesPair(
                    id = it[DatesPairExposed.id],
                    startDate = LocalDate.fromEpochDays(it[DatesPairExposed.startDate]),
                    endDate = LocalDate.fromEpochDays(it[DatesPairExposed.endDate]),
                    title = it[DatesPairExposed.title]
                )
            }
        }

        if (result.isEmpty()) {
            result = listOf(
                DatesPair(
                    id = 0,
                    startDate = LocalDate(2023, 1, 1),
                    endDate = LocalDate(2023, 12, 31),
                    title = "Sample Date Pair"
                )
            )
            saveDatesPair(result[0])
        }

        return result
    }

    override fun deleteDatesPair(id: Int) {
        transaction {
            DatesPairExposed.deleteWhere { DatesPairExposed.id eq id }
        }
    }

    override fun updateDatesPair(datesPair: DatesPair) {
        TODO("Not yet implemented")
    }

    override fun clearDatabase() {
        transaction {
            DatesPairExposed.deleteAll()
        }
    }
}