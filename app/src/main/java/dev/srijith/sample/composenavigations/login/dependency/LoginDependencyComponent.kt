package dev.srijith.sample.composenavigations.login.dependency

import androidx.lifecycle.ViewModelStoreOwner
import dev.srijith.composenavigations.dependencyinjector.DependencyInjector
import dev.srijith.composenavigations.scopedpresenter.presenter
import dev.srijith.sample.composenavigations.login.LoginPresenter
import dev.srijith.sample.composenavigations.navigation.NavigatorPresenter

interface LoginDependencyComponent {
    val loginPresenter: LoginPresenter
}

class LoginDependencyComponentImpl(
    viewModelStoreOwner: ViewModelStoreOwner,
    navigatorPresenter: NavigatorPresenter
) : LoginDependencyComponent {
    override val loginPresenter: LoginPresenter by viewModelStoreOwner.presenter {
        LoginPresenter(navigatorPresenter)
    }
}

class LoginDependencyComponentProvider(private val navigatorPresenter: NavigatorPresenter) :
    DependencyInjector<LoginDependencyComponent> {
    override fun inject(viewModelStoreOwner: ViewModelStoreOwner): LoginDependencyComponent {
        return LoginDependencyComponentImpl(viewModelStoreOwner, navigatorPresenter)
    }

}