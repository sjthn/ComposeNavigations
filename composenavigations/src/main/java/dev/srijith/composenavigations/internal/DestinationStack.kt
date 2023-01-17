package dev.srijith.composenavigations.internal

import androidx.lifecycle.Lifecycle
import dev.srijith.composenavigations.Destination

internal class DestinationStack {
    private var navStack: List<Destination> = listOf()

    fun addEntry(destinationId: String) {
        val destination = Destination(destinationId)
        val temp = navStack.toMutableList()
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