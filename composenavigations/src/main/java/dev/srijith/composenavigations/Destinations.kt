package dev.srijith.composenavigations

import androidx.compose.runtime.Composable

@Deprecated(
    "As it promotes imperative navigation",
    ReplaceWith("Declarative navigation: dev.srijith.composenavigations.navigationgraph.NavDestinations")
)
@Composable
fun Destinations(destination: Destination?, navigationGraph: @Composable Destination.() -> Unit) {
    if (destination != null) {
        navigationGraph(destination)
    }
}