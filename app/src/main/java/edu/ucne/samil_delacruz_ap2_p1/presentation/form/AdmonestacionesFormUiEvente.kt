package edu.ucne.samil_delacruz_ap2_p1.presentation.form

sealed class AdmonestacionesFormUiEvent {
    data class Load(val id: Int?) : AdmonestacionesFormUiEvent()
    data class NombresChanged(val value: String) : AdmonestacionesFormUiEvent()
    data class RazonChanged(val value: String) : AdmonestacionesFormUiEvent()
    data class MontoChanged(val value: String) : AdmonestacionesFormUiEvent()
    object Save : AdmonestacionesFormUiEvent()
}
