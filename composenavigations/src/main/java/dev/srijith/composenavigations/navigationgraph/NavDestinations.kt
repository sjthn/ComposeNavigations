package dev.srijith.composenavigations.navigationgraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.srijith.composenavigations.Destination

@Composable
fun NavDestinations(destination: Destination?, navGraphBuilder: NavGraphBuilder.() -> Unit) {
    if (destination == null) {
        return
    }

    val navigationGraph = remember { NavGraphBuilder().apply(navGraphBuilder).build() }

    val destinationEntry =
        navigationGraph.navGraphEntries.find { it.identifier == destination.identifier }
            ?: throw java.lang.IllegalStateException("None of the composable children have the identifier ${destination.identifier}")

    destinationEntry.view(destination)
}