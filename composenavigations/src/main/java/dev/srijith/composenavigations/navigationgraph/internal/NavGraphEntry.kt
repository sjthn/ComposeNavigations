package dev.srijith.composenavigations.navigationgraph.internal

import androidx.compose.runtime.Composable
import dev.srijith.composenavigations.Destination

internal class NavGraphEntry(
    val identifier: String,
    val view: @Composable (destination: Destination) -> Unit
)