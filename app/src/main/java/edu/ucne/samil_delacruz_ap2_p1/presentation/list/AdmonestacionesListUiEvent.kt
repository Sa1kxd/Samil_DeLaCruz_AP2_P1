package edu.ucne.samil_delacruz_ap2_p1.presentation.list

sealed class AdmonestacionesListUiEvent {
    object Load : AdmonestacionesListUiEvent()
    object ClearMessage : AdmonestacionesListUiEvent()
    object CreateNew : AdmonestacionesListUiEvent()
    data class Delete(val id: Int) : AdmonestacionesListUiEvent()
    data class Edit(val id: Int) : AdmonestacionesListUiEvent()
    data class ShowMessage(val message: String) : AdmonestacionesListUiEvent()
}