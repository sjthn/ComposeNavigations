package dev.srijith.composenavigations.navigationgraph

import androidx.compose.runtime.Composable
import dev.srijith.composenavigations.Destination
import dev.srijith.composenavigations.navigationgraph.internal.NavGraphEntry

fun NavGraphBuilder.scopedComposable(
    identifier: String,
    view: @Composable (destination: Destination) -> Unit
) {
    addEntry(NavGraphEntry(identifier, view))
}