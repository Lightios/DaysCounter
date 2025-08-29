package pl.michal_cyran.days_counter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dayscounter.composeapp.generated.resources.Res
import dayscounter.composeapp.generated.resources.add
import dayscounter.composeapp.generated.resources.delete
import dayscounter.composeapp.generated.resources.start
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pl.michal_cyran.days_counter.data.DAO
import pl.michal_cyran.days_counter.domain.DatesPair
import pl.michal_cyran.days_counter.ui.DatesPairUI
import pl.michal_cyran.days_counter.ui.DatesPairsListState
import pl.michal_cyran.days_counter.ui.composables.DateCard
import pl.michal_cyran.days_counter.ui.composables.DatesPairCard
import pl.michal_cyran.days_counter.ui.theme.AppTheme

@Composable
fun MainScreen(
    datesPairsListState: DatesPairsListState,
    clearAllData: () -> Unit,
    addEmptyDatesPair: () -> Unit,
    onTitleChange: (String, Int) -> Unit,
    setStartDate: (Long?, Int) -> Unit,
    setEndDate: (Long?, Int) -> Unit,
    onDatesPairDelete: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
            .padding(vertical = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Text(
            "Days Counter",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = addEmptyDatesPair,
                modifier = Modifier
                    .weight(4f)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.add),
                    contentDescription = "Add",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Add Counter",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Button(
                onClick = clearAllData,
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.delete),
                    contentDescription = "Delete",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }



        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(datesPairsListState.datesPairs) {
                DatesPairCard(
                    datesPair = it,
                    onStartDateSelected = { date -> setStartDate(date, it.id) },
                    onEndDateSelected = { date -> setEndDate(date, it.id) },
                    onTitleChange = { title -> onTitleChange(title, it.id) },
                    onDelete = { onDatesPairDelete(it.id) }
                )
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    AppTheme(darkTheme = true) {
        MainScreen(
            datesPairsListState = DatesPairsListState(
                datesPairs = listOf(
                    DatesPairUI(
                        id = 1,
                        startDate = "01.02.2023",
                        endDate = "28.08.2026",
                        daysLeft = 10,
                        daysPassed = 20,
                        title = "Test 1",
                    ),
                    DatesPairUI(
                        id = 1,
                        startDate = "01.02.2023",
                        endDate = "28.08.2026",
                        daysLeft = 5,
                        daysPassed = 10,
                        title = "Test 2",
                    )
                )
            ),
            clearAllData = {},
            addEmptyDatesPair = {},
            setStartDate = { _, _ -> },
            setEndDate = { _, _ -> },
            onTitleChange = { _, _ -> },
            onDatesPairDelete = {}
        )
    }
}