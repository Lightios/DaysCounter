package pl.michal_cyran.days_counter

import android.content.Context
import kotlin.io.path.exists
import android.app.Application



 class MyApp : Application() {
     override fun onCreate() {
         super.onCreate()
         instance = this
     }
     companion object {
         lateinit var instance: MyApp
             private set
     }
 }