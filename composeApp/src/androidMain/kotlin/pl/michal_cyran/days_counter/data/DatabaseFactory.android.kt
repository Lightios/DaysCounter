package pl.michal_cyran.days_counter.data

import android.content.Context
import pl.michal_cyran.days_counter.MyApp

actual fun getPlatformSpecificDbPath(dbName: String): String {
    val context: Context = MyApp.instance // Or however you access Context
    // Using app's internal files directory is a good choice
    val dir = context.filesDir // /data/data/your.package.name/files
    // val dir = context.getDatabasePath("databases_folder").parentFile // Alternative to store in 'databases' subfolder

    if (!dir.exists()) {
        dir.mkdirs()
    }
    // H2 connection URL takes the base path, it will add extensions like .mv.db
    return "${dir.absolutePath}/$dbName"
}