package com.example.littlelemon

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.composables.Onboarding
import com.example.littlelemon.data.HomeScreen
import com.example.littlelemon.data.OnboardingScreen
import com.example.littlelemon.data.ProfileScreen
import com.example.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("AppUsage", ComponentActivity.MODE_PRIVATE)
        setContent {
            LittleLemonTheme {
                Navigation(sharedPreferences)
            }
        }
    }
}