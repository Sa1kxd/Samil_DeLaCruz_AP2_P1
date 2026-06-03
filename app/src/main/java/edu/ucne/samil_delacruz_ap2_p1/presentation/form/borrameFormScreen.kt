package edu.ucne.samil_delacruz_ap2_p1.presentation.form

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BorrameFormScreen(
    viewModel: BorrameFormViewModel = hiltViewModel(),
    onBack: () -> Unit
){}

@Preview(showBackground = true)
@Composable
fun borrameFormPreview(){
    BorrameFormScreen(
        onBack = {}
    )
}