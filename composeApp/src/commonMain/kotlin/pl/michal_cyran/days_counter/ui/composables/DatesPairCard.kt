package pl.michal_cyran.days_counter.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dayscounter.composeapp.generated.resources.Res
import dayscounter.composeapp.generated.resources.clock_arrow_down
import dayscounter.composeapp.generated.resources.clock_arrow_up
import dayscounter.composeapp.generated.resources.delete
import dayscounter.composeapp.generated.resources.flag
import dayscounter.composeapp.generated.resources.start
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pl.michal_cyran.days_counter.ui.DatesPairUI
import pl.michal_cyran.days_counter.ui.theme.AppTheme

@Composable
fun DatesPairCard(
    datesPair: DatesPairUI,
    onTitleChange: (String) -> Unit,
    onStartDateSelected: (Long?) -> Unit,
    onEndDateSelected: (Long?) -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = datesPair.title,
                    onValueChange = onTitleChange,
                    placeholder = { Text("Enter name") },
                    singleLine = true,
                    modifier = Modifier.weight(1f)
                )
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                Icon(
                    painter = painterResource(Res.drawable.delete),
                    contentDescription = "Delete",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            onDelete()
                        }
                )
            }

            Row {
                DateCard(
                    header = "Start Date",
                    displayedDate = datesPair.startDate,
                    onDateSelected = onStartDateSelected,
                    icon = Res.drawable.start,
                    modifier = Modifier
                        .weight(1f)
                )

                DateCard(
                    header = "End Date",
                    displayedDate = datesPair.endDate,
                    onDateSelected = onEndDateSelected,
                    icon = Res.drawable.flag,
                    modifier = Modifier
                        .weight(1f)
                )
            }

            val denominator = (datesPair.daysPassed + datesPair.daysLeft).takeIf { it != 0 } ?: 1

            LinearProgressIndicator(
                progress = { (datesPair.daysPassed.toFloat() / denominator) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = Color(0xFFE0E0E0),
                strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize().padding(bottom = 16.dp, top = 8.dp)
            ) {
                StatisticItem(
                    icon = Res.drawable.clock_arrow_up,
                    value = datesPair.daysPassed.toString(),
                    label = "Days Passed",
                    iconTint = MaterialTheme.colorScheme.primary,
                    valueColor = MaterialTheme.colorScheme.onSurface
                )
                StatisticItem(
                    icon = Res.drawable.clock_arrow_down,
                    value = datesPair.daysLeft.toString(),
                    label = "Days Left",
                    iconTint = MaterialTheme.colorScheme.primary,
                    valueColor = MaterialTheme.colorScheme.onSurface
                )
            }

        }
    }
}

@Preview
@Composable
fun DatesPairCardPreview() {
    val datesPair = DatesPairUI(
        id = 1,
        startDate = "2023-01-01",
        endDate = "2023-12-31",
        title = "Sample Event",
        daysPassed = 100,
        daysLeft = 200
    )

    AppTheme(
        darkTheme = true,
    ) {
        DatesPairCard(
            datesPair = datesPair,
            onStartDateSelected = { _, -> },
            onEndDateSelected = { _, -> },
            onTitleChange = { },
            onDelete = { },
        )
    }
}