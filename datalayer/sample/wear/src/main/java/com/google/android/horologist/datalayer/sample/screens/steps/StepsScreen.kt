package com.google.android.horologist.datalayer.sample.screens.steps

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.ScalingLazyColumnState
import com.google.android.horologist.compose.material.Title
import com.google.android.horologist.datalayer.sample.R


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun StepsScreen(
    columnState: ScalingLazyColumnState,
    modifier: Modifier = Modifier,
    viewModel: StepsViewModel = hiltViewModel(),
) {
    // ViewModel에서 수집한 currentStepCount를 UI에 반영
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val stepCountValue = state.stepCountValue?.value ?: 0

    ScalingLazyColumn(
        columnState = columnState,
        modifier = modifier.fillMaxSize(),
    ) {
        item {
            Title(R.string.steps_title, Modifier)
        }
        item {
            Text(
                text = stringResource(id = R.string.server_step_message),
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
            )
        }
        item {
            Text(
                text = if (stepCountValue > 0) "현재 걸음 수 : $stepCountValue" else stringResource(R.string.steps_missing_message),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
