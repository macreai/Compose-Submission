package com.communicare.genshinindex

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.communicare.genshinindex.navigation.Screen
import com.communicare.genshinindex.ui.components.BottomBar
import com.communicare.genshinindex.ui.screen.DetailScreen
import com.communicare.genshinindex.ui.screen.HomeScreen
import com.communicare.genshinindex.ui.screen.ProfileScreen

@Composable
fun GenshinIndexApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        },
        modifier = modifier
    ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(innerPadding)
            ){
                composable(Screen.Home.route){
                    HomeScreen(
                        navigateToDetail = { characterId ->
                            navController.navigate(Screen.Detail.createRoute(characterId))
                        }
                    )
                }
                composable(Screen.Profile.route){
                    ProfileScreen()
                }
                composable(
                    route = Screen.Detail.route,
                    arguments = listOf(navArgument("characterId"){ type = NavType.IntType }),
                ){
                    val id = it.arguments?.getInt("characterId") ?: -1
                    DetailScreen(
                        characterId = id,
                        navigateBack = {
                            navController.navigateUp()
                        }
                    )
                }
            }
        }


}