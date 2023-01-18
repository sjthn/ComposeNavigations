package dev.srijith.sample.composenavigations

import androidx.compose.runtime.Composable
import dev.srijith.composenavigations.Destinations
import dev.srijith.composenavigations.scopedcomposable.ScopedComposable
import dev.srijith.sample.composenavigations.dashboard.Dashboard
import dev.srijith.sample.composenavigations.dashboard.DashboardDependencyProvider
import dev.srijith.sample.composenavigations.navigation.NavigatorPresenter
import dev.srijith.sample.composenavigations.passwordentry.PasswordEntry
import dev.srijith.sample.composenavigations.passwordentry.PasswordEntryDependencyProvider

@Composable
fun MainComponent(navigatorPresenter: NavigatorPresenter) {
    // TODO: Avoid if-else conditions inside `Destinations`.
    //  Instead add support for declarative style of specifying composable views
    Destinations(destination = navigatorPresenter.navigateTo.value) {
        if (identifier == "dashboard") {
            ScopedComposable(
                dependencyInjector = DashboardDependencyProvider(
                    navigatorPresenter
                )
            ) {
                Dashboard(it)
            }
        } else if (identifier == "passwordEntry") {
            ScopedComposable(
                dependencyInjector = PasswordEntryDependencyProvider(
                    navigatorPresenter
                )
            ) {
                PasswordEntry(it)
            }
        }
    }
}
