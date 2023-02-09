package dev.srijith.composenavigations

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class Destination(destinationId: String, val data: Bundle? = null) : LifecycleOwner,
    ViewModelStoreOwner, Parcelable {
    val identifier: String = destinationId

    private var lifecycle = LifecycleRegistry(this)

    private val viewModelStore = ViewModelStore()

    var returnData: Bundle? = null
        internal set

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: throw NullPointerException("DestinationId cannot be null"),
        parcel.readBundle(Bundle::class.java.classLoader)
    ) {
        val lifecycleState = parcel.readString()
        lifecycle.currentState = Lifecycle.State.valueOf(
            lifecycleState ?: throw NullPointerException("Lifecycle state cannot be null")
        )
        returnData = parcel.readBundle(Bundle::class.java.classLoader)
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycle
    }

    fun updateLifecycleState(lifecycleState: Lifecycle.State) {
        lifecycle.currentState = lifecycleState
    }

    override fun getViewModelStore(): ViewModelStore {
        return viewModelStore
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(identifier)
        dest.writeBundle(data)
        dest.writeString(lifecycle.currentState.name)
        dest.writeBundle(returnData)
    }

    companion object CREATOR : Parcelable.Creator<Destination> {
        override fun createFromParcel(parcel: Parcel): Destination {
            return Destination(parcel)
        }

        override fun newArray(size: Int): Array<Destination?> {
            return arrayOfNulls(size)
        }
    }
}