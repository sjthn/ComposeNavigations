package dev.srijith.composenavigations

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class Destination(destinationId: String) : LifecycleOwner, ViewModelStoreOwner {
    val identifier: String = destinationId

    private var lifecycle = LifecycleRegistry(this)

    private val viewModelStore = ViewModelStore()

    override fun getLifecycle(): Lifecycle {
        return lifecycle
    }

    fun updateLifecycleState(lifecycleState: Lifecycle.State) {
        lifecycle.currentState = lifecycleState
    }

    override fun getViewModelStore(): ViewModelStore {
        return viewModelStore
    }
}