package edu.ucne.samil_delacruz_ap2_p1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.ucne.samil_delacruz_ap2_p1.presentation.form.BorrameFormScreen
import edu.ucne.samil_delacruz_ap2_p1.presentation.list.ListBorrameScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "borrame_list"
    ) {
        composable("borrame_list") {
            ListBorrameScreen(
                onAddBorrame = {
                    navController.navigate("borrame_form/0")
                },
                onNavigateToEdit = { id ->
                    navController.navigate("borrame_form/$id")
                }
            )
        }

        composable(
            route = "borrame_form/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            BorrameFormScreen(
                onBack = {
                    navController.navigate("borrame_list") {
                        popUpTo("borrame_list") {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}