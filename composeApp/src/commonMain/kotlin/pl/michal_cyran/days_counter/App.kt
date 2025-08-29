package pl.michal_cyran.days_counter

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import pl.michal_cyran.days_counter.data.DAO
import pl.michal_cyran.days_counter.ui.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    AppTheme {
        val viewModel = viewModel { DaysViewModel(
            localStorage = DAO
        ) }

        val datesPairListState = viewModel.state.collectAsStateWithLifecycle()

        MainScreen(
            datesPairsListState = datesPairListState.value,
            clearAllData = viewModel::clearAllData,
            addEmptyDatesPair = viewModel::addEmptyDatesPair,
            setStartDate = viewModel::setStartDate,
            setEndDate = viewModel::setEndDate,
            onTitleChange = viewModel::setTitle,
            onDatesPairDelete = viewModel::deleteDatesPair,
        )
    }
}


