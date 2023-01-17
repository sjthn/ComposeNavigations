package dev.srijith.sample.composenavigations.scopedobjects

import androidx.compose.runtime.Composable
import dev.srijith.sample.composenavigations.dependencylib.DependencyInjector
import dev.srijith.composenavigations.Destination

@Composable
fun <DependencyComponentT> Destination.ScopedComposable(
    dependencyInjector: DependencyInjector<DependencyComponentT>,
    destinationView: @Composable (dependencyComponent: DependencyComponentT) -> Unit
) {

    val dependencyComponent = dependencyInjector.inject(this)

    destinationView(dependencyComponent)
}