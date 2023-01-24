package dev.srijith.composenavigations.internal

import androidx.lifecycle.Lifecycle
import dev.srijith.composenavigations.Destination
import dev.srijith.composenavigations.navigationoptions.internal.NavigationOptions

internal class DestinationStack {
    private var navStack: List<Destination> = listOf()

    fun addEntry(destinationId: String) {
        val destination = Destination(destinationId)
        val temp = navStack.toMutableList()
        temp.add(destination)
        navStack = temp
        navStack.lastOrNull()?.updateLifecycleState(Lifecycle.State.CREATED)
    }

    fun addEntry(navigationOptions: NavigationOptions) {
        val destinationId = navigationOptions.destinationId
        checkNotNull(destinationId) {
            "Destination ID is required and it cannot be null"
        }

        val popOptions = navigationOptions.popOptions
        if (popOptions == null) {
            addEntry(destinationId)
            return
        }

        val oneIndex = navStack.indexOfFirst { it.identifier == popOptions.popUntil } + 1
        check(oneIndex != 0) {
            "Cannot find the entry with ID ${popOptions.popUntil} to pop"
        }
        navStack = navStack.take(oneIndex - (if (popOptions.inclusive) 1 else 0))
        val temp = navStack.toMutableList()
        val destination = Destination(destinationId)
        temp.add(destination)
        navStack = temp
        navStack.lastOrNull()?.updateLifecycleState(Lifecycle.State.CREATED)
    }

    fun removeTopEntry() {
        navStack.last().updateLifecycleState(Lifecycle.State.DESTROYED)
        val temp = navStack.toMutableList()
        temp.removeLast()
        navStack = temp
    }

    fun isNotEmpty(): Boolean {
        return navStack.isNotEmpty()
    }

    fun isEmpty(): Boolean {
        return navStack.isEmpty()
    }

    fun last(): Destination = navStack.last()

    val size get() = navStack.size
}