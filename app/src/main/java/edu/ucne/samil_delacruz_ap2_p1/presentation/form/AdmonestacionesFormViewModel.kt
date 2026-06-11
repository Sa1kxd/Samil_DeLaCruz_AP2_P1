package edu.ucne.samil_delacruz_ap2_p1.presentation.form

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.samil_delacruz_ap2_p1.domain.model.Admonestacion
import edu.ucne.samil_delacruz_ap2_p1.domain.usecase.GetAdmonestacionUseCase
import edu.ucne.samil_delacruz_ap2_p1.domain.usecase.UpsertAdmonestacionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdmonestacionesFormViewModel @Inject constructor(
    private val getAdmonestacionesusecase: GetAdmonestacionUseCase,
    private val upsertAdmonestacionesUseCase: UpsertAdmonestacionUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val AdmoestacionId: Int? = savedStateHandle.get<Int>("id")

    private val _state = MutableStateFlow(AdmonestacionesFormUiState())
    val state: StateFlow<AdmonestacionesFormUiState> = _state.asStateFlow()

    init {
        loadAdmonestacion(AdmoestacionId)
    }

    fun onEvent(event: AdmonestacionesFormUiEvent) {
        when (event) {
            is AdmonestacionesFormUiEvent.Load -> loadAdmonestacion(event.id)
            is AdmonestacionesFormUiEvent.NombresChanged -> _state.update { it.copy(nombres = event.value, nombresError = null) }
            is AdmonestacionesFormUiEvent.RazonChanged -> _state.update { it.copy(razon = event.value, razonError = null) }
            is AdmonestacionesFormUiEvent.MontoChanged -> _state.update { it.copy(monto = event.value, montoError = null) }
            AdmonestacionesFormUiEvent.Save -> onSave()
        }
    }

    private fun loadAdmonestacion(id: Int?) {
        if (id == null || id == 0) {
            _state.update { it.copy(isNew = true, admonestacionId = null) }
            return
        }

        viewModelScope.launch {
            val empleado = getAdmonestacionesusecase(id)
            if (empleado != null) {
                _state.update {
                    it.copy(
                        isNew = false,
                        admonestacionId = AdmoestacionId,
                        nombres = empleado.nombres,
                        razon = empleado.razon,
                        monto = empleado.monto.toString()
                    )
                }
            } else {
                _state.update { it.copy(isNew = true, admonestacionId = null) }
            }
        }
    }

    private fun onSave() {
        viewModelScope.launch {
            _state.update { it.copy(isSaving = true) }

            val empleadoToSave = Admonestacion(
                admonestacionId = _state.value.admonestacionId ?: 0,
                nombres = _state.value.nombres,
                razon = _state.value.razon,
                monto = _state.value.monto.toDoubleOrNull()
                    ?: 0.0
            )

            val result = upsertAdmonestacionesUseCase(empleadoToSave)

            result.onSuccess {
                _state.update { it.copy(isSaving = false, saved = true) }
            }.onFailure { error ->
                _state.update { it.copy(isSaving = false) }
                val msg = error.message ?: ""
                when {
                    msg.contains("nombres", ignoreCase = true) -> _state.update { it.copy(nombresError = msg) }
                    msg.contains("razon", ignoreCase = true) -> _state.update { it.copy(razon = msg) }
                    msg.contains("monto", ignoreCase = true) -> _state.update { it.copy(montoError = msg) }
                }
            }
        }
    }
}