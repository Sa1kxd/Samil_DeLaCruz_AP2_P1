package edu.ucne.samil_delacruz_ap2_p1.presentation.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.samil_delacruz_ap2_p1.domain.model.Admonestacion

@Composable
fun ListAdmonestacionScreen(
    viewModel: AdmonestacionesListViewModel = hiltViewModel(),
    onAddAdmonestacion: () -> Unit,
    onNavigateToEdit: (Int) -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.navigateToCreate) {
        if (state.navigateToCreate) onAddAdmonestacion()
    }

    LaunchedEffect(state.navigateToEditId) {
        state.navigateToEditId?.let { id -> onNavigateToEdit(id) }
    }

    AdmonestacionListBody(state, viewModel::onEvent, onAddAdmonestacion)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdmonestacionListBody(
    state: AdmonestacionesListUiState,
    onEvent: (AdmonestacionesListUiEvent) -> Unit,
    onAddAdmonestacion: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.message) {
        state.message?.let { message ->
            snackbarHostState.showSnackbar(message)
            onEvent(AdmonestacionesListUiEvent.ClearMessage)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Lista de Admonestaciones") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddAdmonestacion) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar Admonestacion")
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier.padding(padding).fillMaxSize()
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                if (state.admonestacion.isEmpty()) {
                    Text(
                        text = "No hay admonestaciones registradas",
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.bodyLarge
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(items = state.admonestacion, key = { it.admonestacionId }) { admonestacion ->
                            AdmonestacionItem(
                                admonestacion = admonestacion,
                                onDelete = { onEvent(AdmonestacionesListUiEvent.Delete(admonestacion.admonestacionId)) },
                                onClick = { onEvent(AdmonestacionesListUiEvent.Edit(admonestacion.admonestacionId)) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdmonestacionItem(admonestacion: Admonestacion, onDelete: () -> Unit, onClick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth().testTag("admonestacion_item_${admonestacion.admonestacionId}"),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = admonestacion.nombres, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(text = "Razon: ${admonestacion.razon}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Monto: RD$ ${admonestacion.monto}", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
            }
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar", tint = MaterialTheme.colorScheme.error)
            }
        }
    }
}