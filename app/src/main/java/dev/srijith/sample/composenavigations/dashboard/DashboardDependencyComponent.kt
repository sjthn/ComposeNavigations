package dev.srijith.sample.composenavigations.dashboard

import androidx.lifecycle.ViewModelStoreOwner
import dev.srijith.composenavigations.dependencyinjector.DependencyInjector
import dev.srijith.composenavigations.scopedpresenter.presenter
import dev.srijith.sample.composenavigations.UserRepository
import dev.srijith.sample.composenavigations.navigation.NavigatorPresenter

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