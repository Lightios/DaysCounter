package pl.michal_cyran.days_counter.data

actual fun getPlatformSpecificDbPath(dbName: String): String {
    return "./$dbName"
}