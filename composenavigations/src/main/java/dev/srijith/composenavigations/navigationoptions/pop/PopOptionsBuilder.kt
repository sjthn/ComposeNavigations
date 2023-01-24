package dev.srijith.composenavigations.navigationoptions.pop

import dev.srijith.composenavigations.navigationoptions.pop.internal.PopOptions

class PopOptionsBuilder(private val popUntil: String?) {
    var inclusive: Boolean = false

    internal fun build(): PopOptions {
        return PopOptions(popUntil, inclusive)
    }
}