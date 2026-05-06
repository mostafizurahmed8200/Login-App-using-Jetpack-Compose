package com.ahmed.login_jetpack_compose.navigation

sealed class Screen(val route: String) {

    object Login : Screen("login")
    object ForgetPassword : Screen("forget_password")
    object UserData : Screen("user_data/{userId}") {
        fun createRoute(userId: String)="user_data/$userId"
    }

}
