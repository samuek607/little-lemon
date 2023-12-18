package com.example.littlelemon

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.composables.Onboarding
import com.example.littlelemon.data.HomeScreen
import com.example.littlelemon.data.OnboardingScreen
import com.example.littlelemon.data.ProfileScreen

@Composable
fun Navigation(sharedPreferences: SharedPreferences, database: AppDatabase) {
    val navController = rememberNavController()
    val loggedIn = sharedPreferences.getBoolean("LoggedIn", false)
    val startScreen = if (loggedIn){
        OnboardingScreen.route
    }else {
        HomeScreen.route
    }

    NavHost(navController = navController, startDestination = startScreen){
        composable(OnboardingScreen.route){
            Onboarding(navController, sharedPreferences)
        }
        composable(HomeScreen.route){
            Home(navController, database)
        }
        composable(ProfileScreen.route){
            Profile(navController, sharedPreferences)
        }
    }
}