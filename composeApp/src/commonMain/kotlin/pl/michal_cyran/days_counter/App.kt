package pl.michal_cyran.days_counter

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import pl.michal_cyran.days_counter.ui.DateCard
import pl.michal_cyran.days_counter.ui.theme.AppTheme

//import org.jetbrains.exposed.v1.jdbc.Database
//
//
//import org.jetbrains.exposed.v1.core.*
//import org.jetbrains.exposed.v1.core.SqlExpressionBuilder.eq
//import org.jetbrains.exposed.v1.jdbc.SchemaUtils
//import org.jetbrains.exposed.v1.jdbc.deleteWhere
//import org.jetbrains.exposed.v1.jdbc.insert
//import org.jetbrains.exposed.v1.jdbc.select
//import org.jetbrains.exposed.v1.jdbc.selectAll
//import org.jetbrains.exposed.v1.jdbc.transactions.transaction
//import org.jetbrains.exposed.v1.jdbc.update
import pl.michal_cyran.days_counter.domain.DatesPair


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    Text(
    "Hello world"
    )
//    Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
//    print("???")
//
//    transaction {
//        SchemaUtils.create(DatesPair)
//
//        val taskId = DatesPair.insert {
//            it[title] = "Learn Exposed"
//            it[description] = "Go through the Get started with Exposed tutorial"
//        } get DatesPair.id
//
//        val secondTaskId = DatesPair.insert {
//            it[title] = "Read The Hobbit"
//            it[description] = "Read the first two chapters of The Hobbit"
//            it[isCompleted] = true
//        } get DatesPair.id
//
//        println("Created new tasks with ids $taskId and $secondTaskId.")
//
//        DatesPair.select(DatesPair.id.count(), DatesPair.isCompleted).groupBy(DatesPair.isCompleted).forEach {
//            println("${it[DatesPair.isCompleted]}: ${it[DatesPair.id.count()]} ")
//        }
//
//        println("Remaining tasks: ${DatesPair.selectAll().toList()}")
//    }
//    AppTheme(
//        darkTheme = false
//    ) {
//        MainScreen()
//    }
}


