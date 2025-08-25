package pl.michal_cyran.days_counter

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform