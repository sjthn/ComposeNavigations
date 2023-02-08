package dev.srijith.sample.composenavigations.dashboard

import androidx.lifecycle.ViewModelStoreOwner
import dev.srijith.composenavigations.dependencyinjector.DependencyInjector
import dev.srijith.composenavigations.scopedpresenter.presenter
import dev.srijith.sample.composenavigations.navigation.NavigatorPresenter

interface DashboardDependencyComponent {
    val dashboardPresenter: DashboardPresenter
}

class DashboardDependencyComponentImpl(
    viewModelStoreOwner: ViewModelStoreOwner,
    navigatorPresenter: NavigatorPresenter,
    username: String
) :
    DashboardDependencyComponent {
    override val dashboardPresenter: DashboardPresenter by viewModelStoreOwner.presenter {
        DashboardPresenter(navigatorPresenter, username)
    }
}

class DashboardDependencyProvider(
    private val navigatorPresenter: NavigatorPresenter,
    private val username: String
) :
    DependencyInjector<DashboardDependencyComponent> {
    override fun inject(viewModelStoreOwner: ViewModelStoreOwner): DashboardDependencyComponent {
        return DashboardDependencyComponentImpl(
            viewModelStoreOwner,
            navigatorPresenter,
            username
        )
    }
}