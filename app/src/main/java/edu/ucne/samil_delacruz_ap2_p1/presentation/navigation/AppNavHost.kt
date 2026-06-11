package edu.ucne.samil_delacruz_ap2_p1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import edu.ucne.samil_delacruz_ap2_p1.presentation.list.ListAdmonestacionScreen
import edu.ucne.samil_delacruz_ap2_p1.presentation.form.AdmonestacionesFormScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.AdmonestacionesList
    ) {
        composable<Screen.AdmonestacionesList> {
            ListAdmonestacionScreen(
                onAddAdmonestacion = {
                    navController.navigate(Screen.AdmonestacionesForm(id = 0))
                },
                onNavigateToEdit = { id ->
                    navController.navigate(Screen.AdmonestacionesForm(id = id))
                }
            )
        }

        composable<Screen.AdmonestacionesForm> { backStackEntry ->

            AdmonestacionesFormScreen(
                onBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}