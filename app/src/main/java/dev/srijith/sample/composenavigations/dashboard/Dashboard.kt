package dev.srijith.sample.composenavigations.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(dashboardDependencyComponent: DashboardDependencyComponent) {
    val dashboardPresenter = dashboardDependencyComponent.dashboardPresenter
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { dashboardPresenter.onNewEntryClicked() }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "")
            }
        }
    ) { paddingValues ->
        Box(Modifier.padding(paddingValues)) {
            Column {
                Text("Welcome ${dashboardPresenter.username.value}")
                LazyColumn {
                    items(20) { index ->
                        ListItem(headlineText = {
                            Text(text = "$index")
                        })
                    }
                }
            }
        }
    }
}