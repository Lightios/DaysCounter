package pl.michal_cyran.days_counter.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dayscounter.composeapp.generated.resources.Res
import dayscounter.composeapp.generated.resources.clock_arrow_down
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StatisticItem(
    icon: DrawableResource,
    value: String,
    label: String,
    iconTint: Color = Color.Gray,
    valueColor: Color = Color.Black
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = label,
            tint = iconTint,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = value,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = valueColor
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun CounterCardPreview() {
    StatisticItem(
        icon = Res.drawable.clock_arrow_down,
        value = "1304",
        label = "Total Days",
        iconTint = Color.Gray
    )
}

