package pl.michal_cyran.days_counter.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DateCard(
    header: String,
    body: String,
    displayedDate: String,
    onDateSelected: (Long?) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.padding(16.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 12.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface,
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = header,
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
            )

            Text(
                text = body,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            )

            DatePickerFieldToModal(
                displayedDate = displayedDate,
                onDateSelected = onDateSelected,
            )
        }
    }
}