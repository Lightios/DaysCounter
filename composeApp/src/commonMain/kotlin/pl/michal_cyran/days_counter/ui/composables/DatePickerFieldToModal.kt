package pl.michal_cyran.days_counter.ui.composables

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dayscounter.composeapp.generated.resources.Res
import dayscounter.composeapp.generated.resources.date_range
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DatePickerFieldToModal(
    displayedDate: String,
    onDateSelected: (Long?) -> Unit,
    modifier: Modifier = Modifier
) {
    var showModal by remember { mutableStateOf(false) }

    OutlinedButton(
        onClick = { showModal = true },
        modifier = modifier
            .fillMaxWidth(),
        shape = RectangleShape
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = displayedDate,
                fontSize = 10.sp,
            )
            Icon(
                painter = painterResource(Res.drawable.date_range),
                contentDescription = null,
            )
        }
    }

    if (showModal) {
        DatePickerModal(
            onDateSelected = { onDateSelected(it) },
            onDismiss = { showModal = false }
        )
    }
}

@Preview
@Composable
fun DatePickerFieldToModalPreview() {
    DatePickerFieldToModal(
        displayedDate = "Select date",
        onDateSelected = {}
    )
}