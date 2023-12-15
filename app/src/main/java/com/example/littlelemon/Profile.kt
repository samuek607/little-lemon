package com.example.littlelemon

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littlelemon.data.OnboardingScreen

@Composable
fun Profile(navController: NavHostController, sharedPreferences: SharedPreferences){
    val firstName = sharedPreferences.getString("FirstName", "")
    val lastName = sharedPreferences.getString("LastName", "")
    val email = sharedPreferences.getString("Email", "")
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly){
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Little Lemon Logo", alignment = Alignment.TopCenter)
        Text(text = "Profile information:")
        Text(text = "First Name: $firstName" )
        Text(text = "Last Name: $lastName")
        Text(text = "Email: $email")

        Button(onClick = {
            sharedPreferences.edit().putBoolean("LoggedIn", false).apply()
            sharedPreferences.edit().putString("FirstName", "").apply()
            sharedPreferences.edit().putString("LastName", "").apply()
            sharedPreferences.edit().putString("Email", "").apply()
            navController.navigate(OnboardingScreen.route)
        }, modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()){
            Text(text = "Log out")
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun ProfilePreview(){
    LittleLemonTheme {
        Profile()
    }
}*/
