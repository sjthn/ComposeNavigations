package dev.srijith.sample.composenavigations.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.os.bundleOf
import dev.srijith.composenavigations.Destination
import dev.srijith.composenavigations.NavigationViewModel

class NavigatorPresenter(
    private val initialDestination: String,
    private val navigatorVM: NavigationViewModel
) {
    val navigateTo: MutableState<Destination?> = mutableStateOf(null)

    init {
        navigatorVM.observeDestination {
            navigateTo.value = it
        }
    }

    fun onAppStart() {
        navigatorVM.navigate(initialDestination)
    }

    fun onDashboardItemClicked() {
        navigatorVM.navigate("passwordEntry")
    }

    fun onLoginClicked(username: String) {
        navigatorVM.navigate("dashboard") {
            popUntil("login") {
                inclusive = true
            }
            setData(bundleOf("username" to username))
        }
    }
}