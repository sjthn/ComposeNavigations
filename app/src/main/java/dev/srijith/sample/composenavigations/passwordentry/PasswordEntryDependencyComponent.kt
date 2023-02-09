package dev.srijith.sample.composenavigations.passwordentry

import androidx.lifecycle.ViewModelStoreOwner
import dev.srijith.composenavigations.dependencyinjector.DependencyInjector
import dev.srijith.composenavigations.scopedpresenter.presenter
import dev.srijith.sample.composenavigations.navigation.NavigatorPresenter

interface PasswordEntryDependencyComponent {
    val passwordEntryPresenter: PasswordEntryPresenter
}

class PasswordEntryDependencyComponentImpl(
    viewModelStoreOwner: ViewModelStoreOwner,
    navigatorPresenter: NavigatorPresenter
) :
    PasswordEntryDependencyComponent {
    override val passwordEntryPresenter: PasswordEntryPresenter by viewModelStoreOwner.presenter {
        PasswordEntryPresenter(navigatorPresenter)
    }
}

class PasswordEntryDependencyProvider(private val navigatorPresenter: NavigatorPresenter) :
    DependencyInjector<PasswordEntryDependencyComponent> {
    override fun inject(viewModelStoreOwner: ViewModelStoreOwner): PasswordEntryDependencyComponent {
        return PasswordEntryDependencyComponentImpl(
            viewModelStoreOwner,
            navigatorPresenter
        )
    }
}