package dev.srijith.sample.composenavigations.dashboard

import androidx.lifecycle.ViewModelStoreOwner
import dev.srijith.sample.composenavigations.UserRepository
import dev.srijith.sample.composenavigations.dependencylib.DependencyInjector
import dev.srijith.sample.composenavigations.navigation.NavigatorPresenter
import dev.srijith.sample.composenavigations.presenterlib.presenter

interface DashboardDependencyComponent {
    val dashboardPresenter: DashboardPresenter
}

class DashboardDependencyComponentImpl(
    viewModelStoreOwner: ViewModelStoreOwner,
    private val userRepository: UserRepository,
    navigatorPresenter: NavigatorPresenter
) :
    DashboardDependencyComponent {
    override val dashboardPresenter: DashboardPresenter by viewModelStoreOwner.presenter {
        DashboardPresenter(userRepository, navigatorPresenter)
    }
}

class DashboardDependencyProvider(private val navigatorPresenter: NavigatorPresenter) :
    DependencyInjector<DashboardDependencyComponent> {
    override fun inject(viewModelStoreOwner: ViewModelStoreOwner): DashboardDependencyComponent {
        return DashboardDependencyComponentImpl(
            viewModelStoreOwner,
            UserRepository(),
            navigatorPresenter
        )
    }
}