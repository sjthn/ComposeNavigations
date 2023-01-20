package dev.srijith.composenavigations.scopedcomposable

import androidx.compose.runtime.Composable
import dev.srijith.composenavigations.Destination
import dev.srijith.composenavigations.dependencyinjector.DependencyInjector

@Deprecated(
    "Imperative function",
    ReplaceWith("Declarative function: dev.srijith.composenavigations.navigationgraph.scopedComposable")
)
@Composable
fun <DependencyComponentT> Destination.ScopedComposable(
    dependencyInjector: DependencyInjector<DependencyComponentT>,
    destinationView: @Composable (dependencyComponent: DependencyComponentT) -> Unit
) {

    val dependencyComponent = dependencyInjector.inject(this)

    destinationView(dependencyComponent)
}