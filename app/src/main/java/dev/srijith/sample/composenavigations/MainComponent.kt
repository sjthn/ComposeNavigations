package dev.srijith.sample.composenavigations

import androidx.compose.runtime.Composable
import dev.srijith.composenavigations.navigationgraph.NavDestinations
import dev.srijith.composenavigations.navigationgraph.scopedComposable
import dev.srijith.sample.composenavigations.dashboard.Dashboard
import dev.srijith.sample.composenavigations.dashboard.DashboardDependencyProvider
import dev.srijith.sample.composenavigations.login.Login
import dev.srijith.sample.composenavigations.login.dependency.LoginDependencyComponentProvider
import dev.srijith.sample.composenavigations.navigation.NavigatorPresenter
import dev.srijith.sample.composenavigations.passwordentry.PasswordEntry
import dev.srijith.sample.composenavigations.passwordentry.PasswordEntryDependencyProvider

@Composable
fun MainComponent(navigatorPresenter: NavigatorPresenter) {
    NavDestinations(destination = navigatorPresenter.navigateTo.value) {
        scopedComposable("login") {
            val loginDependencyComponent =
                LoginDependencyComponentProvider(navigatorPresenter).inject(it)
            Login(loginDependencyComponent)
        }
        scopedComposable("dashboard") {
            val username = it.data?.getString("username")
            if (username != null) {
                val dashboardDependencyComponent =
                    DashboardDependencyProvider(navigatorPresenter, username).inject(it)
                Dashboard(dashboardDependencyComponent)
            }
        }
        scopedComposable("passwordEntry") {
            val passwordEntryDependencyComponent =
                PasswordEntryDependencyProvider(navigatorPresenter).inject(it)
            PasswordEntry(passwordEntryDependencyComponent)
        }
    }
}
