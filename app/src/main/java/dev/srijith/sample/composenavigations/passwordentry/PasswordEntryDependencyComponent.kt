package dev.srijith.sample.composenavigations.passwordentry

import androidx.lifecycle.ViewModelStoreOwner
import dev.srijith.sample.composenavigations.UserRepository
import dev.srijith.sample.composenavigations.dependencylib.DependencyInjector
import dev.srijith.sample.composenavigations.navigation.NavigatorPresenter
import dev.srijith.sample.composenavigations.presenterlib.presenter

interface PasswordEntryDependencyComponent {
    val passwordEntryPresenter: PasswordEntryPresenter
}

class PasswordEntryDependencyComponentImpl(
    viewModelStoreOwner: ViewModelStoreOwner,
    private val userRepository: UserRepository,
    navigatorPresenter: NavigatorPresenter
) :
    PasswordEntryDependencyComponent {
    override val passwordEntryPresenter: PasswordEntryPresenter by viewModelStoreOwner.presenter {
        PasswordEntryPresenter()
    }
}

class PasswordEntryDependencyProvider(private val navigatorPresenter: NavigatorPresenter) :
    DependencyInjector<PasswordEntryDependencyComponent> {
    override fun inject(viewModelStoreOwner: ViewModelStoreOwner): PasswordEntryDependencyComponent {
        return PasswordEntryDependencyComponentImpl(
            viewModelStoreOwner,
            UserRepository(),
            navigatorPresenter
        )
    }
}