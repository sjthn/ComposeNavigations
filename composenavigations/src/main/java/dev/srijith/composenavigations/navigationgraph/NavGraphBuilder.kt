package dev.srijith.composenavigations.navigationgraph

import dev.srijith.composenavigations.navigationgraph.internal.NavGraphEntry
import dev.srijith.composenavigations.navigationgraph.internal.NavGraph

class NavGraphBuilder {
    private val navGraphEntries: MutableList<NavGraphEntry> = mutableListOf()
    internal fun addEntry(navGraphEntry: NavGraphEntry) {
        navGraphEntries.add(navGraphEntry)
    }

    internal fun build(): NavGraph {
        return NavGraph(navGraphEntries)
    }
}