package dev.srijith.composenavigations

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.lifecycle.ViewModel
import dev.srijith.composenavigations.internal.DestinationStack
import dev.srijith.composenavigations.navigationoptions.NavigationOptionsBuilder

class NavigationViewModel : ViewModel() {

    private val destinationStack: DestinationStack = DestinationStack()

    private var destinationObserver: ((destination: Destination) -> Unit)? = null

    private val onBackPressedCallback = object : OnBackPressedCallback(false) {
        override fun handleOnBackPressed() {
            navigateBack()
        }
    }

    fun navigateBack() {
        destinationStack.removeTopEntry()

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
        destinationObserver?.invoke(destinationStack.last())

        if (!onBackPressedCallback.isEnabled && destinationStack.size > 1) {
            onBackPressedCallback.isEnabled = true
        } else if (destinationStack.size <= 1) {
            onBackPressedCallback.isEnabled = false
        }
    }

    fun observeDestination(observer: (destination: Destination) -> Unit) {
        destinationObserver = observer
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