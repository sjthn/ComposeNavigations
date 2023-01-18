package dev.srijith.composenavigations.dependencyinjector

import androidx.lifecycle.ViewModelStoreOwner

interface DependencyInjector<DependencyComponentT> {
    fun inject(viewModelStoreOwner: ViewModelStoreOwner): DependencyComponentT
}