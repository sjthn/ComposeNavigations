package dev.srijith.composenavigations.internal

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.Lifecycle
import dev.srijith.composenavigations.Destination
import dev.srijith.composenavigations.navigationoptions.internal.NavigationOptions

internal class DestinationStack() : Parcelable {
    private var navStack: List<Destination> = listOf()

    private constructor(parcel: Parcel) : this() {
        val tempNavStack = mutableListOf<Destination>()
        parcel.readTypedList(tempNavStack, Destination.CREATOR)
        navStack = tempNavStack
    }

    fun addEntry(navigationOptions: NavigationOptions) {
        val destinationId = navigationOptions.destinationId
        checkNotNull(destinationId) {
            "Destination ID is required and it cannot be null"
        }

        val popOptions = navigationOptions.popOptions
        if (popOptions == null) {
            addEntry(destinationId, navigationOptions.data)
            return
        }

        val oneIndex = navStack.indexOfFirst { it.identifier == popOptions.popUntil } + 1
        check(oneIndex != 0) {
            "Cannot find the entry with ID ${popOptions.popUntil} to pop"
        }
        navStack = navStack.take(oneIndex - (if (popOptions.inclusive) 1 else 0))
        addEntry(destinationId, navigationOptions.data)
    }

    private fun addEntry(destinationId: String, data: Bundle?) {
        val destination = Destination(destinationId, data)
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

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeTypedList(navStack)
    }

    companion object CREATOR : Parcelable.Creator<DestinationStack> {
        override fun createFromParcel(parcel: Parcel): DestinationStack {
            return DestinationStack(parcel)
        }

        override fun newArray(size: Int): Array<DestinationStack?> {
            return arrayOfNulls(size)
        }
    }
}