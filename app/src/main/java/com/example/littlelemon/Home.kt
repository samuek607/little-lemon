package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littlelemon.data.ProfileScreen
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun Home(navController: NavHostController){
    Row (modifier = Modifier.fillMaxWidth()){
        Image(painter = painterResource(
            id = R.drawable.profile),
            contentDescription = "Little Lemon Logo",
            alignment = Alignment.TopStart,
            modifier = Modifier.padding(top = 15.dp).clickable { navController.navigate(ProfileScreen.route) }
        )
    }
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Little Lemon Logo", alignment = Alignment.Center)
    }
}

/*@Preview(showBackground = true)
@Composable
fun HomePreview(){
    LittleLemonTheme {
        Home()
    }
}*/