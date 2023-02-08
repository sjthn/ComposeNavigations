package dev.srijith.sample.composenavigations.dashboard

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import dev.srijith.sample.composenavigations.navigation.NavigatorPresenter

class DashboardPresenter(private val navigatorPresenter: NavigatorPresenter, username: String) {
    val username: MutableState<String> = mutableStateOf(username)

    fun onNewEntryClicked() {
        navigatorPresenter.onDashboardItemClicked()
    }
}