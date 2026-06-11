package edu.ucne.samil_delacruz_ap2_p1.presentation.form

data class AdmonestacionesFormUiState (
    val isNew: Boolean = true,
    val admonestacionId: Int? = null,
    val nombres: String = "",
    val nombresError: String? = null,
    val razon: String = "",
    val razonError: String? = null,
    val monto: String = "",
    val montoError: String? = null,
    val isSaving: Boolean = false,
    val saved: Boolean = false
)

