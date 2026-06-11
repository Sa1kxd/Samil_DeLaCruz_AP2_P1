package edu.ucne.samil_delacruz_ap2_p1.presentation.form
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdmonestacionesFormScreen(
    viewModel: AdmonestacionesFormViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.saved) {
        if (state.saved) onBack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (state.isNew) "Nueva Admonestacion" else "Editar admonestacion") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = state.nombres,
                onValueChange = { viewModel.onEvent(AdmonestacionesFormUiEvent.NombresChanged(it)) },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                isError = state.nombresError != null,
                supportingText = state.nombresError?.let { { Text(it) } },
                singleLine = true
            )

            OutlinedTextField(
                value = state.razon,
                onValueChange = { viewModel.onEvent(AdmonestacionesFormUiEvent.RazonChanged(it)) },
                label = { Text("Razon") },
                modifier = Modifier.fillMaxWidth(),
                isError = state.razonError != null,
                supportingText = state.razonError?.let { { Text(it) } },
                singleLine = true
            )

            OutlinedTextField(
                value = state.monto,
                onValueChange = { viewModel.onEvent(AdmonestacionesFormUiEvent.MontoChanged(it)) },
                label = { Text("Monto") },
                modifier = Modifier.fillMaxWidth(),
                isError = state.montoError != null,
                supportingText = state.montoError?.let { { Text(it) } },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { viewModel.onEvent(AdmonestacionesFormUiEvent.Save) },
                modifier = Modifier.fillMaxWidth(),
                enabled = !state.isSaving
            ) {
                if (state.isSaving) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    Text("Guardar")
                }
            }
        }
    }
}