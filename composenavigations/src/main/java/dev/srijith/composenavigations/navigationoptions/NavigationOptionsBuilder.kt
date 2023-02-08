package dev.srijith.composenavigations.navigationoptions

import android.os.Bundle
import dev.srijith.composenavigations.navigationoptions.internal.NavigationOptions
import dev.srijith.composenavigations.navigationoptions.pop.PopOptionsBuilder
import dev.srijith.composenavigations.navigationoptions.pop.internal.PopOptions

class NavigationOptionsBuilder(private val destinationId: String) {
    private var popOptions: PopOptions? = null
    private var data: Bundle? = null

    fun popUntil(destinationId: String, popOptionsBuilder: (PopOptionsBuilder.() -> Unit)? = null) {
        val popOptions = PopOptionsBuilder(destinationId).apply {
            popOptionsBuilder?.invoke(this)
        }.build()
        this.popOptions = popOptions
    }

    fun setData(data: Bundle) {
        this.data = data
    }

    internal fun build(): NavigationOptions {
        return NavigationOptions(destinationId, popOptions, data)
    }
}