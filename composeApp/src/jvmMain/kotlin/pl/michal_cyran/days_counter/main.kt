package pl.michal_cyran.days_counter

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "DaysCounter",
    ) {
        App()
    }
}