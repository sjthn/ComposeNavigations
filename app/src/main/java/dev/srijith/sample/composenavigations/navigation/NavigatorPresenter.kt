package dev.srijith.sample.composenavigations.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import dev.srijith.composenavigations.Destination
import dev.srijith.composenavigations.NavigationViewModel

class NavigatorPresenter(initialDestination: String, private val navigatorVM: NavigationViewModel) {
    val navigateTo: MutableState<Destination?> = mutableStateOf(null)

    init {
        navigatorVM.observeDestination {
            navigateTo.value = it
        }
        navigatorVM.navigate(initialDestination)
    }

    fun onDashboardItemClicked() {
        navigatorVM.navigate("passwordEntry")
    }

    fun onLoginClicked() {
        navigatorVM.navigate("dashboard") {
            popUntil("login") {
                inclusive = true
            }
        }
    }
}