package pl.michal_cyran.days_counter.ui.composables

import org.jetbrains.compose.ui.tooling.preview.Preview
import pl.michal_cyran.days_counter.ui.theme.AppTheme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dayscounter.composeapp.generated.resources.Res
import dayscounter.composeapp.generated.resources.clock_arrow_down
import dayscounter.composeapp.generated.resources.delete
import dayscounter.composeapp.generated.resources.start
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CounterScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Add Counter Button


        // Counter Card
        CounterCard()
    }
}

@Composable
fun CounterCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header with title and delete icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = "",
                    onValueChange = { /* Handle text change */ },
                    placeholder = { Text("Enter name") },
                    singleLine = true,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    painter = painterResource(Res.drawable.delete),
                    contentDescription = "Delete",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { /* Delete logic */ }
                )
            }

            // Date fields
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                DateField(
                    label = "START DATE",
                    date = "01.02.2023",
                    subtitle = "Feb 1, 2023",
                    modifier = Modifier.weight(1f)
                )
                DateField(
                    label = "END DATE",
                    date = "28.08.2026",
                    subtitle = "Aug 28, 2026",
                    modifier = Modifier.weight(1f)
                )
            }

            // Progress section
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Progress",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "72%",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium
                    )
                }

            }

            // Statistics
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatisticItem(
                    icon = Res.drawable.clock_arrow_down,
                    value = "940",
                    label = "Days Passed",
                    iconTint = Color.Gray
                )
                StatisticItem(
                    icon = Res.drawable.clock_arrow_down,
                    value = "365",
                    label = "Days Left",
                    iconTint = Color(0xFFFF6B35),
                    valueColor = Color(0xFFFF6B35)
                )
                StatisticItem(
                    icon = Res.drawable.clock_arrow_down,
                    value = "1304",
                    label = "Total Days",
                    iconTint = Color.Gray
                )
            }

            // Remaining days text
            Text(
                text = "365 days remaining",
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun DateField(
    label: String,
    date: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.start),
                contentDescription = "Calendar",
                tint = Color.Gray,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = date,
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
        }

        Text(
            text = subtitle,
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}



@Preview
@Composable
fun CounterScreenPreview() {
    AppTheme {
        CounterScreen()
    }
}
