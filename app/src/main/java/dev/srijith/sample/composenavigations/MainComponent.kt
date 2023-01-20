package dev.srijith.sample.composenavigations

import androidx.compose.runtime.Composable
import dev.srijith.composenavigations.navigationgraph.NavDestinations
import dev.srijith.composenavigations.navigationgraph.scopedComposable
import dev.srijith.sample.composenavigations.dashboard.Dashboard
import dev.srijith.sample.composenavigations.dashboard.DashboardDependencyProvider
import dev.srijith.sample.composenavigations.navigation.NavigatorPresenter
import dev.srijith.sample.composenavigations.passwordentry.PasswordEntry
import dev.srijith.sample.composenavigations.passwordentry.PasswordEntryDependencyProvider

@Composable
fun MainComponent(navigatorPresenter: NavigatorPresenter) {
    NavDestinations(destination = navigatorPresenter.navigateTo.value) {
        scopedComposable("dashboard") {
            val dashboardDependencyComponent =
                DashboardDependencyProvider(navigatorPresenter).inject(it)
            Dashboard(dashboardDependencyComponent)
        }
        scopedComposable("passwordEntry") {
            val passwordEntryDependencyComponent =
                PasswordEntryDependencyProvider(navigatorPresenter).inject(it)
            PasswordEntry(passwordEntryDependencyComponent)
        }
    }
}
