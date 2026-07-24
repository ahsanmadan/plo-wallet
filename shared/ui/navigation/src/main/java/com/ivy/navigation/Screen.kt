package com.ivy.navigation

/**
 * Marks a screen in the app navigation graph.
 * Extend it when creating a new screen.
 */
sealed interface Screen {
    /**
     * Marks whether a given screen uses the legacy navigation flow.
     * If it's a legacy screen, it automatically adds a Surface to make it work.
     * Do NOT mark new Material3 screens as legacy.
     */
    val isLegacy: Boolean
        get() = false
}
