package edu.ucne.samil_delacruz_ap2_p1.presentation.form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BorrameFormScreen(
    viewModel: BorrameFormViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    BorrameFormBody(
        onBack = onBack,
        onDelete = {},
        onSave = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BorrameFormBody(
    onBack: () -> Unit,
    onDelete: () -> Unit,
    onSave: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Form borrame") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onDelete,
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Tengo hambre",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Button(
                onClick = onSave,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BorrameFormPreview(){
    BorrameFormBody(
        onBack = {},
        onDelete = {},
        onSave = {}
    )
}