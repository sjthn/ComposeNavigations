package dev.srijith.composenavigations

import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedDispatcher
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.CreationExtras

fun ComponentActivity.navigator(): NavigationViewModel {
    return LazyNavigationViewModel(
        { viewModelStore },
        { NavigationViewModelFactory() },
        { defaultViewModelCreationExtras },
        { onBackPressedDispatcher }
    ).value
}

class LazyNavigationViewModel(
    private val storeProducer: () -> ViewModelStore,
    private val factoryProducer: () -> NavigationViewModelFactory,
    private val creationExtrasProducer: () -> CreationExtras,
    private val onBackPressedDispatcherProducer: () -> OnBackPressedDispatcher
) : Lazy<NavigationViewModel> {
    private var cached: NavigationViewModel? = null

    override val value: NavigationViewModel
        get() {
            val viewModel = cached
            return if (viewModel == null) {
                val factory = factoryProducer()
                val store = storeProducer()
                ViewModelProvider(
                    store,
                    factory,
                    creationExtrasProducer()
                )[NavigationViewModel::class.java].also {
                    it.enableOnBackPressedCallback(onBackPressedDispatcherProducer())
                    cached = it
                }
            } else {
                viewModel
            }
        }

    override fun isInitialized(): Boolean = cached != null
}

class NavigationViewModelFactory : AbstractSavedStateViewModelFactory() {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        @Suppress("UNCHECKED_CAST")
        return NavigationViewModel(handle) as T
    }

}