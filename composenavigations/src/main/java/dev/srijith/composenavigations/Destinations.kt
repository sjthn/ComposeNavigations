package dev.srijith.composenavigations

import androidx.compose.runtime.Composable

@Composable
fun Destinations(destination: Destination?, navigationGraph: @Composable Destination.() -> Unit) {
    if (destination != null) {
        navigationGraph(destination)
    }
}