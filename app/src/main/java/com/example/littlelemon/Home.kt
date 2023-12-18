package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littlelemon.data.ProfileScreen
import com.example.littlelemon.ui.theme.llYellow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController, database: AppDatabase){
    var search by remember{ mutableStateOf("") }
    val roomMenuItems = database.menuItemDao().getAll()
    var menuItems by remember{ mutableStateOf(roomMenuItems.value!!) }
    Column {
        Row (modifier = Modifier.fillMaxWidth()){
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Little Lemon Logo", alignment = Alignment.Center)
            Image(painter = painterResource(
                id = R.drawable.profile),
                contentDescription = "Profile Image",
                alignment = Alignment.TopEnd,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .clickable { navController.navigate(ProfileScreen.route) }
            )
        }
        Surface (
            Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, top = 20.dp, bottom = 15.dp)){
            Column {
                Text("Little Lemon", style = MaterialTheme.typography.headlineLarge, color = llYellow)
                Row {
                    Column (modifier = Modifier.fillMaxWidth(0.6f)){
                        Text("Chicago", style = MaterialTheme.typography.headlineMedium)
                        Text("We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist")
                    }
                    Image(painter = painterResource(R.drawable.hero_image),
                        contentDescription = "Hero Image",
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .clip(shape = RoundedCornerShape(5.dp))
                    )
                }
                TextField(value = search, onValueChange = {search = it}, placeholder = {Text("Enter Search Phrase")})
            }
        }
        if (search.isNotEmpty()){
            MenuItems(menuItems = menuItems.filter {
                it.title.lowercase().contains(search.lowercase()) ||
                        it.description.lowercase().contains(search.lowercase())
            })
        } else {
            MenuItems(roomMenuItems.value!!)
        }
    }
}
@Composable
fun MenuItems(menuItems: List<MenuItemRoom>){
    var category by remember{ mutableStateOf("") }
    var selected by remember{ mutableStateOf(0) }
    Column () {
        Text("ORDER FOR DELIVERY!")
        Row {
            Button(onClick = {
                category = if (category != "starters"){
                    "starters"
                } else {
                    ""
                }
            }) {
                Text("Starters")
            }
            Button(onClick = {
                category = if (category != "mains"){
                    "mains"
                } else {
                    ""
                }
            }) {
                Text("Mains")
            }
            Button(onClick = {
                category = if (category != "desserts"){
                    "desserts"
                } else {
                    ""
                }
            }) {
                Text("Desserts")
            }
            Button(onClick = {
                category = if (category != "drinks"){
                    "drinks"
                } else {
                    ""
                }
            }) {
                Text("Drinks")
            }
        }
        LazyColumn{
            items(menuItems.filter { it.category == category }){
                MenuItem(menuItem = it)
            }
        }
    }

}
@Composable
fun MenuItem(menuItem: MenuItemRoom){
    val img = when (menuItem.id){
        1 -> R.drawable.greek_salad
        2 -> R.drawable.lemon_dessert
        3 -> R.drawable.grilled_fish
        4 -> R.drawable.pasta
        else -> R.drawable.bruschetta
    }
    Card {
        Row (Modifier.fillMaxWidth()){
            Column (
                Modifier
                    .fillMaxWidth(0.75f)
                    .padding(10.dp)){
                Text(menuItem.title)
                Text(menuItem.description)
                Text(menuItem.price.toString())
            }
            Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                Image(painter = painterResource(id = img), contentDescription = "Image of ${menuItem.title}",
                    modifier = Modifier.padding(5.dp))
            }
        }
        Divider(
            Modifier
                .height(1.dp)
                .fillMaxWidth())
    }
}

/*@Preview(showBackground = true)
@Composable
fun HomePreview(){
    LittleLemonTheme {
        Home()
    }
}*/