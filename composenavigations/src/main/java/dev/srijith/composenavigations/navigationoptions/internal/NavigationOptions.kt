package dev.srijith.composenavigations.navigationoptions.internal

import android.os.Bundle
import dev.srijith.composenavigations.navigationoptions.pop.internal.PopOptions

internal class NavigationOptions(
    val destinationId: String?,
    val popOptions: PopOptions?,
    val data: Bundle? = null
)