package com.ahmed.login_jetpack_compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ahmed.login_jetpack_compose.ui.presentation.forgetpassword.ForgetPasswordScreen
import com.ahmed.login_jetpack_compose.ui.presentation.login.LoginScreen


@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        //Login Screen Route
        composable(route = Screen.Login.route) {
            LoginScreen(
                onNavigateToUserData = { userId ->
                    navController.navigate(Screen.UserData.createRoute(userId))
                    {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToForgetPassword = {
                    navController.navigate(Screen.ForgetPassword.route)
                }
            )

        }

        //Forget Password Screen Route
        composable(route = Screen.ForgetPassword.route) {
            ForgetPasswordScreen(onNavigateBack = { navController.popBackStack() })
        }

        // User Data Screen

    }
}