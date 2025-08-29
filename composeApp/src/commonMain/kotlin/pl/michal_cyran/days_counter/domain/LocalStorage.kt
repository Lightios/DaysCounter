package pl.michal_cyran.days_counter.domain

interface LocalStorage {
    fun saveDatesPair(datesPair: DatesPair)
    fun getAllDatesPairs(): List<DatesPair>
    fun deleteDatesPair(id: Int)
    fun updateDatesPair(datesPair: DatesPair)
    fun clearDatabase()
}