package com.example.littlelemon.composables

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littlelemon.R
import com.example.littlelemon.data.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Onboarding(navController: NavHostController, sharedPreferences: SharedPreferences) {
    val prompt = remember{ mutableStateOf(0) }
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly){
        var firstName by remember{mutableStateOf("")}
        var lastName by remember{mutableStateOf("")}
        var emailAddress by remember{mutableStateOf("")}
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Little Lemon Logo", alignment = Alignment.TopCenter)
        Text(text = "Let's get to know you")
        TextField(value = firstName, onValueChange = {firstName = it}, label = {Text("First Name")})
        TextField(value = lastName, onValueChange = {lastName = it}, label = {Text("Last Name")})
        TextField(value = emailAddress, onValueChange = {emailAddress = it}, label = {Text("Email Address")})
        Button(onClick = {
                         if (firstName == "" || lastName == "" || emailAddress == ""){
                             prompt.value = 1
                         } else {
                             sharedPreferences.edit().putString("FirstName", firstName).apply()
                             sharedPreferences.edit().putString("LastName", lastName).apply()
                             sharedPreferences.edit().putString("Email", emailAddress).apply()
                             sharedPreferences.edit().putBoolean("LoggedIn",true).apply()
                             prompt.value = 2
                         }
        }, modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()){
            Text(text = "Register")
        }
    }
    if (prompt.value == 1){
        Notification(message = "Registration unsuccessful. Please enter all data.", promptOpen = prompt)
    } else if (prompt.value == 2){
        Notification(message = "Registration successful!", promptOpen = prompt, new = 3)
    }
    if (prompt.value == 3){
        navController.navigate(HomeScreen.route)
    }
}
/*@Preview(showBackground = true)
@Composable
fun OnboardingPreview(){
    LittleLemonTheme {
        Onboarding(navController)
    }
}*/