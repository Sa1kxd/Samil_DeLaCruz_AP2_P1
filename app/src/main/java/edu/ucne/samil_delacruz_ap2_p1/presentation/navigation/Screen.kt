package edu.ucne.samil_delacruz_ap2_p1.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {

    @Serializable
    data object  AdmonestacionesList : Screen()

    @Serializable
    data class AdmonestacionesForm(val id: Int) : Screen()
}