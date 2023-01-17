package dev.srijith.sample.composenavigations.dependencylib

import androidx.lifecycle.ViewModelStoreOwner

interface DependencyInjector<DependencyComponentT> {
    fun inject(viewModelStoreOwner: ViewModelStoreOwner): DependencyComponentT
}