package edu.ucne.samil_delacruz_ap2_p1.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListBorrameScreen(
    viewModel: ListBorrameViewModel = hiltViewModel(),
    onAddBorrame: () -> Unit,
    onNavigateToEdit: (Int) -> Unit
) {
    val borramesDummy = listOf(1, 2, 3)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddBorrame) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir")
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
                text = "Lista de Borrame",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(borramesDummy) { id ->
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onNavigateToEdit(id) }
                    ) {
                        Text(
                            text = "Registro ID: $id",
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListBorramePreview(){
    ListBorrameScreen(
        onAddBorrame = {},
        onNavigateToEdit = {}
    )
}

data class BorrameModel(
    val numero: Int
)

data class ListBorrameUiState(
    val isLoading: Boolean = false,
    val listBorrame: List<BorrameModel> = emptyList()
)