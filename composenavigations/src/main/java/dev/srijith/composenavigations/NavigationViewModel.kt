package dev.srijith.composenavigations

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.lifecycle.ViewModel
import dev.srijith.composenavigations.internal.DestinationStack

class NavigationViewModel : ViewModel() {

    private val destinationStack: DestinationStack = DestinationStack()

    private var destinationObserver: ((destination: Destination) -> Unit)? = null

    private val onBackPressedCallback =
        object : OnBackPressedCallback(destinationStack.isNotEmpty()) {
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

    fun navigate(destinationId: String) {
        destinationStack.addEntry(destinationId)
        destinationObserver?.invoke(destinationStack.last())

        if (!onBackPressedCallback.isEnabled) {
            onBackPressedCallback.isEnabled = true
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