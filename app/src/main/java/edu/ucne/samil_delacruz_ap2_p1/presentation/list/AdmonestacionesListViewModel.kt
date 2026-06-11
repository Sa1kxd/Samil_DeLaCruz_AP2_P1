package edu.ucne.samil_delacruz_ap2_p1.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.samil_delacruz_ap2_p1.domain.model.Admonestacion
import edu.ucne.samil_delacruz_ap2_p1.domain.usecase.DeleteAdmonestacionUseCase
import edu.ucne.samil_delacruz_ap2_p1.domain.usecase.ObserveAdmonestacionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdmonestacionesListViewModel @Inject constructor(
private val observeAdmonestacionusecase : ObserveAdmonestacionUseCase,
private val deleteAdmonestacionusecase : DeleteAdmonestacionUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(AdmonestacionesListUiState())
    val state: StateFlow<AdmonestacionesListUiState> = _state.asStateFlow()

    init{
        onEvent(AdmonestacionesListUiEvent.Load)
    }

    fun onEvent(event: AdmonestacionesListUiEvent){
        when(event){
            AdmonestacionesListUiEvent.Load -> observeEmpleados()
            is AdmonestacionesListUiEvent.Delete -> deleteEmpleado(event.id)
            is AdmonestacionesListUiEvent.ShowMessage -> _state.update { it.copy(message = event.message) }
            AdmonestacionesListUiEvent.ClearMessage -> _state.update { it.copy(message = null) }
            AdmonestacionesListUiEvent.CreateNew -> _state.update { it.copy(navigateToCreate = true) }
            is AdmonestacionesListUiEvent.Edit -> _state.update { it.copy(navigateToEditId = event.id) }
        }
    }

    fun observeEmpleados(){
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            observeAdmonestacionusecase().collectLatest { list ->
                _state.update { it.copy(isLoading = false, admonestacion = list, navigateToCreate = false, navigateToEditId = null) }
            }
        }
    }

    fun deleteEmpleado(id: Int){
        viewModelScope.launch {
            try {
                deleteAdmonestacionusecase(id)
                AdmonestacionesListUiEvent.ShowMessage("Admonestacion eliminada")
            }catch (e: Exception){
                AdmonestacionesListUiEvent.ShowMessage("Erro al eliminar")
            }
        }
    }
}