package dev.srijith.sample.composenavigations.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(dashboardDependencyComponent: DashboardDependencyComponent) {
    val dashboardPresenter = dashboardDependencyComponent.dashboardPresenter
    Scaffold { paddingValues ->
        Box(Modifier.padding(paddingValues)) {
            Column {
                Text("Welcome ${dashboardPresenter.userDetails.value?.firstName ?: ""}")
                TextField(
                    value = dashboardPresenter.nickname.value,
                    onValueChange = dashboardPresenter::onNicknameChanged,
                    label = {
                        Text(text = "Enter nickname")
                    }
                )
                Button(onClick = {
                    dashboardPresenter.onNewEntryClicked()
                }) {
                    Text("New Entry")
                }
            }
        }
    }
}