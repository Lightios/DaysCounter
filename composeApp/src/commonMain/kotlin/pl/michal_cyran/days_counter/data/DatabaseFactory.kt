package pl.michal_cyran.days_counter.data

import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

expect fun getPlatformSpecificDbPath(dbName: String): String

object DatabaseFactory {
    fun init(dbFileName: String = "mydayscounter_h2"): Database {
        val absoluteDbPath = getPlatformSpecificDbPath(dbFileName)

        val db = Database.connect(
            url = "jdbc:h2:$absoluteDbPath",
            driver = "org.h2.Driver"
        )

        transaction {
            SchemaUtils.create(DatesPairExposed)
        }

        return db
    }
}