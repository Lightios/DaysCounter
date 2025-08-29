package pl.michal_cyran.days_counter.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dayscounter.composeapp.generated.resources.Res
import dayscounter.composeapp.generated.resources.flag
import dayscounter.composeapp.generated.resources.start
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pl.michal_cyran.days_counter.ui.theme.AppTheme

@Composable
fun DateCard(
    header: String,
    displayedDate: String,
    icon: DrawableResource,
    onDateSelected: (Long?) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Row {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                text = header,
                fontSize = 12.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
        }

        DatePickerFieldToModal(
            displayedDate = displayedDate,
            onDateSelected = onDateSelected,
        )
    }
}


@Preview
@Composable
fun DateCardPreview() {
    AppTheme {
        DateCard(
            header = "Start Date",
            displayedDate = "2023-10-10",
            onDateSelected = {  },
            icon = Res.drawable.flag,
        )
    }
}


@Preview
@Composable
fun DateCardPreviewDark() {
    AppTheme(darkTheme = true) {
        DateCard(
            header = "Start Date",
            displayedDate = "2023-10-10",
            onDateSelected = {  },
            icon = Res.drawable.start,
        )
    }
}