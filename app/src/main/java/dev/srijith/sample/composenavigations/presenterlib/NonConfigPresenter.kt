package dev.srijith.sample.composenavigations.presenterlib

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.get

fun <PresenterT> ViewModelStoreOwner.presenter(creator: () -> PresenterT): Lazy<PresenterT> {
    return LazyPresenter({ viewModelStore },
        { ScopedViewModel.NavigatorViewModelFactory(creator) })
}

class LazyPresenter<PresenterT>(
    private val storeProducer: () -> ViewModelStore,
    private val factoryProducer: () -> ViewModelProvider.Factory,
) : Lazy<PresenterT> {
    private var cached: PresenterT? = null

    override val value: PresenterT
        get() {
            val viewModel = cached
            return if (viewModel == null) {
                val factory = factoryProducer()
                val store = storeProducer()
                ViewModelProvider(
                    store,
                    factory,
                ).get<ScopedViewModel<PresenterT>>().presenter.also {
                    cached = it
                }
            } else {
                viewModel
            }
        }

    override fun isInitialized(): Boolean = cached != null
}

class ScopedViewModel<PresenterT>(val presenter: PresenterT) : ViewModel() {
    class NavigatorViewModelFactory<PresenterT>(private val creator: () -> PresenterT) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ScopedViewModel(creator()) as T
        }
    }
}