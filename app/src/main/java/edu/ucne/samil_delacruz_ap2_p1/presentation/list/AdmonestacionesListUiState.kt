package edu.ucne.samil_delacruz_ap2_p1.presentation.list

import edu.ucne.samil_delacruz_ap2_p1.domain.model.Admonestacion

data class AdmonestacionesListUiState (
    val isLoading: Boolean = false,
    val admonestacion: List<Admonestacion> = emptyList(),
    val message: String? = null,
    val navigateToCreate: Boolean = false,
    val navigateToEditId: Int? = null
)