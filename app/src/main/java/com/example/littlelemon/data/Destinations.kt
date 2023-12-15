package com.example.littlelemon.data

interface Destinations{
    val route: String
}

object OnboardingScreen: Destinations{
    override val route = "Onboarding"
}
object HomeScreen: Destinations{
    override val route = "Home"
}
object ProfileScreen: Destinations{
    override val route = "Profile"
}