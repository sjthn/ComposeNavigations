package dev.srijith.composenavigations

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dev.srijith.composenavigations.internal.DestinationStack
import dev.srijith.composenavigations.navigationoptions.NavigationOptionsBuilder

class NavigationViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val destinationStack: DestinationStack =
        savedStateHandle.get<DestinationStack>("destinationStack") ?: DestinationStack()

    private var destinationObserver: ((destination: Destination) -> Unit)? = null

    private val onBackPressedCallback = object : OnBackPressedCallback(false) {
        override fun handleOnBackPressed() {
            navigateBack()
        }
    }

    fun navigateBack(data: Bundle? = null) {
        destinationStack.removeTopEntry(data)
        savedStateHandle["destinationStack"] = destinationStack

        if (destinationStack.isEmpty() || destinationStack.size == 1) {
            onBackPressedCallback.isEnabled = false
        }

        if (destinationStack.isNotEmpty()) {
            destinationObserver?.invoke(destinationStack.last())
        }
    }

    fun navigate(
        destinationId: String,
        navigationOptionsBuilder: (NavigationOptionsBuilder.() -> Unit)? = null
    ) {
        val navigationOptions = NavigationOptionsBuilder(destinationId).apply {
            navigationOptionsBuilder?.invoke(this)
        }.build()
        destinationStack.addEntry(navigationOptions)
        savedStateHandle["destinationStack"] = destinationStack
        destinationObserver?.invoke(destinationStack.last())

        if (!onBackPressedCallback.isEnabled && destinationStack.size > 1) {
            onBackPressedCallback.isEnabled = true
        } else if (destinationStack.size <= 1) {
            onBackPressedCallback.isEnabled = false
        }
    }

    fun observeDestination(observer: (destination: Destination) -> Unit) {
        destinationObserver = observer
        if (destinationStack.size > 0) {
            observer(destinationStack.last())
        }
    }

    fun enableOnBackPressedCallback(dispatcher: OnBackPressedDispatcher) {
        onBackPressedCallback.remove()
        dispatcher.addCallback(onBackPressedCallback)
        onBackPressedCallback.isEnabled = destinationStack.size > 1
    }

    override fun onCleared() {
        destinationObserver = null
        onBackPressedCallback.remove()
        super.onCleared()
    }
}