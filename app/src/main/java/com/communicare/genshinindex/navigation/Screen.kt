package com.communicare.genshinindex.navigation

sealed class Screen(val route: String){
    object Home: Screen("Home")
    object Profile: Screen("Profile")
    object Detail: Screen("Home/{characterId}"){
        fun createRoute(characterId: Int) = "Home/$characterId"
    }
}
